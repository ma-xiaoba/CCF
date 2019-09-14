import java.util.ArrayList;
import java.util.Scanner;

/*
 * 1.利用集合存入教师数据,初始化钥匙序列N, K:教师人数 时间记录器:time, 集合存储要归还的钥匙
 * 2.还钥匙:根据当前时间判断归还钥匙的老师,将钥匙序号存入集合,排序,再依次放回数组中
 * 3.取钥匙:根据当前时间,判断能使用钥匙的老师,执行取钥匙方法
 * 教师: w:钥匙号    s:开始上课    c:上课时间    e:s+c 结束时间
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //存老师
        ArrayList<Teacher> arrt1 = new ArrayList<Teacher>();
        //存要归还的钥匙
        ArrayList<Integer> key = new ArrayList<Integer>();
        int time = 1;
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        for (int i = 1; i <= K; i++) {
            Teacher t = new Teacher(sc.nextInt(), sc.nextInt(), sc.nextInt());
            arrt1.add(t);
        }
        //结束条件是当前时间大于最后一个老师上完课的时间
        while(time <= maxTime(arrt1)){
            returnKey(time, arrt1, arr, key);
            borrowKey(time, arrt1, arr);
            time++;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    public static void returnKey(int time, ArrayList<Teacher> arrt1, int[] arr, ArrayList<Integer> key) {
        //清空放置要归还钥匙的集合
        key.clear();
        //判断这个时间是否有需要归还钥匙的老师
        for (int i = 0; i < arrt1.size(); i++) {
            if (arrt1.get(i).e == time) {
                key.add(arrt1.get(i).w);
            }
        }
        if (key.isEmpty()) {
            //没有钥匙则不归还
            return;
        } else {
            //将要归还的钥匙从大到小排序
            for (int i = 0; i < key.size() - 1; i++) {
                for (int j = 0; j < key.size() - 1 - i; j++) {
                    if (key.get(j) > key.get(j + 1)) {
                        int temp = key.get(j);
                        key.set(j, key.get(j + 1));
                        key.set(j + 1, temp);
                    }
                }
            }
            //归还钥匙
            for (int i = 0, j = 0; i < arr.length; i++) {
                if(arr[i] == 0){
                    arr[i] = key.get(j);
                    if(key.size()-1 == j){
                        break;
                    }else{
                        j++;
                    }
                }
            }
        }
    }

    public static void borrowKey(int time, ArrayList<Teacher> arrt1, int[] arr){
        for (int i = 0; i < arrt1.size(); i++) {
            //判断在当前时间能够使用钥匙的老师
            if(time == arrt1.get(i).s){
                //取走钥匙
                for (int j = 0; j < arr.length; j++) {
                    if(arrt1.get(i).w == arr[j]){
                        arr[j] = 0;
                        break;
                    }
                }
            }
        }
    }

    public static int maxTime(ArrayList<Teacher> arrt1){
        int temp = 0;
        for (int i = 0; i < arrt1.size(); i++) {
            if(arrt1.get(i).e > temp){
                temp = arrt1.get(i).e;
            }
        }
        return temp;
    }
}

class Teacher {
    int w;
    int s;
    int c;
    int e;

    public Teacher(int w, int s, int c) {
        this.w = w;
        this.s = s;
        this.c = c;
        this.e = this.s + this.c;
    }
}
