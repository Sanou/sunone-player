package com.sunone;

import java.awt.BorderLayout;
import java.io.*;

import java.awt.event.*;
import java.awt.*;
import javax.media.Time;
import javax.media.ControllerAdapter;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.PrefetchCompleteEvent;
import javax.media.RealizeCompleteEvent;
import javax.media.Player;

public class Lecteur extends Thread
{
  static int scrollIncrement = 0;
  public static int ACTIVE = 1;
  public static int UNACTIVE = 0;
  public static Time TIME;
  public static int REPEATPLAYLIST = UNACTIVE;
  public static int REPEATMEDIA = UNACTIVE;
  public static Player player;
  public static final int NEXTWASPAUSE = 0;
  public static final int NEXTWASSTOP = 1;
  public static final int REFERENCESTATUS = 2;
  public static int PLAYSTATUS = REFERENCESTATUS;
  public static Playlist CURRENTPLAYLIST;
  public static String CURRENTMEDIA;
  public static int CURRENTINDEXMEDIA;
  public static final int STATEFULL = 0;
  public static final int STATELESS = 1;

  public static int fullScrean = 0;
  private static Lecteur instance;

  private Lecteur()
  {}

  public static Lecteur getInstance(int i)
  {

    // state full
    if (i == STATEFULL)
    {
      if (instance == null)
        instance = new Lecteur();
    }
    // state less
    else
    {
      instance = new Lecteur();
    }
    return instance;
  }

  public enum SunoneStates
  {
    IN_PAUSE, IN_PLAY, IN_PREVIOUS, IN_NEXT, IN_STOP
  }

  public static SunoneStates state = SunoneStates.IN_STOP;

  // Aucune des methodes de cette classe n'est directement invoqu�e
  // toutes les m�thodes sont app�l�s via la m�thode run() qui est invoqu�e �
  // l'instanciation
  // de la thread Lecteur et auparavant, nous positionnons juste l'attribut
  // CURRENTMETHODE
  // qui d�cide la quelle des m�thodes app�ler.
  public void run()
  {
    try
    {

      if (getsState() == SunoneStates.IN_PLAY)
      {
        if (CURRENTINDEXMEDIA == -1)
        {
          CURRENTINDEXMEDIA++;
          play(CURRENTPLAYLIST, CURRENTINDEXMEDIA);
        }
        else
          play(CURRENTPLAYLIST, CURRENTINDEXMEDIA);
        return;
      }

      if (getsState() == SunoneStates.IN_PAUSE)
      {
        pause();
        return;
      }

      if (getsState() == SunoneStates.IN_PREVIOUS)
      {
        previous();
        return;
      }

      if (getsState() == SunoneStates.IN_NEXT)
      {
        next();
        return;
      }

      if (getsState() == SunoneStates.IN_STOP)
      {
        stop2();
        return;
      }
    }
    catch(Exception ex)
    {}
  }

  // la m�thode play() sans arguments appelle juste la methode play(Playlist
  // playlist,int indexofmedia)
  // � une position bien specifi�
  public static void play() throws Exception
  {
    play(CURRENTPLAYLIST, CURRENTINDEXMEDIA);
  }

  // la methode play avec des param�tres g�re toute la logique metier de lecture
  // c'est le Model dans
  // l'architecture MVC.

  public static void play(Playlist playlist, int indexofmedia) throws Exception
  {
    // la valeur x est utilis�e pour le positionnement du JScroolPane de la
    // JTable
    // la valeur qu'elle cotient � �t� choisit par des experiences que nous
    // avons r�alis�s
    instance.setsStates(SunoneStates.IN_PLAY);
    int x = (4 * 414) / 23;
    // recup�ration du chemin d'une musique dans la playlist
    // la m�thode open file est d�finie dans la classe FileEdited permet juste
    // de lire et renvoyer le chemin d'un fichier
    BufferedReader br = FileEdited.openFile(playlist.getName(), indexofmedia);
    String s = br.readLine();
    br.close();
    CURRENTMEDIA = s;

    do
    {// debut la grande boucle qui boucle jusqu'� la fin du fin de la playlist
     // et r�p�te eventuellement lorsque Repeter la playlist(REPEATPLAYLIST) est
     // activ�e
      while (s != null)
      {// boucle de lecture jusqu'� la fin du fichier afin de reinitialiser
        // les param�tres pour la lecture avant de tester repeatPlaylist.

        if (s != null)// debut du trraitement pour chaque fichier multimedia
                      // dont le chemin a �t� lu dans la playlist.
        {
          // localisation du m�dia gr�ce � la classe Medialocator de JMF
          // cette methode permet d'encapsuler le media elle permet nous permet
          // donc
          // de faire abstraction du format du fichier utili� ( qu'il soit
          // mp3,mpg, avi ou autres)
          MediaLocator mediaLocator = new MediaLocator((new File(s).toURL()).toString());
          do
          {// debut de la boucle dont la condition de sortit est que repeter
           // musique
            // ne soit pas activ�.si REPEATMEDIA est activ� il repete la m�me
            // musique.
            try
            {

              // creation du player. c'est sur lui que le traitement se fait
              // on utilise la classe Manager qui est le gestionnaire permettant
              // return
              // un player (qui est en fait une thread) et c'est sur elle que
              // les op�rations de lecture se feront.
              player = Manager.createPlayer(mediaLocator);

              // ajout du listener qui permet d'�couter les differents phases
              // par lesquels passent
              // le player: de la realisation au debut de la lecture comme nous
              // le verons dans son code
              player.addControllerListener(new ControllerAdapter()
              {
                double dureeTotale = 0;

                // permet d'�couter la fin de la r�alisation du media cet �tat
                // ne peut �tre atteint que lorsque
                // la m�thode realise() a �t� appel� auparavant.
                // lorsque la r�alisation se termine par exemple par exemple, on
                // peut retirer plusieurs
                // propri�t� du media. Comme la dur�e totale
                public void realizeComplete(RealizeCompleteEvent re)
                {
                  // recuperation de la dur�e du media.
                  dureeTotale = player.getDuration().getSeconds();
                  try
                  {
                    if (PLAYSTATUS != NEXTWASPAUSE)
                    {
                      // quelques initialisations des labels centraux de
                      // l'interface graphique
                      // aussi bien celui du nom de l'artiste que celui de la
                      // dur�e totale de la musique
                      GUI.getInstance().jLabel6.setText("00:00/00:00");
                      GUI.getInstance().jLabel.setText(getArtistName(CURRENTMEDIA));
                      GUI.getInstance().jSlider1.setValue(0);
                    }
                  }

                  catch(Exception e)
                  {}
                  GUI.getInstance().jSlider1.setMaximum((int) dureeTotale);
                  player.prefetch();

                }

                public void prefetchComplete(PrefetchCompleteEvent pe)
                {
                  GUI.getInstance().reinitialiseComposantsLecteur();
                  GUI.getInstance().mediaVisuel = player.getVisualComponent();
                  if (GUI.getInstance().mediaVisuel != null)
                    GUI.getInstance().mediaVisuel.addMouseListener(new MouseListener()
                    {

                      public void mouseClicked(MouseEvent e)
                      {
                        if (e.getClickCount() == 2)
                        {
                          if (fullScrean == 0)
                          {
                            fullScrean = 1;
                            GUI.getInstance().fullScrean = GUI.getInstance().getFullScrean();
                            Toolkit kit = Toolkit.getDefaultToolkit();
                            Dimension screen = kit.getScreenSize();
                            GUI.getInstance().fullScrean.setSize(screen);
                            GUI.getInstance().fullScrean.add(GUI.getInstance().mediaVisuel);
                            GUI.getInstance().setVisible(false);
                            GUI.getInstance().fullScrean.setAlwaysOnTop(true);
                            GUI.getInstance().fullScrean.setVisible(true);

                          }
                          else
                          {
                            fullScrean = 0;
                            GUI.getInstance().videoPanel.add(GUI.getInstance().mediaVisuel, BorderLayout.CENTER);
                            GUI.getInstance().fullScrean.setVisible(false);
                            GUI.getInstance().setVisible(true);
                            GUI.getInstance().fullScrean = null;

                          }
                        }
                      }

                      public void mouseEntered(MouseEvent e)
                      {}

                      public void mouseExited(MouseEvent e)
                      {}

                      public void mousePressed(MouseEvent e)
                      {}

                      public void mouseReleased(MouseEvent e)
                      {}

                    });
                  player.getGainControl().setDB(-70 + GUI.getInstance().jSlider.getValue());
                  if (GUI.getInstance().mediaVisuel != null)
                  {
                    GUI.getInstance().jContentPane.add(GUI.getInstance().getJPanel4(), BorderLayout.CENTER);
                    GUI.getInstance().videoPanel.add(GUI.getInstance().mediaVisuel, BorderLayout.CENTER);
                    GUI.getInstance().videoPanel.setVisible(true);
                    GUI.getInstance().videoPanel.setTitle("SunOne");
                    GUI.getInstance().videoPanel.pack();
                    if (GUI.getInstance().fullScrean != null)
                    {
                      GUI.getInstance().fullScrean.add(GUI.getInstance().mediaVisuel);
                      GUI.getInstance().fullScrean.setVisible(true);
                    }

                  }
                  player.start();
                }
              });
              player.realize();

              while (player.getState() != Player.Started)
              {
                Thread.sleep(70);
              }
              String timeTotal = conversion((int) player.getDuration().getSeconds());
              GUI.getInstance().jTable.setValueAt(timeTotal, CURRENTINDEXMEDIA, 2);
              GUI.getInstance().jTable.setValueAt(GUI.fleche, CURRENTINDEXMEDIA, 0);
              if (CURRENTINDEXMEDIA != 0)
                GUI.getInstance().jTable.setValueAt(" ", CURRENTINDEXMEDIA - 1, 0);
              GUI.getInstance().jScrollPane.getVerticalScrollBar().setValue(((4 * 414) / 23) * (CURRENTINDEXMEDIA / 4));
              x += x;
              scrollIncrement = 0;
              if (PLAYSTATUS == NEXTWASPAUSE)
              {
                player.setMediaTime(TIME);
                PLAYSTATUS = -1;
              }
              while (player.getState() == Player.Started)
              {
                Thread.sleep(1000);
                int i = (int) player.getMediaTime().getSeconds();
                GUI.getInstance().jSlider1.setValue(i);
                GUI.getInstance().jLabel6.setText(conversion(i) + "/" + timeTotal);
              }

            }
            catch(NoPlayerException noplayeur)
            {

              noplayeur.printStackTrace();
            }
            catch(IOException io)
            {
              io.printStackTrace();
            }
          }
          while (REPEATMEDIA == ACTIVE);
        }
        CURRENTINDEXMEDIA = CURRENTINDEXMEDIA + 1;
        br = FileEdited.openFile(playlist.getName(), CURRENTINDEXMEDIA);
        s = br.readLine();
        br.close();
        CURRENTMEDIA = s;
      } // fin de while on a atteint la fin du fichier
      // est assez important car il faut reinitialiser les param�tres et tester
      // si repeat eat actif
      // c'est � dire se replacer au debut du fichier.
      br = FileEdited.openFile(playlist.NAME, 0);
      s = br.readLine();
      br.close();
      CURRENTMEDIA = s;
      CURRENTINDEXMEDIA = 0;

    }
    while (REPEATPLAYLIST == ACTIVE);// fin de la grande boucle qui tete repeat
                                     // playlist

  }

  // appelle la methode play pr�cedente avec indexofmedia=0
  public static void play(Playlist playlist) throws Exception
  {
    play(playlist, 0);
  }

  public static void stop2()
  {
    instance.setsStates(SunoneStates.IN_STOP);
    PLAYSTATUS = NEXTWASSTOP;
    player.stop();
    GUI.getInstance().reinitialiseComposantsLecteur();

  }

  public static void pause()
  {
    try
    {
      play(CURRENTPLAYLIST, CURRENTINDEXMEDIA);
    }
    catch(Exception e)
    {}

  }

  public static void previous() throws Exception
  {
    if (REPEATMEDIA == ACTIVE)
    {
      REPEATMEDIA = UNACTIVE;
      GUI.getInstance().repeatTrack.setState(false);
    }
    GUI.getInstance().jTable.setValueAt(" ", CURRENTINDEXMEDIA, 0);
    if (CURRENTINDEXMEDIA == 0)
    {
      if (REPEATPLAYLIST == ACTIVE)
      {
        CURRENTINDEXMEDIA = CURRENTPLAYLIST.getNumberOfMedia() - 1;
        play(CURRENTPLAYLIST, CURRENTINDEXMEDIA);
      }
      else
      {
        play(CURRENTPLAYLIST, CURRENTINDEXMEDIA);
      }
    }
    else
    {
      CURRENTINDEXMEDIA--;
      play(CURRENTPLAYLIST, CURRENTINDEXMEDIA);
    }
  }

  public static void next() throws Exception
  {
    if (REPEATMEDIA == ACTIVE)
    {
      REPEATMEDIA = UNACTIVE;
      GUI.getInstance().repeatTrack.setState(false);
    }
    GUI.getInstance().jTable.setValueAt(" ", CURRENTINDEXMEDIA, 0);
    if (CURRENTINDEXMEDIA == CURRENTPLAYLIST.getNumberOfMedia() - 1)
    {
      if (REPEATPLAYLIST == ACTIVE)
      {
        CURRENTINDEXMEDIA = 0;
        play(CURRENTPLAYLIST, CURRENTINDEXMEDIA);
      }
    }
    else
    {
      CURRENTINDEXMEDIA++;
      play(CURRENTPLAYLIST, CURRENTINDEXMEDIA);
    }

  }

  public static String conversion(int secondes)
  {
    int minute = 0;
    int seconde = 0;
    minute = secondes / 60;
    seconde = secondes % 60;
    String s = (new Integer(minute)).toString(), s2 = (new Integer(seconde)).toString();
    if (minute < 10)
      s = "0" + s;
    if (seconde < 10)
      s2 = "0" + s2;
    return s + ":" + s2;
  }

  public static String getArtistName(String st) throws Exception
  {
    st = new File(st).toURL().toString();
    String st2[] = st.split("/");
    StringBuffer stb = new StringBuffer(st2[st2.length - 1]);
    StringBuffer stb2 = stb.reverse();
    String st3 = new String(stb2);
    int i = 0;
    while (st3.charAt(i) != '.')
      i++;
    String a = st3.substring(i + 1, st3.length());
    if (a.length() >= 21)
      a = "..." + a.substring(a.length() - 21, a.length());
    return (new String(new StringBuffer(a).reverse()));
  }

  public SunoneStates getsState()
  {
    return state;
  }

  public void setsStates(SunoneStates state)
  {
    Lecteur.state = state;
  }
}
