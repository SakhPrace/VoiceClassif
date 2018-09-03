package sample;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * @author ВиКо
 */
public class WaveFileReader {

    private char[] chunkId;

    private int chunkSize;

    private char[] format;

    private char[] subchunk1Id;

    private int subchunk1Size;

    private int audioFormat;

    private int numChannels;

    private int sampleRate;

    private int byteRate;

    private int blockAlign;

    private int bitsPerSample;

    private char[] subchunk2Id;

    private int subchunk2Size;

    private float[] data;

    public WaveFileReader() {
    }

    ;

    public WaveFileReader(String wavFilePath) {

        readWavFile(wavFilePath);

    }

    ;

    public void readWavFile(String wavFilePath) {


        try (FileInputStream fin = new FileInputStream(wavFilePath)) {
            System.out.printf("File size: %d bytes \n", fin.available());
            int i;
            int size = fin.available();
            byte[] tempByte = new byte[4];
            i = fin.read(tempByte);
            chunkId = new char[4];
            chunkId[0] = (char) tempByte[0];
            chunkId[1] = (char) tempByte[1];
            chunkId[2] = (char) tempByte[2];
            chunkId[3] = (char) tempByte[3];
            System.out.println(chunkId);

            tempByte = new byte[4];
            i = fin.read(tempByte);
            ByteBuffer byteBuffer = ByteBuffer.wrap(byteReverse(tempByte));
            chunkSize = byteBuffer.getInt();
            System.out.println(chunkSize);

            tempByte = new byte[4];
            i = fin.read(tempByte);
            format = new char[4];
            format[0] = (char) tempByte[0];
            format[1] = (char) tempByte[1];
            format[2] = (char) tempByte[2];
            format[3] = (char) tempByte[3];
            System.out.println(format);

            tempByte = new byte[4];
            i = fin.read(tempByte);
            subchunk1Id = new char[4];
            subchunk1Id[0] = (char) tempByte[0];
            subchunk1Id[1] = (char) tempByte[1];
            subchunk1Id[2] = (char) tempByte[2];
            subchunk1Id[3] = (char) tempByte[3];
            System.out.println(subchunk1Id);

            tempByte = new byte[4];
            i = fin.read(tempByte);
            byteBuffer = ByteBuffer.wrap(byteReverse(tempByte));
            subchunk1Size = byteBuffer.getInt();
            System.out.println(subchunk1Size);

            tempByte = new byte[2];
            i = fin.read(tempByte);
            byteBuffer = ByteBuffer.wrap(byteReverse(tempByte));
            audioFormat = byteBuffer.getShort();
            System.out.println(audioFormat);

            tempByte = new byte[2];
            i = fin.read(tempByte);
            byteBuffer = ByteBuffer.wrap(byteReverse(tempByte));
            numChannels = byteBuffer.getShort();
            System.out.println(numChannels);

            tempByte = new byte[4];
            i = fin.read(tempByte);
            byteBuffer = ByteBuffer.wrap(byteReverse(tempByte));
            sampleRate = byteBuffer.getInt();
            System.out.println(sampleRate);

            tempByte = new byte[4];
            i = fin.read(tempByte);
            byteBuffer = ByteBuffer.wrap(byteReverse(tempByte));
            byteRate = byteBuffer.getInt();
            System.out.println(byteRate);

            tempByte = new byte[2];
            i = fin.read(tempByte);
            byteBuffer = ByteBuffer.wrap(byteReverse(tempByte));
            blockAlign = byteBuffer.getShort();
            System.out.println(blockAlign);

            tempByte = new byte[2];
            i = fin.read(tempByte);
            byteBuffer = ByteBuffer.wrap(byteReverse(tempByte));
            bitsPerSample = byteBuffer.getShort();
            System.out.println(bitsPerSample);

            tempByte = new byte[4];
            i = fin.read(tempByte);
            subchunk2Id = new char[4];
            subchunk2Id[0] = (char) tempByte[0];
            subchunk2Id[1] = (char) tempByte[1];
            subchunk2Id[2] = (char) tempByte[2];
            subchunk2Id[3] = (char) tempByte[3];
            System.out.println(subchunk2Id);

            tempByte = new byte[4];
            i = fin.read(tempByte);
            byteBuffer = ByteBuffer.wrap(byteReverse(tempByte));
            subchunk2Size = byteBuffer.getInt();
            System.out.println(subchunk2Size);

            data = new float[(size - 44) / blockAlign + 1];
            System.out.println("SOSOSO " + data.length);
            int k = -1;
            while (i != -1) {
                k++;
                tempByte = new byte[blockAlign];
                i = fin.read(tempByte);
                if (i == -1) {
                    break;
                }
                byteBuffer = ByteBuffer.wrap(byteReverse(tempByte));
                data[k] = byteBuffer.getShort();
                System.out.println(data[k]);

            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

    }

    ;

    private byte[] byteReverse(byte[] byteIn) {
        int n = byteIn.length;
        byte[] byteOut = new byte[n];
        int k = 1;
        for (byte byte1 : byteIn) {
            byteOut[n - k] = byte1;
            k++;
        }
        return byteOut;
    }

    ;

    public char[] getChunkId() {
        return chunkId;
    }

    ;

    public int getChunkSize() {
        return chunkSize;
    }

    ;

    public char[] getFormat() {
        return format;
    }

    ;

    public char[] getSubchunk1Id() {
        return subchunk1Id;
    }

    ;

    public int getSubchunk1Size() {
        return subchunk1Size;
    }

    ;

    public int getAudioFormat() {
        return audioFormat;
    }

    ;

    public int getNumChannels() {
        return numChannels;
    }

    ;

    public int getSampleRate() {
        return sampleRate;
    }

    ;

    public int getByteRate() {
        return byteRate;
    }

    ;

    public int getBlockAlign() {
        return blockAlign;
    }

    ;

    public int getBitsPerSample() {
        return bitsPerSample;
    }

    ;

    public char[] getSubchunk2Id() {
        return subchunk2Id;
    }

    ;

    public int getSubchunk2Size() {
        return subchunk2Size;
    }

    ;

    public float[] getData() {
        return data;
    }

    ;

}
