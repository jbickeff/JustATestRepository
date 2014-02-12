import java.util.Arrays;
public class Mergesort
extends Sort
{
   public Mergesort(int[] pNum)
   {
      mNum = pNum;
      compares = 0;
      history = new Performance();
   }

   public String type()
   {
      return "Mergesort";
   }

   public long spSort()
   {
      mSort(mNum);
      return compares;
   }
   
   public void mSort(int []list)
   {
      if (list.length > 1)
      {
         int middle = list.length / 2;
         int[] left = Arrays.copyOfRange(list, 0, middle);
         int[] right = Arrays.copyOfRange(list, middle, list.length);
         mSort(left);
         mSort(right);
         merge(list, left, right);
         // this is where i merge left and right
      }
   }
   public void merge(int[] list, int[] right, int[] left)
   {
      int r = 0;
      int l = 0;
      int t = right.length + left.length;
      int i = 0;
      while(i < t)
      {
         
         if (r < right.length && l < left.length)
         {
            compares++;
            if (right[r] <= left[l])
            {
               list[i] = right[r];
               r++;
               i++;
            }
            else
            {
               list[i] = left[l];
               l++;
               i++;
            }
         }
         else
         {
            // put the rest of right into length
            if(l >= left.length)
            {
               while(r < right.length)
               {
                  list[i] = right[r];
                  r++;
                  i++;
               }
               break;
            }
            //put the rest of left into length
            else 
            {
               while(l < left.length)
               {
                  list[i] = left[l];
                  i++;
                  l++;
               }
               break;
            }
         }
      }
   }
   
}