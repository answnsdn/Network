package chat.step06;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class ChatLoginListener  implements ActionListener{
	ChatLogin view;//�̺�Ʈ�� �߻���Ű�� ȭ�� - ChatLogin
	String nickname;
	public ChatLoginListener(ChatLogin view) {
		super();
		this.view = view;
	}
	
	//swing���� ��ư�� Ŭ���� �� ȣ��Ǵ� �޼ҵ�
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==view.btnConnect){
			nickname = view.txtId.getText().trim();
			if(nickname.isEmpty()) {
				JOptionPane.showMessageDialog(view,
						"��ȭ���� �Է��ϼ���", "��ȭ�����", JOptionPane.WARNING_MESSAGE);
				return;
			}
			String ip = view.cboServer.getSelectedItem()+"";
			int port = Integer.parseInt(view.cboPort.getSelectedItem()+"");
			System.out.println(ip+port);
			
			//======1. ip, port, nickname�� �����ϸ� ClientChatView�� ����======
				ClientChatView chatview = new ClientChatView(ip,port,nickname);
				view.dispose();//�α���â�� ����
			//============================================================
		}
	}
}