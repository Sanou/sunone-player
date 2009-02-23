package com.sunone;

import java.io.File;

import javax.media.Player;
import javax.swing.JFileChooser;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import com.sunone.Lecteur.SunoneStates;

public class SunoneLogic
{

  private static SunoneLogic instance = null;
  private PropertiesConfiguration configuration;
  private String SUNONE_CONFIGURATION_FILE = "sunone.properties";
  private String SUNONE_STATES_PREFIX = "com.sunone.states";
  static Logger logger = Logger.getLogger(SunoneLogic.class);

  private SunoneLogic()
  {}

  static SunoneLogic getInstance()
  {
    if (logger.isDebugEnabled())
      logger.debug("GetInstance");
    if (instance == null)
    {
      instance = new SunoneLogic();
    }
    return instance;
  }

  void init()
  {
    if (logger.isDebugEnabled())
      logger.debug("initializing");
    try
    {
      configuration = new PropertiesConfiguration(SUNONE_CONFIGURATION_FILE);
      GUI.configuration = configuration;
    }
    catch(ConfigurationException e)
    {
      // TODO Auto-generated catch block
      logger.error("Erreur de lecture du fichier de configuration");
    }

    try
    {
      Configuration sunoneStatesConfiguration = configuration.subset(SUNONE_STATES_PREFIX);
      SunoneState.init(sunoneStatesConfiguration);
    }
    catch(Exception e)
    {
      logger.error("Erreur d'initiation de l'�tat du serveur.");
    }

  }

  @SuppressWarnings("deprecation")
  void pauseActionHandled()
  {
    try
    {
      if (Lecteur.state != SunoneStates.IN_STOP)
      {
        Lecteur.TIME = Lecteur.player.getMediaTime();
        Lecteur.player.stop();
        Lecteur.getInstance(Lecteur.STATEFULL).stop();
        Lecteur.state = SunoneStates.IN_STOP;
        System.gc();
        Lecteur.PLAYSTATUS = Lecteur.NEXTWASPAUSE;
      }
      else
      {
        Lecteur.state = SunoneStates.IN_PAUSE;
        Lecteur.getInstance(Lecteur.STATELESS).start();
        System.gc();
      }

    }
    catch(Exception ex)
    {}
  }

  @SuppressWarnings("deprecation")
  void playActionHandled()
  {
    try
    {
      if (Lecteur.state != SunoneStates.IN_STOP)
      {
        Lecteur.player.stop();
        Lecteur.getInstance(Lecteur.STATEFULL).stop();
      }
      Lecteur.state = SunoneStates.IN_PLAY;
      Lecteur.getInstance(Lecteur.STATELESS).start();
      System.gc();
    }
    catch(Exception ex)
    {}
  }

  @SuppressWarnings("deprecation")
  void stopActionHandled()
  {
    try
    {

      if (Lecteur.state != SunoneStates.IN_STOP)
      {
        Lecteur.player.stop();
        Lecteur.getInstance(Lecteur.STATEFULL).stop();

        System.gc();
        GUI.getInstance().videoPanel.setVisible(false);
        GUI.getInstance().reinitialiseComposantsLecteur();

      }

    }
    catch(Exception ex)
    {}
  }

  @SuppressWarnings("deprecation")
  void nextActionHandled()
  {
    try
    {

      if (Lecteur.state != SunoneStates.IN_STOP)
      {
        Lecteur.player.stop();
        Lecteur.getInstance(Lecteur.STATEFULL).stop();
        Lecteur.state = SunoneStates.IN_NEXT;
        Lecteur.getInstance(Lecteur.STATELESS).start();
        System.gc();
      }

    }
    catch(Exception ex)
    {}

  }

  @SuppressWarnings("deprecation")
  void previousActionHandled()
  {
    try
    {

      if (Lecteur.state != SunoneStates.IN_STOP)
      {
        Lecteur.player.stop();
        Lecteur.getInstance(Lecteur.STATEFULL).stop();
        Lecteur.state = SunoneStates.IN_PREVIOUS;
        Lecteur.getInstance(Lecteur.STATELESS).start();
        System.gc();
      }

    }
    catch(Exception ex)
    {}
  }

  void addToPlaylistHandled()
  {
    try
    {
      Playlist.addToPlaylist(GUI.getInstance().getFichier(JFileChooser.FILES_ONLY));
      GUI.saveData = new String[Lecteur.CURRENTINDEXMEDIA + 1];
      for (int i = 0; i <= Lecteur.CURRENTINDEXMEDIA; i++)
      {
        GUI.saveData[i] = (String) GUI.data[i][2];
      }
      GUI.getInstance().jTable = null;
      GUI.getInstance().jScrollPane.setViewportView(GUI.getInstance().getJTable());
      GUI.getInstance().jScrollPane.getVerticalScrollBar().setValue(((4 * 414) / 23) * (Lecteur.CURRENTINDEXMEDIA / 4));
      GUI.getInstance().jTable.setValueAt(GUI.fleche, Lecteur.CURRENTINDEXMEDIA, 0);
    }
    catch(Exception ex)
    {}
  }

  void savePlaylistHandled()
  {
    try
    {
      Playlist.savePlaylist("Give the Playlist Name");
    }
    catch(Exception ex)
    {}
  }

  void doubleClickHandled(int element)
  {
    GUI.getInstance().jTable.setValueAt(" ", Lecteur.CURRENTINDEXMEDIA, 0);

    try
    {
      if (Lecteur.state != SunoneStates.IN_STOP)
      {
        if (GUI.getInstance().videoPanel != null)
          GUI.getInstance().remove(GUI.getInstance().videoPanel);
        Lecteur.player.stop();
        Lecteur.CURRENTINDEXMEDIA = element - 1;
      }
      else
        Lecteur.CURRENTINDEXMEDIA = element;
      Lecteur.state = SunoneStates.IN_PLAY;
      Lecteur.getInstance(Lecteur.STATEFULL).start();
      System.gc();
    }
    catch(Exception ex)
    {}
  }

  void volumeControlActionHandled(int volume)
  {
    if (Lecteur.player.getState() == Player.Started)
    {
      Lecteur.player.getGainControl().setDB(-70 + volume);
    }
  }

  void clearPlaylistHandled()
  {
    try
    {
      Playlist.clearPlaylist(Lecteur.CURRENTPLAYLIST);
      GUI.getInstance().jTable = null;
      GUI.getInstance().jScrollPane.setViewportView(GUI.getInstance().getJTable());
      Lecteur.CURRENTINDEXMEDIA = -1;
    }
    catch(Exception e)
    {}
  }

  void repeatMediaActivationHandled()
  {
    if (GUI.getInstance().repeatTrack.getState() == true)
      Lecteur.REPEATMEDIA = Lecteur.ACTIVE;
    else
      Lecteur.REPEATMEDIA = Lecteur.UNACTIVE;
  }

  void repeatPlaylistActivationHandled()
  {
    if (GUI.getInstance().repeatPlaylist.getState() == true)
      Lecteur.REPEATPLAYLIST = Lecteur.ACTIVE;
    else
      Lecteur.REPEATPLAYLIST = Lecteur.UNACTIVE;
  }

  Playlist getCurrentPlaylist()
  {
    return Lecteur.CURRENTPLAYLIST;
  }

  int getCurrentIndexMedia()
  {
    return Lecteur.CURRENTINDEXMEDIA;
  }

  Object[][] loadPlaylist()
  {
    try
    {

      return getCurrentPlaylist().loadingPlaylist();

    }
    catch(Exception e)
    {

    }
    return null;

  }

  void newPlaylistHanled() throws Exception
  {
    Playlist.newPlaylist();
    GUI.getInstance().jTable = null;
    GUI.getInstance().jScrollPane.setViewportView(GUI.getInstance().getJTable());
    Lecteur.CURRENTINDEXMEDIA = -1;
  }

  void setCurrentIndexMedia(int newValue)
  {
    Lecteur.CURRENTINDEXMEDIA = newValue;
  }

  void setCurrentPlaylist(String playlist)
  {
    Lecteur.CURRENTPLAYLIST = new Playlist(playlist);
  }

  void removeTracks()
  {
    try
    {
      int[] tab = GUI.getInstance().jTable.getSelectedRows();
      Playlist.removeFromPlaylist(tab);
      GUI.getInstance().jTable = null;
      GUI.getInstance().jScrollPane.setViewportView(GUI.getInstance().getJTable());
      int k = 0;
      for (int i = 0; i < tab.length; i++)
        if (tab[i] < Lecteur.CURRENTINDEXMEDIA)
          k++;
      Lecteur.CURRENTINDEXMEDIA -= k;
      GUI.getInstance().jScrollPane.getVerticalScrollBar().setValue(((4 * 414) / 23) * (Lecteur.CURRENTINDEXMEDIA / 4));
      GUI.getInstance().jTable.setValueAt(GUI.fleche, Lecteur.CURRENTINDEXMEDIA, 0);
    }
    catch(Exception ex)
    {}
  }

  void openPlaylist(String file) throws Exception
  {
    Playlist.openPlaylist(new File(file));
    GUI.getInstance().jTable = null;
    Lecteur.CURRENTINDEXMEDIA = -1;
    GUI.getInstance().jScrollPane.setViewportView(GUI.getInstance().getJTable());
  }

  void removePlaylist(String name, int index)
  {
    Playlist.removePlaylist(name);
    GUI.getInstance().removePlaylist.remove(GUI.getInstance().trop[index]);
    GUI.getInstance().openPlaylist.remove(GUI.getInstance().trop[index + 1]);
    GUI.getInstance().removePlaylist2.remove(GUI.getInstance().trop2[index]);
    GUI.getInstance().openPlaylist2.remove(GUI.getInstance().trop2[index + 1]);
  }

  void openFileHandled()
  {
    try
    {
      Playlist.newPlaylist();
      GUI.getInstance().jTable = null;
      GUI.getInstance().jScrollPane.setViewportView(GUI.getInstance().getJTable());
      Playlist.addToPlaylist(GUI.getInstance().getFichier(JFileChooser.FILES_ONLY));
      GUI.getInstance().jTable = null;
      GUI.getInstance().jScrollPane.setViewportView(GUI.getInstance().getJTable());
    }
    catch(Exception e)
    {}
  }

  void openFolderHandled()
  {
    try
    {
      Playlist.newPlaylist();
      GUI.getInstance().jTable = null;
      GUI.getInstance().jScrollPane.setViewportView(GUI.getInstance().getJTable());
      Playlist.addToPlaylist(GUI.getInstance().getFichier(JFileChooser.DIRECTORIES_ONLY));
      GUI.getInstance().jTable = null;
      GUI.getInstance().jScrollPane.setViewportView(GUI.getInstance().getJTable());
    }
    catch(Exception ex)
    {}
  }

  void openUrlHandled()
  {
    try
    {
      Playlist.newPlaylist();
      String s = GUI.messageDialog2("Enter the url");
      File f[] = { new File(s) };
      GUI.getInstance().jTable = null;
      GUI.getInstance().jScrollPane.setViewportView(GUI.getInstance().getJTable());
      Playlist.addToPlaylist(f);
      GUI.getInstance().jTable = null;
      GUI.getInstance().jScrollPane.setViewportView(GUI.getInstance().getJTable());
    }
    catch(Exception ex)
    {}
  }

  void saveSunoneParameters()
  {
    try
    {
      FileEdited.saveSunoneParameters(configuration);
    }
    catch(Exception e)
    {}
    System.gc();
  }
}
