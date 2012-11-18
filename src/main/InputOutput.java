package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class InputOutput 
{
	public static void main(String arg[])throws Exception
	
	{
		readInput("IntegratedDataset.csv");
	}
	
	static String[] getItems(String line)
	{
		String result[] = line.split(",");
		for(int i=0;i<result.length;i++)
		{
			result[i]=result[i].trim().toLowerCase();
			System.out.println(result[i]+"......");
		}
		return result;		
	}
	
	static LinkedList<LinkedList<String>> readInput(String file_name)throws Exception
	{
		LinkedList<LinkedList<String>> all_transactions = new LinkedList<LinkedList<String>>();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file_name))));
		String line = "";
		int i=0;
		while ((line = br.readLine()) != null) 
		{
			System.out.println(line);
			String words[] = getItems(line);
			for(String word : words)
			{
				if(! word.equals("") && !word.equals("\\s+"))
				{
					all_transactions.get(i).add(word);
				}
			}
		}
		return all_transactions;
	}
}
