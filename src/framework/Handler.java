package framework;


import com.rosenberg.plattform.window.Game;
import objects.Block;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    //Fields
    public LinkedList<GameObject> object = new LinkedList<GameObject>();

    private GameObject tempObject;

    //Construtor
    public Handler(){

    }
    //Functions
    public void tick(){

        for (int i = 0; i < object.size() ; i++) {

            tempObject = object.get(i);

            tempObject.tick(object);
        }
    }

    public void render(Graphics g){
        for (int i = 0; i < object.size() ; i++) {

            tempObject = object.get(i);

            tempObject.render(g);
        }
    }


    public void addObject(GameObject object){
        this.object.add(object);
    }
    public void removeObject(GameObject object){
        this.object.remove(object);
    }


    public void createLevel(){
        //Boden
        for (int xx = 0; xx < Game.WIDTH+32*2; xx +=32) {
            if(xx < 160 || xx > 224) {
                addObject(new Block(xx, Game.HEIGHT - 32, ObjectId.Block));
            }
        }
        //Luft
        for (int xx = 480; xx < Game.WIDTH+32; xx +=32) {
            if(xx < 480+(32*6)) {
                addObject(new Block(xx, Game.HEIGHT - 32*5, ObjectId.Block));
            }
        }

    }
}
