package mymain;

import java.util.ArrayList;

import myutil.AdvanceCalc;
import myutil.BaseCalc;

public class MyMain_Calc {
	
	static void use_basecalc(BaseCalc bc) {
				//자손의 ac를 넘겨받으면 ac만갖고있는 기능만 빼고
				//권한을 넘겨받음
		int x = 10, y = 5, res;
		res = bc.multiply(x, y);//basecalc의 기능
		System.out.printf("use_basecalc : %d * %d = %d\n",x,y,res);
	}

	
	static void use_object(Object ob) {
		System.out.println(ob.toString());
		
		int x = 10, res;
		
		AdvanceCalc ac = (AdvanceCalc) ob;
		
		res = ac.hap(x);
		
		System.out.printf("%d까지의 합은 %d\n",x,res);
	
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AdvanceCalc ac = new AdvanceCalc();
		
		int a = 10, b = 3 ,res;
		
		res = ac.plus(a, b);
		
		System.out.printf("%d + %d =%d\n",a,b,res);
		
		res = ac.hap(a);
		
		System.out.printf("%d승=%d\n",a,res);
		
		
		use_basecalc(ac); //위 basecalc메소드에 ac넘김
		
		use_object(ac);
		
		
		
		ArrayList list = new ArrayList();
		
		list.add("자바스트링객체");
		list.add(10); // << new Integer(10); object로 자동 변환
		list.add(true);
		list.add(10.5);
		
		//wrapper class
		Integer nOb = 10;
		
		//unboxing
		int n = nOb; //nOb.intValue();
		double d = nOb;
		float f = nOb;
		
		Object [] array = {new String("스트링"), new Integer(10), new Boolean(true), new Double(10.5)};
		
		String s = (String) array[0]; //<<down casting
		
		
		
		
		
	}
		
		
		
		
		
		
		
	
	
	
	
	
	
}
