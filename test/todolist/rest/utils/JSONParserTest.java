package todolist.rest.utils;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import todolist.entitys.Task;

public class JSONParserTest {
	
	private List<Task> tasks;
	private JSONParser parser ;
	private TaskToJsonParser taskParser = new TaskToJsonParser();
	
	@Before public void setUp(){
		tasks = new ArrayList<Task>();
		parser = new JSONParser();		
	}
		
	@Test public void whenTaskListIsempty(){
		String result = "{}";	
		assertEquals(parser.toStringJSON(tasks),result);
	}
	
	@Test public void whenTaskListWithOneElement(){
		tasks.add(new Task("taskOne",Task.Status.PENDING));
		String result = "{"+taskParser.parser(tasks.get(0))+"}";	
		assertEquals(parser.toStringJSON(tasks),result);
	}
	
	@Test public void whenTaskListWithTwoElements(){
		tasks.add(new Task("taskOne",Task.Status.PENDING));
		tasks.add(new Task("taskOne",Task.Status.PENDING));
		String result = "{"+taskParser.parser(tasks.get(0))+","+taskParser.parser(tasks.get(1))+"}";	
		assertEquals(parser.toStringJSON(tasks),result);		
	}
}
