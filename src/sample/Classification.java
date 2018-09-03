/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.File;


/**
 * @author ВиКо
 */
public class Classification {

    public void voiceClassification(String trainPath, String testPath) {
        File trainDirectoryFile = new File(trainPath);
        File testDirectoryFile = new File(testPath);

        File[] trainWaveFiles = trainDirectoryFile.listFiles();
        File[] testWaveFiles = testDirectoryFile.listFiles();

        //TEST

        System.out.println("Start reading");
        WaveFileReader waveFileReader = new WaveFileReader(trainWaveFiles[0].getPath());
        System.out.println("Finish reading");

        PreProcess preProcess = new PreProcess(waveFileReader.getData(), 512, waveFileReader.getSampleRate());
        System.out.println("NoOfFrames " + preProcess.noOfFrames);

        FeatureExtract featureExtract = new FeatureExtract(preProcess.framedSignal, waveFileReader.getSampleRate(), 512);
        featureExtract.makeMfccFeatureVector();

        double[][] featureVectors = featureExtract.getFeatureVector().getFeatureVector();


        //TEST

    }

    ;

}
