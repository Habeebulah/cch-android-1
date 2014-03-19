package models;

public class Content {
	private long id;
	private String category;
	private String message;
	
	public Content(String message, String category){
		this.category = category;
		this.message = message;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}	
	
	//Will be used by the ArrayAdapter in the ListView
		@Override
		public String toString(){
			return this.message;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
}
