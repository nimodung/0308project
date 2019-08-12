package exam06_view;

import javafx.beans.property.SimpleStringProperty;

public class Name {

	private SimpleStringProperty name; //단순한 문자열 속성 //fx객체를 만들면 가지는 속성 //속성으로 쓰면 get set 같은 함수들을 쓸 수 있음 
										//String과 다를거 없는데 fx에서는 보통 객체(name, image) 속성에서 제공하는 함수를 사용
	private SimpleStringProperty image; 
	
	public Name() {
		this.name = new SimpleStringProperty();
		this.image = new SimpleStringProperty();
	}
	
	public Name(String name, String image) {
		this.name = new SimpleStringProperty(name);
		this.image = new SimpleStringProperty(image);
	}
	
	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getImage() {
		return image.get();
	}

	public void setImage(String image) {
		this.image.set(image);
	}

	
}
