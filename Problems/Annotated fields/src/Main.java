import java.lang.reflect.*;
import java.util.Arrays;

/**
 Get an array of fields the object declares that contain annotations (inherited fields should be skipped).
 */
class AnnotationsUtil {

    public static String[] getFieldsContainingAnnotations(Object object) {
        // Add implementation here
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotations().length > 0)
                .map(Field::getName)
                .toArray(String[]::new);
    }

}