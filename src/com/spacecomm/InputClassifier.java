package com.spacecomm;

/* InputClassifier class helps in identifying various 
 * types of input 
 * Input is of type 
 * 1. Assignment x is y ; a is b etc 
 * 2. x y commodity is decimal credit
 * 3. how much is a b c d ?
 * 4. how many credits is a b c d commodity ?
 */

public class InputClassifier
{
	final String assignmentType="[a-z|A-Z]*(\\s+)(is)(\\s+)[(I||V|X|L|C|D|M]*";
	final String howMuchType="([h|H]ow much is)(\\s+)(.*)?";	
	final String commodityCreditType="([a-z|A-Z]*(\\s+))*(is)(\\s+)(\\d+)(\\s+)([C|c]redits)";
	final String howManyType = "([H|h]ow many [C|c]redits is )(.*)?";
	
	public boolean isAssignmentType(String line)
	{
	   return line.matches(assignmentType);	
    }
	public boolean isHowMuchType(String line)
	{
		return line.matches(howMuchType);
	}
	public boolean isCommodityCreditType(String line)
	{
	   return line.matches(commodityCreditType);
	}	
	public boolean isHowManyType(String line)
	{
		return line.matches(howManyType);
	}
}