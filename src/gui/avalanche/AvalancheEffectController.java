
package gui.avalanche;

import gui.MasterController;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import util.AES;
import util.DES;
import static util.DES.ecipher;
import static util.DES.key;
import util.Histogram;
import util.RSAUtil;
import static util.RSAUtil.encrypt;

/**
 * FXML Controller class
 *
 * @author Priya
 */
public class AvalancheEffectController implements Initializable {

    @FXML
    private TextField input1;
    @FXML
    private TextField input2;
    @FXML
    private Label aes;
    @FXML
    private Label des;
    @FXML
    private Label rsa;
    @FXML
    private Label hybrid;
    
    private String cipher1;
    private String cipher2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MasterController.primaryStage.setTitle("Avalanche Effect");
    }    
    private float analyse(char[] str1, char[] str2)
    {
        int c=0,i,n=str1.length<str2.length?str1.length:str2.length;
        for(i=0;i<n;i++)
        {
            if(str1[i] != str2[i])
                c++;
        }
        return (float)((int)(((c*100.0f)/n)*100)/100.0f);
    }
    @FXML
    private void analysisButtonActionEvent(ActionEvent event) {
        
        
        String pText1 = this.input1.getText();
        String pText2 = this.input2.getText();
        if(!(pText1.isEmpty() || pText1 == null || pText2.isEmpty() || pText2 == null))
        {
            //AES
            cipher1 = AES.encrypt(pText1);
            cipher2 = AES.encrypt(pText2);
            aes.setText(""+analyse(cipher1.toCharArray(),cipher2.toCharArray())+"%");
            
            
            try 
            {
                //DES
                key = KeyGenerator.getInstance("DES").generateKey();
                ecipher = Cipher.getInstance("DES");
                ecipher.init(Cipher.ENCRYPT_MODE, key);
                cipher1 = DES.encrypt(pText1);
                cipher2 = DES.encrypt(pText2);
                des.setText(analyse(cipher1.toCharArray(),cipher2.toCharArray())+"%");
                
                //RSA
                cipher1 = Base64.getEncoder().encodeToString(encrypt(pText1, RSAUtil.publicKey));
                cipher2 = Base64.getEncoder().encodeToString(encrypt(pText2, RSAUtil.publicKey));
                rsa.setText(analyse(cipher1.toCharArray(),cipher2.toCharArray())+"%");
                
                //hybrid
                cipher1 = Base64.getEncoder().encodeToString(encrypt(DES.encrypt(AES.encrypt(pText1)), RSAUtil.publicKey));
                cipher2 = Base64.getEncoder().encodeToString(encrypt(DES.encrypt(AES.encrypt(pText2)), RSAUtil.publicKey));
                hybrid.setText(analyse(cipher1.toCharArray(),cipher2.toCharArray())+"%");
            }
            catch (NoSuchAlgorithmException e) {
                System.out.println("No Such Algorithm:" + e.getMessage());
            }
            catch (NoSuchPaddingException e) {
                System.out.println("No Such Padding:" + e.getMessage());
            }
            catch (InvalidKeyException e) {
                System.out.println("Invalid Key:" + e.getMessage());
            } catch (BadPaddingException ex) {
                Logger.getLogger(AvalancheEffectController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(AvalancheEffectController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @FXML
    private void displayButtonActionEvent(ActionEvent event) {
        new Histogram().display(
                Float.parseFloat(aes.getText().subSequence(0,aes.getText().length()-1).toString()),
                Float.parseFloat(des.getText().subSequence(0,aes.getText().length()-1).toString()),
                Float.parseFloat(rsa.getText().subSequence(0,aes.getText().length()-1).toString()),
                Float.parseFloat(hybrid.getText().subSequence(0,aes.getText().length()-1).toString())
        );
    //System.out.println(aes.getText().subSequence(0,aes.getText().length()).toString());
    }

    @FXML
    private void nextButtonActionEvent(ActionEvent event) {
        Scene scene=null;
        try
        {
            scene = new Scene(FXMLLoader.load(getClass().getResource("../throughput/Throughput_1.fxml")),1000,600);
        } catch (IOException ex) {
            Logger.getLogger(AvalancheEffectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        MasterController.primaryStage.setScene(scene);
    }
    
}
