import java.util.ArrayList;

public class Performance
{
   private ArrayList<Long> runTimes;
   private ArrayList<Long> comparesons;
   private ArrayList<Integer> lengths;

   public Performance()
   {
      runTimes = new ArrayList<Long>();
      comparesons = new ArrayList<Long>();
      lengths = new ArrayList<Integer>();
   }

   public void newTime(long run)
   {
      runTimes.add(run);
   }

   public void newComparesons(Long pComp)
   {
      comparesons.add(pComp);
   }

   public void newLengths(int pLength)
   {
      lengths.add(pLength);
   }

   public ArrayList<Long> getTimes()
   {
      return runTimes;
   }

   public long getTime(int pos)
   {
      return runTimes.get(pos);
   }

   public long avarageTimes()
   {
      long aver = 0;
      for (int i = 0; i < runTimes.size(); i ++)
      {
         aver += runTimes.get(i);
      }
      return aver/runTimes.size();
   }

   public ArrayList<Long> getCompares()
   {
      return comparesons;
   }

   public Long getCompare(int pos)
   {
      return comparesons.get(pos);
   }

   public Long avarageCompares()
   {
      Long aver = new Long(0);
      for (int i = 0; i < comparesons.size(); i ++)
      {
         aver += comparesons.get(i);
      }
      return aver/comparesons.size();
   }

   public ArrayList<Integer> getLenghts()
   {
      return lengths;
   }

   public int getLength(int pos)
   {
      return lengths.get(pos);
   }

   public int avarageLengths()
   {
      int aver = 0;
      for (int l = 0; l < lengths.size(); l++)
      {
         aver += lengths.get(l);
      }
      return aver/lengths.size();
   }

   public void newRecord(long time, Long compares, int len)
   {
      this.newTime(time);
      this.newComparesons(compares);
      this.newLengths(len);
   }

   /**
    * returns the average time it took to sort for the given length
    */
   public long aveTime(int len)
   {
      long ave = 0;
      int counter = 0;
      for (int i = 0; i < lengths.size(); i++)
      {
         if (lengths.get(i) == len)
         {
            ave += runTimes.get(i);
            counter++;
         }
      }
      if (counter != 0)
         return ave/counter;
      else
         return ave; // or 0
   }

   /**
    * return the average number of comparisons for the given length
    */
   public Long aveComp(int len)
   {
      Long ave = new Long(0);
      int counter = 0;
      for (int i = 0; i < lengths.size(); i++)
      {
         if (lengths.get(i) == len)
         {
            ave += comparesons.get(i);
            counter++;
         }
      }
      if (counter != 0)
         return ave/counter;
      else
         return ave; // or 0
   }
}