public class Animation extends Action{
    private int repeatCount;

    public Animation (Entity entity, int repeatCount) {
        super(entity);
        this.repeatCount = repeatCount;
    }

    @Override
    public void executeAction(EventScheduler scheduler) {
        this.getEntity().nextImage();

        if (this.repeatCount != 1) {
            Animation animation = new Animation(this.getEntity(), Math.max(this.repeatCount - 1, 0));
            if (this.getEntity() instanceof ScheduleEntity scheduleentity) {
                scheduler.scheduleEvent(this.getEntity(), animation, scheduleentity.getAnimationPeriod());
            }
        }
    }
}
