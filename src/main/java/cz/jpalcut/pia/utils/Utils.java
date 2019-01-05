package cz.jpalcut.pia.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.util.Calendar;
import java.util.Random;

/**
 * Třída obsahující užitečné metody pro aplikaci
 */
public class Utils {

    /**
     * Generuje číselný řetězec podle zadané délky
     *
     * @param length délka k generování
     * @return vygenerovaný číselný řetězec
     */
    public static String generateNumber(int length) {
        Random r = new Random();
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < length; i++) {
            number.append(r.nextInt(10));
        }
        return number.toString();
    }

    /**
     * Vytváří hash pomocí BCryptPasswordEncoder
     *
     * @param password řetězec k hashování
     * @return hash
     */
    public static String hashPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    /**
     * Porovnává den, měsíc a rok datumu s aktuálním datumem
     *
     * @param first datum k porovnání
     * @return true - datum je stejný nebo starší než aktuální, false - datum je starší než aktuální datum
     */
    public static boolean isValidTransactionDate(Date first) {
        Date second = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(first);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(second);

        //testování jestli se jedná o starší datum než aktuální
        if (first.compareTo(second) < 0) {
            //porovnání let
            if (cal.get(Calendar.YEAR) < cal2.get(Calendar.YEAR)) {
                return false;
            }
            //porovnání měsíců
            if (cal.get(Calendar.MONTH) < cal2.get(Calendar.MONTH)) {
                return false;
            }
            //porovnání dnů
            if (cal.get(Calendar.DAY_OF_MONTH) < cal2.get(Calendar.DAY_OF_MONTH)) {
                return false;
            }
        }
        return true;
    }

}
