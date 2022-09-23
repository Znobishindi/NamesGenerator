import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final List<String> beautifulWords = new ArrayList<>();
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int NUMBER_OF_WORDS = 100_000;

    public static void main(String[] args) {
        Random random = new Random();
        String[] texts = new String[NUMBER_OF_WORDS];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
            if (isPalindrome(texts[i])) {
                beautifulWords.add(texts[i]);
            }
            if (isNext(texts[i])) {
                beautifulWords.add(texts[i]);
            }
        }

        new Thread(() -> {
            AtomicInteger ai = new AtomicInteger(0);
            for (String beautifulWord : beautifulWords) {
                if (beautifulWord.length() == THREE) {
                    ai.incrementAndGet();
                }
            }

            System.out.println("Количество красивых слов с длиной " + THREE + " равно " + ai);
        }).start();

        new Thread(() -> {
            AtomicInteger ai = new AtomicInteger(0);
            for (String beautifulWord : beautifulWords) {
                if (beautifulWord.length() == FOUR) {
                    ai.incrementAndGet();
                }
            }

            System.out.println("Количество красивых слов с длиной " + FOUR + " равно " + ai);
        }).start();

        new Thread(() -> {
            AtomicInteger ai = new AtomicInteger(0);
            for (String beautifulWord : beautifulWords) {
                if (beautifulWord.length() == FIVE) {
                    ai.incrementAndGet();
                }
            }

            System.out.println("Количество красивых слов с длиной " + FIVE + " равно " + ai);
        }).start();

    }

    private static boolean isNext(String input) {
        int pre = 0;
        int end = input.length() - 1;
        while (end > pre) {
            if (input.charAt(pre) > input.charAt(pre + 1)) {
                return false;
            }
            pre++;

        }
        return true;
    }


    private static boolean isPalindrome(String input) {
        int pre = 0;
        int end = input.length() - 1;
        while (end > pre) {
            if (input.charAt(pre) != input.charAt(end)) {
                return false;
            }
            pre++;
            end--;
        }
        return true;
    }


    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }


}

