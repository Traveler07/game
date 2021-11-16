package ui;

import java.io.IOException;

//子弹类
public class Fire extends FlyObjeck {

    int dir;//设置子弹移动的方向

    public Fire(int hx , int hy , int dir){
        try {
            img = App.getImg("/img/fire.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        w = img.getWidth()/4;
        h = img.getHeight()/4;
        x = hx ;
        y = hy ;
        this.dir = dir ;

    }

    public void move() {
        if(dir == 0){//左
            y -= 10 ;
            x -= 1 ;
        }else if(dir == 1){//中
            y -= 10 ;
        }else if (dir == 2){//右
            y -= 10 ;
            x += 1 ;
        }
    }
}
