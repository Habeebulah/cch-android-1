package models;

public class User_Profile {
	
	//private variables
		int staffId;	
	    String firstName;
		String lastName;
		int facilityId;
		String type;
		String supervisor;
		String password;
		String imei;
		int phoneNumber;
		String secretQuestion;
		String answer;
		String masterPassword;		
		
		//constructor
		public User_Profile (int staffId, String firstName, String lastName,
				int facilityId, String type , String supervisor, String password,
				String IMEI, int phoneNumber, String secreteQuestion, String answer){
			
			this.staffId = staffId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.facilityId = facilityId;
			this.type = type;
			this.supervisor = supervisor;
			this.password = password;
			this.imei =IMEI;
			this.phoneNumber = phoneNumber;
			this.secretQuestion =secreteQuestion;
			this.answer= answer;
			
			
		}
		
		
		
		
		
		//get methods
		
		public int getStaffId(){
			return staffId;
		}
		
		public String getPassword(){
			return password;
		}
		
		public String getType(){
			return type;
		}
		public String getSupervisor(){
			return supervisor;
			
		}	
		
		public String getFirstName(){
			return firstName;
		}
		
		public String getLastName(){
			return lastName;
		}
		
		public int getFacilityId(){
			return facilityId;
		}
		public String getImei(){
			return imei;
		}
		public int getPhoneNumber(){
			return phoneNumber;
		}
		public String getSecreteQuestion(){
			return secretQuestion;
		}
		public String getAnswer(){
			return answer;
		}
		// set Methods
		 public void setStaffId(){
			 
		 }
		 
		 public void setFirstName(){
			 
		 }
		 
		 public void setLastName(){
			 
		 }
		 
		 public void setPassword(){
			 
		 }
		 
}
