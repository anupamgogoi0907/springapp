package com.anupam.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AppUtility {
    static String DATE_PATTERN = "yyyy-MM-dd hh:mm:ss";

    private static Logger logger = LogManager.getLogger(AppUtility.class);

    public static Date parseDate(Date date) {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);
            String result = dateFormatter.format(date);
            return dateFormatter.parse(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }

    }
}
