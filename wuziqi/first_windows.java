package wuziqi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class first_windows extends JFrame implements ActionListener {
    BackgroundPanel bgp;
    JLabel jl1;
    JComboBox jcb;
    JButton jb1, jb2;

    public static void main(String[] args) {
        new first_windows();
    }

    public first_windows() {
        jcb = new JComboBox<String>();
        jb1 = new JButton("载入棋盘");
        jb2 = new JButton("退出游戏");
        jb1.setBounds(240, 330, 150, 50);
        jb2.setBounds(240, 390, 150, 50);
        jb1.setFont(new Font("宋体", 1, 20));
        jb2.setFont(new Font("宋体", 1, 20));
        jb1.setBackground(Color.lightGray);
        jb2.setBackground(Color.lightGray);
        jcb.addItem("-游戏模式-");
        jcb.addItem("玩家对战");
        jcb.addItem("人机对战");
        jcb.setFont(new Font("宋体", 1, 23));
        jcb.setBounds(240, 260, 150, 50);
        jcb.setBackground(Color.lightGray);
        jl1 = new JLabel("五子棋");
        jl1.setFont(new Font("宋体", Font.BOLD, 40));
        jl1.setBounds(255, 80, 150, 40);
        Container l1 = this.getContentPane();
        bgp = new BackgroundPanel((new ImageIcon("image/背景2.png")).getImage());
        bgp.setBounds(0, 0, 650, 560);
        bgp.setLayout(null);
        this.add(bgp);
        bgp.add(jl1);
        bgp.add(jcb);
        bgp.add(jb1);
        bgp.add(jb2);
        this.setTitle("五子棋");
        this.setSize(650, 560);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 设置可以通过图形化窗口直接关闭进程
        this.setResizable(false);
        this.setVisible(true);
        jb1.setActionCommand("jb1");
        jb2.setActionCommand("jb2");
        jb1.addActionListener(this);
        jb2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getActionCommand().equals("jb1")){
          if(jcb.getSelectedIndex()==1){
            wuziqi n1=new wuziqi();
            n1.initUI();
            this.dispose();
        }else if(jcb.getSelectedIndex()==2){
            new window2();
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "请选择模式", "提示", JOptionPane.CLOSED_OPTION); //出现弹窗
        }
        }else if(e.getActionCommand().equals("jb2")){
            System.exit(0);
        }
    }

}
class BackgroundPanel extends JPanel
{
	Image im;
	public BackgroundPanel(Image im)
	{
		this.im=im;
		this.setOpaque(true);
	}
	//Draw the back ground.
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);
		
	}
}