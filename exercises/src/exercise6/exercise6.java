package exercise6;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * This program is to test our knowledge of the java Data and Calendar objects. The program will ask a user for input and report how many years months and days old they are.
 * 
 * @author Blake Rollins
 * 
 */
public class exercise6 {

    private Scanner scan;

    public static void main(String[] args) {

        exercise6 ex6 = new exercise6();
        ex6.go();
    }

    void go() {
        scan = new Scanner(System.in);

        int month = readIntFromQuestion("Enter the month you were born (1-12):");
        int day = readIntFromQuestion("Enter the day you were born (1-31)");
        int year = readIntFromQuestion("Enter the year you were born (XXXX)");

        Calendar todayCal = GregorianCalendar.getInstance();
        Calendar bdCal = GregorianCalendar.getInstance();
        SimpleDateFormat df1 = new SimpleDateFormat("MMMM d");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy");

        todayCal.set(todayCal.get(Calendar.YEAR), todayCal.get(Calendar.MONTH), todayCal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        bdCal.set(year, month - 1, day, 0, 0, 0);

        Date today = todayCal.getTime();
        Date birthdate = bdCal.getTime();
        Date diffDate = todayCal.getTime();

        String ds = df1.format(birthdate);

        if (ds.charAt(ds.length() - 1) == '1') {
            ds += "st";
        } else if (ds.charAt(ds.length() - 1) == '2') {
            ds += "nd";
        } else if (ds.charAt(ds.length() - 1) == '3') {
            ds += "rd";
        } else {
            ds += "th";
        }
        ds += ", ";
        ds += df2.format(birthdate);

        System.out.println(ds);

        int yearDif = today.getYear() - birthdate.getYear();
        // System.out.println("yearDif: " + yearDif);

        diffDate.setYear(today.getYear() - yearDif);
        // System.out.println(diffDate);

        int monthDif = diffDate.getMonth() - birthdate.getMonth();
        if (monthDif < 0) {
            yearDif--;
            monthDif += 12;
        }
        diffDate.setMonth(today.getMonth() - monthDif);
        // System.out.println(diffDate);

        // System.out.println("yearDif: " + yearDif);
        // System.out.println("monthDif: " + monthDif);

        int dayDif = diffDate.getDate() - birthdate.getDate();
        if (dayDif < 0) {
            //
            // Do math
            //
            int numDaysLastMonth = 0;
            int todayMonth = today.getMonth();
            // System.out.println("todayMonth: " + todayMonth);
            if (todayMonth == 1 || todayMonth == 3 || todayMonth == 5 || todayMonth == 7 || todayMonth == 8 || todayMonth == 10 || todayMonth == 0) {
                numDaysLastMonth = 31;
            } else if (todayMonth == 4 || todayMonth == 6 || todayMonth == 9 || todayMonth == 11) {
                numDaysLastMonth = 30;
            } else { // last month was feb
                if (isLeapYear(today.getYear())) {
                    numDaysLastMonth = 29;
                } else {
                    numDaysLastMonth = 28;
                }
            }
            monthDif--;
            dayDif += numDaysLastMonth;
            // System.out.println("numDaysLastMonth: " + numDaysLastMonth);
        }

        // System.out.println("yearDif: " + yearDif);
        // System.out.println("monthDif: " + monthDif);
        // System.out.println("dayDif: " + dayDif);

        String returnString = "";
        returnString += "You are ";
        returnString += yearDif;
        returnString += " year";
        if (yearDif != 1) {
            returnString += "s";
        }
        returnString += ", ";
        returnString += monthDif;
        returnString += " month";
        if (monthDif != 1) {
            returnString += "s";
        }
        returnString += ", and ";
        returnString += dayDif;
        returnString += " day";
        if (dayDif != 1) {
            returnString += "s";
        }
        returnString += " old.";

        System.out.println(returnString);

    }

    boolean isLeapYear(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else {
            return true;
        }
    }

    int readIntFromQuestion(String question) {

        System.out.println(question);
        while (true) {
            try {
                int temp = Integer.parseInt(readLine());
                return temp;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Only enter a number.");
            }
        }
    }

    String readLine() {
        return scan.next();
    }
}
