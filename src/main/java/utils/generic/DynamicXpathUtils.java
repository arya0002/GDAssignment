package utils.generic;

public final class DynamicXpathUtils {
    private DynamicXpathUtils(){}

    public static String getXpath(String xpath, String value){
        return String.format(xpath,value);
    }
}
