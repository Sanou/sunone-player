package com.sunone;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

public class FileEdited
{
  static Logger logger = Logger.getLogger(FileEdited.class);

  public static BufferedReader openFile(String pFilename, int pPositionLine)
      throws Exception
  {
    BufferedReader br = new BufferedReader(new FileReader(new File(pFilename)));
    int i = 0;
    while (i < pPositionLine)
    {
      br.readLine();
      i++;
    }
    return br;
  }

  public static void saveSunoneParameters(PropertiesConfiguration configuration)
      throws Exception
  {
    configuration.setAutoSave(true);
    configuration.save();
    configuration.setProperty("com.sunone.currentlookandfeel", new Integer(GUI.lf));
    configuration.setProperty("com.sunone.playlist.currentname", Playlist.currentNAME);
    configuration.setProperty("com.sunone.playlist.currentindexofmedia", new Integer(Lecteur.CURRENTINDEXMEDIA));
    configuration.setProperty("com.sunone.playlist.repeatplaylist.activation", new Integer(Lecteur.REPEATPLAYLIST));
    configuration.setProperty("com.sunone.playlist.repeatmedia.activation", new Integer(Lecteur.REPEATMEDIA));

    if (GUI.currentDirectory != null)
    {
      configuration.setProperty("com.sunone.currentdirectory", GUI.currentDirectory.toString());
    }
    if (logger.isDebugEnabled())
    {
      logger.debug("-->REPEAT MEDIA ACTIVATION: " + Lecteur.REPEATMEDIA);
      logger.debug("-->CURRENT PLAYLIST NAME: " + Playlist.currentNAME);
      logger.debug("-->REPEAT PLAYLIST ACTIVATION: " + Lecteur.REPEATPLAYLIST);
      logger.debug("-->CURRENT INDEX MEDIA: " + Lecteur.CURRENTINDEXMEDIA);
      logger.debug("Saving Sunone Parameters");
      logger.debug("-->LOOK AND FEEL: " + GUI.lf);
      logger.debug("-->CURRENT FILE CHOOSER DIRECTORY: " + GUI.currentDirectory);
    }
    configuration.save();
  }

  public static void restoreSunoneParameters(PropertiesConfiguration configuration)
      throws Exception
  {
    GUI.lf = configuration.getInt("com.sunone.currentlookandfeel");
    Playlist.currentNAME = configuration.getString("com.sunone.playlist.currentname");
    Lecteur.CURRENTINDEXMEDIA = configuration.getInt("com.sunone.playlist.currentindexofmedia");
    Lecteur.REPEATPLAYLIST = configuration.getInt("com.sunone.playlist.repeatplaylist.activation");
    Lecteur.REPEATMEDIA = configuration.getInt("com.sunone.playlist.repeatmedia.activation");
    String Maimouna = configuration.getString("com.sunone.currentdirectory");
    if (Maimouna != null && !Maimouna.equals(""))
      GUI.currentDirectory = new File(Maimouna);
    if (logger.isDebugEnabled())
    {
      logger.debug("Restoring Sunone Parameters");
      logger.debug("-->LOOK AND FEEL: " + GUI.lf);
      logger.debug("-->CURRENT PLAYLIST NAME: " + Playlist.currentNAME);
      logger.debug("-->CURRENT INDEX MEDIA: " + Lecteur.CURRENTINDEXMEDIA);
      logger.debug("-->REPEAT PLAYLIST ACTIVATION: " + Lecteur.REPEATPLAYLIST);
      logger.debug("-->REPEAT MEDIA ACTIVATION: " + Lecteur.REPEATMEDIA);
      logger.debug("-->CURRENT FILE CHOOSER DIRECTORY: " + GUI.currentDirectory);
    }
  }

}
