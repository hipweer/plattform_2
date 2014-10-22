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
    public void clear(){
        this.object.clear();
    }
    public int getPlayerX(){
        int tempX= 0;
        for (int i = 0; i < object.size() ; i++) {

            tempObject = object.get(i);
            if(tempObject.getId() == ObjectId.Player){
                tempX =(int)tempObject.getX();
                break;
            }
        }
        return tempX;
    }
    public int getPlayerY(){
        int tempY= 0;
        for (int i = 0; i < object.size() ; i++) {

            tempObject = object.get(i);
            if(tempObject.getId() == ObjectId.Player){
               tempY = (int)tempObject.getY();
               break;
            }
        }
        return tempY;
    }

    public void createLevel(){
        //Boden
        for (int xx = 0; xx < Game.WIDTH+32*2; xx +=32) {
            if(xx < 160 || xx > 224) {
                addObject(new Block(0,xx,Game.HEIGHT - 32, ObjectId.Block));
            }
        }
        //Luft
        for (int xx = 480; xx < Game.WIDTH+32; xx +=32) {
            if(xx < 480+(32*6)) {
                addObject(new Block(1,xx, Game.HEIGHT - 32*5, ObjectId.Block));
            }
        }

    }
}
