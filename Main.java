import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        readAllLines();


    }

    private static List<String> readAllLines()  {

        List<String> lines = null;
        String path = "passwords/100k.txt";
        int count = 1;
        try {
            lines = new ArrayList<>();
            lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                System.out.println(count ++);

                if (HashWithoutSalt.createHashWithoutSalt(line).equals("5723360ef11043a879520412e9ad897e0ebcb99cc820ec363bfecc9d751a1a99")) {
                   // e7cf3ef4f17c3999a94f2c6f612e8a888e5b1026878e4e19398b23bd38ec221a
                    System.out.println(line);
                    break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return lines;
    }


}
