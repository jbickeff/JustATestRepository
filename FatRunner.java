package scr.Gui;
import java.util.*;
/*
 * Makes it so the fat class will run for the Glory
 * in fact you could say this is an adapter for the Fat class
 */
public class FatRunner
implements Runnable
{

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * calls the strat method for fat class
	 */
	public void run()
	{
		Process p = null;
		try
		{
			ProcessBuilder pb = new ProcessBuilder("java", "scr.Gui.Fat");
			Map<String, String> env = pb.environment();
			env.put("CLASSPATH", System.getProperty("java.class.path"));
			p = pb.start();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		while(Glory.getInstance().shouldThreadBeRunning())
		{
			try 
			{
				Thread.sleep(2500);
			}
			catch (Exception e)
			{
				System.out.println("Fat.run.while(running)1");
			}
		
		}
		if (p != null)
		{
			p.destroy();
		}
		
	}
}
