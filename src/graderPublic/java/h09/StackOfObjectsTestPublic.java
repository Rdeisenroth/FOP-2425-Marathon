package h09;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import static h09.H09_TestUtils.assertDefinedParameters;
import static h09.H09_TestUtils.assertParameters;
import static h09.H09_TestUtils.assertReturnParameter;
import static h09.H09_TestUtils.assertType;
import static h09.H09_TestUtils.getTypeParameters;
import static h09.H09_TestUtils.match;
import static h09.H09_TestUtils.matchArray;
import static h09.H09_TestUtils.matchNested;
import static h09.H09_TestUtils.matchNoBounds;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertNotNull;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.emptyContext;
import static org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers.identical;

@TestForSubmission
public class StackOfObjectsTestPublic {

    BasicTypeLink stackLink;
    Class<?> ctClassStack;
    Method get;
    Method pop;
    Method push;
    Method remove;
    Method of;
    Field objs;


    @BeforeEach
    public void setUp() {
        stackLink = BasicTypeLink.of(StackOfObjects.class);
        ctClassStack = stackLink.reflection();
        get = stackLink.getMethod(identical("get")).reflection();
        pop = stackLink.getMethod(identical("pop")).reflection();
        push = stackLink.getMethod(identical("push")).reflection();
        remove = stackLink.getMethod(identical("remove")).reflection();
        of = stackLink.getMethod(identical("of")).reflection();
        objs = stackLink.getField(identical("objs")).reflection();
    }

    @Test
    public void testClassParameter() {
        assertDefinedParameters(ctClassStack, Set.of(matchNoBounds("O")));
    }

    @Test
    public void testObjsType() {
        assertType(objs, matchArray(matchNoBounds("O")));
        assertNotNull(
            ReflectionUtils.getFieldValue(new StackOfObjects<>(), "objs"),
            emptyContext(),
            r -> "Field objs is not correctly initialized"
        );
    }

    @Test
    public void testPushParameter() {
        assertParameters(push, List.of(matchNoBounds("O")));
    }

    @Test
    public void testRemoveParameter() {
        assertParameters(remove, List.of(matchNoBounds("O")));
    }

    @Test
    public void testPopParameter() {
        assertReturnParameter(pop, matchNoBounds("O"));
    }

    @Test
    public void testGetParameter() {
        assertReturnParameter(get, matchNoBounds("O"));
    }

    @Test
    public void testOfParameter() {
        assertDefinedParameters(of, Set.of(matchNoBounds("O")));

        List<Type> types = getTypeParameters(of, ".*");

        assertReturnParameter(
            of,
            matchNested(StackOfObjects.class, match(((GenericArrayType) types.get(0)).getGenericComponentType()))
        );

        assertParameters(of, List.of(match(types.get(0))));
    }
}
