package it.marcodemartino.icecave.entities.blocks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import javafx.scene.shape.Rectangle;

public abstract class Block extends Rectangle {

    @Expose
    @SerializedName("x")
    private int xGrid;
    @Expose
    @SerializedName("y")
    private int yGrid;

    public void init(int size) {
        setX(xGrid * size);
        setY(yGrid * size);
        setHeight(size);
        setWidth(size);
    }

    public void setXGrid(int xGrid) {
        this.xGrid = xGrid;
    }

    public void setYGrid(int yGrid) {
        this.yGrid = yGrid;
    }

    public int getXGrid() {
        return xGrid;
    }

    public int getYGrid() {
        return yGrid;
    }
}
