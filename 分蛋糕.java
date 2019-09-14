import java.util.Scanner;
public class Main {	
public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num=1;           //最终输出的数
		int temp =0;
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();      //蛋糕块数
		int k = sc.nextInt();	   //蛋糕重量
		
			for(int i =0;i<n;i++){
				temp=temp+sc.nextInt();
				if(temp>=k && i<n-1 ){ //i是从0开始
					num++;
					temp=0;
				}
			
		}
		System.out.println(num);
		
	}
 
}