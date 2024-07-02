package main.jigsawsudoku.Content;

import main.jigsawsudoku.Enums.TileState;

public class Tile {
    private TileState state;
    private char value;

    public Tile(TileState state, char value) {
        this.state = state;
        this.value = value;
    }

    public char getValue() {
        return value;
    }
    public int getIntValue() {
        return value - '0';
    }

    public String getState() {
        return state.getStrState();
    }

    public void setState(TileState state) {
        this.state = state;
    }
}
