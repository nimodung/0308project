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
		
		//interface로 객체를 만들수 없는 이유 => 안에 추상 메소드가 있기 때문에
		// => 추상 메소드를 구현해주면 객체로 만들 수 있다.
		//익명 클래스
		RemoteControl PCrc = new RemoteControl() {
			int volume;
			
			@Override
			public void turnOn() {
				System.out.println("PC를 켭니다.");
			}

			@Override
			public void turnOff() {
				System.out.println("PC를 끕니다.");
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
				
				System.out.println("현재 PC 볼륨 : " + this.volume);
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
