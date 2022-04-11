package mymain;


//awt : Abstrace Window Toolkit(window에 관련된 기능만 모아놓은 패키지)
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyWin extends JFrame{

	public MyWin() { //생성자역할(초기화) << 제일 먼저 호출됨. 
		// TODO Auto-generated constructor stub
		
		
		//super.setTitle("내가 만든 첫번째 윈도우");
	
		super("내가 만든 첫번째 윈도우");
		
		JButton jbt1 = new JButton("안녕");
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
		
		new MyWin(); // 내가 내 클래스 객체를 만듦
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
