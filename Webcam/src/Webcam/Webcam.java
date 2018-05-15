package Webcam;

import java.awt.image.BufferedImage;

import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;

public class Webcam 
{
    static
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    VideoCapture videoCapture;
    ImageConverter converter;

    public Webcam()
    {
    	videoCapture = new VideoCapture();
    	converter = new ImageConverter();
    	
    	startCapturing();
    }
    
    private void startCapturing()
    {
    	videoCapture.open(0);
    }
 
    public BufferedImage getOneFrame() 
    {
    	videoCapture.read(converter.material);
        return converter.getImage(converter.material);
    }

}
