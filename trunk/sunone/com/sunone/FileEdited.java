package com.sunone;
//import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
//import java.net.URL;
public class FileEdited {
    public static BufferedReader openFile(String filename,int positionline)throws Exception{
		BufferedReader br=new BufferedReader(new FileReader(new File(filename)));
		int i=0;
		while(i<positionline)
		{
		i++;
		br.readLine();}
		return br;
       }
    //public static void closeFile(String filename)throws Exception{
    	//BufferedReader br =new BufferedReader(new FileReader(new File(filename)));  	
    	 //br.close();
    //}
    public static void closeFile1(String filename)throws Exception{
    	PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(new File(filename))));
    	pw.println(Interface2.lf);
    	pw.println(Playlist.currentNAME);
    	pw.println(Lecteur.CURRENTINDEXMEDIA);
    	pw.println(Lecteur.REPEATPLAYLIST);
    	pw.print(Lecteur.REPEATMEDIA);
    	if(Interface2.currentDirectory!=null)
    		{pw.println();
    		pw.print(Interface2.currentDirectory.toString());
    		}
    	pw.close();
    }
    public static void closeFile2(String filename)throws Exception{
    	BufferedReader br =new BufferedReader(new FileReader(new File(filename))); 
    	Interface2.lf=Integer.parseInt(br.readLine());
    	Playlist.currentNAME=br.readLine();
    	Lecteur.CURRENTINDEXMEDIA=Integer.parseInt(br.readLine());
    	Lecteur.REPEATPLAYLIST=Integer.parseInt(br.readLine());
    	Lecteur.REPEATMEDIA=Integer.parseInt(br.readLine());
    	String Maimouna=br.readLine();
    	if(Maimouna!=null)
    		Interface2.currentDirectory=new File(Maimouna);
    	br.close();
    }
    
}
