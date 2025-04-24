package com.cis18.plaguerats;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class CharacterFactory {

    /**
     * Creates a character style map object and adds it to the provided map's objects array
     * @param CharacterTexture the original texture file that will be our character
     * @param GameMap the map we want the character added to
     * @param ObjectLayer the layer of the map we want the character rendered on
     * @return
     */

    public MapObject CreateCharacter(Texture CharacterTexture, TiledMap GameMap, TiledMapTileLayer ObjectLayer){

        //makes a texture region out of our character texture
        TextureRegion CharacterTextureRegion = new TextureRegion(CharacterTexture);

        //the map object we are going to return at the end of the function call
        MapObject Character = new MapObject();
        //adding properties to the new character
        Character.getProperties().put("Texture", CharacterTextureRegion);
        Character.getProperties().put("x", 0f);
        Character.getProperties().put("y", 0f);

        //add the object to the CharObjLayer
        ObjectLayer.getObjects().add(Character);

    return Character;
    }


}
