package scr.Gui;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
 * RunAll class changes the stage colors 
 */
public class RunAll implements Runnable
{
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run()
	{
		Stage mStage = Glory.getInstance().getStage();
		int i = 0;
		while (Glory.getInstance().shouldThreadBeRunning())
		{
			changeColor(i++, mStage);
			System.out.println("changing colors");
			try
			{
				Thread.sleep(1500);
			}
			catch (Exception e)
			{
			}
			if (i >= 5)
			{
				i = 0;
			}
		}
	}

	/*
	 * depending on i sets the stage to a different color
	 */
	public void changeColor(int i, Stage mStage)
	{
		switch (i)
		{
			case (0):
				mStage.getScene().setFill(Color.PURPLE);	
				break;
			case (1):
				mStage.getScene().setFill(Color.RED);
				break;
			case (2):
				mStage.getScene().setFill(Color.BLUE);
				break;
			case (3):
				mStage.getScene().setFill(Color.ORANGE);
				break;
			case (4):
				mStage.getScene().setFill(Color.WHITE);
				break;
		}
	}
}