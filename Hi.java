package scr.Gui;

/*
 * A simple thread that simply prints out a message
 */
public class Hi 
implements Runnable
{
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run()
	{
		while (Glory.getInstance().shouldThreadBeRunning())
		{
			try
			{
				Thread.sleep(1500);
				System.out.println("Hey there how you doing?");
			}
			catch (Exception e)
			{
			}
		}
	}
}
