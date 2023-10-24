public class Activity extends Action {
    private WorldModel world;
    private ImageStore imageStore;


    public Activity(Entity entity, WorldModel world, ImageStore imageStore) {
        super(entity);
        this.world = world;
        this.imageStore = imageStore;
    }

    @Override
    public void executeAction(EventScheduler scheduler) {
        if (this.getEntity() instanceof ExecuteEntity executeEntity) {
            executeEntity.executeActivity(this.world, this.imageStore, scheduler);
        }
//        switch (getEntity().getKind()) {
//            case SAPLING:
//                this.getEntity().executeSaplingActivity(this.world, this.imageStore, scheduler);
//                break;
//            case TREE:
//                this.getEntity().executeTreeActivity(this.world, this.imageStore, scheduler);
//                break;
//            case FAIRY:
//                this.getEntity().executeFairyActivity(this.world, this.imageStore, scheduler);
//                break;
//            case DUDE_NOT_FULL:
//                this.getEntity().executeDudeNotFullActivity(this.world, this.imageStore, scheduler);
//                break;
//            case DUDE_FULL:
//                this.getEntity().executeDudeFullActivity(this.world, this.imageStore, scheduler);
//                break;
//            default:
//                throw new UnsupportedOperationException(String.format("executeActivityAction not supported for %s", this.getEntity().getKind()));
//        }
    }
}
