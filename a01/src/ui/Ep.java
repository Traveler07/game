package ui;
//敌机
import java.io.IOException;
import java.util.Random;

public class Ep extends FlyObjeck{
    int sp ;//敌机速度
    int hp ;
    int type ;
    public Ep (){
        Random rd = new Random();
        int r = rd.nextInt(15);
        String in = "/img/ep"+r+".png";
        type = r ;
        try {
            img = App.getImg(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        w = img.getWidth() ;
        h = img.getHeight() ;
        x = rd.nextInt(512-w) ;
        y = -h ;
        sp = 16 - r ;
        hp = 5;//敌机血量
    }

    public void move() {
        if(type == 5 ){
            x-=5;
            y += sp ;
        }
        if(type == 7){
            x+=5;
            y += sp ;
        }
        y += sp ;
    }

    public boolean shootBy(Fire f) {

        boolean hit = x<=f.x +f.w && x>= f.x - w && y<= f.y + f.h && y >= f.y - h ;
        return hit ;
    }

    public boolean hitBy(Hero f) {
        boolean hit = x<=f.x +f.w && x>= f.x - w && y<= f.y + f.h && y >= f.y - h ;
        return hit ;
    }
}
