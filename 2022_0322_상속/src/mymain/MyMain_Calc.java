package mymain;

import java.util.ArrayList;

import myutil.AdvanceCalc;
import myutil.BaseCalc;

public class MyMain_Calc {
	
	static void use_basecalc(BaseCalc bc) {
				//�ڼ��� ac�� �Ѱܹ����� ac�������ִ� ��ɸ� ����
				//������ �Ѱܹ���
		int x = 10, y = 5, res;
		res = bc.multiply(x, y);//basecalc�� ���
		System.out.printf("use_basecalc : %d * %d = %d\n",x,y,res);
	}

	
	static void use_object(Object ob) {
		System.out.println(ob.toString());
		
		int x = 10, res;
		
		AdvanceCalc ac = (AdvanceCalc) ob;
		
		res = ac.hap(x);
		
		System.out.printf("%d������ ���� %d\n",x,res);
	
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AdvanceCalc ac = new AdvanceCalc();
		
		int a = 10, b = 3 ,res;
		
		res = ac.plus(a, b);
		
		System.out.printf("%d + %d =%d\n",a,b,res);
		
		res = ac.hap(a);
		
		System.out.printf("%d��=%d\n",a,res);
		
		
		use_basecalc(ac); //�� basecalc�޼ҵ忡 ac�ѱ�
		
		use_object(ac);
		
		
		
		ArrayList list = new ArrayList();
		
		list.add("�ڹٽ�Ʈ����ü");
		list.add(10); // << new Integer(10); object�� �ڵ� ��ȯ
		list.add(true);
		list.add(10.5);
		
		//wrapper class
		Integer nOb = 10;
		
		//unboxing
		int n = nOb; //nOb.intValue();
		double d = nOb;
		float f = nOb;
		
		Object [] array = {new String("��Ʈ��"), new Integer(10), new Boolean(true), new Double(10.5)};
		
		String s = (String) array[0]; //<<down casting
		
		
		
		
		
	}
		
		
		
		
		
		
		
	
	
	
	
	
	
}
