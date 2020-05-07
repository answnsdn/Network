package chat.step05;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ClientChatView extends JFrame {
	JPanel contentPane;
	 JTextField txtinput;
	 JTextArea taChat;
	 JButton btnsend;
	 JList lstconnect;

	 String ip;
	 int port;
	 String nickname;
	 Socket socket;

	 //=======1. 서버와 통신하기 위한 입출력 스트림 변수선언========
	 InputStream is;
	 InputStreamReader ir;
	 BufferedReader br;
	 
	 OutputStream os;
	 PrintWriter pw;
	//==================================================

	public ClientChatView(String ip, int port, String nickname) {
		this.ip = ip;
		this.port = port;
		this.nickname = nickname;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//창닫기 버튼을 누르면 닫힘
		setBounds(100, 100, 758, 478);//위치정의
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		taChat = new JTextArea();
		taChat.setBounds(12, 10, 501, 375);
		contentPane.add(taChat);
		
		txtinput = new JTextField();
		txtinput.setBounds(12, 395, 378, 35);
		contentPane.add(txtinput);
		txtinput.setColumns(10);
		
		btnsend = new JButton("\uC11C\uBC84\uB85C\uC804\uC1A1");
		btnsend.setFont(new Font("HY견고딕", Font.BOLD, 14));
		btnsend.setBounds(402, 395, 109, 35);
		contentPane.add(btnsend);
		
		JLabel lblNewLabel = new JLabel("\uC811\uC18D\uC790:");
		lblNewLabel.setFont(new Font("HY견고딕", Font.BOLD, 14));
		lblNewLabel.setBounds(519, 10, 120, 35);
		contentPane.add(lblNewLabel);
		
		lstconnect = new JList();
		lstconnect.setBounds(525, 47, 205, 108);
		contentPane.add(lstconnect);
		
		setVisible(true);//화면에 JFrame이 보이도록 설정
		connectServer();
		
	}
	
	public void connectServer() {
		try {
			socket = new Socket(ip,port);
		//======3. ioWork 호출======
			if(socket!=null) {
				ioWork();
			}
		//=========================
		//======5. 서버에게 nickname전송하기======
			sendMsg(nickname);
		//===================================
		//======11. 서버가 보내는 환영메세지 출력하기======
		//채팅을 구현하기 위해서는 이 부분을 쓰레드, while문으로 구현해야 한다.
			String msg = br.readLine();
			taChat.append(msg+"\n");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	//======2. 서버와 통신할 수 있도록 소켓으로부터 input/output 스트림 얻기======
		public void ioWork() {
			try {
				is = socket.getInputStream();
				ir = new InputStreamReader(is);
				br = new BufferedReader(ir);
				os = socket.getOutputStream();
				pw = new PrintWriter(os,true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	//======4. 서버에 메세지를 전송하는 메소드 정의======
		public void sendMsg(String message) {
			System.out.println("클라이언트가 서버에게 메세지 전송하기"+message);
			pw.println(message);
		}
}
