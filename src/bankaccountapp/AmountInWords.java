package bankaccountapp;



/*
 * This code feels wholesome but untested on a battle field.
 * You can also help test run it through some of your requirement
 * before you use.
 * It is, nevertheless, free for you(and only you) to use.
 */

/**
 *
 * @author adebowale odunlami
 */
public class AmountInWords {

    public static void main(String [] args){
        byte b = 23;
        String[]s={"naira","kobo"};
        double g = 345799089.71;
        System.out.println(toWord(g,TYPE_MEASURE,s));
        System.out.println(toWord(g,TYPE_CURRENCY,s));
    }

    //10exp18 = quintillion;10exp21 = sextillion; 10exp24 = septillion;10exp27 = octillion
    //gazillion, zillion, jillion == large and indeterminate number
    private static final String units[] = {" zero "," one "," two "," three "," four "," five "," six "," seven "," eight "," nine "};
    private static final String tens[] = { " twenty "," thirty "," forty "," fifty "," sixty "," seventy "," eighty "," ninety "};
    private static final String teens [] = {" ten ", " eleven ", " twelve "," thirteen "," fourteen "," fifteen "," sixteen "," seventeen "," eighteen "," nineteen "};
    private static final String levels [] = {" hundred "," thousand "," million "," trillion "," quadrillion "," quintillion "," sextillion ", " septillion "," octillion "};
    private static final String and  = " and ";
    private static final String point  = " point ";
    private static final short t[] = {10,100,1000};
    public static final String TYPE_CURRENCY = "TYPE_CURRENCY";
    public static final String TYPE_MEASURE = "TYPE_MEASURE";
    public static String toWord(double number, String valuetype, String [] deno){
        StringBuilder ret = new StringBuilder();
        String st = null;
        byte sig = 1;
        double frac = 0;
        //if number is -ve, turn number to positive and note it
        if(number<0.0){
           sig = -1;
           number*=sig;
        }
        //get fractional part of number is there is
        frac = (number - (long)number);//(float)
        System.out.println("Fractional Part is :" + frac);
        //get whole decimal part of number
        long num = (long)number;

        byte level = 0;
        if(num==0 && !valuetype.equals(TYPE_CURRENCY)) ret.insert(0, units[0]);
        while(num>0){
            short rem = (short) (num%t[2]); //get the last hundred,ten and unit as rem
            st = getWord(rem, level).toString(); //convert rem with its level to words
            ret.insert(0, st); // insert words to begining of buffer
            level++;
            num = num/t[2]; //chop off the last  hundred,ten and unit as rem
        }
        //remove the last comma ',', if it is there
        if(ret.length()>1 && ret.charAt(ret.length()-1)==','){ret.replace(ret.length()-1,ret.length(),"");}
        //add the fractional path with the units
        if (valuetype == null || !valuetype.equals(TYPE_CURRENCY)){
            if(frac>0.0){
                ret.append(point).append(spellFraction(frac));
            }
            if(deno.length>=1 && deno[0]!=null && number > 0) ret.append(" ").append(deno[0]);
        }else{
            if(deno.length>=1 && deno[0]!=null && (long)number > 0) ret.append(" ").append(deno[0]);
            if(frac>0.0){
                ret.append(getWord((short)(frac*100), (byte)-1).toString());
                if(deno.length>=2 && deno[1]!=null ) ret.append(" ").append(deno[1]);
            }
        }
        //firstly turn multiple spaces(spaces only) to single space,then turn comma with spaces to comma only, then remove trailing comma.
        return ret.toString().replaceAll("[\\s]{2,}", " ").replaceAll("[\\s,]{2,}", ",").replaceAll("^[\\s,]{1,}|[\\s,]{1,}$", "");
    }

    private static StringBuffer spellFraction(double f){
         StringBuffer ret = new StringBuffer();
         System.out.println("Fractional Part is :" + f);
         byte i = 4;byte k = 0;
         f*=Math.pow(10, i);
         for(int j =1;j<=i && (int)f>0;j++){
             k = (byte)(f/Math.pow(10, i-j));
             ret.append(units[k]);
             f%=Math.pow(10, i-j);
             //System.out.println(f);
         }
         return ret;
    }
    private static StringBuffer getWord(short num, byte level){
        StringBuffer ret = new StringBuffer();
        short qu = (short) (num/t[1]);
        if (qu > 0){ret.append(units[qu]).append(levels[0]);}
        short re = (short) (num%t[1]);
        short q = (short) (re/t[0]);
        short r = (short) (re%t[0]);
        if (q == 1){
            if(qu>0 || level==0) ret.append(and);
            ret.append(teens[r]);
        }else{
            if (q > 1) {
                if(qu>0 || level==0) ret.append(and);
                ret.append(tens[q-2]);
            }
            if (r > 0){
                if(q==0 &&( qu > 0 || level ==0)){ret.append(and);}
                ret.append(units[r]);
            }
        }
        if(num>0 && level>0) ret.append(levels[level]).append(",");
        return ret;
    }
}

