package exam01_extends;

public class DmbCellphone extends Cellphone{

	int channel;
	
	DmbCellphone(String model, String color, int channel)
	{
		super(model,color);
		//super("갤럭시",color);
		this.channel = channel;
	}
	

	void turnOnDmb() {
		System.out.println("채널 " + channel + "번 DMB 방송 수신을 시작합니다.");
	}
	void changeChannelDmb(int channel) {
		this.channel = channel;
		System.out.println("채널 " + channel + "번으로 바꿉니다.");
	}
	void turnOffDmb() {
		System.out.println("DMB 방송 수신을 멈춥니다.");
	}
	
	@Override
	void powerOn() { 
		System.out.println("폰과 DMB 전원을 켭니다.");
		super.powerOn();
	}
	
	@Override
	void powerOff() {
		System.out.println("폰과 DMB 전원을 끕니다.");
	}

	
}
