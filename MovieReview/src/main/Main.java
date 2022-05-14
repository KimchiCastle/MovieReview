package main;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import Vo.LoginVo;
import Dao.LoginDao;

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
		
		
		
		//insert ÇÏ±â À§ÇÑ vo »ı¼º
		//LoginVo vo = new LoginVo();
		
		//ÀÌ¹Ì ÀÖ´Â ¾ÆÀÌµğÀÎÁö Á¶È¸ÇÏ±â
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
				System.out.println("\nÀÌ¹Ì Á¸ÀçÇÏ´Â ¾ÆÀÌµğ ÀÔ´Ï´Ù.");
				continue OUT1;
			}

			vo = new LoginVo();
			
			
			OUT2:
			while(true) {
				
				System.out.println("\nºñ¹Ğ¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
				System.out.print("ºñ¹Ğ¹øÈ£ >> ");
				String new_pwd = sc.nextLine();
				
				if(isKorean(new_pwd)) {
					System.out.println("\nºñ¹Ğ¹øÈ£´Â ÇÑ±Û, °ø¹éÀº ÀÔ·ÂÀÌ ºÒ°¡´ÉÇÕ´Ï´Ù.");
					
					continue OUT2;
				}
				//ºñ¹Ğ¹øÈ£ Ã¼Å© ¿Ï·áÇßÀ¸¸é Æ÷Àå
				vo.setPassword(new_pwd);
				break;
			}

			
			OUT3: 
			while (true) {
				System.out.println("\n´Ğ³×ÀÓÀ» ÀÔ·ÂÇÏ¼¼¿ä.");
				System.out.print("´Ğ³×ÀÓ >> ");
				String new_nick = sc.nextLine();

				LoginVo vo2 = LoginDao.getInstance().selectOneFromNickname(new_nick);
				
				//vo2°ªÀÌ ³ÎÀÌ ¾Æ´Ï¸é Áï Å×ÀÌºí¿¡ µ¥ÀÌÅÍ°¡ ÀÖÀ¸¸é
				if(vo2!=null) {
					System.out.println("\nÀÌ¹Ì Á¸ÀçÇÏ´Â ´Ğ³×ÀÓ ÀÔ´Ï´Ù."); 
					continue OUT3;
				}

				//´Ğ³×ÀÓ Æ÷Àå
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
		//Á¶È¸ÇÏ±â À§ÇØ Dao°´Ã¼ »ı¼º
		list = LoginDao.getInstance().selectList();

			for(int i=0; i<5; i++){
				
			System.out.print("¾ÆÀÌµğ : \n");
			String id_check = sc.nextLine();
			System.out.print("ºñ¹Ğ¹øÈ£ : \n");
			String pwd_check = sc.nextLine();

			/*
			 * ¾ÆÀÌµğ	   ºñ¹ø			´Ğ³×ÀÓ	
			 * 'id1',	   '123123' 	'¹ÚÆò½ÄÀÇ5Á¡'
			 *  'my123',,  '1q2w3e4r' 	'¿ìÁÖ¸íÀÛ7±¤±¸'
			 *  'bonglove','bong123' 	'ºÀÁØÈ£»ç¶ûÇØ'
			 *  'kimchi12' 'kimchi12'	'kimchi12'
			 *  'gogo0325' '123123'		'¿µÈ­ÇĞ»ìÀÚ'
			 */
			

			for (LoginVo a : list) {
				//¾ÆÀÌµğ Ã¼Å©
				if (id_check.equals(a.getUserid())) {
					//ºñ¹øÃ¼Å©
					if(pwd_check.equals(a.getPassword())) {
						System.out.println("\n·Î±×ÀÎ ¿Ï·á");
						System.out.println();
						
						nickname = a.getNickname();
						ID = a.getUserid();
						
						
						System.out.printf("\n%s ´Ô È¯¿µÇÕ´Ï´Ù!! \n\n",nickname);
						
						main_main_disp();
						
						//¸ŞÀÎÁ¾·áµÇ¸é ¸®ÅÏÇØ¼­ È£ÃâÇÑ °÷À¸·Î
						return;
						
					}
	
				} // id if - end;

			} // È¸¿ø check Á¾·á

			System.out.printf("·Î±×ÀÎ ½ÇÆĞ %dÈ¸!! \n",i+1);
			System.out.println();
			
			if((i+1==5)) {
				System.out.println();System.out.println();
				System.out.println("·Î±×ÀÎ¿¡ ½ÇÆĞÇÏ¿´½À´Ï´Ù.");
				System.out.println();System.out.println();System.out.println();
			}
			
			
		} // ·Î±×ÀÎ for end;
		

	}

	private static void main_main_disp() {
		// TODO Auto-generated method stub
		
		System.out.println("\n\n¸ŞÀÎÈ­¸éÀÔ´Ï´Ù.");
		System.out.println("¹´½ä¿¡ ¿À½Å°É È¯¿µÇÕ´Ï´Ù!!!!");
		System.out.println("¸Ş´º¸¦ ¼±ÅÃÇÏ¼¼¿ä");
		
		System.out.println("[1] ¸íÀÛ¿µÈ­");
		System.out.println("[2] Á¹ÀÛ¿µÈ­");
		System.out.println("[3] Å³¸µÅ¸ÀÓ¿µÈ­");
		System.out.println("[4] ¾Æ¹«°Å³ª");
		System.out.println("[5] ¸ğ½Ã²¤ÀÌ");
		
		System.out.printf("[6] %s ´ÔÀÌ ¾´±Û È®ÀÎÇÏ±â\n",nickname);
		System.out.println("[7] ·Î±×¾Æ¿ô");
		
		
		while(true) {
			
			try {
				int res = sc.nextInt();
				
				sc.nextLine(); // ÀÔ·Â±¸ºĞÀÚÁ¦°Å
				if(res>=8) {
					System.out.println("Àß¸ø ÀÔ·ÂµÇ¾ú½À´Ï´Ù.");
					continue;
				}	
				
				switch (res) {
				case 1: review_disp(); break;
				
				case 2: review_disp(); break;
				
				case 3: review_disp(); break;
				
				case 4: review_disp(); break;
				
				case 5: review_disp(); break;
				
				case 6: review_disp(); break;
				
				case 7: System.out.println("\n ·Î±×¾Æ¿ô ÇÕ´Ï´Ù.\n ÀÌ¿ëÇØ ÁÖ¼Å¼­ °¨»çÇÕ´Ï´Ù."); return; 
				}
				
				
				
			} catch (Exception e) {
				System.out.println("Àß¸ø ÀÔ·ÂµÇ¾ú½À´Ï´Ù.");
				sc = new Scanner(System.in);
			}

			
			
			
		}
		
		

		
	}

	private static void review_disp() {
		// TODO Auto-generated method stub

		while (true) {

			int idx = 1;

			String[] m = { "¿µÈ­Á¦¸ñ", "¿µÈ­ ÇÑÁÙÆòµé¾î°¥ °÷(ÃÖ´ë20ÀÚ)", "È«±æµ¿", "2022/05/11" };

			String[] menu = { "±Û¹øÈ£", "¿µÈ­Á¦¸ñ", "³»¿ë", "ÀÛ¼ºÀÚ", "ÀÛ¼ºÀÏÀÚ" };

			System.out.println(
					"- - - - + - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - + - - - - - - - -");
			System.out.printf(" %s | %8s      | %17s               | %4s   | %5s \n", menu[0], menu[1], menu[2],
					menu[3], menu[4]);

			System.out.println(
					"- - - - + - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - + - - - - - - - -");

			System.out.printf("   %04d | %13s | %21s | %6s | %11s \n", idx, m[0], m[1], m[2], m[3]);
			System.out.println(
					"- - - - + - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - + - - - - - - - -");

			System.out.println("1.±Û¾²±â\n2.¼öÁ¤\n3.»èÁ¦\n4.³»°¡ ¾´±Û È®ÀÎ\n5.ÀÌÀü È­¸éÀ¸·Î..");

			int choice = sc.nextInt();

			if (choice == 5) {
				return;
			}

		} // Ãâ·ÂÈ­¸é while - end
		
		

	}

	//¾ÆÀÌµğ °Ë»ç ¸Ş¼Òµå ÇÑ±¹¾î¿Í ¶ç¾î¾²±â°¡ µé¾î¿ÔÀ¸¸é
	public static boolean isKorean(String str) {
		return Pattern.matches(".*[¤¡-¤¾¤¿-¤Ó°¡-ÆR!?()\s]+.*", str.replace("\n", ""));
		
	}

	
	
	// ¸ŞÀÎ¸Ş¼Òµå ½ÃÀÛ
	
	public static void main(String[] args) {

		main_login_disp(); // ÀÌ ÇÔ¼ö¿¡¼­ returnÇÏ¸é ³¡³²

		System.out.println("ÇÁ·Î±×·¥ Á¾·á");

	}
}
