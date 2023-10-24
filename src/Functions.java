import java.util.*;

import processing.core.PImage;
import processing.core.PApplet;

/**
 * This class contains many functions written in a procedural style.
 * You will reduce the size of this class over the next several weeks
 * by refactoring this codebase to follow an OOP style.
 */
public final class Functions {
//    public static final Random rand = new Random();


//    public static final List<String> PATH_KEYS = new ArrayList<>(Arrays.asList("bridge", "dirt", "dirt_horiz", "dirt_vert_left", "dirt_vert_right", "dirt_bot_left_corner", "dirt_bot_right_up", "dirt_vert_left_bot"));
//
//    public static final double SAPLING_ACTION_ANIMATION_PERIOD = 1.000; // have to be in sync since grows and gains health at same time
//    public static final int SAPLING_HEALTH_LIMIT = 5;
//
//    public static final int PROPERTY_ID = 1;
//    public static final int PROPERTY_COL = 2;
//    public static final int PROPERTY_ROW = 3;
//    public static final int ENTITY_NUM_PROPERTIES = 4;

//    public static final String STUMP_KEY = "stump";
//    public static final int STUMP_NUM_PROPERTIES = 0;

//    public static final String SAPLING_KEY = "sapling";
//    public static final int SAPLING_HEALTH = 0;
//    public static final int SAPLING_NUM_PROPERTIES = 1;

//    public static final String OBSTACLE_KEY = "obstacle";
//    public static final int OBSTACLE_ANIMATION_PERIOD = 0;
//    public static final int OBSTACLE_NUM_PROPERTIES = 1;

//    public static final String DUDE_KEY = "dude";
//    public static final int DUDE_ACTION_PERIOD = 0;
//    public static final int DUDE_ANIMATION_PERIOD = 1;
//    public static final int DUDE_LIMIT = 2;
//    public static final int DUDE_NUM_PROPERTIES = 3;

//    public static final String HOUSE_KEY = "house";
//    public static final int HOUSE_NUM_PROPERTIES = 0;

//    public static final String FAIRY_KEY = "fairy";
//    public static final int FAIRY_ANIMATION_PERIOD = 0;
//    public static final int FAIRY_ACTION_PERIOD = 1;
//    public static final int FAIRY_NUM_PROPERTIES = 2;

//    public static final String TREE_KEY = "tree";
//    public static final int TREE_ANIMATION_PERIOD = 0;
//    public static final int TREE_ACTION_PERIOD = 1;
//    public static final int TREE_HEALTH = 2;
//    public static final int TREE_NUM_PROPERTIES = 3;

//    public static final double TREE_ANIMATION_MAX = 0.600;
//    public static final double TREE_ANIMATION_MIN = 0.050;
//    public static final double TREE_ACTION_MAX = 1.400;
//    public static final double TREE_ACTION_MIN = 1.000;
//    public static final int TREE_HEALTH_MAX = 3;
//    public static final int TREE_HEALTH_MIN = 1;

//
//    public static void executeSaplingActivity(Entity entity, WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
//        entity.health++;
//        if (!transformPlant(entity, world, scheduler, imageStore)) {
//            scheduler.scheduleEvent(entity, createActivityAction(entity, world, imageStore), entity.actionPeriod);
//        }
//    }
//
//    public static void executeTreeActivity(Entity entity, WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
//
//        if (!transformPlant(entity, world, scheduler, imageStore)) {
//
//            scheduler.scheduleEvent(entity, createActivityAction(entity, world, imageStore), entity.actionPeriod);
//        }
//    }
//
//    public static void executeFairyActivity(Entity entity, WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
//        Optional<Entity> fairyTarget = findNearest(world, entity.getPosition(), new ArrayList<>(List.of(EntityKind.STUMP)));
//
//        if (fairyTarget.isPresent()) {
//            Point tgtPos = fairyTarget.get().getPosition();
//
//            if (entity.moveToFairy(world, fairyTarget.get(), scheduler)) {
//
//                Entity sapling = createSapling(SAPLING_KEY + "_" + fairyTarget.get().id, tgtPos, imageStore.getImageList(SAPLING_KEY), 0);
//
//                world.addEntity(sapling);
//                scheduleActions(sapling, scheduler, world, imageStore);
//            }
//        }
//
//        scheduler.scheduleEvent(entity, createActivityAction(entity, world, imageStore), entity.actionPeriod);
//    }
//
//    public static void executeDudeNotFullActivity(Entity entity, WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
//        Optional<Entity> target = findNearest(world, entity.getPosition(), new ArrayList<>(Arrays.asList(EntityKind.TREE, EntityKind.SAPLING)));
//
//        if (target.isEmpty() || !entity.moveToNotFull(world, target.get(), scheduler) || !transformNotFull(entity, world, scheduler, imageStore)) {
//            scheduler.scheduleEvent(entity, createActivityAction(entity, world, imageStore), entity.actionPeriod);
//        }
//    }

//    public static void executeDudeFullActivity(Entity entity, WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
//        Optional<Entity> fullTarget = findNearest(world, entity.getPosition(), new ArrayList<>(List.of(EntityKind.HOUSE)));
//
//        if (fullTarget.isPresent() && entity.moveToFull(world, fullTarget.get(), scheduler)) {
//            transformFull(entity, world, scheduler, imageStore);
//        } else {
//            scheduler.scheduleEvent(entity, createActivityAction(entity, world, imageStore), entity.actionPeriod);
//        }
//    }


//    public static void scheduleActions(Entity entity, EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
//        switch (entity.kind) {
//            case DUDE_FULL:
//                scheduler.scheduleEvent(entity, createActivityAction(entity, world, imageStore), entity.actionPeriod);
//                scheduler.scheduleEvent(entity, entity.createAnimationAction(0), entity.getAnimationPeriod());
//                break;
//
//            case DUDE_NOT_FULL:
//                scheduler.scheduleEvent(entity, createActivityAction(entity, world, imageStore), entity.actionPeriod);
//                scheduler.scheduleEvent(entity, entity.createAnimationAction(0), entity.getAnimationPeriod());
//                break;
//
//            case OBSTACLE:
//                scheduler.scheduleEvent(entity, entity.createAnimationAction(0), entity.getAnimationPeriod());
//                break;
//
//            case FAIRY:
//                scheduler.scheduleEvent(entity, createActivityAction(entity, world, imageStore), entity.actionPeriod);
//                scheduler.scheduleEvent(entity, entity.createAnimationAction(0), entity.getAnimationPeriod());
//                break;
//
//            case SAPLING:
//                scheduler.scheduleEvent(entity, createActivityAction(entity, world, imageStore), entity.actionPeriod);
//                scheduler.scheduleEvent(entity, entity.createAnimationAction(0), entity.getAnimationPeriod());
//                break;
//
//            case TREE:
//                scheduler.scheduleEvent(entity, createActivityAction(entity, world, imageStore), entity.actionPeriod);
//                scheduler.scheduleEvent(entity, entity.createAnimationAction(0), entity.getAnimationPeriod());
//                break;
//
//            default:
//        }
//    }

//    public static boolean transformNotFull(Entity entity, WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        if (entity.resourceCount >= entity.resourceLimit) {
//            Entity dude = createDudeFull(entity.id, entity.getPosition(), entity.actionPeriod, entity.animationPeriod, entity.resourceLimit, entity.images);
//
//            world.removeEntity(scheduler, entity);
//            scheduler.unscheduleAllEvents(entity);
//
//            world.addEntity(dude);
//            scheduleActions(dude, scheduler, world, imageStore);
//
//            return true;
//        }
//
//        return false;
//    }


//    public static boolean moveToNotFull(Entity dude, WorldModel world, Entity target, EventScheduler scheduler) {
//        if (adjacent(dude.position, target.position)) {
//            dude.resourceCount += 1;
//            target.health--;
//            return true;
//        } else {
//            Point nextPos = nextPositionDude(dude, world, target.position);
//
//            if (!dude.position.equals(nextPos)) {
//                moveEntity(world, scheduler, dude, nextPos);
//            }
//            return false;
//        }
//    }

//    public static boolean moveToFull(Entity dude, WorldModel world, Entity target, EventScheduler scheduler) {
//        if (adjacent(dude.getPosition(), target.getPosition())) {
//            return true;
//        } else {
//            Point nextPos = dude.nextPositionDude(world, target.getPosition());
//
//            if (!dude.getPosition().equals(nextPos)) {
//                world.moveEntity(scheduler, dude, nextPos);
//            }
//            return false;
//        }
//    }

//    public static Point nextPositionFairy(Entity entity, WorldModel world, Point destPos) {
//        int horiz = Integer.signum(destPos.x - entity.getPosition().x);
//        Point newPos = new Point(entity.getPosition().x + horiz, entity.getPosition().y);
//
//        if (horiz == 0 || world.isOccupied(newPos)) {
//            int vert = Integer.signum(destPos.y - entity.getPosition().y);
//            newPos = new Point(entity.getPosition().x, entity.getPosition().y + vert);
//
//            if (vert == 0 || world.isOccupied(newPos)) {
//                newPos = entity.getPosition();
//            }
//        }
//
//        return newPos;
//    }

//    public static Point nextPositionDude(Entity entity, WorldModel world, Point destPos) {
//        int horiz = Integer.signum(destPos.x - entity.position.x);
//        Point newPos = new Point(entity.position.x + horiz, entity.position.y);
//
//        if (horiz == 0 || world.isOccupied(newPos) && world.getOccupancyCell(newPos).kind != EntityKind.STUMP) {
//            int vert = Integer.signum(destPos.y - entity.position.y);
//            newPos = new Point(entity.position.x, entity.position.y + vert);
//
//            if (vert == 0 || world.isOccupied(newPos) && world.getOccupancyCell(newPos).kind != EntityKind.STUMP) {
//                newPos = entity.position;
//            }
//        }
//
//        return newPos;
//    }

//
//    public static int getIntFromRange(int max, int min) {
//        Random rand = new Random();
//        return min + rand.nextInt(max-min);
//    }
//
//    public static double getNumFromRange(double max, double min) {
//        Random rand = new Random();
//        return min + rand.nextDouble() * (max - min);
//    }

//
//
//
//    public static void parseSapling(WorldModel world, String[] properties, Point pt, String id, ImageStore imageStore) {
//        if (properties.length == SAPLING_NUM_PROPERTIES) {
//            int health = Integer.parseInt(properties[SAPLING_HEALTH]);
//            Entity entity = createSapling(id, pt, imageStore.getImageList(SAPLING_KEY), health);
//            world.tryAddEntity(entity);
//        }else{
//            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", SAPLING_KEY, SAPLING_NUM_PROPERTIES));
//        }
//    }
//
//    public static void parseDude(WorldModel world, String[] properties, Point pt, String id, ImageStore imageStore) {
//        if (properties.length == DUDE_NUM_PROPERTIES) {
//            Entity entity = createDudeNotFull(id, pt, Double.parseDouble(properties[DUDE_ACTION_PERIOD]), Double.parseDouble(properties[DUDE_ANIMATION_PERIOD]), Integer.parseInt(properties[DUDE_LIMIT]), imageStore.getImageList(DUDE_KEY));
//            world.tryAddEntity(entity);
//        }else{
//            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", DUDE_KEY, DUDE_NUM_PROPERTIES));
//        }
//    }
//
//    public static void parseFairy(WorldModel world, String[] properties, Point pt, String id, ImageStore imageStore) {
//        if (properties.length == FAIRY_NUM_PROPERTIES) {
//            Entity entity = createFairy(id, pt, Double.parseDouble(properties[FAIRY_ACTION_PERIOD]), Double.parseDouble(properties[FAIRY_ANIMATION_PERIOD]), imageStore.getImageList(FAIRY_KEY));
//            world.tryAddEntity(entity);
//        }else{
//            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", FAIRY_KEY, FAIRY_NUM_PROPERTIES));
//        }
//    }
//
//    public static void parseTree(WorldModel world, String[] properties, Point pt, String id, ImageStore imageStore) {
//        if (properties.length == TREE_NUM_PROPERTIES) {
//            Entity entity = createTree(id, pt, Double.parseDouble(properties[TREE_ACTION_PERIOD]), Double.parseDouble(properties[TREE_ANIMATION_PERIOD]), Integer.parseInt(properties[TREE_HEALTH]), imageStore.getImageList(TREE_KEY));
//            world.tryAddEntity(entity);
//        }else{
//            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", TREE_KEY, TREE_NUM_PROPERTIES));
//        }
//    }
//
//    public static void parseObstacle(WorldModel world, String[] properties, Point pt, String id, ImageStore imageStore) {
//        if (properties.length == OBSTACLE_NUM_PROPERTIES) {
//            Entity entity = createObstacle(id, pt, Double.parseDouble(properties[OBSTACLE_ANIMATION_PERIOD]), imageStore.getImageList(OBSTACLE_KEY));
//            world.tryAddEntity(entity);
//        }else{
//            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", OBSTACLE_KEY, OBSTACLE_NUM_PROPERTIES));
//        }
//    }
//
//    public static void parseHouse(WorldModel world, String[] properties, Point pt, String id, ImageStore imageStore) {
//        if (properties.length == HOUSE_NUM_PROPERTIES) {
//            Entity entity = createHouse(id, pt, imageStore.getImageList(HOUSE_KEY));
//            world.tryAddEntity(entity);
//        }else{
//            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", HOUSE_KEY, HOUSE_NUM_PROPERTIES));
//        }
//    }
//    public static void parseStump(WorldModel world, String[] properties, Point pt, String id, ImageStore imageStore) {
//        if (properties.length == STUMP_NUM_PROPERTIES) {
//            Entity entity = createStump(id, pt, imageStore.getImageList(STUMP_KEY));
//            world.tryAddEntity(entity);
//        }else{
//            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", STUMP_KEY, STUMP_NUM_PROPERTIES));
//        }
//    }

//    public static Optional<Entity> nearestEntity(List<Entity> entities, Point pos) {
//        if (entities.isEmpty()) {
//            return Optional.empty();
//        } else {
//            Entity nearest = entities.get(0);
//            int nearestDistance = distanceSquared(nearest.getPosition(), pos);
//
//            for (Entity other : entities) {
//                int otherDistance = distanceSquared(other.getPosition(), pos);
//
//                if (otherDistance < nearestDistance) {
//                    nearest = other;
//                    nearestDistance = otherDistance;
//                }
//            }
//
//            return Optional.of(nearest);
//        }
//    }

//    public static int distanceSquared(Point p1, Point p2) {
//        int deltaX = p1.x - p2.x;
//        int deltaY = p1.y - p2.y;
//
//        return deltaX * deltaX + deltaY * deltaY;
//    }


    /*
       Assumes that there is no entity currently occupying the
       intended destination cell.
    */




//    public static Action createActivityAction(Entity entity, WorldModel world, ImageStore imageStore) {
//        return new Action(ActionKind.ACTIVITY, entity, world, imageStore, 0);
//    }
//
//    public static Entity createHouse(String id, Point position, List<PImage> images) {
//        return new Entity(EntityKind.HOUSE, id, position, images, 0, 0, 0, 0, 0, 0);
//    }
//
//    public static Entity createObstacle(String id, Point position, double animationPeriod, List<PImage> images) {
//        return new Entity(EntityKind.OBSTACLE, id, position, images, 0, 0, 0, animationPeriod, 0, 0);
//    }
//
//    public static Entity createTree(String id, Point position, double actionPeriod, double animationPeriod, int health, List<PImage> images) {
//        return new Entity(EntityKind.TREE, id, position, images, 0, 0, actionPeriod, animationPeriod, health, 0);
//    }
//
//    public static Entity createStump(String id, Point position, List<PImage> images) {
//        return new Entity(EntityKind.STUMP, id, position, images, 0, 0, 0, 0, 0, 0);
//    }
//
//    // health starts at 0 and builds up until ready to convert to Tree
//    public static Entity createSapling(String id, Point position, List<PImage> images, int health) {
//        return new Entity(EntityKind.SAPLING, id, position, images, 0, 0, SAPLING_ACTION_ANIMATION_PERIOD, SAPLING_ACTION_ANIMATION_PERIOD, 0, SAPLING_HEALTH_LIMIT);
//    }
//
//    public static Entity createFairy(String id, Point position, double actionPeriod, double animationPeriod, List<PImage> images) {
//        return new Entity(EntityKind.FAIRY, id, position, images, 0, 0, actionPeriod, animationPeriod, 0, 0);
//    }

    // need resource count, though it always starts at 0


    // don't technically need resource count ... full






//    public static Background getBackgroundCell(WorldModel world, Point pos) {
//        return world.background[pos.y][pos.x];
//    }


//    public static Point viewportToWorld(Viewport viewport, int col, int row) {
//        return new Point(col + viewport.col, row + viewport.row);
//    }


    /*
      Called with color for which alpha should be set and alpha value.
      setAlpha(img, color(255, 255, 255), 0));
    */



//    public static boolean contains(Viewport viewport, Point p) {
//        return p.y >= viewport.row && p.y < viewport.row + viewport.numRows && p.x >= viewport.col && p.x < viewport.col + viewport.numCols;
//    }

}
