package magnit.test.app;

import magnit.test.app.model.ApplicationProperties;
import magnit.test.app.util.AppUtil;
import magnit.test.app.service.MainServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

/**
 * Created by a.kvitko on 13.01.2016.
 */
public class Launcher {

    public static void main( String[] args ){
        System.out.println("START PROCESS AT: "+ AppUtil.formatDate( new Date() ) );
        parseInputArguments(args);
        if ( ApplicationProperties.PROPS.getFieldsCount() == null ){
            System.out.println("Program terminated");
            return;
        }
        createWorkDirectoryIfNotExist();
        MainServiceImpl mainService = new MainServiceImpl();
        mainService.execute();
        System.out.println("PROCESS ENDED SUCCESS AT: "+ AppUtil.formatDate( new Date() ) );
    }

    /**
     * Parsinng input arguments
     * fill ApplicationProperties singelton
     */
    private static void parseInputArguments( String[] args ){
        Integer fieldCount = null;
        if ( args.length < 5 ) {
            System.out.println("ERROR: Required parameters  not set ... \n");
            showHelp();
            return;
        }
        ApplicationProperties.PROPS.setDataBase(args[0].toUpperCase());
        ApplicationProperties.PROPS.setDataBaseUrl(args[1]);
        ApplicationProperties.PROPS.setUserName(args[2]);
        ApplicationProperties.PROPS.setUserPassword( args[3] );
        try {
            fieldCount = Integer.valueOf( args[4] );
            ApplicationProperties.PROPS.setFieldsCount(fieldCount);
        } catch ( NumberFormatException e ){
            System.err.println("\nERROR:  Integer format expected as field count...");
            showHelp();
            return;
        }
    }

    /**
     * Show help end examples how to use apllication
     */
    private static void showHelp(){
        System.out.println("HELP: Required parameters by order: database databaseUrl userName password fieldsCount\n");
        System.out.println("EXAMPLE:[MySql] mysql \"localhost:3306/magnit_test?autoReconnect=true\" magnit magnit 250000");
        System.out.println("EXAMPLE:[Oracle] oracle \"localhost:1521:magnit_test\" magnit magnit 250000");
        System.out.println("EXAMPLE:[Postgresql] \"postgresql localhost/magnit_test\" magnit magnit 250000");
    }

    /**
     * Create application work directory if it not exist
     */
    private static void createWorkDirectoryIfNotExist(){
        Path workDir = Paths.get ( AppConstants.WORK_DIRECTORY );

        if ( Files.notExists( workDir ) ){
            try {
                Files.createDirectory(workDir);
                File file = new File( AppConstants.TRANSFER_XLST_FILE );
                Files.write(Paths.get( file.toURI()), AppConstants.TEMPLATE_XLST.getBytes("utf-8"), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) {
                System.err.println("\nERROR: Can not create working directory [ "+AppConstants.WORK_DIRECTORY+" ]");
            }
        }
    }
}
