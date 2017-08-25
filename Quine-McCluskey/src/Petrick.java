
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;


public class Petrick {
	
	LinkedList<String> minimumTerms = new LinkedList<String>() ;
	LinkedList<String> minimumCost = new LinkedList<String>() ;
	LinkedList<String> EssintialImplicants = new LinkedList<String>() ;
	LinkedList<LinkedList> mintermsLitrals = new LinkedList<LinkedList>();
	LinkedList<LinkedList<String>> multipliedLitrals = new LinkedList<LinkedList<String>>();	
	LinkedList<String> finalFunctions = new LinkedList<String>();
	int variableNum;
//----------------------------------------------------------------------------------------------------------------
	
public void coverdByImplicant(LinkedList<LinkedList> result ,String[] minterm){
		
		int[] minterms = new int[minterm.length] ;
		minterms = implicants(minterm) ;
		variableNum = numberOfVariables(biggestMinterm(minterms)); 
		for (int i = 0 ; i < minterms.length ; i ++)
		{
			this.minimumTerms.add(i, this.getCoverdMinterms(result , minterms[i]));
		}
			this.getEssintialImplicants();
			listSplit();
			this.mintermsLitrals = this.multiplyTerms(this.mintermsLitrals) ;
			this.finalFunctions = this.mintermsLitrals.getFirst() ;
			System.out.println("hey");
			this.cleanRedandent();
			this.multiplySplit();
			this.sort();
			this.finalFunctions();
			System.out.println("hey");
			//this.chooseMinimumcost();

	}
//----------------------------------------------------------------------------------------------------------------
	
public int[] implicants(String[] inputs){
		int[] minterms = new int[inputs.length] ;
		for (int i = 0 ; i < inputs.length ; i ++)
		{
			minterms[i] = Integer.parseInt(inputs[i]) ;
		}
		return minterms ;
	}
//----------------------------------------------------------------------------------------------------------------

	public String getCoverdMinterms(LinkedList<LinkedList> result , int implicant){
		String covers = new String() ;
		for (int j = 0 ; j < result.size() ; j ++)
		{
			int flag = 0 ;
			for (int k = 0 ; k < result.get(j).size()  ; k++)
			{
				int groupNumber = 0 ;
				if (k != 0 )
				{
				 groupNumber = (int)result.get(j).get(0) + (int)result.get(j).get(k) ;
				}
				else
				{
					groupNumber = (int)result.get(j).get(0) ;
					if (groupNumber == implicant)
					{
						flag = 1 ;
					}
				}
				for (int h = k +1 ; h < result.get(j).size() ; h ++)
				{
					int coveredTerm = groupNumber + (int)result.get(j).get(h) ;
					if (coveredTerm == implicant)
					{
						flag = 1 ;
					}
					
				}
			}
			if (flag == 1 )
			{
				 covers += j ;
				 covers += " " ;
			}
		}
		return covers ;
	}
//----------------------------------------------------------------------------------------------------------------
	
public LinkedList<LinkedList> multiplyTerms(LinkedList<LinkedList> terms){
		int count = terms.size() - 1 ;
		for (int i = 0 ; i < count ; i ++)
		{
			
			LinkedList<String> temp = new LinkedList<String>() ;
			for (int j = 0 ; j < terms.get(1).size() ; j ++ )
			{
				
				for (int k = 0 ; k < terms.get(0).size() ; k ++)
				{
					String x = new String() ;
					x += terms.get(0).get(k) ;
					x += " " ;
					x += terms.get(1).get(j) ;
					x = new LinkedHashSet<String>(Arrays.asList(x.split(" "))).toString().replaceAll("(^\\[|\\]$)", "").replace(", ", " ");
					temp.add(x) ;
				}
			}
			
			for (int y = 0 ; y < temp.size() ; y ++)
			{
				for (int k = y + 1 ; k < temp.size() ; k ++)
				{
					if (temp.get(k).contains(temp.get(y)))
					{
						temp.remove(k) ;
						k -- ;
					}
				}
			}
			
			terms.removeFirst() ;
			terms.removeFirst() ;
			terms.addFirst(temp);
			
		}
		LinkedList<String> temp = new LinkedList<String>() ;
		LinkedList<String> reducedTemp = new LinkedList<String>() ;
		temp = terms.get(0) ;
		int min = this.gitMinimum(temp) ;
		for (int k = 0 ; k < temp.size() ; k ++)
		{
			if (1 + temp.get(k).length() - temp.get(k).replace(" ", "").length()  == min)
			{
				reducedTemp.add(temp.get(k)) ;
			}
		}
		terms.set(0, reducedTemp) ;
		return terms ;
	}
//----------------------------------------------------------------------------------------------------------------
	
public void cleanRedandent(){
		for (int i = 0 ; i < this.finalFunctions.size() ; i ++)
		{
			for (int k = i ; k < this.finalFunctions.size() - 1 ; k++)
			{
				String x = this.finalFunctions.get(i) ;
				String y = this.finalFunctions.get(k + 1) ;
				if (x.equals(y))
				{
					this.finalFunctions.remove(k + 1) ;
					k -- ;
				}
			}
		}	
	}
//----------------------------------------------------------------------------------------------------------------	
	
public void finalFunctions(){
		for (int i = 0 ; i < this.multipliedLitrals.size() ; i ++)
		{
			for (int k = i ; k < this.multipliedLitrals.size() - 1 ; k++)
			{
				if (this.multipliedLitrals.get(k + 1).equals(this.multipliedLitrals.get(i)))
				{
					this.multipliedLitrals.remove(k + 1) ;
					k -- ;
				}
			}
		}	
	}
//----------------------------------------------------------------------------------------------------------------
	
public void listSplit(){
		for (int i = 0 ; i < this.minimumTerms.size() ; i ++)
		{
			LinkedList<String> temp = new LinkedList<String>() ;
			for (int j = 0 ; j < this.minimumTerms.get(i).length() ; j++)
			{
				String x = new String() ;
				while (this.minimumTerms.get(i).charAt(j)  != ' ')
				{
					x += this.minimumTerms.get(i).charAt(j) ;
					j ++ ;
				}
				
				temp.add(x) ;
			}
			this.mintermsLitrals.add(temp) ;
		}
	}
//----------------------------------------------------------------------------------------------------------------
	
public void multiplySplit(){
		for (int i = 0 ; i < this.finalFunctions.size() ; i ++)
		{
			LinkedList<String> temp = new LinkedList<String>() ;
			for (int j = 0 ; j < this.finalFunctions.get(i).length() ; j++)
			{
				String x = new String() ;
				while (this.finalFunctions.get(i).charAt(j)  != ' ')
				{
					x += this.finalFunctions.get(i).charAt(j) ;
					j ++ ;
					if (j == this.finalFunctions.get(i).length())
					{
						break ;
					}
				}
				
				temp.add(x) ;
			}
			this.multipliedLitrals.add(temp) ;
		}
	}
//----------------------------------------------------------------------------------------------------------------
	
public void sort(){
		for (int i = 0 ; i < this.multipliedLitrals.size() ; i ++)
		{
			int[] sorted = new int[this.multipliedLitrals.get(i).size()] ;
			for (int j = 0 ; j < this.multipliedLitrals.get(i).size() ; j ++)
			{
				sorted[j] =  Integer.parseInt(this.multipliedLitrals.get(i).get(j)) ;
			}
			Arrays.sort(sorted);
			String[] sorted1 = new String[this.multipliedLitrals.get(i).size()];
			for(int n = 0; n < sorted1.length; n++)
			{
				sorted1[n] = Integer.toString(sorted[n]);
			}
			for (int k = 0 ; k < this.multipliedLitrals.get(i).size() ; k ++)
			{
				this.multipliedLitrals.get(i).set(k, sorted1[k]) ;
			}
		}
	}
//----------------------------------------------------------------------------------------------------------------	
	
public int gitMinimum(LinkedList<String> temp){
		
		int min = 1 + temp.get(0).length() - temp.get(0).replace(" ", "").length() ;
		for (int i = 0 ; i < temp.size() ; i ++)
		{
			int count = temp.get(i).length() - temp.get(i).replace(" ", "").length();
			count ++ ;
			if (count < min )
			{
				min = count ;
			}
		}
		return min ;
	}
//----------------------------------------------------------------------------------------------------------------

	public int getLength(String term){
		
		return  term.length() - term.replace(" ", "").length() + 1 ;
	}
//----------------------------------------------------------------------------------------------------------------
	
public String getAlphabet(LinkedList result)
	{
		Integer x = (Integer) result.getFirst();
		String binaryString = Integer.toBinaryString(x);
	    StringBuffer sb = new StringBuffer();
	    for(int i  = 0; i < binaryString.length(); i++)
	    {
	    	sb.append(binaryString.charAt(i));
	    }
	    int numZeros = variableNum - binaryString.length();
	    while(numZeros-- > 0) { 
	    	sb.insert(0, "0");
	    }
	    binaryString = sb.toString();
	    char[] chars = binaryString.toCharArray();
	    for(int i = 1; i < result.size(); i++)
	    {
	    	int base = (int)( Math.log((int) result.get(i)) / Math.log(2));
	    	chars[chars.length - base - 1] = '2';
	    }
	    StringBuffer PI = new StringBuffer();
	    for(int j = 0; j < chars.length; j++)
	    {
	    	if(chars[j] == '2')
	    	{
	    		continue;
	    	}
	    	else if(chars[j] == '1')
	    	{
	    		char index = (char) j;
	    		index = (char) (j+65);
	    		PI.append(index);
	    	}
	    	else if(chars[j] == '0')
	    	{
	    		char index = (char)j;
	    		index = (char) (j+65);
	    		PI.append(index);
	    		PI.append("'");
	    	}
	    }
	    return PI.toString();
	}
//----------------------------------------------------------------------------------------------------------------
	
public int biggestMinterm(int[] minterms)
	{
		int x = 0;
		for(int i = 0; i < minterms.length; i++)
		{
			if(x <= minterms[i])
			{
				x = minterms[i];
			}
		}
		return x;
	}
//----------------------------------------------------------------------------------------------------------------

	public int numberOfVariables(int biggestMinterms)
	{
		
		int counter = 0; 
		while(biggestMinterms > 0)
		{
			counter++;
			biggestMinterms  = biggestMinterms >> 1;
		}
		return counter;
	}
//----------------------------------------------------------------------------------------------------------------
	
public LinkedList<String> finalResult(LinkedList<LinkedList> finalResult)
	{
		LinkedList<String> returned = new LinkedList<String>();
		for(int i = 0; i < mintermsLitrals.get(0).size(); i++)
		{
			String[] splited = mintermsLitrals.get(0).get(i).toString().split(" ");
			StringBuffer expression = new StringBuffer();
			for(int j = 0; j < splited.length; j++)
			{
				int x =   Integer.parseInt(splited[j]);
				String s = getAlphabet(finalResult.get(x));
				expression.append(s);
				if(j != splited.length -1)
				{
					expression.append(" + ");
				}
			}
			returned.add(expression.toString());
		}
		return returned;
	}
public void getEssintialImplicants(){
	for (int i = 0 ; i < this.minimumTerms.size() ; i ++)
	{
		if (this.getLength(this.minimumTerms.get(i)) == 2)
		{
			this.EssintialImplicants.add(this.minimumTerms.get(i)) ;
		}
	}
}

}
