/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * @author ВиКо
 */
public class Controller implements Initializable {

    private String trainFolderPath;

    private String testFolderPath;
    @FXML
    private Button button;

    @FXML
    private void handleButtonActionFileClose(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleButtonActionSelectTrain(ActionEvent event) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int ret = jFileChooser.showDialog(null, "Open folder");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            if (file.isDirectory()) {
                trainFolderPath = file.getPath();
            } else {
                JOptionPane.showMessageDialog(null, "Wrong folder");
            }
        }
    }

    @FXML
    private void handleButtonActionSelectTest(ActionEvent event) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int ret = jFileChooser.showDialog(null, "Open folder");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            if (file.isDirectory()) {
                testFolderPath = file.getPath();
            } else {
                JOptionPane.showMessageDialog(null, "Wrong folder");
            }
        }
    }

    @FXML
    private void handleButtonActionTestingRead(ActionEvent event) {
        JFileChooser jFileChooser = new JFileChooser();
        int ret = jFileChooser.showDialog(null, "Open waveFile");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            if (file.isFile()) {
                String waveFilePath = file.getPath();
                WaveFileReader waveFile = new WaveFileReader(waveFilePath);
            } else {
                JOptionPane.showMessageDialog(null, "Wrong file");
            }
        }
    }

    @FXML
    private void handleButtonActionStart(ActionEvent event) {
        if (testFolderPath == "" || trainFolderPath == "") {
            JOptionPane.showMessageDialog(null, "Choose folders of test and trains");
        } else {
            Classification classificator = new Classification();
            classificator.voiceClassification(trainFolderPath, testFolderPath);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
