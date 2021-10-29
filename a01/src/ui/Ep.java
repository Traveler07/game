package ui;
//敌机
import java.io.IOException;
import java.util.Random;

public class Ep extends FlyObjeck{
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
        y = 0 ;
    }

    public void move() {
        y += 1 ;
    }
}
