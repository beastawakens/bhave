package models.terms;
import org.junit.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.junit.Test;

import java.util.*;
import play.test.*;
import models.*;
import models.Dictionary;
import models.terms.BObject.BObjectType;

public class BObjectTest extends UnitTest {

    @Test
    public void copyingABObjectMarksItAsACopyAndUpdatesTheId() {
    	BObject source = new BObject("source"+String.valueOf(Math.random()), BObjectType.Value,"value");
    	
    	BObject copy = source.createTestCopy();
    	
    	assertThat(copy.testCopy, is(true));
    	assertThat(copy.name, is(source.name));
    	assertThat(copy.value, is(source.value));
    	assertThat(copy.objectType, is(source.objectType));
    	assertThat(copy.id, is(not(source.id)));
    }

}
