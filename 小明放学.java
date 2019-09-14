import java.util.Scanner;

public class Main{

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
		// 红灯时间
        long r = scanner.nextInt();
        // 黄灯时间
        long y = scanner.nextInt();
        // 绿灯时间
        long g = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = scanner.nextInt();
            arr[i][1] = scanner.nextInt();
        }
        System.out.println(wait(arr, r, y, g));
    }

    public static long wait(int[][] arr, long r, long y, long g) {
        int length = arr.length;
        long sum = 0;
        for (int i = 0; i < length; i++) {
        // 当前道路不存在红绿灯
            if (arr[i][0] == 0) {
                sum = sum + arr[i][1];
            }
            // 为红灯时
            if (arr[i][0] == 1) {
            	// 红绿黄总时间 求余数 判断处于哪一个区间。
                long r1 = (sum - arr[i][1]) %(r+y+g);
                if (r1 <= 0) {
                	// 位于红灯区间
                    sum = sum - r1;
                } else if (r1 > g) {
                	// 处于黄灯和红灯区间
                    sum = sum + r+y+g - r1;
                }
            }
            // 黄灯
            if (arr[i][0] == 2) {
                long r1 = (sum - arr[i][1]) %(r+y+g);
                // 处于红灯区间
                if (r1 <= r) {           
                    sum = sum + r - r1;
                } else if (r1 > r + g) {
                	// 位于 黄灯区间 还要加上接下来的 红灯等待时间
                    sum = sum + r + y + g - r1 + r;
                }
            }
            // 绿灯
            if (arr[i][0] == 3) {
                long r1 = (sum - arr[i][1]) % (r+y+g);
                if (r1 > 0) {
                    if (r1 <= r + y) {
                        sum = sum + y+r - r1;
                    }
                }
            }
        }
        return sum;
    }
}