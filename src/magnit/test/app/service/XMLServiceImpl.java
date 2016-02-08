package magnit.test.app.service;

import magnit.test.app.AppConstants;
import magnit.test.app.pojo.Entries;
import magnit.test.app.pojo.Entry;
import magnit.test.app.pojo.TargetEntries;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by a.kvitko on 13.01.2016.
 */
public class XMLServiceImpl {

    /**
     * Marshal Java class to XML
     *
     * @param entriesList - ArrayList<Entry>
     */
    public void createFirstXmlFile(ArrayList<Entry> entriesList) {
        try {
            JAXBContext jc = JAXBContext.newInstance(magnit.test.app.pojo.Entries.class);
            Marshaller m = jc.createMarshaller();
            Entries entries = new Entries();
            entries.setEntries(entriesList);
            OutputStream os = new FileOutputStream(AppConstants.FIRST_XML_FILE);
            m.marshal(entries, os);
        } catch (JAXBException | FileNotFoundException e) {
            System.err.println("ERROR:  Can not create first xml file, " + e.getMessage());
            System.err.println("Program terminated");
            System.exit(0);
        }
    }

    /**
     * Transform one XML to other use XLST
     */
    public void transformToSecondXml() {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            Source xslt = new StreamSource(new File(AppConstants.TRANSFER_XLST_FILE));
            transformer = factory.newTransformer(xslt);
            Source source = new StreamSource(new File(AppConstants.FIRST_XML_FILE));
            transformer.transform(source, new StreamResult(new File(AppConstants.SECOND_XML_FILE)));
        } catch (TransformerException e) {
            System.err.println("ERROR:  Can not transform to second xml file, " + e.getMessage());
            System.err.println("Program terminated");
            System.exit(0);
        }
    }

    /**
     * Unmarshal XML to Java class
     */
    public TargetEntries getEntriesFromSecondXml() {
        TargetEntries entries = null;
        try {
            JAXBContext jc = JAXBContext.newInstance(magnit.test.app.pojo.TargetEntries.class);
            Unmarshaller u = jc.createUnmarshaller();
            entries = (TargetEntries) u.unmarshal(new File(AppConstants.SECOND_XML_FILE));
        } catch (JAXBException e) {
            System.err.println("ERROR:  Can not unmarshal second xml file, " + e.getMessage());
            System.err.println("Program terminated");
            System.exit(0);
        }
        return entries;
    }

}
