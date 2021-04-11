package it.marcodemartino.icecave.entities;

import com.google.gson.annotations.Expose;
import it.marcodemartino.icecave.entities.blocks.Block;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.List;

public class Player extends Rectangle {

    @Expose private int xGrid;
    @Expose private int yGrid;
    private final int objectSize;
    private final List<Block> grid;

    public Player(int xGrid, int yGrid, int objectSize, List<Block> grid) {
        super(objectSize,objectSize);
        this.grid = grid;
        this.objectSize = objectSize;
        this.xGrid = xGrid;
        this.yGrid = yGrid;
        setX(xGrid*objectSize);
        setY(yGrid*objectSize);
        setFill(Color.BLACK);

        Image img = new Image(getClass().getResource("/images/mario.png").toString());
        setFill(new ImagePattern(img));
    }


    boolean isMoving = false;
    public void move(Direction direction) {
        if (isMoving) return;

        for (Block block : grid) {
            System.out.println(block.getY() < yGrid);

            switch (direction) {
                case UP:
                    if (block.getX() == xGrid && block.getY() < yGrid) yGrid = block.getYGrid()+1;
                    break;
                case DOWN:
                    if (block.getX() == xGrid && block.getY() > yGrid) yGrid = block.getYGrid()-1;
                    break;
                case LEFT:
                    if (block.getY() == yGrid && block.getX() < xGrid) xGrid = block.getXGrid()+1;
                    break;
                case RIGHT:
                    if (block.getY() == yGrid && block.getX() > xGrid) xGrid = block.getXGrid()-1;
                    break;
            }
        }

        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(xProperty(), xGrid*objectSize),
                        new KeyValue(yProperty(), yGrid*objectSize)
                ));
        isMoving = true;
        timeline.play();
        timeline.setOnFinished(actionEvent -> isMoving = false);

    }
}
