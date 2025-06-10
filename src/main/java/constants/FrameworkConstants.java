package constants;

import lombok.Getter;

import static java.util.Objects.isNull;

@Getter
public class FrameworkConstants {
    private FrameworkConstants(){}

    //to call these non static variables, i need to create a new object everytime which i see as a overload
    //Use singleton

    private static FrameworkConstants frameworkConstants;

    public static synchronized FrameworkConstants getInstance(){
        if(isNull(frameworkConstants)){
            frameworkConstants = new FrameworkConstants();
        }
        return frameworkConstants;
    }


    private final String resourcePath =System.getProperty("user.dir") + "/src/test/resources";
    private final String apkFilePath = resourcePath+"/apkFiles/leaforg.apk";
    private final String configFilePath = resourcePath+"/config/config.properties";



}
