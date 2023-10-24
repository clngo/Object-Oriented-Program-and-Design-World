public interface MoveEntity {
    boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler);

    Point nextPosition(WorldModel world, Point destPos);
}
