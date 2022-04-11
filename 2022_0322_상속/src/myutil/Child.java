package myutil;

public class Child extends Parent {
	
	int c_money;
	
	public Child() {
		// TODO Auto-generated constructor stub
		c_money = 1000;
		System.out.println("--Child()--");
	}

	public Child(int c_money) {
		super();//생략시 default 호출(부모초기화한걸로 인식)
		this.c_money = c_money;
		System.out.println("--Child(int)--");
	}
	
	public Child(int c_money, int p_money){
		
		super(p_money);
		this.c_money = c_money;
		System.out.println("--Child(int,int)--");
		
	}
	
	
	

}
