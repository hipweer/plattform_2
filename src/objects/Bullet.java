package objects;

import framework.GameObject;
import framework.Handler;
import framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class Bullet extends GameObject {

    Handler handler;

    public Bullet(float x, float y,Handler handler, ObjectId id, int velX) {
        super(x, y, id);
        this.velX = velX;
        this.handler = handler;
    }

    public void tick(LinkedList<GameObject> object) {
        x += velX;
        Collision(object);
    }

    private void Collision(LinkedList<GameObject> object) {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ObjectId.Block) {
                //Top
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(this);
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int) y,16,16);
    }



    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,16,16);
    }
}
