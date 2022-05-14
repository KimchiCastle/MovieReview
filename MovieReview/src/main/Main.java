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
				
				sc.nextLine(); // ÀÔ·Â±¸ºÐÀÚÁ¦°Å
				if(res>=4) {
					System.out.println("Àß¸ø ÀÔ·ÂµÇ¾ú½À´Ï´Ù.");
					
					continue;
					
				}	
				if (res == 1) {
					
					login();
					
				} else if (res == 2) {
					
					sign_up();
					
					
				} else if (res == 3) {
					//ÇÁ·Î±×·¥ Á¾·á
					return;
				}
			} catch (Exception e) {
				System.out.println("Àß¸ø ÀÔ·ÂµÇ¾ú½À´Ï´Ù.");
				sc = new Scanner(System.in);
			}
		}

	}
	//È¸¿ø°¡ÀÔ
	private static void sign_up() {
		
		System.out.println("@@@@@@È¸¿ø°¡ÀÔ@@@@@@");
		
		
		
		//insert ÇÏ±â À§ÇÑ vo »ý¼º
		//LoginVo vo = new LoginVo();
		
		//ÀÌ¹Ì ÀÖ´Â ¾ÆÀÌµðÀÎÁö Á¶È¸ÇÏ±â
		list = LoginDao.getInstance().selectList();
		
		OUT1:
		while(true) {
			
			System.out.println("\nÈ¸¿ø °¡ÀÔÇÒ ID¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
			System.out.print("ID >> ");
			String new_id = sc.nextLine();
			
			if(isKorean(new_id)) {
				System.out.println("\nIDÀÔ·Â½Ã ÇÑ±Û, °ø¹éÀº ÀÔ·ÂÀÌ ºÒ°¡´ÉÇÕ´Ï´Ù.");
				continue OUT1;
			}
			
			
			
			LoginVo vo = LoginDao.getInstance().selectOneFromID(new_id);
			
			if(vo!=null) {
				System.out.println("\nÀÌ¹Ì Á¸ÀçÇÏ´Â ¾ÆÀÌµð ÀÔ´Ï´Ù.");
				continue OUT1;
			}

			vo = new LoginVo();
			
			
			OUT2:
			while(true) {
				
				System.out.println("\nºñ¹Ð¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
				System.out.print("ºñ¹Ð¹øÈ£ >> ");
				String new_pwd = sc.nextLine();
				
				if(isKorean(new_pwd)) {
					System.out.println("\nºñ¹Ð¹øÈ£´Â ÇÑ±Û, °ø¹éÀº ÀÔ·ÂÀÌ ºÒ°¡´ÉÇÕ´Ï´Ù.");
					
					continue OUT2;
				}
				//ºñ¹Ð¹øÈ£ Ã¼Å© ¿Ï·áÇßÀ¸¸é Æ÷Àå
				vo.setPassword(new_pwd);
				break;
			}

			
			OUT3: 
			while (true) {
				System.out.println("\n´Ð³×ÀÓÀ» ÀÔ·ÂÇÏ¼¼¿ä.");
				System.out.print("´Ð³×ÀÓ >> ");
				String new_nick = sc.nextLine();

				LoginVo vo2 = LoginDao.getInstance().selectOneFromNickname(new_nick);
				
				//vo2°ªÀÌ ³ÎÀÌ ¾Æ´Ï¸é Áï Å×ÀÌºí¿¡ µ¥ÀÌÅÍ°¡ ÀÖÀ¸¸é
				if(vo2!=null) {
					System.out.println("\nÀÌ¹Ì Á¸ÀçÇÏ´Â ´Ð³×ÀÓ ÀÔ´Ï´Ù."); 
					continue OUT3;
				}

				//´Ð³×ÀÓ Æ÷Àå
				vo.setNickname(new_nick);
				break;

			}
			
			
			vo.setUserid(new_id);
			
			
			int res = LoginDao.getInstance().signUp_insert(vo);
			
			System.out.println("È¸¿ø°¡ÀÔ ¿Ï·á!!" + res);
			
			return;
			
			} // while - end;

	}

	private static void login() {
		System.out.println("               [·Î±×ÀÎ]");
		System.out.println();
		//Á¶È¸ÇÏ±â À§ÇØ Dao°´Ã¼ »ý¼º
		list = LoginDao.getInstance().selectList();

			for(int i=0; i<5; i++){
				
			System.out.print("¾ÆÀÌµð : \n");
			String id_check = sc.nextLine();
			System.out.print("ºñ¹Ð¹øÈ£ : \n");
			String pwd_check = sc.nextLine();

			/*
			 * ¾ÆÀÌµð	   ºñ¹ø			´Ð³×ÀÓ	
			 * 'id1',	   '123123' 	'¹ÚÆò½ÄÀÇ5Á¡'
			 *  'my123',,  '1q2w3e4r' 	'¿ìÁÖ¸íÀÛ7±¤±¸'
			 *  'bonglove','bong123' 	'ºÀÁØÈ£»ç¶ûÇØ'
			 *  'kimchi12' 'kimchi12'	'kimchi12'
			 *  'gogo0325' '123123'		'¿µÈ­ÇÐ»ìÀÚ'
			 */
			

			for (LoginVo a : list) {
				//¾ÆÀÌµð Ã¼Å©
				if (id_check.equals(a.getUserid())) {
					//ºñ¹øÃ¼Å©
					if(pwd_check.equals(a.getPassword())) {
						System.out.println("\n·Î±×ÀÎ ¿Ï·á");
						System.out.println();
						
						nickname = a.getNickname();
						ID = a.getUserid();
						
						
						System.out.printf("\n%s ´Ô È¯¿µÇÕ´Ï´Ù!! \n\n",nickname);
						
						main_main_disp();
						
						//¸ÞÀÎÁ¾·áµÇ¸é ¸®ÅÏÇØ¼­ È£ÃâÇÑ °÷À¸·Î
						return;
						
					}
	
				} // id if - end;

			} // È¸¿ø check Á¾·á

			System.out.printf("·Î±×ÀÎ ½ÇÆÐ %dÈ¸!! \n",i+1);
			System.out.println();
			
			if((i+1==5)) {
				System.out.println();System.out.println();
				System.out.println("·Î±×ÀÎ¿¡ ½ÇÆÐÇÏ¿´½À´Ï´Ù.");
				System.out.println();System.out.println();System.out.println();
			}
			
			
		} // ·Î±×ÀÎ for end;
		

	}

	private static void main_main_disp() {
		// TODO Auto-generated method stub
		
		
		
		while(true) {
			System.out.println("\n\n¸ÞÀÎÈ­¸éÀÔ´Ï´Ù.");
			System.out.println("¹´½ä¿¡ ¿À½Å°É È¯¿µÇÕ´Ï´Ù!!!!");
			System.out.println("¸Þ´º¸¦ ¼±ÅÃÇÏ¼¼¿ä");
			
			//¿µÈ­Å×ÀÌºíÀ» Á¶È¸ÇØ¼­ ³»¿ëÀ» 1~5±îÁöÀÇ ³»¿ëÀ» ÀÐ¾î¿Í¼­ µ¿ÀûÀ¸·Î Ãâ·ÂÇØÁÖ´Â °Å
			//MovieVo vo = new MovieVo();
			List<MovieVo> movie_list = new ArrayList<MovieVo>();
			movie_list = MovieDao.getInstance().selectList();
			
			//Å×ÀÌºí¿¡¼­ µ¿ÀûÀ¸·Î ¿µÈ­Á¦¸ñ ÀÐ¾î¿À±â
			for(MovieVo vo : movie_list) {
				System.out.println("[" + vo.getMovieidx() + "] " + vo.getTitle());
			}
			
			/*
			 * System.out.println("[1] ¸íÀÛ¿µÈ­"); System.out.println("[2] Á¹ÀÛ¿µÈ­");
			 * System.out.println("[3] Å³¸µÅ¸ÀÓ¿µÈ­"); System.out.println("[4] ¾Æ¹«°Å³ª");
			 * System.out.println("[5] ¸ð½Ã²¤ÀÌ");
			 */
			
			System.out.printf("[6] %s ´ÔÀÌ ¾´±Û È®ÀÎÇÏ±â\n",nickname);
			System.out.println("[7] ·Î±×¾Æ¿ô");
			
			try {
				int res = sc.nextInt();
				
				sc.nextLine(); // ÀÔ·Â±¸ºÐÀÚÁ¦°Å
				if(res>=8) {
					System.out.println("Àß¸ø ÀÔ·ÂµÇ¾ú½À´Ï´Ù.");
					continue;
				}	
				
				switch (res) {
				case 1: review_disp(1); break;
				
				case 2: review_disp(2); break;
				
				case 3: review_disp(3); break;
				
				case 4: review_disp(4); break;
				
				case 5: review_disp(5); break;
				
				//case 6: review_disp(6); break;
				case 6: review_disp_user(); break;
				
				case 7: System.out.println("\n ·Î±×¾Æ¿ô ÇÕ´Ï´Ù.\n ÀÌ¿ëÇØ ÁÖ¼Å¼­ °¨»çÇÕ´Ï´Ù."); return; 
				}
				
				
				
			} catch (Exception e) {
				System.out.println("Àß¸ø ÀÔ·ÂµÇ¾ú½À´Ï´Ù.");
				sc = new Scanner(System.in);
			}

			
			
			
		}
		
		

		
	}
	
	
	//·Î±×ÀÎÇÑ »ç¿ëÀÚ°¡ ÀÛ¼ºÇÑ ¸®ºä Á¶È¸ÇÏ±â
	private static void review_disp_user() {
		// TODO Auto-generated method stub
		
		System.out.println(nickname + "´ÔÀÌ ÀÛ¼ºÇÑ ¸®ºä ¸ñ·ÏÀÔ´Ï´Ù.");
		
		String[] menu = { "±Û¹øÈ£", "¿µÈ­Á¦¸ñ", "³»¿ë", "ÀÛ¼ºÀÚ", "ÀÛ¼ºÀÏÀÚ" };

		System.out.println(
				"- - - - + - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - + - - - - - - - +");
		System.out.printf("   %s | %8s         | %17s                 | %4s     | %5s       |\n", menu[0], menu[1], menu[2],
				menu[3], menu[4]);
		System.out.println(
				"- - - - + - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - + - - - - - - - +");

		
		
		List<ReviewVo> select_review_list = new ArrayList<ReviewVo>();
		select_review_list = ReviewDao.getInstance().selectList_UserOnly(nickname);
		
		for(ReviewVo vo : select_review_list) {
			System.out.printf("   %04d | %13s     | %21s         | %6s   | %11s   |\n", vo.getGeulno(), vo.getTitle(), vo.getGeultext(), vo.getNickname(), vo.getGeulDate().substring(0, 10));
			System.out.println(
					"- - - - + - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - + - - - - - - - +");
			
		}
		
		System.out.println("1.±Û¾²±â\n2.¼öÁ¤\n3.»èÁ¦\n4.ÀÌÀü È­¸éÀ¸·Î..");

		int choice = sc.nextInt();
		sc.nextLine();
		
		//´Ù¸¥ ¼ýÀÚ¸¦ ÀÔ·ÂÇßÀ»¶§, ¾î¶»°Ô ÇÒÁö ¿¡·¯Ã³¸® ÇØ¾ßÇÔ
		
		switch(choice) {
			case 1:	write_review(); break;
			case 2:	update_review(); break;
			case 3:	delete_review(); break;
			case 5:	return;
			
		}
		
		
	}
	private static void delete_review() {
		// TODO Auto-generated method stub
		
		//ÀÌ°÷¿¡ »èÁ¦ ¸Þ¼Òµå¸¦ ÀÛ¼ºÇØÁÖ¼¼¿ä
	}
	private static void update_review() {
		// TODO Auto-generated method stub
		
		//ÀÌ°÷¿¡ ¼öÁ¤ ¸Þ¼Òµå¸¦ ÀÛ¼ºÇØÁÖ¼¼¿ä
	}
	
	
	
	//¹«ºñ ¸®½ºÆ® Á¶È¸ÇÏ±â
	private static void review_disp(int movieNo) {
		// TODO Auto-generated method stub

		while (true) {

			int idx = 0;

			String[] m = { "¿µÈ­Á¦¸ñ", "¿µÈ­ ÇÑÁÙÆòµé¾î°¥ °÷(ÃÖ´ë20ÀÚ)", "È«±æµ¿", "2022-05-11" };

			String[] menu = { "±Û¹øÈ£", "¿µÈ­Á¦¸ñ", "³»¿ë", "ÀÛ¼ºÀÚ", "ÀÛ¼ºÀÏÀÚ" };

			System.out.println(
					"- - - - + - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - + - - - - - - - +");
			System.out.printf("   %s | %8s         | %17s                 | %4s     | %5s       |\n", menu[0], menu[1], menu[2],
					menu[3], menu[4]);

			System.out.println(
					"- - - - + - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - + - - - - - - - +");

			System.out.printf("   %04d | %13s    | %21s         | %6s   | %11s   |\n", idx, m[0], m[1], m[2], m[3]);
			System.out.println(
					"- - - - + - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - + - - - - - - - +");

			//ÀÎÀÚ·Î ¹ÞÀº ¹«ºñ³Ñ¹ö¿Í °°Àº ¸®ºä ·¹ÄÚµå Á¶È¸ÇÏ±â 
			String movieTitle= MovieDao.getInstance().selectMovieTitle(movieNo);
			
			
			List<ReviewVo> select_review_list = new ArrayList<ReviewVo>();
			select_review_list = ReviewDao.getInstance().selectList(movieTitle);
			
			for(ReviewVo vo : select_review_list) {
				System.out.printf("   %04d | %13s     | %21s         | %6s   | %11s   |\n", vo.getGeulno(), vo.getTitle(), vo.getGeultext(), vo.getNickname(), vo.getGeulDate().substring(0, 10));
				System.out.println(
						"- - - - + - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - + - - - - - - - +");
				
			}
			
			System.out.println("1.±Û¾²±â\n2.¼öÁ¤\n3.»èÁ¦\n4.³»°¡ ¾´±Û È®ÀÎ\n5.ÀÌÀü È­¸éÀ¸·Î..");

			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
				case 1:	write_review(); break;
				case 2:	update_review(); break;
				case 3:	delete_review(); break;
				case 4:	review_disp_user(); break;
				case 5:	return;
				
			}
			

		} // Ãâ·ÂÈ­¸é while - end
		
		

	}
	
	
	//¸®ºä ÀÛ¼ºÇØ¼­ »ðÀÔÇÏ´Â ºÎºÐ
	private static void write_review() {
		// TODO Auto-generated method stub
		String title;
		String geulText;
		String userId = ID;
		String geulDate;
		int movieNo;
		
		//ÇöÀç ³¯Â¥ ±¸ÇÏ±â
		LocalDate now = LocalDate.now();
		//Æ÷¸ËÀû¿ëÇØ¼­ ¿øÇÏ´Â ³¯Â¥¸¸ °¡Á®¿À±â
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		
		geulDate = now.format(formatter);
		
		
		
		System.out.print("ÀÛ¼ºÀ» ¿øÇÏ´Â ¿µÈ­ Á¦¸ñÀ» ÀÔ·ÂÇÏ¼¼¿ä >> ");
		title = sc.nextLine();
		//System.out.println(title);
		movieNo = MovieDao.getInstance().selectMovieNo(title);
		
		System.out.print("\n¿µÈ­¿¡ ´ëÇÑ ¸®ºä¸¦ ÀÛ¼ºÇØÁÖ¼¼¿ä.´Ü, ±ÛÀÚ¼ö´Â 20ÀÚ¸¦ ³ÑÀ» ¼ö ¾ø½À´Ï´Ù.");
		geulText = sc.nextLine();
		
		ReviewVo vo = new ReviewVo(movieNo, geulText, userId, geulDate);
		
		int res = ReviewDao.getInstance().insert_review(vo);
		
		if(res == 0) {
			System.out.println("±Û ¾²±â ½ÇÆÐ!");
		} else {
			System.out.println("±Û ¾²±â ¼º°ø!");
		}
		
		//System.out.println(movieNo);
		//System.out.println(geulDate);
		
		
	}
	//¾ÆÀÌµð °Ë»ç ¸Þ¼Òµå ÇÑ±¹¾î¿Í ¶ç¾î¾²±â°¡ µé¾î¿ÔÀ¸¸é
	public static boolean isKorean(String str) {
		return Pattern.matches(".*[¤¡-¤¾¤¿-¤Ó°¡-ÆR!?()\s]+.*", str.replace("\n", ""));
		
	}

	
	
	// ¸ÞÀÎ¸Þ¼Òµå ½ÃÀÛ
	
	public static void main(String[] args) {

		main_login_disp(); // ÀÌ ÇÔ¼ö¿¡¼­ returnÇÏ¸é ³¡³²

		System.out.println("ÇÁ·Î±×·¥ Á¾·á");

	}
}
