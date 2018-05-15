package Webcam;

import java.awt.image.BufferedImage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class ImageConverter 
{
    Mat material = new Mat();
    BufferedImage image;
    byte[] data;
    
    // Constructor
    public ImageConverter() { }
    
    // Constructor(Mat)
    public ImageConverter(Mat material) 
    {
        getSpace(material);
    }
    
    public void getSpace(Mat material) 
    {
        this.material = material;
        
        int width = material.cols();
        int height = material.rows();
        int channels = 3;
        
        if (data == null || data.length != width * height * channels)
        {
        	data = new byte[width * height * channels];
        }
        
        if (image == null || image.getWidth() != width || image.getHeight() != height || image.getType() != BufferedImage.TYPE_3BYTE_BGR)
        {
        	image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        }
                
        // colour correction
        Imgproc.cvtColor(material, material, Imgproc.COLOR_RGB2BGR);
    }
    
    BufferedImage getImage(Mat material)
    {
		getSpace(material);
		material.get(0, 0, data);
		image.getRaster().setDataElements(0, 0, material.cols(), material.rows(), data);
            
        return image;
    }
    
    static
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
}
