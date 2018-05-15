package GUI;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Constants.Constants;

public class ImagePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel imageLabel;
	private ImageIcon transformedImageIcon;
	
	
	// Contains image
	public ImagePanel() 
	{
		this.imageLabel = new JLabel();
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(Constants.IMAGE_LABEL_BORDER, Constants.IMAGE_LABEL_BORDER, Constants.IMAGE_LABEL_BORDER, Constants.IMAGE_LABEL_BORDER));
	
		add(imageLabel, BorderLayout.CENTER);
	}
	
	// Updates image
	public void updateImage(final Image image)
	{
		imageLabel.setIcon(new ImageIcon(scaleImage(image)));
	}
	
	// Scaling image
	private Image scaleImage(Image image)
	{
		return image.getScaledInstance(700, 500, Image.SCALE_SMOOTH);
	}
	
	// Load image
	public void loadImage(File file)
	{
		// Store reference to transport icon
		this.transformedImageIcon = new ImageIcon(file.getAbsolutePath());
		Image image = transformedImageIcon.getImage();
		
		// Update image after transformation
		updateImage(image);
	}

}
