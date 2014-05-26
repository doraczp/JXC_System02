import java.util.ArrayList;


public class StockManagement {
	String instruction;
	String[][] messageTable=new String[100][11];
	Helper helper=new Helper();
	ArrayList<String>iminfo=new ArrayList<String>();
	ArrayList<String>exinfo=new ArrayList<String>();
	ArrayList<String>cominfo=new ArrayList<String>();
	ArrayList<String>history=new ArrayList<String>();
	public void setInstruction(String instruction){
		this.instruction=instruction;
	}
	
		
	public void go(){
		if(instruction.equals("STOCK_SHO")){
		
			
			//��ȡ��ʷ��¼��������ÿһ����Ʒ���������²���
			helper.setFilename("data/history.txt");
			history=helper.readfile();
			int n0=history.size();
			for(int j=0;j<n0;j++){
				int imquantity=0;
				int totalimprice=0;
				int exquantity=0;
				int totalexprice=0;
			

			    String name=history.get(j);
			    String number=null;
			    //��ȡ���������������ܼ�
			    
			    helper.setFilename("data/commodity.txt");
			    cominfo=helper.readfile();
			    for(int i=0;i<cominfo.size();i++){
			    	helper.split(cominfo.get(i));
			    	if(helper.sArray[0].equals(name)){
			    		number=helper.sArray[1];
			    	}
			    }
			    
			    
			    helper.setFilename("data/importsheet.txt");
			    iminfo=helper.readfile();
			    int n=iminfo.size();
			    
					for(int i=0;i<n;i++){
						helper.split(iminfo.get(i));
						String commodityname=helper.sArray[1];
						if(name.equals(commodityname)&&iminfo.get(i).charAt(0)=='A'){
							imquantity=imquantity+Integer.parseInt(helper.sArray[3]);
							totalimprice=totalimprice+Integer.parseInt(helper.sArray[6].replace(" ", ""));
						}else{
							imquantity=imquantity-Integer.parseInt(helper.sArray[3]);
							totalimprice=totalimprice-Integer.parseInt(helper.sArray[6].replace(" ", ""));
						}
					}
				
				//��ȡ���������������ܼ�
				helper.setFilename("data/exportsheet.txt");
				exinfo=helper.readfile();
				int n1=exinfo.size();
				
					for(int i=0;i<n1;i++){
						helper.split(exinfo.get(i));
						String commodityname=helper.sArray[1];
						if(name.equals(commodityname)&&exinfo.get(i).charAt(0)=='A'){
							exquantity=exquantity+Integer.parseInt(helper.sArray[3]);
							totalexprice=totalexprice+Integer.parseInt(helper.sArray[6]);
						}else{
							exquantity=exquantity-Integer.parseInt(helper.sArray[3]);
							totalexprice=totalexprice-Integer.parseInt(helper.sArray[6]);
						}
					}
				
				
				
				//���
				int averageimprice;
				int averageexprice;
				int averageprice;
				
				if(imquantity==0)
					averageimprice=0;
				else
					averageimprice=totalimprice/imquantity;
				
				if(exquantity==0)
					averageexprice=0;
				else
					averageexprice=totalexprice/exquantity;
				
				if(imquantity==exquantity)
					averageprice=0;
				else
					averageprice=(totalimprice-totalexprice)/(imquantity-exquantity);
					
				
				
				System.out.println(name+" "+number+" "+imquantity+" "+averageimprice+" "+totalimprice+" "+exquantity+" "+averageexprice+" "+totalexprice+" "+(imquantity-exquantity)+" "+averageprice+" "+(totalimprice-totalexprice));
			    messageTable[j][0]=name;
			    messageTable[j][1]=number;
			    messageTable[j][2]=imquantity+"";
			    messageTable[j][3]=averageimprice+"";
			    messageTable[j][4]=totalimprice+"";
			    messageTable[j][5]=exquantity+"";
			    messageTable[j][6]=averageexprice+"";
			    messageTable[j][7]=totalexprice+"";
			    messageTable[j][8]=(imquantity-exquantity)+"";
			    messageTable[j][9]=averageprice+"";
			    messageTable[j][10]=(totalimprice-totalexprice)+"";
			
			}
		}else{
			System.out.println("ָ�����");
		}
		
	}
	public String[][] getMessageTable(){
		return messageTable;
	}

}
