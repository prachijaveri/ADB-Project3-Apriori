package main;

import java.util.LinkedList;

public class Main 
{
	public static void main(String arg[])
	{
		try
		{
			double min_support = Double.parseDouble(arg[0]);
			double min_confidence  = Double.parseDouble(arg[1]);
			LinkedList<LinkedList<String>> all_transactions = InputOutput.readInput("IntegratedDataset.csv");
//			System.out.println("READING INPUT DONE");
			Apriori.start(all_transactions,min_support, min_confidence);
//			Apriori.printAllItems();
//			LinkedList<String> l =new LinkedList<String>();
//			l.add("a");
//			l.add("b");
//			l.add("c");
//			l.add("d");
//			l.add("e");
//			l.add("f");
//			l.add("g");
//			l.add("h");
//			l.add("i");
//			l.add("j");
//			l.add("k");
//			l.add("l");
//			l.add("m");
//			l.add("e");
//			System.out.println(l);
//			System.out.println();
//			Apriori.getSubsets(l, 5);
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
