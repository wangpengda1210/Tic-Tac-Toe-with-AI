import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        // put your code here
        parseHtmlTag(new Scanner(System.in).nextLine());
    }

    private static void parseHtmlTag(String outerHtml) {
        Pattern p = Pattern.compile("<([\\w]+)>(.*)</\\1>", Pattern.MULTILINE | Pattern.DOTALL);
        Matcher m = p.matcher(outerHtml);

        m.results().forEach(r -> {
            String tagContent = r.group(2);
            parseHtmlTag(tagContent);
            System.out.println(tagContent);
        });
    }
}