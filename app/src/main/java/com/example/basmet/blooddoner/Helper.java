package com.example.basmet.blooddoner;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by basmet on 5/2/2018.
 */

public class Helper {

    public static final String FIRSTNAME = "First Name";
    public static final String LASTNAME = "Last Name";
    public static final String PASSWORD = "Password";
    public static final String EMAIL = "Email";
    public static final String DATEOFBIRTH = "Date OfBirth";
    public static final String PHONE_NUMBER = "Phone Number";
    public static final String GENDER = "Gender";
    public static final String CITY = "City";
    public static final String Location = "Location";
    public static final String BLOOD_GROUP= "Blood Group";
    public static final int SELECT_PICTURE = 2000;

    public static boolean isValidEmail(String email){
        if(email.contains("@")){
            return true;
        }
        return false;
    }

    public static void displayMessageToast(Context context, String displayMessage){
        Toast.makeText(context, displayMessage, Toast.LENGTH_LONG).show();
    }
}
