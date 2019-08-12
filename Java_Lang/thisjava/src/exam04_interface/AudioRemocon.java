package exam04_interface;

public class AudioRemocon implements RemoteControl {

	private int volume;

	@Override
	public void turnOn() {
		System.out.println("������� �մϴ�.");
		
	}

	@Override
	public void turnOff() {
		System.out.println("������� ���ϴ�.");

		
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
		
		System.out.println("���� TV ���� : " + this.volume);
	}
	
	

}
