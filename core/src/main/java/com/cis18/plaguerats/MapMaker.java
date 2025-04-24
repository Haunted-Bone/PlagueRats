package com.cis18.plaguerats;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.MathUtils;

/**
 * A factory class for making square shaped tile maps for the Plague-Rats Game Project.
 * Use a MapResults object to store the results of the CreateMap function
 */

public class MapMaker {
    private TiledMap gameMap;
    private TiledMapTileLayer baseLayer;
    private TiledMapTileLayer characterObjectLayer;

    /**
     * Takes the given parameters and creates a tile map for the game.
     * @param MapSize the size of the map's width and height, measured in the amount of cells
     * @param CellSize the size of each cell's width and height measured in pixels
     * @param TileSetTexture the original texture file containing our tile set that will be cut up and stored in an array
     * @return A custom storage class, MapResult, which includes the Map itself, as well as the 2 layers included in the map
     */

    //public MapMaker() {
        //this.gameMap = gameMap;
        //this.baseLayer = baseLayer;
        //this.characterObjectLayer = characterObjectLayer;
    //}

    public MapResult CreateMap(int MapSize, int CellSize, Texture TileSetTexture) {

        //creates the map instance
        TiledMap gameMap = new TiledMap();

        //Creates the base layer of the map
        TiledMapTileLayer baseLayer = new TiledMapTileLayer(MapSize, MapSize, CellSize, CellSize);
        //Creates the layer for objects and characters
        TiledMapTileLayer charObjLayer = new TiledMapTileLayer(MapSize, MapSize, CellSize, CellSize);

        this.gameMap = gameMap;
        this.baseLayer = baseLayer;
        this.characterObjectLayer = charObjLayer;



        //Creates the array of tile textures from the tile set texture file by cutting the file up into squares.
        TextureRegion[][] tileTextures = TextureRegion.split(TileSetTexture, CellSize, CellSize);
        //Creates the actual Tile Set object that will be used by the map.
        TiledMapTileSet mapTileSet = new TiledMapTileSet();
        //Fills the Tile Set object with the tiles from the array made above
        int tileId = 1;
        for (TextureRegion[] tileTexture : tileTextures) {
            for (TextureRegion textureRegion : tileTexture) {
                StaticTiledMapTile tile = new StaticTiledMapTile(textureRegion);
                mapTileSet.putTile(tileId++, tile);
            }
        }

        //adds the tile set and layers to the map
        // MapLayers mapLayers = gameMap.getLayers();
        // mapLayers.add(baseLayer);
        // mapLayers.add(charObjLayer);

        gameMap.getLayers().add(baseLayer);
        gameMap.getLayers().add(charObjLayer);
        gameMap.getTileSets().addTileSet(mapTileSet);

        //Sets the layer properties
        baseLayer.setVisible(true);
        charObjLayer.setVisible(true);


        // For loop that fills base layer with map tiles
        for (int x = 0; x < MapSize; x++) {
            for (int y = 0; y < MapSize; y++) {
                //Creates a new cell for the map layer
                TiledMapTileLayer.Cell mapCell = new TiledMapTileLayer.Cell();
                //assigns the new cell a tile texture from the test tile set
                mapCell.setTile(mapTileSet.getTile(1 + (MathUtils.random(1,150))));
                //assigns that cell a location on the base layer grid.
                baseLayer.setCell(x, y, mapCell);
            }
        }

        return new MapResult(gameMap,baseLayer,charObjLayer);

    }
}
