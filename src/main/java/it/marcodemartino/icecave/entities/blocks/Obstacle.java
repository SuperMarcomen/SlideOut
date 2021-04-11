package it.marcodemartino.icecave.entities.blocks;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Obstacle extends Block {
    public Obstacle(int xGrid, int yGrid, int size) {
        super(xGrid, yGrid, size);
        Image img = new Image(getClass().getResource("/images/stone.png").toString());
        setFill(new ImagePattern(img));
    }
}
