import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class CaesarCipher  {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String str;
        try {
            while (true) {
                System.out.println("Do you wish to encrypt or decrypt a message?");
                str = scanner.next();
                if (str.equals("encrypt"))
                    encrypt();
                else if (str.equals("decrypt"))
                    decrypt();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    // Get shifting key number
    public static int getKeyShift(){
        System.out.println("Enter the key number (1-52): ");
        Scanner scanner = new Scanner(System.in);
        int i= scanner.nextInt();
        while (true){
            if (i>0 && i<52){
                break;
            }
            i = scanner.nextInt();
        }
        return i;
    }
    // Encrypt/Decrypt a string, given a key number
    public static String cipher(String str, int shift){
        String result = "";
        for (int i=0; i<str.length(); i++){
            if (!Character.isAlphabetic(str.charAt(i))) {
                result += str.charAt(i);
                continue;
            }
            if (Character.isUpperCase(str.charAt(i)))
                result += (char)(((int)str.charAt(i) + shift - 65) % 26 + 65);
            else
                result += (char)(((int)str.charAt(i) + shift - 97) % 26 + 97);
        }
        return result;
    }

    public static void encrypt() throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your message: ");
        String str = scanner.nextLine();
        int shift = getKeyShift();

        String encryption = cipher(str, shift);
        Files.write(Paths.get("encrypt.log"), encryption.getBytes());
        System.out.println(encryption);
    }

    public static void decrypt() throws IOException{
        int shift = getKeyShift();
        String str = Files.readString(Paths.get("encrypt.log"));

        String decryption = cipher(str, shift);
        System.out.println(decryption);
    }
}
