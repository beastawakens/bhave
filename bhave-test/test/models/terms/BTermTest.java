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

public class BTermTest extends UnitTest {

    @Test
    public void copyingABVerbMarksItAsACopyAndUpdatesTheIdInTheCommand() {
    	String command = "something_"+Dictionary.TERM_ID_SUBSTITUTION+"_else";
		BTerm source = new BVerb("source"+String.valueOf(Math.random()), command);
		
		BTerm copy = source.createTestCopy();

		assertThat(copy.testCopy, is(true));
		assertThat(copy.name, is(source.name));
		assertThat(copy.command, is("something_"+copy.id+"_else"));
		assertThat(copy.id, is(not(source.id)));
    }

    @Test
    public void copyingABObjectMarksItAsACopyAndUpdatesTheId() {
    	BTerm source = new BObject("source"+String.valueOf(Math.random()), BObjectType.Value,"value");
    	
    	BTerm copy = source.createTestCopy();
    	
    	assertThat(copy.testCopy, is(true));
    	assertThat(copy.name, is(source.name));
    	assertThat(copy.value, is(source.value));
    	assertThat(copy.objectType, is(source.objectType));
    	assertThat(copy.id, is(not(source.id)));
    }

    @Test
    public void copyingABSubjectMarksItAsACopyAndUpdatesTheId() {
    	BTerm source = new BSubject("source"+String.valueOf(Math.random()));
    	
    	BTerm copy = source.createTestCopy();
    	
    	assertThat(copy.testCopy, is(true));
    	assertThat(copy.name, is(source.name));
    	assertThat(copy.id, is(not(source.id)));
    }

    @Test
    public void copyingABConjunctionMarksItAsACopyAndUpdatesTheId() {
    	BTerm source = new BConjunction("source"+String.valueOf(Math.random()));
    	
    	BTerm copy = source.createTestCopy();
    	
    	assertThat(copy.testCopy, is(true));
    	assertThat(copy.name, is(source.name));
    	assertThat(copy.id, is(not(source.id)));
    }

    @Test
    public void copyingABArticleMarksItAsACopyAndUpdatesTheId() {
    	BTerm source = new BArticle("source"+String.valueOf(Math.random()));
    	
    	BTerm copy = source.createTestCopy();
    	
    	assertThat(copy.testCopy, is(true));
    	assertThat(copy.name, is(source.name));
    	assertThat(copy.id, is(not(source.id)));
    }

    @Test
    public void copyingABSynonymMarksItAsACopyAndUpdatesTheId() {
    	BTerm source = new BSynonym("source"+String.valueOf(Math.random()), new LinkedList<Long>());
    	
    	BTerm copy = source.createTestCopy();
    	
    	assertThat(copy.testCopy, is(true));
    	assertThat(copy.name, is(source.name));
    	assertThat(copy.id, is(not(source.id)));
    }

}
