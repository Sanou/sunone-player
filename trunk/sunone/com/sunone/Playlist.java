package com.sunone;
import java.io.*;
import java.util.*;
import javax.swing.*;

import org.apache.log4j.Logger;
public class Playlist {
	//Player player=null;
	static Logger logger =Logger.getLogger(Playlist.class);
	public Playlist(String s){
		NAME=s;
	}

	public  int NUMBEROFMEDIA=0;
	public String NAME;
	//contient le nom de la playlist en cours de lecture
	public static String currentNAME;
	public static void addToPlaylist(){
		if(logger.isDebugEnabled())
			logger.debug("Add to playlist (methode without parameters).");
		JFileChooser choixFichier=new JFileChooser();
		choixFichier.setFileSelectionMode(JFileChooser.FILES_ONLY);
		choixFichier.setMultiSelectionEnabled(true);
		
	}	
	public boolean isEmpty()throws Exception{
		if(logger.isDebugEnabled())
			logger.debug("Verifying if playlist is empty or not.");
		BufferedReader br=new BufferedReader(new FileReader(new File(this.NAME)));
		if(br.readLine()!=null)
		{
			br.close();
			return true;			
		}
		
		else {
			br.close();
			return false;
			}
	}
	public static void copy(File fsource,File fdestination)throws Exception{
		if(logger.isDebugEnabled())
			logger.debug("copy - source - destination.");
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(fdestination)));
		BufferedReader br=new BufferedReader(new FileReader(fsource));
		String st;
		if((st=br.readLine())!=null)
			pw.print(st);
		while((st=br.readLine())!=null){
			pw.println();
			pw.print(st);
			
		}
		pw.close();
		br.close();
	}
	public static void addToPlaylist(File[] filesnames)throws Exception{
		if(logger.isDebugEnabled())
			logger.debug("Add to playlist (with parameters - filenames[]).");
		File f=new File(Lecteur.CURRENTPLAYLIST.NAME);
		File Fe=new File(Lecteur.CURRENTPLAYLIST.NAME.substring(0, Lecteur.CURRENTPLAYLIST.NAME.length()-19)+"wssederdferdtdfdgetrdfdte.pl");
		PrintWriter br=new PrintWriter(new BufferedWriter(new FileWriter(Fe)));
		BufferedReader br1=new BufferedReader(new FileReader(f));
		String st=null;
		while((st=br1.readLine())!=null){
			br.println(st);
		}
		for(int i=0;i<filesnames.length-1;i++){
		br.println(filesnames[i].toString());	
		}
		br.print(filesnames[filesnames.length-1].toString());
		br.close();
		br1.close();
		new File(Lecteur.CURRENTPLAYLIST.NAME).delete();
		(new File(Lecteur.CURRENTPLAYLIST.NAME.substring(0, Lecteur.CURRENTPLAYLIST.NAME.length()-19)+"wssederdferdtdfdgetrdfdte.pl")).renameTo(new File(Lecteur.CURRENTPLAYLIST.NAME));
	}
	//c'est 2 methodes d'ajouter un ou des medias dans la playlist répèré par CURRENTPLAYLIST a partir de
	//l'index en cours répèré par CURRENTINDEXMEDIA
	public static void removeFromPlaylist(int[] index)throws Exception{
		if(logger.isDebugEnabled())
			logger.debug("Remove from playlist.");
		GUI.saveData=new String[GUI.data.length-index.length];
		BufferedReader br=new BufferedReader(new FileReader(new File(Lecteur.CURRENTPLAYLIST.NAME)));
		String s0=Lecteur.CURRENTPLAYLIST.NAME.substring(0,Lecteur.CURRENTPLAYLIST.NAME.length()-19);
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(new File(s0+"wssederdferdtdfdgetrdfdte.pl"))));
		String s;
		int k=0;
		int p=0;
		int i=index[0];
		int indice=0;
		if((s=br.readLine())!=null){	
			if(k==i){
				indice++;
				if(indice<index.length)
					i=index[indice];
                   k++;
			       }
			else{
				GUI.saveData[p]=(String)GUI.data[k][2];
				p++;
				k++;
				pw.print(s);
			}
			
		}
		while((s=br.readLine())!=null){	
			if(k==i){
				indice++;
				if(indice<index.length)
					i=index[indice];
                   k++;
			  }
			else{
				if(index[0]==0)
				 index[0]=-1;
				else
					pw.println();
				GUI.saveData[p]=(String)GUI.data[k][2];
				p++;
				k++;
				pw.print(s);
			}
			
		}
		pw.close();
		br.close();
		(new File(Lecteur.CURRENTPLAYLIST.NAME)).delete();
		(new File(s0+"wssederdferdtdfdgetrdfdte.pl")).renameTo(new File(Lecteur.CURRENTPLAYLIST.NAME));
	}
	public static void removePlaylist(String s){
		if(logger.isDebugEnabled())
			logger.debug("Remove playlist.");
		if(s.equals(currentNAME))
			currentNAME=Lecteur.CURRENTPLAYLIST.NAME;
		    (new File(s)).delete();
	}
	// Vide la current playlist et attend que vous charger la nouvelle
	public static void newPlaylist()throws Exception{
		if(logger.isDebugEnabled())
			logger.debug("New playlist.");
		if(Lecteur.CURRENTPLAYLIST.isEmpty())
		{     			 
			    savePlaylist("Would you like to save the Current Playlist");
			    currentNAME=Lecteur.CURRENTPLAYLIST.NAME; //pour donner l'impression qu'on est dans current playlist
			  }
			 clearPlaylist(Lecteur.CURRENTPLAYLIST);
		
//		Ici on doit initialiser INDEXOFMEDIA à 1 
	}
	public static void clearPlaylist(Playlist pl)throws Exception{
		String s=pl.NAME;
		//(new File(s)).delete();
		(new File(s+".dec")).delete();
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(new File(s))));
		pw.close();
	}
	public static void savePlaylist(String message)throws Exception{
		if(logger.isDebugEnabled())
			logger.debug("Save playlist.");
		if(currentNAME.equals(Lecteur.CURRENTPLAYLIST.NAME)==false){
			copy(new File(Lecteur.CURRENTPLAYLIST.NAME),new File(currentNAME));
				}
		else{
		saveAsPlaylist(message);
		}
	}
	public static void saveAsPlaylist(String message)throws Exception{
		if(logger.isDebugEnabled())
			logger.debug("Save as playlist.");
		String s=GUI.messageDialog2(message);
		if(s!=null){
			String s2=Lecteur.CURRENTPLAYLIST.NAME.substring(0, Lecteur.CURRENTPLAYLIST.NAME.length()-19);
			String sa=s2+s+".pl";
			File f=new File(sa); 
			copy(new File(Lecteur.CURRENTPLAYLIST.NAME),f);
			currentNAME=f.toString();
			GUI.thisClass.createOpenItem(sa);
			GUI.thisClass.createdeleteItem(sa);
			}
	}
	public static void orderByName(){}
	public static void orderByArtist(){}
	public static void orderBySize(){}
	
	public static void openPlaylist(File sanou)throws Exception{
		if(logger.isDebugEnabled())
			logger.debug("Open playlist.");
		if(Lecteur.CURRENTPLAYLIST.isEmpty())
		{      
			    savePlaylist("Current Playlist isn't save ; to save now Enter the Playlist Name");
			    new File(Lecteur.CURRENTPLAYLIST.NAME+".dec").delete();
			    copy(sanou,new File(Lecteur.CURRENTPLAYLIST.NAME));
		}
		else{
			copy(sanou,new File(Lecteur.CURRENTPLAYLIST.NAME));
		}
	  currentNAME=sanou.toString();	
	}
	public static void clear(){}
	public String getName(){
		if(logger.isDebugEnabled())
			logger.debug("Get Name.");
		return NAME;
	}
	public int getNumberOfMedia(){
		if(logger.isDebugEnabled())
			logger.debug("Get Number Of Media.");
		int i=0;
		try{
		BufferedReader br=new BufferedReader(new FileReader(new File(NAME)));
		while(br.readLine()!=null)
			i++;
		}catch(Exception e){}
		return i;
	}
	@SuppressWarnings("deprecation")
	public  void cutPlaylist()throws Exception{
		if(logger.isDebugEnabled())
			logger.debug("Cut Playlist.");
		String st;
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(new File(this.NAME+".dec"))));
		BufferedReader br=new BufferedReader(new FileReader(new File(this.NAME)));
		if((st=br.readLine())!= null){
			String st2[];
			st=new File(st).toURL().toString();
		    st2=st.split("/");
		    StringBuffer stb=new StringBuffer(st2[st2.length-1]);
		    StringBuffer stb2=stb.reverse();
		    String st3=new String(stb2);
		    int i=0;
		    while(st3.charAt(i)!='.')
		        i++;   
		    String a=st3.substring(i+1,st3.length());
			    pw.print(new StringBuffer(a).reverse());
		}
		while((st=br.readLine())!= null){
			pw.println();
			String st2[];
			st=new File(st).toURL().toString();
		    st2=st.split("/");
		    StringBuffer stb=new StringBuffer(st2[st2.length-1]);
		    StringBuffer stb2=stb.reverse();
		    String st3=new String(stb2);
		    int i=0;
		    while(st3.charAt(i)!='.')		    
		      i++;
		    String a=st3.substring(i+1,st3.length());
		    pw.print(new StringBuffer(a).reverse());
			}
		pw.close();
		br.close();
	}		
	@SuppressWarnings("unchecked")
	public Object[][] loadingPlaylist()throws Exception{
		if(logger.isDebugEnabled())
			logger.debug("Loading Playlist.");
		File f=new File(this.NAME+".dec");
		Object[][] o=null;Vector v=new Vector();
		
	//	Vector v2=new Vector();
		int n=0;
		if(f.exists()==true){
		BufferedReader br=new BufferedReader(new FileReader(f));
		String st;
		while((st=br.readLine())!=null)
			{
			n++;
			v.addElement(st);
			}
			br.close();
			if(n<5)
			{
			//int k=5-n;	
				o=new Object[5][3];
				for(int i=4;i>=n;i--)
				{
					o[i][0]=new String(" "); 					
					o[i][1]=new String(" ");					
					o[i][2]=new String(" ");
				}	
			}
			else
		o=new Object[n][3];
		
		int k=0;
		for(int i=0;i<n;i++){
			o[i][0]=new String(" "); o[i][1]=v.elementAt(k);o[i][2]=new String(" ");
			k++;
			
		}
	if(GUI.saveData!=null)		
		for(int j=0;j<GUI.saveData.length;j++){
		 o[j][2]=GUI.saveData[j];
		  }
		br.close();
		f.delete();
		}		
		else
		{
			cutPlaylist();
			return loadingPlaylist();
		}
		return o;
	}
}
