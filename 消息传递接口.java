import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] ss = s.split(" ");
		int T = Integer.parseInt(ss[0]), n = Integer.parseInt(ss[1]);
		for(int i=0;i<T;i++) {
			LinkedList<Integer>[] thd = new LinkedList[n+1];	//需要模拟的进程，因为0进程不好处理，所以用1~n进程标记，即所有进程顺次加1
			int len = 0;										//记录题目给出的样例如果没有问题的话，大概有多少对S、R
			for(int j=1;j<n+1;j++) {
				s = br.readLine();
				ss = s.split(" ");
				len += ss.length;
				thd[j] = new LinkedList<Integer>();
				for(int k=0;k<ss.length;k++) {
					if(ss[k].charAt(0)=='S')
						thd[j].add(Integer.parseInt(ss[k].substring(1, ss[k].length()))+1);//S为正数
					else
						thd[j].add(-Integer.parseInt(ss[k].substring(1, ss[k].length()))-1);//R为负数
				}
			}
			System.out.println(Judge(n, len/2, thd));
		}
		br.close();
	}
	public static int Judge(int n, int len, LinkedList<Integer>[] thd) {
		int count = len;
		while(len>0) {
			int tmplen=len;
			for(int i=1;i<n;i++) {
				if(!thd[i].isEmpty()) {
					int tmp = thd[i].get(0);
					if(tmp>0) {
						if(!thd[tmp].isEmpty() && thd[tmp].get(0)+i==0) {
							thd[i].remove(0);
							thd[tmp].remove(0);
							count--;
							len--;
						}
					}
					else if(tmp<0){
						if(!thd[-tmp].isEmpty() && thd[-tmp].get(0)==i) {
							thd[i].remove(0);
							thd[-tmp].remove(0);
							count--;
							len--;
						}
					}
				}
			}
			if(tmplen == len) {		//如果tmplen == len说明锁死了，即while死循环了
				return 1;
			}
		}
		if(count==0)		//count等于0说明给出的为完整的len对，并且没有锁死
			return 0;
		return 1;
	}
}
 