package main;

import java.util.LinkedList;

public class Item 
{
	String item_name ="";
	LinkedList<String> transaction_ids = new LinkedList<String>();
	int count = 0;
	
	Item(String name, int id)
	{
		item_name = name.toLowerCase();
		transaction_ids.add(id+"");
		count++;
	}
	
	void increaseCount()
	{
		count++;
	}
	
	void addTransaction(int id)
	{
		if(! transaction_ids.contains(id+""))
			transaction_ids.add(id+"");
	}
	
	String getItemName()
	{
		return item_name;
	}
	
	public String toString()
	{
		return item_name+"  "+count+"  "+transaction_ids.toString();
	}
}
