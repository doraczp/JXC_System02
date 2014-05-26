import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class accountGUI extends JPanel {
	mainFrame jframe;
	JXC_View view=new JXC_View();
	JXC_Controller con=new JXC_Controller(view);
	JButton[] buttons=new JButton[]{new JButton("创建收款单"),new JButton("创建付款单"),new JButton("查看总账目"),new JButton("账目初始化"),new JButton("后退")};
	Point[] points=new Point[]{new Point(20,100),new Point(20,175),new Point(20,325),new Point(20,400),new Point(20,475)};
	JPanel opPanel=new JPanel();
	JLabel customerLb=new JLabel("客户姓名");
    JLabel receiveLb=new JLabel("收款金额");
    JLabel payLb=new JLabel("付款金额");
    JLabel yearLb=new JLabel("年");
    JLabel monthLb=new JLabel("月");
    JLabel dayLb=new JLabel("日");
    JLabel initialLb=new JLabel("初始总额");
    
    JTextField cusTf=new JTextField(25);
    JTextField recTf=new JTextField(25);
    JTextField payTf=new JTextField(25);
    JTextField yearTf=new JTextField(5);
    JTextField monthTf=new JTextField(3);
    JTextField dayTf=new JTextField(3);
    JTextField iniTf=new JTextField(25);
    
    JButton yesBt=new JButton("确认");
    JButton canBt=new JButton("取消");
    
    int currentPage;
   
   
	public accountGUI(mainFrame jframe){
		this.jframe=jframe;
		this.setLayout(null);
		this.setVisible(true);
		this.setBounds(0, 0, jframe.FRAME_WIDTH/2, jframe.FRAME_HEIGHT);
		jframe.setDragable(this);
		initialButton();
		initialopPanel();
		addOpPanel();
	}
	
	public void addOpPanel(){
		opPanel.setBounds(512,0,512,768);
		opPanel.setLayout(null);
        opPanel.setVisible(true);
		this.add(opPanel);
		jframe.setContentPane(this);
	}
	public void initialopPanel(){
		String instruction="ACCOUNT_DET:";
	    view.setInstruction(instruction);
	    con.setInstruction();
	    con.go();
	    
		
		String[] colomn={"时间","单据性质","客户姓名","收款金额","付款金额"};

		 DefaultTableModel tablem=new DefaultTableModel(con.getMessageTable(),colomn){
	    	 public boolean isCellEditable(int row,int colomn) {
	    	     return false;
	    	    }
	     };
	     JTable table=new JTable();
	     table.setModel(tablem);
	    JScrollPane scrollPane=new JScrollPane(table);
	    scrollPane.setBounds(0, 200, 500, 300);
		opPanel.add(scrollPane);
		
	}
	public void initialButton(){
		for(int i=0;i<buttons.length;i++){
			buttons[i].setBorderPainted(false);
			buttons[i].setBounds(points[i].x, points[i].y, 100, 50);
			buttonMouseAdapterAndActionListener buttonMouseAdapterAndActionListener=new buttonMouseAdapterAndActionListener(i,this);
   			if(buttons[i].getMouseListeners().length<2){
			buttons[i].addMouseListener(buttonMouseAdapterAndActionListener);
			}
			if(buttons[i].getActionListeners().length<1){
			buttons[i].addActionListener(buttonMouseAdapterAndActionListener);
			}
			this.add(buttons[i]);
		}
		
		 jframe.setContentPane(this);

	}
	
	public void initialComponents(){
		 customerLb.setBounds(20, 100, 100, 50);
	     receiveLb.setBounds(20, 175, 100, 50);
	     payLb.setBounds(20, 175, 100, 50);
		 //timeLb.setBounds(20, 250, 100, 50);
	     yearLb.setBounds(20, 250, 100, 50);
		 monthLb.setBounds(180, 250, 50, 50);
		 dayLb.setBounds(300,250,50,50);
		 initialLb.setBounds(20,100,100,50);
	
		 cusTf.setBounds(150, 100,300,20);
		 recTf.setBounds(150, 175, 300, 20);
	     payTf.setBounds(150, 175, 300, 20);
		 //tmTf.setBounds(150, 250, 300, 20);
	     yearTf.setBounds(120,250,50,20);
		 monthTf.setBounds(230,250,40,20);
		 dayTf.setBounds(330,250,40,20);
		 iniTf.setBounds(150, 100, 300, 20);


		
		 
		 yesBt.setBounds(20, 575, 100, 50);
		 canBt.setBounds(150,575,100,50);
		 yesBt.setBorderPainted(false);
		 canBt.setBorderPainted(false);
		 
	}
	
	public void clearComponents(){
		cusTf.setText("");
		recTf.setText("");
		payTf.setText("");
		iniTf.setText("");
		yearTf.setText("");
		monthTf.setText("");
		dayTf.setText("");
		
	}
	
	class buttonMouseAdapterAndActionListener extends MouseAdapter implements ActionListener{
		int i;
		accountGUI account;
		private buttonMouseAdapterAndActionListener(int i, accountGUI account){
			this.i=i;
			this.account=account;
			
		}
		
		/*public void mouseEntered(MouseEvent e){
			buttons[i].setIcon(LOGIN_BUTTONS_ENTERED[i]);
			
		}
		public void mouseExited(MouseEvent e){
			buttons[i].setIcon(LOGIN_BUTTONS[i]);
			
		}*/
		public void actionPerformed(ActionEvent e) {
			initialComponents();
			if(yesBt.getActionListeners().length<1)
		        yesBt.addActionListener(new yesL());
			canBt.addActionListener(new canL());
			
			currentPage=i;
			System.out.println(currentPage);
		    
		if(i==0){
			//添加收款单
			opPanel.removeAll();
			clearComponents();
			
					
			opPanel.add(customerLb);
		    opPanel.add(receiveLb);
		    opPanel.add(yearLb);
		    opPanel.add(monthLb);
		    opPanel.add(dayLb);
		    
		    opPanel.add(cusTf);
		    opPanel.add(recTf);
		    opPanel.add(yearTf);
		    opPanel.add(monthTf);
		    opPanel.add(dayTf);
		   
		
		    opPanel.add(yesBt);
		    opPanel.add(canBt);

		    addOpPanel();
		    
		    return;
		}
		if(i==1){
			//添加付款单
			opPanel.removeAll();
			clearComponents();
			
			
			opPanel.add(customerLb);
			opPanel.add(payLb);
			opPanel.add(yearLb);
		    opPanel.add(monthLb);
		    opPanel.add(dayLb);
			
			opPanel.add(cusTf);
			opPanel.add(payTf);
			opPanel.add(yearTf);
		    opPanel.add(monthTf);
		    opPanel.add(dayTf);
			
			opPanel.add(yesBt);
			opPanel.add(canBt);
		    addOpPanel();
		
			return;
		}
		
		
		
		if(i==2){
			//显示总账目
			String instruction="ACCOUNT_ALL:";
	        view.setInstruction(instruction);
	        con.setInstruction();
	        con.go();
			opPanel.removeAll();
			clearComponents();
			
			JLabel label=new JLabel(con.getMessageText());
			System.out.println("Message text: "+con.getMessageText());
			label.setBounds(50, 200,300,300);
			opPanel.add(label);
			//opPanel.add(yesBt);
			//opPanel.add(canBt);
		    addOpPanel();
		
			return;
		}
		if(i==3){
			//初始化账目
			opPanel.removeAll();
			clearComponents();
			opPanel.add(initialLb);
			opPanel.add(iniTf);
			opPanel.add(yesBt);
			opPanel.add(canBt);
		    addOpPanel();
		
			return;
		}
		
		if(i==4){
			setVisible(false);
			jframe.setContentPane(new mainGUI(jframe));
		}
		}
		
		
		class yesL implements ActionListener{
			String keyword;
			String instruction;
			
			
			public void actionPerformed(ActionEvent e){
				class confirm implements ActionListener{
					public void actionPerformed(ActionEvent e){
						view.setInstruction(instruction);
						con.setInstruction();
						con.go();
						
						opPanel.removeAll();
						initialopPanel();
						addOpPanel();
					}
				}
				
				class cancel implements ActionListener{
					public void actionPerformed(ActionEvent e){
						opPanel.removeAll();
						initialopPanel();
						addOpPanel();
					}
				}
				switch (currentPage){
				
				case 0:{instruction="ACCOUNT_IN:"+cusTf.getText().trim()+"；"+yearTf.getText().trim()+"/"+monthTf.getText().trim()+"/"+dayTf.getText().trim();
				    opPanel.removeAll();
				    
				    JLabel label=new JLabel("<html>"+"                    确认如下收款单？"+"<br>"+"-----------------------------------------------------------"+"<br><br>"+"客户姓名："+cusTf.getText().trim()+"<br><br>"+"收款金额："+recTf.getText().trim()+"<br><br>"+"时间"+yearTf.getText().trim()+"/"+monthTf.getText().trim()+"/"+dayTf.getText().trim()+"<br><br>"+"---------------------------------------------------------"+"</html>");
					label.setBounds(50, 50,400,400);
					JButton confirmBt=new JButton("确认");
					JButton cancelBt=new JButton("取消");
					confirmBt.setBounds(50, 600, 100, 50);
					cancelBt.setBounds(300, 600, 100, 50);
					confirmBt.addActionListener(new confirm());
					cancelBt.addActionListener(new cancel());
				    opPanel.add(label);
				    opPanel.add(confirmBt);
				    opPanel.add(cancelBt);
					addOpPanel();
					
				    break;
				}
				case 1:{instruction="ACCOUNT_OUT:"+cusTf.getText().trim()+"；"+yearTf.getText().trim()+"/"+monthTf.getText().trim()+"/"+dayTf.getText().trim();
				    clearComponents();
				    opPanel.removeAll();
				    
				    JLabel label=new JLabel("<html>"+"                    确认如下付款单？"+"<br>"+"-----------------------------------------------------------"+"<br><br>"+"客户姓名："+cusTf.getText().trim()+"<br><br>"+"付款金额："+recTf.getText().trim()+"<br><br>"+"时间"+yearTf.getText().trim()+"/"+monthTf.getText().trim()+"/"+dayTf.getText().trim()+"<br><br>"+"---------------------------------------------------------"+"</html>");
					label.setBounds(50, 50,400,400);
					JButton confirmBt=new JButton("确认");
					JButton cancelBt=new JButton("取消");
					confirmBt.setBounds(50, 600, 100, 50);
					cancelBt.setBounds(300, 600, 100, 50);
					confirmBt.addActionListener(new confirm());
					cancelBt.addActionListener(new cancel());
				    opPanel.add(label);
				    opPanel.add(confirmBt);
				    opPanel.add(cancelBt);
					addOpPanel();
				    break;
				}
				
				
				case 3:{instruction="ACCOUNT_INI:"+iniTf.getText().trim();
			        view.setInstruction(instruction);
			        con.setInstruction();
			        con.go();
			    
			        clearComponents();
				    opPanel.removeAll();
				
			        break;
			    }
				case 4:{
				    setVisible(false);
				    jframe.setContentPane(new mainGUI(jframe));
				}
				}
				
				addOpPanel();
			}
			
		}
		
		class canL implements ActionListener{
			public void actionPerformed(ActionEvent e){
				opPanel.removeAll();
				initialopPanel();
				addOpPanel();
			}
			
		}
	}

}

