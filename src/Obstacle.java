import processing.core.PImage;

import java.util.List;

public class Obstacle extends ScheduleEntity {
    public Obstacle(String id, Point position, List<PImage> images, double animationPeriod) {
        super(id, position, images, 0, animationPeriod);
    }

    @Override
    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this, new Animation(this, 0), this.getAnimationPeriod());
    }
}
