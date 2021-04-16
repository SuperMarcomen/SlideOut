package it.marcodemartino.icecave.scenes;

import it.marcodemartino.icecave.entities.Level;
import it.marcodemartino.icecave.entities.Player;
import it.marcodemartino.icecave.entities.blocks.Block;
import it.marcodemartino.icecave.handlers.InputHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GameLevel {

    private final int width, height;
    private final Level level;
    private final LevelSelector levelSelector;
    private Player player;

    public GameLevel(int width, int height, Level level, LevelSelector levelSelector) {
        this.width = width;
        this.height = height;
        this.level = level;
        this.levelSelector = levelSelector;
        level.createWall();
    }

    public Scene draw() {
        Pane pane = new Pane();

        StackPane root = new StackPane(pane);
        pane.setId("root");
        pane.setPrefSize(width, height);

        drawGrid(pane);
        if (player == null)
            player = new Player(level.getPlayerPosition().getX(), level.getPlayerPosition().getY(), width / level.getGridSize(), level.getGrid());
        pane.getChildren().add(player);

        if (player.hasWon()) {
            Label text = new Label("You have won");
            text.setId("won_text");

            Button button = new Button("Back to menu");
            button.setId("won_button");
            button.setOnMouseClicked(mouseEvent -> levelSelector.createScene());

            VBox vBox = new VBox(text, button);
            vBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7)");
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(50);
            root.getChildren().add(vBox);
        }

        Scene scene = new Scene(root, width, height);
        InputHandler inputHandler = new InputHandler(player, levelSelector);
        scene.setOnKeyPressed(inputHandler);
        scene.getStylesheets().add("stylesheet.css");

        return scene;
    }

    private void drawGrid(Pane pane) {
        for (Block block : level.getGrid()) {
            block.init(width / level.getGridSize());
            pane.getChildren().add(block);
        }
    }
}
