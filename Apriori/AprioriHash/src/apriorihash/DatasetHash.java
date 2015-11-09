package apriorihash;

import java.lang.*;
import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileWriter;



/**
 *
 * @author c137204
 */
public class DatasetHash {

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
        
        
        
        File outf=new File("Z:/AprioriHash/dataset.txt");
        FileWriter outs=null;
        
        outs=new FileWriter(outf);
       
        char ch=' ';
        String ff="";
        
        for(int f=1;f<=q;f++)
        {
            int g=((Math.abs((r.nextInt())))%p)+1;
            if(g==0)
            g=g+1;
           // System.out.println(g);\
            ff=Integer.toString(f);
            //outs.write(ff+" ");
             boolean []mark=new boolean[p+1];
             for(int w=0;w<=p;w++)
             {
                 mark[w]=false;
             }
            int c=0;
            while(c<g)
            {
                int ss=((Math.abs(r.nextInt()))%p)+1;
                if(ss==0)
                    ss=ss+1;
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
    
}
