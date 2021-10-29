package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

//面板
public class GamePanel extends JPanel {
    BufferedImage bg ;//背景图
    Hero hero = new Hero();


    public GamePanel (GameFrame frame){
        setBackground(Color.BLACK);

        try {
            bg = App.getImg("/img/bg3.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        MouseAdapter adapter = new MouseAdapter(){
            public void mouseMoved(MouseEvent e){
                int mx = e.getX();
                int my = e.getY();
                hero.moveToMouse(mx,my);
                repaint();
            }
        };
        addMouseListener(adapter);
        addMouseMotionListener(adapter);
        KeyAdapter kd = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                int kx = 0 ;
                int ky = 0 ;
                if (key == KeyEvent.VK_UP){
                    //上
                    ky -= 10 ;
                }else if (key == KeyEvent.VK_DOWN){
                    //下
                    ky += 10 ;
                }else if (key == KeyEvent.VK_LEFT){
                    //左
                    kx -= 10 ;
                }else if (key == KeyEvent.VK_RIGHT){
                    //右
                    kx += 10 ;
                }
                hero.moveToKey(kx,ky);
                repaint();
            }
        };
        frame.addKeyListener(kd);
    }
    public void paint(Graphics g){
    super.paint(g);
    g.drawImage(bg,0,0,null);
    g.drawImage(hero.img,hero.x,hero.y,hero.w,hero.h,null);
    }
}
