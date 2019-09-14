import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class Main {
    public static void main(String[] args) {
        String emRegex = "_([^_]+)_";
        String linkRegex = "\\[([^\\]]+)\\]\\(([^\\)]+)\\)";
        String headRegex = "(#+) +(.+)";
        String itemRegex = "\\*+ +(.+)";
        String listRegex = "(\\*+ +.+\n?)+";
        Pattern headPattern = Pattern.compile(headRegex);
        Pattern itemPattern = Pattern.compile(itemRegex);
 
        StringBuffer stringBuffer = new StringBuffer();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("-1"))
                break;
            line = line.replaceAll(emRegex, "<em>$1</em>");
            line = line.replaceAll(linkRegex, "<a href=\"$2\">$1</a>");
            stringBuffer.append(line);
            stringBuffer.append("\n");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        scanner.close();
 
        Matcher matcher;
        String[] blocks = stringBuffer.toString().split("\n{2,}");
        for (String block : blocks) {
            if (block.matches(headRegex)) {
                matcher = headPattern.matcher(block);
                if (matcher.find()) {
                    int count = matcher.group(1).length();
                    System.out.println(String.format("<h%d>%s</h%d>", count, matcher.group(2), count));
                }
            } else if (block.matches(listRegex)) {
                System.out.println("<ul>");
                for(String item : block.split("\n")){
                    matcher = itemPattern.matcher(item);
                    if (matcher.find())
                        System.out.println(String.format("<li>%s</li>", matcher.group(1)));
                }
                System.out.println("</ul>");
            } else {
                System.out.println(String.format("<p>%s</p>", block));
            }
        }
    }
 
}