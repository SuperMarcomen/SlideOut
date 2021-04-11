package it.marcodemartino.icecave.scenes;

import it.marcodemartino.icecave.entities.Level;
import it.marcodemartino.icecave.entities.Player;
import it.marcodemartino.icecave.entities.blocks.Block;
import it.marcodemartino.icecave.handlers.InputHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class IceCave extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private final int width, height;
    private final Level level;

    public IceCave(int width, int height, Level level) {
        this.width = width;
        this.height = height;
        this.level = level;
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        root.setId("root");

        drawGrid(root);
        Player player = new Player(5, 10, level.getGridSize(), level.getGrid());
        root.getChildren().add(player);

        Scene scene = new Scene(root, width, height);
        InputHandler inputHandler = new InputHandler(player);
        scene.setOnKeyPressed(inputHandler);
        scene.getStylesheets().add("stylesheet.css");

        primaryStage.setScene(scene);
    }

    private void drawGrid(GridPane pane) {
        for (Block block : level.getGrid()) {
            block.init(level.getGridSize());
            pane.add(block, block.getXGrid(), block.getYGrid());
        }
    }
}
