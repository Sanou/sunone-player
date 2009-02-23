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
    StringBuilder result = new StringBuilder();

    result.append(getMessage()).append("\n").append(getStackTrace());
    if (getCause() == null)
      return result.toString();
    else
      return result.append("\n").append("caused by ").append(getCause()).toString();
  }

}
