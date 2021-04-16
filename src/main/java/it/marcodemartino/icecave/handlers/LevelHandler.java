package it.marcodemartino.icecave.handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.marcodemartino.icecave.entities.Level;
import it.marcodemartino.icecave.entities.blocks.Block;
import it.marcodemartino.icecave.entities.blocks.BlockAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LevelHandler {
    private final List<Level> levels;

    public LevelHandler() throws IOException {
        levels = loadLevels();
    }

    private List<Level> loadLevels() throws IOException {
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

        System.out.println("json letto");
        return levels;
    }

    public List<Level> getLevels() {
        return levels;
    }
}
