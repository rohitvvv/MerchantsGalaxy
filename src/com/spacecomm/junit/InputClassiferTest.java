package com.spacecomm.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.spacecomm.InputClassifier;


public class InputClassiferTest
{
	
	InputClassifier classifier= new InputClassifier();
	@Test
	public void testAssignment() 
	{
		assertTrue(classifier.isAssignmentType("glob is I"));
	    assertTrue(classifier.isAssignmentType("a is I"));
	    assertFalse(classifier.isAssignmentType("foo is bar"));
	    assertFalse(classifier.isAssignmentType("a is a is a"));
	}
	@Test
	public void testCommodityCreditType()
	{
		assertTrue(classifier.isCommodityCreditType("glob glob Silver is 34 Credits"));
		assertTrue(classifier.isCommodityCreditType("globa glob glob Silver gold is 23 credits"));
		assertFalse(classifier.isCommodityCreditType("glob is I"));
	    assertFalse(classifier.isCommodityCreditType("a is I"));
	    assertFalse(classifier.isCommodityCreditType("foo is bar"));
	    assertFalse(classifier.isCommodityCreditType("a is a is a"));
		
	}
	@Test
	public void testIsHowMuchType()
	{
		assertTrue(classifier.isHowMuchType("how much is pish tegj glob glob ?"));
		assertTrue(classifier.isHowMuchType("how much is pish tegj glob glob"));
		assertFalse(classifier.isHowMuchType("123"));
	    assertFalse(classifier.isHowMuchType("glob is I?"));
	}
	@Test
	public void testIsHowManyType()
	{
		assertTrue(classifier.isHowManyType("how many Credits is glob prok Silver ?"));
		assertTrue(classifier.isHowManyType("How many Credits is glob prok Silver ?"));
		assertFalse(classifier.isHowManyType("123"));
		//assertFalse(classifier.isHowManyType("How many Credits is glob prok Silver"));
	}

}
