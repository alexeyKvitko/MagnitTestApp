package magnit.test.app.model;

/**
 * Created by a.kvitko on 13.01.2016.
 */
public class ApplicationProperties {

    public static final ApplicationProperties PROPS = new ApplicationProperties();

    private String dataBase;
    private String dataBaseUrl;
    private String userName;
    private String userPassword;
    private Integer fieldsCount;

    public String getDataBase() {
        return dataBase;
    }

    public void setDataBase(String dataBase) {
        this.dataBase = dataBase;
    }

    public String getDataBaseUrl() {
        return dataBaseUrl;
    }

    public void setDataBaseUrl(String dataBaseUrl) {
        this.dataBaseUrl = dataBaseUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getFieldsCount() {
        return fieldsCount;
    }

    public void setFieldsCount(Integer fieldsCount) {
        this.fieldsCount = fieldsCount;
    }

    @Override
    public String toString() {
        return "ApplicationProperties{" +
                "dataBase='" + dataBase + '\'' +
                ", dataBaseUrl='" + dataBaseUrl + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", fieldsCount=" + fieldsCount +
                '}';
    }
}
