import java.lang.*;
import java.util.*;
import java.io.*;

public class FPTree {

    
   
    public static void main(String[] args) {
        
        ////I shall master R programming
        Scanner s=new Scanner(System.in);
        System.out.println("Enter no of transactions");
        int p=s.nextInt();
        
       
        String[] data=new String[p+1];
        
        int arr[]=new int[10];
        
        for(int i=0;i<=9;i++)
        {
            arr[i]=0;
        }
       // s.delimiter();
        for(int i=1;i<=p;i++)
        {
            String gh;
            gh=s.nextLine();
            data[i]=gh;
            char[] buff=new char[10];
            buff=gh.toCharArray();
            
            
            for(int j=0;j<buff.length;j++)
            {
                int fg=(int)buff[j]-48;
                arr[fg]++;
            }
        }  
            System.out.println("Enter Min_support");
            int minsup=s.nextInt();
            for(int j=1;j<=9;j++)
            {
                if(arr[j]<minsup)
                {
                    arr[j]=-1;
                }
            }
            
        
            for(int j=1;j<=p;j++)
            {
                char[] buff=new char[10];
                buff=data[j].toCharArray();
                String fin="";
                for(int h=0;h<buff.length;h++)
                {
                    int fg=((int)buff[h])-48;
                    if(arr[fg]!=-1)
                    {
                        fin+=buff[h];
                    }
                   
                }
                data[j]=fin;
                data[j]=sort( data[j], arr);
            }
                
           ///////////BAsic Stuff done////////////////////////////////////
            
            
            
            /////FP TREE CONSTRUCTION
           trie tr=new trie();
           
           for(int j=1;j<=p;j++)
           {
               tr.insert(data[j]);
           }
            tr.show(minsup);
            
            
            ////FP TREE Construction Done\\\\\\
            
            
            ////Now FP TREE Itemset Generation\\\\
            
            
            
           // trie x=new trie();
            
            
            int max=5;
            int [] mark=new int[max+1];
            int []yoyo=new int[max+1];
           for(int i=0;i<=max;i++)
             {
                 yoyo[i]=i+1;
                 mark[i]=0;
             }
           
           int end=(int)Math.pow(2,max);
           for(int i=1;i<end;i++)
             {
                 mark=bringme(i,max);
                
                 String up="",down="";
                 
                 for(int j=0;j<max;j++)
                 {
                     
                     if(mark[j]==1)
                         down+=yoyo[j];
                   
                  }
                 
                 makeCondFP(tr,down,data,p,minsup);
                 
             }
    }
    
    public static void makeCondFP(trie tr,String down,String[]data,int size,int minsup)
    {
        trie cond=new trie();
        int count=0;
        for(int i=1;i<=size;i++)
        {
            if(checkContain(down,data[i])==true)
            {
                count++;
            }
        }
        if(count>=minsup)
        {
            for(int i=1;i<=size;i++)
            {
                
            if(checkContain(down,data[i])==true)
            {
                cond.insert(data[i]);
            }
            
         }
        }
        System.out.println("CondFPTree of  "+down);
        cond.show(minsup);
    }
    
     public static boolean checkContain(String down,String data)
     {
         char[]buff=new char[100];
         char []trans=new char[100];
         trans=data.toCharArray();
         buff=down.toCharArray();
         
         for(int j=0;j<buff.length;j++)
         {
                 
             if(ghotcheck(trans,buff[j])==false)
             {
                 return false;
             }
                 
         }
         return true;
     }
     
     public static boolean ghotcheck(char[] down,char buff)
     {
         for(int i=0;i<down.length;i++)
         {
             if(down[i]==buff)
                 return true;
         }
         return false;
     }
    
    public static  String sort(String buff,int []arr)
    {
        char[] h=new char[10];
        h=buff.toCharArray();
       
        for(int i=0;i<h.length;i++)
        {
            for(int j=i;j<h.length-1;j++)
            {
                if(arr[(int) h[j]-48]<arr[(int)h[j+1]-48])
                {
                    char d=h[j];
                    
                    h[j]=h[j+1];
                    h[j+1]=d;
                    
                }
                    
            }
        }
        
       
        buff="";
        for(int i=0;i<h.length;i++)
        {
            buff+=h[i];
        }
        return buff;
       
    }
        
        ///take count
        
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
                
        
       
               
}
  
   

