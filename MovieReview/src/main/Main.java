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
			
			System.out.println("☆★☆★☆★☆★☆★뭅썰☆★☆★☆★☆★☆★");
			System.out.println("로그인 [1] ");
			System.out.println("회원 가입[2]");
			System.out.println("끝내기[3]");
			
			
			try {
				int res = sc.nextInt();
				
				sc.nextLine(); // 입력구분자제거
				if(res>=4) {
					System.out.println("다시 입력하세요");
					
					continue;
					
				}	
				if (res == 1) {
					
					login();
					
				} else if (res == 2) {
					
					sign_up();
					
					
				} else if (res == 3) {
					//프로그램 종료
					return;
				}
			} catch (Exception e) {
				System.out.println("다시 입력하세요");
				sc = new Scanner(System.in);
			}
		}

	}
	//회원가입
	private static void sign_up() {
		
		System.out.println("회원가입입니다.");
		
		//insert 하기 위한 vo 생성
		//LoginVo vo = new LoginVo();
		
		//이미 있는 아이디인지 조회하기
		list = LoginDao.getInstance().selectList();
		
		OUT1:
		while(true) {
			
			System.out.println("회원 가입할 ID를 입력하세요.");
			System.out.println("ID >> ");
			String new_id = sc.nextLine();
			
			LoginVo vo = LoginDao.getInstance().selectOneFromID(new_id);
			
			if(vo!=null) {
				System.out.println("이미 존재하는 아이디 입니다.");
				continue OUT1;
			}

			vo = new LoginVo();
			
			
			
			System.out.println("비밀번호를 입력하세요.");
			System.out.println("비밀번호 >> ");
			String new_pwd = sc.nextLine();

			
			OUT2: 
			while (true) {
				System.out.println("닉네임을 입력하세요.");
				System.out.println("닉네임 >> ");
				String new_nick = sc.nextLine();

				LoginVo vo2 = LoginDao.getInstance().selectOneFromNickname(new_nick);
				
				//vo2값이 널이 아니면 즉 테이블에 데이터가 있으면
				if(vo2!=null) {
					System.out.println("이미 존재하는 닉네임 입니다."); 
					continue OUT2;
				}

				//닉네임 포장
				vo.setNickname(new_nick);
				break;

			}
			
			
			vo.setUserid(new_id);
			vo.setPassword(new_pwd);
			
			int res = LoginDao.getInstance().signUp_insert(vo);
			
			System.out.println("회원가입 완료!!" + res);
			
			return;
			
			} // while - end;

	}

	private static void login() {
		System.out.println("               [로그인]");
		System.out.println();
		//조회하기 위해 Dao객체 생성
		list = LoginDao.getInstance().selectList();

			for(int i=0; i<5; i++) {
				
			System.out.println("아이디 :");
			System.out.println("비밀번호 :");
			
			String id_check = sc.nextLine();
			String pwd_check = sc.nextLine();
			
			
			
			
			
			/*
			 * 아이디	   비번			닉네임	
			 * 'id1',	   '123123' 	'박평식의5점'
			 *  'my123',,  '1q2w3e4r' 	'우주명작7광구'
			 *  'bonglove','bong123' 	'봉준호사랑해'
			 *  'kimchi12' 'kimchi12'	'kimchi12'
			 *  'gogo0325' '123123'		'영화학살자'
			 */
			
			
			
			for (LoginVo a : list) {
				//아이디 체크
				if (id_check.equals(a.getUserid())) {
					//비번체크
					if(pwd_check.equals(a.getPassword())) {
						System.out.println("로그인 완료");
						System.out.println();
						
						nickname = a.getNickname();
						ID = a.getUserid();
						
						
						System.out.printf("%s 님 환영합니다!!",nickname);
						
						main_main_disp();
						
						//메인종료되면 리턴해서 호출한 곳으로
						return;
						
					}
	
				} // id if - end;

			} // 회원 check 종료

			System.out.println("잘못된 정보를 입력 하셨습니다.");
			
			
		} // 로그인 for end;
		

	}

	private static void main_main_disp() {
		// TODO Auto-generated method stub
		
		System.out.println("메인화면입니다.");
		System.out.println("1. 명작영화");
		System.out.println("2. 졸작영화");
		System.out.printf("3. %s 님이 쓴글 확인하기",nickname);
		
		System.out.println("로그아웃 합니다.");
		return;
		
	}

	public static void main(String[] args) {

		main_login_disp(); // 이 함수에서 return하면 끝남

		System.out.println("프로그램 종료");

	}
}
