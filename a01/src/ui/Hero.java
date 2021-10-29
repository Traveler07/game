package ui;

import java.awt.image.BufferedImage;
import java.io.IOException;

//英雄机
public class Hero {
    BufferedImage img ;
    //英雄机坐标
     int x ;
     int y ;
//     英雄机大小
     int w ;
     int h ;
     public Hero(){
         try {
             img = App.getImg("/img/hero.png");
         } catch (IOException e) {
             e.printStackTrace();
         }
         x = 200 ;
         y = 600 ;
         w = img.getWidth() ;
         h = img.getHeight() ;
     }
     public void moveToMouse(int mx,int my){
        x = mx - w/2 ;
        y = my - (h*2)/3 ;
     }
    public void moveToKey(int kx,int ky){
        x += kx ;
        y += ky ;
    }
}
