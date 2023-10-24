import processing.core.PImage;

import java.util.List;

public class Watermelon extends ScheduleEntity{

    public Watermelon(String id, Point position, List<PImage> images) {
        super(id, position, images, 0.3, 0.3);
    }
    @Override
    public void nextImage() {
        if (getImageIndex() < 2) {
            setImageIndex(getImageIndex() + 1);
        }
    }

}
