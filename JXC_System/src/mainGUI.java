
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;





import javax.swing.*;

public class mainGUI extends JPanel{
	private mainFrame jframe;
	String position="";
	JXC_View view=new JXC_View();
	JXC_Controller con=new JXC_Controller(view);
	Helper helper=new Helper();
	private JButton[] buttons=new JButton[9];
	private JLabel infoLb=new JLabel();
	private Point[] buttonPoints=new Point[]{new Point(810,20),new Point(910,20),new Point(70,280),new Point(315,280),new Point(70,360),new Point(315,360),new Point(315,440),new Point(555,280),new Point(230,600)};
	public ImageIcon[] LOGIN_BUTTONS=new ImageIcon[]{new ImageIcon("graphics/ring.png"),new ImageIcon ("graphics/ring.png"),new ImageIcon("graphics/commodity/commodity_management1.png"),new ImageIcon("graphics/customer/customer_management1.png"),new ImageIcon("graphics/stock/stock_management1.png"),new ImageIcon("graphics/import/import_management1.png"),new ImageIcon("graphics/export/export_management1.png"),new ImageIcon("graphics/account/account_management1.png"),new ImageIcon("graphics/注销1.png")};
	public ImageIcon[] LOGIN_BUTTONS_ENTERED=new ImageIcon[]{new ImageIcon("graphics/minimize.png"),new ImageIcon ("graphics/close.png"),new ImageIcon("graphics/commodity/commodity_management2.png"),new ImageIcon("graphics/customer/customer_management2.png"),new ImageIcon("graphics/stock/stock_management2.png"),new ImageIcon("graphics/import/import_management2.png"),new ImageIcon("graphics/export/export_management2.png"),new ImageIcon("graphics/account/account_management2.png"),new ImageIcon("graphics/注销2.png")};
	public ImageIcon[] LOGIN_BUTTONS_DIS=new ImageIcon[]{new ImageIcon("graphics/minimize.png"),new ImageIcon ("graphics/close.png"),new ImageIcon("graphics/commodity/commodity_management_dis.png"),new ImageIcon("graphics/customer/customer_management_dis.png"),new ImageIcon("graphics/stock/stock_management_dis.png"),new ImageIcon("graphics/import/import_management_dis.png"),new ImageIcon("graphics/export/export_management_dis.png"),new ImageIcon("graphics/account/account_management_dis.png"),new ImageIcon("graphics/注销2.png")};
	public Image bg=new ImageIcon("graphics/main_background.png").getImage();
	public Image[] LOGIN_BUTTON=new Image[]{new ImageIcon("graphics/01.png").getImage(),new ImageIcon ("graphics/02.png").getImage(),new ImageIcon("graphics/春分.png").getImage(),new ImageIcon("graphics/客户管理.png").getImage(),new ImageIcon("graphics/库存管理.png").getImage(),new ImageIcon("graphics/进货管理.png").getImage(),new ImageIcon("graphics/销售管理.png").getImage(),new ImageIcon("graphics/销售管理.png").getImage(),new ImageIcon("graphics/注销.png").getImage()};
	public mainGUI(mainFrame jframe){
		this.jframe=jframe;
		jframe.setDragable(this);
		this.setBounds(0, 0, jframe.FRAME_WIDTH, jframe.FRAME_HEIGHT);
		this.setLayout(null);
		
		helper.setFilename("data/current.txt");
		helper.split(helper.readfile().get(0));
		
		switch(helper.sArray[1]){
		case "stockManager":position="仓库管理员";break;
		case "salesman":position="销售人员";break;
		case "account":position="财务人员";break;
		}
		
		infoLb.setText(helper.sArray[0]+"，欢迎使用本系统！"+"您的身份是"+position);
		infoLb.setFont(new Font(helper.sArray[0]+"，欢迎使用本系统！"+"您的身份是"+position,1,20));
		infoLb.setForeground(new Color(243,217,34) );
		infoLb.setBounds(200,195,600,20);
		//infoLb.setOpaque(false);
		this.add(infoLb);
		
		buttonInitial();
	}
	private void buttonInitial() {
		//System.out.println(buttons.length);
		for(int i=0;i<buttons.length;i++){
			//this.buttons=new JButton[]{new JButton(LOGIN_BUTTONS[0]),new JButton(LOGIN_BUTTONS[1]),new JButton(LOGIN_BUTTONS[2]),new JButton(LOGIN_BUTTONS[3]),new JButton(LOGIN_BUTTONS[4]),new JButton(LOGIN_BUTTONS[5]),new JButton(LOGIN_BUTTONS[6]),new JButton(LOGIN_BUTTONS[7]),new JButton(LOGIN_BUTTONS[8])};
			buttonMouseAdapterAndActionListener buttonMouseAdapterAndActionListener=new buttonMouseAdapterAndActionListener(i,this);
		
			switch (position){
			case "仓库管理员":{
				if(i==4||i==2||i==0||i==1||i==8){
					buttons[i]=new JButton(LOGIN_BUTTONS[i]);
					buttons[i].setBounds(buttonPoints[i].x, buttonPoints[i].y,LOGIN_BUTTONS[i].getImage().getWidth(null),LOGIN_BUTTONS[i].getImage().getHeight(null));
		   			
					if(buttons[i].getMouseListeners().length<2){
					buttons[i].addMouseListener(buttonMouseAdapterAndActionListener);
					}
					if(buttons[i].getActionListeners().length<1){
					buttons[i].addActionListener(buttonMouseAdapterAndActionListener);
					}
				}else{
					buttons[i]=new JButton(LOGIN_BUTTONS_DIS[i]);
					buttons[i].setBounds(buttonPoints[i].x, buttonPoints[i].y,LOGIN_BUTTONS_DIS[i].getImage().getWidth(null),LOGIN_BUTTONS_DIS[i].getImage().getHeight(null));
				}
				break;
			}
			case "销售人员":{
				if(i==3||i==5||i==6||i==0||i==1||i==8){
					buttons[i]=new JButton(LOGIN_BUTTONS[i]);
					buttons[i].setBounds(buttonPoints[i].x, buttonPoints[i].y,LOGIN_BUTTONS[i].getImage().getWidth(null),LOGIN_BUTTONS[i].getImage().getHeight(null));
		   			
					if(buttons[i].getMouseListeners().length<2){
					buttons[i].addMouseListener(buttonMouseAdapterAndActionListener);
					}
					if(buttons[i].getActionListeners().length<1){
					buttons[i].addActionListener(buttonMouseAdapterAndActionListener);
					}

				}else{
					buttons[i]=new JButton(LOGIN_BUTTONS_DIS[i]);
					buttons[i].setBounds(buttonPoints[i].x, buttonPoints[i].y,LOGIN_BUTTONS[i].getImage().getWidth(null),LOGIN_BUTTONS[i].getImage().getHeight(null));
				}
				break;
			}
			case "财务人员":{
                if(i==7||i==0||i==1||i==8){
					buttons[i]=new JButton(LOGIN_BUTTONS[i]);
					buttons[i].setBounds(buttonPoints[i].x, buttonPoints[i].y,LOGIN_BUTTONS[i].getImage().getWidth(null),LOGIN_BUTTONS[i].getImage().getHeight(null));
		   			
					if(buttons[i].getMouseListeners().length<2){
					buttons[i].addMouseListener(buttonMouseAdapterAndActionListener);
					}
					if(buttons[i].getActionListeners().length<1){
					buttons[i].addActionListener(buttonMouseAdapterAndActionListener);
					}

				}else{				
					buttons[i]=new JButton(LOGIN_BUTTONS_DIS[i]);
					buttons[i].setBounds(buttonPoints[i].x, buttonPoints[i].y,LOGIN_BUTTONS[i].getImage().getWidth(null),LOGIN_BUTTONS[i].getImage().getHeight(null));
				}
                break;
			}
			}

			buttons[i].setBorderPainted(false);
			buttons[i].setContentAreaFilled(false);
			this.add(buttons[i]);
		}
		
	}
	
	public void paintComponent(Graphics g){
		 g.drawImage(bg, 0, 0, 1024, 768, null);
		 /*for(int i=0;i<buttons.length;i++){
			 g.drawImage(LOGIN_BUTTON[i], buttonPoints[i].x, buttonPoints[i].y, LOGIN_BUTTONS[i].getImage().getWidth(null), LOGIN_BUTTONS[i].getImage().getHeight(null), null);
		 }*/
	}
	
	
	class buttonMouseAdapterAndActionListener extends MouseAdapter implements ActionListener{
		int i;
		mainGUI main;
		private buttonMouseAdapterAndActionListener(int i, mainGUI main){
			this.i=i;
			this.main=main;
			
		}
		public void mouseEntered(MouseEvent e){
			buttons[i].setIcon(LOGIN_BUTTONS_ENTERED[i]);
			
		}
		public void mouseExited(MouseEvent e){
			buttons[i].setIcon(LOGIN_BUTTONS[i]);
			
		}
		public void actionPerformed(ActionEvent e) {
		if(i==0){
			//最小化
			jframe.setExtendedState(jframe.ICONIFIED); 
			//System.out.println(1);
			return;
		}
		if(i==1){
			//关闭
			System.exit(0);
			//System.out.println(0);
			return;
		}
		if(i==2&&position.equals("仓库管理员")){
			setVisible(false);
			jframe.setContentPane(new commodityGUI(jframe));
		}else{}
		if(i==3&&position.equals("销售人员")){
			setVisible(false);
			jframe.setContentPane(new customerGUI(jframe));
		}else{}
	    if(i==4&&position.equals("仓库管理员")){
			setVisible(false);
			jframe.setContentPane(new stockGUI(jframe));
		}else{}
	    if(i==5&&position.equals("销售人员")){
	    	setVisible(false);
			jframe.setContentPane(new importGUI(jframe));
	    }else{}
	    if(i==6&&position.equals("销售人员")){
	    	setVisible(false);
			jframe.setContentPane(new exportGUI(jframe));
	    }else{}
	    if(i==7&&position.equals("财务人员")){
	    	setVisible(false);
			jframe.setContentPane(new accountGUI(jframe));
	    }else{}
	    if(i==8){
	    	setVisible(false);
			jframe.setContentPane(new LoginGUI(jframe));
	    }
		}
	}
	

	
}

