package com.cis18.plaguerats;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

/**
 * A custom storage class, call the map and it's layers from here after they are made.
 */
public class MapResult {
    public final TiledMap GameMap;
    public final TiledMapTileLayer BaseLayer;
    public final TiledMapTileLayer CharObjLayer;

    public MapResult(TiledMap GameMap, TiledMapTileLayer BaseLayer, TiledMapTileLayer CharObjLayer) {
        this.GameMap = GameMap;
        this.BaseLayer = BaseLayer;
        this.CharObjLayer = CharObjLayer;
    }
}
