package magnit.test.app;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by a.kvitko on 13.01.2016.
 */
public abstract class AppConstants {

    public static final String DATE_FORMAT = "dd-MMMM-yyyy HH:mm:ss";

    public static final String WORK_DIRECTORY = System.getProperty("user.home")+ File.separator+"magnit-test";

    public static final String COLUMN_FIELD = "field";
    public static final String FIRST_XML_FILE = WORK_DIRECTORY + File.separator + "1.xml";
    public static final String SECOND_XML_FILE = WORK_DIRECTORY + File.separator + "2.xml";
    public static final String TEMPLATE_XLST = "<xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"><xsl:template match=\"entries\"><entries><xsl:apply-templates/></entries></xsl:template><xsl:template match=\"entry\"><entry field=\"{field}\"/></xsl:template></xsl:stylesheet>";
    public static final String TRANSFER_XLST_FILE = WORK_DIRECTORY + File.separator + "transfer.xlst";


    public static final Map< String, String > JDBC_DRIVERS = new HashMap< String, String>(){{
        put("MYSQL","com.mysql.jdbc.Driver");
        put("ORACLE","oracle.jdbc.driver.OracleDriver");
        put("POSTGRESQL","org.postgresql.Driver");
    }};

    public static final Map< String, String > JDBC_URLS_PREFIX = new HashMap< String, String>(){{
        put("MYSQL","jdbc:mysql://");
        put("ORACLE","jdbc:oracle:thin:@");
        put("POSTGRESQL","jdbc:postgresql://");
    }};

    public static final int BATCH_SIZE = 5000;
    public static final int POOL_SIZE = 25;
}
