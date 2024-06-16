import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Hash {
      public static String hash(String input) {
          MessageDigest md = null;
          try {
              md = MessageDigest.getInstance("SHA-256");
          } catch (NoSuchAlgorithmException e) {
              e.printStackTrace();
          }
          md.update(input.getBytes());
          //Message digest
          byte[] digest = md.digest();

          //bytearray to hex string format
          StringBuilder hexString = new StringBuilder();
          for (byte b : digest) {
              String temp = Integer.toHexString(0xFF & b);
              if (temp.length() == 1) {
                  hexString.append("0");
              }
              hexString.append(temp);
          }
          //string hex value of the generate hash
          String to_return = hexString.toString();
          return to_return;
      }
}
