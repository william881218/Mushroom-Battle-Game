package mushroom_battle.model;

import java.util.List;

import mushroom_battle.model.sprite.*;
import sdk.model.*;

public class GravityCollisionHandler extends CollisionHandler{

    final static int SCHEDULING_IDX = 0;

    public GravityCollisionHandler(){
        super(SCHEDULING_IDX);
    }

    public GravityCollisionHandler(int level){
        super(level);
    }

    @Override
    public boolean shouldHandle(Sprite target){
        return target instanceof ActiveSprite;
    }

    @Override
    public void handle(Sprite target, List<Sprite> otherSprites){
        ActiveSprite targetSprite = (ActiveSprite) target;
        for (Sprite otherSprite: otherSprites){
            if (otherSprite instanceof Ground)
                landingHandler(targetSprite, (Ground) otherSprite);
        }
    }

    public void landingHandler(ActiveSprite sprite, Ground ground){
        if (sprite.intersects(ground.x, ground.y-1, ground.width, ground.height) && 
            sprite.isFalling && 
            sprite.ySpeed > 0
        ){
            sprite.isFalling = false;
            sprite.setLocation(sprite.x, ground.y - sprite.height);
        }
    }
}