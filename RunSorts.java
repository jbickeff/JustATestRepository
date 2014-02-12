import java.util.ArrayList;

public class RunSorts
{
 
   /**
    * tests the sort that was passed in with all of the lists passed in.
    * data collection also occures but that will soon be handled by each sort
    * and so each sort will know what its performance has been
    */
   public static void test(ArrayList<Sort> sorters, ListHolder lists)
   {
      for(int a = 0; a < sorters.size(); a++)
      {
         System.out.println("Testing the " + sorters.get(a).type());
         lists.scrambleAll();
         for (int i = 0; i < lists.number();i++)
         {
            sorters.get(a).exchangeList(lists.get(i));
            sorters.get(a).sort();
         }
      }
   }
   
   public static void main(String[] args)
   {
      ArrayList<Sort> sorts = new ArrayList<Sort>();
      int[] list1 = new int[10];
      int[] list2 = new int[10];
      int[] list3 = new int[100];
      int[] list4 = new int[100];
      int[] list5 = new int[1000];
      int[] list6 = new int[1000];
      int[] list7 = new int[10000];
      int[] list8 = new int[10000];
      int[] list9 = new int[30000];
      int[] list10 = new int[100000];
      int[] list11 = new int[1000000];
      ListHolder lists = new ListHolder();
      lists.add(list1);
      lists.add(list2);
      lists.add(list3);
      lists.add(list4);
      lists.add(list5);
      lists.add(list6);
      lists.add(list7);
      lists.add(list8);
      lists.add(list9);
      lists.add(list10);
      lists.add(list11);
      Bubble bSort= new Bubble(list1);
      Selection sSort = new Selection(list2);
      Mergesort mSort = new Mergesort(list3);
      Quicksort qSort = new Quicksort(list4);
      sorts.add(bSort);
      sorts.add(sSort);
      sorts.add(mSort);
      sorts.add(qSort);
      test(sorts, lists);
      report(sorts, lists);
      
   }  

   public static void report(ArrayList<Sort> sorts, ListHolder lists)
   {
      //                        11     18          30      
      String tableH = "Sort      |length     |Comparisons   |RunTimes";
      tableH = tableH + "\n---------------|------|--------------|--------\n";
      String body = "";
      for (int i = 0; i < sorts.size(); i++)
      {
         // 11 white space from the S to the |
         Performance current = sorts.get(i).getHistory();
         String info = sorts.get(i).type();
         int len = -1;
         for (int l = 0; l < lists.number(); l++)
         {
            if (len != lists.get(l).length)
            {
               len = lists.get(l).length;
               info = fillRow(info, len, current.aveComp(len), current.aveTime(len));
            }
            body = body + info;
            info = "";
         }
         body = body+"--------------------------------------\n";
      }
      System.out.println(tableH + body);
   }

   private static String fillRow(String row, int len, long comp, long runTime)
   {
      row = createWhiteSpace(row, 15);
      row = row+"|"+len;
      row = createWhiteSpace(row, 25);
      row = row + "|" + comp;
      row = createWhiteSpace(row, 37);
      row = row +"|" + runTime + "\n";
      return row;
   }
   private static String createWhiteSpace(String word, int neededLength)
   {
      int s = word.length();
      while (s != neededLength)
      {
         word = word + " ";
         s = word.length();
      }
      return word;
   }
}