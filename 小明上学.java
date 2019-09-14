import java.util.*;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);


		int r = scanner.nextInt();

		int y = scanner.nextInt();
		int g = scanner.nextInt();
		int N = scanner.nextInt();
		int time = 0;
		for(int i = 0;i <N;i++) {
			int key = scanner.nextInt();
			int value = scanner.nextInt();
			if(key == 0)
				time += value;
			if(key == 1)
				time += value;
			if(key == 2)
				time += (value+r);
			if(key == 3)	
				time+=0;
		}

		System.out.println(time);

	}

}