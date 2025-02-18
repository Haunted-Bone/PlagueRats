package io.github.CR.PlagueRats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class FirstScreen implements Screen {

    private final OrthographicCamera camera;
    private final SpriteBatch batch;
    private final Texture background;
    private final Stage stage;
    private Skin skin;
    private FileHandle fileHandle;
    private String jsonString;

    public FirstScreen(final RatGame game) {

        // Create camera and SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();

        // Load background texture
        background = new Texture("background.jpg");

        // Create stage and skin for UI elements
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage); // Set stage as the input processor
        fileHandle = Gdx.files.internal("ui/uiskin.json");
        jsonString = fileHandle.readString();
        skin = new Skin(fileHandle); // Load UI skin

        // Create "Play" button
        TextButton playButton = new TextButton("Play", skin);
        playButton.setPosition((float) Gdx.graphics.getWidth() / 2 - 50, (float) Gdx.graphics.getHeight() / 2 - 25);
        playButton.setSize(100, 50);

        // Create "Settings" button
        // TextButton settingsButton = new

            // Add listener to the "Play" button
            playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game)); // Switch to the game screen
            }
        });

        // Add button to the stage
        stage.addActor(playButton);

    }

    @Override
    public void show() {
        // Called when this screen becomes the current screen for the game
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update camera
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // Draw background
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Draw UI
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // Called when the game is paused
    }

    @Override
    public void resume() {
        // Called when the game is resumed
    }

    @Override
    public void hide() {
        // Called when this screen is no longer the current screen for the game
    }

    @Override
    public void dispose() {
        // Clean up resources
        batch.dispose();
        background.dispose();
        stage.dispose();
        skin.dispose();
    }
}
