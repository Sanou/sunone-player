package com.sunone;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.configuration.Configuration;

import com.sunone.Lecteur.SunoneStates;

public abstract class SunoneState
{

  private SunoneStates state;
  private static HashMap<SunoneStates, SunoneState> stateMap;

  @SuppressWarnings("unchecked")
  static void init(Configuration configuration) throws SunoneException
  {

    Iterator iterator = configuration.getKeys();

    stateMap = new HashMap<SunoneStates, SunoneState>();
    for (; iterator.hasNext();)
    {
      String stateName = (String) iterator.next();
      String stateClass = (String) configuration.getString(stateName);
      stateClass = stateClass.trim();
      SunoneState state = null;

      try
      {
        Class classState = Class.forName(stateClass);
        Constructor<SunoneState> constructor = classState.getConstructor();
        state = constructor.newInstance();
      }
      catch(InvocationTargetException e)
      {
        throw new SunoneException("PUserState.init Error getting object for State: " + stateName + ", " + e.getMessage());
      }
      catch(InstantiationException e)
      {
        throw new SunoneException("PUserState.init Error getting object for State: " + stateName + ", " + e.getMessage());
      }
      catch(IllegalAccessException e)
      {
        throw new SunoneException("PUserState.init Error getting object for State: " + stateName + ", " + e.getMessage());
      }
      catch(NoSuchMethodException e)
      {
        throw new SunoneException("PUserState.init Error getting object for State: " + stateName + ", " + e.getMessage());
      }
      catch(ClassNotFoundException e)
      {
        throw new SunoneException("PUserState.init Error getting object for State: " + stateName + ", " + e.getMessage());
      }

      stateMap.put(SunoneStates.valueOf(stateName), state);

    } // end

  }

  public static SunoneState getStateObject(SunoneStates state)
      throws SunoneException
  {
    SunoneState stateObject = stateMap.get(state);
    if (stateObject == null)
    {
      throw new SunoneException("PUserState.getStateObject: Error can not find state object for: " + state.toString());
    }
    return stateObject;
  }

  public SunoneStates play() throws SunoneException
  {
    throw new SunoneException("PUserState Error: method exitPagurezone not implemented in PUserState: " + getClass().getName());
  }

  public SunoneStates stop() throws SunoneException
  {
    throw new SunoneException("PUserState Error: method exitPagurezone not implemented in PUserState: " + getClass().getName());
  }

  public SunoneStates next() throws SunoneException
  {
    throw new SunoneException("PUserState Error: method exitPagurezone not implemented in PUserState: " + getClass().getName());
  }

  public SunoneStates previous() throws SunoneException
  {
    throw new SunoneException("PUserState Error: method exitPagurezone not implemented in PUserState: " + getClass().getName());
  }

  public SunoneStates pause() throws SunoneException
  {
    throw new SunoneException("PUserState Error: method exitPagurezone not implemented in PUserState: " + getClass().getName());
  }

  public SunoneStates getState()
  {
    return state;
  }

  public void setState(SunoneStates state)
  {
    this.state = state;
  }
}
