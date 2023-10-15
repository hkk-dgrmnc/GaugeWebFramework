import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ElementsReader {

    private static Properties properties;

    public ElementsReader() {}
    public ElementsReader(String filePath) throws IOException {

        properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(filePath);
        properties.load(fileInputStream);
        fileInputStream.close();
    }

    public String getLocatorType(String key){
        String forKeyFullValue = properties.getProperty(key);
        String[] parts = forKeyFullValue.split(":", 2);
        return parts[0].trim();
    }

    public String getLocatorValue(String key){
        String forKeyFullValue = properties.getProperty(key);
        String[] parts = forKeyFullValue.split(":", 2);
        return parts[1].trim();
    }





}


