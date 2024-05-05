import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class TrackAndTrace
{
    public static Scanner kbd = new Scanner(System.in);
    public static void main(String[] args)
    {
            System.out.println("Enter the start time:");
            int start1 = kbd.nextInt();
            System.out.println("Enter the end time:");
            int end1 = kbd.nextInt();
            if (args.length != 0)
            {
                System.out.println("There are " + fileInput(start1, end1, args) + " tests needed.");
            }
            else
            {
                getOverlaps(start1, end1);
            }
            
    }
    
    public static int fileInput(int Start, int End, String[] args)
    {
        int testCounter = 0;
        for (int i = 0; i<args.length; i++)
        {
            try
            {
                File file = new File(args[i]);
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine())
                {
                    String data = reader.nextLine();
                    String[] lineArray = data.split(" ");
                    String name = lineArray[0];
                    int arrivalTime = Integer.parseInt(lineArray[1]);
                    int departureTime = Integer.parseInt(lineArray[2]);
                    if (TrackAndTrace.overlap(Start, End, arrivalTime, departureTime))
                    {
                        testCounter++;
                        System.out.println(name + " needs a test.");
                    }
                    
                }
            
            }
            catch (FileNotFoundException e)
            {
                System.out.println("WARNING: "+args[i] + " not found.");
            }
        }
        return testCounter;
    }
    public static boolean overlap(Integer start1, Integer end1, Integer start2, Integer end2)
    {
        if((start1 >= 1) && (start1 <= 6)) 
        {
            start1 = start1 + 24;
        }    
        if((end1 >= 1) && (end1 <= 6)) 
        {
            end1 = end1 + 24;
        }    
        if((end2 >= 1) && (end2 <= 6)) 
        {
            end2 = end2 + 24;
        }      
        if((start2 >= 1) && (start2 <= 6)) 
        {
            start2 = start2 + 24;
        }    
        if(!(end1 <= start2 || start1 >= end2))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public static String AskName()
    {
        System.out.println("Enter the customer's name:");
        return kbd.next();
    }
    
    public static Integer  AskArrival()
    {
        System.out.println("Enter the arrival time:");
        return kbd.nextInt();
    }
    
    public static Integer  AskDeparture()
    {
        System.out.println("Enter the departure time:");
        return kbd.nextInt();
    }

    public static void getOverlaps(Integer start1, Integer end1)
    {
        Integer people;
        System.out.println("Enter the number of customers:");
        people = kbd.nextInt();
        int infected = 0;
        for (int i = 0; i < people; i++)
        {
            String name = AskName();
            Integer start2 = AskArrival();
            Integer end2 = AskDeparture();
            if (overlap(start1, end1, start2, end2))
            {
                System.out.println(name + " needs a test.");
                infected++;
            }
            else
            {
                System.out.println(name + " does not need a test.");
            }
        }
        System.out.println("There are " + infected + " tests needed.");
    }
}
