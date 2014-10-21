package framework;

import java.awt.*;
import java.util.LinkedList;


public abstract class GameObject {

    //Fields
    protected float x;
    protected float y;
    protected float velX =0; //velocitiyX
    protected float velY =0; //velocitiyY

    protected float height ;
    protected float width ;

    protected int facing = 1;


    protected boolean falling = true;
    protected boolean jumping = false;

    protected ObjectId id;

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    //Constructor
    public GameObject(float x, float y, ObjectId id){
        this.x = x;
        this.y = y;

        this.id = id;
    }

    //Fields
    public abstract void tick(LinkedList<GameObject> object);
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }

    public float getVelX(){
        return velX;
    }
    public float getVelY(){
        return velY;
    }
    public void setVelX(float velX){
        this.velX = velX;
    }
    public void setVelY(float velY){
        this.velY = velY;
    }

    public float getHeight(){return height;}
    public float getWidth() {return width;}

    public int getFacing(){return facing;}


    public ObjectId getId(){
        return id;
    }

    //Special Moves
    public void bigSize(){ height += 5; width *= 2;x -=width/2;}
    public void normalSize(){ height -= 5; width /= 2;x +=width/2;}
}
