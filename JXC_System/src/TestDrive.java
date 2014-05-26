
public class TestDrive {

	Helper helper=new Helper();
	CommodityManagement commodity=new CommodityManagement();
	StockManagement stock=new StockManagement();
	ImportManagement importer=new ImportManagement();
	ExportManagement exporter=new ExportManagement();
	CustomerManagement customer=new CustomerManagement();
	AccountManagement account=new AccountManagement();
	String instruction;
	
	public void go(){
		String keyword="";
		instruction=helper.getInput();
		keyword=instruction.substring(0, 2);
		//System.out.println(keyword);
		switch(keyword){
		case "CO":{
			commodity.setInstruction(instruction.substring(10));
			commodity.go();
			break;
		}
		case "AC":{
			account.setInstruction(instruction.substring(8));
			account.go();
			break;
		}
		case "CU":{
			customer.setInstruction(instruction.substring(9));
			customer.go();
			break;
		}
		case "EX":{
			exporter.setInstruction(instruction.substring(7));
			exporter.go();
			break;
		}
		case "IM":{
			importer.setInstruction(instruction.substring(7));
			importer.go();
			break;
		}
		case "ST":{
			stock.setInstruction(instruction);
			stock.go();
			break;
		}
		default:System.out.println("∏Ò Ω¥ÌŒÛ£°");
		}
	}
	public static void main(String[] args) {
		TestDrive test=new TestDrive();
		test.go();// TODO Auto-generated method stub

	}

}
