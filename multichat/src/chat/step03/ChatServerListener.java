package chat.step03;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ChatServerListener implements ActionListener{
	ChatServerView view;

	public ChatServerListener(ChatServerView view) {
		super();
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==view.btnstartServer){
			String port = JOptionPane.showInputDialog(view, 
					"port를 입력하세요",JOptionPane.INFORMATION_MESSAGE);
			//======4. 서버 시작버튼을 누르면 서버를 Start하는 메소드 호출======
			view.serverStart(Integer.parseInt(port));
		}else if(e.getSource()==view.btnstop){
			System.out.println("?꽌踰꾩쨷吏?");
		}
	}
	
	
}
