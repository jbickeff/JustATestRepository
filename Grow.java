package scr.Gui;
import javafx.stage.Stage;

/*
 * the grow class simply makes the stage grow that called it
 */
public class Grow implements Runnable
{
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run()
	{
		Stage changing = Glory.getInstance().getStage();
	
		while (Glory.getInstance().shouldThreadBeRunning())
		{
			System.out.println("growing");
			double width = changing.getWidth();
			changing.setWidth(width*2);
			try
			{
				Thread.sleep(1500);
			}
			catch (Exception e)
			{
			}
		}
	}
}
