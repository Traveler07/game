package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//英雄机
public class Hero extends FlyObjeck {

    int hp ;
    int findex ;
    int lv ;
    int power = 20 ;
    List<Fire> fs = new ArrayList<>();//英雄机弹药库
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
         hp = 5 ;
     }
    public void shoot() {
        //生成，发射子弹
        findex++;
        if(findex >= power) {//攻速
            if(lv == 2) {
                //左
                Fire fire1 = new Fire(x + 15, y, 0);
                fs.add(fire1);
                //中
                Fire fire2 = new Fire(x + 45, y - 20, 1);
                fs.add(fire2);
                //右
                Fire fire3 = new Fire(x + 75, y, 2);
                fs.add(fire3);
            }else if(lv == 1 ){
                //左
                Fire fire1 = new Fire(x + 15, y, 1);
                fs.add(fire1);
                //右
                Fire fire3 = new Fire(x + 75, y, 1);
                fs.add(fire3);
            }else {
                Fire fire2 = new Fire(x + 45, y - 20, 1);
                fs.add(fire2);
            }
            findex = 0 ;
        }
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
