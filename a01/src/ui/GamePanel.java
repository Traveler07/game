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
import java.util.Random;

//面板
public class GamePanel extends JPanel {
    BufferedImage bg ;//背景图
    Hero hero = new Hero();
    List<Ep> eps = new ArrayList<>();//敌机集和
    int score ;//分数
    int index = 0 ;//记录生产敌机方法执行次数
    boolean gameover = false ;
    public void action(){
        //创建一个新的线程
        new Thread(() -> {
            while(true) {
                if(!gameover) {
                    epEnter();//创建敌机
                    System.out.println(hero.power);
                    epMove();//移动敌机
                    hero.shoot();
                    shootEp();
                    hit();
                    fireMove();
                    gremove();
                }
                try {
                    Thread.sleep(20);
                    //线程休眠（毫秒）
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }).start();
    }
    private void hit() {//敌机碰撞英雄机
        for (int i = 0; i < eps.size(); i++) {
            Ep e = eps.get(i);
            if(e.hitBy(hero)){
                hero.hp-- ;
                hero.lv = 0 ;
                eps.remove(e);
                if(hero.hp <= 0){
                    gameover = true ;
                }
            }
        }
    }
    private void gremove() {//清除屏幕外子弹
        for (int i = 0; i < hero.fs.size(); i++) {
            Fire f = hero.fs.get(i);
            if (f.x < 0 && f.y < 0) {
                hero.fs.remove(f);
            }
        }
    }
    private void shootEp() {//射击敌机
        for (int i = 0; i < hero.fs.size(); i++) {
            Fire f = hero.fs.get(i);
            bang(f);
        }
    }
    private void bang(Fire f) {//击中判定
        for (int i = 0; i < eps.size(); i++) {
            Ep e = eps.get(i);
            if(e.shootBy(f)){
                e.hp --;
                if(e.hp <= 0){
                    eps.remove(e);
                    score++;
                    if(e.type == 12 ){
                        if(hero.lv < 2) {
                            hero.lv++;
                        }else if (hero.hp <8 ){
                            hero.hp++;
                        }else if(hero.power > 10){
                            hero.power -- ;
                        }
                    }
                }
                hero.fs.remove(f);
        }
        }
    }
    private void fireMove() {
        for(Fire f : hero.fs){
            f.move();
        }
    }
    private void epMove() {
        //敌机移动
        for (Ep e : eps) {
            e.move();
        }
    }
    protected void epEnter(){
        //生成敌机
        index++;
        if(index == 30) {//方法每执行20次生成一个敌机
            Ep e = new Ep();
            eps.add(e);
            index = 0 ;
        }
    }
    public GamePanel (GameFrame frame){
        setBackground(Color.BLACK);//设置背景色

        try {
            bg = App.getImg("/img/bg3.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }//导入背景图


        MouseAdapter adapter = new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(gameover){
                    gameover = false ;
                    score = 0 ;
                    hero = new Hero();
                    Random r = new Random();
                    int rbg = r.nextInt(5) + 1 ;
                    try {
                        bg = App.getImg("/img/bg"+ rbg +".jpg");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    repaint();
                }
            }

            public void mouseMoved(MouseEvent e){
                int mx = e.getX();
                int my = e.getY();
                if(!gameover) {
                    hero.moveToMouse(mx, my);
                }
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
                //刷新
            }
        };
        frame.addKeyListener(kd);//键盘监听
    }
    public void paint(Graphics g){//绘图

    g.drawImage(bg,0,0,null);//绘制背景图

        for (Fire fire : hero.fs) {//绘制子弹
            g.drawImage(fire.img,fire.x,fire.y,fire.w,fire.h,null);
        }

        for (Ep ep : eps) {//绘制敌机
            g.drawImage(ep.img, ep.x, ep.y, ep.w, ep.h, null);
        }

        g.drawImage(hero.img,hero.x,hero.y,hero.w,hero.h,null);//绘制英雄机

        g.setColor(Color.CYAN);
        g.setFont(new Font("楷体",Font.BOLD,20));
        g.drawString("分数："+ score, 10 ,35);
        for (int i = 0; i < hero.hp; i++) {
            g.drawImage(hero.img,5+i*35,50 ,30,30,null);
        }
        if(gameover) {
            g.setColor(Color.red);
            g.setFont(new Font("楷体", Font.BOLD, 45));
            g.drawString("GAMEOVER!", 120, 300);
        }
    }
}
