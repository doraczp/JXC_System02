
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class exportGUI extends JPanel {
	mainFrame jframe;
	JXC_View view=new JXC_View();
	JXC_Controller con=new JXC_Controller(view);
	JButton[] buttons=new JButton[]{new JButton("创建销售单"),new JButton("创建销售退货单"),new JButton("查看单据"),new JButton("后退")};
	Point[] points=new Point[]{new Point(20,100),new Point(20,175),new Point(20,250),new Point(20,325)};
	JPanel opPanel=new JPanel();
	JLabel customerLb=new JLabel("客户姓名");
    JLabel commodityLb=new JLabel("商品名称");
    JLabel numberLb=new JLabel("商品型号");
    JLabel quantityLb=new JLabel("数量");
    JLabel priceLb=new JLabel("单价");
    JLabel yearLb=new JLabel("年");
    JLabel monthLb=new JLabel("月");
    JLabel dayLb=new JLabel("日");
    JLabel sheetLb=new JLabel("销售单号");
    JLabel startLb=new JLabel("起始日期");
    JLabel endLb=new JLabel("结束日期");

    JTextField cusTf=new JTextField(25);
    JTextField comTf=new JTextField(25);
    JTextField numTf=new JTextField(25);
    JTextField quaTf=new JTextField(25);
    JTextField priTf=new JTextField(25);
    JTextField yearTf=new JTextField(5);
    JTextField monthTf=new JTextField(3);
    JTextField dayTf=new JTextField(3);
    JTextField sheTf=new JTextField(25);
    JTextField staTf=new JTextField(25);
    JTextField endTf=new JTextField(25);

    
    
    JButton yesBt=new JButton("确认");
    JButton canBt=new JButton("取消");
    
    int currentPage;
   
   
	public exportGUI(mainFrame jframe){
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
		view.setInstruction("EXPORT_WHOLE:");
		con.setInstruction();
		con.go();
		
		String[] colomn={"时间","单据性质","客户姓名","商品名称","商品型号","数量","单价","总价","对应销售单号"};

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
		for(int i=0;i<4;i++){
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
		 commodityLb.setBounds(20, 175, 100, 50);
		 numberLb.setBounds(20, 250, 100, 50);
		 quantityLb.setBounds(20, 325, 100, 50);
		 priceLb.setBounds(20, 400, 100, 50);
		 //timeLb.setBounds(20, 475, 100, 50);
		 yearLb.setBounds(20, 475, 100, 50);
		 monthLb.setBounds(180, 475, 50, 50);
		 dayLb.setBounds(300,475,50,50);
		 sheetLb.setBounds(20,100,100,50);
		 startLb.setBounds(20,100,100,50);
		 endLb.setBounds(20,175,100,50);
		 
		 cusTf.setBounds(150, 100,300,20);
		 comTf.setBounds(150, 175,300,20);
		 numTf.setBounds(150, 250, 300, 20);
		 quaTf.setBounds(150, 325, 300, 20);
		 priTf.setBounds(150,400,300,20);
		 //tmTf.setBounds(150, 475, 300, 20);
		 yearTf.setBounds(120,475,50,20);
		 monthTf.setBounds(230,475,40,20);
		 dayTf.setBounds(330,475,40,20);
		 sheTf.setBounds(150, 100, 300,20);
		 staTf.setBounds(150, 100, 300,20);
		 endTf.setBounds(150, 175, 300,20);


		
		 
		 yesBt.setBounds(20, 575, 100, 50);
		 canBt.setBounds(150,575,100,50);
		 yesBt.setBorderPainted(false);
		 canBt.setBorderPainted(false);
		 
	}
	
	public void clearComponents(){
		cusTf.setText("");
		comTf.setText("");
		numTf.setText("");
		quaTf.setText("");
		priTf.setText("");
		yearTf.setText("");
		monthTf.setText("");
		dayTf.setText("");
		
	}
	
	class buttonMouseAdapterAndActionListener extends MouseAdapter implements ActionListener{
		int i;
		exportGUI exports;
		private buttonMouseAdapterAndActionListener(int i, exportGUI exports){
			this.i=i;
			this.exports=exports;
			
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
			//添加销售单
			opPanel.removeAll();
			clearComponents();
			
					
			opPanel.add(customerLb);
		    opPanel.add(commodityLb);
		    opPanel.add(numberLb);
		    opPanel.add(quantityLb);
		    opPanel.add(priceLb);
		    opPanel.add(yearLb);
		    opPanel.add(monthLb);
		    opPanel.add(dayLb);
		    
		    opPanel.add(cusTf);
		    opPanel.add(numTf);
		    opPanel.add(comTf);
		    opPanel.add(quaTf);
		    opPanel.add(priTf);
		    opPanel.add(yearTf);
		    opPanel.add(monthTf);
		    opPanel.add(dayTf);
		   
		
		    opPanel.add(yesBt);
		    opPanel.add(canBt);

		    addOpPanel();
		    
		    return;
		}
		if(i==1){
			//添加销售退货单
			opPanel.removeAll();
			clearComponents();
			
			
			opPanel.add(sheetLb);
			opPanel.add(quantityLb);
			opPanel.add(yearLb);
		    opPanel.add(monthLb);
		    opPanel.add(dayLb);
			opPanel.add(sheTf);
			opPanel.add(quaTf);
			opPanel.add(yearTf);
			opPanel.add(monthTf);
			opPanel.add(dayTf);
			
			opPanel.add(yesBt);
			opPanel.add(canBt);
		    addOpPanel();
		
			return;
		}
		
		if(i==2){
			//显示单据
			opPanel.removeAll();
			clearComponents();
			
			
			opPanel.add(startLb);
			opPanel.add(endLb);
			opPanel.add(staTf);
			opPanel.add(endTf);
			
			
			opPanel.add(yesBt);
			opPanel.add(canBt);
		    addOpPanel();
		
			return;
		}
		
		if(i==3){
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
				
				case 0:{instruction="EXPORT_ADD:"+cusTf.getText().trim()+"；"+comTf.getText().trim()+"；"+numTf.getText().trim()+"；"+quaTf.getText().trim()+"；"+priTf.getText().trim()+"；"+yearTf.getText().trim()+"/"+monthTf.getText().trim()+"/"+dayTf.getText().trim();
				    opPanel.removeAll();
				    
				    JLabel label=new JLabel("<html>"+"                    确认如下销售？"+"<br>"+"-----------------------------------------------------------"+"<br><br>"+"客户姓名："+cusTf.getText().trim()+"<br><br>"+"商品名称："+comTf.getText().trim()+"<br><br>"+"商品型号："+numTf.getText().trim()+"<br><br>"+"数量："+quaTf.getText().trim()+"<br><br>"+"单价："+priTf.getText().trim()+"<br><br>"+"时间"+yearTf.getText().trim()+"/"+monthTf.getText().trim()+"/"+dayTf.getText().trim()+"<br><br>"+"---------------------------------------------------------"+"</html>");
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
				case 1:{instruction="EXPORT_DEL:"+sheTf.getText().trim()+"；"+quaTf.getText().trim()+"；"+yearTf.getText().trim()+"/"+monthTf.getText().trim()+"/"+dayTf.getText().trim();
				    clearComponents();
				    opPanel.removeAll();
				    
				    JLabel label=new JLabel("<html>"+"确认如下销售退货单？"+"<br>"+"-------------------------"+"<br>"+"进货单号："+sheTf.getText().trim()+"<br>"+"退货数量："+quaTf.getText().trim()+"<br>"+"日期："+yearTf.getText().trim()+"/"+monthTf.getText().trim()+"/"+dayTf.getText().trim()+"<br>"+"---------------------------"+"</html>");
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
				case 2:{instruction="EXPORT_SHO:"+staTf.getText().trim()+"；"+endTf.getText().trim();
				    view.setInstruction(instruction);
				    con.setInstruction();
				    con.go();
				    
				    clearComponents();
					opPanel.removeAll();
					
				    break;
				}
				}
				
				

				
				switch (currentPage){
				case 2:{
					String[] colomn={"时间","单据性质","客户姓名","商品名称","商品型号","数量","单价","总价","对应销售单号"};


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
					break;
				}
				default:;
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
