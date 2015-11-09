import java.lang.*;
import java.util.*;
import java.io.*;
public class trie {
    
   
    public static class Node
    {
       int freq[];
       Node child[];
       
       Node(int a)
       {
           freq=new int[10];
           child=new Node[10];
           
           for(int i=1;i<=9;i++)
           {
               freq[i]=0;
               child[i]=null;
           }
           
          
       }
       
    }


    
    
        Node head;
        
        trie()
        {
           Node b=new Node(0);
           head=b;
        }
        public void insert(String p)
        {
           char[] buff=new char[10];
           buff=p.toCharArray();
            head=recurins(buff,head,0);
        }
        
        public Node recurins(char[] p,Node head,int k)
        {
            if(k<p.length)
            {
                if(head.child[(int)p[k]-48]==null)
                {
                    head.child[(int)p[k]-48]=new Node(0);
                    head.freq[(int)p[k]-48]=1;
                    head.child[(int)p[k]-48]=recurins(p,head.child[(int)p[k]-48],k+1);
                }
            else
            {
                 head.freq[(int)p[k]-48]++;
                 head.child[(int)p[k]-48]=recurins(p,head.child[(int)p[k]-48],k+1);
            }
           
            }
             return head;
        }
        
        public void show(int sup)
        {
            
            recursiveshow(head,0,sup);
            
        }
        public void recursiveshow(Node head,int level,int sup)
        {
            if(head==null)
                return;
            
            
            for(int i=1;i<=9;i++)
            {
                if(head.freq[i]>=sup)
                {
                System.out.print("level "+level+" ");
                System.out.print(i);
                System.out.print(" ");
                System.out.println(head.freq[i]);
                }
                recursiveshow(head.child[i],level+1,sup);
            }
        }
       
    }

