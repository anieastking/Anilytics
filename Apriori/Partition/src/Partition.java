import java.lang.*;
import java.io.*;
import java.util.*;
class Partition
{
	public static void main(String args[])throws Exception
	{
		int n,min;
		int max=0;
		int maxLen=0;
                Dataset p=new Dataset();    ///generate random dataset
                Scanner fre=new Scanner(System.in);
                n=p.initi();
              // System.out.println("gefg"+n+'\n');
                Partition ap=new Partition();              //initiate class
                File inf=new File("Z:/Partition/dataset.txt");
                FileReader dd=new FileReader(inf);
                
                System.out.println("How many partitions do you want  ?");
                int part=fre.nextInt();
                
               
		BufferedReader br=new BufferedReader(dd);
		//BufferedReader as=new BufferedReader(dd);
		//System.out.print("Enter Number Of Transactions ");
		//n=Integer.parseInt(br.readLine());
		String transx[]=new String[n];
                System.out.print("Enter Minimum Support ");
		min=fre.nextInt();
                
		for(int i=0;i<part;i++)
		{
                        p.applyApriori(part,br,n,fre,min,i);
                       // transx[i]=as.readLine();
                      
			
		}
                br.close();
                
                File infx=new File("Z:/Partition/dataset.txt");
                FileReader ddx=new FileReader(infx);
                BufferedReader as=new BufferedReader(ddx);
                System.out.println("fge");
		for(int i=0;i<n;i++)
		{
                    transx[i]=as.readLine();
			  System.out.println(transx[i]);
                       // System.out.println(trans[i]);
			for(int j=0;j<transx[i].length();j++)
			{
				if(Integer.parseInt(""+transx[i].charAt(j))>max)
					max=Integer.parseInt(""+transx[i].charAt(j));
			}
		}
             
		String str="";
		for(int i=1;i<=max;i++)
			str+=""+i;
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
				for(int k=0;k<n;k++)
				{
					for(int m=0;m<(arr[i].get(j).length());m++)
					{
						if(transx[k].indexOf(arr[i].get(j).charAt(m))==-1)
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
                
             
                
                
                
           
                File gg=new File("Z:/Partition/Joinedoutput.txt");
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
