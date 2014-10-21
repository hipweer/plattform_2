package objects;

import com.rosenberg.plattform.window.Animation;
import com.rosenberg.plattform.window.Game;
import framework.GameObject;
import framework.Handler;
import framework.ObjectId;
import framework.Texture;

import java.awt.*;
import java.util.LinkedList;


public class Player extends GameObject{

    //Fields
    private float width = 28;
    private float height = 54;

    private final float MAX_SPEED = 15;
    private float gravity = 0.5f;


    Handler handler;
    Game game;

    Texture tex = Game.getInstance();

    private Animation playerWalk;
    private Animation playerWalkLeft;
    private Animation playerWalkJumpR;
    private Animation playerWalkJumpL;

    //Constructor
    public Player(float x, float y, Handler handler, ObjectId id, Game game) {
        super(x, y, id);
        this.handler = handler;
        this.game = game;
        playerWalk = new Animation(6,tex.player[1],tex.player[2],tex.player[3],tex.player[4],tex.player[5],tex.player[6]);
        playerWalkLeft = new Animation(6,tex.player[8],tex.player[9],tex.player[10],tex.player[11],tex.player[12],tex.player[13]);
        playerWalkJumpR = new Animation(6,tex.playerJumpR[0]);
        playerWalkJumpL = new Animation(6,tex.playerJumpL[0]);
    }

    //Functions
    public void tick(LinkedList<GameObject> object) {
        x += velX;
        y += velY;

        if(falling || jumping){
            velY += gravity;

            if(velY > MAX_SPEED)
               velY = MAX_SPEED;
        }

        if(velX<0) facing = -1;
        else if(velX>0) facing = 1;

        Collision(object);
        playerWalk.runAnimation();
        playerWalkLeft.runAnimation();
        playerWalkJumpR.runAnimation();
        playerWalkJumpL.runAnimation();
        if(y > Game.HEIGHT){
            game.ifDead();

        }
    }

    private void Collision(LinkedList<GameObject> object){
        for (int i = 0; i < handler.object.size() ; i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ObjectId.Block){
                //Top
                if(getBoundsTop().intersects(tempObject.getBounds())){
                    y = tempObject.getY() + tempObject.getHeight();
                    velY = 0;
                }
                //Bottom
                if(getBounds().intersects(tempObject.getBounds())){
                    y = tempObject.getY()-height;
                    velY = 0;
                    falling = false;
                    jumping = false;
                }else falling = true;
                //Right
                if(getBoundsRight().intersects(tempObject.getBounds())){
                    x = tempObject.getX() - tempObject.getWidth();
                    velX = 0;
                }
                //Left
                if(getBoundsLeft().intersects(tempObject.getBounds())){
                    x = tempObject.getX() + tempObject.getWidth();
                    velX = 0;
                }
            }
        }
    }

    public void render(Graphics g) {

        if(velX != 0 && !jumping){
            if(facing == 1)
            playerWalk.drawAnimation(g, (int) x, (int) y, 28, 54);
            else
                playerWalkLeft.drawAnimation(g, (int) x, (int) y, 28, 54);
        }else {
            if (!jumping) {
                if (facing == 1)
                    g.drawImage(tex.player[0], (int) x, (int) y, 28, 54, null);
                else g.drawImage(tex.player[7], (int) x, (int) y, 28, 54, null);
            } else {
                if (facing == 1) playerWalkJumpR.drawAnimation(g, (int) x, (int) y, 28, 54);
                else playerWalkJumpL.drawAnimation(g, (int) x, (int) y, 28, 54);
            }
        }
    }

    //Bounding Boxes
    public Rectangle getBounds() {
        return new Rectangle(((int)x+((int)(width/2)-((int)width/2)/2)),((int)y+((int)height/2)),(int)width/2,(int)height/2);
        }
    public Rectangle getBoundsTop() {
        return new Rectangle((int) ((int)x+((width/2)-((width/2)/2))),(int)y,(int)width/2,(int)height/2);
    }
    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int)x+width-5),(int)y,(int)5,(int)height-10);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x,(int)y+5,(int)5,(int)height-10);
    }

    //Special Move
    public void bigSize(){ height += 5; width *= 2;x -=width/2;}
    public void normalSize(){ height -= 5; width /= 2;x +=width/2;}

    public float getX() {return  x;}
    public float getY() {return  y;}

}
