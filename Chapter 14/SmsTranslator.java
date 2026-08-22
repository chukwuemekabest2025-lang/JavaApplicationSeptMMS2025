import java.util.*;

public class SmsTranslator {
    private static final Map<String, String> smsToEng = new HashMap<>();
    private static final Map<String, String> engToSms = new HashMap<>();

    static {
        addMapping("imo", "in my opinion");
        addMapping("brb", "be right back");
        addMapping("lol", "laugh out loud");
        addMapping("idk", "i don't know");
        addMapping("tbh", "to be honest");
        addMapping("btw", "by the way");
    }

    private static void addMapping(String sms, String eng) {
        smsToEng.put(sms, eng);
        engToSms.put(eng, sms);
    }

    public static String translateToEnglish(String sms) {
        String[] words = sms.toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            sb.append(smsToEng.getOrDefault(w, w)).append(" ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter SMS text: ");
        String sms = scanner.nextLine();

        System.out.println("English translation: " + translateToEnglish(sms));
        scanner.close();
    }
}