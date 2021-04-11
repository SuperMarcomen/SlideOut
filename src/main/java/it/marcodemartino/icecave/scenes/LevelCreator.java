package it.marcodemartino.icecave.scenes;

import com.google.gson.Gson;
import it.marcodemartino.icecave.entities.Level;
import it.marcodemartino.icecave.entities.blocks.Block;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LevelCreator extends Application {

    private final int WIDTH = 900, HEIGHT = 900;
    private final List<Level> levels;

    public static void main(String[] args) {
        launch(args);
    }

    public LevelCreator() throws IOException {
        List<Block> grid = new ArrayList<>();
        levels = getLevels();

    }

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();

        for (int i = 0; i < levels.size(); i++) {
            Rectangle rectangle = new Rectangle(20*i+20, 20, 20, 20);
            rectangle.setFill(Color.BLACK);

            int finalI = i;
            rectangle.setOnMouseClicked(mouseEvent -> {
                IceCave iceCave = new IceCave(WIDTH, HEIGHT, levels.get(finalI));
                iceCave.start(stage);
            });
            root.getChildren().add(rectangle);
        }


        Scene scene = new Scene(root, WIDTH, HEIGHT);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Freeze");
        stage.show();
    }

    private List<Level> getLevels() throws IOException {
        final Gson gson = new Gson();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/levels")));
        final List<Level> levels = new ArrayList<>();

        while(reader.ready()) {
            String fileName = reader.readLine();
            Level level = gson.fromJson(new InputStreamReader(getClass().getResourceAsStream("/levels/" + fileName)), Level.class);
            levels.add(level);
        }

        return levels;
    }

}
