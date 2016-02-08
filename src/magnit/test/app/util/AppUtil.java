package magnit.test.app.util;

import magnit.test.app.AppConstants;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by a.kvitko on 13.01.2016.
 */
public abstract class AppUtil {

    /**
     *  Format Date to String
     *  @param date - Date
     *  @return String
     */
    public static String formatDate( Date date ) {
        SimpleDateFormat sdf = new SimpleDateFormat( AppConstants.DATE_FORMAT );
        String result = null;
        try {
            result = sdf.format(date);
        } catch ( Exception ex) {
            System.out.println("ERROR:  Can not format date ...");
        }
        return result;
    }
}
