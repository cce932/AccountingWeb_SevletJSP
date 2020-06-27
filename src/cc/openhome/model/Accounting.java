package cc.openhome.model;

public class Accounting {
    private boolean type;     		//true收 false支
    private String date;      		//帳務發生日
    private String category;  		//收食衣住行育樂
    private boolean accounttype;	//true現金 false銀行帳戶
    private int amount;   			//金額
    private String notes;  			//備註
    private String name;  			//使用者
    
    public Accounting(boolean _type,String _date,String _category,boolean _accounttype,int _amount,String _notes,String _name) {
        this.type=_type;
        this.date=_date;
        this.category=_category;
        this.accounttype=_accounttype;
        this.amount=_amount;
        this.notes=_notes;
        this.name=_name;
    }
    
    public boolean getType() {
    	return type;
    }
    
	public String getDate() {
        return date;
    }
    
	public String getCategory() {
        return category;
    }
	
    public boolean getAccounttype() {
    	return accounttype;
    }
    
    public int getAmount() {
    	return amount;
    }
    
	public String getNotes() {
        return notes;
    }
    
	public String getName() {
        return name;
    }

}
