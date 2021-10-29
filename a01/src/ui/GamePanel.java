package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//面板
public class GamePanel extends JPanel {
    BufferedImage bg ;//背景图
    Hero hero = new Hero();
    //Ep ep = new Ep();
    List<Ep> eps = new ArrayList<Ep>();//敌机集和

    public void action(){
        new Thread(){
            public void run() {
                while(true) {
                    epEnter();
                    epMove();
                    try {
                        Thread.sleep(20);
                        //线程休眠（毫秒）
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }
            }
        }.start();
    }

    private void epMove() {
        //敌机移动
        for(int i = 0 ; i < eps.size();i++){
            Ep e = eps.get(i);
            e.move();
        }
    }

    protected void epEnter(){
        //生成敌机
        Ep e = new Ep();
        eps.add (e) ;
    }

    public GamePanel (GameFrame frame){
        setBackground(Color.BLACK);//设置背景色

        try {
            bg = App.getImg("/img/bg3.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }//导入背景图


        MouseAdapter adapter = new MouseAdapter(){
            public void mouseMoved(MouseEvent e){
                int mx = e.getX();
                int my = e.getY();
                hero.moveToMouse(mx,my);
                repaint();
            }
        };//获取鼠标坐标，并，移动英雄机


        addMouseListener(adapter);//鼠标监听
        addMouseMotionListener(adapter);//鼠标监听
        KeyAdapter kd = new KeyAdapter() {//键盘监听


            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_UP){
                    //上
                    hero.moveUp();
                }else if (key == KeyEvent.VK_DOWN){
                    //下
                    hero.moveDown();
                }else if (key == KeyEvent.VK_LEFT){
                    //左
                    hero.moveLeft();
                }else if (key == KeyEvent.VK_RIGHT){
                    //右
                    hero.moveRight();
                }
                repaint();
            }
        };
        frame.addKeyListener(kd);//键盘监听
    }
    public void paint(Graphics g){//绘图
    super.paint(g);
    g.drawImage(bg,0,0,null);
    g.drawImage(hero.img,hero.x,hero.y,hero.w,hero.h,null);
        for (int i = 0; i < eps.size(); i++) {
            Ep ep = eps.get(i);
            g.drawImage(ep.img, ep.x, ep.y, ep.w, ep.h, null);
        }


    }
}
