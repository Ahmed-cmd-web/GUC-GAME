package model.characters;

public class Feral extends Zombie {
    private State state;

    public Feral(int speed, State state) {
        super(speed);
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
