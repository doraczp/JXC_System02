import java.util.ArrayList;


public class CommodityManagement {
	String instruction;
	String messageText=null;
	Object[][] messageTable;
	ArrayList<String>info=new ArrayList<String>();
	ArrayList<commodity>com=new  ArrayList<commodity>();
	ArrayList<String>history=new ArrayList<String>();
	Helper helper=new Helper();
	public void setInstruction(String instruction){
		this.instruction=instruction;
		//获取历史记录
		helper.setFilename("data/history.txt");
		history=helper.readfile();
		
		helper.setFilename("data/commodity.txt");
		info=helper.readfile();
		helper.output(info);
		
		int n=info.size();
		
		com.clear();
		
		if(!info.get(0).equals("")){
		    for(int i=0;i<n;i++){
		    	if(!info.get(i).equals("")){
		    		commodity tempcom=new commodity();
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
		}
		}else{
			info.clear();
		}
	}
	

	
	public void go(){
		char keyword=instruction.charAt(0);
		switch(keyword){
		case 'A':{
			String s=instruction.substring(4);
			String newinfo;
			boolean isAlreadyThere=false;
			helper.split(s);
			for(int i=0;i<com.size();i++){
				if(com.get(i).name.equals(helper.sArray[0]))
					isAlreadyThere=true;
			}
			if(!isAlreadyThere){
				newinfo=helper.sArray[0]+"；"+helper.sArray[1]+"；"+"0"+"；"+helper.sArray[2]+"；"+helper.sArray[3]+"；0；0";
				info.add(newinfo);
				System.out.println("成功添加！");
			}else{
				System.out.println("商品已存在");
			}
			
			helper.output(info);
			break;
		}
		case 'D':{
			String s=instruction.substring(4);
			helper.split(s);
			String name1=helper.sArray[0];
			String number1=helper.sArray[1];
			int n=info.size();
			int n1=n;
			for(int i=0;i<n;i++){
				String name2=com.get(i).name;
				String number2=com.get(i).number;
				
				if(name1.equals(name2)&&number1.equals(number2)&&(history.indexOf(name1)<0)){
					info.remove(i);
    				i--;
    				n--;
    				break;
				}
			}
			if(n1==n){
				System.out.println("删除失败！");
			}else{
				System.out.println("已从仓库删除！");
			} 
			helper.output(info);
			break;
		}
		case 'U':{
			String s=instruction.substring(4);
			helper.split(s);
			String name1=helper.sArray[0];
			String number1=helper.sArray[1];
			String defaultImportPrice=helper.sArray[2];
			String defaultExportPrice=helper.sArray[3];
			int n=info.size();
			int n1=n;
			
			for(int i=0;i<n;i++){
				helper.split(info.get(i));
				String name2=helper.sArray[0];
				String number2=helper.sArray[1];
				String quantity=helper.sArray[2];
				String latestImportPrice=helper.sArray[5];
				String latestExportPrice=helper.sArray[6];
				if(name1.equals(name2)&&number1.equals(number2)){
					String newinfo=name2+"；"+number2+"；"+quantity+"；"+defaultImportPrice+"；"+defaultExportPrice+"；"+latestImportPrice+"；"+latestExportPrice;

					info.remove(i);
    				info.add(newinfo);
    				i--;
    				n--;
				}
			}
			if(n1==n){
				System.out.println("原来无此商品！");
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
			String number1=helper.sArray[1];
			int n=info.size();
			
			for(int i=0;i<n;i++){
				helper.split(info.get(i));
				//String name2=helper.sArray[0];
				//String number2=helper.sArray[1];
				String name2=com.get(i).name;
				String number2=com.get(i).number;
				
				if(name1.equals(name2)&&number1.equals(number2)){
					System.out.println(info.get(i));
					messageText="<html>"+"商品名称："+name2+"<br>"+"商品型号： "+number2+"<br>"+"数量： "+com.get(i).quantity+"<br>"+"默认进价："+com.get(i).defaultImportPrice+"<br>"+"默认售价："+com.get(i).defaultExportPrice+"<br>"+"上一次进价："+com.get(i).latestImportPrice+"<br>"+"上一次售价："+com.get(i).latestExportPrice+"</html>";
				}
			}
			break;
		}
		case 'S':{
			int n=info.size();
			messageTable=new Object[n][8];
			for(int i=0;i<n;i++){
				System.out.println(com.get(i).name);
				messageTable[i][0]=new Boolean(false);
				messageTable[i][1]=com.get(i).name;
				messageTable[i][2]=com.get(i).number;
				messageTable[i][3]=com.get(i).quantity+"";
				messageTable[i][4]=com.get(i).defaultImportPrice+"";
				messageTable[i][5]=com.get(i).defaultExportPrice+"";
				messageTable[i][6]=com.get(i).latestImportPrice+"";
				messageTable[i][7]=com.get(i).latestExportPrice+"";
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
