package com.sunone;

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
		
}
