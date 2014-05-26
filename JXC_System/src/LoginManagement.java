import java.util.ArrayList;


public class LoginManagement {
	String instruction;
	boolean isLoginSuccessfully=false;
	String position="";
	String name;
	Helper helper=new Helper();
	ArrayList<String>loginfo=new ArrayList<String>();
	public void setInstruction(String instruction){
		this.instruction=instruction;
		helper.setFilename("data/loginData.txt");
		loginfo=helper.readfile();
	}
	public void go(){
		String keyword=instruction.substring(0, 3);
		
		switch (keyword){
		case "LOG":{
			helper.split(instruction.substring(4));
			String username=helper.sArray[0];
			String password=helper.sArray[1];
			for(int i=0;i<loginfo.size();i++){
				helper.split(loginfo.get(i));
				if(helper.sArray[0].equals(username)&&helper.sArray[1].equals(password)){
					isLoginSuccessfully=true;
					position=helper.sArray[2];
					name=helper.sArray[0];
				}
			}
			System.out.println(name+"  "+position);

			break;
		}
		case "RES":{
			loginfo.add(instruction.substring(4));
			helper.output(loginfo);
			break;
		}
		default:System.out.println("Ö¸Áî´íÎó£¡");
		}

	}
	
	public String getPosition(){
		return position;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean isSuccessful() {
		// TODO Auto-generated method stub
		return isLoginSuccessfully;
	}
	

}
