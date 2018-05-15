package App;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import GUI.MainFrame;

public class App {

	public static void main(String[] args) {
		
		// Load system look and feel (Shows OS Features)
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}

		// Create new thread for the application
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run()
			{
				new MainFrame();
			}
		});
	}
}
