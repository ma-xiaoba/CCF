import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		int len = scanner.nextInt();
		int time = scanner.nextInt();
		int[][] arr = new int[num][3];
		for (int i = 0; i < num; i++) {
			arr[i][0] = scanner.nextInt();
			arr[i][1] = i;
			arr[i][2] = 1;
		}
		scanner.close();
		Arrays.sort(arr, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		for (int i = 0; i < time; i++) {
			int j = 0;
			if (arr[j][0] == 0) {
				arr[j][0] = 1;
				arr[j][2] = 1;
				j++;
			}
			for (; j < num-1; j++) {
				if (arr[j][0] == arr[j + 1][0]) {
					arr[j][2] = -1;
					arr[j][0]--;
					arr[j + 1][2] = 1;
					arr[j + 1][0]++;
					j++;
				} else {
					arr[j][0] = arr[j][0] + arr[j][2];
				}
			}
			if (j == (num-1)) {
				if (arr[j][0] == len) {
					arr[j][0]--;
					arr[j][2] = -1;
				} else
					arr[j][0] = arr[j][0] + arr[j][2];
			}
		}
		Arrays.sort(arr, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		for (int i = 0; i < num - 1; i++) {
			System.out.print(arr[i][0] + " ");
		}
		System.out.print(arr[num - 1][0]);
	}
}
