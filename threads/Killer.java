package scr.Gui;

import java.util.Set;

/*
 * this class just kills every thing that it can...
 * not a safe class to have running generally speaking
 */
public class Killer 
implements Runnable
{
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * searches for other threads that it can stop
	 */
	public void run()
	{
		while(Glory.getInstance().shouldThreadBeRunning())
		{
			try 
			{ 
				Set<Thread> r= Thread.getAllStackTraces().keySet();
				for(Thread t : r)
				{
					if(!Glory.getInstance().shouldThreadBeRunning(t))
					{
						try 
						{		
		    			  t.stop();
						}
						catch (Exception e)
						{	
						}
					}
				}
				Thread.sleep(2500);
				System.out.println("cleaner is running");
			}
			catch (Exception e)
			{
				System.out.println("Fat.run.while(running)1");
			}
		
		}
	}
}
