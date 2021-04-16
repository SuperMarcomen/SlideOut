package it.marcodemartino.icecave.entities;

import com.google.gson.annotations.Expose;

public class PlayerPosition {

    @Expose
    private final int x;
    @Expose
    private final int y;

    public PlayerPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
