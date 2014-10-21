package objects;

import com.rosenberg.plattform.window.Game;
import framework.GameObject;
import framework.ObjectId;
import framework.Texture;

import java.awt.*;
import java.util.LinkedList;


public class BackgroundBlock extends GameObject {

    private int height = 32;
    private int width = 32;

    Texture tex = Game.getInstance();

    public BackgroundBlock(float x, float y, ObjectId id){
        super(x,y,id);
    }

    public float getHeight(){return height;}
    public float getWidth() {return width;}

    public void tick(LinkedList<GameObject> object) {

    }


    public void render(Graphics g) {
        g.setColor(new Color(155, 134, 91));
        g.fillRect((int) x, (int) y, 32, 32);
    }


    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, height, width);
    }
}
