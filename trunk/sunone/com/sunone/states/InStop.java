package com.sunone.states;

import com.sunone.SunoneException;
import com.sunone.SunoneState;
import com.sunone.Lecteur.SunoneStates;

public class InStop extends SunoneState{
	
	public SunoneStates play() throws SunoneException {
		// TODO Auto-generated method stub
		return SunoneStates.IN_PLAY;
	}

}
