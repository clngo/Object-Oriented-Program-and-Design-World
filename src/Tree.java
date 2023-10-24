import java.util.*;

import processing.core.PImage;
public class Tree extends Plant{
    public static final double TREE_ANIMATION_MAX = 0.600;
    public static final double TREE_ANIMATION_MIN = 0.050;
    public static final double TREE_ACTION_MAX = 1.400;
    public static final double TREE_ACTION_MIN = 1.000;
    public static final int TREE_HEALTH_MAX = 3;
    public static final int TREE_HEALTH_MIN = 1;

    public Tree(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, int health) {
        super(id, position, images, actionPeriod, animationPeriod, health);
    }

    @Override
    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (this.getHealth() <= 0) {
            Stump stump = new Stump(WorldModel.STUMP_KEY + "_" + this.getId(), this.getPosition(), imageStore.getImageList(WorldModel.STUMP_KEY));

            world.removeEntity(scheduler, this);

            world.addEntity(stump);

            return true;
        }
        return false;
    }

    public void moletransform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        Bunny bunny = new Bunny(WorldModel.BUNNY_KEY, this.getPosition(), imageStore.getImageList(WorldModel.BUNNY_KEY));
        world.removeEntity(scheduler, this);
        world.addEntity(bunny);
        bunny.scheduleActions(scheduler, world, imageStore);

    }
    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

        if (!this.transform(world, scheduler, imageStore)) {

            scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
        }
    }







}
