package com.sunone;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

public class SunoneLogic {
	
	private static SunoneLogic instance=null;
	private PropertiesConfiguration configuration;
	private String SUNONE_CONFIGURATION_FILE="sunone.properties";
	private String SUNONE_STATES_PREFIX="com.sunone.states";
	Logger logger =Logger.getLogger(SunoneLogic.class);
	
	private SunoneLogic(){}

	public static SunoneLogic getInstance(){
		if(instance==null){
		instance= new SunoneLogic();
		}
		return instance;
		}
	
	public void init(){
	
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
	
	public void pauseActionHandled(){
		
	}
	public void playActionHandled(){}
	public void stopActionHandled(){}
	public void nextActionHandled(){}
	public void previousActionHandled(){}
		
}
