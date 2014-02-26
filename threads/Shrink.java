package scr.Gui;
import javafx.stage.Stage;

/*
 * the Shrink class just shrinks the stage
 */
public class Shrink 
extends Thread
{

	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run()
	{
		while (Glory.getInstance().shouldThreadBeRunning())
		{
			System.out.println("shrinking");
			Stage mStage = Glory.getInstance().getStage();
			double width = mStage.getWidth();
			mStage.setWidth(width / 2);
			try
			{
				Thread.sleep(1500);
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
	}
}
