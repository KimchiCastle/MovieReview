package myutil;

public class Child extends Parent {
	
	int c_money;
	
	public Child() {
		// TODO Auto-generated constructor stub
		c_money = 1000;
		System.out.println("--Child()--");
	}

	public Child(int c_money) {
		super();//������ default ȣ��(�θ��ʱ�ȭ�Ѱɷ� �ν�)
		this.c_money = c_money;
		System.out.println("--Child(int)--");
	}
	
	public Child(int c_money, int p_money){
		
		super(p_money);
		this.c_money = c_money;
		System.out.println("--Child(int,int)--");
		
	}
	
	
	

}
