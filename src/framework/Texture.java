package framework;


import com.rosenberg.plattform.window.BufferedImageLoader;

import java.awt.image.BufferedImage;

public class Texture {

    SpriteSheet blocks;
    SpriteSheet players;

    private BufferedImage block_sheet = null;
    private BufferedImage player_sheet = null;

    public BufferedImage[] block = new BufferedImage[2];
    public BufferedImage[] player = new BufferedImage[14];
    public BufferedImage[] playerJumpR = new BufferedImage[1];
    public BufferedImage[] playerJumpL = new BufferedImage[1];

    public Texture(){

        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            block_sheet = loader.loadImage("/block_sheet.png");
            player_sheet = loader.loadImage("/player_sheet.png");
        }catch (Exception e){
            e.printStackTrace();
        }

        blocks = new SpriteSheet(block_sheet);
        players = new SpriteSheet(player_sheet);

        getTextures();
    }

    private void getTextures(){
        block[0] = blocks.grabImage(1,1,32,32);
        block[1] = blocks.grabImage(2,1,32,32);
        //Right
        player[0] = players.grabImage(1,1,32,64);//idle
        player[1] = players.grabImage(2,1,32,64);
        player[2] = players.grabImage(3,1,32,64);
        player[3] = players.grabImage(4,1,32,64);
        player[4] = players.grabImage(5,1,32,64);
        player[5] = players.grabImage(6,1,32,64);
        player[6] = players.grabImage(7,1,32,64);
        //Left
        player[7] = players.grabImage(20,1,32,64);//idle
        player[8] = players.grabImage(19,1,32,64);
        player[9] = players.grabImage(18,1,32,64);
        player[10] = players.grabImage(17,1,32,64);
        player[11] = players.grabImage(16,1,32,64);
        player[12] = players.grabImage(15,1,32,64);
        player[13] = players.grabImage(14,1,32,64);

        //Jump
        playerJumpR[0] = players.grabImage(10,2,32,64);
        playerJumpL[0] = players.grabImage(11,2,32,64);



    }

}
