package com.example.app2.Utils;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.Calendar;
import java.util.Date;

public class IntegrityChecks {
    public static boolean isValidName(String _value)
    {
        char[] letters={'א','ב','ג','ד','ה','ו','ז','ח','ט','י','כ','ך','ל','מ','ם','נ','ן','ס','ע','פ','ף','צ','ץ','ק','ר','ש','ת',' '};
        if (_value != null)
        {
            if (_value.length() > 25) return false;
            char current;
            for (int i = 0; i < _value.length(); i++)
            {
                current = _value.charAt(i);
                boolean in=false;

                for(char item: letters) {
                    if(current == item)
                        in = true;
                }
                if(!in)
                    return false;
            }
            return true;
        }
        return false;
    }

    public static boolean isValidEmail(String e)
    {

        if (!TextUtils.isEmpty(e) && Patterns.EMAIL_ADDRESS.matcher(e).matches()) {
            return true;
        }else {
            return false;
        }
    }
//        if (e.contains("@"))
//        {
//            return true;
//        }
//        return false;
//    }

    public static boolean isValidPhone(String _number)
    {

        if (_number != null) {
            if (_number.length() != 10 && _number.length() != 9) return false;

            if (_number.charAt(0) != '0') return false;
            try {
                Long.parseLong(_number);
                return true;

            }catch (NumberFormatException e)
            {
                return false;
            }
        }
        return false;
    }
    public static boolean isValidDates(Date start, Date end)
    {
        try
        {
            if(end.before(start) && start.before(new Date()))
                return false;
            return true;
        }
        catch (Exception e)
        {
            return false;
        }

    }
}