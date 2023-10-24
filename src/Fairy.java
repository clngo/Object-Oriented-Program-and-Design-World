import jdk.jshell.Snippet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Fairy extends ScheduleEntity implements ExecuteEntity, MoveEntity{

    public Fairy(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod) {
        super(id, position, images, actionPeriod, animationPeriod);
    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> fairyTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(Stump.class, Watermelon.class)));

        if (fairyTarget.isPresent() && fairyTarget.get() instanceof Stump) {
            Point tgtPos = fairyTarget.get().getPosition();

            if (this.moveTo(world, fairyTarget.get(), scheduler)) {

                //                Entity sapling = createSapling(WorldModel.SAPLING_KEY + "_" + fairyTarget.get().id, tgtPos, imageStore.getImageList(WorldModel.SAPLING_KEY), 0);
                Sapling sapling = new Sapling(WorldModel.SAPLING_KEY + "_" + fairyTarget.get().getId(), tgtPos, imageStore.getImageList(WorldModel.SAPLING_KEY));

                world.addEntity(sapling);
                sapling.scheduleActions(scheduler, world, imageStore);
            }
        }
        if (fairyTarget.isPresent() && fairyTarget.get() instanceof Watermelon) {
            Point tgtPos = fairyTarget.get().getPosition();

            if (this.moveTo(world, fairyTarget.get(), scheduler)) {
                System.out.println("Basil is revived!");
                Basil basil = new Basil(WorldModel.BASIL_KEY + "_" +  fairyTarget.get().getId(), tgtPos, imageStore.getImageList(WorldModel.BASIL_KEY));
                world.addEntity(basil);
                basil.scheduleActions(scheduler, world, imageStore);
            }
        }
        scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
    }

    @Override
    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (adjacent(this.getPosition(), target.getPosition())) {
            world.removeEntity(scheduler, target);
            return true;
        } else {
            Point nextPos = this.nextPosition(world, target.getPosition());

            if (!this.getPosition().equals(nextPos)) {
                world.moveEntity(scheduler, this, nextPos);
            }
            return false;
        }
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
