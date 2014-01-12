/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ignis.v;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 *
 * @author henrypitcairn
 */
public class SampleController implements Initializable {
    
    private String target;
    private int numThreads;
    private UDPFlood udpflood;
    @FXML
    private TextField threadField;
    
    @FXML
    private TextField addrField;
    
    @FXML
    private Button attackBtn;
    
    @FXML
    private Slider threadSlider;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Attack clicked");
        handleAttack();
    }
    public void handleAttack() {
        target = addrField.getText();
        numThreads = (int) threadSlider.getValue();
        udpflood.setTarget(target);
        udpflood.setThreads(numThreads);
        if (udpflood.isRunning()) {
            attackBtn.setText("Start Attack");
            udpflood.stop();
        }
        else {
            attackBtn.setText("Stop Attack");
            udpflood.start();
        }
    }
    @FXML
    private void handleTextFieldAction() {
        target = addrField.getText();
    }
    
    @FXML
    private void handleSliderAction() {
        threadField.setText(Double.toString((int) threadSlider.getValue()));
        numThreads = (int)threadSlider.getValue();
        System.out.println((int)threadSlider.getValue());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        udpflood = new UDPFlood();
    }    
}
