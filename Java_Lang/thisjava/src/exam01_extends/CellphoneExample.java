package exam01_extends;

public class CellphoneExample {
	public static void main(String[] args) {
		Cellphone cellphone = new Cellphone("iphone8", "pink");
		
		System.out.println("model : " + cellphone.model + ", color : " + cellphone.color);
		cellphone.powerOn();
		
		DmbCellphone dmbcellphone = new DmbCellphone("°¶·°½Ã", "black", 5);
		
		System.out.println("model : " + dmbcellphone.model + ", color : " + dmbcellphone.color);
		dmbcellphone.powerOn();
		dmbcellphone.turnOnDmb();
		dmbcellphone.powerOff();
	}
}
