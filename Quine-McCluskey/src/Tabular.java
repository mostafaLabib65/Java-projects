

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Tabular extends Petrick{
	HashMap<Integer, LinkedList<LinkedList>> list = new HashMap<Integer, LinkedList<LinkedList>>();
	LinkedList<LinkedList> result = new LinkedList<LinkedList>();
	HashMap<Integer, LinkedList<LinkedList>> prevTempResult = new HashMap<Integer, LinkedList<LinkedList>>();
	LinkedList<LinkedList> checked = new LinkedList<LinkedList>();
//------------------------------------------------------------------------------------------------------------------------------------------
	public int numberOfOnes(int minterm)
	{
		int counter = 0;
		while(minterm != 0)
		{
			if(minterm % 2 == 1)
			{
				counter++;
			}
			minterm = minterm/2;
		}
		return counter;
	}
//------------------------------------------------------------------------------------------------------------------------------------------	

	public void addToList(int numberOfOnes,int minterm)
	{
		LinkedList mintermData = new LinkedList();
		mintermData.add(minterm);
		if(list.containsKey(numberOfOnes))
		{
			list.get(numberOfOnes).add(mintermData);
		}
		else
		{
			LinkedList<LinkedList> data = new LinkedList<LinkedList>();
			data.add(mintermData);
			list.put(numberOfOnes, data);
		}
	}
//------------------------------------------------------------------------------------------------------------------------------------------
	
	public HashMap<Integer, LinkedList<LinkedList>> generate(HashMap<Integer, LinkedList<LinkedList>> valueList)
	{
		int checker = 0;
		int flag = 0;
		int hashCounter = 0;
		HashMap<Integer, LinkedList<LinkedList>> tempResult = new HashMap<Integer, LinkedList<LinkedList>>();
		for(int i = 0; i < valueList.size(); hashCounter++)
		{	
			if(!valueList.containsKey(hashCounter))
				continue;
			if(i != valueList.size() -1){
				LinkedList<LinkedList> data = new LinkedList<LinkedList>();
				for(int j = 0; j < valueList.get(hashCounter).size(); j++)
				{
					int hashCounter2 = hashCounter+1;
					for(int k = i ; k < valueList.size()-1; hashCounter2++)
					{
						if(!valueList.containsKey(hashCounter2))
							continue;
						for(int l = 0; l< valueList.get(hashCounter2).size(); l++)
						{
							LinkedList currentMinterm = new LinkedList ();
							LinkedList comparedMinterm = new LinkedList ();
							if(valueList.get(hashCounter).get(j).size() > 1)
							{
								for(int counter = 1; counter <  valueList.get(hashCounter).get(j).size(); counter++)
								{
									currentMinterm.add(valueList.get(hashCounter).get(j).get(counter));
								}
							}
							
							else
								currentMinterm.add(valueList.get(hashCounter).get(j).getFirst());
							
							if(valueList.get(hashCounter2).get(l).size() > 1)
							{
								for(int counter = 1; counter <  valueList.get(hashCounter2).get(l).size(); counter++)
								{
									comparedMinterm.add(valueList.get(hashCounter2).get(l).get(counter));
								}
							}
							
							else
								comparedMinterm.add(valueList.get(hashCounter2).get(l).getFirst());
							
							boolean check = listCompare(comparedMinterm, currentMinterm);
							if(check || valueList.get(hashCounter).get(j).size() == 1)
							{
								int deffrence = (int) valueList.get(hashCounter2).get(l).getFirst() - (int) valueList.get(hashCounter).get(j).getFirst();
								if(numberOfOnes(deffrence) == 1)
								{
									LinkedList temp = new LinkedList();
									for(int counter = 0; counter < valueList.get(hashCounter).get(j).size(); counter++)
									{
										temp.add(valueList.get(hashCounter).get(j).get(counter));
									}
									temp.add(deffrence);
									sort(temp);
									boolean ifExcisted = false;
									checked.add(valueList.get(hashCounter2).get(l));
									for(int v = 0; v < data.size(); v++)
									{
										if(listCompare(temp, data.get(v)))
										{
											ifExcisted = true;
											flag++;
											break;
										}
									}
									if(!ifExcisted)
									{
										data.add(temp);
										checker++;
									}
										
								}
							}
						}
						k++;
					}
					if(checker == 0 && flag == 0){
						if(!checked.contains(valueList.get(hashCounter).get(j))){
							result.add(valueList.get(hashCounter).get(j));
							checked.add(valueList.get(hashCounter).get(j));
						}
					}
					else{
						checker = 0;
						flag = 0;
					}
				}
				if(data.size() != 0)
				{
					prevTempResult.clear();
					tempResult.put(hashCounter, data);
					prevTempResult.put(hashCounter, data);
				}
			}
			else
			{
				for(int n = 0; n < valueList.get(hashCounter).size(); n++)
				{
					if(!checked.contains(valueList.get(hashCounter).get(n)))
					{
						result.add(valueList.get(hashCounter).get(n));
					}
				}
			}
			i++;
		}
		if(tempResult.size() == 0)
		{
			
			return prevTempResult;
		}
		else
		{
		 	return	generate(tempResult);
		}
	}
//------------------------------------------------------------------------------------------------------------------------------------------
	
	public void merge(HashMap<Integer, LinkedList<LinkedList>> tempResult)
	{
		int l = 0;
		for(int i = 0; i < tempResult.size(); l++)
		{
			if(!tempResult.containsKey(l))
				continue;
			for(int j = 0; j < tempResult.get(l).size() ; j++)
			{
				if(!result.contains(tempResult.get(l).get(j)))
					result.add(tempResult.get(l).get(j));
			}
			i++;
		}
	}
//------------------------------------------------------------------------------------------------------------------------------------------
	
	public boolean listCompare(LinkedList a, LinkedList b)
	{
		if(a.size() != b.size())
			return false;
		for(int i = 0; i < a.size(); i++)
		{
			if(!(a.get(i).equals(b.get(i))))
				return false;
		}
		return true;
	}
//------------------------------------------------------------------------------------------------------------------------------------------

	public void sort(LinkedList temp)
	{
		int x;
		for(int i =1; i < temp.size()-1; i++)
		{
			x = (int) temp.get(i);
			int index = 0;
			for(int j = i+1; j < temp.size(); j++)
			{
				
				if((int) temp.get(j) <= x)
				{
					x = (int) temp.get(j);
					index = j;
				}
			}
			if(index != 0)
			{
				temp.remove(index);	
				temp.add(i, x);
			}
		}
	}
//------------------------------------------------------------------------------------------------------------------------------------------
	
}

	



