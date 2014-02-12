/**
 * The general outline for every Sort, not every sort will use everything
 * provided, but every Sort will at least use int sort(), int length(),
 * String type(), and exchangeList()
 */
public abstract class Sort
{

   protected int[]mNum;
   protected Performance history;
   protected long compares;

   /**
    * allows the sort to change the possition of two numbers in its list
    */
   public void change(int possition1, int possition2)
   {
      int temp = mNum[possition1];
      mNum[possition1] = mNum[possition2];
      mNum[possition2] = temp;
   }

   /**
    * displays the entire list, sorted or unsorted, doesnt matter
    */
   public void display()
   {
      String list = "";
      for (int i = 0; i < mNum.length; i++)
      {
         list = list + mNum[i] + ", ";
      }
      System.out.println(list.substring(0, list.length() -2));
   }

   /**
    * changes the lists that will be used for sorting
    */
   public void exchangeList(int[] pList)
   {
      mNum = pList;
   }

   /**
    * returns the list legnth of the list
    */
   public int length()
   {
      return mNum.length;
   }

   /**
    * returns the type of sort it is
    */
   abstract public String type();

   /**
    * Prepares the class for collecting information about the sort
    * (comparisons, and timeing) starts the time, runs the special sort
    * stops the time, and records the number of comparisons, the time, and
    * the length of the list that was sorted.  Returns the number of
    * comparisons for no good reason... i might chagne it to a void
    */
   public long sort()
   {
      compares = 0;
      long start;
      long stop;
      start = System.currentTimeMillis();
      spSort();
      stop = System.currentTimeMillis();
      history.newRecord(stop - start, compares, mNum.length);
      return compares;
   }

   /**
    * abstract so that each different type of sort can call a special
    * sort method
    */
   abstract public long spSort();

   /**
    * return the Performance of the sort, giving the user the ability to view
    * the history
    */
   public Performance getHistory()
   {
      return history;
   }
}