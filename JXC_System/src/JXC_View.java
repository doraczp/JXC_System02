import java.awt.Image;


public class JXC_View {
	//accountGUI account=new accountGUI();
	//commodityGUI commodity=new commodityGUI();
	//customerGUI customer=new customerGUI();
	//importGUI imports=new importGUI();
	//exportGUI export=new exportGUI();
	//stockGUI stock=new stockGUI();
	String instruction=null;
	String messageText=null;

	
	
	public void initialApp(){
		mainFrame main=new mainFrame();
		//mainGUI maingui=new mainGUI(main);
		LoginGUI login=new LoginGUI(main);
		//main.setContentPane(maingui);
		main.setContentPane(login);
	}
	public void refreshDisplay(){
		
	
		
	}
	
    public void setInstruction(String s){
		instruction=s;
	}
	public String getInstruction(){
		
		return instruction;
	}
	
	public void setMessagePicture(Image im){
		
	}
	
	public void getMessagePicture(){
		
	}
	public void setMessageText(String s){
		messageText=s;
	}
	public String getMessageText(){
		return messageText;
	}

}
