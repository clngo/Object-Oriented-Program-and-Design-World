import jdk.jshell.Snippet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Redhand extends ScheduleEntity implements ExecuteEntity, MoveEntity{

    private boolean exists = true;
    public Redhand(String id, Point position, List<PImage> images) {
        super(id, position, images, 0.65, 0.65);
    }
    public boolean getExists() {
        return this.exists;
    }
    public void setExists(boolean cond) {
        this.exists = cond;
    }
    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        //Basil -> watermelon; Dude does Tree -> Stump
        Optional<Entity> target = world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(Basil.class)));

        if (target.isPresent() && target.get() instanceof Basil basil) {
            if (this.moveTo(world, target.get(), scheduler)) {
                basil.decreaseHealth();
            }
        }
        scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
    }

    @Override
    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (adjacent(this.getPosition(), target.getPosition())) {
            if (target instanceof Basil basil) {
                //System.out.println("asfjalksdjfadlf");
                basil.decreaseHealth();
            }
            if (target instanceof Plant planttarget) {
                planttarget.decreaseHealth();
            }

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
