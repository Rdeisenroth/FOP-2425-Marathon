package h09;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * An object of class {@link StackOfObjects} represents a data structure of type stack.
 */
@SuppressWarnings({"ManualArrayCopy"})
public class StackOfObjects<O> {
    @StudentImplementationRequired("H9.1.1")
    private O[] objs = (O[]) new Object[0];

    /**
     * Pushes the given object on this stack.
     *
     * @param obj the object
     */
    @StudentImplementationRequired("H9.1.2")
    public void push(O obj) {
        O[] newArray = (O[]) new Object[objs.length + 1];
        for (int i = 0; i < objs.length; i++) newArray[i] = objs[i];
        newArray[objs.length] = obj;
        objs = newArray;
    }

    /**
     * Removes the given object from this stack.
     *
     * @param obj the object
     */
    @StudentImplementationRequired("H9.1.3")
    public void remove(O obj) {
        O[] newArray = (O[]) new Object[objs.length - 1];
        int n = 0;
        for (O currObj : objs) {
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
    @StudentImplementationRequired("H9.1.4")
    public O get(int index) {
        return objs[index];
    }

    /**
     * Removes and returns the top object of this stack.
     *
     * @return the top object
     */
    @StudentImplementationRequired("H9.1.4")
    public O pop() {
        O o = get(objs.length - 1);
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
    @StudentImplementationRequired("H9.1.5")
    public static <O> StackOfObjects<O> of(O... objs) {
        StackOfObjects<O> stack = new StackOfObjects<>();
        stack.objs = objs;
        return stack;
    }
}
