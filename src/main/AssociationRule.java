package main;

import java.util.LinkedList;

public class AssociationRule 
{
	LinkedList<String> left = new LinkedList<String>();
	LinkedList<String> right = new LinkedList<String>();
	double support;
	double confidence;
	
	AssociationRule(String l, String r)
	{
		left.add(l);
		right.add(r);
	}
	
	void addToLeft(String l)
	{
		left.add(l);
	}
	
	void addToRight(String r)
	{
		right.add(r);
	}
	
	public String toString()
	{
		return left +" ---> " + right +"\nSupport : "+support+"\nConfidence : "+confidence;
	}

}
