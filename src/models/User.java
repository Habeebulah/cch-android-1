package models;

public class User {

	private int _id;
	private int staffId;
	private String password;
	private String passwordAgain;
	private String firstname;
	private String lastname;
	
	
	
	
	 // Empty constructor
    public User(){
    	
    }
 // constructor

    public User(int staffId, String password){
        this.password = password;
        this.staffId = staffId;
      
    }
    
    // constructor
    public User(int id, int staffId, String password){
        this._id = id;
        this.staffId = staffId;
        this.password = password;
    }

	
	

     
   
    // getting ID
    public int getID(){
        return this._id;
    }
     
    // setting id
    public void setID(int id){
        this._id = id;
    }
    // getting Staffid
	public int getStaffId() {
		return staffId;
	}
	
	//setting StaffId
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	
	
	
	public String getPasswordAgain() {
		return passwordAgain;
	}
	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
	public String getDisplayName() {
		return firstname + " " + lastname;
	}

	// getting password
	public String getPassword() {
		return password;
	}
	
	//setting password
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
    
     
   
     
    


}
