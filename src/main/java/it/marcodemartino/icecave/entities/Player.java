package it.marcodemartino.icecave.entities;

import com.google.gson.annotations.Expose;
import it.marcodemartino.icecave.entities.blocks.Block;
import it.marcodemartino.icecave.entities.blocks.Goal;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Player extends Rectangle {

    @Expose
    private int xGrid;
    @Expose
    private int yGrid;
    private final int objectSize;
    private final List<Block> grid;
    private boolean won = false;


    boolean isMoving = false;

    public Player(int xGrid, int yGrid, int objectSize, List<Block> grid) {
        super(objectSize, objectSize);
        this.grid = grid;
        this.objectSize = objectSize;
        this.xGrid = xGrid;
        this.yGrid = yGrid;
        setX(xGrid * objectSize);
        setY(yGrid * objectSize);
        setFill(Color.BLACK);

        Image img = new Image(getClass().getResource("/images/player.png").toString());
        setFill(new ImagePattern(img));
        //setFill(Color.RED);
    }

    public void move(Direction direction) {
        if (isMoving || won) return;

        updateCoordinates(getClosestBlock(direction), direction);

        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(xProperty(), xGrid * objectSize),
                        new KeyValue(yProperty(), yGrid * objectSize)
                ));
        isMoving = true;
        timeline.play();
        timeline.setOnFinished(actionEvent -> isMoving = false);

    }

    private void updateCoordinates(Optional<Block> optionalBlock, Direction direction) {
        if (optionalBlock.isEmpty()) return;
        Block block = optionalBlock.get();

        if (block instanceof Goal) {
            xGrid = block.getXGrid();
            yGrid = block.getYGrid();
            won = true;
        } else {
            switch (direction) {
                case UP -> yGrid = block.getYGrid() + 1;
                case DOWN -> yGrid = block.getYGrid() - 1;
                case LEFT -> xGrid = block.getXGrid() + 1;
                case RIGHT -> xGrid = block.getXGrid() - 1;
            }
        }
    }

    private Optional<Block> getClosestBlock(Direction direction) {
        Optional<Block> optionalBlock = Optional.empty();
        switch (direction) {
            case UP -> {
                optionalBlock = grid.stream().filter(block -> block.getXGrid() == xGrid)
                        .filter(block -> block.getYGrid() < yGrid)
                        .min(Comparator.comparing(i -> Math.abs(i.getYGrid() - yGrid)));
            }
            case DOWN -> {
                optionalBlock = grid.stream().filter(block -> block.getXGrid() == xGrid)
                        .filter(block -> block.getYGrid() > yGrid)
                        .min(Comparator.comparing(i -> Math.abs(i.getYGrid() - yGrid)));
            }
            case LEFT -> {
                optionalBlock = grid.stream().filter(block -> block.getYGrid() == yGrid)
                        .filter(block -> block.getXGrid() < xGrid)
                        .min(Comparator.comparing(i -> Math.abs(i.getXGrid() - xGrid)));
            }
            case RIGHT -> {
                optionalBlock = grid.stream().filter(block -> block.getYGrid() == yGrid)
                        .filter(block -> block.getXGrid() > xGrid)
                        .min(Comparator.comparing(i -> Math.abs(i.getXGrid() - xGrid)));
            }
        }
        return optionalBlock;
    }

    public boolean hasWon() {
        return won;
    }
}
