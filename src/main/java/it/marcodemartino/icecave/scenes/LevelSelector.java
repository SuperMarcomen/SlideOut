package it.marcodemartino.icecave.scenes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.marcodemartino.icecave.entities.Level;
import it.marcodemartino.icecave.entities.blocks.Block;
import it.marcodemartino.icecave.entities.blocks.BlockAdapter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LevelSelector extends Application {

    private final int WIDTH = 900, HEIGHT = 900;
    private final List<Level> levels;

    public static void main(String[] args) {
        launch(args);
    }

    public LevelSelector() throws IOException {
        levels = getLevels();
        /*List<Block> blocks = new ArrayList<>();
        blocks.add(new Obstacle(5,5,30));
        blocks.add(new Obstacle(7,5,30));
        blocks.add(new Obstacle(8,5,30));
        blocks.add(new Obstacle(9,5,30));
        Level level = new Level( 30, blocks);
        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(Block.class, new BlockAdapter())
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting().create();
        System.out.println(gson.toJson(level));*/
    }

    @Override
    public void start(Stage stage) {
        VBox vBox = new VBox();
        VBox.setMargin(vBox, new Insets(100));
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setId("root");

        Label title = new Label("SLIDE OUT");
        title.setId("title");
        Label subtitle = new Label("An escape game");
        subtitle.setId("subtitle");

        Region spacer = new Region();
        spacer.setMinHeight(50);

        GridPane grid = new GridPane();
        grid.setId("grid");
        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setId("scrollpane");
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);



        grid.setHgap(100);
        grid.setVgap(100);
        grid.setPadding(new Insets(0, 100, 100, 100));
        for (int i = 0; i < levels.size(); i++) {
            StackPane stackPane = new StackPane();
            Label text = new Label(i+1 + "");
            text.setId("level_label");

            Rectangle rectangle = new Rectangle(WIDTH/5.52, HEIGHT/5.52);
            rectangle.setId("level_selector");

            int finalI = i;
            rectangle.setOnMouseClicked(mouseEvent -> {
                IceCave iceCave = new IceCave(WIDTH, HEIGHT, levels.get(finalI));
                iceCave.start(stage);
            });

            stackPane.getChildren().addAll(rectangle, text);
            grid.add(stackPane, i%3, i/3);
        }


        vBox.getChildren().addAll(title, subtitle, spacer, scrollPane);
        Scene scene = new Scene(vBox, WIDTH, HEIGHT);
        scene.getStylesheets().add("stylesheet.css");

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Slide out");
        stage.show();
    }

    private List<Level> getLevels() throws IOException {
        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(Block.class, new BlockAdapter())
                .excludeFieldsWithoutExposeAnnotation().create();

        final BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/levels")));
        final List<Level> levels = new ArrayList<>();

        String line = "";
        while ((line = reader.readLine()) != null) {
            Level level = gson.fromJson(new InputStreamReader(getClass().getResourceAsStream("/levels/" + line)), Level.class);
            levels.add(level);
        }

        return levels;
    }

}
