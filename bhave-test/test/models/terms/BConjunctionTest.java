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

public class BConjunctionTest extends UnitTest {

    @Test
    public void copyingABConjunctionMarksItAsACopyAndUpdatesTheId() {
    	BConjunction source = new BConjunction("source"+String.valueOf(Math.random()));
    	
    	BConjunction copy = source.createTestCopy();
    	
    	assertThat(copy.testCopy, is(true));
    	assertThat(copy.name, is(source.name));
    	assertThat(copy.id, is(not(source.id)));
    }

}
