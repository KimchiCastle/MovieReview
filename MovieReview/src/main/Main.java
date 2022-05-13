package main;

import java.util.List;
import java.util.Scanner;

import Vo.LoginVo;
import Dao.LoginDao;

public class Main {

	static Scanner sc = new Scanner(System.in);

	
	static String nickname;
	static String ID;
	
	private static void main_login_disp() {

		
		while (true) {
			
			System.out.println("�١ڡ١ڡ١ڡ١ڡ١ڹ���١ڡ١ڡ١ڡ١ڡ١�");
			System.out.println("�α��� [1] ");
			System.out.println("ȸ�� ����[2]");
			System.out.println("������[3]");
			
			
			try {
				int res = sc.nextInt();
				
				sc.nextLine(); // �Է±���������
				if(res>=4) {
					System.out.println("�ٽ� �Է��ϼ���");
					
					continue;
					
				}	
				if (res == 1) {
					
					login();
					
				} else if (res == 2) {
					
					sign_up();
					
					
				} else if (res == 3) {
					//���α׷� ����
					return;
				}
			} catch (Exception e) {
				System.out.println("�ٽ� �Է��ϼ���");
				sc = new Scanner(System.in);
			}
		}

	}
	//ȸ������
	private static void sign_up() {
		
		System.out.println("ȸ�������Դϴ�.");
		while(true) {
			
			
			
			return;
		}
		

	}

	private static void login() {
		System.out.println("               [�α���]");
		System.out.println();

		List<LoginVo> list = LoginDao.getInstance().selectList();

			for(int i=0; i<5; i++) {
				
			System.out.println("���̵� :");
			System.out.println("��й�ȣ :");
			
			String id_check = sc.nextLine();
			String pwd_check = sc.nextLine();
			
			
			
			
			
			/*
			 * ���̵�	   ���			�г���	
			 * 'id1',	   '123123' 	'�������5��'
			 *  'my123',,  '1q2w3e4r' 	'���ָ���7����'
			 *  'bonglove','bong123' 	'����ȣ�����'
			 *  'kimchi12' 'kimchi12'	'kimchi12'
			 *  'gogo0325' '123123'		'��ȭ�л���'
			 */
			
			
			
			for (LoginVo a : list) {
				//���̵� üũ
				if (id_check.equals(a.getUserid())) {
					//���üũ
					if(pwd_check.equals(a.getPassword())) {
						System.out.println("�α��� �Ϸ�");
						System.out.println();
						
						nickname = a.getNickname();
						ID = a.getUserid();
						
						
						System.out.printf("%s �� ȯ���մϴ�!!",nickname);
						
						main_main_disp();
						
						//��������Ǹ� �����ؼ� ȣ���� ������
						return;
						
					}
	
				} // id if - end;

			} // ȸ�� check ����

			System.out.println("�߸��� ������ �Է� �ϼ̽��ϴ�.");
			
			
		} // �α��� for end;
		

	}

	private static void main_main_disp() {
		// TODO Auto-generated method stub
		
		System.out.println("����ȭ���Դϴ�.");
		System.out.println("1. ���ۿ�ȭ");
		System.out.println("2. ���ۿ�ȭ");
		System.out.printf("3. %s ���� ���� Ȯ���ϱ�",nickname);
		
		System.out.println("�α׾ƿ� �մϴ�.");
		return;
		
	}

	public static void main(String[] args) {

		main_login_disp(); // �� �Լ����� return�ϸ� ����

		System.out.println("���α׷� ����");

	}
}
