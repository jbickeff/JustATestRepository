
public class Selection
extends Sort
{
   public Selection(int[] pNum)
   {
      mNum = pNum;
      history = new Performance();
   }

   public String type()
   {
      return "Selection";
   }

   public long spSort()
   {
      int smallestPos;
      for (int i = 0; i < mNum.length; i++)
      {
         smallestPos = i;
         for (int y = i+1; y < mNum.length; y++)
         {
            if (mNum[smallestPos] > mNum[y])
            {
               smallestPos = y;
            }
            compares++;
         }
         if(smallestPos != i )
         {
            change(i, smallestPos);
         }
      }
      return compares;
   }
}