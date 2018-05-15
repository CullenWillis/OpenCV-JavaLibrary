package Algorithm;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import Constants.Constants;
import GUI.ImagePanel;

/*
 * PARAMETERS FOR FACE DETECTION
 * 
 * cascadeClassifier.detectMultiScale(image, faceDetections, scaleFactor, minNeighbours, flags, minSize, maxSize);
 * 
 * 1.) ScaleFactor:
 * 		Since some faces may be closer to the camera, they would appear bigger than other face in the back ->
 * 		the ScaleFactor compensates for this by specifying how much the image size is reduced at each image scale.
 * 
 * 		The model has a fixed size defined during training: in the .xml file!!!
 * 		By re-scaling the input image, you can resize a larger face to a smaller one, making it detectable by the
 * 		Algorithm.
 * 
 * 		Value: 1.1 - 1.4
 * 			Small -> Algorithm will be slow since it is more thorough (Quality)
 * 			high -> faster detection witch the risk of missing some faces altogether (Performance)
 * 2.) MinNeighbours: 
 * 		Specifying how many neighbours each candidate rectangle should have to retain it.
 * 
 * 		Value Interval: 3 - 6
 * 			Smaller Value -> more detection but with lower quality (Performance, and high success rate)
 * 			Higher Value -> less detections but with higher quality. (Quality, and low success rate)
 * 
 * 3.) Flags:
 * 		Kind of a heuristic. Rejects some image regions that may contain too few or too many edges and thus
 * 		can not contain the searched objects.
 * 
 * 4.) minSize:
 * 		objects smaller than the specified size are ignored!!!
 * 		we can specify what is the smallest object we want to recognise. ([30, 30] is the standard size)
 * 
 * 5.) maxSize:
 * 		objects larger than the specified size are ignored!!!
 * 		we can specify what is the largest object we want to recognise.
 */

public class FaceDetection {

	private CascadeClassifier cascadeClassifier;
	
	private double scaleFactor = 1.1;
	private int minNeighbours = 3;
	private int flags = 10;
	private Size minSize = new Size(30, 30);
	private Size maxSize = new Size(500, 500);
	
	public FaceDetection() 
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		this.cascadeClassifier = new CascadeClassifier(Constants.CASCADE_CLASSIFIER);
	}
	
	public void detectFaces(File file, ImagePanel imagePanel)
	{
		Mat image = Imgcodecs.imread(file.getAbsolutePath(), Imgcodecs.CV_LOAD_IMAGE_COLOR);
		
		MatOfRect faceDetections = new MatOfRect();
		cascadeClassifier.detectMultiScale(image, faceDetections, scaleFactor, minNeighbours, flags, minSize, maxSize);
		
		for(Rect rect : faceDetections.toArray())
		{
			Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(100, 100, 250), 10);
		}
		
		BufferedImage bufferedImage = convertMatToImage(image);
		imagePanel.updateImage(bufferedImage);
	}

	private BufferedImage convertMatToImage(Mat mat) {

		int type = BufferedImage.TYPE_BYTE_GRAY;
		
		if(mat.channels() > 1)
		{
			type = BufferedImage.TYPE_3BYTE_BGR;
		}
		
		int bufferSize = mat.channels() * mat.cols() * mat.rows();
		
		byte[] bytes = new byte[bufferSize];
		mat.get(0, 0, bytes);
		
		BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
		
		final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		
		System.arraycopy(bytes, 0, targetPixels, 0, bytes.length);
		
		return image;
	}
}
