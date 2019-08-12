import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class SerialExam {

	public static void main(String[] args) {
		try {
			new SerialExam().connect("COM7");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//시리얼 연결을 하기위한 함수 
	private void connect(String portName) throws Exception{
		
		System.out.printf("Port : %s\n", portName);
		
		
		CommPortIdentifier portIdentifier = 
					CommPortIdentifier.getPortIdentifier(portName);
		
		if(portIdentifier.isCurrentlyOwned()) { //currentlyowned : 누가 쓰고있냐
			System.out.println("Error: Port is currently in use");
		}
		else {
			CommPort commPort = 
					portIdentifier.open(this.getClass().getName(), 2000);
			
			if(commPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams( //시리얼 포트 설정
						9600, //통신 속도
						SerialPort.DATABITS_8, //몇비트 통신인가, 
						SerialPort.STOPBITS_1,  
						SerialPort.PARITY_NONE);
				
				InputStream in = serialPort.getInputStream();
				
				OutputStream out = serialPort.getOutputStream();
				
				(new Thread(new SerialReader(in))).start();
				(new Thread(new SerialWriter(out))).start();
			}
		}
	}

	public static class SerialReader implements Runnable {
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
					System.out.print(new String(buffer, 0, len));
				}
			} catch (IOException e) {
		
				e.printStackTrace();
			}
			
		}
	}
	
	public static class SerialWriter implements Runnable{
		OutputStream out;
		
		public SerialWriter(OutputStream out) {
			this.out = out;
		}

		@Override
		public void run() {
			try {
				int c = 0;
				System.out.println("\n Keyboard Input Read!!!");//안내 문구 출력
				while((c = System.in.read()) > -1) {
					this.out.write(c);
				}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
}
