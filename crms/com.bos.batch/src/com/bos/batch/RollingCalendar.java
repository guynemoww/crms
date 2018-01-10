package com.bos.batch;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/** 
 * RollingCalendar is a helper class to AppDailyRollingFileAppender. Given a 
 * periodicity type and the current time, it computes the start of the next 
 * interval. 
 * */  
@SuppressWarnings("serial")
class RollingCalendar extends GregorianCalendar {  
  
    int type = AppDailyRollingFileAppender.TOP_OF_TROUBLE;  
  
    RollingCalendar() {  
        super();  
    }  
  
    RollingCalendar(final TimeZone tz, final Locale locale) {  
        super(tz, locale);  
    }  
  
    void setType(final int type) {  
        this.type = type;  
    }  
  
    public long getNextCheckMillis(final Date now) {  
        return getNextCheckDate(now).getTime();  
    }  
  
    public Date getNextCheckDate(final Date now) {  
        this.setTime(now);  
  
        switch (type) {  
        case AppDailyRollingFileAppender.TOP_OF_MINUTE:  
            this.set(Calendar.SECOND, 0);  
            this.set(Calendar.MILLISECOND, 0);  
            this.add(Calendar.MINUTE, 1);  
            break;  
        case AppDailyRollingFileAppender.TOP_OF_HOUR:  
            this.set(Calendar.MINUTE, 0);  
            this.set(Calendar.SECOND, 0);  
            this.set(Calendar.MILLISECOND, 0);  
            this.add(Calendar.HOUR_OF_DAY, 1);  
            break;  
        case AppDailyRollingFileAppender.HALF_DAY:  
            this.set(Calendar.MINUTE, 0);  
            this.set(Calendar.SECOND, 0);  
            this.set(Calendar.MILLISECOND, 0);  
            final int hour = get(Calendar.HOUR_OF_DAY);  
            if (hour < 12) {  
                this.set(Calendar.HOUR_OF_DAY, 12);  
            } else {  
                this.set(Calendar.HOUR_OF_DAY, 0);  
                this.add(Calendar.DAY_OF_MONTH, 1);  
            }  
            break;  
        case AppDailyRollingFileAppender.TOP_OF_DAY:  
            this.set(Calendar.HOUR_OF_DAY, 0);  
            this.set(Calendar.MINUTE, 0);  
            this.set(Calendar.SECOND, 0);  
            this.set(Calendar.MILLISECOND, 0);  
            this.add(Calendar.DATE, 1);  
            break;  
        case AppDailyRollingFileAppender.TOP_OF_WEEK:  
            this.set(Calendar.DAY_OF_WEEK, getFirstDayOfWeek());  
            this.set(Calendar.HOUR_OF_DAY, 0);  
            this.set(Calendar.SECOND, 0);  
            this.set(Calendar.MILLISECOND, 0);  
            this.add(Calendar.WEEK_OF_YEAR, 1);  
            break;  
        case AppDailyRollingFileAppender.TOP_OF_MONTH:  
            this.set(Calendar.DATE, 1);  
            this.set(Calendar.HOUR_OF_DAY, 0);  
            this.set(Calendar.SECOND, 0);  
            this.set(Calendar.MILLISECOND, 0);  
            this.add(Calendar.MONTH, 1);  
            break;  
        default:  
            throw new IllegalStateException("Unknown periodicity type.");  
        }  
        return getTime();  
    }  
}