package cz.jpalcut.pia.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

}
