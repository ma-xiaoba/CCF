import java.util.Scanner; //导入Scanner类
public class Main{
    public static void main (String args[]){
        Scanner reader = new Scanner(System.in); //使用scanner类创建reader对象
        int score = 0; // 跳到中心，中心累加分
        int sum = 0; //总得分
        int n = 0;  // 输入数字0/1/2
        while((n=reader.nextInt())!=0){ //对象调用nextInt()方法
            //  nextInt()方法会读取下一个int型标志的token.但是焦点不会移动到下一行，仍然处在这一行上。
            if (n == 1){
                sum += 1;
                score = 0; //遇 1 则 中心累加分 归于 0
            }
            else if(n == 2){
                score += 2; 
                sum += score;
            }
            else if(n == 0){
                break;
            }
        }
        System.out.println(sum);
    }
}