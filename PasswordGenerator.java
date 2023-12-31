import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter the length of the password: ");
            int length = scanner.nextInt();
            if (length <= 0) {
                System.out.println("Length should be a positive integer.");
                return;
            }

            System.out.print("Enter the complexity (1 for numbers only, 2 for letters only, 3 for numbers and letters): ");
            int complexity = scanner.nextInt();
            if (complexity < 1 || complexity > 3) {
                System.out.println("Complexity should be 1, 2, or 3.");
                return;
            }

            String password = generatePassword(length, complexity);
            System.out.println("Generated Password: " + password);
        } finally {
            scanner.close();
        }
    }

    public static String generatePassword(int length, int complexity) {
        String numbers = "0123456789";
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String characters = "";

        if (complexity == 1) {
            characters = numbers;
        } else if (complexity == 2) {
            characters = letters;
        } else if (complexity == 3) {
            characters = numbers + letters;
        } else {
            throw new IllegalArgumentException("Invalid complexity level");
        }

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}