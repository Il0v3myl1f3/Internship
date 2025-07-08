import java.io.*;
import java.util.*;

public class Test {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java Anagram <file_name>");
            return;
        }

        String fileName = args[0];
        Map<String, List<String>> anagramGroups = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String word;

            while ((word = reader.readLine()) != null) {

                if (word.isEmpty()) continue;

                String signature = sortLetters(word);
                anagramGroups.computeIfAbsent(signature, k -> new ArrayList<>()).add(word);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        for (List<String> group : anagramGroups.values()) {
            System.out.println(String.join(" ", group));
        }
    }

    private static String sortLetters(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
