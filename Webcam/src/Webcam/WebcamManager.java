package Webcam;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WebcamManager extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private Webcam videoCap;
	
	public WebcamManager() 
	{
		videoCap = new Webcam();
		contentPane = new JPanel();
		
		frameSetup();
  
        new DrawThread().start();
    }
	
	private void frameSetup()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 650, 490);
        
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(contentPane);
        contentPane.setLayout(null);
	}
 
    public void paint(Graphics graphics)
    {
    	graphics = contentPane.getGraphics();
    	graphics.drawImage(videoCap.getOneFrame(), 0, 0, this);
    }
 
    class DrawThread extends Thread
    {
        @Override
        public void run() 
        {
            for (;;)
            {
                repaint();
                
                try 
                { 
                	Thread.sleep(30);
                } 
                catch (InterruptedException e) {    }
            }  
        } 
    }
}
