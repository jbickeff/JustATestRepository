
import java.util.Stack;
public class Quicksort
extends Sort
{
   public Quicksort(int[] pNum)
   {
      mNum = pNum;
      compares = 0;
      history = new Performance();
   }

   /**
    * return the String Quicksort
    */
   public String type()
   {
      return "Quicksort";
   }

   /**
    * Preforms a quicksort on the list, givin the range and a pivit
    * point
    */
   public void qSort(int lowRange, int highRange, int pivit)
   {
      Stack after = new Stack();
      int pos = lowRange;
      Stack before = new Stack();
      int pivitNum = mNum[pivit];
      String sA = "";
      String sB = "";
      for (int i = lowRange; i < highRange; i++)
      {
         if (i != pivit)
         {
            if (mNum[i] <= pivitNum)
            {
               before.push(mNum[i]);
               sB = sB + mNum[i] + ", ";
            }
            else
            {
               after.push(mNum[i]);
               sA = sA + mNum[i] + ", ";
            }
            compares++;
         }
      }
      while(!before.empty())
      {
         mNum[pos] = (int)before.pop();
         pos++;
      }
      mNum[pos] = pivitNum;
      pivit = pos;
      pos++;
      while(!after.empty())
      {
         mNum[pos] = (int)after.pop();
         pos++;
      }
      if (pivit - lowRange > 1)
         qSort(lowRange, pivit-1, (lowRange + pivit - 1)/2);
      if (highRange - pivit > 1)
         qSort(pivit + 1, highRange, (highRange + pivit +1)/2);
   }

   /**
    * Calles the special sort that goes with this class
    */
   public long spSort()
   {
      qSort(0, mNum.length, mNum.length/2);
      return compares;
   }

}