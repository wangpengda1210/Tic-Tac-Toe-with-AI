import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 Check whether the class declares public parameterless constructor
 */
class ConstructorChecker {

    public boolean checkPublicParameterlessConstructor(Class<?> clazz) {
        // Add implementation here
        Constructor[] constructors = clazz.getConstructors();

        for (Constructor constructor : constructors) {
            if (constructor.getModifiers() == Modifier.PUBLIC &&
                    constructor.getParameterCount() == 0) {
                return true;
            }
        }

        return false;
    }

}