package exam10_nested_interface;

//import exam10_nested_interface.Button.OnClickListener;

public class CallListener implements Button.OnClickListener {

	@Override
	public void onClick() {
		System.out.println("전화를 겁니다.");

	}

}
