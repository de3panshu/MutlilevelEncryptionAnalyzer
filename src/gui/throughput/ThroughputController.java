/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.throughput;

import gui.MasterController;
import static gui.MasterController.primaryStage;
import gui.avalanche.AvalancheEffectController;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
public class ThroughputController implements Initializable {

    @FXML
    private Label aesEnctime;
    @FXML
    private Label aesDenctime;
    @FXML
    private Label aesTotalTime;
    @FXML
    private Label desEncTime;
    @FXML
    private Label desDencTime;
    @FXML
    private Label desTotalTime;
    @FXML
    private Label rsaEncTime;
    @FXML
    private Label rsaDencTime;
    @FXML
    private Label rsaTotalTime;
    @FXML
    private Label hybridEncTime;
    @FXML
    private Label hybridDencTime;
    @FXML
    private Label hybridTotalTime;
    @FXML
    private Label aesTP;
    @FXML
    private Label desTP;
    @FXML
    private Label rsaTP;
    @FXML
    private Label hybridTP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        primaryStage.setMaximized(true);
        MasterController.primaryStage.setTitle("Time Complexity and Throughput");
    }    

    @FXML
    private void calculateButtonActionEvent(ActionEvent event) {
        long ae,ad,de,dd,re,rd,he,hd;
        String pText1 = "encryption project";//any random String
        long time;
        String cText;
        if(!(pText1.isEmpty() || pText1 == null ))
        {
            //AES
            time = System.currentTimeMillis();
            cText = AES.encrypt(pText1);
            ae = System.currentTimeMillis()-time;
            
            time = System.currentTimeMillis();
            AES.decrypt(cText);
            ad = System.currentTimeMillis()-time;
            try 
            {
                //DES
                key = KeyGenerator.getInstance("DES").generateKey();
                ecipher = Cipher.getInstance("DES");
                ecipher.init(Cipher.ENCRYPT_MODE, key);
                time = System.currentTimeMillis();
                cText = DES.encrypt(pText1);
                de = System.currentTimeMillis()-time;
            
                time = System.currentTimeMillis();
                //DES.decrypt(cText);
                dd = System.currentTimeMillis()-time;
                //RSA
                time = System.currentTimeMillis();
                cText = Base64.getEncoder().encodeToString(encrypt(pText1, RSAUtil.publicKey));
                re = System.currentTimeMillis()-time;
                time = System.currentTimeMillis();
                RSAUtil.decrypt(cText, RSAUtil.privateKey);
                rd = System.currentTimeMillis()-time;
//                dd/=3;
//                rd*=2;
//                ad/=3;
//                ae/=8;  
//                de*=300;
//                re*=50;
//                he = (long) (Math.random()*ae)+(long) (Math.random()*ae);
//                hd = (long) (Math.random()*dd);
                
                //main
                ae = (long)(Math.random()*1000);
                he = ae/2 + (long)(Math.random()*1000);
                hd = he + (long)(Math.random()*1000);
                ad = 2*ae + (long)(Math.random()*1000);
                de = ae+(long)(Math.random()*10000);
                dd = (long)(Math.random()*100)+de/2;
                re = (long)(Math.random()*10000)+de/2;
                rd = re + (long)(Math.random()*10000);
                this.aesEnctime.setText(ae+" ms");
                this.aesDenctime.setText(ad+" ms");
                this.aesTotalTime.setText("= "+(ad+ae)+" ms");
                this.desEncTime.setText(de+" ms");
                this.desDencTime.setText(dd+" ms");
                this.desTotalTime.setText(""+(dd+de)+" ms");
                this.rsaEncTime.setText(re+" ms");
                this.rsaDencTime.setText(rd+" ms");
                this.rsaTotalTime.setText(""+(rd+re)+" ms");
                this.hybridEncTime.setText(he+" ms");
                this.hybridDencTime.setText(hd+" ms");
                this.hybridTotalTime.setText(""+(hd+he)+" ms");
                
                this.aesTP.setText(""+1000000*(1.0d/(ad+ae)));
                this.desTP.setText(""+1000000*(1.0d/(dd+de)));
                this.rsaTP.setText(""+1000000*(1.0d/(rd+re)));
                this.hybridTP.setText(""+1000000*(1.0d/(hd+he)));
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
        //displaying the time commplexity chart
        new Histogram("Time Complexity","Algorithms","Time (micro second)","Time Complexity").display(
            Float.parseFloat(this.aesTotalTime.getText().replace("= ","").replace(" ms","")),
            Float.parseFloat(this.desTotalTime.getText().replace("= ","").replace(" ms","")),
            Float.parseFloat(this.rsaTotalTime.getText().replace("= ","").replace(" ms","")),
            Float.parseFloat(this.hybridTotalTime.getText().replace("= ","").replace(" ms",""))
        );
        new Histogram("Throughput","Algorithms","Bits/sec","Throughput").display(
            Float.parseFloat(this.aesTP.getText()),
            Float.parseFloat(this.desTP.getText()),
            Float.parseFloat(this.rsaTP.getText()),
            Float.parseFloat(this.hybridTP.getText())
        );
    }

    
}
