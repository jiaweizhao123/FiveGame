package wuziqi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class wuziqi2 extends JFrame {

    public static void main(String[] args) {
        new window2();
    }
}

class window2 extends JFrame implements MouseListener, ActionListener {
    JPanel jp1, jp2;
    private int lenght = 60;
    JButton jb1, jb2, jb3, jb4, jb5;
    JRadioButton jr1, jr2;
    ButtonGroup group;
    private int flag = 0;
    HashMaps hashmap;
    private int x;
    private int y;
    JLabel jl1, jl2;
    int[][] first_level=new int[12][12];  //人机权值
    int m,n;
    int[][] chack={{1,1},{-1,-1},{0,1},{1,0},{0,-1},{-1,0},{1,-1},{-1,1}};  //ai下棋位置数组
    int[][] map = new int[12][12]; //棋盘整体布局数组
    public window2() {
        hashmap=new HashMaps();
        this.hashmap.initmap();
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
            System.out.println(this.flag);
          
        }
        set_qizi(m,n);
    }
    }
    public void set_qizi(int x,int y){
        Graphics g=getGraphics();
        if(map[x][y]!=0){
                g.setColor(Color.black);
                g.fillOval(x*lenght-15, y*lenght-15, 30, 30); 
                        
        for(int i=0;i<1;i++){
            if(game_win1(1)){
              JOptionPane.showMessageDialog(null,"黑棋获胜", "提示", JOptionPane.CLOSED_OPTION); //出现弹窗
              replay();
             break;
            }
            if(game_win2(1)){
              JOptionPane.showMessageDialog(null,"黑棋获胜", "提示", JOptionPane.CLOSED_OPTION); //出现弹窗
              replay();
              break;
            }
            if(game_win3(1)){
              JOptionPane.showMessageDialog(null,"黑棋获胜", "提示", JOptionPane.CLOSED_OPTION); //出现弹窗
              replay();
              break;
            }
            if(game_win4(1)){
              JOptionPane.showMessageDialog(null,"黑棋获胜", "提示", JOptionPane.CLOSED_OPTION); //出现弹窗
              replay();
              break;
            }
          }
                ai_play();
        }
    }
    public void ai_play(){
        int k1=0,k2=0;
        int max=0;
        for(int i=0;i<11;i++){   //对已下过棋的地点权值归零
            for(int j=0;j<11;j++){
                if(map[i][j]==0)
                first_level[i][j]=0;
            }
        }
        System.out.println("test1");
        for(int i=1;i<=11;i++){
            for(int j=1;j<=11;j++){
                if(map[i][j]==0){
                  game_win1s(i,j);
                  game_win2s(i,j);
                  game_win3s(i,j);
                  game_win4s(i,j);
                  if(first_level[i][j]>max){
                      k1=i;
                      k2=j;
                      max=first_level[i][j];
                  }
                }
            }
        }
        System.out.println(k1+","+k2+","+first_level[k1][k2]);
       Graphics g=getGraphics();
       g.setColor(Color.white);
       map[k1][k2]=2;
       g.fillOval(k1*lenght-15, k2*lenght-15, 30, 30);
       this.m=k1;
       this.n=k2;
       for(int i=0;i<1;i++){
        if(game_win1(2)){
          JOptionPane.showMessageDialog(null, "白棋获胜", "提示", JOptionPane.CLOSED_OPTION); //出现弹窗
          replay();
         break;
        }
        if(game_win2(2)){
          JOptionPane.showMessageDialog(null, "白棋获胜", "提示", JOptionPane.CLOSED_OPTION); //出现弹窗
          replay();
          break;
        }
        if(game_win3(2)){
          JOptionPane.showMessageDialog(null, "白棋获胜", "提示", JOptionPane.CLOSED_OPTION); //出现弹窗
          replay();
          break;
        }
        if(game_win4(2)){
          JOptionPane.showMessageDialog(null, "白棋获胜", "提示", JOptionPane.CLOSED_OPTION); //出现弹窗
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
    public boolean game_win1(int flag){
        int count=1;
        for(int i=1;i<5;i++){
            if(n-i<1){
                break;
            }
            if(map[m][n-i]==flag){
                count++;
            }else{
                break;
            }
        }
        for(int i=1;i<5;i++){
            if(n+i>11){
                break;
            }
            if(map[m][n+i]==flag){
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
    public void game_win1s(int p,int q){
        
       String key="0";
       int flags=0;
       int count=0;
       for(int i=1;i<5;i++){
        if(q+i>11)
        break;
         if(map[p][q+i]!=0){
             flags=map[p][q+i];
             break;
         }
    }
       for(int i=1;i<5;i++){
           if(q+i>11){
           break;
        }
            if(map[p][q+i]!=flags&&map[p][q+i]!=0){
                key+=map[p][q+i]+"";
                break;
        }
            key+=map[p][q+i]+"";     
       }
       first_level[p][q]+=this.hashmap.get(key);
       key="0";
       flags=0;
       for(int i=1;i<5;i++){
        if(q-i<1)
        break;
         if(map[p][q-i]!=0){
             flags=map[p][q-i];
             break;
         }
    }
       for(int i=1;i<5;i++){
           if(q-i<1)
           break;
            if(map[p][q-i]!=flags&&map[p][q-i]!=0){
                key+=map[p][q-i]+"";
                break;
        }
            key+=map[p][q-i]+"";
       }
       first_level[p][q]+=this.hashmap.get(key);
    }
    //上下
    public boolean game_win2(int flag){
        int count=1;
        for(int i=1;i<5;i++){
            if(m-i<1){
                break;
            }
            if(map[m-i][n]==flag){
                count++;
            }else{
                break;
            }
        }
        for(int i=1;i<5;i++){
            if(m+i>11){
                break;
            }
            if(map[m+i][n]==flag){
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
    public void game_win2s(int p,int q){
        String key="0";
        int flags=0;
        int count=0;
        for(int i=1;i<5;i++){
         if(p+i>11)
         break;
          if(map[p+i][q]!=0){
              flags=map[p+i][q];
              break;
          }
        }

        for(int i=1;i<5;i++){
            if(p+i>11)
            break;
             if(map[p+i][q]!=0&&map[p+i][q]!=flags){
                key+=map[p+i][q]+"";
                break;
            }
             key+=map[p+i][q]+"";
        }
        first_level[p][q]+=this.hashmap.get(key);
        key="0";
        flags=0;
        for(int i=1;i<5;i++){
         if(p-i<1)
         break;
          if(map[p-i][q]!=0){
              flags=map[p-i][q];
              break;
          }
     }
        for(int i=1;i<5;i++){
            if(p-i<1)
            break;
             if(map[p-i][q]!=0&&map[p-i][q]!=flags){
                key+=map[p-i][q]+"";
                break;
            }
             key+=map[p-i][q]+"";
        }
        first_level[p][q]+=this.hashmap.get(key);
    }
    //左上_右下
    public boolean game_win3(int flag){
        int count=1;
        for(int i=1;i<5;i++){
            if(m-i<1||n-i<1){
                break;
            }
            if(map[m-i][n-i]==flag){
                count++;
            }else{
                break;
            }
        }
        for(int i=1;i<5;i++){
            if(m+i>11||n+i>11){
                break;
            }
            if(map[m+i][n+i]==flag){
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
    public  void game_win3s(int p,int q){
        String key="0";
        int flags=0;
        int count=0;
        for(int i=1;i<5;i++){
         if(q+i>11||p+i>11)
         break;
          if(map[p+i][q+i]!=0){
              flags=map[p+i][q+i];
              break;
          }
     }
        for(int i=1;i<5;i++){
            if(q+i>11||p+i>11)
            break;
             if(map[p+i][q+i]!=0&&map[p+i][q+i]!=flags){
                key+=map[p+i][q+i]+"";
                break;
            }
             key+=map[p+i][q+i]+"";
        }
        first_level[p][q]+=this.hashmap.get(key);
      
        key="0";
        flags=0;
        
        for(int i=1;i<5;i++){
         if(q-i<1||p-i<1)
         break;
          if(map[p-i][q-i]!=0){
              flags=map[p-i][q-i];
              break;
          }
     }
   
        for(int i=1;i<5;i++){
            if(q-i<1||p-i<1)
            break;
           
             if(map[p-i][q-i]!=0&&map[p-i][q-i]!=flags){
                key+=map[p-i][q-i]+"";
                break;
             }
             key+=map[p-i][q-i]+"";
        }
        first_level[p][q]+=this.hashmap.get(key);
    }
     //右上_左下
     public boolean game_win4(int flag){
        int count=1;
        for(int i=1;i<5;i++){
            if(m-i<1||n+i<1){
                break;
            }
            if(map[m-i][n+i]==flag){
                count++;
            }else{
                break;
            }
        }
        for(int i=1;i<5;i++){
            if(m+i>11||n-i>11){
                break;
            }
            if(map[m+i][n-i]==flag){
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
    public void game_win4s(int p,int q){
        String key="0";
       int flags=0;
       int count=0;
       for(int i=1;i<5;i++){
        if(q+i>11||p-i<1)
        break;
         if(map[p-i][q+i]!=0){
             flags=map[p-i][q+i];
             break;
         }
    }
       for(int i=1;i<5;i++){
           if(q+i>11||p-i<1)
           break;
            if(map[p-i][q+i]!=0&&map[p-i][q+i]!=flags){
                key+=map[p-i][q+i]+"";
                break;
            }
            key+=map[p-i][q+i]+"";
       }
       first_level[p][q]+=this.hashmap.get(key);
       key="0";
       flags=0;
       for(int i=1;i<5;i++){
        if(q-i<1||p+i>11)
        break;
         if(map[p+i][q-i]!=0){
             flags=map[p+i][q-i];
             break;
         }
    }
       for(int i=1;i<5;i++){
           if(q-i<1||p+i>11)
           break;
            if(map[p+i][q-i]!=0&&map[p+i][q-i]!=flags){
                key+=map[p+i][q-i]+"";
                break;
            }
            key+=map[p+i][q-i]+"";
       }
       first_level[p][q]+=this.hashmap.get(key);
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
            this.flag=1;
            jb2.setEnabled(true);
            jb3.setEnabled(false);
            jb4.setEnabled(true);
            jb5.setEnabled(true);
        }else if(e.getActionCommand().equals("jb2")){
            repaint();
            for(int i=0;i<12;i++){
                for(int j=0;j<12;j++){
                    map[i][j]=0;
                }
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