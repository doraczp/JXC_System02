import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
public class customerGUI extends JPanel {
	mainFrame jframe;
	JXC_View view=new JXC_View();
	JXC_Controller con=new JXC_Controller(view);
	JButton[] buttons=new JButton[]{new JButton("添加客户"),new JButton("删除客户"),new JButton("更新客户"),new JButton("查找客户"),new JButton("后退")};
	Point[] points=new Point[]{new Point(20,100),new Point(20,175),new Point(20,250),new Point(20,325),new Point(20,475)};
	JPanel opPanel=new JPanel();
	JLabel customerLb=new JLabel("客户姓名");
    JLabel numberLb=new JLabel("联系方式");
    JTextField cusTf=new JTextField(25);
    JTextField numTf=new JTextField(25);
    
    JTable table;    
    
    JButton yesBt=new JButton("确认");
    JButton canBt=new JButton("取消");
    
    int currentPage;
   
   
	public customerGUI(mainFrame jframe){
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
		view.setInstruction("CUSTOMER_SHO:");
		con.setInstruction();
		con.go();
		
		String[] colomn={"客户姓名","联系方式","应收账款金额","应付账款金额","合计"};

		DefaultTableModel tablem=new DefaultTableModel(con.getMessageTable(),colomn){
			public boolean isCellEditable(int row,int colomn) {
	    		 if(colomn==0)
	    			 return true;
	    		 else
	    			 return false;
	    	}
	    	 
	    	public Class<?> getColumnClass(int columnIndex) 
	        { 
	            if(columnIndex==0)
	            { return Boolean.class; 
	            } 
	            return Object.class; 
	        } 
	    };
	    JTable table=new JTable();
	    table.setModel(tablem);
	    this.table=table;
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
			buttons[i].setEnabled(true);
			this.add(buttons[i]);
		}
		
		 jframe.setContentPane(this);

	}
	
	public void initialComponents(){
		 customerLb.setBounds(20, 100, 100, 50);
		 numberLb.setBounds(20, 175, 100, 50);
		 
		 cusTf.setBounds(150, 100,300,20);
		 numTf.setBounds(150, 175,300,20);
		
		 
		 yesBt.setBounds(20, 475, 100, 50);
		 canBt.setBounds(150,475,100,50);
		 yesBt.setBorderPainted(false);
		 canBt.setBorderPainted(false);
		 
	}
	
	public void clearComponents(){
		cusTf.setText("");
		numTf.setText("");
		
	}
	
	class buttonMouseAdapterAndActionListener extends MouseAdapter implements ActionListener{
		int i;
		customerGUI customer;
		private buttonMouseAdapterAndActionListener(int i, customerGUI customer){
			this.i=i;
			this.customer=customer;
			
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
			//添加客户
			customer.buttons[1].setEnabled(false);
			jframe.setContentPane(customer);
			opPanel.removeAll();
			clearComponents();
			
					
			opPanel.add(customerLb);
		    opPanel.add(numberLb);
		    opPanel.add(cusTf);
		    opPanel.add(numTf);
		   
		
		    opPanel.add(yesBt);
		    opPanel.add(canBt);

		    addOpPanel();
		    
		    return;
		}
		if(i==1){
			//删除客户
			ArrayList<String> instructions=new ArrayList<String>();
			instructions.clear();
			
			for(int j=0;j<table.getRowCount();j++){
				if(Boolean.parseBoolean(table.getValueAt(j, 0).toString())){
					instructions.add("CUSTOMER_DEL:"+table.getValueAt(j, 1).toString());
				}
			}
			
			System.out.println(instructions.size());
			for(int j=0;j<instructions.size();j++){
				view.setInstruction(instructions.get(j));
				con.setInstruction();
				con.go();
			}
			
			instructions.clear();
			initialopPanel();
			
		
			return;
		}
		if(i==2){
			//更新客户
			customer.buttons[1].setEnabled(false);
			jframe.setContentPane(customer);
			
			opPanel.removeAll();
			clearComponents();
		
		    
			opPanel.add(customerLb);
		    opPanel.add(numberLb);
		    opPanel.add(cusTf);
		    opPanel.add(numTf);
		    
		    for(int j=0;j<table.getRowCount();j++){
				if(Boolean.parseBoolean(table.getValueAt(j, 0).toString())){
					cusTf.setText(table.getValueAt(j, 1).toString());
					numTf.setText(table.getValueAt(j, 2).toString());
				}
			}
			
		    opPanel.add(yesBt);
		    opPanel.add(canBt);

		    addOpPanel();
		    
		    return;
		}
		if(i==3){
			//查找客户

			customer.buttons[1].setEnabled(false);
			jframe.setContentPane(customer);
			opPanel.removeAll();
			clearComponents();
			
			
			opPanel.add(customerLb);
			opPanel.add(cusTf);
			
			
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
				switch (currentPage){
				
				case 0:{instruction="CUSTOMER_ADD:"+cusTf.getText().trim()+"；"+numTf.getText().trim();
				    break;
				}
				case 2:{instruction="CUSTOMER_UPD:"+cusTf.getText().trim()+"；"+numTf.getText().trim();
				    break;
				}
				case 3:{instruction="CUSTOMER_FIN:"+cusTf.getText().trim();
			        break;
			    }
				
				}
				
				view.setInstruction(instruction);
				con.setInstruction();
				con.go();

				clearComponents();
				opPanel.removeAll();
				
				switch (currentPage){
				case 3:{
					JLabel label=new JLabel(con.getMessageText());
					label.setBounds(50, 200,300,300);
					opPanel.add(label);
					break;
				}
				default:initialopPanel();
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
