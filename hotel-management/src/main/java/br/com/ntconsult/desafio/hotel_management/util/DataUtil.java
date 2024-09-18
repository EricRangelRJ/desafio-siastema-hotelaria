package br.com.ntconsult.desafio.hotel_management.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DataUtil {

    public static Integer getDaysBetween(LocalDate startDate, LocalDate endDate) {
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }

}
