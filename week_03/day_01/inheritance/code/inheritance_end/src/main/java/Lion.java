public class Lion extends Animal{

    private boolean canWaitToBeKing;

    public Lion(String name, boolean canWaitToBeKing){
        super(name);
        this.canWaitToBeKing = canWaitToBeKing;
    }

    @Override
    public String makeNoise(){
        return "ROAR!";
    }

}
