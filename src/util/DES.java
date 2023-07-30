package util;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
 
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
 
import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;
 
public class DES {
 
    public static Cipher ecipher;
    public static Cipher dcipher;
 
    public static SecretKey key;
 
    
    public static String encrypt(String str) {
 
  try {
 
    // encode the string into a sequence of bytes using the named charset
 
    // storing the result into a new byte array. 
 
    byte[] utf8 = str.getBytes("UTF8");
 
byte[] enc = ecipher.doFinal(utf8);
 
// encode to base64
 
enc = BASE64EncoderStream.encode(enc);
 
return new String(enc);
 
  }
 
  catch (Exception e) {
 
    e.printStackTrace();
 
  }
 
  return null;
 
    }
 
    public static String decrypt(String str) {
 
  try {
 
    // decode with base64 to get bytes
 
byte[] dec = BASE64DecoderStream.decode(str.getBytes());
 
byte[] utf8 = dcipher.doFinal(dec);
 
// create new string based on the specified charset
 
return new String(utf8, "UTF8");
 
  }
 
  catch (Exception e) {
 
    e.printStackTrace();
 
  }
 
  return null;
 
    }
 
}