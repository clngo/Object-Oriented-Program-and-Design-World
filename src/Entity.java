import java.util.*;

import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */
public abstract class Entity {
    //    private EntityKind kind;
//    public EntityKind getKind() {
//        return this.kind;
//    }
    private String id;
    private Point position;

    private List<PImage> images;
    private int imageIndex;
//    private int resourceLimit;
//    private int resourceCount;
//    private double actionPeriod;
//    private double animationPeriod;
//    private int health;
//    public int getHealth() {
//        return this.health;
//    }
//    private int healthLimit;
//    public static final double SAPLING_ACTION_ANIMATION_PERIOD = 1.000; // have to be in sync since grows and gains health at same time
//    public static final int SAPLING_HEALTH_LIMIT = 5;

    public Entity(String id, Point position, List<PImage> images) {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
//        this.imageIndex = 0;
//        this.resourceLimit = resourceLimit;
//        this.resourceCount = resourceCount;
//        this.actionPeriod = actionPeriod;
//        this.animationPeriod = animationPeriod;
//        this.health = health;
//        this.healthLimit = healthLimit;
    }

    public String getId() {
        return this.id;
    }

    public Point getPosition() {
        return this.position;
    }

    public void setPosition(Point new_position) {
        this.position = new_position;
    }

    public List<PImage> getImages() {
        return this.images;
    }

    public int getImageIndex() {
        return this.imageIndex;
    }
    public void setImageIndex(int num) {this.imageIndex = num;}


    /**
     * Helper method for testing. Preserve this functionality while refactoring.
     */
    public String log() {
        return this.id.isEmpty() ? null :
                String.format("%s %d %d %d", this.id, this.position.x, this.position.y, this.imageIndex);
    }

    public void nextImage() {
        this.imageIndex = this.imageIndex + 1;
    }


    public boolean adjacent(Point p1, Point p2) {
        return (p1.x == p2.x && Math.abs(p1.y - p2.y) == 1) || (p1.y == p2.y && Math.abs(p1.x - p2.x) == 1);
    }


    public int getIntFromRange(int max, int min) {
        Random rand = new Random();
        return min + rand.nextInt(max - min);
    }

    public double getNumFromRange(double max, double min) {
        Random rand = new Random();
        return min + rand.nextDouble() * (max - min);
    }


//    public Activity createActivityAction(WorldModel world, ImageStore imageStore) {
//        return new Activity(this, world, imageStore);
//    }


//    public Animation createAnimationAction(int repeatCount) {
//        return new Animation(this, repeatCount);
//    }
//    public  double getAnimationPeriod() {
//        switch (this.kind) {
//            case DUDE_FULL:
//            case DUDE_NOT_FULL:
//            case OBSTACLE:
//            case FAIRY:
//            case SAPLING:
//            case TREE:
//                return this.animationPeriod;
//            default:
//                throw new UnsupportedOperationException(String.format("getAnimationPeriod not supported for %s", this.kind));
//        }
//    }

//    public static Entity createHouse(String id, Point position, List<PImage> images) {
//        return new Entity(EntityKind.HOUSE, id, position, images, 0, 0, 0, 0, 0, 0);
//    }

//    public static Entity createObstacle(String id, Point position, double animationPeriod, List<PImage> images) {
//        return new Entity(EntityKind.OBSTACLE, id, position, images, 0, 0, 0, animationPeriod, 0, 0);
//    }

//    public static Entity createTree(String id, Point position, double actionPeriod, double animationPeriod, int health, List<PImage> images) {
//        return new Entity(EntityKind.TREE, id, position, images, 0, 0, actionPeriod, animationPeriod, health, 0);
//    }

//    public static Entity createStump(String id, Point position, List<PImage> images) {
//        return new Entity(EntityKind.STUMP, id, position, images, 0, 0, 0, 0, 0, 0);
//    }

    // health starts at 0 and builds up until ready to convert to Tree
//    public static Entity createSapling(String id, Point position, List<PImage> images, int health) {
//        return new Entity(EntityKind.SAPLING, id, position, images, 0, 0, SAPLING_ACTION_ANIMATION_PERIOD, SAPLING_ACTION_ANIMATION_PERIOD, 0, SAPLING_HEALTH_LIMIT);
//    }


//    public static Entity createFairy(String id, Point position, double actionPeriod, double animationPeriod, List<PImage> images) {
//        return new Entity(EntityKind.FAIRY, id, position, images, 0, 0, actionPeriod, animationPeriod, 0, 0);
//    }

//    public Point nextPositionDude(WorldModel world, Point destPos) {
//        int horiz = Integer.signum(destPos.x - this.position.x);
//        Point newPos = new Point(this.position.x + horiz, this.position.y);
//
//        if (horiz == 0 || world.isOccupied(newPos) && world.getOccupancyCell(newPos).kind != EntityKind.STUMP) {
//            int vert = Integer.signum(destPos.y - this.position.y);
//            newPos = new Point(this.position.x, this.position.y + vert);
//
//            if (vert == 0 || world.isOccupied(newPos) && world.getOccupancyCell(newPos).kind != EntityKind.STUMP) {
//                newPos = this.position;
//            }
//        }
//
//        return newPos;
//    }

//    public boolean moveToNotFull(WorldModel world, Entity target, EventScheduler scheduler) {
//        if (adjacent(this.position, target.position)) {
//            this.resourceCount += 1;
//            target.health--;
//            return true;
//        } else {
//            Point nextPos = this.nextPositionDude(world, target.position);
//
//            if (!this.position.equals(nextPos)) {
//                world.moveEntity(scheduler, this, nextPos);
//            }
//            return false;
//        }
//    }

//    public boolean moveToFairy(WorldModel world, Entity target, EventScheduler scheduler) {
//        if (adjacent(this.getPosition(), target.getPosition())) {
//            world.removeEntity(scheduler, target);
//            return true;
//        } else {
//            Point nextPos = nextPositionFairy(world, target.getPosition());
//
//            if (!this.getPosition().equals(nextPos)) {
//                world.moveEntity(scheduler, this, nextPos);
//            }
//            return false;
//        }
//    }
//    public boolean moveToFull(WorldModel world, Entity target, EventScheduler scheduler) {
//        if (adjacent(this.getPosition(), target.getPosition())) {
//            return true;
//        } else {
//            Point nextPos = this.nextPositionDude(world, target.getPosition());
//
//            if (!this.getPosition().equals(nextPos)) {
//                world.moveEntity(scheduler, this, nextPos);
//            }
//            return false;
//        }
//    }

//    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
//        switch (this.kind) {
//            case DUDE_FULL:
//                scheduler.scheduleEvent(this, createActivityAction(world, imageStore), this.actionPeriod);
//                scheduler.scheduleEvent(this, this.createAnimationAction(0), this.getAnimationPeriod());
//                break;
//
//            case DUDE_NOT_FULL:
//                scheduler.scheduleEvent(this, this.createActivityAction(world, imageStore), this.actionPeriod);
//                scheduler.scheduleEvent(this, this.createAnimationAction(0), this.getAnimationPeriod());
//                break;
//
//            case OBSTACLE:
//                scheduler.scheduleEvent(this, this.createAnimationAction(0), this.getAnimationPeriod());
//                break;
//
//            case FAIRY:
//                scheduler.scheduleEvent(this, this.createActivityAction(world, imageStore), this.actionPeriod);
//                scheduler.scheduleEvent(this, this.createAnimationAction(0), this.getAnimationPeriod());
//                break;
//
//            case SAPLING:
//                scheduler.scheduleEvent(this, this.createActivityAction(world, imageStore), this.actionPeriod);
//                scheduler.scheduleEvent(this, this.createAnimationAction(0), this.getAnimationPeriod());
//                break;
//
//            case TREE:
//                scheduler.scheduleEvent(this, this.createActivityAction(world, imageStore), this.actionPeriod);
//                scheduler.scheduleEvent(this, this.createAnimationAction(0), this.getAnimationPeriod());
//                break;
//
//            default:
//        }
//    }
//    public static Entity createDudeNotFull(String id, Point position, double actionPeriod, double animationPeriod, int resourceLimit, List<PImage> images) {
//        return new Entity(EntityKind.DUDE_NOT_FULL, id, position, images, resourceLimit, 0, actionPeriod, animationPeriod, 0, 0);
//    }
//    public static Entity createDudeFull(String id, Point position, double actionPeriod, double animationPeriod, int resourceLimit, List<PImage> images) {
//        return new Entity(EntityKind.DUDE_FULL, id, position, images, resourceLimit, 0, actionPeriod, animationPeriod, 0, 0);
//    }

//    public void transformFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        Entity dude = createDudeNotFull(this.id, this.getPosition(), this.actionPeriod, this.animationPeriod, this.resourceLimit, this.images);
//
//        world.removeEntity(scheduler, this);
//
//        world.addEntity(dude);
//        dude.scheduleActions(scheduler, world, imageStore);
//    }
//    public boolean transformNotFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        if (this.resourceCount >= this.resourceLimit) {
//            Entity dude = createDudeFull(this.id, this.getPosition(), this.actionPeriod, this.animationPeriod, this.resourceLimit, this.images);
//
//            world.removeEntity(scheduler, this);
//            scheduler.unscheduleAllEvents(this);
//
//            world.addEntity(dude);
//            dude.scheduleActions(scheduler, world, imageStore);
//
//            return true;
//        }
//
//        return false;
//    }

//    public void executeDudeFullActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
//        Optional<Entity> fullTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(EntityKind.HOUSE)));
//
//        if (fullTarget.isPresent() && this.moveToFull(world, fullTarget.get(), scheduler)) {
//            transformFull(world, scheduler, imageStore);
//        } else {
//            scheduler.scheduleEvent(this, createActivityAction(world, imageStore), this.actionPeriod);
//        }
//    }

//    public void executeDudeNotFullActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
//        Optional<Entity> target = world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(EntityKind.TREE, EntityKind.SAPLING)));
//
//        if (target.isEmpty() || !this.moveToNotFull(world, target.get(), scheduler) || !transformNotFull(world, scheduler, imageStore)) {
//            scheduler.scheduleEvent(this, this.createActivityAction(world, imageStore), this.actionPeriod);
//        }
//    }
//    public void executeSaplingActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
//        this.health++;
//        if (!transformPlant(world, scheduler, imageStore)) {
//            scheduler.scheduleEvent(this, this.createActivityAction(world, imageStore), this.actionPeriod);
//        }
//    }
//    public boolean transformPlant(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        if (this.kind == EntityKind.TREE) {
//            return transformTree(world, scheduler, imageStore);
//        } else if (this.kind == EntityKind.SAPLING) {
//            return transformSapling(world, scheduler, imageStore);
//        } else {
//            throw new UnsupportedOperationException(String.format("transformPlant not supported for %s", this));
//        }
//    }

//    public boolean transformTree(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        if (this.health <= 0) {
//            Entity stump = createStump(WorldModel.STUMP_KEY + "_" + this.id, this.getPosition(), imageStore.getImageList(WorldModel.STUMP_KEY));
//
//            world.removeEntity(scheduler, this);
//
//            world.addEntity(stump);
//
//            return true;
//        }
//
//        return false;
//    }

//    public boolean transformSapling(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        if (this.health <= 0) {
//            Stump stump = new Stump(this.id, this.getPosition(), imageStore.getImageList(WorldModel.STUMP_KEY));
//
//            world.removeEntity(scheduler, this);
//
//            world.addEntity(stump);
//
//            return true;
//        } else if (this.health >= this.healthLimit) {
//            Tree tree = new Tree(this.id, this.getPosition(),imageStore.getImageList(WorldModel.TREE_KEY));
//
//            world.removeEntity(scheduler, this);
//
//            world.addEntity(tree);
//            tree.scheduleActions(scheduler, world, imageStore);
//
//            return true;
//        }
//
//        return false;
//    }

//    public void executeTreeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
//
//        if (!transformPlant(world, scheduler, imageStore)) {
//
//            scheduler.scheduleEvent(this, this.createActivityAction(world, imageStore), this.actionPeriod);
//        }
//    }

//    public void executeFairyActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
//        Optional<Entity> fairyTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(EntityKind.STUMP)));
//
//        if (fairyTarget.isPresent()) {
//            Point tgtPos = fairyTarget.get().getPosition();
//
//            if (this.moveToFairy(world, fairyTarget.get(), scheduler)) {
//
//                Entity sapling = createSapling(WorldModel.SAPLING_KEY + "_" + fairyTarget.get().id, tgtPos, imageStore.getImageList(WorldModel.SAPLING_KEY), 0);
//
//                world.addEntity(sapling);
//                sapling.scheduleActions(scheduler, world, imageStore);
//            }
//        }
//
//        scheduler.scheduleEvent(this, this.createActivityAction(world, imageStore), this.actionPeriod);
//    }


//    public Point nextPositionFairy(WorldModel world, Point destPos) {
//        int horiz = Integer.signum(destPos.x - this.getPosition().x);
//        Point newPos = new Point(this.getPosition().x + horiz, this.getPosition().y);
//
//        if (horiz == 0 || world.isOccupied(newPos)) {
//            int vert = Integer.signum(destPos.y - this.getPosition().y);
//            newPos = new Point(this.getPosition().x, this.getPosition().y + vert);
//
//            if (vert == 0 || world.isOccupied(newPos)) {
//                newPos = this.getPosition();
//            }
//        }
//
//        return newPos;
//    }


}
