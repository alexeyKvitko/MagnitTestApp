package magnit.test.app.service;

import magnit.test.app.model.ApplicationProperties;
import magnit.test.app.pojo.Entry;
import magnit.test.app.pojo.TargetEntries;
import magnit.test.app.pojo.TargetEntry;

import java.util.ArrayList;

/**
 * Created by a.kvitko on 13.01.2016.
 */
public class MainServiceImpl {

    private DAOServiceImpl daoService;
    private XMLServiceImpl xmlService;

    public MainServiceImpl() {
        this.daoService = new DAOServiceImpl();
        this.xmlService = new XMLServiceImpl();
   }

    /**
     *  The method of initiating the process of transformation
     */
    public void execute(){
        System.out.print("   INFO: update fields in database with " + ApplicationProperties.PROPS.getFieldsCount().toString() + " records ... ");
        daoService.updateFieldsInDatabase();
        System.out.println("SUCCESS.");
        System.out.print("   INFO: select fieids from test database ... ");
        ArrayList<Entry> entries = daoService.selectFieldsFromDatabase();
        System.out.println("SUCCESS.");
        System.out.print("   INFO: create first xml file ... ");
        xmlService.createFirstXmlFile(entries);
        System.out.println("SUCCESS.");
        System.out.print("   INFO: transform one xml file to other ... ");
        xmlService.transformToSecondXml();
        System.out.println("SUCCESS.");
        System.out.print("   INFO: unmarshal second xls ... ");
        TargetEntries targetEntries = xmlService.getEntriesFromSecondXml();
        System.out.println("SUCCESS.");
        long summAllFields = 0l;
        if ( targetEntries != null ){
            for(TargetEntry entry : targetEntries.getEntries() ){
                summAllFields += entry.getField();
            }
        }
        System.out.println("\n R E S U L T = "+summAllFields+"\n" );
    }
}
