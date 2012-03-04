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

public class BVerbTest extends UnitTest {

    @Test
    public void copyingABVerbMarksItAsACopyAndUpdatesTheId() {
    	String command = "something_"+Dictionary.TERM_ID_SUBSTITUTION+"_else";
		BVerb source = new BVerb("source"+String.valueOf(Math.random()), command);
		
		BVerb copy = source.createTestCopy();

		assertThat(copy.testCopy, is(true));
		assertThat(copy.name, is(source.name));
		assertThat(copy.command, is(source.command));
		assertThat(copy.id, is(not(source.id)));
    }

}
