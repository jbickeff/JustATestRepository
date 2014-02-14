import java.io.File;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Racer
   implements ActionListener
{
   private FileToucher mMonitor;
      
   public Racer()
   {
      mMonitor = new OutputtingFileToucher();
      mMonitor.setFileFilter(
          new FileFilter()
            {
               public boolean accept(File pFile)
               {
                  return pFile.getName().startsWith("file.");
               }
            }
                             );
   }

   public void actionPerformed(ActionEvent ae)
   {
      mMonitor.run();
   }
   public void run()
   {
      Thread create = new Creator();
      Thread destroy = new Destroyer();
      create.start();
      try
      {
         Thread.sleep(2000);
      }
      catch (Exception e)
      {
      }
      destroy.start();
     
   }
   
   public static void main(Sring args[])
   {
      new Racer().run;
   }
   
   
}