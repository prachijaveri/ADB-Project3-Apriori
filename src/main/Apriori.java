package main;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class Apriori
{
	static int count=1;
	//static Vector item;
	//static Vector sup;
	static LinkedList<Item> list_of_items = new LinkedList<Item>();
	static int transaction_count;
//	static Vector titem[];//list of all transactions
	static boolean flag=true;
//	static double min_support=0,min_confidence=0;
	static LinkedList<LinkedList<String>> large_items = new LinkedList<LinkedList<String>>();
	static Hashtable<LinkedList<String> , Double> candidate_items = new Hashtable<LinkedList<String> , Double>();
	static Hashtable<LinkedList<String> , Double> large_items_for_rules = new Hashtable<LinkedList<String> , Double>(); 
	static int iteration_number =1;
	
	static Item getItem(String item)
	{
		for(int j=0;j<list_of_items.size();j++)
		{
			if(list_of_items.get(j).getItemName().equalsIgnoreCase(item))
			{
				return list_of_items.get(j);
			}
		}
		return null;
	}
	
	static void printAllItems()
	{
		for(int j=0;j<list_of_items.size();j++)
		{
			System.out.println(list_of_items.get(j));
		}
	}
	
	static void getLargeItems(double min_support)
	{
		large_items = new LinkedList<LinkedList<String>>();
		Set<LinkedList<String>> keys = candidate_items.keySet();
		Iterator<LinkedList<String>> iterator = keys.iterator();
		while(iterator.hasNext())
		{
			LinkedList<String> l = (LinkedList<String>) iterator.next();
			if(candidate_items.get(l) >= min_support)
			{
				large_items_for_rules.put(l,candidate_items.get(l));
				large_items.add(l);
			}
		}
//		System.out.println(large_items);
//		System.out.println(large_items_for_rules);
	}
	
	static boolean contains(LinkedList<LinkedList<String>> all_subsets, LinkedList<String> subset)
	{
//		System.out.println("CONTAINS");
		for(int i=0;i<all_subsets.size();i++)
		{
			LinkedList<String> l =all_subsets.get(i);
			if(l.size() == subset.size())
			{
				boolean flag =true;
				for(int j=0;j<subset.size();j++)
				{
					if(!l.contains(subset.get(j)))
						flag = false;
				}
				if(flag)
				{
//					System.out.println("CONTAINS DONE");
					return flag;
				}
			}
		}
//		System.out.println("CONTAINS DONE");
		return false;
	}
	static LinkedList<LinkedList<String>> getSubsets(LinkedList<String> l ,int set_size)
	{
//		System.out.println("SUBSET");
//		System.out.println(set_size);
//		System.out.println(l);
		LinkedList<LinkedList<String>> all_subsets = new LinkedList<LinkedList<String>>();
		int n = (l.size()-set_size+1);
		for(int i=0;i<n;i++)
		{
			String start = l.get(i);
			for(int j=i+1;j<n+1;j++)
			{
				LinkedList<String> subset = new LinkedList<String>();
				subset.add(start);
				for(int k=j;k<j+set_size-1;k++)
				{
					subset.add(l.get(k));
				}
				if(!contains(all_subsets,subset))
					all_subsets.add(subset);
			}
		}
//		System.out.println(all_subsets);
//		System.out.println("SUBSET DONE");
		return all_subsets;
	}
	
	static boolean isLargeItem(LinkedList<String> a)
	{
		Set<LinkedList<String>> large_item_set = large_items_for_rules.keySet();
		Iterator<LinkedList<String>> i = large_item_set.iterator();
		while(i.hasNext())
		{
			LinkedList<String> b =(LinkedList<String>) i.next();
			flag = true;
			for(int j=0;j<a.size();j++)
			{
				if(! b.contains(a.get(j)))
					flag=false;
			}
			if(flag)
				return true;
		}
		return false;
	}
	static boolean checkSubsets(LinkedList<String> l, String item)
	{
//		System.out.println("CHECK WATEVER");
		
		for(int i=1 ; i < l.size() ; i++)
		{
			LinkedList<LinkedList<String>> subsets = getSubsets(l,i);
			System.out.println(i+"   "+subsets);
//			System.out.println()
			for(int j=0;j<subsets.size();j++)
			{
				subsets.get(j).add(item);
				if(!isLargeItem(subsets.get(i)) && subsets.get(i).contains(item))
					return true;
			}
		}
		return false;
	}
	
	static LinkedList<String> combine(LinkedList<String> l , LinkedList<String> m)
	{
		LinkedList<String> combine_list = new LinkedList<String>();
		for(int i=0;i<l.size();i++)
			combine_list.add(l.get(i));
		System.out.println("\nCOMBINING  ");
		System.out.println(l);
		System.out.println(m);
//		System.out.println("COMBINE WATEVER");
		for(int i =0;i<m.size();i++)
		{
			String item = m.get(i);
			if(checkSubsets(l,item))
				return null;
			if(!combine_list.contains(item))
			{
				combine_list.add(item);
			}
		}
		System.out.println(combine_list);
		return combine_list;
	}
	
	static double getSupport(LinkedList<String> l)
	{
		System.out.println("\n\nSUPPORT FOR " + l);
		double support = 0.0;
		LinkedList<String> transactions = getItem(l.get(0)).getTransactionIds();
		System.out.println(transactions);
		for(int i=1;i<l.size();i++)
		{
			LinkedList<String> t = getItem(l.get(i)).getTransactionIds();
			System.out.println("\t"+t);
			for(int j =0 ;j<transactions.size();j++)
			{
				if(! t.contains(transactions.get(j)))
				{
					transactions.remove(j);
					j--;
				}
			}
		}
		System.out.println(transactions);
		support = ((double)transactions.size()) / transaction_count;
		System.out.println("SUPPORT DONE\n");
		return support;
	}
	
	static void getCandidateItems(double min_support,int size)
	{
//		System.out.println("WATEVER");
//		System.out.println(large_items);
		candidate_items = new Hashtable<LinkedList<String>,Double>();
		for(int i = 0;i<large_items.size()-1;i++)
		{
			for(int j = i+1;j<large_items.size();j++)
			{
				LinkedList<String> z = combine(large_items.get(i),large_items.get(j));
				if(z != null)
				{
					double support = getSupport(z);
					if(z.size() == size)
						candidate_items.put(z,support);
				}
			}
		}
	}
	
	
	
	public static void start(LinkedList<LinkedList<String>>all_transactions, double min_support, double min_confidence)
	{
//		int size = 1;
//		GETTING ALL ITEMS
		transaction_count = all_transactions.size();
		System.out.println(transaction_count);
		for(int i=0;i<transaction_count;i++)	
		{
			LinkedList<String> basket = all_transactions.get(i);
//			System.out.println(basket);
			int items_size=basket.size();
			for(int j=0; j<items_size; j++)
			{
				String item_in_basket = basket.get(j);
				Item item = getItem(item_in_basket);
				if(item == null)
				{
					item = new Item(item_in_basket,(i+1));
					list_of_items.add(item);
				}
				else
				{
					item.addTransaction((i+1));
					item.increaseCount();
				}
			}
		}
		
		for(int i=0; i<list_of_items.size();i++)
		{
			Item item = list_of_items.get(i);
			item.calculateSupport(transaction_count);  
//			if(item.getSupport() >= min_support)
//			{
				LinkedList<String> l = new LinkedList<String>();
				l.add(item.getItemName());
				candidate_items.put(l,item.getSupport());
//			}
		}
		System.out.println("------------------------------ "+iteration_number+" -------------------------------");
		System.out.println(candidate_items);
		getLargeItems(min_support);
		System.out.println("L  "+large_items);
		iteration_number++;
		printAllItems();
		do
		{
			System.out.println("------------------------------ "+iteration_number+" -------------------------------");
			getCandidateItems(min_support,iteration_number);
			System.out.println("C  "+candidate_items);
			getLargeItems(min_support);
			System.out.println("L  "+large_items);
			iteration_number++;
		}while(checkAllConditions());
	}	
	
	static boolean checkAllConditions()
	{
		if(list_of_items.size() <= iteration_number)
			return false;
		if(candidate_items.size() == 0)
			return false;
		if(large_items.size() == 0)
			return false;
		return true;
	
	}	
}