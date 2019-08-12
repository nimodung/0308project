package exam01_extends;

public class DmbCellphone extends Cellphone{

	int channel;
	
	DmbCellphone(String model, String color, int channel)
	{
		super(model,color);
		//super("������",color);
		this.channel = channel;
	}
	

	void turnOnDmb() {
		System.out.println("ä�� " + channel + "�� DMB ��� ������ �����մϴ�.");
	}
	void changeChannelDmb(int channel) {
		this.channel = channel;
		System.out.println("ä�� " + channel + "������ �ٲߴϴ�.");
	}
	void turnOffDmb() {
		System.out.println("DMB ��� ������ ����ϴ�.");
	}
	
	@Override
	void powerOn() { 
		System.out.println("���� DMB ������ �մϴ�.");
		super.powerOn();
	}
	
	@Override
	void powerOff() {
		System.out.println("���� DMB ������ ���ϴ�.");
	}

	
}
