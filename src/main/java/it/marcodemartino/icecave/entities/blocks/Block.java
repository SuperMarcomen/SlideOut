package it.marcodemartino.icecave.entities.blocks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import javafx.scene.shape.Rectangle;

public abstract class Block extends Rectangle {

    @Expose @SerializedName("x") private final int xGrid;
    @Expose @SerializedName("y") private final int yGrid;

    public Block(int xGrid, int yGrid, int size) {
        this.xGrid = xGrid;
        this.yGrid = yGrid;
        setX(xGrid*size);
        setY(yGrid*size);
        setHeight(size);
        setWidth(size);
    }
    public void init(int size) {
        setX(xGrid*size);
        setY(yGrid*size);
        setHeight(size);
        setWidth(size);
    }

    public int getXGrid() {
        return xGrid;
    }

    public int getYGrid() {
        return yGrid;
    }
}
