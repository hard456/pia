package cz.jpalcut.pia.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.util.Calendar;
import java.util.Random;

public class Utils {

    public static String generateNumber(int length){
        Random r = new Random();
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < length; i++){
            number.append(r.nextInt(10));
        }
        return number.toString();
    }

    public static String hashPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static boolean isValidDate(Date first){
        Date second = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(first);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(second);

        //Testování jestli se jedná o starší datum než aktuální
        if(first.compareTo(second) < 0){
            //Porovnání let
            if(cal.get(Calendar.YEAR) < cal2.get(Calendar.YEAR)){
                return false;
            }
            //Porovnání měsíců
            if(cal.get(Calendar.MONTH) < cal2.get(Calendar.MONTH)){
                return false;
            }
            //Porovnání dnů
            if(cal.get(Calendar.DAY_OF_MONTH) < cal2.get(Calendar.DAY_OF_MONTH)){
                return false;
            }
        }

        return true;
    }

}
