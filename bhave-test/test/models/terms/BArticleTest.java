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

public class BArticleTest extends UnitTest {

    @Test
    public void copyingABArticleMarksItAsACopyAndUpdatesTheId() {
    	BArticle source = new BArticle("source"+String.valueOf(Math.random()));
    	
    	BArticle copy = source.createTestCopy();
    	
    	assertThat(copy.testCopy, is(true));
    	assertThat(copy.name, is(source.name));
    	assertThat(copy.id, is(not(source.id)));
    }

}
