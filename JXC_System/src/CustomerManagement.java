import java.util.ArrayList;


public class CustomerManagement {
	String instruction;
	String messageText=null;
	Object[][] messageTable;
	ArrayList<String>info=new ArrayList<String>();
	ArrayList<customer>cus=new  ArrayList<customer>();
	Helper helper=new Helper();
	public void setInstruction(String instruction){
		this.instruction=instruction;
		helper.setFilename("data/customer.txt");
		info=helper.readfile();
		int n=info.size();
		cus.clear();
		if(!info.get(0).equals("")){
		for(int i=0;i<n;i++){
			customer tempcus=new customer();
			helper.split(info.get(i));
			tempcus.name=helper.sArray[0];
			tempcus.phoneNumber=helper.sArray[1];
			tempcus.needToReceive=Integer.parseInt(helper.sArray[2]);
			tempcus.needToPay=Integer.parseInt(helper.sArray[3]);
			tempcus.total=Integer.parseInt(helper.sArray[4]);
			cus.add(tempcus);
		}
		}else{
			info.clear();
		}
	}
	
	public void go(){
		char keyword=instruction.charAt(0);
		switch (keyword){
		case 'A':{
			String s=instruction.substring(4)+"；0；0；0";
			//System.out.println(s);
			info.add(s);
			System.out.println("成功添加！");
			helper.output(info);
			break;
		}
		case 'D':{
			String s=instruction.substring(4);
			helper.split(s);
			String name1=helper.sArray[0];
			int n=info.size();
			int n1=n;
			//System.out.println(name1);
			for(int i=0;i<n;i++){
				String name2=cus.get(i).name;
				//System.out.println(name2);
				if(name1.equals(name2)){
					info.remove(i);
    				i--;
    				n--;
    				break;
				}
			}
			if(n1==n){
				System.out.println("删除失败！");
			}else{
				System.out.println("已从客户列表中删除！");
			} 
			helper.output(info);
			break;
		}
		case 'U':{
			String s=instruction.substring(4);
			helper.split(s);
			String name1=helper.sArray[0];
			int n=info.size();
			int n1=n;
			
			for(int i=0;i<n;i++){
				helper.split(info.get(i));
				String name2=helper.sArray[0];
				String needToRec=helper.sArray[2];
				String needToPay=helper.sArray[3];
				String total=helper.sArray[4];
				if(name1.equals(name2)){
					info.remove(i);
    				info.add(s+"；"+needToRec+"；"+needToPay+"；"+total);
    				i--;
    				n--;
				}
			}
			if(n1==n){
				System.out.println("原来无此客户！");
			}else{
				System.out.println("已成功更新！");
			} 
			helper.output(info);
			break;
			
		}
		case 'F':{
			String s=instruction.substring(4);
			helper.split(s);
			String name1=helper.sArray[0];
			int n=info.size();
			
			for(int i=0;i<n;i++){
				helper.split(info.get(i));
				String name2=helper.sArray[0];
				if(name1.equals(name2)){
					System.out.println(info.get(i));
					messageText="<html>"+"客户姓名："+name2+"<br>"+"联系方式： "+cus.get(i).phoneNumber+"<br>"+"应收账款金额： "+cus.get(i).needToReceive+"<br>"+"应付账款金额："+cus.get(i).needToPay+"<br>"+"合计："+cus.get(i).total+"</html>";
				}
			}
			break;
		}
		case 'S':{
			int n=info.size();
			messageTable=new Object[n][6];
			for(int i=0;i<n;i++){
				messageTable[i][0]=new Boolean(false);
				messageTable[i][1]=cus.get(i).name;
				messageTable[i][2]=cus.get(i).phoneNumber;
				messageTable[i][3]=cus.get(i).needToReceive+"";
				messageTable[i][4]=cus.get(i).needToPay+"";
				messageTable[i][5]=cus.get(i).total+"";
			}
			

			break;
		}
		}
	}
	
	public String getMessageText(){
		return messageText;
	}
	
	public Object[][] getMessageTable(){
		return messageTable;
	}

}
