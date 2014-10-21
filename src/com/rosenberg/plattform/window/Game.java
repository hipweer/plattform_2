package com.rosenberg.plattform.window;

import framework.Handler;
import framework.KeyInput;
import framework.ObjectId;
import framework.Texture;
import objects.Block;
import objects.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Game extends Canvas implements Runnable{

    //Fields
    public static int HEIGHT ;
    public static int WIDTH ;

    private boolean running = false;
    private boolean pause;
    private Thread thread;

    private BufferedImage level = null;
    private BufferedImage clouds = null;

    //Object
    Handler handler;
    Camera cam;
    static Texture tex;

    //Constructor
    public Game() {
    }


    //Functions
    private void init(){

        HEIGHT = getHeight();
        WIDTH = getWidth();

        tex = new Texture();

        //Loading Level////////////////////////////////////////
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            level = loader.loadImage("/level.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            clouds = loader.loadImage("/background.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ///////////////////////////////////////////////////////
        handler = new Handler();

        //Level laden
        LoadImageLevel(level);

        cam = new Camera(0,0);

       //handler.addObject(new Player(100,100,handler, ObjectId.Player));
       //handler.createLevel();

        this.addKeyListener(new KeyInput(handler, this));

        pause = false;
    }

    public synchronized  void start(){
        if(running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void run(){
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                if(!pause){
                tick();}
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    private void tick(){
        handler.tick();
        for (int i = 0; i < handler.object.size() ; i++) {
            if(handler.object.get(i).getId() == ObjectId.Player){
                cam.tick(handler.object.get(i));
            }
        }

    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        /////////////////////////////////
        ///Draw Here
        g.setColor(new Color(39, 121, 234));
        g.fillRect(0, 0, getWidth(), getHeight());



        g2d.translate(cam.getX(),cam.getY()); //begin of cam, anything inside this, is affected
            for (int xx = 0; xx < 640*20; xx += 640) {
                g.drawImage(clouds,xx,0,800,640,this);
             }

            handler.render(g);
        g.setColor(new Color(255, 255, 255,25));
        g.fillOval((int)handler.getPlayerX()-60,(int)handler.getPlayerY()-50, 150, 150);

        g2d.translate(-cam.getX(),-cam.getY()); //end of cam

        /////////////////////////////////
        g.dispose();
        bs.show();

    }

    private void LoadImageLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();

        for (int xx = 0; xx < h; xx++) {
            for (int yy = 0; yy < w ; yy++) {

                int pixel = image.getRGB(xx,yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if(red == 0 && green == 0 && blue == 255){
                    handler.addObject(new Player(xx*32,yy*32,handler,ObjectId.Player,this));
                }

                if(red == 255 && green == 255 && blue == 255){
                    handler.addObject(new Block(0,xx*32,yy*32, ObjectId.Block));
                }
                if(red == 128 && green == 128 && blue == 128){
                    handler.addObject(new Block(1,xx*32,yy*32, ObjectId.Block));
                }
               //if(red == 0 && green == 255 && blue == 0){
               //     handler.addObject(new BackgroundBlock(xx*32,yy*32,ObjectId.BackgroundBlock));
               //}


            }
        }
    }
    public void setPause(Boolean pause){this.pause = pause;}
    public boolean getPause(){ return pause;}


    public void ifDead() {
        init();
    }

    public static  Texture getInstance(){
        return tex;
    }

    //Main Functions
    public static void main(String args[]){
        new Window(800, 600, "Neon shit", new Game());
    }



}
