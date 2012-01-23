package com.sjl.util.regex;

import static junit.framework.Assert.*;

import java.util.*;
import java.util.regex.*;

import org.junit.*;

import com.sjl.testing.*;
import com.sjl.testing.ThroughputComparison.Throughput;
import com.sjl.testing.ThroughputComparison.Worker;
import com.sjl.util.*;
import com.sjl.util.collections.*;

public class PatternsTest {

	@Test
	public void testAllowsSetPopulationByStringCapture() {		
		Set<String> _s1 = Patterns.captureAsSet("steve is cool", "(\\S*) (\\S*) (\\S*)");
		assertTrue(_s1.contains("steve"));
		assertTrue(_s1.contains("is"));
		assertTrue(_s1.contains("cool"));
	}
	
	@Test
	public void testAllowsSetPopulationByPatternsplit() {		
		Set<String> _s1 = Patterns.splitAsSet("steve is cool", "\\s+");
		assertTrue(_s1.contains("steve"));
		assertTrue(_s1.contains("is"));
		assertTrue(_s1.contains("cool"));
	}
	
	@Test
	public void providesFasterRepeatIterationsOfMatches()
	throws Exception {		
		
		ThroughputComparison _comparison = new ThroughputComparison();
		
		Throughput _t1 = _comparison.add(new Worker("Patterns"){
			public void performOneIteration() {
				Patterns.matches("steve is cool", "[s,t,e,v]+\\s[i,s]+\\s[c,o,l]+");				
			}
		});

		Throughput _t2 = _comparison.add(new Worker("Pattern"){
			public void performOneIteration() {
				Pattern.matches("steve is cool", "[s,t,e,v]+\\s[i,s]+\\s[c,o,l]+");	
			}			
		});

		_comparison.testConcurrentlyFor(500);
		
//		System.out.println(_t1);
//		System.out.println(_t2);
		
		assertTrue(_t1.getCompletedIterationCount() > _t2.getCompletedIterationCount());
	}
	
	@Test
	public void testMapsStringValuesUsingRegexSplit() {
		FuncMap<String, String> _map = Patterns.splitAsMap("steve is cool", " ", "steve is cool".split(" "));
		assertEquals("steve", _map.get("steve"));
		assertEquals("is", _map.get("is"));
		assertEquals("cool", _map.get("cool"));
	}

	@Test
	public void testMapsOnlyThoseValuesForWhichWeProvideSufficientKeys() {
		FuncMap<String, String> _map = Patterns.splitAsMap("steve is cool", " ", "steve is".split(" "));
		assertEquals("steve", _map.get("steve"));
		assertEquals("is", _map.get("is"));
		assertNull(_map.get("cool"));
	}
	
	@Test
	public void testDoesNotMapKeysForWhichNoValueIsAvailable() {
		FuncMap<String, String> _map = Patterns.splitAsMap("steve is", " ", "steve is cool".split(" "));
		assertEquals("steve", _map.get("steve"));
		assertEquals("is", _map.get("is"));
		assertFalse(_map.containsKey("cool"));
	}	

	@Test
	public void testMapsStringValuesUsingRegexCapture() {
		FuncMap<String, String> _map = Patterns.captureAsMap("steve is cool", "([a-z]*) ([a-z]*) ([a-z]*)", "steve is cool".split(" "));
		assertEquals("steve", _map.get("steve"));
		assertEquals("is", _map.get("is"));
		assertEquals("cool", _map.get("cool"));
	}

	@Test
	public void testCapturesOnlyThoseValuesForWhichWeProvideSufficientKeys() {
		FuncMap<String, String> _map = Patterns.captureAsMap("steve is cool", "([a-z]*) ([a-z]*) ([a-z]*)", "steve is".split(" "));
		assertEquals("steve", _map.get("steve"));
		assertEquals("is", _map.get("is"));
		assertNull(_map.get("cool"));
	}	
	
	@Test
	public void testUsesValueFactoryToCreateValuesWhereMissing() {
		ValueFactory<String, String> _vf = new ValueFactory<String, String>(){
			public String create(String aKey) {
				return aKey;
			}			
		};
		FuncMap<String, String> _map = Patterns.captureAsMap("steve is", "([a-z]*) ([a-z]*)", _vf, "steve is cool".split(" "));
		assertEquals("steve", _map.get("steve"));
		assertEquals("is", _map.get("is"));
		assertEquals("cool", _map.get("cool"));
	}
}
