import java.io.*;
	import java.util.ArrayList;
public class Helper {
	
	
		String Filename;
		ArrayList<String>info=new ArrayList<String>();
		String[] sArray=new String[100];
		public void setFilename(String Filename){
			this.Filename=Filename;
		}
		
		public String getInput(){
			String Inputline=null;
			try{
				BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
				Inputline=br.readLine();
				
			}catch(IOException e){
				System.out.println("IOException:"+e);
			}
			return Inputline;
		}
		
		public ArrayList<String> readfile(){
			String temp=null;
			try{
	    		File file=new File(Filename);
	    		FileInputStream fis=new FileInputStream(file);
	    		InputStreamReader isr=new InputStreamReader(fis);
	    		BufferedReader br=new BufferedReader(isr);
	    		info.clear();
	    		while((temp=br.readLine())!=null){
	    			info.add(temp);
	    		}
	    		br.close();
			
		}catch(IOException e){
			System.out.println("IOException:"+e);
			}
			return info;
		}
	    
		public void output(ArrayList<String> info){
	    	int i,n;
	    	try{
	    		FileOutputStream fos=new FileOutputStream(Filename);
	    		PrintWriter pw=new PrintWriter(fos);
	    		n=info.size();
	    		for(i=0;i<n;i++){
	    		pw.write(info.get(i).toString());
	    		pw.write("\r\n");
	    		
	    		}
	    		pw.flush();
	    		pw.close();
			}catch(IOException e){
				System.out.println("IOException:"+e);
			}
	    }
	    
	    public void split(String s){
	    	sArray=s.split("£»",-1);
	    }
	    
	    public String dataplusone(String date1){
	    	String newdate;
	    	String[] tempsArray;
	    	int year;
	    	int month;
	    	int day;
	    	
	    	tempsArray=date1.split("/", -1);
	    	year=Integer.parseInt(tempsArray[0]);
	    	month=Integer.parseInt(tempsArray[1]);
	    	day=Integer.parseInt(tempsArray[2]);
	    	
	    	day=day+1;
	    	
	    	if((month==1&&day==32)||(month==3&&day==32)||(month==5&&day==32)||(month==7&&day==32)||(month==8&&day==32)||(month==10&&day==32)||(month==12&&day==32)){
	    		month++;
	    		day=day-31;
	    	}
	    	
	    	if((month==4||month==6||month==9||month==11)&&day==31){
	    		month++;
	    		day=day-30;
	    	}
	    	
	    	if((month==2)&&day==29){
	    		month++;
	    		day=day-28;
	    	}
	    	
	    	if(month==13){
	    		month--;
	    		year++;
	    	}
	    	
	    	newdate=year+""+"/"+month+""+"/"+day+"";
	    	return newdate;
	    }
	    
	    public boolean isEarlier(String date1,String date2){
	    	boolean result;
	    	String[] tempsArray1;
	    	int year1;
	    	int month1;
	    	int day1;
	    	String[] tempsArray2;
	    	int year2;
	    	int month2;
	    	int day2;
	    	
	    	tempsArray1=date1.split("/", -1);
	    	year1=Integer.parseInt(tempsArray1[0]);
	    	month1=Integer.parseInt(tempsArray1[1]);
	    	day1=Integer.parseInt(tempsArray1[2]);
	    	tempsArray2=date2.split("/", -1);
	    	year2=Integer.parseInt(tempsArray2[0]);
	    	month2=Integer.parseInt(tempsArray2[1]);
	    	day2=Integer.parseInt(tempsArray2[2]);
	    	
	    	if(year1<year2)
	    		result=true;
	    	else{
	    		if(year1>year2)
	    			result=false;
	    		else{
	    			if(month1<month2)
	    				result=true;
	    			else{
	    				if(month1>month2)
		    				result=false;
	    			else{
	    				if(day1<day2)
	    					result=true;
	    				else
	    					result=false;
	    					
	    			}
	    		}
	    	}
	    }
	    	return result;
	}
}


