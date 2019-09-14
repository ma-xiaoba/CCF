import java.util.*;
 
public class Main {
 
    public static void main(String[] args){
         Scanner sc = new Scanner(System.in);
         int n = sc.nextInt();
         int data[] = new int[n];
         int min = 999999;
         for(int i=0;i<n;i++){
            data[i] = sc.nextInt();
            for(int j=0;j<i;j++){
                if(min>Math.abs(data[j]-data[i]))
                    min = Math.abs(data[j]-data[i]);
            }
         }
         System.out.println(min);
    }
}