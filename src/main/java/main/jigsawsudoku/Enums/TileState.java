package main.jigsawsudoku.Enums;

public enum TileState {
    HIDDEN,
    SHOWN;
    public String getStrState(){
        return String.valueOf(this);
    }
}
