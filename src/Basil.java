import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Basil extends Plant implements MoveEntity{

    private Entity previousTarget;
    private Entity target;
    private Redhand redhand;

    public Basil(String id, Point position, List<PImage> images){
        super(id, position, images, 0.7, 0.5, 3);
        this.target = null;
    }
    public Entity acquireTarget(List<Entity> entities) {
        // basil acquires a target of a tree.
        List<Entity> potentialTargets = entities.stream()
                .filter(entity -> (entity instanceof Tree) || (entity instanceof Stump) && entity != previousTarget)
                .toList();
        if (potentialTargets.isEmpty()) {
            return null;
        }
        else {
            return potentialTargets.get(getIntFromRange(potentialTargets.size(), 0));
        }
    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        if (this.redhand == null || (this.redhand.getPosition().equals(new Point(-1,-1)))) {
            while (true) {
                int randx = this.getIntFromRange(39, 0);
                int randy = this.getIntFromRange(20, 0);
                Point randp = new Point(randx, randy);
                if (world.withinBounds(randp) && !(world.isOccupied(randp))){
                    redhand = new Redhand("redhand", randp, imageStore.getImageList(WorldModel.REDHAND_KEY));
                    world.addEntity(redhand);
                    redhand.scheduleActions(scheduler, world, imageStore);
                    break;
                }
            }
        }
        if (!(this.redhand.getExists())){
            while (true) {
                int randx = this.getIntFromRange(39, 0);
                int randy = this.getIntFromRange(20, 0);
                Point randp = new Point(randx, randy);
                if (world.withinBounds(randp) && !(world.isOccupied(randp))){
                    redhand = new Redhand("redhand", randp, imageStore.getImageList(WorldModel.REDHAND_KEY));
                    world.addEntity(redhand);
                    redhand.scheduleActions(scheduler, world, imageStore);
                    break;
                }
            }
        }
        //Check if the target exists. If it doesn't exist, get a new one.
        if (this.target == null || (this.target.getPosition().equals(new Point(-1,-1)))) {
            this.target = acquireTarget(world.getEntities().stream().toList());
        }
        if (this.moveTo(world, this.target, scheduler)) {
            Point tgtPos = this.getPosition();
            List<Point> possible = List.of(
                    new Point(tgtPos.x, tgtPos.y - 1),
                    new Point(tgtPos.x, tgtPos.y + 1),
                    new Point(tgtPos.x - 1, tgtPos.y),
                    new Point(tgtPos.x + 1, tgtPos.y));
            int req = 0;
            Point actual = possible.get(0);
            for (Point point: possible) {
                if (world.withinBounds(point) && !(world.isOccupied(point))) {
                    req += 1;
                    actual = point;
                }
            }
            if (req >= 2) {
                Sapling sapling = new Sapling(WorldModel.SAPLING_KEY + "_" + this.target.getId(), actual, imageStore.getImageList(WorldModel.SAPLING_KEY));
                world.addEntity(sapling);
                sapling.scheduleActions(scheduler, world, imageStore);
                System.out.println("Basil planted a sapling.");
            }
            this.previousTarget = target;
            this.target = acquireTarget(world.getEntities().stream().toList());
        }
//        Optional<Entity> basilTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(Tree.class, Stump.class, Sapling.class)));
        if (!(this.transform(world, scheduler, imageStore))) {
            scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
        }



    }
    @Override
    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        //Transforms Basil into a watermelon from a hand.
        if (this.getHealth() <= 0) {
            System.out.println("Basil became a watermelon!");

            Watermelon watermelon = new Watermelon("watermelon", this.getPosition(), imageStore.getImageList(WorldModel.WATERMELON_KEY));
            world.removeEntity(scheduler, this);
            world.addEntity(watermelon);
            watermelon.scheduleActions(scheduler, world, imageStore);

            world.removeEntity(scheduler, redhand);
            scheduler.unscheduleAllEvents(redhand);
            this.redhand.setExists(false);
            Optional<Entity> check = world.findNearest(this.getPosition(), new ArrayList<>(List.of(Redhand.class)));
            if (check.isPresent()){
                world.removeEntity(scheduler, check.get());
            }
            return true;
        }

        return false;
    }
    @Override
    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
//        if (!pathToTarget.isEmpty() || adjacent(this.getPosition(), target.getPosition())) {
//        Point tgtPos = target.getPosition();
//        List<Point> possible = List.of(
//                new Point(tgtPos.x, tgtPos.y - 1),
//                new Point(tgtPos.x, tgtPos.y + 1),
//                new Point(tgtPos.x - 1, tgtPos.y),
//                new Point(tgtPos.x + 1, tgtPos.y));
        if (adjacent(this.getPosition(), target.getPosition())) {
            return true;
        }
        Point nextPos = this.nextPosition(world, target.getPosition());

        if (!this.getPosition().equals(nextPos)) {
            world.moveEntity(scheduler, this, nextPos);
        }
        return false;
    }
    @Override
    public Point nextPosition(WorldModel world, Point destPos) {
        PathingStrategy s = new AStarPathingStrategy();

        Predicate<Point> canPassThrough = p -> world.withinBounds(p) && !(world.isOccupied(p));

        BiPredicate<Point, Point> withinReach = (p1, p2) -> (Math.abs(p2.x - p1.x) + Math.abs(p2.y - p1.y)) == 1;

        Function<Point, Stream<Point>> potentialNeighbors = PathingStrategy.CARDINAL_NEIGHBORS;
        List<Point> list = s.computePath(this.getPosition(), destPos, canPassThrough, withinReach, potentialNeighbors);
        if (list == null) {
            return this.getPosition();
        }
        if (list.size() > 0) {
            return list.get(0);
        }
        else {
            return this.getPosition();
        }
    }
}
