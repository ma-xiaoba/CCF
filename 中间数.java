import java.util.Scanner;

public class Main{
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n=sc.nextInt();
		int[] a=new int[n];
		for(int i=0;i<n;i++) {
			a[i]=sc.nextInt();
		}
		int c=0;
		for(int i=0;i<n;i++) {
			int x=0,y=0;
			for(int j=0;j<n;j++) {
				if(a[i]>a[j]) {
					x++;
				}else if(a[i]<a[j]) {
					y++;
				}
			}
			if(x==y) {
				c=a[i];
				System.out.println(c);
				return;
			}
		}
		System.out.println(-1);
	}
}