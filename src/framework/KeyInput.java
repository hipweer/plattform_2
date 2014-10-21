package framework;

import com.rosenberg.plattform.window.Game;
import objects.Bullet;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter {

    Handler handler;
    Game game;
    public int vlar = 0;
    public int zlar = 0;

    public KeyInput(Handler handler, Game spiel){
        this.handler = handler;
        game = spiel;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size() ; i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId()== ObjectId.Player){

                if(key == KeyEvent.VK_D){
                    tempObject.setVelX(5);
                    vlar = 1;}

                if(key == KeyEvent.VK_A){
                    tempObject.setVelX(-5);
                    vlar = 2;
                }

                if(key == KeyEvent.VK_W && !tempObject.isJumping()){
                    tempObject.setJumping(true);
                    tempObject.setVelY(-12);
                }
                if(key == KeyEvent.VK_SPACE && zlar == 0){
                    zlar = 1;
                    handler.addObject(new Bullet(tempObject.getX(),tempObject.getY()+25,handler , ObjectId.Bullet,tempObject.getFacing()*10));
                }
                //Special Move
                //if(key == KeyEvent.VK_W)tempObject.bigSize();
            }
        }
        
        if(key == KeyEvent.VK_ESCAPE){
            System.exit(1);

        }
        if(key == KeyEvent.VK_P){
            if(!game.getPause())game.setPause(true);
            else game.setPause(false);
        }

    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size() ; i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ObjectId.Player){
                if(key == KeyEvent.VK_D &&  vlar == 1)tempObject.setVelX(0);
                if(key == KeyEvent.VK_A &&  vlar == 2)tempObject.setVelX(0);
                if(key == KeyEvent.VK_SPACE) zlar = 0;
                //Special Move
                //if(key == KeyEvent.VK_W)tempObject.normalSize();
            }
        }



    }

}
