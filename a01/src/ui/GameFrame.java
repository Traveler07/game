package ui;
//窗体
import javax.swing.JFrame ;

public class GameFrame extends JFrame{
    public GameFrame(){
        setTitle("雷霆战机");
        setSize(512,768);//窗口大小
        setLocationRelativeTo(null);//居中
        setResizable(false);//禁止调整窗口大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口结束进程
    }
    public static void main(String[] args){
        GameFrame frame = new GameFrame() ;
        GamePanel panel = new GamePanel(frame) ;
        frame.add(panel);
        frame.setVisible(true);//创建窗口

    }
}
