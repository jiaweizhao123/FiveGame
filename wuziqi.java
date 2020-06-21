package db_test;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class wuziqi extends JFrame {

    public static void main(String[] args) {
        new window();
    }
}

class window extends JFrame implements MouseListener, ActionListener {
    JPanel jp1, jp2;
    private int lenght = 60;
    JButton jb1, jb2, jb3, jb4, jb5;
    JRadioButton jr1, jr2;
    ButtonGroup group;
    private int flag = 0;
    private int x;
    private int y;
    JLabel jl1, jl2;
    int m,n;
    int[][] chack={{1,1},{-1,-1},{0,1},{1,0},{0,-1},{-1,0},{1,-1},{-1,1}};  //判断棋子连接数组
    int[][] map = new int[12][12]; //棋盘整体布局数组
    public window() {
        for(int i=0;i<12;i++){
            for(int j=0;j<12;j++){
                map[i][j]=0;
            }
        }
        jp2 = new JPanel();
        jb1 = new JButton("开始游戏");
        jb2 = new JButton("重新开始");
        jb3 = new JButton("悔棋");
        jb4 = new JButton("求和");
        jb5 = new JButton("认输");
        jl1 = new JLabel("");
        jl2 = new JLabel("0");
        group = new ButtonGroup();
        jr1 = new JRadioButton("白方先手");
        jr2 = new JRadioButton("黑方先手");
        group.add(jr1);
        group.add(jr2);
        jr1.setBounds(40, 120, 80, 30);
        jr2.setBounds(40, 160, 80, 30);
        jl1.setBounds(50, 50, 80, 20);
        jl1.setFont(new Font("宋体",Font.BOLD,16));
        jp2.setBounds(lenght * 12, 0, lenght * 3, lenght * 12);
        jp2.setLayout(null);
        jb2.setEnabled(false); // 按钮禁用
        jb3.setEnabled(false); // 按钮禁用
        jb4.setEnabled(false); // 按钮禁用
        jb5.setEnabled(false); // 按钮禁用
        jb1.setBounds(40, 310, 90, 40);
        jb2.setBounds(40, 365, 90, 40);
        jb3.setBounds(40, 420, 90, 40);
        jb4.setBounds(40, 470, 90, 40);
        jb5.setBounds(40, 520, 90, 40);
        jb1.setActionCommand("jb1");
        jb2.setActionCommand("jb2");
        jb3.setActionCommand("jb3");
        jr1.setActionCommand("jr1");
        jr2.setActionCommand("jr2");
        jb4.setActionCommand("jb4");
        jb5.setActionCommand("jb5");
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
        jb4.addActionListener(this);
        jb5.addActionListener(this);
        jb1.setBackground(Color.white);
        jb2.setBackground(Color.white);
        jb3.setBackground(Color.white);
        jb4.setBackground(Color.white);
        jb5.setBackground(Color.white);
        this.addMouseListener(this);
        jp2.setOpaque(true);
        this.add(jp2);
        jp2.add(jr1);
        jp2.add(jr2);
        jp2.add(jl1);
        jp2.add(jb1);
        jp2.add(jb2);
        jp2.add(jb3);
        jp2.add(jb4);
        jp2.add(jb5);
        this.setSize(lenght * 15, lenght * 12);
        this.setTitle("五子棋");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(3);
        this.setLayout(null);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.gray);
    }

    public void paint(Graphics g) {
        super.paint(g);
        
        g.setColor(Color.DARK_GRAY);
        Graphics2D g2 = (Graphics2D) g; // g是Graphics对象
        g2.setStroke(new BasicStroke(3.0f)); // 用以设置线条粗细
        for (int i = 1; i <= 11; i++) {
            g.drawLine(lenght * 1, lenght * i, lenght * 11, lenght * i);
            g.drawLine(lenght * i, lenght * 1, lenght * i, lenght * 11);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
        if(this.flag==1){
            jl1.setText("白棋阶段");
        }else if(this.flag==-1){
            jl1.setText("黑棋阶段");
        }
        if(x<lenght||x>lenght*11||y<lenght||y>lenght*11)
        System.out.println("棋子超界!!");
        else if(this.flag!=0){
        for(int i=1;i<=11;i++){
            for(int j=1;j<=11;j++){
                if(Math.sqrt((x-i*lenght)*(x-i*lenght)+(y-j*lenght)*(y-j*lenght))<=30){
                    this.m=i;
                    this.n=j;
                    break;
                }
            }
        }
        if(map[this.m][this.n]==0){
            map[this.m][this.n]=this.flag;
            System.out.println(this.flag);
            this.flag *= -1;
            System.out.println(this.flag);
            set_qizi(this.m, this.n);
        }

    }
    }
    public void set_qizi(int x,int y){
        Graphics g=getGraphics();
        if(map[x][y]!=0){
            if(map[x][y]==1){
                g.setColor(Color.black);
            }else{
                g.setColor(Color.white);
            }
            g.fillOval(x*lenght-15, y*lenght-15, 30, 30); 
        }
        for(int i=0;i<1;i++){
      if(game_win1()){
        JOptionPane.showMessageDialog(null, (this.flag==1?"白棋":"黑棋")+"获胜", "提示", JOptionPane.CLOSED_OPTION); //出现弹窗
        replay();
       break;
      }
      if(game_win2()){
        JOptionPane.showMessageDialog(null, (this.flag==1?"白棋":"黑棋")+"获胜", "提示", JOptionPane.CLOSED_OPTION); //出现弹窗
        replay();
        break;
      }
      if(game_win3()){
        JOptionPane.showMessageDialog(null, (this.flag==1?"白棋":"黑棋")+"获胜", "提示", JOptionPane.CLOSED_OPTION); //出现弹窗
        replay();
        break;
      }
      if(game_win4()){
        JOptionPane.showMessageDialog(null, (this.flag==1?"白棋":"黑棋")+"获胜", "提示", JOptionPane.CLOSED_OPTION); //出现弹窗
        replay();
        break;
      }
    }
    }
    public void replay(){
        repaint();
                jb2.setEnabled(false);
                jb3.setEnabled(false);
                jb4.setEnabled(false);
                jb5.setEnabled(false);
                this.flag=0;
                for(int i=0;i<12;i++){
                    for(int j=0;j<12;j++){
                        map[i][j]=0;
                    }
                }
    }
    //左右
    public boolean game_win1(){
        int count=1;
        for(int i=1;i<5;i++){
            if(n-i<1){
                break;
            }
            if(map[m][n-i]==this.flag*-1){
                count++;
            }else{
                break;
            }
        }
        for(int i=1;i<5;i++){
            if(n+i>11){
                break;
            }
            if(map[m][n+i]==this.flag*-1){
                count++;
            }else{
                break;
            }
        }
        if(count>=5){
            return true;
        }else{
            return false;
        }
    }
    //上下
    public boolean game_win2(){
        int count=1;
        for(int i=1;i<5;i++){
            if(m-i<1){
                break;
            }
            if(map[m-i][n]==this.flag*-1){
                count++;
            }else{
                break;
            }
        }
        for(int i=1;i<5;i++){
            if(m+i>11){
                break;
            }
            if(map[m+i][n]==this.flag*-1){
                count++;
            }else{
                break;
            }
        }
        if(count>=5){
            return true;
        }else{
            return false;
        }
    }
    //左上_右下
    public boolean game_win3(){
        int count=1;
        for(int i=1;i<5;i++){
            if(m-i<1||n-i<1){
                break;
            }
            if(map[m-i][n-i]==this.flag*-1){
                count++;
            }else{
                break;
            }
        }
        for(int i=1;i<5;i++){
            if(m+i>11||n+i>11){
                break;
            }
            if(map[m+i][n+i]==this.flag*-1){
                count++;
            }else{
                break;
            }
        }
        if(count>=5){
            return true;
        }else{
            return false;
        }
    }
     //右上_左下
     public boolean game_win4(){
        int count=1;
        for(int i=1;i<5;i++){
            if(m-i<1||n+i<1){
                break;
            }
            if(map[m-i][n+i]==this.flag*-1){
                count++;
            }else{
                break;
            }
        }
        for(int i=1;i<5;i++){
            if(m+i>11||n-i>11){
                break;
            }
            if(map[m+i][n-i]==this.flag*-1){
                count++;
            }else{
                break;
            }
        }
        if(count>=5){
            return true;
        }else{
            return false;
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getActionCommand().equals("jb1")){
            jb2.setEnabled(true);
            jb3.setEnabled(true);
            jb4.setEnabled(true);
            jb5.setEnabled(true);
            if(jr1.isSelected()==true){
                this.flag=-1;
            }else{
                this.flag=1;
            }
        }else if(e.getActionCommand().equals("jb2")){
            repaint();
            for(int i=0;i<12;i++){
                for(int j=0;j<12;j++){
                    map[i][j]=0;
                }
            }
            if(jr1.isSelected()==true){
                this.flag=-1;
            }else{
                this.flag=1;
            }
        }else if(e.getActionCommand().equals("jb3")){   //悔棋功能
            this.map[this.m][this.n]=0;
            Graphics g=getGraphics();
            g.setColor(Color.gray);
            g.fillOval(this.m*lenght-15, this.n*lenght-15, 30, 30); 
            g.setColor(Color.DARK_GRAY);
            Graphics2D g2 = (Graphics2D) g; // g是Graphics对象
            g2.setStroke(new BasicStroke(3.0f)); // 用以设置线条粗细
            g.drawLine(this.m*lenght-15, this.n*lenght, this.m*lenght+15, this.n*lenght);
            g.drawLine(this.m*lenght, this.n*lenght-15, this.m*lenght, this.n*lenght+15);
            this.flag*=-1;
        }else if(e.getActionCommand().equals("jb5")){  //认输功能
            int n=JOptionPane.showConfirmDialog(null, (this.flag==1?"黑方":"白方")+"确认认输吗?", "提示",JOptionPane.YES_NO_OPTION);
            if(n==0){
            if(map[this.m][this.n]==1){
                JOptionPane.showMessageDialog(null, "黑棋获胜", "提示", JOptionPane.CLOSED_OPTION); //出现弹窗
                repaint();
                jb2.setEnabled(false);
                jb3.setEnabled(false);
                jb4.setEnabled(false);
                jb5.setEnabled(false);
                this.flag=0;
                for(int i=0;i<12;i++){
                    for(int j=0;j<12;j++){
                        map[i][j]=0;
                    }
                }
            }else if(map[this.m][this.n]==-1){ 
                JOptionPane.showMessageDialog(null, "白棋获胜", "提示", JOptionPane.CLOSED_OPTION); //出现弹窗
                repaint();
                jb2.setEnabled(false);
                jb3.setEnabled(false);
                jb4.setEnabled(false);
                jb5.setEnabled(false);
                this.flag=0;
                for(int i=0;i<12;i++){
                    for(int j=0;j<12;j++){
                        map[i][j]=0;
                    }
                }
            }
        }
        }else if(e.getActionCommand().equals("jb4")){  //和棋功能
            int n=JOptionPane.showConfirmDialog(null, (this.flag==1?"白方":"黑方")+"同意对方的求和吗?", "提示",JOptionPane.YES_NO_OPTION);
            if(n==0){
                JOptionPane.showMessageDialog(null, "本局和局", "提示", JOptionPane.CLOSED_OPTION); //出现弹窗
                repaint();
                jb2.setEnabled(false);
                jb3.setEnabled(false);
                jb4.setEnabled(false);
                jb5.setEnabled(false);
                this.flag=0;
                for(int i=0;i<12;i++){
                    for(int j=0;j<12;j++){
                        map[i][j]=0;
                    }
                }
            }
        }
    }
}