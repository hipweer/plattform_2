package objects;

import com.rosenberg.plattform.window.Game;
import framework.GameObject;
import framework.ObjectId;
import framework.Texture;

import java.awt.*;
import java.util.LinkedList;


public class Block extends GameObject {

    private int height = 32;
    private int width = 32;

    Texture tex = Game.getInstance();
    private int type;

    public Block(int type, float x,float y, ObjectId id){
        super(x,y,id); this.type=type;
    }



    public float getHeight(){return height;}
    public float getWidth() {return width;}

    public void tick(LinkedList<GameObject> object) {

    }


    public void render(Graphics g) {
        if(type == 0)
            g.drawImage(tex.block[0],(int)x,(int)y,null);
        if(type == 1)
            g.drawImage(tex.block[1],(int)x,(int)y,null);
    }


    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, height, width);
    }
}
