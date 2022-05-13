package main;

import java.util.List;
import java.util.Scanner;

import Vo.LoginVo;
import Dao.LoginDao;

public class Main {

	static Scanner sc = new Scanner(System.in);

	
	static String nickname;
	static String ID;
	
	static List<LoginVo> list = null;
	
	
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
		
		//insert �ϱ� ���� vo ����
		//LoginVo vo = new LoginVo();
		
		//�̹� �ִ� ���̵����� ��ȸ�ϱ�
		list = LoginDao.getInstance().selectList();
		
		OUT1:
		while(true) {
			
			System.out.println("ȸ�� ������ ID�� �Է��ϼ���.");
			System.out.println("ID >> ");
			String new_id = sc.nextLine();
			
			LoginVo vo = LoginDao.getInstance().selectOneFromID(new_id);
			
			if(vo!=null) {
				System.out.println("�̹� �����ϴ� ���̵� �Դϴ�.");
				continue OUT1;
			}

			vo = new LoginVo();
			
			
			
			System.out.println("��й�ȣ�� �Է��ϼ���.");
			System.out.println("��й�ȣ >> ");
			String new_pwd = sc.nextLine();

			
			OUT2: 
			while (true) {
				System.out.println("�г����� �Է��ϼ���.");
				System.out.println("�г��� >> ");
				String new_nick = sc.nextLine();

				LoginVo vo2 = LoginDao.getInstance().selectOneFromNickname(new_nick);
				
				//vo2���� ���� �ƴϸ� �� ���̺� �����Ͱ� ������
				if(vo2!=null) {
					System.out.println("�̹� �����ϴ� �г��� �Դϴ�."); 
					continue OUT2;
				}

				//�г��� ����
				vo.setNickname(new_nick);
				break;

			}
			
			
			vo.setUserid(new_id);
			vo.setPassword(new_pwd);
			
			int res = LoginDao.getInstance().signUp_insert(vo);
			
			System.out.println("ȸ������ �Ϸ�!!" + res);
			
			return;
			
			} // while - end;

	}

	private static void login() {
		System.out.println("               [�α���]");
		System.out.println();
		//��ȸ�ϱ� ���� Dao��ü ����
		list = LoginDao.getInstance().selectList();

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
