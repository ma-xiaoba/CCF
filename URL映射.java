import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int m = scan.nextInt();
		int n = scan.nextInt();
		scan.nextLine();
		String[] input = new String[m];// 保存原有的规则内容以便前导0判断<int>
		String[][] rules = new String[m][2];// 这个数组第一个元素存的是构造后的正则，第二个是规则名
		for (int i = 0; i < m; i++) {
			input[i] = scan.nextLine();
			rules[i] = input[i].split(" ");// 按空格将规则分两半
			rules[i][0] = rules[i][0].replaceAll("<int>", "([0-9]+)").replaceAll("<str>", "([^/]+)")
					.replaceAll("<path>", "(.+)");
		} // 将规则变成正则表达式
		String[] output = new String[n];
		for (int i = 0; i < n; i++) {
			String url = scan.nextLine();
			if (!url.matches("[/A-Za-z0-9\\-\\._]+")) {// 判断输入是否合法
				output[i] = "404";
				continue;
			}
			boolean flag = false;// 标记是否已经匹配到了

			for (int j = 0; j < m; j++) {// 遍历每一项规则
				String[] rule = rules[j];
				Pattern p = Pattern.compile(rule[0]);
				Matcher matcher = p.matcher(url);
				if (matcher.matches()) {// 如果完全匹配，注意区别：find()是部分匹配
					flag = true;
					output[i] = rule[1];// 先存入规则名
					for (int k = 1; k <= matcher.groupCount(); k++) {// 逐一获取参数值
						String param = matcher.group(k);// 对每个参数值，判断它是否以0开头，如果不是，就直接接到输出后面，如果是，则要判断它对应的参数类型是否为<int>
						if (!param.startsWith("0"))// 如果是<str>或<path>怎么办
							output[i] = output[i] + " " + param;
						else {// 找出该条规则中的所有参数占位符，就是形如<xxx>的
							Pattern pp = Pattern.compile("(<[a-z]+>)");
							Matcher mat = pp.matcher(input[j]);
							int count = 0;
							while (mat.find()) {
								count++;// 找到第K个占位符，因为这个参数值是第k个，所以对应的是该条规则中的第k个参数，也就是第k个<xxx>
								if (count == k)
									break;
							}
							if (mat.group(1).equals("<int>"))
								param = param.substring(1);
							output[i] = output[i] + " " + param;

						} // else startsWith("0")
					} // for groupCount

					break; // 既然找到了第一个完全匹配的规则，搞完了就跳出
				} // if matches

			} // for j:m
			if (flag == false)
				output[i] = "404";// 遍历了所有规则也没找到匹配的
		} // for i:n

		for (String s : output)
			System.out.println(s);

	}

}
