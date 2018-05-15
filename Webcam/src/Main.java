import java.awt.EventQueue;

import Webcam.WebcamManager;

public class Main
{
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
            public void run() 
            {
                try 
                {
                    WebcamManager frame = new WebcamManager();
                    frame.setVisible(true);
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
        });
	}
	}