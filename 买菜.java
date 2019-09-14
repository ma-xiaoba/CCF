import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
public class Main {
	static Scanner sc;
	static int total;
	static int time;
	static List<TimeGap> list1;
	static List<TimeGap> list2;
	static String temp;
	static String[] cmd;
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		list1 = new ArrayList<>();
		list2 = new ArrayList<>();
		total = sc.nextInt();		
		
		for(int i=0;i<total;i++) {
			list1.add(new TimeGap(sc.nextInt(), sc.nextInt()));
		}
		for(int i=0;i<total;i++) {
			list2.add(new TimeGap(sc.nextInt(), sc.nextInt()));
		}
		
		for(int i=0;i<total;i++) {
			for(int j=0;j<total;j++) {
				if((list1.get(i).start>=list2.get(j).end) || (list2.get(j).start>=list1.get(i).end)) {
					continue;
				} else {
					if((list1.get(i).start<=list2.get(j).start)&&(list1.get(i).end<=list2.get(j).end)) {
						time+=list1.get(i).end-list2.get(j).start;
					} else if((list2.get(j).start<=list1.get(i).start)&&(list2.get(j).end<=list1.get(i).end)) {
						time+=list2.get(j).end-list1.get(i).start;
					} else if((list1.get(i).start<=list2.get(j).start)&&(list1.get(i).end)>=list2.get(j).end) {
						time+=list2.get(j).end-list2.get(j).start;
					} else if((list2.get(j).start<=list1.get(i).start)&&(list2.get(j).end)>=list1.get(i).end) {
						time+=list1.get(i).end-list1.get(i).start;
					} else
						continue;
				}
			}
			
		}
		
		System.out.print(time);
	}
 
}
 
class TimeGap{
	public int start;
	public int end;
	public TimeGap(int start, int end) {
		this.start = start;
		this.end = end;
	}
}