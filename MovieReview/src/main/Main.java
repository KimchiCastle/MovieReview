package main;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import dao.LoginDao;
import vo.LoginVo;

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
					System.out.println("�߸� �ԷµǾ����ϴ�.");
					
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
				System.out.println("�߸� �ԷµǾ����ϴ�.");
				sc = new Scanner(System.in);
			}
		}

	}
	//ȸ������
	private static void sign_up() {
		
		System.out.println("@@@@@@ȸ������@@@@@@");
		
		
		
		//insert �ϱ� ���� vo ����
		//LoginVo vo = new LoginVo();
		
		//�̹� �ִ� ���̵����� ��ȸ�ϱ�
		list = LoginDao.getInstance().selectList();
		
		OUT1:
		while(true) {
			
			System.out.println("\nȸ�� ������ ID�� �Է��ϼ���.");
			System.out.print("ID >> ");
			String new_id = sc.nextLine();
			
			if(isKorean(new_id)) {
				System.out.println("\nID�Է½� �ѱ�, ������ �Է��� �Ұ����մϴ�.");
				continue OUT1;
			}
			
			
			
			LoginVo vo = LoginDao.getInstance().selectOneFromID(new_id);
			
			if(vo!=null) {
				System.out.println("\n�̹� �����ϴ� ���̵� �Դϴ�.");
				continue OUT1;
			}

			vo = new LoginVo();
			
			
			OUT2:
			while(true) {
				
				System.out.println("\n��й�ȣ�� �Է��ϼ���.");
				System.out.print("��й�ȣ >> ");
				String new_pwd = sc.nextLine();
				
				if(isKorean(new_pwd)) {
					System.out.println("\n��й�ȣ�� �ѱ�, ������ �Է��� �Ұ����մϴ�.");
					
					continue OUT2;
				}
				//��й�ȣ üũ �Ϸ������� ����
				vo.setPassword(new_pwd);
				break;
			}

			
			OUT3: 
			while (true) {
				System.out.println("\n�г����� �Է��ϼ���.");
				System.out.print("�г��� >> ");
				String new_nick = sc.nextLine();

				LoginVo vo2 = LoginDao.getInstance().selectOneFromNickname(new_nick);
				
				//vo2���� ���� �ƴϸ� �� ���̺� �����Ͱ� ������
				if(vo2!=null) {
					System.out.println("\n�̹� �����ϴ� �г��� �Դϴ�."); 
					continue OUT3;
				}

				//�г��� ����
				vo.setNickname(new_nick);
				break;

			}
			
			
			vo.setUserid(new_id);
			
			
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

			for(int i=0; i<5; i++){
				
			System.out.print("���̵� : \n");
			String id_check = sc.nextLine();
			System.out.print("��й�ȣ : \n");
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
						System.out.println("\n�α��� �Ϸ�");
						System.out.println();
						
						nickname = a.getNickname();
						ID = a.getUserid();
						
						
						System.out.printf("\n%s �� ȯ���մϴ�!! \n\n",nickname);
						
						main_main_disp();
						
						//��������Ǹ� �����ؼ� ȣ���� ������
						return;
						
					}
	
				} // id if - end;

			} // ȸ�� check ����

			System.out.printf("�α��� ���� %dȸ!! \n",i+1);
			System.out.println();
			
			if((i+1==5)) {
				System.out.println();System.out.println();
				System.out.println("�α��ο� �����Ͽ����ϴ�.");
				System.out.println();System.out.println();System.out.println();
			}
			
			
		} // �α��� for end;
		

	}

	private static void main_main_disp() {
		// TODO Auto-generated method stub
		
		System.out.println("\n\n����ȭ���Դϴ�.");
		System.out.println("���信 ���Ű� ȯ���մϴ�!!!!");
		System.out.println("�޴��� �����ϼ���");
		
		System.out.println("[1] ���ۿ�ȭ");
		System.out.println("[2] ���ۿ�ȭ");
		System.out.println("[3] ų��Ÿ�ӿ�ȭ");
		System.out.println("[4] �ƹ��ų�");
		System.out.println("[5] ��ò���");
		
		System.out.printf("[6] %s ���� ���� Ȯ���ϱ�\n",nickname);
		System.out.println("[7] �α׾ƿ�");
		
		
		while(true) {
			
			try {
				int res = sc.nextInt();
				
				sc.nextLine(); // �Է±���������
				if(res>=8) {
					System.out.println("�߸� �ԷµǾ����ϴ�.");
					continue;
				}	
				
				switch (res) {
				case 1: review_disp(); break;
				
				case 2: review_disp(); break;
				
				case 3: review_disp(); break;
				
				case 4: review_disp(); break;
				
				case 5: review_disp(); break;
				
				case 6: review_disp(); break;
				
				case 7: System.out.println("\n �α׾ƿ� �մϴ�.\n �̿��� �ּż� �����մϴ�."); return; 
				}
				
				
				
			} catch (Exception e) {
				System.out.println("�߸� �ԷµǾ����ϴ�.");
				sc = new Scanner(System.in);
			}

			
			
			
		}
		
		

		
	}

	private static void review_disp() {
		// TODO Auto-generated method stub

		while (true) {

			int idx = 1;

			String[] m = { "��ȭ����", "��ȭ ������� ��(�ִ�20��)", "ȫ�浿", "2022/05/11" };

			String[] menu = { "�۹�ȣ", "��ȭ����", "����", "�ۼ���", "�ۼ�����" };

			System.out.println(
					"- - - - + - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - + - - - - - - - -");
			System.out.printf(" %s | %8s      | %17s               | %4s   | %5s \n", menu[0], menu[1], menu[2],
					menu[3], menu[4]);

			System.out.println(
					"- - - - + - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - + - - - - - - - -");

			System.out.printf("   %04d | %13s | %21s | %6s | %11s \n", idx, m[0], m[1], m[2], m[3]);
			System.out.println(
					"- - - - + - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - + - - - - - - - -");

			System.out.println("1.�۾���\n2.����\n3.����\n4.���� ���� Ȯ��\n5.���� ȭ������..");

			int choice = sc.nextInt();

			if (choice == 5) {
				return;
			}

		} // ���ȭ�� while - end
		
		

	}

	//���̵� �˻� �޼ҵ� �ѱ���� ���Ⱑ ��������
	public static boolean isKorean(String str) {
		return Pattern.matches(".*[��-����-�Ӱ�-�R!?()\s]+.*", str.replace("\n", ""));
		
	}

	
	
	// ���θ޼ҵ� ����
	
	public static void main(String[] args) {

		main_login_disp(); // �� �Լ����� return�ϸ� ����

		System.out.println("���α׷� ����");

	}
}
