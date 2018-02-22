package fr.ul.cassebrique.model;

public class GameState {

    private State state ;

    public enum State {
        Running,
        BallLoss,
        GameOver,
        Won,
        Pause,
        Quit
    } ;

    public GameState(){ state = State.Running ;}

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
