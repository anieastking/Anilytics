
import java.lang.*;
import java.io.*;
import java.util.*;
class DynamicCounting
{
	public static void main(String args[])throws Exception
	{
		int n,min;
		int max=0;
		int maxLen=0;
                DatasetDyn p=new DatasetDyn();    ///generate random dataset
                Scanner fre=new Scanner(System.in);
                n=p.initi();
              // System.out.println("gefg"+n+'\n');
                DynamicCounting ap=new DynamicCounting();              //initiate class
                File inf=new File("Z:/DynamicCounting/dataset.txt");
                FileReader dd=new FileReader(inf);
                
                
                
		BufferedReader br=new BufferedReader(dd);
		
		//System.out.print("Enter Number Of Transactions"
		//n=Integer.parseInt(br.readLine())
		String trans[]=new String[n];
                
                 for(int i=0;i<n;i++)
             {
                 trans[i]=br.readLine();         ///read into trans array of strings
                 
                 for(int j=0;j<trans[i].length();j++)
			{
				if(Integer.parseInt(""+trans[i].charAt(j))>max)
                                    max=Integer.parseInt(""+trans[i].charAt(j));
			}
             } 
                
                //System.out.println("yahan tak sahi hai");
                ////////////////BITMASK\\\\\\\\\\\\\\\\\\\\
             int cc=max;
             int []yoyo=new int[max+1];
             int []mark=new int[max+1];
            // char yoyo[max+1];
             for(int i=0;i<=max;i++)
             {
                 yoyo[i]=i+1;
                 mark[i]=0;
             }
             
             int end=(int)Math.pow(2,max);
             
            // System.out.println(end);
             String[] repos=new String[end+1];
             int siz=0;
             for(int i=1;i<end;i++)
             {
                 mark=bringme(i,max);
                
                 String up="",down="";
                 
                 for(int j=0;j<max;j++)
                 {
                     if(mark[j]==1)
                         down+=yoyo[j];
                   
                     
                    
                 }
             char[] chars = down.toCharArray();
             Arrays.sort(chars);
             String ddown = new String(chars);
             repos[siz++]=ddown;
             
                 
             }
               
             
             for(int y=0;y<siz;y++)
             {
                 System.out.println(repos[y]);
             }
             
            // System.exit(1);
             int []touched=new int[end+1];
             
             int []freq=new int[end+1];
             for(int i=0;i<end+1;i++)
             {
                
               touched[i]=0;freq[i]=0;
             }
          ////////////////////BITMASK END\\\\\\\\\\\\\\\\\\\\\\\\\\\
             System.out.print("Enter Minimum Support ");
	     min=fre.nextInt();
           
               
             
            System.out.println("Enter Checkpoint length from start");
            int checkpoint=fre.nextInt();
            
		for(int i=0;i<n;i++)
		{
			
                     char[] chars = trans[i].toCharArray();
                      Arrays.sort(chars);
                      String sortedTrans = new String(chars);
                      
                      for(int j=0;j<end;j++)
                      {
                          if(repos[j]==null)
                              continue;
                           if(allInclude(sortedTrans,repos[j]))
                           {
                               freq[j]++;
                               touched[j]=1;
                           }
                      }
			
                      if(i%checkpoint==0)
                      {
                          //print obtained
                          System.out.println(i+" th"+"checkpoint Results");
                          for(int k=0;k<end;k++)
                          {
                              if(freq[k]>=min && touched[k]!=-1)
                              {
                                  System.out.println(repos[k]+"    "+freq[k]);
                                  touched[k]=-1;
                             }
                          }
                      }
                        
		}
		
             
             
             System.exit(1);
             
             
                
		String str="";
		for(int i=1;i<=max;i++)
		   str+=i;
                
                
		ArrayList al;
		max=0;
		ArrayList<String> allItems=new ArrayList<String>();
		for(int i=1;i<=str.length();i++)
		{
			al=ap.apriorigen(str.substring(0,i));
			for(int j=0;j<al.size();j++)
			{
				allItems.add(""+al.get(j));
				if(new String(""+al.get(j)).length()>max)
					max=new String(""+al.get(j)).length();    ///get length of max items in transaction
			}
		}
               /////before pruning 
               //System.out.println("Before Pruning");
                
                
                
                  
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
				for(int k=0;k<n;k++)
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
          
            
                File gg=new File("Z:/AprioriFinal/output.txt");
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
                
                
                
                
                
                
                
                
                
             File gx=new File("Z:/AprioriFinal/AssociationRules.txt");
                FileWriter writx=new FileWriter(gx);
             for(int i=0;i<end;i++)
             {
                 for(int j=i+1;j<end;j++)
                 {
                     String a=repos[i];
                     String b=repos[j];
                     try
                     {
                     b=b.concat(a);
                     b=removeDuplicate(b);
                     }
                     catch(Exception e)
                     {
                         
                     }
                     
                     int f=findpos(a,arr,count);
                     int ggg=findpos(b,arr,count);
                     
                     if(f!=-1 && ggg!=-1)
                     {
                         
                         
                     
                    double d=-1;
                     if(f!=0 && ggg!=-1)
                     {
                     d =(double)ggg/f;
                     
                      System.out.println(a+"--->"+b+"       "+d);
                      writx.write(a+"--->"+b+"       "+d);
                      writx.write("\r\n");
                     }
                     }
                 }
             }
             writx.flush();
                
	}
        
        
           public static String removeDuplicate(String s)
     {  
    StringBuilder sb = new StringBuilder();
    Set<Character> seen = new HashSet<Character>();
try
{
    for(int i = 0; i < s.length(); ++i)
    {
        char c = s.charAt(i);
        if(!seen.contains(c)) {
            seen.add(c);
            sb.append(c);
        }
    }
}
catch(Exception e)
{
}
    return sb.toString();

    
      }
           
           public static int[] bringme(int val,int max)
        {
            int []mark=new int[max+1];
            int f=max-1;
            for(int i=0;i<max;i++)
            {
                mark[i]=0;
            }
            while(val!=0)
            {
                mark[f--]=val%2;
                val=val/2;
                
            }
            return mark;
        }
           
           
      
        public static int findpos(String a,ArrayList<String> [] arr,int []count)
        {
            int h=0;
            
            
            
            for(int i=0;i<arr.length;i++)
                {
                    ArrayList<String> bara=new ArrayList<String>();
                    
                    bara=arr[i];
                    for(String f:bara)
                    {
                        try
                        {
                        if(f==a)
                        return count[h];
                        else if(f.contains(a)==true)
                            return count[h];
                        
                        h++;
                        }
                        catch(Exception e)
                        {
                            
                        }
                    }
             
                 }
            return -1;
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
        
        
        public static boolean allInclude(String a,String b)
        {
            char [] bchar=b.toCharArray();
            char [] achar=a.toCharArray();
            
            boolean[] alphamark=new boolean[10];
            
            for(int i=0;i<10;i++)
            {
                alphamark[i]=false;
            }
            for(int i=0;i<achar.length;i++)
            {
                alphamark[achar[i]-'0']=true;
            }
            
            for(int i=0;i<bchar.length;i++)
            {
                if(alphamark[bchar[i]-'0']==false)
                    return false;
            }
            return true;
        }
}
