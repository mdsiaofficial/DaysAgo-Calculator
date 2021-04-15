import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;


/**************************************
            How to use:
        
         Enter a date like:
        [day] [month] [year]
        
             EXAMPLE:
            01 01 2000

**************************************/



public class DaysAgo{
    
    static boolean IsLeapYear(int y){
        boolean bl = false;
        if((y%4)==0){
            bl = true;
        }
        return bl;
    }
    
    static int MonthSize(int m, int y){
        int size = 0;
        HashMap<Integer, Integer> monthsize = new HashMap<Integer, Integer>();
        monthsize.put(1, 31);
        monthsize.put(2, 28);
        monthsize.put(3, 31);
        monthsize.put(4, 30);
        monthsize.put(5, 31);
        monthsize.put(6, 30);
        monthsize.put(7, 31);
        monthsize.put(8, 31);
        monthsize.put(9, 30);
        monthsize.put(10, 31);
        monthsize.put(11, 30);
        monthsize.put(12, 31);
        if (IsLeapYear(y) && m == 2){
            size = 29;
        }else {
            size = monthsize.get(m);
        }
        
        return size;
        
    }
   
    static int GetDayCount(int curday, int curmonth, int curyear, int gday, int gmonth, int gyear){
        int calc = 0;
        
        // calculate year days    
        if (gmonth < curmonth){
            for (int c = (gyear); c < curyear; c ++){
                if (IsLeapYear((c))){
                    calc = calc + 366;
                }else{
                    calc = calc + 365;
                }
            }
        }else if (gmonth == curmonth){
            if (gday <= curday){
                for (int c = (gyear); c < curyear; c ++){
                    if (IsLeapYear((c))){
                        calc = calc + 366;
                    }else{
                        calc = calc + 365;
                    }
                }
            }
        }else{
            for (int c = (gyear+1); c < curyear; c ++){
                if (IsLeapYear((c))){
                    calc = calc + 366;
                }else{
                    calc = calc + 365;
                }
            }
        }
            
        if (gyear == curyear - 1){
            if (gmonth < curmonth){
                if (IsLeapYear((gyear))){
                    calc = calc + 366;
                }else{
                    calc = calc + 365;
                }
            }else if (gmonth == curmonth){
                if (gday <= curday){
                    if (IsLeapYear((gyear))){
                        calc = calc + 366;
                    }else{
                        calc = calc + 365;
                    }
                }
            }
        }
        if (((curyear - gyear)/ 100) >= 1){
            calc = calc - ((curyear - gyear)/100);
        }
        
        // calculate month days
        if (gmonth > curmonth){
            for (int c = gmonth; c <= 12; c++){
                calc = (calc + MonthSize(c, (curyear-1)));
            }
            for (int c = 1; c < curmonth; c++){
                calc = (calc + MonthSize(c, curyear));
            }
        }else{
            if (gday > curday){
                for (int c = gmonth; c < (curmonth-1); c ++){
                    calc = (calc + MonthSize(c, curyear));
                }
            }else{
                for (int c = gmonth; c < curmonth; c ++){
                    calc = (calc + MonthSize(c, curyear));
                }
            }
        }
        
        // calculate days
        if (gday > curday){
            if (curmonth == 1){
                for (int c = gday; c < MonthSize(12, curyear-1); c++){
                    calc = calc + 1;
                }
            }else{
                for (int c = gday; c < MonthSize(curmonth-1, curyear); c++){
                    calc = calc + 1;
                }
            }
            
            for (int c = 1; c <= curday; c++){
                calc = calc + 1;
            }
            
        }else{
            for (int c = gday; c < curday; c++){
                calc = calc + 1;
            }
        }
        
        return calc;
    }
   
    public static void main(String[] args) {
        try{
            String[] days = {"", ""};
            int currentyear = Calendar.getInstance().get(Calendar.YEAR);
            int currentmonth = (Calendar.getInstance().get(Calendar.MONTH) + 1);
            int currentday = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            int inputday = 0;
            int inputmonth= 0;
            int inputyear = 0;
            Scanner inp = new Scanner(System.in);
            String[] input = inp.nextLine().split(" ");
            int inpn[] = {Integer.parseInt( input[0] ), Integer.parseInt( input[1] ), Integer.parseInt( input[2] )};
            boolean cont = true;
            if (((Integer.parseInt( input[0] )) < 1) || ((Integer.parseInt( input[0] )) > 31)){
                System.out.println("Please enter valid day.   1 | 31");
                cont = false;
            }
            else{
            inputday = Integer.parseInt( input[0] );
            }
            
            if (((Integer.parseInt( input[1] )) < 1) || ((Integer.parseInt( input[1] )) > 12)){
                System.out.println("Please enter valid month.   1 | 12");
                cont = false;
            }
            else{
            inputmonth = Integer.parseInt( input[1] );
            }
            
            if (((Integer.parseInt( input[2] )) < 1) ){
                System.out.println("Please enter valid year.   1 | " + currentyear);
                cont = false;
            }
            else{
            inputyear = Integer.parseInt( input[2] );
            }
            
            if (inpn[2] > currentyear){
                System.out.println("Please enter day in the past.");
                cont = false;
            }else if (inpn[2] == currentyear){
                if (inpn[1] > currentmonth){
                    System.out.println("Please enter day in the past.");
                    cont = false;
                }else if (inpn[1] == currentmonth){
                    if (inpn[0] > currentday){
                        System.out.println("Please enter day in the past.");
                        cont = false;
                    }else if (inpn[0] == currentday){
                        System.out.println("You entered today!");
                        cont = false;
                    }else if (inpn[0] < currentday){
                        cont = true;
                    }
                }
            }
            if (cont){
                System.out.println("" + inputday + "/" + inputmonth + "/" + inputyear + " Was:");
                System.out.println("-----------------");
                System.out.println(GetDayCount(currentday, currentmonth, currentyear, inputday, inputmonth, inputyear));
                System.out.println("-----------------");
                System.out.println("Days ago. \n");
            }
        }catch(Exception e){
            System.out.println("Error, Please enter input as '[day] [month] [year]' EXAMPLE: 01 01 2000");
        }
    }
}
