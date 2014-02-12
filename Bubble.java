
public class Bubble
extends Sort
{
   public Bubble(int[] pNum)
   {
      mNum = pNum;
      history = new Performance();
   }

   /**
    * sorts the arraylist so that the largest number is on the bottom
    */
   public String type()
   {
      return "Bubble";
   }

   /**
    * Preforms a special sort for bubble
    */
   public long spSort()
   {
      boolean sorted = false;
       while (!sorted)
      {
         sorted = true;
         for (int i = 0; i < mNum.length - 1; i++)
         {
            if (mNum[i] > mNum[i + 1])
            {
               change(i, i +1);
               sorted = false;
            }
            compares++;
         }
      }
       return compares;
   }
}