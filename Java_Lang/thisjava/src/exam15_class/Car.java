package exam15_class;

public class Car {

	private String model;
	private String owner;
	
	public Car() {
		
	}
	
	public Car(String model) {
		this.model = model;
	}
	
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
