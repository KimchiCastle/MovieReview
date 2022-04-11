package myutil;

public class AdvanceCalc extends BaseCalc{

	
	public int hap(int n) {
		
		int sum=0;
		
		for(int i=0; i<=n; i++) {
			//sum = sum+i;
			sum = plus(sum,i);
		}
		return sum;
	}
	
	public int pow(int n, int m) {
		
		int res = 1;
		for(int i=0; i<m; i++) {
			//res = res*n;
			res = multiply(res, n);
		}
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
}
