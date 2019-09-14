import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    //static Node root = new Node(0, null, "root", "");  修改前 90
    static Node root = new Node(0, null, "", ""); //修改后 100

    public static void main(String[] args) {
        int n = input.nextInt();
        int m = input.nextInt();

        //接收结构化文档，并整理好结构
        input.nextLine();
        getDateToTree(n);

        //对每个选择器进行匹配，输出匹配结果
        printAns(m);
    }

    //接收结构化文档，并整理成树结构
    public static void getDateToTree(int n) {
        for (int i = 0; i < n; i++) {
            String[] line = input.nextLine().split(" ");
            int dotCount = 0;
            int idx = 0;
            for (int j = 0; j < line[0].length() && line[0].charAt(j) == '.'; j++) {
                dotCount++;
                idx++;
            }

            //把标签转为小写
            String label = line[0].substring(idx, line[0].length()).toLowerCase();
            String id = line.length == 2 ? line[1] : "";

            //指针每次从根结点开始找，找dotCount/2次
            Node pointer = root;
            for (int j = 0; j < dotCount / 2; j++)
                pointer = pointer.subNodes.get(pointer.subNodes.size() - 1);
            pointer.subNodes.add(new Node(i + 1, pointer, label, id));
        }
    }

    //对每个选择器进行匹配，输出匹配结果
    public static void printAns(int m) {
        for (int i = 0; i < m; i++) {
            //该列表存储的是选择器匹配到的标签们在结构化文档中的行数
            ArrayList<Integer> lineNums = new ArrayList<>();
            String[] line = input.nextLine().split(" ");
            match(lineNums, line);
            lineNums.sort(Comparator.comparing(Integer::byteValue));
            System.out.print(lineNums.size());
            for (int lineNum : lineNums)
                System.out.print(" " + lineNum);
            System.out.println();
        }
    }

    //选择器匹配，层次遍历结构树，对每个结点进行匹配
    public static void match(ArrayList<Integer> lineNums, String[] line) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node tem = queue.poll();

            //从选择器最底端开始匹配
            //如果选择器是标签就改为小写
            String seleter = line[line.length - 1].charAt(0) == '#' ?
                    line[line.length - 1] : line[line.length - 1].toLowerCase();
            if (seleter.equals(tem.label) || seleter.equals(tem.id)) {
                int lineIdx = line.length - 2;
                Node pointer = tem.preNode;
                //从最下级开始往上找父元素
                while (pointer != null && lineIdx >= 0) {
                    //如果选择器是标签就改为小写
                    seleter = line[lineIdx].charAt(0) == '#' ?
                            line[lineIdx] : line[lineIdx].toLowerCase();
                    if (pointer.label.equals(seleter) ||
                            pointer.id.equals(seleter)) {
                        lineIdx--;
                    }
                    pointer = pointer.preNode;
                }
                if (lineIdx == -1)
                    lineNums.add(tem.lineNum);
            }

            //子结点入队
            for (Node val : tem.subNodes)
                queue.offer(val);
        }
    }

}

//标签元素结点类
class Node {
    int lineNum;//元素所在的行数
    Node preNode;
    ArrayList<Node> subNodes = new ArrayList<>();//子结点列表
    String label;
    String id;

    Node(int lineNum, Node preNode, String label, String id) {
        this.lineNum = lineNum;
        this.preNode = preNode;
        this.label = label;
        this.id = id;
    }
}

