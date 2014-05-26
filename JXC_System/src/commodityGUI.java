import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
public class commodityGUI extends JPanel {
	mainFrame jframe;
	JXC_View view=new JXC_View();
	JXC_Controller con=new JXC_Controller(view);
	JButton[] buttons=new JButton[]{new JButton("添加商品"),new JButton("删除商品"),new JButton("更新商品"),new JButton("查找商品"),new JButton("后退")};
	Point[] points=new Point[]{new Point(20,100),new Point(20,175),new Point(20,250),new Point(20,325),new Point(20,475)};
	JPanel opPanel=new JPanel();
	JLabel commodityLb=new JLabel("商品名称");
    JLabel numberLb=new JLabel("商品序号");
    JLabel defaultImLb=new JLabel("默认进价");
    JLabel defaultExLb=new JLabel("默认售价");
    JTextField comTf=new JTextField(25);
    JTextField numTf=new JTextField(25);
    JTextField imTf=new JTextField(25);
    JTextField exTf=new JTextField(25);
    
    JButton yesBt=new JButton("确认");
    JButton canBt=new JButton("取消");
    
    JTable table;    
    int currentPage;
   
   
	public commodityGUI(mainFrame jframe){
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
		view.setInstruction("COMMODITY_SHO:");
		con.setInstruction();
		con.go();
		
		String[] colomn={"选择","商品名称","商品型号","数量","默认进价","默认售价","上一次进价","上一次售价"};
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
		 commodityLb.setBounds(20, 100, 100, 50);
		 numberLb.setBounds(20, 175, 100, 50);
		 defaultImLb.setBounds(20, 250, 100, 50);
		 defaultExLb.setBounds(20, 325, 100, 50);
		    
		 comTf.setBounds(150, 100,300,20);
		 numTf.setBounds(150, 175,300,20);
		 imTf.setBounds(150, 250,300,20);
		 exTf.setBounds(150, 325,300,20);
		 
		 yesBt.setBounds(20, 475, 100, 50);
		 canBt.setBounds(150,475,100,50);
		 yesBt.setBorderPainted(false);
		 canBt.setBorderPainted(false);
		 
	}
	
	public void clearComponents(){
		comTf.setText("");
		numTf.setText("");
		imTf.setText("");
		exTf.setText("");
	}
	
	class buttonMouseAdapterAndActionListener extends MouseAdapter implements ActionListener{
		int i;
		commodityGUI commodity;
		private buttonMouseAdapterAndActionListener(int i, commodityGUI commodity){
			this.i=i;
			this.commodity=commodity;
			
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
			//添加商品
			commodity.buttons[1].setEnabled(false);
			jframe.setContentPane(commodity);
			opPanel.removeAll();
			clearComponents();
			
					
			opPanel.add(commodityLb);
		    opPanel.add(numberLb);
		    opPanel.add(defaultImLb);
		    opPanel.add(defaultExLb);
		    opPanel.add(comTf);
		    opPanel.add(numTf);
		    opPanel.add(imTf);
		    opPanel.add(exTf);
		    
		
		    opPanel.add(yesBt);
		    opPanel.add(canBt);

		    addOpPanel();
		    
		    return;
		}
		if(i==1){
			//删除商品
			ArrayList<String> instructions=new ArrayList<String>();
			instructions.clear();
			
			for(int j=0;j<table.getRowCount();j++){
				if(Boolean.parseBoolean(table.getValueAt(j, 0).toString())){
					instructions.add("COMMODITY_DEL:"+table.getValueAt(j, 1).toString()+"；"+table.getValueAt(j, 2).toString());
				}
			}
			
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
			//修改商品信息
			commodity.buttons[1].setEnabled(false);
			jframe.setContentPane(commodity);
			opPanel.removeAll();
			clearComponents();
		
		    
			opPanel.add(commodityLb);
		    opPanel.add(numberLb);
		    opPanel.add(defaultImLb);
		    opPanel.add(defaultExLb);
		    opPanel.add(comTf);
		    opPanel.add(numTf);
		    opPanel.add(imTf);
		    opPanel.add(exTf);
		    
		    for(int j=0;j<table.getRowCount();j++){
				if(Boolean.parseBoolean(table.getValueAt(j, 0).toString())){
					comTf.setText(table.getValueAt(j, 1).toString());
					numTf.setText(table.getValueAt(j, 2).toString());
					imTf.setText(table.getValueAt(j, 4).toString());
					exTf.setText(table.getValueAt(j, 5).toString());
				}
			}
			
		    opPanel.add(yesBt);
		    opPanel.add(canBt);

		    addOpPanel();
		    
		    return;
		}
		if(i==3){
			commodity.buttons[1].setEnabled(false);
			jframe.setContentPane(commodity);
			opPanel.removeAll();
			clearComponents();
			
			
			opPanel.add(commodityLb);
			opPanel.add(numberLb);
			opPanel.add(comTf);
			opPanel.add(numTf);
			
			
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
				
				case 0:{instruction="COMMODITY_ADD:"+comTf.getText().trim()+"；"+numTf.getText().trim()+"；"+imTf.getText().trim()+"；"+exTf.getText().trim();
				    break;
				}
				case 2:{instruction="COMMODITY_UPD:"+comTf.getText().trim()+"；"+numTf.getText().trim()+"；"+imTf.getText().trim()+"；"+exTf.getText().trim();
				    break;
				}
				case 3:{instruction="COMMODITY_FIN:"+comTf.getText().trim()+"；"+numTf.getText().trim();
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
					System.out.println("Message text: "+con.getMessageText());
					label.setBounds(50, 200,300,300);
					opPanel.add(label);
					break;
				}
				
				default:initialopPanel();
				}
				
				opPanel.repaint();
				addOpPanel();
				
	           
			}
			
		}
		
		class canL implements ActionListener{
			public void actionPerformed(ActionEvent e){
				opPanel.removeAll();
				initialopPanel();
				initialButton();
				addOpPanel();
			}
			
		}
	}
	
}
