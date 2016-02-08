package magnit.test.app.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by a.kvitko on 13.01.2016.
 */
@XmlRootElement(name="entries")
public class Entries implements Serializable {


    private ArrayList< Entry > entries;

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    @XmlElement(name="entry")
    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

}
