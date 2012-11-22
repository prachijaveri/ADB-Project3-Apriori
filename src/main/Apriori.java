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
		Iterator iterator = keys.iterator();
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
					return flag;
			}
		}
		return false;
	}
	static LinkedList<LinkedList<String>> getSubsets(LinkedList<String> l ,int set_size)
	{
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
		System.out.println(all_subsets);
		return all_subsets;
	}
	
	static boolean checkSubsets(LinkedList<String> l, String item)
	{
		for(int i=0 ; i < l.size()/2 +1 ; i++)
		{
			LinkedList<LinkedList<String>> subsets = getSubsets(l,i);
			for(int j=0;j<l.size();j++)
			{
				
			}
		}
		return false;
	}
	
	static LinkedList<String> combine(LinkedList<String> l , LinkedList<String> m)
	{
		for(int i =0;i<m.size();i++)
		{
			String item = m.get(i);
			if( checkSubsets(l,item))
				return null;
			if(!l.contains(m))
			{
				
			}
		}
		return l;
	}
	
	static void getCandidateItems(double min_support)
	{
		candidate_items = new Hashtable<LinkedList<String>,Double>();
		for(int i = 0;i<large_items.size()-1;i++)
		{
			for(int j = i+1;j<large_items.size();j++)
			{
				LinkedList<String> l = combine(large_items.get(i),large_items.get(j));
			}
		}
	}
	
	
	
	public static void start(LinkedList<LinkedList<String>>all_transactions, double min_support, double min_confidence)
	{
		//GETTING ALL ITEMS
		transaction_count = all_transactions.size();
//		System.out.println(transaction_count);
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
		
		getLargeItems(min_support);
		getCandidateItems(min_support);
		
//		System.out.println(candidate_items);

	
//	static void getCandidate()
//	{
//		Vector newitem=new Vector();
//		for(int i=0;i<item.size();i++)
//		{
//			for(int j=i+1;j<item.size();j++)
//			{
//				String x=item.elementAt(i)+"";
//				String y=item.elementAt(j)+"";
//				String z[]=y.split(",");
//				for(int k=0;k<z.length;k++)
//				{
//					if(x.indexOf(z[k]) == -1)
//						x=(x+","+z[k]);
//				}
//				if( !has(newitem,x))
//				{
//					newitem.add(x);
//				}
//			}
//		}
//	
//		Vector newsup=getSupport(newitem);
//		if(newitem.size() == item.size())
//			flag=false;		
//		for(int i=0;i<newitem.size();i++)
//		{
//			if((Double.parseDouble(newsup.elementAt(i)+"")) < min_support)
//			{
//				newitem.removeElementAt(i);
//				newsup.removeElementAt(i);
//				i--;
//			}
//		}
//		if(newitem.size()<1)
//			flag=false;
//		else
//		{
//			item=new Vector();
//			sup=new Vector();
//			for(int i=0;i<newitem.size();i++)
//			{
//				item.add(newitem.elementAt(i));
//				sup.add(newsup.elementAt(i));
//			}
//		}
//		System.out.println("ItemSet\tSupport");
//		for(int i=0;i<item.size();i++)
//			System.out.println(item.elementAt(i)+"\t\t"+sup.elementAt(i));
//		System.out.println();
//	}
//	
//	static Vector getSupport(Vector newitem)
//	{
//		Vector newsup=new Vector();
//		for(int i=0;i<newitem.size();i++)
//		{
//			String s=newitem.elementAt(i)+"";
//			String r[]=s.split(",");
//			boolean f;
//			int cnt=0;
//			for(int j=0;j<titem.length;j++ )
//			{
//				f=true;
//				for(int k=0;k<r.length;k++)
//					if(!titem[j].contains(r[k]))
//						f=false;
//				if(f)
//					cnt++;
//			}
//			newsup.add(cnt);
//		}
//		return newsup;
//	}
//	
//	static double getConfidence(String a,int index)
//	{
//		double con=Double.parseDouble(sup.elementAt(index)+"");
//		String b[]=a.split(" => ");
//		String c[]=b[0].split(" ");
//		double cnt=0;
//		boolean f1=true;
//		for(int i=0;i<titem.length;i++)
//		{
//			f1=true;
//			for(int j=0;j<c.length;j+=2)
//			{
//				if(! titem[i].contains(c[j]))
//					f1=false;
//			}
//			if(f1)
//				cnt++;
//		}
//		con=con/cnt;
//		return con;
	}	
}