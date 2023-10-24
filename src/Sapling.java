import processing.core.PImage;

import java.util.List;

public class Sapling extends Plant {
    public static final double SAPLING_ACTION_ANIMATION_PERIOD = 1.000; // have to be in sync since grows and gains health at same time
    public static final int SAPLING_HEALTH_LIMIT = 5;
    private int healthLimit;

    public Sapling(String id, Point position, List<PImage> images) {
        super(id, position, images, SAPLING_ACTION_ANIMATION_PERIOD, SAPLING_ACTION_ANIMATION_PERIOD, 0);
        this.healthLimit = this.SAPLING_HEALTH_LIMIT;
    }

    @Override
    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (this.getHealth() <= 0) {
            Stump stump = new Stump(WorldModel.STUMP_KEY + "_" + this.getId(), this.getPosition(), imageStore.getImageList(WorldModel.STUMP_KEY));

            world.removeEntity(scheduler, this);

            world.addEntity(stump);

            return true;
        } else if (this.getHealth() >= this.healthLimit) {
            Tree tree = new Tree(WorldModel.TREE_KEY + "_" + this.getId(), this.getPosition(), imageStore.getImageList(WorldModel.TREE_KEY), getNumFromRange(Tree.TREE_ACTION_MAX, Tree.TREE_ACTION_MIN), getNumFromRange(Tree.TREE_ANIMATION_MAX, Tree.TREE_ANIMATION_MIN), getIntFromRange(Tree.TREE_HEALTH_MAX, Tree.TREE_HEALTH_MIN));

            world.removeEntity(scheduler, this);

            world.addEntity(tree);
            tree.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        this.increaseHealth();
        if (!this.transform(world, scheduler, imageStore)) {
            scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
        }

    }



}