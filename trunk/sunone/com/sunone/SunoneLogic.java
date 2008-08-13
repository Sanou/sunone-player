package com.sunone;

import javax.swing.JFileChooser;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import com.sunone.Lecteur.SunoneStates;

public class SunoneLogic {
	
	private static SunoneLogic instance=null;
	private PropertiesConfiguration configuration;
	private String SUNONE_CONFIGURATION_FILE="sunone.properties";
	private String SUNONE_STATES_PREFIX="com.sunone.states";
	static Logger logger =Logger.getLogger(SunoneLogic.class);
	
	private SunoneLogic(){}

	public static SunoneLogic getInstance(){
		if(logger.isDebugEnabled())
			logger.debug("GetInstance");
		if(instance==null){
		instance= new SunoneLogic();
		}
		return instance;
		}
	
	public void init(){
		if(logger.isDebugEnabled())
			logger.debug("initializing");
			try {
				configuration = new PropertiesConfiguration(SUNONE_CONFIGURATION_FILE);
				GUI.configuration =configuration;
			} catch (ConfigurationException e) {
				// TODO Auto-generated catch block
				logger.error("Erreur de lecture du fichier de configuration");
			}
			
			try{
				Configuration sunoneStatesConfiguration = configuration.subset(SUNONE_STATES_PREFIX);
		    	SunoneState.init(sunoneStatesConfiguration);
		    }
		    catch (Exception e){
		    	logger.error("Erreur d'initiation de l'état du serveur.");
		}
		    
	}
	
	@SuppressWarnings("deprecation")
	public void pauseActionHandled(){
		try{ 
			if(Lecteur.state!=SunoneStates.IN_STOP)
				{ 
					    Lecteur.TIME=Lecteur.player.getMediaTime();		    
					    Lecteur.player.stop();
					    Lecteur.getInstance(Lecteur.STATEFULL).stop();
					    Lecteur.state=SunoneStates.IN_STOP;
					    System.gc();
                        Lecteur.PLAYSTATUS=Lecteur.NEXTWASPAUSE;
				}
				else{ 
					Lecteur.state=SunoneStates.IN_PAUSE;
					Lecteur.getInstance(Lecteur.STATELESS).start();
				System.gc();
		}
					
			
	}catch (Exception ex){}
	}
	@SuppressWarnings("deprecation")
	public void playActionHandled(){
		try{ 
			if(Lecteur.state!=SunoneStates.IN_STOP){
			Lecteur.player.stop();
			Lecteur.getInstance(Lecteur.STATEFULL).stop();
		}
			Lecteur.state=SunoneStates.IN_PLAY;
			Lecteur.getInstance(Lecteur.STATELESS).start();
	    System.gc();
	}catch (Exception ex){}
	}
	@SuppressWarnings("deprecation")
	public void stopActionHandled(){
		try{ 
			
			if(Lecteur.state!=SunoneStates.IN_STOP){
			Lecteur.player.stop();
			Lecteur.getInstance(Lecteur.STATEFULL).stop();
            
            System.gc();
			GUI.getInstance().videoPanel.setVisible(false);
			GUI.getInstance().reinitialiseComposantsLecteur();
            
		}
	    
	}catch (Exception ex){}
	}
	@SuppressWarnings("deprecation")
	public void nextActionHandled(){
		try{ 
			
			if(Lecteur.state!=SunoneStates.IN_STOP){
			Lecteur.player.stop();
			Lecteur.getInstance(Lecteur.STATEFULL).stop();
			Lecteur.state=SunoneStates.IN_NEXT;
			Lecteur.getInstance(Lecteur.STATELESS).start();
		    System.gc();
		}
	    
	}catch (Exception ex){}
	
	}
	@SuppressWarnings("deprecation")
	public void previousActionHandled(){
		try{ 
			
			if(Lecteur.state!=SunoneStates.IN_STOP){
			Lecteur.player.stop();
			Lecteur.getInstance(Lecteur.STATEFULL).stop();
			Lecteur.state=SunoneStates.IN_PREVIOUS;
			Lecteur.getInstance(Lecteur.STATELESS).start();
		    System.gc();
		 }
	    
	}catch (Exception ex){}
	}
	
	void addToPlaylist(){
		try{
    		Playlist.addToPlaylist(GUI.getInstance().getFichier(JFileChooser.FILES_ONLY));
    		GUI.saveData=new String[Lecteur.CURRENTINDEXMEDIA+1];
    		for(int i=0;i<=Lecteur.CURRENTINDEXMEDIA;i++){
    			GUI.saveData[i]=(String)GUI.data[i][2];
    		}
    		GUI.getInstance().jTable=null;
			GUI.getInstance().jScrollPane.setViewportView(GUI.getInstance().getJTable());
			GUI.getInstance().jScrollPane.getVerticalScrollBar().setValue(((4*414)/23)*(Lecteur.CURRENTINDEXMEDIA/4));
			GUI.getInstance().jTable.setValueAt(GUI.fleche,Lecteur.CURRENTINDEXMEDIA, 0);
    	}catch(Exception ex){}
	}
		
	void savePlaylist(){
		try{	
    		Playlist.savePlaylist("Give the Playlist Name");
    	}catch(Exception ex){}
	}
}
