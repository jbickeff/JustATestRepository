import java.io.RandomAccessFile;
public class CreatFile
{
   public static void main(String[] args)
   throws Exception
   {
      RandomAccessFile newFile = new RandomAccessFile("numFile.txt", "rw");
      int storing;
      String build = "10000\n";
      for (int i = 0; i < 10000; i++)
      {
         storing = (int)(Math.random()*1000); 
         build = build + storing + " ";
      }
      newFile.writeUTF(build);
      newFile.close();
   }
}