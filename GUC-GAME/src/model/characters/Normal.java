package model.characters;

public class Normal extends Zombie {
    private boolean isRunner;

    public Normal(int speed) {
        super(speed);
        this.isRunner = false;
    }

    public boolean isRunner() {
        return isRunner;
    }

    public void setRunner(boolean isRunner) {
        this.isRunner = isRunner;
    }




}
