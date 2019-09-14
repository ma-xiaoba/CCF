import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
    static ArrayDeque queue;
    static int num=1;
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int count=in.nextInt();
        int n=in.nextInt();
        queue=new ArrayDeque();
        for (int i=0;i<count;i++){
            queue.offer(i+1);
        }while (queue.size()>1){
            int top= (int) queue.poll();
            if (num%n!=0&&num%10!=n){
                queue.offer(top);
            }
            num++;
        }
        System.out.println(queue.peek());
    }
}