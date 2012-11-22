package main;

import java.util.LinkedList;

public class Main 
{
	public static void main(String arg[])
	{
		try
		{
			double min_support = 0;//Double.parseDouble(arg[0]);
			double min_confidence  = 0;//Double.parseDouble(arg[1]);
			LinkedList<LinkedList<String>> all_transactions = InputOutput.readInput("IntegratedDataset.csv");
			System.out.println("READING INPUT DONE");
			Apriori.start(all_transactions,min_support, min_confidence);
			System.out.println("APRIORI DONE");
			Apriori.printAllItems();
			
//			AssociationRule r = new AssociationRule("hello" , "watever");
//			r.addToLeft("gdsf");
//			r.addToLeft("dsfgtrg");
//			r.addToRight("Dfvasd");
//			System.out.println("\n"+r);
			System.out.println("------------------------------------------END-----------------------------------------------");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
}
