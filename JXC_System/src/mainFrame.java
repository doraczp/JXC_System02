import javax.swing.*;


import java.awt.*;
public class mainFrame extends JFrame{
	public final int FRAME_WIDTH=1024;
	public final int FRAME_HEIGHT=768;
	int locationX=0;
	int locationY=0;
	public mainFrame(){
		this.setTitle("进销存系统");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Dimension screen=toolkit.getScreenSize();
		this.locationX=(screen.width-this.getWidth())/2;
		this.locationY=(screen.height-this.getHeight())/2;
		this.setLocation(this.locationX,this.locationY);
		this.setUndecorated(true);
		this.setVisible(true);
	}
	private Point loc = null;
	private Point tmp = null;
	private boolean isDragged = false;
	private mainFrame jFrame=this;

	 public void setDragable(JPanel panel) {
	    	panel.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseReleased(java.awt.event.MouseEvent e) {
	               isDragged = false;
	               //TODO 更换鼠标图案
	            //   jFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	            }
	            public void mousePressed(java.awt.event.MouseEvent e) {
	               tmp = new Point(e.getX(), e.getY());
	               isDragged = true;
	             //TODO 更换鼠标图案
	             //  jFrame.setCursor(new Cursor(Cursor.MOVE_CURSOR));
	            }
	        });
	    	panel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
	            public void mouseDragged(java.awt.event.MouseEvent e) {
	               if(isDragged) {
	                   loc = new Point(jFrame.getLocation().x + e.getX() - tmp.x,
	                     jFrame.getLocation().y + e.getY() - tmp.y);
	                   jFrame.setLocation(loc);
	               }
	            }
	        });
	 }
}
