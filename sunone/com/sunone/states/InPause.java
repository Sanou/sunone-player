package com.sunone.states;

import com.sunone.SunoneException;
import com.sunone.SunoneState;
import com.sunone.Lecteur.SunoneStates;

public class InPause extends SunoneState
{

  public SunoneStates stop() throws SunoneException
  {
    // TODO Auto-generated method stub
    return SunoneStates.IN_STOP;
  }

  public SunoneStates play() throws SunoneException
  {
    // TODO Auto-generated method stub
    return SunoneStates.IN_PLAY;
  }

  public SunoneStates pause() throws SunoneException
  {
    // TODO Auto-generated method stub
    return SunoneStates.IN_PLAY;
  }
}
