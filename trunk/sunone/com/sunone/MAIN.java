package com.sunone;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.SwingUtilities;

public class MAIN
{

  /**
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception
  {
    try
    {
      System.gc();
      SunoneLogic.getInstance().init();
      FileEdited.restoreSunoneParameters(GUI.configuration);
    }
    catch(Exception ex)
    {}
    GUI.lookAndFeel(GUI.lf);
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {

          GUI.getInstance().addWindowListener(new WindowListener()
          {
            public void windowClosing(WindowEvent e)
            {
              try
              {
                FileEdited.saveSunoneParameters(GUI.configuration);
                System.gc();
              }
              catch(Exception ex)
              {}
              System.exit(0);
            }

            public void windowClosed(WindowEvent e)
            {}

            public void windowDeactivated(WindowEvent e)
            {}

            public void windowIconified(WindowEvent e)
            {}

            public void windowOpened(WindowEvent e)
            {
              boolean b = Lecteur.REPEATPLAYLIST == Lecteur.ACTIVE ? true : false;
              GUI.getInstance().repeatPlaylist.setState(b);
              b = Lecteur.REPEATMEDIA == Lecteur.ACTIVE ? true : false;
              GUI.getInstance().repeatTrack.setState(b);

            }

            public void windowDeiconified(WindowEvent e)
            {}

            public void windowActivated(WindowEvent e)
            {}

          });
          GUI.getInstance().setVisible(true);

        }
        catch(Exception e)
        {}
      }
    });

  }
}
