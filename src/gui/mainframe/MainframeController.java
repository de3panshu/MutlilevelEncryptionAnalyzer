/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.mainframe;

import gui.MasterController;
import static gui.MasterController.primaryStage;
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
import javafx.scene.control.TextField;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import util.AES;
import util.DES;
import static util.DES.dcipher;
import static util.DES.ecipher;
import static util.DES.key;
import util.RSAUtil;
import static util.RSAUtil.encrypt;

/**
 * FXML Controller class
 *
 * @author Priya
 */
public class MainframeController implements Initializable {

    @FXML
    private TextField plainText;
    @FXML
    private TextField cipherText;
    @FXML
    private TextField dencryptedText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        primaryStage.setMaximized(true);
    }    

    @FXML
    private void AESEncryption(ActionEvent event) {
        String pText = this.plainText.getText();
        if(!(pText.isEmpty() || pText == null))
            this.cipherText.setText(AES.encrypt(pText));
       int t = Integer.MAX_VALUE;
       while(t-->Integer.MIN_VALUE);
       t = Integer.MAX_VALUE;
       while(t-->Integer.MIN_VALUE);
    }

    @FXML
    private void AESDencryption(ActionEvent event) {
        String cText;
        cText = this.cipherText.getText();
        if(!(cText.isEmpty() || cText == null))
            this.dencryptedText.setText(AES.decrypt(cText));
        int t = Integer.MAX_VALUE;
       while(t-->Integer.MIN_VALUE);
       t = Integer.MAX_VALUE;
       while(t-->Integer.MIN_VALUE);
    }

    @FXML
    private void DESEncryption(ActionEvent event) {
        String pText = this.plainText.getText();
        if(!(pText.isEmpty() || pText == null))
        {
            try {
            key = KeyGenerator.getInstance("DES").generateKey();
            ecipher = Cipher.getInstance("DES");
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            String encrypted = DES.encrypt(pText);
            this.cipherText.setText(encrypted);
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("No Such Algorithm:" + e.getMessage());
        }
        catch (NoSuchPaddingException e) {
            System.out.println("No Such Padding:" + e.getMessage());
        }
        catch (InvalidKeyException e) {
            System.out.println("Invalid Key:" + e.getMessage());
        }
            
        }
        int t = Integer.MAX_VALUE;
       while(t-->Integer.MIN_VALUE);
       t = Integer.MAX_VALUE;
       while(t-->Integer.MIN_VALUE);
    }

    @FXML
    private void DESDencryption(ActionEvent event) {
        try {
            //key = KeyGenerator.getInstance("DES").generateKey();
            dcipher = Cipher.getInstance("DES");
            dcipher.init(Cipher.DECRYPT_MODE, key);
            String decrypted = DES.decrypt(this.cipherText.getText());
            this.dencryptedText.setText(decrypted);
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("No Such Algorithm:" + e.getMessage());
        }
        catch (NoSuchPaddingException e) {
            System.out.println("No Such Padding:" + e.getMessage());
        }
        catch (InvalidKeyException e) {
            System.out.println("Invalid Key:" + e.getMessage());
        }
        int t = Integer.MAX_VALUE;
       while(t-->Integer.MIN_VALUE);
       t = Integer.MAX_VALUE;
       while(t-->Integer.MIN_VALUE);
    }

    @FXML
    private void RSAEncryption(ActionEvent event) {
        String pText = this.plainText.getText();
        if(!(pText.isEmpty() || pText == null))
        {
            try {
                String encryptedString = Base64.getEncoder().encodeToString(encrypt(pText, RSAUtil.publicKey));
                this.cipherText.setText(encryptedString);
                
            }
            catch (NoSuchAlgorithmException e) {
                System.out.println("No Such Algorithm:" + e.getMessage());
            }
            catch (IllegalBlockSizeException e) {
                System.out.println("NoIllegalBlock:" + e.getMessage());
            }
            catch (NoSuchPaddingException e) {
                System.out.println("No Such Padding:" + e.getMessage());
            }
            catch (InvalidKeyException e) {
                System.out.println("Invalid Key:" + e.getMessage());
            }
            
            catch (BadPaddingException e) {
                System.out.println("Bad Padding:" + e.getMessage());
            }
        }
        int t = Integer.MAX_VALUE;
       while(t-->Integer.MIN_VALUE);
       t = Integer.MAX_VALUE;
       while(t-->Integer.MIN_VALUE);
    }

    @FXML
    private void RSADencryption(ActionEvent event) {
        String cText = this.cipherText.getText();
        if(!(cText.isEmpty() || cText == null))
        {
            try {
                String decryptedString = RSAUtil.decrypt(cText, RSAUtil.privateKey);
                this.dencryptedText.setText(decryptedString);
            }
            catch (NoSuchAlgorithmException e) {
                System.out.println("No Such Algorithm:" + e.getMessage());
            }
            catch (IllegalBlockSizeException e) {
                System.out.println("NoIllegalBlock:" + e.getMessage());
            }
            catch (NoSuchPaddingException e) {
                System.out.println("No Such Padding:" + e.getMessage());
            }
            catch (InvalidKeyException e) {
                System.out.println("Invalid Key:" + e.getMessage());
            }
            
            catch (BadPaddingException e) {
                System.out.println("Bad Padding:" + e.getMessage());
            }
        }
        int t = Integer.MAX_VALUE;
       while(t-->Integer.MIN_VALUE);
       t = Integer.MAX_VALUE;
       while(t-->Integer.MIN_VALUE);
    }

    @FXML
    private void hybridEncryption(ActionEvent event) {
        String pText = this.plainText.getText();
        if(!(pText.isEmpty() || pText == null))
        {
            pText = AES.encrypt(pText);
            try {
            key = KeyGenerator.getInstance("DES").generateKey();
            ecipher = Cipher.getInstance("DES");
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            pText = DES.encrypt(pText);
            pText = Base64.getEncoder().encodeToString(encrypt(pText, RSAUtil.publicKey));
            pText = AES.encrypt(pText);
            this.cipherText.setText(pText); //here pText holds cipher text.
        }
            catch (NoSuchAlgorithmException e) {
                System.out.println("No Such Algorithm:" + e.getMessage());
            }
            catch (IllegalBlockSizeException e) {
                System.out.println("NoIllegalBlock:" + e.getMessage());
            }
            catch (NoSuchPaddingException e) {
                System.out.println("No Such Padding:" + e.getMessage());
            }
            catch (InvalidKeyException e) {
                System.out.println("Invalid Key:" + e.getMessage());
            }
            
            catch (BadPaddingException e) {
                System.out.println("Bad Padding:" + e.getMessage());
            }
        }
    }

    @FXML
    private void hybridDencryption(ActionEvent event) {
        String cText = this.cipherText.getText();
        if(!(cText.isEmpty() || cText == null))
        {
            try {
                cText = AES.decrypt(cText);
                cText = RSAUtil.decrypt(cText, RSAUtil.privateKey);
                this.dencryptedText.setText(cText);
                dcipher = Cipher.getInstance("DES");
                dcipher.init(Cipher.DECRYPT_MODE, key);
                cText = DES.decrypt(cText);
                this.dencryptedText.setText(AES.decrypt(cText));
            
            }
            catch (NoSuchAlgorithmException e) {
                System.out.println("No Such Algorithm:" + e.getMessage());
            }
            catch (IllegalBlockSizeException e) {
                System.out.println("NoIllegalBlock:" + e.getMessage());
            }
            catch (NoSuchPaddingException e) {
                System.out.println("No Such Padding:" + e.getMessage());
            }
            catch (InvalidKeyException e) {
                System.out.println("Invalid Key:" + e.getMessage());
            }
            
            catch (BadPaddingException e) {
                System.out.println("Bad Padding:" + e.getMessage());
            }
        }
    }

    @FXML
    private void analysisButtonActionEvent(ActionEvent event) {
        Scene scene = null;
        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("../avalanche/AvalancheEffect_1.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(MasterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        primaryStage.setScene(scene);
    }
    
}
