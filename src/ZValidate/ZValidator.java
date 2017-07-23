/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ZValidate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author dell
 */
public class ZValidator {
    
    // <editor-fold defaultstate="collapsed" desc="Number validate"> 
    /*
     * Method: isNumber
     * Parameter:
     *   - String text: A string to test if it contained only number
     * Return:
     *   - Boolean: true if the string only contain number. Otherwise false
     */
    public static boolean isNumber(String text){
        String numberRegex = "^\\d+$";
        return text.matches(numberRegex);
    }
    
    /*
     * Method: isNumberInRage
     * Parameter:
     *   - String text: A string to test if it contained only number and had the
     * length in the specific range
     *   - String range: A string to specify the range of the input number.
     *        Range format:
     *        Number-Number: 10-9, 9-1
     * Return:
     *   - Boolean: true if the number is in the range. Otherwise false
     */
    public static boolean isNumberInRage(String text, String range){
        String[] rangeArray = range.split("-");
        int min = Integer.parseInt(rangeArray[0]);
        int max = Integer.parseInt(rangeArray[1]);
        min = min > max ? max : min;
        max = max > min ? max : min;
        String numberRegex = "^\\d{" + min + "," + max + "}$";
        return text.matches(numberRegex);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="String validator"> 
    /*
     * Method: isString
     * Parameter:
     *   - String text: A string to test if it contained only string
     * Return:
     *   - Boolean: true if the string contained only string. Otherwise false
     */
    public static boolean isString(String text){
        String stringRegex = "^[a-zA-Z]+$";
        return text.matches(stringRegex);
    }
    
    /*
     * Method: isStringWithSpace
     * Parameter:
     *   - String text: A string to test if it contained only string and space
     * Return:
     *   - Boolean: true if the string contained only string and space. 
     * Otherwise false
     */
    public static boolean isStringWithSpace(String text){
        String stringRegex = "^[a-zA-Z ]+$";
        return text.matches(stringRegex);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Date validator"> 
    /*
     * Method: isDate
     * Parameter:
     *   - String format: A string to specify the format of the date
     *        Date format: dd/MM/yyyy
     *   - String value: A string that contain the date
     * Return:
     *   - Boolean: true if the date is correct. Otherwise false
     */
    public static boolean isDate(String format, String value){
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            return false;
        }
        return date != null;
    }
    
    /*
     * Method: isAgeGreaterThan
     * Parameter:
     *   - Int age: An integer to specify the age to compare
     *   - String format: A string to specify the format of the date
     *        Date format: dd/MM/yyyy
     *   - String value: A string that contain the date
     * Return:
     *   - Boolean: true if the current year - the year of the date is greater
     * than the inputted age. Otherwise false
     */
    public static boolean isAgeGreaterThan(int age, String format, String value){
        if(!isDate(format, value)) return false;
        else{
            Date date;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                date = sdf.parse(value);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                cal.setTime(new Date());
                int currentYear = cal.get(Calendar.YEAR);
                return currentYear - year > age;
            } catch(ParseException ex){
                return false;
            }
        }          
    }
    
    /*
     * Method: isAgeSmallerThan
     * Parameter:
     *   - Int age: An integer to specify the age to compare
     *   - String format: A string to specify the format of the date
     *        Date format: dd/MM/yyyy
     *   - String value: A string that contain the date
     * Return:
     *   - Boolean: true if the current year - the year of the date is smaller
     * than the inputted age. Otherwise false
     */
    public static boolean isAgeSmallerThan(int age, String format, String value){
        if(!isDate(format, value)) return false;
        else{
            Date date;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                date = sdf.parse(value);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                cal.setTime(new Date());
                int currentYear = cal.get(Calendar.YEAR);
                return currentYear - year < age;
            } catch(ParseException ex){
                return false;
            }
        }          
    }
    
    /*
     * Method: isAgeEqual
     * Parameter:
     *   - Int age: An integer to specify the age to compare
     *   - String format: A string to specify the format of the date
     *        Date format: dd/MM/yyyy
     *   - String value: A string that contain the date
     * Return:
     *   - Boolean: true if the current year - the year of the date is equal
     * the inputted age. Otherwise false
     */
    public static boolean isAgeEqual(int age, String format, String value){
        if(!isDate(format, value)) return false;
        else{
            Date date;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                date = sdf.parse(value);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                cal.setTime(new Date());
                int currentYear = cal.get(Calendar.YEAR);
                return currentYear - year == age;
            } catch(ParseException ex){
                return false;
            }
        }          
    }
    
    /*
     * Method: isAgeGreaterThanOrEqual
     * Parameter:
     *   - Int age: An integer to specify the age to compare
     *   - String format: A string to specify the format of the date
     *        Date format: dd/MM/yyyy
     *   - String value: A string that contain the date
     * Return:
     *   - Boolean: true if the current year - the year of the date is greater
     * than or equal the inputted age. Otherwise false
     */
    public static boolean isAgeGreaterThanOrEqual(int age, String format, String value){
        if(!isDate(format, value)) return false;
        else{
            Date date;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                date = sdf.parse(value);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                cal.setTime(new Date());
                int currentYear = cal.get(Calendar.YEAR);
                return currentYear - year >= age;
            } catch(ParseException ex){
                return false;
            }
        }          
    }
    
    /*
     * Method: isAgeSmallerThanOrEqual
     * Parameter:
     *   - Int age: An integer to specify the age to compare
     *   - String format: A string to specify the format of the date
     *        Date format: dd/MM/yyyy
     *   - String value: A string that contain the date
     * Return:
     *   - Boolean: true if the current year - the year of the date is smaller
     * than or equal the inputted age. Otherwise false
     */
    public static boolean isAgeSmallerThanOrEqual(int age, String format, String value){
        if(!isDate(format, value)) return false;
        else{
            Date date;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                date = sdf.parse(value);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                cal.setTime(new Date());
                int currentYear = cal.get(Calendar.YEAR);
                return currentYear - year <= age;
            } catch(ParseException ex){
                return false;
            }
        }          
    }
    
    /*
     * Method: compareDate
     * Parameter:
     *   - String format: A string to specify the format of the date
     *        Date format: dd/MM/yyyy
     *   - String date1: A string that contain the date
     *   - String date2: A string that contain the date
     * Return:
     *   - int: 0 if 2 dates are equal, -1 if the first date is before the 
     * second date, 1 if the first date is after the second date and 2 if any
     * error occured
     */
    public static int compareDate(String format, String date1 , String date2){
        if(!isDate(format, date1) || !isDate(format, date2)) return 2;
        else{
            Date firstDate;
            Date secondDate;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                firstDate = sdf.parse(date1);
                secondDate = sdf.parse(date2);
                if(firstDate.after(secondDate) && firstDate.before(secondDate)){
                    return 0;
                }
                else if(firstDate.after(secondDate)){
                    return 1;
                }
                else if(firstDate.before(secondDate)){
                    return -1;
                }
                else{
                    return 2;
                }
            } catch(ParseException ex){
                return 2;
            }
        }
    }
    // </editor-fold>
}
