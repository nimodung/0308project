package application;

public class CardMatchThread extends Thread {

	RootController controller;
	
	public CardMatchThread(RootController controller) {
		super();
		this.controller = controller;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			controller.checkMatch();
		}
	}
}
