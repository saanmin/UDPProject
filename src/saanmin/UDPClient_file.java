package saanmin;

import java.io.*;
import java.net.*;

public class UDPClient_file {
	public static void main(String[] args) throws IOException {
		DatagramSocket soc = new DatagramSocket(8000);
		System.out.println("전송받을 준비 완료!");
		
		File file = null;
		DataOutputStream dos = null;
		
		while(true) { //무한루프 돌면서 도착하는 데이터를 입력받는다.
			DatagramPacket dp = new DatagramPacket(new byte[65508], 65508);
			soc.receive(dp);
			String str = new String(dp.getData()).trim();
			//전송받은 데이터의 내용을 String 객체의 형태로 변환, white character 제거
			if(str.equalsIgnoreCase("start")) {
				System.out.println("전송되고 있음!");
				file = new File("/Users/saanmin/network-workspace/UDPProject/src/edu/skku/saanmin/output.txt");
				dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
				
			}
			else if (str.equalsIgnoreCase("end")) {
				System.out.println("전송완료!");
				dos.close();
				break;
			}
			else if(file != null) {
				dos.write(str.getBytes(),0,str.getBytes().length);
			}
		}
	}
}
