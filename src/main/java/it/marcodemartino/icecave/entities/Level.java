package it.marcodemartino.icecave.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.marcodemartino.icecave.entities.blocks.Block;
import it.marcodemartino.icecave.entities.blocks.Goal;
import it.marcodemartino.icecave.entities.blocks.Obstacle;

import java.util.List;

public class Level {

    @Expose
    @SerializedName("grid_size")
    private final int gridSize;
    @Expose
    private final List<Block> grid;
    @Expose
    private final PlayerPosition playerPosition;

    public Level(int gridSize, List<Block> grid, PlayerPosition playerPosition) {
        this.gridSize = gridSize;
        this.grid = grid;
        this.playerPosition = playerPosition;
        createWall();
    }

    public void createWall() {
        Block goal = grid.stream()
                .filter(block -> block instanceof Goal)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("There is no goal in the level"));

        for (int i = 0; i <= gridSize - 1; i++) {
            /* horizontal */
            if (goal.getYGrid() != 0 || goal.getXGrid() != i) {
                Obstacle obstacle = new Obstacle();
                obstacle.setYGrid(0);
                obstacle.setXGrid(i);
                grid.add(obstacle);
            }
            if (goal.getYGrid() != gridSize - 1 || goal.getXGrid() != i) {
                Obstacle obstacle = new Obstacle();
                obstacle.setYGrid(gridSize - 1);
                obstacle.setXGrid(i);
                grid.add(obstacle);
            }
            /* vertical */
            if (goal.getYGrid() != i || goal.getXGrid() != 0) {
                Obstacle obstacle = new Obstacle();
                obstacle.setYGrid(i);
                obstacle.setXGrid(0);
                grid.add(obstacle);
            }
            if (goal.getYGrid() != i || goal.getXGrid() != gridSize - 1) {
                Obstacle obstacle = new Obstacle();
                obstacle.setYGrid(i);
                obstacle.setXGrid(gridSize - 1);
                grid.add(obstacle);
            }
        }
    }

    public int getGridSize() {
        return gridSize;
    }

    public List<Block> getGrid() {
        return grid;
    }

    public PlayerPosition getPlayerPosition() {
        return playerPosition;
    }
}
