package com.spacecomm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser
{

	BufferedReader reader;
	String line;
	HashMap<String,String> translation; 
	List<Integer> sum;
	List<String> keyWords = new ArrayList<String>();
	HashMap<String,Integer> price = new HashMap<String,Integer>();
	InputClassifier classifier=null;

	/*
	 * Initialize data structures for state keeping
	 */
	public void init()
	{
		keyWords.add("how");
		keyWords.add("much");
		keyWords.add("?");
		keyWords.add("is");
		keyWords.add("Credits");
		keyWords.add("many");
		classifier= new InputClassifier();
		translation = new HashMap<String,String>();
		sum = new ArrayList<Integer>();
	}
	
	/*
	 *Parser parses the input from the file. Uses a classifier to classify 
	 *the input type 
	 * a) Assignment x is y ; a is b etc 
	 * b) x y commodity is decimal credits
	 * c) how much is a b c d ?
	 * d) how many credits is a b c d commodity ?
	 * @param filename URI of the filename
	 */
	
	public InputParser(String filename) 
	{ 
		try
		{
			init();
			reader= new BufferedReader(new FileReader(filename));
			
			while((line=reader.readLine())!=null)
			{
			  if(classifier.isAssignmentType(line))
			  {
				  buildTranslation(line);
			  }
			  else if(classifier.isHowMuchType(line))
			  {
				  sumRomanTranslation(line);
			  }
			  else if(classifier.isCommodityCreditType(line))
			  {
				  if(!(true==buildCommodityValues(line)))
					  invalidInput();
			  }
			  else if(classifier.isHowManyType(line))
			  {
				  if(!(true==evaluateQuery(line)))
					  invalidInput();	
				  //System.out.println(line);
			  }
			  else 
			  {  
				  invalidInput();
				  //System.out.println(line);
			  }
			}
		}
		catch(FileNotFoundException ex)
		{
			System.err.println(ex.toString());
		}
		catch(IOException ioex)
		{
			System.err.println(ioex.toString());
		}
		catch(NumberFormatException ex)
		{
			System.out.printf("\nI have no idea what you are talking about");
		}
	}
	
	/* 
	 * Assuming the input is assignment type input populate the translation 
	 * hashmap. 
	 * @param line An input line from the buffer reader
	 * @return void
	 */
	
	public void buildTranslation(String line)
	{
		String[] value = line.split("\\s+");
		translation.put(value[0], value[2]);
	}
	
	/*
	 * Assuming "how much type" input compute the Roman to decimal 
	 * e.g: how much is pish tegj glob glob ?
	 */	
	
	public void sumRomanTranslation(String line)
	{
		String[] alienLang = line.split("\\s+");
	    int i;
	    StringBuilder roman= new StringBuilder();
        System.out.println();       
	    for(i=0;i<alienLang.length;i++)
	    {
	      if(!keyWords.contains(alienLang[i]))
	    	{
	    	  roman.append(translation.get(alienLang[i]));
	    	  System.out.print(alienLang[i]+" ");
	    	}
	    }
	    System.out.print("is "+new RomanNumerals(roman.toString()).romanToDeciaml());
	}
	
	/*
	 * Extract digit out of the given buffered line
	 * @param line An input line from the buffer reader
	 * @return integer value extracted from the line
	 */
	public int extractDigit(String line)
	{
		Matcher matchedDigit = Pattern.compile("\\d+").matcher(line);
		int digit=0;
		if(matchedDigit.find())
		{
			digit = Integer.parseInt(matchedDigit.group());
		}
	    return digit;
	}
	
	/*
	 * Tests if given string is digit 
	 * @param str - input string
	 * @return boolean if input string is a digit of not
	 */
	public boolean isDigit(String str)
	{
		Matcher matchedDigit = Pattern.compile("\\d+").matcher(str);
		return matchedDigit.find();
	}

	/*
	 * Assuming input is of type commodity credit value type 
	 * build the data structure of key value pairs to store commodity
	 * value per unit 
	 * e.g: pish pish Iron is 3910 Credits
	 * @return boolean true indicates successful operation. false indicates
	 * something went wrong with the conversion. Bad input
	 * @param line An input line from the buffer reader
	 */
	
	public boolean buildCommodityValues(String line)
	{
	   String [] alienLang = line.split("\\s+");
       String commodity=null;
	   int credits=extractDigit(line);
	   int quantity=0;
	   StringBuilder roman= new StringBuilder();
	   for(String str:alienLang)
	   {
	     if(translation.get(str)!=null)
	     {
	          roman.append(translation.get(str));
	     }
	     if(translation.get(str)==null  && !keyWords.contains(str) && !isDigit(str))
	     {
	    	 commodity=str;
	     }
	   }
	   quantity = new RomanNumerals(roman.toString()).romanToDeciaml();
	   if(quantity==0)
			  return false;
	   //Store price per unit quantity
	   price.put(commodity, credits/quantity); 
	
       return true;
	}
	
	/* Assuming input is of how many type evaluate the value for 
	 * the commodity. 
	 * @line An input line from the buffer reader
	 * @boolean true indicates successful operation. false indicates
	 * something went wrong with the conversion. Bad input. 
	 * e.g: how many Credits is glob prok Iron ?
	 */
	
	public boolean evaluateQuery(String line)
	{
	  System.out.println();
      String[] alienLang = line.split("\\s+");
	  StringBuilder roman= new StringBuilder();
	  String commodity=null;
	  int quantity;
	  for(String str:alienLang)
	  {
		  if(translation.get(str)!=null)
		  {
			  roman.append(translation.get(str));
		      System.out.print(str+" ");
		  }
		  if(translation.get(str)==null  && !keyWords.contains(str) && !isDigit(str))
		  {
		      commodity=str;
	          System.out.print(commodity+" is ");
		  }
	  }
	  quantity = new RomanNumerals(roman.toString()).romanToDeciaml();
	  //Something went wrong
	  if(quantity==0)
		  return false;
	  System.out.print(price.get(commodity)*quantity+" Credits");
	  return true;
	}
	
	void invalidInput()
	{
	  System.out.printf("\nI have no idea what you are talking about");
	}
}
