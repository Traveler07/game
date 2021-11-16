package ui;
//敌机
import java.io.IOException;
import java.util.Random;

public class Ep extends FlyObjeck{
    int sp ;//敌机速度
    public Ep (){
        Random rd = new Random();
        int r = rd.nextInt(15);
        String in = "/img/ep"+r+".png";
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
    }

    public void move() {
        y += sp ;
    }

    public boolean shootBy(Fire f) {

        boolean hit = x<=f.x +f.w && x>= f.x - w && y<= f.y + f.h && y >= f.y - h ;
        return hit ;
    }
}
