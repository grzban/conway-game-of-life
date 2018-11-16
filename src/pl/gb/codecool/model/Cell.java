package pl.gb.codecool.model;

public class Cell {
    private String id;
    private boolean state;

    Cell() {
        state = false;
    }

    public boolean isState() {
        return state;
    }

    void setState(boolean state) {
        this.state = state;
    }

    String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getId() + " " + isState();
    }
}
