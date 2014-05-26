import java.util.ArrayList;


public class ImportManagement {
	String instruction;
	String[][] messageTable=new String[100][9];
	Helper helper=new Helper();
	ArrayList<String>iminfo=new ArrayList<String>();
	public void setInstruction(String instruction){
		this.instruction=instruction;
		
	}
	
	public void go(){
		char keyword=instruction.charAt(0);
		helper.setFilename("data/importsheet.txt");
		iminfo=helper.readfile();
		if(iminfo.get(0).equals("")){
			iminfo.clear();
		}
		switch(keyword){
		case 'A':{
			helper.split(instruction.substring(4));
			String cusName=helper.sArray[0];
			String commodityName=helper.sArray[1];
			int quantity=Integer.parseInt(helper.sArray[3]);
			int imprice=Integer.parseInt(helper.sArray[4]);
			int cashPay=quantity*imprice;
			int n=iminfo.size();
			
			iminfo.add(instruction+"；"+cashPay+"；"+n+"");
			helper.output(iminfo);
			System.out.println("添加成功！");
		
			changeCommodity(commodityName,imprice,0,quantity);
			changeAccount(cashPay,0);
			changeCustomer(cusName,cashPay,0);
			changeHistory(commodityName);
			break;
		}
		case 'D':{
			helper.split(instruction.substring(4));
		
			String sheetNumber=helper.sArray[0];
			int quantity=Integer.parseInt(helper.sArray[1]);
			String date=helper.sArray[2];
			
			for(int i=0;i<iminfo.size();i++){
			    helper.split(iminfo.get(i));
			    String tempSheetNumber=helper.sArray[7];
			    if(sheetNumber.trim().equals(tempSheetNumber.trim())){
			        String cusName=helper.sArray[0];
			        String commodityName=helper.sArray[1];
			        String number=helper.sArray[2];
			        int tempquantity=Integer.parseInt(helper.sArray[3]);
			        int imprice=Integer.parseInt(helper.sArray[4]);
			        //String tempdate=helper.sArray[5];
			        if(quantity<tempquantity){
			            setInstruction("DEL:"+sheetNumber+"；"+tempquantity+""+"；"+date);
			            go();
			            setInstruction("ADD:"+cusName.substring(4)+"；"+commodityName+"；"+number+"；"+(tempquantity-quantity)+""+"；"+imprice+""+"；"+date);
			            go();
			        }else{
			            int cashPay=tempquantity*imprice;
			            iminfo.add("DEL:"+cusName.substring(4)+"；"+commodityName+"；"+number+"；"+tempquantity+"；"+imprice+""+"；"+date+"；"+cashPay+""+"；"+sheetNumber);
			            helper.output(iminfo);
			            System.out.println("添加成功！");
			
			
			            changeCommodity(commodityName,imprice,0,-quantity);
			            changeAccount(-cashPay,0);
			            changeCustomer(cusName.substring(4),-cashPay,0);
			            break;
			        }

			    }
			}

		    break;

			
	
		}
		case 'S':{
			int n=iminfo.size();
			helper.split(instruction.substring(4));
			String date1=helper.sArray[0];
			String date2=helper.sArray[1];
			
			do{
			for(int i=0;i<n;i++){
				helper.split(iminfo.get(i));
				String imdate=helper.sArray[5];
				
				if(imdate.equals(date1)){
					messageTable[i][0]=helper.sArray[5];
				    messageTable[i][1]=helper.sArray[0].substring(0,3);
				    messageTable[i][2]=helper.sArray[0].substring(4);
				    messageTable[i][3]=helper.sArray[1];
				    messageTable[i][4]=helper.sArray[2];
				    messageTable[i][5]=helper.sArray[3];
				    messageTable[i][6]=helper.sArray[4];
				    messageTable[i][7]=helper.sArray[6];
				    messageTable[i][8]=helper.sArray[7];
				   
					System.out.println(imdate+":"+iminfo.get(i));
				}
				
			}
			helper.dataplusone(date1);

			}while(date1.equals(date2)==true);
			break;

		}
		case 'W':{
			for(int i=0;i<iminfo.size();i++){
				helper.split(iminfo.get(i));
				messageTable[i][0]=helper.sArray[5];
			    messageTable[i][1]=helper.sArray[0].substring(0,3);
			    messageTable[i][2]=helper.sArray[0].substring(4);
			    messageTable[i][3]=helper.sArray[1];
			    messageTable[i][4]=helper.sArray[2];
			    messageTable[i][5]=helper.sArray[3];
			    messageTable[i][6]=helper.sArray[4];
			    messageTable[i][7]=helper.sArray[6];
			    messageTable[i][8]=helper.sArray[7];
			}
			break;
		}
		default:System.out.println("格式错误！");
		}
	}
	
	public void changeCommodity(String commodityName,int imprice,int exprice,int quantity){
		Helper helper=new Helper();
		commodity tempcom=new commodity();
		ArrayList<String>info=new ArrayList<String>();
		ArrayList<commodity>com=new  ArrayList<commodity>();
		String newinfo="";
		helper.setFilename("data/commodity.txt");
		info=helper.readfile();
		int n=info.size();
		for(int i=0;i<n;i++){
			helper.split(info.get(i));
			tempcom.name=helper.sArray[0];
			tempcom.number=helper.sArray[1];
			tempcom.quantity=Integer.parseInt(helper.sArray[2]);
			tempcom.defaultImportPrice=Integer.parseInt(helper.sArray[3]);
			tempcom.defaultExportPrice=Integer.parseInt(helper.sArray[4]);
			tempcom.latestImportPrice=Integer.parseInt(helper.sArray[5]);
			tempcom.latestExportPrice=Integer.parseInt(helper.sArray[6]);
			com.add(tempcom);


		}
		
		for(int i=0;i<n;i++){
			if(com.get(i).name.equals(commodityName)){
				com.get(i).quantity=com.get(i).quantity+quantity;
				com.get(i).latestImportPrice=imprice;
			}
			newinfo=com.get(i).name+"；"+com.get(i).number+"；"+com.get(i).quantity+""+"；"+com.get(i).defaultImportPrice+""+"；"+com.get(i).defaultExportPrice+""+"；"+com.get(i).latestImportPrice+""+"；"+com.get(i).latestExportPrice+"";
		}
		
		helper.split(newinfo);
		int n1=n;
		
		for(int i=0;i<n;i++){
			helper.split(info.get(i));
			String name2=helper.sArray[0];
			//System.out.println(name1+" "+name2);
			if(commodityName.equals(name2)){
				info.remove(i);
				info.add(newinfo);
				//i--;
				n1--;
			}
		}
		if(n1==n){
			System.out.println("原来无此商品！");
		}
		
		helper.output(info);
		
	
	}
	
	public void changeAccount(int cashPay,int cashRec){
		Helper helper=new Helper();
		ArrayList<String>accinfo=new ArrayList<String>();
		ArrayList<Account>acc=new ArrayList<Account>();
		String newaccinfo="";
		Account tempacc=new Account();
		helper.setFilename("data/account.txt");
		accinfo=helper.readfile();
		helper.split(accinfo.get(0));
		tempacc.allCash=Integer.parseInt(helper.sArray[0]);
		tempacc.allNeedToReceive=Integer.parseInt(helper.sArray[1])+cashPay;
		tempacc.allNeedToPay=Integer.parseInt(helper.sArray[2])+cashRec;
		acc.add(tempacc);
		newaccinfo=tempacc.allCash+""+"；"+tempacc.allNeedToReceive+""+"；"+tempacc.allNeedToPay+"";
		
		accinfo.clear();
		accinfo.add(newaccinfo);
		helper.output(accinfo);

		
	}
	
	public void changeCustomer(String cusName,int cashRec,int cashPay){
		Helper helper=new Helper();
		ArrayList<String>info=new ArrayList<String>();
		ArrayList<customer>cus=new ArrayList<customer>();
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
			tempcus.total=Integer.parseInt(helper.sArray[4]);
			cus.add(tempcus);


		}
		for(int i=0;i<n;i++){
			//System.out.println(cus.get(i).name.equals(cusName));

			if(cus.get(i).name.equals(cusName)){
				cus.get(i).needToPay=cus.get(i).needToPay+cashPay;
				cus.get(i).needToReceive=cus.get(i).needToReceive+cashRec;
				newinfo=cus.get(i).name+"；"+cus.get(i).phoneNumber+"；"+cus.get(i).needToReceive+""+"；"+cus.get(i).needToPay+""+"；"+(cus.get(i).needToReceive-cus.get(i).needToPay)+"";
			}
					
		}
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
	public void changeHistory(String commodityName){
		helper.setFilename("data/history.txt");
		ArrayList<String>hisinfo=helper.readfile();
        if(hisinfo.get(0).equals(""))
        	hisinfo.clear();
		if(hisinfo.indexOf(commodityName)<0)
			hisinfo.add(commodityName);
		helper.output(hisinfo);
	}
	
	public String[][] getMessageTable(){
		return messageTable;
	}

}
