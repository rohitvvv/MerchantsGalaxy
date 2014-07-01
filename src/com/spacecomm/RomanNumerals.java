package com.spacecomm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RomanNumerals 
{
    int length;
	String roman;
	char []val;
	Matcher matchedDigit;
	final String romanType="[[I|i]|[V|v]|[X|x]|[L|l]|[C|c]|[D|d]|[M|m]]*";
	/*
	 * numerals is a enumeration of standard Roman values from which
	 * other values can be derived
	 */
	
	public enum numerals 
    {
	 I(1),V(5),X(10),L(50),C(100),D(500),M(1000);
	 private int value;
	 private numerals(int value)
	 {
		 this.value=value;
	 }
	 public int getValue()
	 {
		 return value;
	 }
    };
   
    public RomanNumerals()
	{
	
	}
    
    /*
     * Constructor which implicitly checks the validity of the Roman numeral
     */
	
    public RomanNumerals(String roman)
	{
		if(true==validateRomanNumeral(roman))
		{
			this.roman=roman.toUpperCase();
			length = roman.length();
			val = this.roman.toCharArray();
		}
	}
	
    /*
	 * Validates the roman representation
	 * @return boolean indicating valid or not
	 */
    
    public boolean validateRomanNumeral(String roman)
    {
    	
    	if(roman!=null)
    	{
	        if(!roman.matches(romanType))
	        	return false;
    		matchedDigit = Pattern.compile("(D|d)(D|d)+").matcher(roman);
	    	if(matchedDigit.find()){return false;}
	    	matchedDigit = Pattern.compile("(L|l)(L|l)+").matcher(roman);
	    	if(matchedDigit.find()){return false;}
	    	matchedDigit = Pattern.compile("(V|v)(V|v)+").matcher(roman);
	    	if(matchedDigit.find()){return false;}
	    	matchedDigit = Pattern.compile("(XXX)X+|(xxx)x+").matcher(roman);
	    	if(matchedDigit.find()){return false;}
	    	matchedDigit = Pattern.compile("(III)I+|(iii)i+").matcher(roman);
	    	if(matchedDigit.find()){return false;}
	    	matchedDigit = Pattern.compile("(CCC)C+|(ccc)c+").matcher(roman);
	    	if(matchedDigit.find()){return false;}
	    	matchedDigit = Pattern.compile("(MMM)M+|(mmm)m+").matcher(roman);
	    	if(matchedDigit.find()){return false;}
	    	
	        for(int i=0;i<length;i++)
	    	{
	    		int x,y;
	    		if(length==1)
	    			return true;
	    		 x = getEnumValue(i);
	             y = getEnumValue(i+1);
	    		if(x>=y)
	    			continue;
	    		
	    		else//x,y y>x condition
	    		{
	    		 if(y==numerals.V.value||y==numerals.L.value||y==numerals.D.value)
	    			 return false;
	    		 if(y==numerals.I.value&&!(x==numerals.V.value||x==numerals.X.value))
	    		 {
	    			 return false;
	    		 }	 
	    		 if(y==numerals.X.value&&!(x==numerals.L.value||x==numerals.C.value))
	    		 {
	    			 return false;
	    		 }
	    		 if(y==numerals.C.value&&!(x==numerals.D.value||x==numerals.M.value))
	    		 {
	    			 return false;
	    		 }
	    		 i++;
	    		}
	    	}
	       
    	}
	    return true;
    }
    
    int getEnumValue(int index)
    {
    	return numerals.valueOf(String.valueOf(val[index])).getValue();
    }
    
    /*
     * romanToDecimal converts the Roman representation to decimal given the Roman value is valid
     * @return decimal value
     */
    int romanToDeciaml()
    {
        int i;
        int decimal=0;
        for(i=0;i<length;i++)
        {
          int x,y;
          //Single digit numeral
          if(length==1||i==length-1)
        	  return decimal+getEnumValue(i);
     
          x = getEnumValue(i);
          y = getEnumValue(i+1);
  
          if(x>=y)
          {
        	  decimal += x;
        	  continue;
          }
          else if(y>x)
          {
        	  decimal += y-x;  
        	  //When grouping is found increment
        	  i++; 
          }
        }
    	return decimal;
    }
    
}
