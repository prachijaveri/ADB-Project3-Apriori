package main;

import java.util.LinkedList;

public class Item 
{
	String item_name ="";
	LinkedList<String> transaction_ids = new LinkedList<String>();
	int count = 0;
	
	Item(String name, int id)
	{
		item_name = name;
		transaction_ids.add(id+"");
		count++;
	}
	
	void increaseCount()
	{
		count++;
	}
	
	void addTransaction(int id)
	{
		transaction_ids.add(id+"");
	}
}
