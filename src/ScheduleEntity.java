import processing.core.PImage;

import java.util.List;

public abstract class ScheduleEntity extends Entity{
    private double actionPeriod;
    private double animationPeriod;

    public ScheduleEntity(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod) {
        super(id, position, images);
        this.actionPeriod = actionPeriod;
        this.animationPeriod = animationPeriod;

    }

    public double getActionPeriod() {
        return actionPeriod;
    }

    public double getAnimationPeriod() {
        return animationPeriod;
    }


    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.actionPeriod);
        scheduler.scheduleEvent(this, new Animation(this, 0), this.animationPeriod);
    }


}
