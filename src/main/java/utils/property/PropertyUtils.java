package utils.property;

import constants.FrameworkConstants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyUtils {

    public static final Properties properties = new Properties();
    private static Map<String,String> MAP = new HashMap<>();

    //kept in static block so taht we dont have to make explicit call to load and use the property file
    static {
        try {
            properties.load(new FileInputStream(FrameworkConstants.getInstance().getConfigFilePath()));
            for(Map.Entry<Object,Object> entry : properties.entrySet()){
                String key = String.valueOf(entry.getKey());
                String value = String.valueOf(entry.getValue());
                MAP.put(key,value);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(0);

        }
    }

    public static String getValue(String key){
        return MAP.get(key);
    }

}
