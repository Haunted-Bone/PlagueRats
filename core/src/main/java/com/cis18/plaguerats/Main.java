package com.cis18.plaguerats;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {


    // Sprite Batch and various textures
    private SpriteBatch batch;
    private Texture player_texture;
    private Texture TileSetTexture;

    //The map result object, this stores our map it's layers.
    public MapResult RatWorldMap;

    //Our Character Factory
    public CharacterFactory CharacterMaker;

    //our test character object
    private MapObject TestCharacter;

    //Game Camera and Viewport
    private OrthographicCamera camera;
    private Viewport viewport;

    //The map renderer and the unit scale it will work with (Should be set to 1/x where x = the width and height of a tile in pixels)
    private OrthogonalTiledMapRenderer MapRenderer;
    private float UnitScale = 1/16f;

    public static MapMaker GameMapMaker;

    @Override
    public void create() {

        // Initialize SpriteBatch and Textures
        batch = new SpriteBatch();
        TileSetTexture = new Texture("Buch_Tiles.Png");
        player_texture = new Texture("Player_test.png");

        //Initializes the mapmaker and creates the map, with 2 layers, and fill the base layer with our tile set
        GameMapMaker = new MapMaker();
        RatWorldMap = GameMapMaker.CreateMap(50, 16, TileSetTexture);

        //Initializes the character factory and adds our test character to the map
        CharacterMaker = new CharacterFactory();
        TestCharacter = CharacterMaker.CreateCharacter(player_texture,RatWorldMap.GameMap,RatWorldMap.CharObjLayer);

        // Initialize camera with proper viewport
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() * UnitScale, Gdx.graphics.getHeight() * UnitScale);

        // Initialize the map renderer
        MapRenderer = new OrthogonalTiledMapRenderer(RatWorldMap.GameMap, UnitScale);

    }

    @Override
    public void render() {
        // Clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Set the camera view to match the map renderer
        MapRenderer.setView(camera);

        // Renders the map
        MapRenderer.render();

    }

    @Override
    public void dispose() {
        batch.dispose();
        RatWorldMap.GameMap.dispose();
        TileSetTexture.dispose();
        player_texture.dispose();
        MapRenderer.dispose();
    }
}
