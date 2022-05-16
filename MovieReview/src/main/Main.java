package mymain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import HHHHH.LoginDao;
import HHHHH.MovieDao;
import HHHHH.ReviewDao;
import Vo.CategoryVo;
import Vo.LoginVo;
import Vo.MovieVo;
import Vo.ReviewVo;

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
				if (res >= 4) {
					System.out.println("�߸� �ԷµǾ����ϴ�.");

					continue;

				}
				if (res == 1) {

					login();

				} else if (res == 2) {

					sign_up();

				} else if (res == 3) {
					// ���α׷� ����
					return;
				}
			} catch (Exception e) {
				System.out.println("�߸� �ԷµǾ����ϴ�.");
				sc = new Scanner(System.in);
			}
		}

	}

	// ȸ������
	private static void sign_up() {

		System.out.println("@@@@@@ȸ������@@@@@@");

		// insert �ϱ� ���� vo ����
		// LoginVo vo = new LoginVo();

		// �̹� �ִ� ���̵����� ��ȸ�ϱ�
		list = LoginDao.getInstance().selectList();

		OUT1: while (true) {

			System.out.println("ȸ�������� �׸� �ν÷��� Y��, ��� �����Ͻ÷��� �ƹ�Ű�� ��������.");
			String yn = sc.nextLine();

			if (yn.equalsIgnoreCase("Y")) {
				return;
			}

			System.out.println("\nȸ�� ������ ID�� �Է��ϼ���.");
			System.out.print("ID >> ");
			String new_id = sc.nextLine();

			if (isKorean(new_id)) {
				System.out.println("\nID�Է½� �ѱ�, ������ �Է��� �Ұ����մϴ�.");
				System.out.println();
				continue OUT1;
			}

			LoginVo vo = LoginDao.getInstance().selectOneFromID(new_id);

			if (vo != null) {
				System.out.println("\n�̹� �����ϴ� ���̵� �Դϴ�.");
				continue OUT1;
			}

			vo = new LoginVo();

			OUT2: while (true) {

				System.out.println("\n��й�ȣ�� �Է��ϼ���.");
				System.out.print("��й�ȣ >> ");
				String new_pwd = sc.nextLine();

				if (isKorean(new_pwd)) {
					System.out.println("\n��й�ȣ�� �ѱ�, ������ �Է��� �Ұ����մϴ�.");

					continue OUT2;
				}
				// ��й�ȣ üũ �Ϸ������� ����
				vo.setPassword(new_pwd);
				break;
			}

			OUT3: while (true) {
				System.out.println("\n�г����� �Է��ϼ���.");
				System.out.print("�г��� >> ");
				String new_nick = sc.nextLine();

				LoginVo vo2 = LoginDao.getInstance().selectOneFromNickname(new_nick);

				// vo2���� ���� �ƴϸ� �� ���̺� �����Ͱ� ������
				if (vo2 != null) {
					System.out.println("\n�̹� �����ϴ� �г��� �Դϴ�.");
					continue OUT3;
				}

				// �г��� ����
				vo.setNickname(new_nick);
				break;

			}

			vo.setUserid(new_id);

			// �����͸� ��¥ �ذ�
			int res = LoginDao.getInstance().signUp_insert(vo);

			System.out.println("ȸ������ �Ϸ�!!");

			return;

		} // while - end;

	}

	private static void login() {
		System.out.println("               [�α���]");
		System.out.println();
		// ��ȸ�ϱ� ���� Dao��ü ����
		list = LoginDao.getInstance().selectList();

		for (int i = 0; i < 5; i++) {

			System.out.print("���̵� : ");
			String id_check = sc.nextLine();

			System.out.print("��й�ȣ : ");
			String pwd_check = sc.nextLine();

			System.out.println();

//			  ���̵� ��� �г��� 
//			  'id1'      '123123'   '�������5��' 
//			  'my123',   '1q2w3e4r' '���ָ���7����'
//			  'bonglove' 'bong123'  '����ȣ�����' 
//			  'kimchi12' 'kimchi12' 'kimchi12' 
//			  'gogo0325' '123123'   '��ȭ�л���'

			for (LoginVo a : list) {
				// ���̵� üũ
				if (id_check.equals(a.getUserid())) {
					// ���üũ
					if (pwd_check.equals(a.getPassword())) {
						System.out.println("\n�α��� �Ϸ�...!!");
						System.out.println();

						nickname = a.getNickname();
						ID = a.getUserid();

						System.out.printf("%s �� ȯ���մϴ�!! \n", nickname);

						main_main1_1_disp();

						// ��������Ǹ� �����ؼ� ȣ���� ������
						return;

					}

				} // id if - end;

			} // ȸ�� check ����

			System.out.printf("�α��� ���� %dȸ!! \n", i + 1);
			System.out.println();

			if ((i + 1 == 5)) {
				System.out.println();
				System.out.println();
				System.out.println("�α��ο� �����Ͽ����ϴ�.");
				System.out.println();
				System.out.println();
				System.out.println();
			}

		} // �α��� for end;

	}

	// ī�װ� ���÷���
	private static void main_main1_1_disp() {
		// TODO Auto-generated method stub

		while (true) {

			System.out.println("�������� ����ȭ��");
			System.out.println("���信 ���Ű��� ȯ���մϴ�.");
			System.out.println("��ȭ�帣�� �����ϼ���.");

			List<CategoryVo> list = MovieDao.getInstance().select_Category_List();

			for (CategoryVo vo : list) {

				System.out.println("[" + vo.getCateno() + "] " + vo.getCatename());

			}

			System.out.println("[7] ���� �� �� ��������.");
			System.out.println("[8] �α׾ƿ�");

			try {
				int choice = sc.nextInt();
				sc.nextLine();

				if (choice >= 9) {
					System.out.println("�߸� �ԷµǾ����ϴ�.");
					continue;

				}

				switch (choice) {
				case 1:
					main_main_disp(1);
					break;
				case 2:
					main_main_disp(2);
					break;
				case 3:
					main_main_disp(3);
					break;
				case 4:
					main_main_disp(4);
					break;
				case 5:
					main_main_disp(5);
					break;
				case 6:
					main_main_disp(6);
					break;
				case 7:
					review_disp_user();
				case 8:
					return;
				}

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�߸��� �Է��Դϴ�.");
				sc = new Scanner(System.in);

			}

		} // while - end

	}

	// ī�װ��� ��ȭ ���÷���
	private static void main_main_disp(int cateno) {
		// TODO Auto-generated method stub
		OUT: while (true) {
			// System.out.println("����ȭ���Դϴ�.");
			// System.out.println("���信 ���Ű� ȯ���մϴ�!!!!");
			System.out.println("��ȭ�� �����ϼ���");

			// ��ȭ���̺��� ��ȸ�ؼ� ������ 1~5������ ������ �о�ͼ� �������� ������ִ� ��
			// MovieVo vo = new MovieVo();
			List<MovieVo> movie_list = new ArrayList<MovieVo>();
			List<String> movie_list2 = new ArrayList<>();
			movie_list = MovieDao.getInstance().selectList(cateno);
			

			
			// ���̺��� �������� ��ȭ���� �о����
			for (MovieVo vo : movie_list) {

				System.out.println(vo.getTitle());
				movie_list2.add(vo.getTitle());
		
			}
			
			String res = sc.nextLine();

				if (movie_list2.contains(res)) {
					review_disp(res);
				} else {
		
					System.out.println("�帣�� ���Ե��� ���� ��ȭ�Դϴ�");
					break;
				}
			
				
			

		}

	}

//	private static void main_main_disp() {
//		// TODO Auto-generated method stub
//
//		while (true) {
//			System.out.println("����ȭ���Դϴ�.");
//			System.out.println("���信 ���Ű� ȯ���մϴ�!!!!");
//			System.out.println("�޴��� �����ϼ���");
//
//			// ��ȭ���̺��� ��ȸ�ؼ� ������ 1~5������ ������ �о�ͼ� �������� ������ִ� ��
//			// MovieVo vo = new MovieVo();
//			List<MovieVo> movie_list = new ArrayList<MovieVo>();
//			movie_list = MovieDao.getInstance().selectList();
//
//			// ���̺��� �������� ��ȭ���� �о����
//			for (MovieVo vo : movie_list) {
//				System.out.println("[" + vo.getMovieidx() + "] " + vo.getTitle());
//			}
//
//			/*
//			 * System.out.println("[1] ���ۿ�ȭ"); System.out.println("[2] ���ۿ�ȭ");
//			 * System.out.println("[3] ų��Ÿ�ӿ�ȭ"); System.out.println("[4] �ƹ��ų�");
//			 * System.out.println("[5] ��ò���");
//			 */
//
//			System.out.printf("[6] %s ���� ���� Ȯ���ϱ�\n", nickname);
//			System.out.println("[7] �α׾ƿ�");
//
//			try {
//				int res = sc.nextInt();
//
//				sc.nextLine(); // �Է±���������
//				if (res >= 8) {
//					System.out.println("�߸� �ԷµǾ����ϴ�.");
//					continue;
//				}
//
//				switch (res) {
//				case 1:
//					review_disp(1);
//					break;
//
//				case 2:
//					review_disp(2);
//					break;
//
//				case 3:
//					review_disp(3);
//					break;
//
//				case 4:
//					review_disp(4);
//					break;
//
//				case 5:
//					review_disp(5);
//					break;
//
//				// case 6: review_disp(6); break;
//				case 6:
//					review_disp_user();
//					break;
//
//				case 7:
//					System.out.println("\n �α׾ƿ� �մϴ�.\n �̿��� �ּż� �����մϴ�.");
//					return;
//				}
//
//			} catch (Exception e) {
//				System.out.println("�߸� �ԷµǾ����ϴ�.");
//				sc = new Scanner(System.in);
//			}
//
//		}
//
//	}

	// �α����� ����ڰ� �ۼ��� ���� ��ȸ�ϱ�
	private static void review_disp_user() {
		// TODO Auto-generated method stub

		while (true) {

			System.out.println(nickname + "���� �ۼ��� ���� ����Դϴ�.");

			String[] menu = { "�۹�ȣ", "��ȭ����", "����", "�ۼ���", "�ۼ�����" };

			System.out.println(
					"- - - - + - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - + - - - - - - - +");
			System.out.printf("   %s | %8s         | %17s                 | %4s     | %5s       |\n", menu[0], menu[1],
					menu[2], menu[3], menu[4]);
			System.out.println(
					"- - - - + - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - + - - - - - - - +");

			List<ReviewVo> select_review_list = new ArrayList<ReviewVo>();
			select_review_list = ReviewDao.getInstance().selectList_UserOnly(nickname);

			for (ReviewVo vo : select_review_list) {
				System.out.printf("   %04d | %13s     | %21s         | %6s   | %11s   |\n", vo.getGeulno(),
						vo.getTitle(), vo.getGeultext(), vo.getNickname(), vo.getGeulDate().substring(0, 10));
				System.out.println(
						"- - - - + - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - + - - - - - - - +");

			}

			System.out.println("1.�۾���\n2.����\n3.����\n4.���� ȭ������..");

			// �ٸ� ���ڸ� �Է�������, ��� ���� ����ó�� �ؾ���
			try {
				int choice = sc.nextInt();
				sc.nextLine();

				if (choice >= 5) {
					System.out.println("�߸� �ԷµǾ����ϴ�.");
					continue;
				}

				switch (choice) {
				case 1:
					write_review();
					break;
				case 2:
					update_review();
					break;
				case 3:
					delete_review();
					break;
				case 4:
					return;

				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�߸� �Է��Ͽ����ϴ�.");
				sc = new Scanner(System.in);
				continue;
			} // catch

		} // while - end

	}

	// ���ο��� �� ���丸 ���϶� �޼ҵ�
	private static void delete_review() {

		while (true) {

			System.out.println("������ ������ ��ȣ�� �Է��ϼ���");

			try {

				int geulno = sc.nextInt();
				int res = ReviewDao.getInstance().delete_review(geulno);

				System.out.println("���䰡 �����Ǿ����ϴ�.");

				return;

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�۹�ȣ�� �Է��� �ּ���.");
				sc = new Scanner(System.in);
				continue;
			}

		} // while - end

	}

	// �� ���丸 ���϶� �޼ҵ�
	private static void update_review() {

		while (true) {

			System.out.println("������ ������ �۹�ȣ�� �Է��ϼ���");

			try {

				int geulno = sc.nextInt();
				sc.nextLine();

				System.out.println("������ ���並 �����̳� �Է��ϼ���");
				String geultext = sc.nextLine();
				// ���� ��¥ ���ϱ�
				LocalDate now = LocalDate.now();
				// ���������ؼ� ���ϴ� ��¥�� ��������
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

				String geulDate = now.format(formatter);

				ReviewVo vo = new ReviewVo();

				vo.setGeultext(geultext);
				vo.setGeulDate(geulDate);
				vo.setGeulno(geulno);

				ReviewDao.getInstance().update_review(vo);

				System.out.println("���� ������ �Ϸ�ƽ��ϴ�.");
				System.out.println();

				return;

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�۹�ȣ�� �Է��� �ּ���.");
				sc = new Scanner(System.in);
				continue;

			}

		} // while - end

	}

	// ��ȭ��Ͽ����� �� ����� Ÿ���� ����� �����ϱ����� �޼ҵ�
	private static void update_onlymine() {

		OUT: while (true) {

			System.out.println("������ ������ �۹�ȣ�� �Է��ϼ���");

			try {

				int geulno = sc.nextInt();
				sc.nextLine();

				List<ReviewVo> list = ReviewDao.getInstance().selectList_UserOnly(geulno);

				for (ReviewVo a : list) {

					// ���̵� üũ
					if (nickname.equals(a.getNickname())) {

						System.out.println("������ ���並 �����̳� �Է��ϼ���");
						String geultext = sc.nextLine();
						// ���� ��¥ ���ϱ�
						LocalDate now = LocalDate.now();
						// ���������ؼ� ���ϴ� ��¥�� ��������
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

						String geulDate = now.format(formatter);

						ReviewVo vo = new ReviewVo();

						vo.setGeulno(geulno);
						vo.setGeultext(geultext);
						vo.setGeulDate(geulDate);

						int res = ReviewDao.getInstance().update_review(vo);

						System.out.println("���� ������ �Ϸ�ƽ��ϴ�." + res);
						System.out.println();
						break;

					} else {
						System.out.println("������ �ۼ��� ���� �ƴմϴ�.");
						continue OUT;
					}

				}

				// �� ���� �Ϸ��
				return;

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�۹�ȣ�� �Է��� �ּ���.");
				sc = new Scanner(System.in);
				continue;
			}

		} // while - end

	}

	// ��ȭ��Ͽ����� �� ����� Ÿ���� ����� �����ϱ����� �޼ҵ�
	private static void delete_onlymine() {

		OUT: while (true) {

			System.out.println("������ ������ �۹�ȣ�� �Է��ϼ���");

			try {

				int geulno = sc.nextInt();
				sc.nextLine();

				// geulno�� �����ϴ� nickname�� ����ִ� list
				List<ReviewVo> list = ReviewDao.getInstance().selectList_UserOnly(geulno);
				for (ReviewVo a : list) {
					if (nickname.equals(a.getNickname())) {

						int res = ReviewDao.getInstance().delete_review(geulno);
						System.out.println("���䰡 �����ƽ��ϴ�.");
						break;

					} else {
						System.out.println("������ �ۼ��� ���� �ƴմϴ�.");
						continue OUT;
					}

				} // for �� ��

				// �� ���� �Ϸ��
				break;

			} catch (Exception e) {
				System.out.println("���ڸ� �Է����ּ���.");
				sc = new Scanner(System.in);
				continue;
				// TODO: handle exception
			}

		} // while end

	}

	// ���� ����Ʈ ��ȸ�ϱ�
	private static void review_disp(String title) {
		// TODO Auto-generated method stub

		while (true) {

			int idx = 0;

			// String[] m = { "��ȭ����", "��ȭ ������� ��(�ִ�20��)", "ȫ�浿", "2022-05-11" };

			String[] menu = { "�۹�ȣ", "��ȭ����", "����", "�ۼ���", "�ۼ�����" };

			System.out.println(
					"- - - - + - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - + - - - - - - - +");
			System.out.printf("   %s | %8s         | %17s                 | %4s     | %5s       |\n", menu[0], menu[1],
					menu[2], menu[3], menu[4]);

			System.out.println(
					"- - - - + - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - + - - - - - - - +");

			// ���ڷ� ���� ��ȭ����(title)�� ���� ���� ���ڵ� ��ȸ�ϱ�
			// String title2 = ReviewDao.getInstance().selectList(title);

			List<ReviewVo> select_review_list = new ArrayList<ReviewVo>();
			select_review_list = ReviewDao.getInstance().selectList(title);

			for (ReviewVo vo : select_review_list) {
				System.out.printf("   %04d | %13s     | %21s         | %10s   | %13s   |\n", vo.getGeulno(),
						vo.getTitle(), vo.getGeultext(), vo.getNickname(), vo.getGeulDate().substring(0, 10));
				System.out.println(
						"- - - - + - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - + - - - - - - - +");

			}

			System.out.println("1.�۾���\n2.����\n3.����\n4.���� ���� Ȯ��\n5.���� ȭ������..");

			try {

				int choice = sc.nextInt();
				sc.nextLine();

				if (choice >= 6) {
					System.out.println("�߸� �Է��Ͽ����ϴ�.");
					continue;
				}

				switch (choice) {
				case 1:
					write_review();
					break;
				case 2:
					update_onlymine();
					break;
				case 3:
					delete_onlymine();
					break;
				case 4:
					review_disp_user();
					break;
				case 5:
					return;

				}

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�߸� �Է��Ͽ����ϴ�.");
				sc = new Scanner(System.in);
				continue;
			}

		} // ���ȭ�� while - end

	}

	// ���� �ۼ��ؼ� �����ϴ� �κ�
	private static void write_review() {
		// TODO Auto-generated method stub
		String title;
		String geulText;
		String userId = ID;
		String geulDate;
		int movieNo;

		// ���� ��¥ ���ϱ�
		LocalDate now = LocalDate.now();
		// ���������ؼ� ���ϴ� ��¥�� ��������
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		geulDate = now.format(formatter);

		System.out.println("�ۼ��� ���ϴ� ��ȭ ������ �Է��ϼ��� >> ");
		title = sc.nextLine();
		// System.out.println(title);
		movieNo = MovieDao.getInstance().selectMovieNo(title);

		System.out.print("\n��ȭ�� ���� ���並 �ۼ����ּ���.��, ���ڼ��� 20�ڸ� ���� �� �����ϴ�.");
		geulText = sc.nextLine();

		ReviewVo vo = new ReviewVo(movieNo, geulText, userId, geulDate);

		int res = ReviewDao.getInstance().insert_review(vo);

		if (res == 0) {
			System.out.println("�� ���� ����!");
		} else {
			System.out.println("�� ���� ����!");
		}

		// System.out.println(movieNo);
		// System.out.println(geulDate);

	}

	// ���̵� �˻� �޼ҵ� �ѱ���� ���Ⱑ ��������
	public static boolean isKorean(String str) {
		return Pattern.matches(".*[��-����-�Ӱ�-�R!?()\s]+.*", str.replace("\n", ""));

	}

	// ���θ޼ҵ� ����

	public static void main(String[] args) {

		main_login_disp(); // �� �Լ����� return�ϸ� ����

		System.out.println("���α׷� ����");

	}
}
