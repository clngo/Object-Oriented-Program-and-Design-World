import processing.core.PImage;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class Dude extends ScheduleEntity implements ExecuteEntity, MoveEntity, TransformEntity{
    private int resourceLimit;

    public Dude(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, int resourceLimit){
        super(id, position, images, actionPeriod, animationPeriod);
        this.resourceLimit = resourceLimit;
    }

    public int getResourceLimit() {
        return resourceLimit;
    }

    @Override
    public Point nextPosition(WorldModel world, Point destPos) {
//        PathingStrategy s = new SingleStepPathingStrategy();
        PathingStrategy s = new AStarPathingStrategy();

        Predicate<Point> canPassThrough = p -> world.withinBounds(p) && !(world.isOccupied(p) && world.getOccupancyCell(p).getClass() != Stump.class);

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
