package exam04_interface;

public class Remotecontrol_exam {
	public static void main(String[] args) {
		RemoteControl rc;
		
		rc = new AudioRemocon();
		rc.turnOn();
		rc.setVolume(5);
		rc.turnOff();
		
		rc = new TV_Remocon();
		rc.turnOn();
		rc.setVolume(15);
		rc.turnOff();
		RemoteControl.changeBattery();
		
		//interface�� ��ü�� ����� ���� ���� => �ȿ� �߻� �޼ҵ尡 �ֱ� ������
		// => �߻� �޼ҵ带 �������ָ� ��ü�� ���� �� �ִ�.
		//�͸� Ŭ����
		RemoteControl PCrc = new RemoteControl() {
			int volume;
			
			@Override
			public void turnOn() {
				System.out.println("PC�� �մϴ�.");
			}

			@Override
			public void turnOff() {
				System.out.println("PC�� ���ϴ�.");
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
				
				System.out.println("���� PC ���� : " + this.volume);
			}			
		};
		
		PCrc.turnOn();
		PCrc.setVolume(9);
		PCrc.turnOff();
		
		SmartTV_Remocon STVrc= new SmartTV_Remocon();
		STVrc.turnOn();
		STVrc.setVolume(18);
		STVrc.searchable("Hot Keyword");
		STVrc.turnOff();
	}
}
