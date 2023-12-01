package at.stephanpesch.adventofcode2023.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day01 {
    private static List<String> readFile(String filename) {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            return stream.toList();
        } catch (IOException e) {
            return List.of();
        }
    }

    private static int createNumber(String line) {
        List<Character> digits = line.chars().mapToObj(c -> (char) c).filter(Character::isDigit).toList();
        return Integer.parseInt(digits.get(0) + "" + digits.get(digits.size() - 1));
    }

    private static int sum(List<String> lines) {
        return lines.stream().mapToInt(Day01::createNumber).sum();
    }

    public static int solve1(String filename) {
        List<String> lines = readFile(filename);
        return sum(lines);
    }

    public static String replaceNumberStringToInt(String line) {
        StringBuilder sb = new StringBuilder();

        Map<String, Integer> numberMap =
                Map.of("zero", 0,
                        "one", 1,
                        "two", 2,
                        "three", 3,
                        "four", 4,
                        "five", 5,
                        "six", 6,
                        "seven", 7,
                        "eight", 8,
                        "nine", 9);

        IntStream.range(0, line.length())
                .forEach(i -> IntStream.range(i, line.length() + 1)
                        .forEach(j -> {
                                    if (Character.isDigit(line.charAt(i))) {
                                        sb.append(line.charAt(i));
                                    } else {
                                        if (numberMap.containsKey(line.substring(i, j))) {
                                            sb.append(numberMap.get(line.substring(i, j)));
                                        }
                                    }
                                }
                        )
                );

        return sb.toString();
    }


    public static int solve2(String filename) {
        List<String> lines = readFile(filename);
        List<String> newLines = lines.stream().map(Day01::replaceNumberStringToInt).toList();
        return sum(newLines);
    }
}
