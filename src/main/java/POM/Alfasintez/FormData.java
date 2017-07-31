package POM.Alfasintez;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Дмитрий on 20.04.2017.
 */
public class FormData {
    private String name;
    private String telephone;
    public String random;

    public String getName() {
        return name;
    }

    public String getTelephone() {
        return telephone;
    }


    public FormData(String name, String telephone){
        this.name = name;
        this.telephone = telephone;
    }

    public FormData(String random){
        this.random = random;
    }

    public static String generateRandomNumber(int col){
        if (col<=0) return "";
        Random random = new Random();
        int[] arr = new int[col];
        for (int i=0; i<= arr.length-1; i++){
            arr[i] = random.nextInt(10);
        }
        String rand =  Arrays.toString(arr).replace(",","").replace("[", "").replace(" ","").replace("]","");
        return rand;
    }

    public static String generateRandomChars(int col){
        if (col<=0) return "";
        Random random = new Random();
        char[] arr = new char[col];
        for (int i=0; i<= arr.length-1; i++){
            arr[i] =  (char)(random.nextInt(26) + 'a');
        }
        String rand =  Arrays.toString(arr).replace(",","").replace("[", "").replace(" ","").replace("]","");
        return rand;
    }

    public static FormData generate(int a, int b) {
        return new FormData(
                generateRandomChars(a), generateRandomNumber(b));
    }

    public static FormData generate(int a) {
        return new FormData(
                generateRandomChars(a));
    }

}
