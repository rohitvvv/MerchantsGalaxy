package com.spacecomm.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.spacecomm.RomanNumerals;
public class TestRomanNumerals {

	RomanNumerals roman=new RomanNumerals();
	@Test
	public void testRomanValidity() 
	{
	 assertTrue(roman.validateRomanNumeral("XXX"));
	 assertFalse(roman.validateRomanNumeral("XXXX"));
	 assertTrue(roman.validateRomanNumeral("XI"));
	 assertTrue(roman.validateRomanNumeral("II"));
	 assertFalse(roman.validateRomanNumeral("IIII"));
	 assertFalse(roman.validateRomanNumeral("abcd"));
	 assertFalse(roman.validateRomanNumeral("123"));
	 assertTrue(roman.validateRomanNumeral("iii"));
	 assertTrue(roman.validateRomanNumeral("XXXIV"));
	 assertTrue(roman.validateRomanNumeral("XXXIX"));
	 assertTrue(roman.validateRomanNumeral("dccc"));
	 assertFalse(roman.validateRomanNumeral("dcccc"));
	 assertFalse(roman.validateRomanNumeral("MMMM"));
	 assertTrue(roman.validateRomanNumeral("ID"));
	 assertFalse(roman.validateRomanNumeral("MMMMCCCXII"));
	}
}
