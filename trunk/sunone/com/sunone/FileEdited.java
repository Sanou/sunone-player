package com.sunone;

//import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

//import java.net.URL;
public class FileEdited
{
  static Logger logger = Logger.getLogger(FileEdited.class);

  public static BufferedReader openFile(String filename, int positionline)
      throws Exception
  {
    BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
    int i = 0;
    while (i < positionline)
    {
      i++;
      br.readLine();
    }
    return br;
  }

  public static void saveSunoneParameters(PropertiesConfiguration configuration)
      throws Exception
  {
    // PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(new
    // File(filename))));
    configuration.setAutoSave(true);
    configuration.save();
    if (logger.isDebugEnabled())
    {
      logger.debug("Saving Sunone Parameters");
      logger.debug("-->LOOK AND FEEL: " + GUI.lf);
    }
    configuration.setProperty("com.sunone.currentlookandfeel", new Integer(GUI.lf));
    logger.debug("-->CURRENT PLAYLIST NAME: " + Playlist.currentNAME);
    configuration.setProperty("com.sunone.playlist.currentname", Playlist.currentNAME);
    logger.debug("-->CURRENT INDEX MEDIA: " + Lecteur.CURRENTINDEXMEDIA);
    configuration.setProperty("com.sunone.playlist.currentindexofmedia", new Integer(Lecteur.CURRENTINDEXMEDIA));
    logger.debug("-->REPEAT PLAYLIST ACTIVATION: " + Lecteur.REPEATPLAYLIST);
    configuration.setProperty("com.sunone.playlist.repeatplaylist.activation", new Integer(Lecteur.REPEATPLAYLIST));
    logger.debug("-->REPEAT MEDIA ACTIVATION: " + Lecteur.REPEATMEDIA);
    configuration.setProperty("com.sunone.playlist.repeatmedia.activation", new Integer(Lecteur.REPEATMEDIA));

    if (GUI.currentDirectory != null)
    {
      logger.debug("-->CURRENT FILE CHOOSER DIRECTORY: " + GUI.currentDirectory);
      configuration.setProperty("com.sunone.currentdirectory", GUI.currentDirectory.toString());
    }
    configuration.save();
  }

  public static void restoreSunoneParameters(PropertiesConfiguration configuration)
      throws Exception
  {

    if (logger.isDebugEnabled())
      logger.debug("Restoring Sunone Parameters");
    GUI.lf = configuration.getInt("com.sunone.currentlookandfeel");
    logger.debug("-->LOOK AND FEEL: " + GUI.lf);
    Playlist.currentNAME = configuration.getString("com.sunone.playlist.currentname");
    logger.debug("-->CURRENT PLAYLIST NAME: " + Playlist.currentNAME);
    Lecteur.CURRENTINDEXMEDIA = configuration.getInt("com.sunone.playlist.currentindexofmedia");
    logger.debug("-->CURRENT INDEX MEDIA: " + Lecteur.CURRENTINDEXMEDIA);
    Lecteur.REPEATPLAYLIST = configuration.getInt("com.sunone.playlist.repeatplaylist.activation");
    logger.debug("-->REPEAT PLAYLIST ACTIVATION: " + Lecteur.REPEATPLAYLIST);
    Lecteur.REPEATMEDIA = configuration.getInt("com.sunone.playlist.repeatmedia.activation");
    logger.debug("-->REPEAT MEDIA ACTIVATION: " + Lecteur.REPEATMEDIA);
    String Maimouna = configuration.getString("com.sunone.currentdirectory");
    if (Maimouna != null && !Maimouna.equals(""))
      GUI.currentDirectory = new File(Maimouna);
    logger.debug("-->CURRENT FILE CHOOSER DIRECTORY: " + GUI.currentDirectory);
  }

}
