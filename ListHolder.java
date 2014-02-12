import java.util.ArrayList;

public class ListHolder
{
   private ArrayList<int[]> holder;

   public ListHolder()
   {
      holder = new ArrayList<int[]>();
   }
   
   public void add(int[] list)
   {
      holder.add(list);
   }

   public int[] get(int i)
   {
      return holder.get(i);
   }

   public int number()
   {
      return holder.size();
   }
   
   public void scrambleAll()
   {
      for (int i = 0; i < holder.size(); i++)
      {
         buildList(holder.get(i));
      }
   }

   public void buildList(int[] list)
   {
      int storing;
      for (int i = 0; i < list.length; i++)
      {
         storing = (int)(Math.random()*1000); 
         list[i] = storing;
      }
   }
}