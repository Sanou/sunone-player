package com.sunone.states;

import com.sunone.SunoneException;
import com.sunone.SunoneState;
import com.sunone.Lecteur.SunoneStates;

public class InPlay extends SunoneState
{
  @Override
  public SunoneStates pause() throws SunoneException
  {
    // TODO Auto-generated method stub
    return SunoneStates.IN_PLAY;
  }

  @Override
  public SunoneStates stop() throws SunoneException
  {
    // TODO Auto-generated method stub
    return SunoneStates.IN_STOP;
  }

  public SunoneStates next() throws SunoneException
  {
    // TODO Auto-generated method stub
    return SunoneStates.IN_PLAY;
  }

  public SunoneStates previous() throws SunoneException
  {
    // TODO Auto-generated method stub
    return SunoneStates.IN_PLAY;
  }

}
