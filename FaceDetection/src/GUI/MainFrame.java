package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Algorithm.FaceDetection;
import Constants.Constants;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private ImagePanel imagePanel;
	private JFileChooser fileChooser;
	private FaceDetection faceDetection;
	private File file;
	
	public MainFrame()
	{
		super(Constants.APPLICATION_NAME); // Setup title
		
		setJMenuBar(createMenuBar()); // Setup menu bar
		
		// Instantiate variables
		this.imagePanel = new ImagePanel();
		this.fileChooser = new JFileChooser();
		this.faceDetection = new FaceDetection();
		
		add(imagePanel, BorderLayout.CENTER);
		
		setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Sets frame in the center of the screen
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
	}
	
	public JMenuBar createMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();
		
		// Create drop-down menu called File
		JMenu fileMenu = new JMenu("File");
		
		// Create buttons for the drop-down menu
		JMenuItem loadMenuItem = new JMenuItem("Load Image");
		JMenuItem detectMenuItem = new JMenuItem("Detect Faces");
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		
		// Add buttons to drop-down menu (File)
		fileMenu.add(loadMenuItem);
		fileMenu.add(detectMenuItem);
		fileMenu.add(exitMenuItem);
		
		// Add eventListener to load image button (Will open the dialog window)
		loadMenuItem.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent event) 
			{
				if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION)
				{
					MainFrame.this.file = fileChooser.getSelectedFile();
					
					// Load image (Maybe progress dialog)
					MainFrame.this.imagePanel.loadImage(MainFrame.this.file);
				}
			}
		});
		
		// Add eventListener to detect faces button
		detectMenuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				// Detection Algorithm
				MainFrame.this.faceDetection.detectFaces(MainFrame.this.file, MainFrame.this.imagePanel);
			}
		});
		
		// Help and About drop-down menu
		JMenu aboutMenu = new JMenu("About");
		JMenu helpMenu = new JMenu("Help");
		
		// Add drop-downs to the menu
		menuBar.add(fileMenu);
		menuBar.add(aboutMenu);
		menuBar.add(helpMenu);
		
		// Add eventListener to exit button
		exitMenuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				int action = JOptionPane.showConfirmDialog(MainFrame.this, Constants.EXIT_WARNING);
				
				if(action == JOptionPane.OK_OPTION)
				{
					System.gc();
					System.exit(0);
				}
			}
		});
		
		return menuBar;
	}
}
