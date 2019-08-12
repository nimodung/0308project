package exam04_interface;

public class SmartTV_Remocon implements RemoteControl, Searchable {

	int volume;
	@Override
	public void searchable(String keyWord) {
		System.out.println(keyWord + "�� �˻��մϴ�.");
	}

	@Override
	public void turnOn() {
		System.out.println("����Ʈ TV�� �մϴ�.");
	}

	@Override
	public void turnOff() {
		System.out.println("����Ʈ TV�� ���ϴ�.");
	}

	@Override
	public void setVolume(int volume) {
		if(volume > RemoteControl.MAX_VOLUME) {
			this.volume = RemoteControl.MAX_VOLUME;
		}
		else if(volume < RemoteControl.MIN_VOLUME) {
			this.volume = RemoteControl.MIN_VOLUME;
		}
		else this.volume = volume;
		
		System.out.println("���� ����Ʈ TV ���� : " + this.volume);
	}

	

}
