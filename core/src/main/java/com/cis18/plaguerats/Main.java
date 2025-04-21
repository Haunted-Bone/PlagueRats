package com.cis18.plaguerats;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    //randomizer
    private int randomizer;

    // Sprite Batch and various textures
    private SpriteBatch batch;
    private Texture player_texture;
    private Texture TileSetTexture;


    // World Dimensions measured in Tiles
    final static int World_Height = 100;
    final static int World_Width = 100;

    //Tile Dimensions measured in pixels
    final static int Tile_Height = 16;
    final static int Tile_Width = 16;

    //Game Camera
    private OrthographicCamera camera;

    //The Game Tile Map
    private TiledMap GameMap;

    //The Base Map Layer -- for the map tiles themselves and other things that are not going to change or move throughout the game
    private TiledMapTileLayer BaseLayer;
    //The Characters and Objects Layer -- For anything expected to change or move in a regular game
    private TiledMapTileLayer CharObjLayer;

    //The Map Tile Set we are using
    private TiledMapTileSet TestTileSet;

    //The map renderer and the unit scale it will work with (Should be set to 1/x where x = the width and height of a tile in pixels)
    private OrthogonalTiledMapRenderer MapRenderer;
    private float UnitScale = 1/16f;

    //The Tile Set for Characters
    private TiledMapTileSet CharacterSet;

    //The Player Game Object and the Player Cell
    private TiledMapTileMapObject PlayerObject;
    private TiledMapTileLayer.Cell PlayerCell;


    @Override
    public void create() {
        //intialize the randomizer
        randomizer = MathUtils.random(1,100);

        // Initialize SpriteBatch and Textures
        batch = new SpriteBatch();
        TileSetTexture = new Texture("Buch_Tiles.Png");
        player_texture = new Texture("Player_test.png");

        // Initialize camera with proper viewport
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() * UnitScale, Gdx.graphics.getHeight() * UnitScale);

        /*Initializes The Tile Set and fills it with tiles
            Divides the Tile Set Texture file up into individual files by following the dimensions given in pixels
            and puts it into an array, here named TileTexture*/
        TextureRegion[][] TileTextures = TextureRegion.split(TileSetTexture, Tile_Width, Tile_Height);
            //Creates The Tile Set
        TestTileSet = new TiledMapTileSet();
            //Fills the Tile Set with the tiles from the Texture Region Array above.
        int tileId = 1;
        for (int y = 0; y < TileTextures.length; y++) {
            for (int x = 0; x < TileTextures[y].length; x++) {
                StaticTiledMapTile tile = new StaticTiledMapTile(TileTextures[y][x]);
                TestTileSet.putTile(tileId++, tile);
            }
        }

        /*        The Split TextureRegion.split function cuts the Texture file given to it up into a square grid.
                  The individual textures cut out of the original file are stored in the array with the first item stored
                  being the bottom left tile, i.e. 0,0. The Next item stored is the tile directly to the right, i.e. 1,0.
                  This continues across to the right, then resets to the left and goes up to the 0,1 tile. If you want to
                  call for specific tiles, keep this pattern in mind.

                  What I have above is just a test model and simply spits a bunch of tiles in without labeling very well.
                  Highly suggest making individual tiles with our final tile set.


                  Here is an example of how to create an individual static tile by hand from this type of array and add it to a tile set.

                    StaticTiledMapTile TestTile = new StaticTiledMapTile(TileTextures[0][0]);
                    TestTileSet.putTile(1, TestTile);

                    StaticTiledMapTile DifferentTestTile = new StaticTiledMapTile(TileTextures[0][1]);
                    TestTileSet.putTile(2, DifferentTestTile);

             */

        //cuts up the player_texture file into use able tile textures, stored in array named CharacterTextures
        TextureRegion[][] CharacterTextures = TextureRegion.split(player_texture, 32,32);

        //Creates the new tile set for storing character textures
        CharacterSet = new TiledMapTileSet();
        //Creates a new static tile texture out of the first image in the CharacterTextures array
        StaticTiledMapTile Player_Tile = new StaticTiledMapTile(CharacterTextures[0][0]);
        //Adds the new static tile texture, Player_Tile, to the CharacterSet Tile Set under tile id 1.
        CharacterSet.putTile(1, Player_Tile);


        // Initializes the Map, its layers and tile sets
        GameMap = new TiledMap();
        GameMap.getTileSets().addTileSet(TestTileSet);
        GameMap.getTileSets().addTileSet(CharacterSet);

        //Base layer is for map tiles
        BaseLayer = new TiledMapTileLayer(World_Width, World_Height, Tile_Width, Tile_Height);
        //Character and Object layer is for anything expected to move or change and is projected above the base layer
        CharObjLayer = new TiledMapTileLayer(World_Width, World_Height, Tile_Width, Tile_Height);

        //Adds the layers to the map
        GameMap.getLayers().add(BaseLayer);
        GameMap.getLayers().add(CharObjLayer);

        // For loop that fills base layer with map tiles
        for (int x = 0; x < World_Width; x++) {
            for (int y = 0; y < World_Height; y++) {
                //Creates a new cell for the map layer
                TiledMapTileLayer.Cell mapcell = new TiledMapTileLayer.Cell();
                //assigns the new cell a tile texture from the test tile set
                mapcell.setTile(TestTileSet.getTile(1 + (MathUtils.random(1,150))));
                //assigns that cell a location on the base layer grid.
                BaseLayer.setCell(x, y, mapcell);
            }
        }

        //for loop that creates the object layer cells, these cells are left empty for now
        for (int a = 0; a < World_Width; a++) {
            for (int b = 0; b < World_Height; b++){
                TiledMapTileLayer.Cell CharObjCell = new TiledMapTileLayer.Cell();
                CharObjLayer.setCell(a,b,CharObjCell);
            }
        }

        PlayerObject = new TiledMapTileMapObject(Player_Tile,false,false);
        //adds the player map object to the CharObjLayer
        CharObjLayer.getObjects().add(PlayerObject);
        //Creates a starting location for the player object at the center of the map on the CharObjLayer.
        PlayerCell = CharObjLayer.getCell(World_Width/2, World_Height/2);
        //Assigns the player object a location using PlayerCell
        PlayerCell.setTile(Player_Tile);

        // Initialize the map renderer
        MapRenderer = new OrthogonalTiledMapRenderer(GameMap, UnitScale);

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
        GameMap.dispose();
        TileSetTexture.dispose();
        player_texture.dispose();
        MapRenderer.dispose();
    }
}
