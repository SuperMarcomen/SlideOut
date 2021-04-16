package it.marcodemartino.icecave.entities.blocks;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Goal extends Block {
    public Goal() {
        Image img = new Image(getClass().getResource("/images/fish.png").toString());
        setFill(new ImagePattern(img));
    }
}
