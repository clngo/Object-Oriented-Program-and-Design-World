import jdk.jshell.Snippet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Bunny extends ScheduleEntity implements ExecuteEntity, MoveEntity{

    public Bunny(String id, Point position, List<PImage> images) {
        super(id, position, images, 1.0, 1.0);
    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> bunnyTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(Redhand.class)));

        if (bunnyTarget.isPresent() && bunnyTarget.get() instanceof Redhand redhand) {
            if (this.moveTo(world, bunnyTarget.get(), scheduler)) {
                //                Entity sapling = createSapling(WorldModel.SAPLING_KEY + "_" + fairyTarget.get().id, tgtPos, imageStore.getImageList(WorldModel.SAPLING_KEY), 0);
                world.removeEntity(scheduler, redhand);
                scheduler.unscheduleAllEvents(redhand);
                System.out.println("The Hand has been slain");
                redhand.setExists(false);
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
