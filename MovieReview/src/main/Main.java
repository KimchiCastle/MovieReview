package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import dao.LoginDao;
import dao.MovieDao;
import dao.ReviewDao;
import vo.CategoryVo;
import vo.LoginVo;
import vo.MovieVo;
import vo.ReviewVo;

public class Main {

	static Scanner sc = new Scanner(System.in);

	static String nickname;
	static String ID;

	static List<LoginVo> list = null;

	private static void main_login_disp() {

		while (true) {

			System.out.println("¡Ù¡Ú¡Ù¡Ú¡Ù¡Ú¡Ù¡Ú¡Ù¡Ú¹´½ä¡Ù¡Ú¡Ù¡Ú¡Ù¡Ú¡Ù¡Ú¡Ù¡Ú");
			System.out.println("·Î±×ÀÎ [1] ");
			System.out.println("È¸¿ø °¡ÀÔ[2]");
			System.out.println("³¡³»±â[3]");

			try {
				int res = sc.nextInt();

				sc.nextLine(); // ÀÔ·Â±¸ºĞÀÚÁ¦°Å
				if (res >= 4) {
					System.out.println("Àß¸ø ÀÔ·ÂµÇ¾ú½À´Ï´Ù.");

					continue;

				}
				if (res == 1) {

					login();

				} else if (res == 2) {

					sign_up();

				} else if (res == 3) {
					// ÇÁ·Î±×·¥ Á¾·á
					return;
				}
			} catch (Exception e) {
				System.out.println("Àß¸ø ÀÔ·ÂµÇ¾ú½À´Ï´Ù.");
				sc = new Scanner(System.in);
			}
		}

	}

	// È¸¿ø°¡ÀÔ
	private static void sign_up() {

		System.out.println("@@@@@@È¸¿ø°¡ÀÔ@@@@@@");

		// insert ÇÏ±â À§ÇÑ vo »ı¼º
		// LoginVo vo = new LoginVo();

		// ÀÌ¹Ì ÀÖ´Â ¾ÆÀÌµğÀÎÁö Á¶È¸ÇÏ±â
		list = LoginDao.getInstance().selectList();

		OUT1: while (true) {

			System.out.println("È¸¿ø°¡ÀÔÀ» ±×¸¸ µÎ½Ã·Á¸é Y¸¦, °è¼Ó ÁøÇàÇÏ½Ã·Á¸é ¾Æ¹«Å°¸¦ ´©¸£¼¼¿ä.");
			String yn = sc.nextLine();

			if (yn.equalsIgnoreCase("Y")) {
				return;
			}

			System.out.println("\nÈ¸¿ø °¡ÀÔÇÒ ID¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
			System.out.print("ID >> ");
			String new_id = sc.nextLine();

			if (isKorean(new_id)) {
				System.out.println("\nIDÀÔ·Â½Ã ÇÑ±Û, °ø¹éÀº ÀÔ·ÂÀÌ ºÒ°¡´ÉÇÕ´Ï´Ù.");
				System.out.println();
				continue OUT1;
			}

			LoginVo vo = LoginDao.getInstance().selectOneFromID(new_id);

			if (vo != null) {
				System.out.println("\nÀÌ¹Ì Á¸ÀçÇÏ´Â ¾ÆÀÌµğ ÀÔ´Ï´Ù.");
				continue OUT1;
			}

			vo = new LoginVo();

			OUT2: while (true) {

				System.out.println("\nºñ¹Ğ¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
				System.out.print("ºñ¹Ğ¹øÈ£ >> ");
				String new_pwd = sc.nextLine();

				if (isKorean(new_pwd)) {
					System.out.println("\nºñ¹Ğ¹øÈ£´Â ÇÑ±Û, °ø¹éÀº ÀÔ·ÂÀÌ ºÒ°¡´ÉÇÕ´Ï´Ù.");

					continue OUT2;
				}
				// ºñ¹Ğ¹øÈ£ Ã¼Å© ¿Ï·áÇßÀ¸¸é Æ÷Àå
				vo.setPassword(new_pwd);
				break;
			}

			OUT3: while (true) {
				System.out.println("\n´Ğ³×ÀÓÀ» ÀÔ·ÂÇÏ¼¼¿ä.");
				System.out.print("´Ğ³×ÀÓ >> ");
				String new_nick = sc.nextLine();

				LoginVo vo2 = LoginDao.getInstance().selectOneFromNickname(new_nick);

				// vo2°ªÀÌ ³ÎÀÌ ¾Æ´Ï¸é Áï Å×ÀÌºí¿¡ µ¥ÀÌÅÍ°¡ ÀÖÀ¸¸é
				if (vo2 != null) {
					System.out.println("\nÀÌ¹Ì Á¸ÀçÇÏ´Â ´Ğ³×ÀÓ ÀÔ´Ï´Ù.");
					continue OUT3;
				}

				// ´Ğ³×ÀÓ Æ÷Àå
				vo.setNickname(new_nick);
				break;

			}

			vo.setUserid(new_id);

			// µ¥ÀÌÅÍ¸¦ ÁøÂ¥ ÁØ°Í
			int res = LoginDao.getInstance().signUp_insert(vo);

			System.out.println("È¸¿ø°¡ÀÔ ¿Ï·á!!");

			return;

		} // while - end;

	}

	private static void login() {
		System.out.println("               [·Î±×ÀÎ]");
		System.out.println();
		// Á¶È¸ÇÏ±â À§ÇØ Dao°´Ã¼ »ı¼º
		list = LoginDao.getInstance().selectList();

		for (int i = 0; i < 5; i++) {

			System.out.print("¾ÆÀÌµğ : ");
			String id_check = sc.nextLine();

			System.out.print("ºñ¹Ğ¹øÈ£ : ");
			String pwd_check = sc.nextLine();

			System.out.println();

//			  ¾ÆÀÌµğ ºñ¹ø ´Ğ³×ÀÓ 
//			  'id1'      '123123'   '¹ÚÆò½ÄÀÇ5Á¡' 
//			  'my123',   '1q2w3e4r' '¿ìÁÖ¸íÀÛ7±¤±¸'
//			  'bonglove' 'bong123'  'ºÀÁØÈ£»ç¶ûÇØ' 
//			  'kimchi12' 'kimchi12' 'kimchi12' 
//			  'gogo0325' '123123'   '¿µÈ­ÇĞ»ìÀÚ'

			for (LoginVo a : list) {
				// ¾ÆÀÌµğ Ã¼Å©
				if (id_check.equals(a.getUserid())) {
					// ºñ¹øÃ¼Å©
					if (pwd_check.equals(a.getPassword())) {
						System.out.println("\n·Î±×ÀÎ ¿Ï·á...!!");
						System.out.println();

						nickname = a.getNickname();
						ID = a.getUserid();

						System.out.printf("%s ´Ô È¯¿µÇÕ´Ï´Ù!! \n", nickname);

						main_main1_1_disp();

						// ¸ŞÀÎÁ¾·áµÇ¸é ¸®ÅÏÇØ¼­ È£ÃâÇÑ °÷À¸·Î
						return;

					}

				} // id if - end;

			} // È¸¿ø check Á¾·á

			System.out.printf("·Î±×ÀÎ ½ÇÆĞ %dÈ¸!! \n", i + 1);
			System.out.println();

			if ((i + 1 == 5)) {
				System.out.println();
				System.out.println();
				System.out.println("·Î±×ÀÎ¿¡ ½ÇÆĞÇÏ¿´½À´Ï´Ù.");
				System.out.println();
				System.out.println();
				System.out.println();
			}

		} // ·Î±×ÀÎ for end;

	}

	// Ä«Å×°í¸® µğ½ºÇÃ·¹ÀÌ
	private static void main_main1_1_disp() {
		// TODO Auto-generated method stub

		while (true) {

			System.out.println("°ø»çÁßÀÎ ¸ŞÀÎÈ­¸é");
			System.out.println("¹´½ä¿¡ ¿À½Å°ÍÀ» È¯¿µÇÕ´Ï´Ù.");
			System.out.println("¿µÈ­Àå¸£¸¦ ¼±ÅÃÇÏ¼¼¿ä.");

			List<CategoryVo> list = MovieDao.getInstance().select_Category_List();

			for (CategoryVo vo : list) {

				System.out.println("[" + vo.getCateno() + "] " + vo.getCatename());

			}

			System.out.println("[7] ³»°¡ ¾´ ±Û º¸·¯°¡±â.");
			System.out.println("[8] ·Î±×¾Æ¿ô");

			try {
				int choice = sc.nextInt();
				sc.nextLine();

				if (choice >= 9) {
					System.out.println("Àß¸ø ÀÔ·ÂµÇ¾ú½À´Ï´Ù.");
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
				System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
				sc = new Scanner(System.in);

			}

		} // while - end

	}

	// Ä«Å×°í¸®ÀÇ ¿µÈ­ µğ½ºÇÃ·¹ÀÌ
	private static void main_main_disp(int cateno) {
		// TODO Auto-generated method stub
		OUT: while (true) {
			// System.out.println("¸ŞÀÎÈ­¸éÀÔ´Ï´Ù.");
			// System.out.println("¹´½ä¿¡ ¿À½Å°É È¯¿µÇÕ´Ï´Ù!!!!");
			System.out.println("¿µÈ­¸¦ ¼±ÅÃÇÏ¼¼¿ä");

			// ¿µÈ­Å×ÀÌºíÀ» Á¶È¸ÇØ¼­ ³»¿ëÀ» 1~5±îÁöÀÇ ³»¿ëÀ» ÀĞ¾î¿Í¼­ µ¿ÀûÀ¸·Î Ãâ·ÂÇØÁÖ´Â °Å
			// MovieVo vo = new MovieVo();
			List<MovieVo> movie_list = new ArrayList<MovieVo>();
			List<String> movie_list2 = new ArrayList<>();
			movie_list = MovieDao.getInstance().selectList(cateno);
			

			
			// Å×ÀÌºí¿¡¼­ µ¿ÀûÀ¸·Î ¿µÈ­Á¦¸ñ ÀĞ¾î¿À±â
			for (MovieVo vo : movie_list) {

				System.out.println(vo.getTitle());
				movie_list2.add(vo.getTitle());
		
			}
			
			String res = sc.nextLine();

				if (movie_list2.contains(res)) {
					review_disp(res);
				} else {
		
					System.out.println("Àå¸£¿¡ Æ÷ÇÔµÇÁö ¾ÊÀº ¿µÈ­ÀÔ´Ï´Ù");
					break;
				}
			
				
			

		}

	}

//	private static void main_main_disp() {
//		// TODO Auto-generated method stub
//
//		while (true) {
//			System.out.println("¸ŞÀÎÈ­¸éÀÔ´Ï´Ù.");
//			System.out.println("¹´½ä¿¡ ¿À½Å°É È¯¿µÇÕ´Ï´Ù!!!!");
//			System.out.println("¸Ş´º¸¦ ¼±ÅÃÇÏ¼¼¿ä");
//
//			// ¿µÈ­Å×ÀÌºíÀ» Á¶È¸ÇØ¼­ ³»¿ëÀ» 1~5±îÁöÀÇ ³»¿ëÀ» ÀĞ¾î¿Í¼­ µ¿ÀûÀ¸·Î Ãâ·ÂÇØÁÖ´Â °Å
//			// MovieVo vo = new MovieVo();
//			List<MovieVo> movie_list = new ArrayList<MovieVo>();
//			movie_list = MovieDao.getInstance().selectList();
//
//			// Å×ÀÌºí¿¡¼­ µ¿ÀûÀ¸·Î ¿µÈ­Á¦¸ñ ÀĞ¾î¿À±â
//			for (MovieVo vo : movie_list) {
//				System.out.println("[" + vo.getMovieidx() + "] " + vo.getTitle());
//			}
//
//			/*
//			 * System.out.println("[1] ¸íÀÛ¿µÈ­"); System.out.println("[2] Á¹ÀÛ¿µÈ­");
//			 * System.out.println("[3] Å³¸µÅ¸ÀÓ¿µÈ­"); System.out.println("[4] ¾Æ¹«°Å³ª");
//			 * System.out.println("[5] ¸ğ½Ã²¤ÀÌ");
//			 */
//
//			System.out.printf("[6] %s ´ÔÀÌ ¾´±Û È®ÀÎÇÏ±â\n", nickname);
//			System.out.println("[7] ·Î±×¾Æ¿ô");
//
//			try {
//				int res = sc.nextInt();
//
//				sc.nextLine(); // ÀÔ·Â±¸ºĞÀÚÁ¦°Å
//				if (res >= 8) {
//					System.out.println("Àß¸ø ÀÔ·ÂµÇ¾ú½À´Ï´Ù.");
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
//					System.out.println("\n ·Î±×¾Æ¿ô ÇÕ´Ï´Ù.\n ÀÌ¿ëÇØ ÁÖ¼Å¼­ °¨»çÇÕ´Ï´Ù.");
//					return;
//				}
//
//			} catch (Exception e) {
//				System.out.println("Àß¸ø ÀÔ·ÂµÇ¾ú½À´Ï´Ù.");
//				sc = new Scanner(System.in);
//			}
//
//		}
//
//	}

	// ·Î±×ÀÎÇÑ »ç¿ëÀÚ°¡ ÀÛ¼ºÇÑ ¸®ºä Á¶È¸ÇÏ±â
	private static void review_disp_user() {
		// TODO Auto-generated method stub

		while (true) {

			System.out.println(nickname + "´ÔÀÌ ÀÛ¼ºÇÑ ¸®ºä ¸ñ·ÏÀÔ´Ï´Ù.");

			String[] menu = { "±Û¹øÈ£", "¿µÈ­Á¦¸ñ", "³»¿ë", "ÀÛ¼ºÀÚ", "ÀÛ¼ºÀÏÀÚ" };

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

			System.out.println("1.±Û¾²±â\n2.¼öÁ¤\n3.»èÁ¦\n4.ÀÌÀü È­¸éÀ¸·Î..");

			// ´Ù¸¥ ¼ıÀÚ¸¦ ÀÔ·ÂÇßÀ»¶§, ¾î¶»°Ô ÇÒÁö ¿¡·¯Ã³¸® ÇØ¾ßÇÔ
			try {
				int choice = sc.nextInt();
				sc.nextLine();

				if (choice >= 5) {
					System.out.println("Àß¸ø ÀÔ·ÂµÇ¾ú½À´Ï´Ù.");
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
				System.out.println("Àß¸ø ÀÔ·ÂÇÏ¿´½À´Ï´Ù.");
				sc = new Scanner(System.in);
				continue;
			} // catch

		} // while - end

	}

	// ¸ŞÀÎ¿¡¼­ ³» ¸®ºä¸¸ º¸ÀÏ¶§ ¸Ş¼Òµå
	private static void delete_review() {

		while (true) {

			System.out.println("»èÁ¦ÇÒ ¸®ºäÀÇ ¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä");

			try {

				int geulno = sc.nextInt();
				int res = ReviewDao.getInstance().delete_review(geulno);

				System.out.println("¸®ºä°¡ »èÁ¦µÇ¾ú½À´Ï´Ù.");

				return;

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("±Û¹øÈ£¸¸ ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
				sc = new Scanner(System.in);
				continue;
			}

		} // while - end

	}

	// ³» ¸®ºä¸¸ º¸ÀÏ¶§ ¸Ş¼Òµå
	private static void update_review() {

		while (true) {

			System.out.println("¼öÁ¤ÇÒ ¸®ºäÀÇ ±Û¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä");

			try {

				int geulno = sc.nextInt();
				sc.nextLine();

				System.out.println("¼öÁ¤ÇÒ ¸®ºä¸¦ ÇÑÁÙÀÌ³» ÀÔ·ÂÇÏ¼¼¿ä");
				String geultext = sc.nextLine();
				// ÇöÀç ³¯Â¥ ±¸ÇÏ±â
				LocalDate now = LocalDate.now();
				// Æ÷¸ËÀû¿ëÇØ¼­ ¿øÇÏ´Â ³¯Â¥¸¸ °¡Á®¿À±â
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

				String geulDate = now.format(formatter);

				ReviewVo vo = new ReviewVo();

				vo.setGeultext(geultext);
				vo.setGeulDate(geulDate);
				vo.setGeulno(geulno);

				ReviewDao.getInstance().update_review(vo);

				System.out.println("¸®ºä ¼öÁ¤ÀÌ ¿Ï·áµÆ½À´Ï´Ù.");
				System.out.println();

				return;

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("±Û¹øÈ£¸¸ ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
				sc = new Scanner(System.in);
				continue;

			}

		} // while - end

	}

	// ¿µÈ­¸ñ·Ï¿¡¼­ÀÇ ³» ¸®ºä¿Í Å¸À¯Àú ¸®ºä¿Í ±¸º°ÇÏ±âÀ§ÇÑ ¸Ş¼Òµå
	private static void update_onlymine() {

		OUT: while (true) {

			System.out.println("¼öÁ¤ÇÒ ¸®ºäÀÇ ±Û¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä");

			try {

				int geulno = sc.nextInt();
				sc.nextLine();

				List<ReviewVo> list = ReviewDao.getInstance().selectList_UserOnly(geulno);

				for (ReviewVo a : list) {

					// ¾ÆÀÌµğ Ã¼Å©
					if (nickname.equals(a.getNickname())) {

						System.out.println("¼öÁ¤ÇÒ ¸®ºä¸¦ ÇÑÁÙÀÌ³» ÀÔ·ÂÇÏ¼¼¿ä");
						String geultext = sc.nextLine();
						// ÇöÀç ³¯Â¥ ±¸ÇÏ±â
						LocalDate now = LocalDate.now();
						// Æ÷¸ËÀû¿ëÇØ¼­ ¿øÇÏ´Â ³¯Â¥¸¸ °¡Á®¿À±â
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

						String geulDate = now.format(formatter);

						ReviewVo vo = new ReviewVo();

						vo.setGeulno(geulno);
						vo.setGeultext(geultext);
						vo.setGeulDate(geulDate);

						int res = ReviewDao.getInstance().update_review(vo);

						System.out.println("¸®ºä ¼öÁ¤ÀÌ ¿Ï·áµÆ½À´Ï´Ù." + res);
						System.out.println();
						break;

					} else {
						System.out.println("º»ÀÎÀÌ ÀÛ¼ºÇÑ ±ÛÀÌ ¾Æ´Õ´Ï´Ù.");
						continue OUT;
					}

				}

				// ±Û ¼öÁ¤ ¿Ï·á½Ã
				return;

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("±Û¹øÈ£¸¸ ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
				sc = new Scanner(System.in);
				continue;
			}

		} // while - end

	}

	// ¿µÈ­¸ñ·Ï¿¡¼­ÀÇ ³» ¸®ºä¿Í Å¸À¯Àú ¸®ºä¿Í ±¸º°ÇÏ±âÀ§ÇÑ ¸Ş¼Òµå
	private static void delete_onlymine() {

		OUT: while (true) {

			System.out.println("»èÁ¦ÇÒ ¸®ºäÀÇ ±Û¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä");

			try {

				int geulno = sc.nextInt();
				sc.nextLine();

				// geulno¿¡ ºÎÇÕÇÏ´Â nicknameÀÌ µé¾îÀÖ´Â list
				List<ReviewVo> list = ReviewDao.getInstance().selectList_UserOnly(geulno);
				for (ReviewVo a : list) {
					if (nickname.equals(a.getNickname())) {

						int res = ReviewDao.getInstance().delete_review(geulno);
						System.out.println("¸®ºä°¡ »èÁ¦µÆ½À´Ï´Ù.");
						break;

					} else {
						System.out.println("º»ÀÎÀÌ ÀÛ¼ºÇÑ ±ÛÀÌ ¾Æ´Õ´Ï´Ù.");
						continue OUT;
					}

				} // for ¹® ³¡

				// ±Û »èÁ¦ ¿Ï·á½Ã
				break;

			} catch (Exception e) {
				System.out.println("¼ıÀÚ¸¸ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				sc = new Scanner(System.in);
				continue;
				// TODO: handle exception
			}

		} // while end

	}

	// ¹«ºñ ¸®½ºÆ® Á¶È¸ÇÏ±â
	private static void review_disp(String title) {
		// TODO Auto-generated method stub

		while (true) {

			int idx = 0;

			// String[] m = { "¿µÈ­Á¦¸ñ", "¿µÈ­ ÇÑÁÙÆòµé¾î°¥ °÷(ÃÖ´ë20ÀÚ)", "È«±æµ¿", "2022-05-11" };

			String[] menu = { "±Û¹øÈ£", "¿µÈ­Á¦¸ñ", "³»¿ë", "ÀÛ¼ºÀÚ", "ÀÛ¼ºÀÏÀÚ" };

			System.out.println(
					"- - - - + - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - + - - - - - - - +");
			System.out.printf("   %s | %8s         | %17s                 | %4s     | %5s       |\n", menu[0], menu[1],
					menu[2], menu[3], menu[4]);

			System.out.println(
					"- - - - + - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - + - - - - - - - +");

			// ÀÎÀÚ·Î ¹ŞÀº ¿µÈ­Á¦¸ñ(title)°ú °°Àº ¸®ºä ·¹ÄÚµå Á¶È¸ÇÏ±â
			// String title2 = ReviewDao.getInstance().selectList(title);

			List<ReviewVo> select_review_list = new ArrayList<ReviewVo>();
			select_review_list = ReviewDao.getInstance().selectList(title);

			for (ReviewVo vo : select_review_list) {
				System.out.printf("   %04d | %13s     | %21s         | %10s   | %13s   |\n", vo.getGeulno(),
						vo.getTitle(), vo.getGeultext(), vo.getNickname(), vo.getGeulDate().substring(0, 10));
				System.out.println(
						"- - - - + - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - + - - - - - - - +");

			}

			System.out.println("1.±Û¾²±â\n2.¼öÁ¤\n3.»èÁ¦\n4.³»°¡ ¾´±Û È®ÀÎ\n5.ÀÌÀü È­¸éÀ¸·Î..");

			try {

				int choice = sc.nextInt();
				sc.nextLine();

				if (choice >= 6) {
					System.out.println("Àß¸ø ÀÔ·ÂÇÏ¿´½À´Ï´Ù.");
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
				System.out.println("Àß¸ø ÀÔ·ÂÇÏ¿´½À´Ï´Ù.");
				sc = new Scanner(System.in);
				continue;
			}

		} // Ãâ·ÂÈ­¸é while - end

	}

	// ¸®ºä ÀÛ¼ºÇØ¼­ »ğÀÔÇÏ´Â ºÎºĞ
	private static void write_review() {
		// TODO Auto-generated method stub
		String title;
		String geulText;
		String userId = ID;
		String geulDate;
		int movieNo;

		// ÇöÀç ³¯Â¥ ±¸ÇÏ±â
		LocalDate now = LocalDate.now();
		// Æ÷¸ËÀû¿ëÇØ¼­ ¿øÇÏ´Â ³¯Â¥¸¸ °¡Á®¿À±â
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		geulDate = now.format(formatter);

		System.out.println("ÀÛ¼ºÀ» ¿øÇÏ´Â ¿µÈ­ Á¦¸ñÀ» ÀÔ·ÂÇÏ¼¼¿ä >> ");
		title = sc.nextLine();
		// System.out.println(title);
		movieNo = MovieDao.getInstance().selectMovieNo(title);

		System.out.print("\n¿µÈ­¿¡ ´ëÇÑ ¸®ºä¸¦ ÀÛ¼ºÇØÁÖ¼¼¿ä.´Ü, ±ÛÀÚ¼ö´Â 20ÀÚ¸¦ ³ÑÀ» ¼ö ¾ø½À´Ï´Ù.");
		geulText = sc.nextLine();

		ReviewVo vo = new ReviewVo(movieNo, geulText, userId, geulDate);

		int res = ReviewDao.getInstance().insert_review(vo);

		if (res == 0) {
			System.out.println("±Û ¾²±â ½ÇÆĞ!");
		} else {
			System.out.println("±Û ¾²±â ¼º°ø!");
		}

		// System.out.println(movieNo);
		// System.out.println(geulDate);

	}

	// ¾ÆÀÌµğ °Ë»ç ¸Ş¼Òµå ÇÑ±¹¾î¿Í ¶ç¾î¾²±â°¡ µé¾î¿ÔÀ¸¸é
	public static boolean isKorean(String str) {
		return Pattern.matches(".*[¤¡-¤¾¤¿-¤Ó°¡-ÆR!?()\s]+.*", str.replace("\n", ""));

	}

	// ¸ŞÀÎ¸Ş¼Òµå ½ÃÀÛ

	public static void main(String[] args) {

		main_login_disp(); // ÀÌ ÇÔ¼ö¿¡¼­ returnÇÏ¸é ³¡³²

		System.out.println("ÇÁ·Î±×·¥ Á¾·á");

	}
}
