import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class stockGUI extends JPanel{
	mainFrame jframe;
	JXC_View view=new JXC_View();
	JXC_Controller con=new JXC_Controller(view);
	JButton button=new JButton("后退");
	Point point=new Point(462,600);
	
	public stockGUI(mainFrame jframe){
		this.jframe=jframe;
		this.setLayout(null);
		this.setVisible(true);
		this.setBounds(0, 0, jframe.FRAME_WIDTH, jframe.FRAME_HEIGHT);
		jframe.setDragable(this);
		initialButton();
		
		view.setInstruction("STOCK_SHO");
		con.setInstruction();
		con.go();
		
		addTable();
		System.out.println("here2");


	}
	
	public void initialButton(){
		button.setBorderPainted(false);
		button.setBounds(point.x, point.y, 100, 50);
		buttonMouseAdapterAndActionListener buttonMouseAdapterAndActionListener=new buttonMouseAdapterAndActionListener(this);
		if(button.getMouseListeners().length<2){
			button.addMouseListener(buttonMouseAdapterAndActionListener);
			}
		if(button.getActionListeners().length<1){
			button.addActionListener(buttonMouseAdapterAndActionListener);
			}
		this.add(button);
		jframe.setContentPane(this);


	}
	
	public void addTable(){
	     String[] colomn={"商品名称","商品型号","进货数量","进货平均单价","进货总价","销售数量","销售单价","销售总价","库存数量","库存平均单价","库存总价"};

	     DefaultTableModel tablem=new DefaultTableModel(con.getMessageTable(),colomn){
	    	 public boolean isCellEditable(int row,int colomn) {
	    	     return false;
	    	    }
	     };
	     JTable table=new JTable();
	     table.setModel(tablem);
	
         JScrollPane scrollPane=new JScrollPane(table);
         scrollPane.setBounds(100, 200, 850, 300);
	     this.add(scrollPane);
	     jframe.setContentPane(this);
	}
	class buttonMouseAdapterAndActionListener extends MouseAdapter implements ActionListener{

		stockGUI stock;
		private buttonMouseAdapterAndActionListener(stockGUI stock){
			this.stock=stock;
		}
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			jframe.setContentPane(new mainGUI(jframe));
			// TODO Auto-generated method stub
			
		}

		
		
	}

}
