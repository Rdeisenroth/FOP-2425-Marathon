package h09;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * An object of class {@link StackOfObjects} represents a data structure of type stack.
 */
@SuppressWarnings({"ManualArrayCopy"})
public class StackOfObjects<TODO_REPLACE> {
    @StudentImplementationRequired("H9.1.1") // TODO: H9.1.1
    private Object[] objs = new Object[0];

    /**
     * Pushes the given object on this stack.
     *
     * @param obj the object
     */
    @StudentImplementationRequired("H9.1.2") // TODO: H9.1.2
    public void push(Object obj) {
        Object[] newArray = new Object[objs.length + 1];
        for (int i = 0; i < objs.length; i++) newArray[i] = objs[i];
        newArray[objs.length] = obj;
        objs = newArray;
    }

    /**
     * Removes the given object from this stack.
     *
     * @param obj the object
     */
    @StudentImplementationRequired("H9.1.3") // TODO: H9.1.3
    public void remove(Object obj) {
        Object[] newArray = new Object[objs.length - 1];
        int n = 0;
        for (Object currObj : objs) {
            if (currObj == obj) continue;
            newArray[n++] = currObj;
        }
        objs = newArray;
    }

    /**
     * Returns the number of objects in this stack.
     *
     * @return the number of objects
     */
    @DoNotTouch
    public int size() {
        return objs.length;
    }

    /**
     * Returns the object at the given index in this stack.
     *
     * @param index the index
     * @return the object
     */
    @StudentImplementationRequired("H9.1.4") // TODO: H9.1.4
    public Object get(int index) {
        return objs[index];
    }

    /**
     * Removes and returns the top object of this stack.
     *
     * @return the top object
     */
    @StudentImplementationRequired("H9.1.4") // TODO: H9.1.4
    public Object pop() {
        Object o = get(objs.length - 1);
        remove(o);
        return o;
    }

    /**
     * Constructs and returns a stack with the given objects.
     * The last object is the top object.
     *
     * @param objs the objects
     * @return the stack
     */
    @SafeVarargs
    @StudentImplementationRequired("H9.1.5") // TODO: H9.1.5
    public static StackOfObjects of(Object... objs) {
        StackOfObjects stack = new StackOfObjects();
        stack.objs = objs;
        return stack;
    }
}
