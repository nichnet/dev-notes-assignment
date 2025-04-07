package com.nruotsalainen.notes_backend.utils;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class DateUtils {
    public static boolean isTimezoneValid(String timezoneId) {
        try {
            getTimezone(timezoneId);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    public static ZoneId getTimezone(String timezoneId) throws DateTimeException {
        return ZoneId.of(timezoneId.trim());
    }

    public static ZonedDateTime convertToTimezone(ZonedDateTime date, String timezoneId) {
        if (date == null) {
            return null;
        }

        if(timezoneId == null || timezoneId.isEmpty()) {
            return date;
        }

        ZoneId selectedZoneId = getTimezone(timezoneId);

        if(date.getZone().equals(selectedZoneId)) {
            return date;
        }

        return date.withZoneSameInstant(selectedZoneId);
    }
}
