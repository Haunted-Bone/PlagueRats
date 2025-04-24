package com.cis18.plaguerats;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.MathUtils;

public class MapMaker {


    /**
     * Takes the given parameters and creates a tile map for the game.
     * @param MapSize the size of the map's width and height, measured in the amount of cells
     * @param CellSize the size of each cell's width and height measured in pixels
     * @param TileSetTexture the original texture file containing our tile set
     */
    public TiledMap CreateMap(int MapSize, int CellSize, Texture TileSetTexture){

        //creates the map instance
        TiledMap GameMap = new TiledMap();
        //creates a map renderer and the scale it will render at
        OrthogonalTiledMapRenderer GameMapRenderer = new OrthogonalTiledMapRenderer(GameMap);
        float UnitScale = ((float) 1 /CellSize);



        //Creates the base layer of the map
        TiledMapTileLayer BaseLayer = new TiledMapTileLayer(MapSize,MapSize,CellSize,CellSize);
        //Creates the layer for objects and characters
        TiledMapTileLayer CharObjLayer = new TiledMapTileLayer(MapSize,MapSize,CellSize,CellSize);

        //Creates the array of tile textures from the tile set texture file by cutting the file up into squares.
        TextureRegion[][] TileTextures = TextureRegion.split(TileSetTexture, CellSize, CellSize);
        //Creates the actual Tile Set object that will be used by the map.
        TiledMapTileSet MapTileSet = new TiledMapTileSet();
        //Fills the Tile Set object with the tiles from the array made above
        int tileId = 1;
        for (int y = 0; y < TileTextures.length; y++) {
            for (int x = 0; x < TileTextures[y].length; x++) {
                StaticTiledMapTile tile = new StaticTiledMapTile(TileTextures[y][x]);
                MapTileSet.putTile(tileId++, tile);
            }
        }

        //adds the tile set and layers to the map
        GameMap.getLayers().add(BaseLayer);
        GameMap.getLayers().add(CharObjLayer);
        GameMap.getTileSets().addTileSet(MapTileSet);


        // For loop that fills base layer with map tiles
        for (int x = 0; x < MapSize; x++) {
            for (int y = 0; y < MapSize; y++) {
                //Creates a new cell for the map layer
                TiledMapTileLayer.Cell mapcell = new TiledMapTileLayer.Cell();
                //assigns the new cell a tile texture from the test tile set
                mapcell.setTile(MapTileSet.getTile(1 + (MathUtils.random(1,150))));
                //assigns that cell a location on the base layer grid.
                BaseLayer.setCell(x, y, mapcell);
            }
        }


        return GameMap;


    }


}
