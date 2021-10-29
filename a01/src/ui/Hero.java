package ui;

import java.io.IOException;

//英雄机
public class Hero extends FlyObjeck {

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
    public void moveUp(){
        y -= 10 ;
    }
    public void moveDown(){
        y += 10 ;
    }
    public void moveLeft(){
        x-=10 ;
    }
    public void moveRight(){
        x+=10 ;
    }
}
