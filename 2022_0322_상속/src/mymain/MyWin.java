package mymain;


//awt : Abstrace Window Toolkit(window�� ���õ� ��ɸ� ��Ƴ��� ��Ű��)
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyWin extends JFrame{

	public MyWin() { //�����ڿ���(�ʱ�ȭ) << ���� ���� ȣ���. 
		// TODO Auto-generated constructor stub
		
		
		//super.setTitle("���� ���� ù��° ������");
	
		super("���� ���� ù��° ������");
		
		JButton jbt1 = new JButton("�ȳ�");
		super.add(jbt1, "South");
		JButton jbt2 = new JButton("Hello Everyone");
		super.add(jbt2, "North");
		
		super.setLocation(100,100);
		
		
		super.setSize(400,300);
		
		super.setVisible(true);
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new MyWin(); // ���� �� Ŭ���� ��ü�� ����
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
