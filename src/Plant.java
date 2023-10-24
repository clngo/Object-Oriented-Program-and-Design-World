import processing.core.PImage;

import java.util.List;

public abstract class Plant extends ScheduleEntity implements TransformEntity, ExecuteEntity{
    private int health;
    public Plant(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, int health){
        super(id, position, images, actionPeriod, animationPeriod);
        this.health = health;
    }
    public int getHealth() {
        return this.health;
    }

    public void increaseHealth() {
        this.health++;
    }

    public void decreaseHealth() {
        this.health--;
    }
}
