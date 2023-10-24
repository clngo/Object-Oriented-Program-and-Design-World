import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Dude_NotFull extends Dude{
    private int resourceCount;
    public Dude_NotFull(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, int resourceLimit){
        super(id, position, images, actionPeriod, animationPeriod, resourceLimit);
        this.resourceCount = 0;
    }
    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> target = world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(Tree.class, Sapling.class, Redhand.class)));

        if (target.isEmpty() || !this.moveTo(world, target.get(), scheduler) || !this.transform(world, scheduler, imageStore)) {
            scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
        }
    }

    @Override
    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (this.resourceCount >= this.getResourceLimit()) {
            Dude_Full dude = new Dude_Full(super.getId(), this.getPosition(), this.getImages(), this.getActionPeriod(), this.getAnimationPeriod(), this.getResourceLimit());

            world.removeEntity(scheduler, this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(dude);
            dude.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }

    @Override
    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (adjacent(this.getPosition(), target.getPosition())) {
            if (target instanceof Plant planttarget) {
                this.resourceCount += 1;
                planttarget.decreaseHealth();
            }
            if (target instanceof Redhand redhand) {
                world.removeEntity(scheduler, redhand);
                scheduler.unscheduleAllEvents(redhand);
                System.out.println("The Hand has been slain");
                redhand.setExists(false);
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




}
