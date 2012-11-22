package main;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Vector;

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
	LinkedList<String> large_items = new LinkedList<String>();
	Hashtable<LinkedList<String> , Double> candidate_items = new Hashtable<LinkedList<String> , Double>();
	
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
	
	public static void start(LinkedList<LinkedList<String>>all_transactions, double min_support, double min_confidence)
	{
		//GETTING ALL ITEMS
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
//		System.out.println();
//		for(int i=0;i<item.size();i++)
//		{
//			if((Double.parseDouble(sup.elementAt(i)+"")) < min_support)
//			{
//				item.removeElementAt(i);
//				sup.removeElementAt(i);
//				i--;
//			}
//		}
//		System.out.println("SCAN "+(count++));
//		System.out.println("ItemSet\tSupport");
//		for(int i=0;i<item.size();i++)
//		{
//			System.out.println(item.elementAt(i)+"\t\t"+sup.elementAt(i));
//		}
//		System.out.println();
//		while(flag)
//		{
//				System.out.println("SCAN "+(count++));
//			getCandidate();
//		}
//		
//		//ASSOCIATION RULES CALCULATION
//		Vector rule=new Vector();
//		Vector conf=new Vector();
//		System.out.println();
//		for(int i=0;i<item.size();i++)
//		{
//			String a=item.elementAt(i)+"";
//			String b[]=a.split(",");
//			for(int j=1;j<b.length;j++)
//			{
//				for(int k=0;k<b.length;k++)
//				{
//					String l="",r="";
//					for(int s=0;s<j;s++)
//					{
//						l=l+" ^ "+b[(s+k)%b.length];
//					}
//					for(int p=j;p<b.length;p++)
//					{
//						r=r+" ^ "+b[(p+k)%b.length];
//					}
//					String x=l.substring(3,l.length())+" => "+r.substring(3,r.length());
//					rule.add(x);
//					double y=getConfidence(x,i);
//					conf.add(y);
//				}
//			}
//		}
//		System.out.println("RULES");
//		for(int i=0;i<rule.size();i++)
//		{
//			double d=Double.parseDouble(conf.elementAt(i)+"");
//			System.out.println(rule.elementAt(i)+"\t"+d+"\t"+(d*100));
//			if((d*100)<min_confidence)
//			{
//				rule.removeElementAt(i);
//				conf.removeElementAt(i);
//				i--;
//			}
//		}
//		System.out.println();
//		System.out.println("STRONG RULES");
//		for(int i=0;i<rule.size();i++)
//		{
//			double d=Double.parseDouble(conf.elementAt(i)+"");
//			System.out.println(rule.elementAt(i)+"\t"+d+"\t"+(d*100));	
//		}
//	}
//	
//	static boolean has(Vector newitem,String x)
//	{
//		if(newitem.contains(x))
//			return true;
//		if(newitem.isEmpty())
//			return false;
//		
//		boolean f=true;
//		boolean fin=false;
//		for(int i=0;i<newitem.size();i++)
//		{
//			String y=newitem.elementAt(i)+"";
//			String x1[]=x.split(",");
//			f=true;
//			if(y.length()!= x.length())
//				f=false;
//			for(int j=0;j<x1.length;j++)
//			{
//				
//				if(y.indexOf(x1[j]) < 0)
//				{
//					f=false;
//					break;
//				}
//			}
//			fin=fin || f;
//		}
//		return fin;
//	}
//	
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