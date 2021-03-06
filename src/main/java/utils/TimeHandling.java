package utils;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class TimeHandling {

    public static boolean validateInputDate(String dateFrom, String dateTo){
        //check ISO format
        if(!isValidIsoDateTime(dateFrom) || !isValidIsoDateTime(dateTo)){
            System.out.println("DateFrom/DateTo must be in ISO-8601 format. Eg: 2019-08-01T00:00:00Z\n" +
                    "Please run again with appropriate format.\n" +
                    "Program stopped with invalid inputs");
            return false;
        }

        //Check date comparision
        Instant isDateFrom = Instant.parse(dateFrom);
        Instant isDateTo = Instant.parse(dateTo);
        if(isDateFrom.compareTo(isDateTo) >= 0){
            System.out.println("DateFrom cannot be equal or greater than DateTo");
            return false;
        }

        return true;
    }

    private static boolean isValidIsoDateTime(String date) {
        try {
            DateTimeFormatter.ISO_DATE_TIME.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static ArrayList<String> getHourNameList(Instant isDateFrom, Instant isDateTo) {
        System.out.println("Data process is from " + isDateFrom.truncatedTo(ChronoUnit.HOURS).toString()
                + " to " + isDateTo.truncatedTo(ChronoUnit.HOURS).toString());
        ArrayList<String> hourList = new ArrayList<>();
        Instant iterInstant = isDateFrom;
        while(iterInstant.compareTo(isDateTo) < 0){
            String downloadName =   String.format("%04d", iterInstant.atZone(ZoneOffset.UTC).getYear()) + "-" +
                                    String.format("%02d", iterInstant.atZone(ZoneOffset.UTC).getMonthValue()) + "-" +
                                    String.format("%02d", iterInstant.atZone(ZoneOffset.UTC).getDayOfMonth()) + "-" +
                                    iterInstant.atZone(ZoneOffset.UTC).getHour();

            hourList.add(downloadName);
            iterInstant = iterInstant.plus(1, ChronoUnit.HOURS);
        }
        return hourList;
    }

}
