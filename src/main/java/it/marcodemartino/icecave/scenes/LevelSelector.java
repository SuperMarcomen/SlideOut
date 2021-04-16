package it.marcodemartino.icecave.scenes;

import it.marcodemartino.icecave.handlers.LevelHandler;
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

import java.io.IOException;

public class LevelSelector extends Application {

    private final int WIDTH = 900, HEIGHT = 900;
    private final LevelHandler levelHandler;
    private GameLevel gameLevel;
    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    public LevelSelector() throws IOException {
        levelHandler = new LevelHandler();
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        createScene();
        stage.setResizable(false);
        stage.setTitle("Slide out");
        stage.show();
    }

    public void drawWonScreen() {
        Scene scene = gameLevel.draw();
        stage.setScene(scene);
    }

    public void createScene() {
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
        for (int i = 0; i < levelHandler.getLevels().size(); i++) {
            StackPane stackPane = new StackPane();
            Label text = new Label(i + 1 + "");
            text.setId("level_label");

            Rectangle rectangle = new Rectangle(WIDTH / 5.52, HEIGHT / 5.52);
            rectangle.setId("level_selector");

            int finalI = i;
            rectangle.setOnMouseClicked(mouseEvent -> {
                gameLevel = new
                        GameLevel(WIDTH, HEIGHT, levelHandler.getLevels().get(finalI), this);
                Scene scene = gameLevel.draw();
                stage.setScene(scene);
            });

            stackPane.getChildren().addAll(rectangle, text);
            grid.add(stackPane, i%3, i/3);
        }


        vBox.getChildren().addAll(title, subtitle, spacer, scrollPane);
        Scene scene = new Scene(vBox, WIDTH, HEIGHT);
        scene.getStylesheets().add("stylesheet.css");

        stage.setScene(scene);
    }



}
