import java.util.ArrayList;


public class AccountManagement {
	String instruction;
	String messageText=null;
	String[][] messageTable=new String[100][5];
	int allInStock;
	int allNeedToPay;
	int allNeedToReceive;
	ArrayList<Account>acc=new ArrayList<Account>();
	Helper helper=new Helper();
	ArrayList<String>accinfo=new ArrayList<String>();
	public void setInstruction(String instruction){
		this.instruction=instruction;
		helper.setFilename("data/account.txt");
		accinfo=helper.readfile();
		
		if(accinfo.get(0).equals("")){
			accinfo.clear();
			accinfo.add("10000；0；0");
		}
		
		//System.out.println(accinfo.get(0).equals(""));
		
		helper.split(accinfo.get(0));
		Account tempacc=new Account();
		tempacc.allCash=Integer.parseInt(helper.sArray[0]);
		tempacc.allNeedToReceive=Integer.parseInt(helper.sArray[1]);
		tempacc.allNeedToPay=Integer.parseInt(helper.sArray[2]);
		acc.add(tempacc);
		
	}
	
	public void go(){
		String keyword=instruction.substring(0, 3);
		//System.out.println(keyword);
		
		Account tempacc=new Account();
		acc.add(tempacc);
		String tempaccinfo=(acc.get(0).allCash+"")+"；"+(acc.get(0).allNeedToReceive+"")+"；"+(acc.get(0).allNeedToPay+"");
		accinfo.add(tempaccinfo);
		
		switch(keyword){
		case "IN:":{
			String s=instruction.substring(3);
			String name;
			int cashReceive;
			String date;
			helper.split(s);
			name=helper.sArray[0];
			cashReceive=Integer.parseInt(helper.sArray[1]);
			date=helper.sArray[2];
			
			acc.get(0).allCash=acc.get(0).allCash+cashReceive;
			acc.get(0).allNeedToReceive=acc.get(0).allNeedToReceive-cashReceive;
			
			
			outputAccInfo();
			
			outputAccSheet(date,"IN",name,cashReceive);
			
			System.out.println("收款成功！");
			
			changeCusInfo(name,cashReceive,0);
		
			break;
		}
		case "OUT":{
			String s=instruction.substring(4);
			String name;
			String date;
			int cashPay;
			helper.split(s);
			name=helper.sArray[0];
			cashPay=Integer.parseInt(helper.sArray[1]);
			date=helper.sArray[2];

			
			acc.get(0).allCash=acc.get(0).allCash-cashPay;
			acc.get(0).allNeedToPay=acc.get(0).allNeedToPay-cashPay;
			
			outputAccInfo();
			
			outputAccSheet(date,"OUT",name,cashPay);

			
			System.out.println("付款成功！");
			
			changeCusInfo(name,0,cashPay);
		
			break;
			
		}
		case "ALL":{
			helper.split(accinfo.get(0));
			messageText="<html>"+"账目总额："+helper.sArray[0]+"<br>"+"应收总额："+helper.sArray[1]+"<br>"+"应付总额："+helper.sArray[2]+"</html>";
			System.out.println(accinfo.get(0));
			break;
		}
		case "DET":{
			helper.setFilename("data/accountsheet.txt");
			ArrayList<String>info=helper.readfile();
			for(int i=0;i<info.size();i++){
				helper.split(info.get(i));
				messageTable[i][0]=helper.sArray[0];
				messageTable[i][1]=helper.sArray[1].substring(8);
				messageTable[i][2]=helper.sArray[2];
				if(helper.sArray[1].substring(8).charAt(0)=='I'){
					messageTable[i][3]=helper.sArray[3];
					messageTable[i][4]="0";
				}else{
					messageTable[i][3]="0";
					messageTable[i][4]=helper.sArray[3];
				}
				
				System.out.println(info.get(i));
				
			}
			break;
		}
		case "INI":{
			acc.get(0).allCash=Integer.parseInt(instruction.substring(4));
			acc.get(0).allNeedToPay=0;
			acc.get(0).allNeedToReceive=0;
			
			outputAccInfo();
			
			System.out.println("初始化成功！");
			break;
		}
		}
	}
	
	public void changeCusInfo(String name,int cashReceive,int cashPay){
		Helper helper=new Helper();
		ArrayList<String>info=new ArrayList<String>();
		ArrayList<customer>cus=new  ArrayList<customer>();
		String newinfo="";
		customer tempcus=new customer();
		helper.setFilename("data/customer.txt");
		info=helper.readfile();
		int n=info.size();
		for(int i=0;i<n;i++){
			helper.split(info.get(i));
			tempcus.name=helper.sArray[0];
			tempcus.phoneNumber=helper.sArray[1];
			tempcus.needToReceive=Integer.parseInt(helper.sArray[2]);
			tempcus.needToPay=Integer.parseInt(helper.sArray[3]);
			cus.add(tempcus);


		}
		
		for(int i=0;i<n;i++){
			if(cus.get(i).name.equals(name)){
				cus.get(i).needToPay=cus.get(i).needToPay-cashPay;
				cus.get(i).needToReceive=cus.get(i).needToReceive-cashReceive;
				newinfo=cus.get(i).name+"；"+cus.get(i).phoneNumber+"；"+cus.get(i).needToReceive+""+"；"+cus.get(i).needToPay+"";

			}
		}
		System.out.println(newinfo);
		
		if(newinfo.equals("")){
			System.out.println("原来无此客户！");
		}
		
		helper.split(newinfo);
		String name1=helper.sArray[0];
	
		
		for(int i=0;i<n;i++){
			helper.split(info.get(i));
			String name2=helper.sArray[0];
			//System.out.println(name1+" "+name2);
			if(name1.equals(name2)){
				info.remove(i);
				info.add(newinfo);
				//i--;
				
			}
		}
		
		
		helper.output(info);
	}
	
	public void outputAccInfo(){
		helper.setFilename("data/account.txt");
		String tempaccinfo=(acc.get(0).allCash+"")+"；"+(acc.get(0).allNeedToReceive+"")+"；"+(acc.get(0).allNeedToPay+"");
		accinfo.clear();
		accinfo.add(tempaccinfo);
		helper.output(accinfo);
		
		
	}
	
	public void outputAccSheet(String date,String s,String name,int cash){
		helper.setFilename("data/accountsheet.txt");
		ArrayList<String>info=helper.readfile();
		if(info.get(0).equals("")){
			info.remove(0);
		}
		String newinfo=date+"；"+"ACCOUNT_"+s+"；"+name+"；"+cash+"";
		info.add(newinfo);
		helper.output(info);
	}
	
	public String getMessageText(){
		return messageText;
	}
	
	public String[][] getMessageTable(){
		return messageTable;
	}

}
