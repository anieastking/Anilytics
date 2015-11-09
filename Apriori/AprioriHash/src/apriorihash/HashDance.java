package apriorihash;

import java.lang.*;
import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileWriter;
public class HashDance {
    
    
    
    public int HashCal(String p,int hashval)
    {
       // int si=p.length();
        
        int val=Integer.parseInt(p);
        
        return (val%hashval);
    }
    
    
    public void makehash( String []arr,int minsup,int max,int gyt)
    {
        int [][]valmap=new int[max+1][max+1];
        
        for(int i=0;i<max+1;i++)
        {
            for(int j=0;j<max+1;j++)
            {
                valmap[i][j]=0;
            }
        }
        
        
        
       for(int i=0;i<10;i++)
       {
           String ch=arr[i];
           
          // System.out.println(arr[i]);
           
           if(arr[i].length()<2)
            continue;
           char [] g=ch.toCharArray();
           
           
            
          for(int j=0;j<ch.length();j++)
           {
               for(int k=j+1;k<ch.length();k++)
               {
                   int jojo=(int)g[j]-48;int yoy=(int) g[k]-48;
                  // System.out.println(jojo+"  "+yoy);
                   valmap[jojo][yoy]++;
               }
        
          }
       }
       
        //ystem.exit(1);
       int hashval=7;
       
       
      
     
          ArrayList<String> [] hash= (ArrayList<String>[])new ArrayList[hashval+1];
          
          for(int i=0;i<hashval;i++)
          {
              hash[i]=new ArrayList<String>();
          }
          
          int [] count=new int[hashval];
           for(int i=0;i<hashval;i++)
               count[i]=0;
      //    System.out.println(arr.length);
          for(int i=0;i<arr.length;i++)
           {
               String p=arr[i];
              //System.out.println("fs"+arr[i]);
               char[] chars = p.toCharArray();
              Arrays.sort(chars);
                p = new String(chars);
             // System.out.println(p.length());
              if(p.length()<2)
                  continue;
                for(int j=0;j<p.length();j++)
                {
                    for(int k=j+1;k<p.length();k++)
                    {
                        String love="";
                        love+=chars[j];love+=chars[k];
                        // System.out.println(love);
                          int ans=HashCal(love,hashval);
                        //  System.out.println(ans);
                         if(!(hash[ans].contains(love)));
                         {
                          hash[ans].add(love);
                          count[ans]++;
                         }
                     
                         
                    }
                }
              
             
           }
             
       
        try{  
       File outx=new File("Z:/AprioriHash/HashBin.txt");
        FileWriter outa=null;
        
        outa=new FileWriter(outx);
           for(int i=0;i<hashval;i++)
           {
               if(count[i]>=minsup)
               {
                   ArrayList<String> cc=new ArrayList();
                   cc=hash[i];
                 
                   System.out.print("bucket "+i+"-------->");
                   outa.write("bucket "+i+"-------->");
                   for(String p :cc)
                   {
                     //  System.out.println(p);
                       char []fin=p.toCharArray();
                       
                       if(valmap[fin[0]-48][fin[1]-48]>=minsup)
                       {
                           outa.write(fin[0]+" ,"+fin[1]+"    ");
                           
                           System.out.print(fin[0]+" ,"+fin[1]+"    ");
                       }
                       
                       
                   }
                   
                   System.out.println("");
                   outa.write("\r\n");
               }
           }
           outa.flush();
        }
        catch(Exception e)
        {}
        
    
       
       
    }
    
}
