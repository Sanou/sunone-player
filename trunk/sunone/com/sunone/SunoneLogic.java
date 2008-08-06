package com.sunone;

public class SunoneLogic {
	
	private static SunoneLogic instance=null;
	
	SunoneLogic(){}

	public static SunoneLogic getInstance(){
		if(instance==null){
		instance= new SunoneLogic();
		}
		return instance;
		}
}
