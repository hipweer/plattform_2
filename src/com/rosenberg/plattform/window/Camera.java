package com.rosenberg.plattform.window;


import framework.GameObject;

public class Camera {

    private float x;
    private float y;

    public Camera(float x, float y){

        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }
    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }

    public void tick(GameObject player) {
        if(player.getX() >= Game.WIDTH/2){
            x = x - player.getVelX();
        }
        if(player.getY() <= Game.HEIGHT/2){
            y = y - player.getVelY();
        }
        if(y <0){
            y=0;
        }
    }
}
