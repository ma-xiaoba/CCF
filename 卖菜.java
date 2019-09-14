import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
public class Main {
	static Scanner sc;
	static int n;//总的商店的数目
	static int[] total;
	static List<Integer> list;
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		n = sc.nextInt();
		list = new ArrayList<>();
		total = new int[n];
		for(int i=0;i<n;i++) {
			list.add(sc.nextInt());
		}
		for(int i=0;i<list.size();i++) {
			if(i==0) {
				total[i] = (list.get(i)+list.get(i+1))/2;
				System.out.print(total[i]+" ");
			} else if((i!=0)&&(i!=list.size()-1)) {
				total[i] = (list.get(i-1)+list.get(i)+list.get(i+1))/3;
				System.out.print(total[i]+" ");
			} else {
				total[i] = (list.get(i-1)+list.get(i))/2;
				System.out.print(total[i]);
			}
		}
	}
 
}