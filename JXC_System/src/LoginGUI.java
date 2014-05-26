
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
public class LoginGUI extends JPanel {
	mainFrame jframe;
	JXC_View view=new JXC_View();
	JXC_Controller con=new JXC_Controller(view);
	public ImageIcon[] LOGIN_BUTTONS=new ImageIcon[]{new ImageIcon("graphics/login/loginButton1.png"),new ImageIcon("graphics/login/registerButton1.png"),new ImageIcon("graphics/ring.png"),new ImageIcon("graphics/ring.png"),new ImageIcon("graphics/confirm1.png"),new ImageIcon("graphics/cancel1.png")};
	public ImageIcon[] LOGIN_BUTTONS_ENTERED=new ImageIcon[]{new ImageIcon("graphics/login/loginButton2.png"),new ImageIcon("graphics/login/registerButton2.png"),new ImageIcon("graphics/minimize.png"),new ImageIcon("graphics/close.png"),new ImageIcon("graphics/confirm2.png"),new ImageIcon("graphics/cancel2.png")};
	public Image bg=new ImageIcon("graphics/login/login_background.png").getImage();
	public Image[] LOGIN_BUTTON=new Image[]{new ImageIcon("graphics/login/loginButton1.png").getImage(),new ImageIcon("graphics/login/registerButton1.png").getImage(),new ImageIcon("graphics/ring.png").getImage(),new ImageIcon("graphics/ring.png").getImage(),new ImageIcon("graphics/confirm1.png").getImage(),new ImageIcon("graphics/cancel1.png").getImage()};
	JButton[] buttons;
	Point[] points=new Point[]{new Point(150,575),new Point(350,575),new Point(810,20),new Point(910,20)};

    JTextField idTf=new JTextField(25);
    JPasswordField passTp=new JPasswordField(25);
    JTextField newidTf=new JTextField(25);
    JPasswordField newpassTp=new JPasswordField(25);
    JPasswordField passagainTp=new JPasswordField(25);
    
    JRadioButton doRememberMe=new JRadioButton();
    JRadioButton[] rads=new JRadioButton[]{new JRadioButton(),new JRadioButton(),new JRadioButton()}; 
    ButtonGroup group = new ButtonGroup();

    JButton yesBt=new JButton();
    JButton canBt=new JButton();
    
    int currentPage;
    String name="";
    String position="";
   
	public LoginGUI(mainFrame jframe){
		this.jframe=jframe;
		this.buttons=new JButton[]{new JButton(LOGIN_BUTTONS[0]),new JButton(LOGIN_BUTTONS[1]),new JButton(LOGIN_BUTTONS[2]),new JButton(LOGIN_BUTTONS[3]),new JButton(LOGIN_BUTTONS[4]),new JButton(LOGIN_BUTTONS[5])};
		this.setLayout(null);
		this.setVisible(true);
		this.setBounds(0, 0, jframe.FRAME_WIDTH, jframe.FRAME_HEIGHT);
		jframe.setDragable(this);
		initialButton();
		initialComponents();
		initialPanel();
	}
	
    public void initialButton(){
		for(int i=0;i<buttons.length;i++){
			buttons[i].setBorderPainted(false);
			buttons[i].setContentAreaFilled(false);

			if(i<2)
			    buttons[i].setBounds(points[i].x, points[i].y, 150, 50);
			else{
				if(i<4)
				    buttons[i].setBounds(points[i].x, points[i].y, 60, 60);
			}
			
			buttonMouseAdapterAndActionListener buttonMouseAdapterAndActionListener=new buttonMouseAdapterAndActionListener(i,this);
   			if(buttons[i].getMouseListeners().length<2){
			buttons[i].addMouseListener(buttonMouseAdapterAndActionListener);
			}
			if(buttons[i].getActionListeners().length<1){
			buttons[i].addActionListener(buttonMouseAdapterAndActionListener);
			}
			if(i<4)
			    this.add(buttons[i]);
		}
		 jframe.setContentPane(this);

	}
    
    public void initialPanel(){
    	doRememberMe.setSelected(false);
    	Helper helper=new Helper();
    	ArrayList<String> loginInfo=new ArrayList<String>();
    	helper.setFilename("data/current.txt");
    	loginInfo=helper.readfile();
    	if(loginInfo.size()==1){
    		helper.split(loginInfo.get(0));
    		String isRemembered=helper.sArray[2];
    		if(isRemembered.equals("true")){
    			idTf.setText(helper.sArray[0]);
    			passTp.setText(helper.sArray[3]);
    			doRememberMe.setSelected(true);
    		}
    	}
    	loginInfo.clear();
    	helper.output(loginInfo);
    	this.add(idTf);
    	this.add(passTp);
    	this.add(doRememberMe);
		jframe.setContentPane(this);
    }
	
	public void initialComponents(){
		 idTf.setBounds(265, 330,200,20);
		 passTp.setBounds(265, 412, 200, 20);
	     
	     idTf.setBorder(BorderFactory.createEmptyBorder());
	     passTp.setBorder(BorderFactory.createEmptyBorder());
	     
	     idTf.setFont(new Font(null,3,18));
	     passTp.setFont(new Font(null,3,18));

	     doRememberMe.setBounds(370, 485, 20, 20);
	     doRememberMe.setContentAreaFilled(false);
	     doRememberMe.setBorderPainted(false);
	     
	     for(int i=0;i<3;i++){
	    	 group.add(rads[i]);
	    	 rads[i].setContentAreaFilled(false);
	    	 rads[i].setBorderPainted(false);
	     }
	     
	     rads[0].setBounds(240, 257, 15, 15);
	     rads[1].setBounds(410, 255, 15, 15);
	     rads[2].setBounds(535, 255, 15, 15);
	     
	     newidTf.setBounds(315, 54, 200, 20);
	     newpassTp.setBounds(315, 125, 200, 20);
		 passagainTp.setBounds(315,193,200,20);
		    
		 newidTf.setBorder(BorderFactory.createEmptyBorder());
		 newpassTp.setBorder(BorderFactory.createEmptyBorder());
		 passagainTp.setBorder(BorderFactory.createEmptyBorder());
		     
		 newidTf.setFont(new Font(null,3,18));
		 newpassTp.setFont(new Font(null,3,18));
		 passagainTp.setFont(new Font(null,3,18));
		 
		 yesBt.setIcon(LOGIN_BUTTONS[4]);
		 canBt.setIcon(LOGIN_BUTTONS[5]);
		 yesBt.setBounds(175, 320, 150, 50);
		 canBt.setBounds(450, 320, 150, 50);
		 yesBt.setBorderPainted(false);
		 canBt.setBorderPainted(false);
		 yesBt.setContentAreaFilled(false);
		 canBt.setContentAreaFilled(false);
	}
	
	public void clearComponents(){
		idTf.setText("");
		passTp.setText("");
		newpassTp.setText("");;
	    passagainTp.setText("");;
	}
  
	public void paintComponent(Graphics g){
		 //super.paintComponent(g);
		 g.drawImage(bg, 0, 0, 1024, 768, null);
		 /*for(int i=0;i<buttons.length;i++){
			 g.drawImage(LOGIN_BUTTON[i], points[i].x, points[i].y, LOGIN_BUTTONS[i].getImage().getWidth(null), LOGIN_BUTTONS[i].getImage().getHeight(null), null);
		 }*/
	}
	
	class buttonMouseAdapterAndActionListener extends MouseAdapter implements ActionListener{
		int i;
		LoginGUI login;
		private buttonMouseAdapterAndActionListener(int i,LoginGUI login){
			this.i=i;
			this.login=login;
		}
		
		public void mouseEntered(MouseEvent e){
			buttons[i].setIcon(LOGIN_BUTTONS_ENTERED[i]);
		}
		
		public void mouseExited(MouseEvent e){
			buttons[i].setIcon(LOGIN_BUTTONS[i]);
		}
		
		public void actionPerformed(ActionEvent e) {
			initialComponents();
			if(yesBt.getActionListeners().length<1)
		        yesBt.addActionListener(new yesL());
			canBt.addActionListener(new canL());
			
			if(yesBt.getMouseListeners().length<2)
				yesBt.addMouseListener(new yesL());
			canBt.addMouseListener(new canL());

			String instruction;
			currentPage=i;
			System.out.println(currentPage);
		    
		if(i==0){
			//登陆
			instruction="LOGIN_LOG:"+idTf.getText().trim()+"；"+passTp.getText().trim();
		    view.setInstruction(instruction);
			con.setInstruction();
			con.go();
		    if(con.login.isSuccessful()){
		    	name=idTf.getText();
		    	position=con.login.getPosition();
		    	setVisible(false);
		    	Helper helper=new Helper();
		    	ArrayList<String> loginInfo=new ArrayList<String>();
		    	helper.setFilename("data/current.txt");
		    	if(doRememberMe.isSelected()){
		    		loginInfo.add(name+"；"+position+"；"+"true"+"；"+passTp.getText());
		    	}else{
		    		loginInfo.add(name+"；"+position+"；"+"false"+"；"+passTp.getText());
		    	}
		    	helper.output(loginInfo);
		     	jframe.setContentPane(new mainGUI(jframe));		    	
				clearComponents();

		    }else{
		    	JPanel errorPanel=new JPanel();
		    	JLabel errorLabel=new JLabel(new ImageIcon("graphics/login/error-login.png"));
		    	errorLabel.setBounds(0, 0, 450, 300);
		    	errorPanel.add(errorLabel);
		    	
		    	initialButton();
		    	login.buttons[0].setVisible(false);
		    	login.buttons[1].setVisible(false);
		    	
		    	errorPanel.add(buttons[4]);
		    	errorPanel.setBounds(100, 300, 450, 300);
		    	errorPanel.setOpaque(false);

		    	login.removeAll();
		    	login.add(errorPanel);
		    	login.add(buttons[2]);
		    	login.add(buttons[3]);
		    	jframe.setContentPane(login);
		    }
		    return;
		}
		if(i==1){
			//注册
			JPanel registerPanel=new JPanel(){
				public void paintComponent(Graphics g){
					g.drawImage(new ImageIcon("graphics/login/register.png").getImage(), 0, 0, 725, 425, null);
				}
			};
			
			registerPanel.setLayout(null);
			
			registerPanel.add(newidTf);
			registerPanel.add(newpassTp);
			registerPanel.add(passagainTp);
			
			for(int i=0;i<3;i++){
				registerPanel.add(rads[i]);
			}
			
	    	registerPanel.add(yesBt);
	    	registerPanel.add(canBt);
	    	
			registerPanel.setBounds(50, 250, 725, 425);
			registerPanel.setOpaque(false);
			login.removeAll();
			
			login.add(registerPanel);
	    	login.add(buttons[2]);
	    	login.add(buttons[3]);
	    	jframe.setContentPane(login);
			return;
		}
		
		if(i==2){
			//最小化
			jframe.setExtendedState(jframe.ICONIFIED); 
			//System.out.println(1);
			return;
		}
		
		if(i==3){
			//关闭
			System.exit(0);
			//System.out.println(0);
			return;
		}
		if(i==4){
		    jframe.setContentPane(new LoginGUI(jframe));
		    return;
		}
		
		}
		
		
		class yesL extends MouseAdapter implements ActionListener{
			String keyword;
			String instruction;
		    //@SuppressWarnings("deprecation")
			public void mouseEntered(MouseEvent e){
				yesBt.setIcon(new ImageIcon("graphics/confirm2.png"));
				
			}
			public void mouseExited(MouseEvent e){
				yesBt.setIcon(new ImageIcon("graphics/confirm1.png"));
				
			}
			
			public void actionPerformed(ActionEvent e){
				String position="";
				boolean haveError=false;
				if(newpassTp.getText().equals(passagainTp.getText())){
					    if(rads[0].isSelected()){
					    	position="stockManager";
					    }
					    if(rads[1].isSelected()){
					    	position="salesman";
					    }
					    if(rads[2].isSelected()){
					    	position="account";
					    }
					    if(rads[0].isSelected()||rads[1].isSelected()||rads[2].isSelected()==false)
					    	haveError=true;
					    if(newidTf.getText().trim()==""&&newpassTp.getText().trim()==""||passagainTp.getText().trim()=="")
					    	haveError=true;
					    if(!haveError){
						    instruction="LOGIN_RES:"+newidTf.getText().trim()+"；"+newpassTp.getText().trim()+"；"+position+"；"+"false";
						    view.setInstruction(instruction);
							con.setInstruction();
							con.go();
							
							JPanel successPanel=new JPanel(){
								public void paintComponent(Graphics g){
									g.drawImage(new ImageIcon("graphics/login/success_register.png").getImage(), 0, 0, 725, 425, null);
								}
							};
					    	successPanel.setLayout(null);
					    	
					    	initialButton();
					    	login.buttons[0].setVisible(false);
					    	login.buttons[1].setVisible(false);

					    	login.buttons[4].setBounds(287, 320, 150, 50);
					    	successPanel.setBounds(50, 250, 725, 425);
					    	successPanel.setOpaque(false);
					    	successPanel.add(login.buttons[4]);

					    	login.removeAll();
					    	login.add(successPanel);
					    	login.add(buttons[2]);
					    	login.add(buttons[3]);
					    	jframe.setContentPane(login);
					    	
					    }
					    else{
					    	JPanel errorPanel=new JPanel(){
								public void paintComponent(Graphics g){
									g.drawImage(new ImageIcon("graphics/login/new2_error_register.png").getImage(), 0, 0, 725, 425, null);
								}
							};
					    	errorPanel.setLayout(null);
					    	
					    	initialButton();
					    	login.buttons[0].setVisible(false);
					    	login.buttons[1].setVisible(false);

					    	login.buttons[4].setBounds(287, 320, 150, 50);
					    	errorPanel.setBounds(50, 250, 725, 425);
					    	errorPanel.setOpaque(false);
					    	errorPanel.add(login.buttons[4]);

					    	login.removeAll();
					    	login.add(errorPanel);
					    	login.add(buttons[2]);
					    	login.add(buttons[3]);
					    	jframe.setContentPane(login);
					    }
				}
				
				
			    //setVisible(false);
			    //jframe.setContentPane(new LoginGUI(jframe));
				
	
			}
		    
			
		}
		
		class canL extends MouseAdapter implements ActionListener{
			public void mouseEntered(MouseEvent e){
				canBt.setIcon(new ImageIcon("graphics/cancel2.png"));
				
			}
			public void mouseExited(MouseEvent e){
				canBt.setIcon(new ImageIcon("graphics/cancel1.png"));
				
			}
			public void actionPerformed(ActionEvent e){
				//login.removeAll();
				//login.initialButton();
				//login.initialPanel();
				jframe.setContentPane(new LoginGUI(jframe));
			}
			
		}
	}
	

}
