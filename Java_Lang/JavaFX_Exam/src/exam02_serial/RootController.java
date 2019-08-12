package exam02_serial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class RootController implements Initializable {

	@FXML private Button btn_led_on, btn_led_off, btn_led_toggle;
	
	static InputStream in;
	static OutputStream out; 
	static SerialPort serialPort;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			new RootController().connect("COM7");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		btn_led_on.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				System.out.println("button 1 clicked");
				String string = "led on\n";
				try {	
					for(int  i = 0; i <string.length(); i++) {
						out.write(string.charAt(i));
					}
					//out.write('\n');
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btn_led_off.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				System.out.println("button 1 clicked");
				String string = "led off\n";
				try {	
					for(int  i = 0; i <string.length(); i++) {
						out.write(string.charAt(i));
					}
					//out.write('\n');
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btn_led_toggle.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				System.out.println("button 1 clicked");
				String string = "led toggle\n";
				try {	
					for(int  i = 0; i <string.length(); i++) {
						out.write(string.charAt(i));
					}
					//out.write('\n');
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}

	private void connect(String portName) throws Exception{
		
		System.out.printf("Port : %s\n", portName);
		
		
		CommPortIdentifier portIdentifier = 
					CommPortIdentifier.getPortIdentifier(portName);
		
		if(portIdentifier.isCurrentlyOwned()) { //currentlyowned : ���� �����ֳ�
			System.out.println("Error: Port is currently in use");
		}
		else {
			CommPort commPort = 
					portIdentifier.open(this.getClass().getName(), 2000);
			
			if(commPort instanceof SerialPort) {
				serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams( //�ø��� ��Ʈ ����
						9600, //��� �ӵ�
						SerialPort.DATABITS_8, //���Ʈ ����ΰ�, 
						SerialPort.STOPBITS_1,  
						SerialPort.PARITY_NONE);
				
				//in, out thread�� ���� ������ִ�
				//�� ���� thread�� ���� 
				//os�� delay�� �ᵵ ���� 
				//=> ��Ƽ thread�̱� ������ delay�� �� �����尡 ���絵 �ٸ� thread���� �����ϱ� ������ ������ ����
				//in, out thread�� ����ϸ� main�� �������(interrupt ó��)�� �� ����
				in = serialPort.getInputStream();
				
				out = serialPort.getOutputStream();
				
				(new Thread(new SerialReader(in))).start();
				(new Thread(new SerialWriter(out))).start();
			}
		}
	}

	//��ǻ�� ���忡���� ����
	public static class SerialReader implements Runnable {
		//���� ���� data�� ȭ�鿡 ������ִ� 
		InputStream in;
		
		public SerialReader(InputStream in) {
			this.in = in;
		}

		@Override
		public void run() {
			byte[] buffer = new byte[1024];
			int len = -1;
			
			try {
				while((len = this.in.read(buffer)) > -1) {
					//this.in.read(buffer) : in�� �о buffer�� �����ϰ� buffer�� ���� return 
					// �����Ұ� ������ return -1
					System.out.print(new String(buffer, 0, len));
				}
			} catch (IOException e) {
		
				e.printStackTrace();
			}
			
		}
	}
	
	//��ǻ�� ���忡���� �۽�
	public static class SerialWriter implements Runnable{
		OutputStream out;
		
		public SerialWriter(OutputStream out) {
			this.out = out;
		}

		@Override
		public void run() {
			try {
				int c = 0;
				System.out.println("\n Keyboard Input Read!!!");//�ȳ� ���� ���
				while((c = System.in.read()) > -1) {
					//System.in.read() : Ű���� �Է� , ���̻� ���� data�� ������ return -1
					this.out.write(c); 
				}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
}
