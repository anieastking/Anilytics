import java.lang.*;
import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileWriter;


public class Dataset {

  public int initi()
  {
      int p;
      Scanner s=new Scanner(System.in);
        System.out.println("How many items??");
        p=s.nextInt();
        
        
        
        System.out.println("how Many transactions??");
        int q=s.nextInt();
        try
        {
        
        
        Random r=new Random();
        
        
        
        File outf=new File("Z:/Partition/dataset.txt");
        FileWriter outs=null;
        
        outs=new FileWriter(outf);
       
        char ch=' ';
        String ff="";
        
        for(int f=0;f<q;f++)
        {
            int g=(Math.abs(r.nextInt()))%p;
            if(g==0)
            g=g+1;
           // System.out.println(g);\
            ff=Integer.toString(f);
            //outs.write(ff+" ");
             boolean []mark=new boolean[p];
             for(int w=0;w<p;w++)
             {
                 mark[w]=false;
             }
            int c=0;
            while(c<g)
            {
                int ss=(Math.abs(r.nextInt()))%p;
                if(ss==0)
                    ss=1;
                if(mark[ss]==false)
                {
                ff=Integer.toString(ss);
                outs.write(ss+"");
                mark[ss]=true;
                c++;
                 }
                else
                {
                    continue;
                }
            }
            outs.write("\r\n");
        }
        outs.flush();
        
        }
        catch(Exception e)
        {
        }
        return q;
}
  
  
  public void applyApriori(int part,BufferedReader br,int n,Scanner fre,int minsup,int partno)
  {
      try
      {
          
      int max=0;
      int min;
      String trans[]=new String[part];
      
      int maxLen=0;
      
      for(int i=0;i<part;i++)
		{
                        
			trans[i]=br.readLine();
                      //  System.out.println(trans[i]);
                       // System.out.println(trans[i]);
			for(int j=0;j<trans[i].length();j++)
			{
				if(Integer.parseInt(""+trans[i].charAt(j))>max)
					max=Integer.parseInt(""+trans[i].charAt(j));
			}
		}
		min=1;
              //  fre.skip("");
                
               // System.out.append("beauty");
               // befprune p=new befprune();
		String str="";
		for(int i=1;i<=max;i++)
			str+=""+i;
		ArrayList al;
		max=0;
		ArrayList<String> allItems=new ArrayList<String>();
		for(int i=1;i<=str.length();i++)
		{
			al=apriorigen(str.substring(0,i));
			for(int j=0;j<al.size();j++)
			{
				allItems.add(""+al.get(j));
				if(new String(""+al.get(j)).length()>max)
					max=new String(""+al.get(j)).length();    ///get length of max items in transaction
			}
		}
               /////before pruning 
             //   System.out.println("Before Pruning");
                
                  
		ArrayList<String> arr[]=new ArrayList[max];
		for(int i=0;i<max;i++)
			arr[i]=new ArrayList<String>();
		for(int j=0;j<allItems.size();j++)
			arr[( allItems.get(j).length())-1].add(allItems.get(j));
		int count[]=new int[allItems.size()],l=0;
		int flag=0;
                
              
		for(int i=0;i<max;i++)
		{
			for(int j=0;j<arr[i].size();j++)
			{
				for(int k=0;k<part;k++)
				{
					for(int m=0;m<(arr[i].get(j).length());m++)
					{
						if(trans[k].indexOf(arr[i].get(j).charAt(m))==-1)
							flag=1;
					}
					if(flag==0)
						count[l]+=1;
					flag=0;
				}
				l++;
			}
		}
		int k=0;
		for(int i=0;i<max;i++)
		{
			for(int j=0;j<arr[i].size();j++)
			{
				if(count[k]>=min)
				{
					if(maxLen<arr[i].get(j).length())
						maxLen=arr[i].get(j).length();
				}
				k++;
			}
		}
		k=0;
                
          
           
                File gg=new File("Z:/Partition/output"+partno+".txt");
                FileWriter writer=new FileWriter(gg);
		System.out.println("Frequent Itemsets are ");
		for(int i=0;i<max;i++)
		{
			for(int j=0;j<arr[i].size();j++)
			{
				if(count[k]>=min)
				{
						System.out.println(arr[i].get(j)+" "+count[k]);
                                                writer.write(arr[i].get(j)+" "+count[k]);
                                                writer.write("\r\n");
				}
				k++;
			}
		}
                writer.flush();
 
                
      }
      catch(Exception e)
      {
          
      }
  }
    
  
  
  
  
  	ArrayList<String> apriorigen(String str)
	{
		String temp="",match="";
		int flag=0;
		ArrayList<String> result=new ArrayList<String>();
		if(str.length()==1)
		{
			result.add(str);
			return result;
		}
		else
		{
			char ch=str.charAt(0);
			String rem=str.substring(1);
			ArrayList<String> a=apriorigen(rem);   //recursive generation to make all subsets from previous subsets ///
			for(String p:a)
			{
				result.add(p);    ////add to set
				
                  ///////////////////////////////////////////// 
                                ArrayList addn=prune(ch,p);    ///remove all undesirables///
				for(int i=0;i<addn.size();i++)
				{
					match=(String)addn.get(i);
					flag=0;
					for(int j=i+1;j<addn.size();j++)
					{                                                             
						temp=(String)addn.get(j);
						for(int k=0;k<temp.length();k++)
						{
							for(int l=0;l<match.length();l++)
							{
								if(temp.charAt(k)==match.charAt(l))    //matching a1 b1 a2 b2....//
									flag++;
							}
						}
						if(flag>=match.length())
							addn.remove(j);
					}
				}
				result.addAll(addn);
			}
                       
                         
                       
			return(result);
		}
	}
        
        
        
        
        
        ArrayList<String> prune(char c,String str)
	{                                                            //////pruning step 
		String temp="",match="";
		int flag=0;
		ArrayList<String> res=new ArrayList<String>();
		for(int i=0;i<=str.length();i++)
		{
			res.add(str.substring(0,i)+c+str.substring(i));
		}
		for(int i=0;i<res.size();i++)
		{
			match=(String)res.get(i);
			flag=0;
			for(int j=i+1;j<res.size();j++)
			{
				temp=(String)res.get(j);
				for(int k=0;k<temp.length();k++)
				{
					for(int l=0;l<match.length();l++)
					{
						if(temp.charAt(k)==match.charAt(l))
							flag++;
					}
				}
				if(flag>=match.length())
					res.remove(j);           ///removed when subset absent////
			}
		}
		return(res);
	}
}
