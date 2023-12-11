import org.yaml.snakeyaml.Yaml;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class ValueReader {
    private Map<String, Object> yamlData;

    public ValueReader(String filePath) {

        try (InputStream input = Files.newInputStream(Paths.get(filePath))) {
            Yaml yaml = new Yaml();
            yamlData = yaml.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String text){
        String value;
        if (yamlData.containsKey(text)) {
            value = yamlData.get(text).toString();
        } else {
            throw new AssertionError( text + " is not found in value.yaml");
        }
        return value;
    }
}