package com.sunone;
//import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.commons.configuration.Configuration;
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
   
    public static void closeFile1(Configuration configuration)throws Exception{
    	//PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(new File(filename))));
    	configuration.setProperty("com.sunone.playlist.currentname", GUI.lf);
    	configuration.setProperty("com.sunone.playlist.currentname", Playlist.currentNAME);
    	configuration.setProperty("com.sunone.playlist.currentindex_of_media", Lecteur.CURRENTINDEXMEDIA);
    	configuration.setProperty("com.sunone.playlist.repeatplaylist.activation", Lecteur.REPEATPLAYLIST);
    	configuration.setProperty("com.sunone.playlist.repeatmedia.activation", Lecteur.REPEATMEDIA);
    	//pw.println(GUI.lf);
    	//pw.println(Playlist.currentNAME);
    	//pw.println(Lecteur.CURRENTINDEXMEDIA);
    	//pw.println(Lecteur.REPEATPLAYLIST);
    	//pw.print(Lecteur.REPEATMEDIA);
    	if(GUI.currentDirectory!=null)
    		{
    		//pw.println();
    		//pw.print(GUI.currentDirectory.toString()); 
    		configuration.setProperty("com.sunone.currentdirectory", GUI.currentDirectory.toString());
    		}
    	//pw.close();
    }
    public static void closeFile2(Configuration configuration)throws Exception{
    	//BufferedReader br =new BufferedReader(new FileReader(new File(filename))); 
    	
    	GUI.lf=configuration.getInt("");
    	Playlist.currentNAME=configuration.getString("com.sunone.playlist.currentname");
    	Lecteur.CURRENTINDEXMEDIA=configuration.getInt("com.sunone.playlist.current_indexof_media");
    	Lecteur.REPEATPLAYLIST=configuration.getInt("com.sunone.playlist.repeatplaylist.activation");
    	Lecteur.REPEATMEDIA=configuration.getInt("com.sunone.playlist.repeatmedia.activation");
    	String Maimouna=configuration.getString("com.sunone.currentdirectory");
    	if(Maimouna!=null && !Maimouna.equals(""))
    		GUI.currentDirectory=new File(Maimouna);
    }
    
}
