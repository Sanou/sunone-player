package com.sunone;

public class SunoneException extends Exception
{

  /**
	 * 
	 */
  private static final long serialVersionUID = -5580033577035735501L;

  public SunoneException()
  {
  // TODO Auto-generated constructor stub
  }

  public SunoneException(String message)
  {

  }

  public SunoneException(Throwable cause)
  {
    super(cause);
    // TODO Auto-generated constructor stub
  }

  public SunoneException(String message, Throwable cause)
  {
    super(message, cause);
    // TODO Auto-generated constructor stub
  }

  public String toString()
  {
    String result = getMessage() + "\n" + getStackTrace();
    if (getCause() == null)
      return result;
    else
      return result + "\n caused by " + getCause();
  }

}
