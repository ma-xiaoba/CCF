import java.util.Scanner;
public class B {
    public static int[] a=new int[1005];
    public int find(int data,int n){
        /**
         *@Description: 找到data号人现在所在的位置
         *@param1:data
         *@param2:n
         *@Return: 位置的索引号
         */
        for(int i=0;i<n;i++){
            if(a[i]==data){
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
 
        B b=new B();
 
        Scanner scan =new Scanner(System.in);
        int total=scan.nextInt();   //总人数
        int n=scan.nextInt();
 
        for(int i=1;i<=total;i++){  //便于理解
            a[i-1]=i;
        }
 
        for(int i=0;i<n;i++){
            int num=scan.nextInt();         //位置
            int position=scan.nextInt();    //移动方式
            int temp=b.find(num,total);     //这个人现在站在哪（位置编号/数组下标）
            int tempNum=a[temp];            //这个人是几号（号码传入临时变量/让这个人出列）
            int count=0;
            if(position>0){                 //向右移动
                while(count!=position){
                    a[temp+count]=a[temp+count+1];  //重整队伍，从空位依次向前移动
                    count++;
                }
                a[count+temp]=tempNum;      //入列
            }else{
                while(count!=position){     //向左移动
                    a[temp+count]=a[temp+count-1];
                    count--;
                }
                a[count+temp]=tempNum;
            }
        }
        for(int i=0;i<total;i++){
            System.out.print(a[i]+" ");
        }
    }
}