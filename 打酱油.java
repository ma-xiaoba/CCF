import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		int num1 = 0;  //买5瓶
		int num2 = 0;  //买3瓶
		int num3 = 0;  //买1瓶
 
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
 
		if (N >= 50) {       //大于等于50以上的             50-300
			num1 = N / 50;   //买过多少次5瓶
			N = N % 50;      //剩下的余额
		}
		if (N >= 30) {       //大于等于30以上的              30-40
			num2 = N / 30;   //买过多少次3瓶
			N = N % 30;      //剩下的余额
		}
		if (N <= 20) {       //小于等于20以上的              10-20
			num3 = N / 10;   //买过多少次1瓶
			N = N % 30;      //剩下的余额 
		}
		System.out.println(num1*7+num2*4+num3*1);//买的方式乘以一共的瓶数相加
 
	}
 
}