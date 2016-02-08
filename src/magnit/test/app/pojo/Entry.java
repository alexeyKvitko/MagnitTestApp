package magnit.test.app.pojo;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * Created by a.kvitko on 13.01.2016.
 */
public class Entry implements Serializable{

    private Integer field;

    public Integer getField() {
        return field;
    }

    @XmlElement(name="field")
    public void setField(Integer field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "field=" + field +
                '}';
    }

    @Override
    public int hashCode() {
        return getField() != null ? getField().hashCode() : 0;
    }
}
