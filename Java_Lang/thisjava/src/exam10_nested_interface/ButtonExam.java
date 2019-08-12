package exam10_nested_interface;

public class ButtonExam implements Button.OnClickListener{

	Button Btn_Call = new Button();
	Button Btn_Message = new Button();
	

	Btn_Call.SetOnClickListener(new CallListener() {
		);
	/*
	 * Button.OnClickListener listener = new Button.OnClickListener() {
	 * 
	 * @Override public void onClick() { System.out.println("전화를 겁니다."); } };
	 */
	
	
	Btn_Call.touch();
	Btn_Message.setOnClickListener(new Button.OnClickListener() {
		
	});
	
	Btn_Massage.touch();
}
	
	
}
