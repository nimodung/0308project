package exam06_view;

import javafx.beans.property.SimpleStringProperty;

public class Name {

	private SimpleStringProperty name; //�ܼ��� ���ڿ� �Ӽ� //fx��ü�� ����� ������ �Ӽ� //�Ӽ����� ���� get set ���� �Լ����� �� �� ���� 
										//String�� �ٸ��� ���µ� fx������ ���� ��ü(name, image) �Ӽ����� �����ϴ� �Լ��� ���
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
