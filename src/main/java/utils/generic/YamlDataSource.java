
package utils.generic;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface YamlDataSource {
    String value(); // Path to the YAML file
}
