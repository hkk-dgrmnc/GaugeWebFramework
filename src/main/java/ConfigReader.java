import org.yaml.snakeyaml.Yaml;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class ConfigReader {
    private Map<String, Object> yamlData;

    public ConfigReader(String filePath) {

        try (InputStream input = Files.newInputStream(Paths.get(filePath))) {
            Yaml yaml = new Yaml();
            yamlData = yaml.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key){
        String value;
        if (yamlData.containsKey(key)) {
            value = yamlData.get(key).toString();
        } else {
            throw new AssertionError("Key not found: " + key);
        }
        return value;
    }
}
