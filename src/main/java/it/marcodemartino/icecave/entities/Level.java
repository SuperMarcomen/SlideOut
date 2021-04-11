package it.marcodemartino.icecave.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.marcodemartino.icecave.entities.blocks.Block;

import java.util.List;

public class Level {

    @Expose @SerializedName("grid_size") private final int gridSize;
    @Expose private final List<Block> grid;

    public Level(int gridSize, List<Block> grid) {
        this.gridSize = gridSize;
        this.grid = grid;
    }

    public int getGridSize() {
        return gridSize;
    }

    public List<Block> getGrid() {
        return grid;
    }
}
