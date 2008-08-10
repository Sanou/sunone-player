package com.sunone;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import javax.media.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.table.*;
import java.io.File;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.jdesktop.swingx.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.filechooser.FileFilter;
import org.jdesktop.swingx.decorator.*;
import com.l2fprod.gui.plaf.skin.Skin;
import com.l2fprod.gui.plaf.skin.SkinLookAndFeel;
import com.l2fprod.util.OS;


import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JOptionPane;
// Ce Projet est réalisé par  Diallo Mamadou Sanou et Fetue

public class GUI extends JXFrame{
	
	public static PropertiesConfiguration configuration;
	Logger logger =Logger.getLogger(GUI.class);
	
	public Component mediaVisuel;
	public static ImageIcon fleche;
	public static String[] saveData={" "," "," "," "};
	private static final long serialVersionUID = 1L;
	public static GUI thisClass=null;
	static Boolean b;
	static Lecteur lecteur=null;
	public JPanel jContentPane = null;
	private JMenuBar jJMenuBar = null;
	private JMenu jMenu = null;
	private JMenu jMenu1 = null;
	private JMenu jMenu2 = null;
	private JMenu jMenu3 = null;
	private JMenu jMenu4 = null;
	//serviront pour open et remove playlist du menu Playlist
	public JMenuItem[] trop=new JMenuItem[50];
	public JMenuItem[] trop2=new JMenuItem[50];
	int tropindex=0;
	int tropindex2=1;
	private JMenuItem jMenuItem = null;
	private JMenuItem jMenuItem1 = null;
	private JMenuItem jMenuItem2 = null;
	private JMenuItem jMenuItem3 = null;
	private JMenuItem jMenuItem4 = null;
	private JMenuItem jMenuItem5 = null;
	private JMenuItem jMenuItem6 = null;
	private JMenuItem jMenuItem7 = null;
	private JPanel jPanel = null;
	private JXImagePanel jPanel1 = null;
	private JXImagePanel jPanel2 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;
	private JButton jButton4 = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	public JSlider jSlider = null;
	public  JSlider jSlider1 = null; 
	private JLabel jLabel7 = null;
	public JScrollPane jScrollPane = null;
	public  JXTable jTable = null;
	private JMenu openPlaylist = null;
	private JMenuItem jMenuItem9 = null;
	private JMenuItem jMenuItem10 = null;
	private JMenu removePlaylist = null;
	private JMenuItem jMenuItem12 = null;
	private JMenuItem jMenuItem13 = null;
	private JMenuItem jMenuItem14 = null;
	private JMenu jMenu5 = null;
	private JCheckBoxMenuItem jMenuItem15 = null;
	private JCheckBoxMenuItem jMenuItem16 = null;
	private JMenuItem jMenuItem71 = null;
	private JCheckBoxMenuItem jMenuItem17 = null;
	public JCheckBoxMenuItem repeatTrack;
	public JCheckBoxMenuItem repeatPlaylist;
	public JLabel jLabel = null;
	public JLabel jLabel6 = null;
    public JWindow fullScrean=null;
    public JWindow getFullScrean(){
    	if(fullScrean==null){
    		fullScrean=new JWindow();
    		}
    	return fullScrean;
    }
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.setPreferredSize(new Dimension(35, 20));
			jJMenuBar.setBackground(SystemColor.controlHighlight);
			jJMenuBar.setForeground(Color.cyan);
			jJMenuBar.add(getJMenu());
			jJMenuBar.add(getJMenu1());
			jJMenuBar.add(getJMenu2());
			jJMenuBar.add(getJMenu3());
			jJMenuBar.add(getJMenu4());
  			
		}
		return jJMenuBar;
	}

	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setPreferredSize(new Dimension(30, 15));
			jMenu.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jMenu.setText("File");
			jMenu.setMnemonic(KeyEvent.VK_F);
			jMenu.add(getJMenuItem());
			jMenu.add(getJMenuItem1());
			jMenu.add(getJMenuItem2());
			jMenu.addSeparator();
			jMenu.add(getJMenuItem3());
			jMenu.addSeparator();
			jMenu.add(getJMenuItem4());
			jMenu.addSeparator();
			jMenu.add(getJMenuItem5());
			jMenu.addSeparator();
			jMenu.add(getJMenuItem6());
		}
		return jMenu;
	}

	private JMenu getJMenu1() {
		if (jMenu1 == null) {
			jMenu1 = new JMenu();
			jMenu1.setPreferredSize(new Dimension(45, 15));
			jMenu1.setMnemonic(KeyEvent.VK_ALT);
			jMenu1.setText("Player");
			jMenu1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jMenu1.add(getJMenuPlay());
			jMenu1.add(getJMenuPrevious());
			jMenu1.add(getJMenuPause());
			jMenu1.add(getJMenuStop());
			jMenu1.add(getJMenuNext());
			
		}
		return jMenu1;
	}
	private JMenu getJMenu2() {
		if (jMenu2 == null) {
			jMenu2 = new JMenu();
			jMenu2.setFocusable(true);
			JPopupMenu.setDefaultLightWeightPopupEnabled(false);
			jMenu2.setPreferredSize(new Dimension(50, 15));
			jMenu2.setMnemonic(KeyEvent.VK_L);
			jMenu2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jMenu2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			jMenu2.setText("Playlist");
			jMenu2.add(getopenPlaylist());
			jMenu2.add(getJMenuItem9());
			jMenu2.add(getJMenuItem10());
			jMenu2.add(getJMenuItem14());
			jMenu2.add(getJMenu5());
			jMenu2.add(getremovePlaylist());
			jMenu2.addSeparator();
			jMenu2.add(getJMenuItem12());
			jMenu2.add(getJMenuItem13());
		}
		return jMenu2;
	}

	private JMenu getJMenu3() {
		if (jMenu3 == null) {
			jMenu3 = new JMenu();
			jMenu3.setFocusable(true);
			JPopupMenu.setDefaultLightWeightPopupEnabled(false);
			jMenu3.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jMenu3.setText("Skins");
			jMenu3.setMnemonic(KeyEvent.VK_O);
			jMenu3.add(getDefaultlf());
			jMenu3.add(getlf0());
			jMenu3.add(getlf1());
			jMenu3.setPreferredSize(new Dimension(55, 15));
		}
		return jMenu3;
	}
	private JMenuItem Defaultlf=null;
	private JMenuItem getDefaultlf() {
		if (Defaultlf == null) {
			Defaultlf = new JMenuItem();
			Defaultlf.setText("Default Skin");
			Defaultlf.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					JOptionPane.showMessageDialog(thisClass,"The Skin will be reloaded on the next start");
				    lf=0;
				}
				
			});
		}
		return Defaultlf;
	}
	private JMenuItem lf0=null;
	private JMenuItem getlf0() {
		if (lf0 == null) {
			lf0 = new JMenuItem();
			lf0.setText("Skin 1");
			lf0.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
						JOptionPane.showMessageDialog(thisClass,"The Skin will be reloaded on the next start");
				         lf=1;	
				}
				
			});
		}
		return lf0;
	}
	private JMenuItem lf1=null;
	private JMenuItem getlf1() {
		if (lf1 == null) {
			lf1 = new JMenuItem();
			lf1.setText("Skin 2");
			lf1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					JOptionPane.showMessageDialog(thisClass,"The Skin will be reloaded on the next start");
				     lf=2;	
				}
				
			});
		}
		return lf1;
	}
	
	private JMenu getJMenu4() {
		if (jMenu4 == null) {
			jMenu4 = new JMenu();
			jMenu4.setFocusable(true);
			JPopupMenu.setDefaultLightWeightPopupEnabled(false);
			jMenu4.setText("Help");
			jMenu4.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jMenu4.setMnemonic(KeyEvent.VK_H);
		}
		return jMenu4;
	}
	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem();
			jMenuItem.setText("Open File");
			jMenuItem.setMnemonic(KeyEvent.VK_CONTROL);
			jMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
					try{
						Playlist.newPlaylist();
						jTable=null;
						jScrollPane.setViewportView(getJTable());
						Playlist.addToPlaylist(getFichier(JFileChooser.FILES_ONLY));
					    jTable=null;
					    jScrollPane.setViewportView(getJTable());
					}catch(Exception ex){}
					}
				
			});
		}
		return jMenuItem;
	}
	

	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("Open Folder");
			jMenuItem1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
					try{
						Playlist.newPlaylist();
						jTable=null;
						jScrollPane.setViewportView(getJTable());
						Playlist.addToPlaylist(getFichier(JFileChooser.DIRECTORIES_ONLY));
					    jTable=null;
					    jScrollPane.setViewportView(getJTable());
					}catch(Exception ex){}
					}
				
			});
		}
		return jMenuItem1;
	}

	/**
	 * This method initializes jMenuItem2	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem2() {
		if (jMenuItem2 == null) {
			jMenuItem2 = new JMenuItem();
			jMenuItem2.setText("Open URL");
			jMenuItem2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
					try{
						Playlist.newPlaylist();
						String s=messageDialog2("Enter the url");
						File f[]={new File(s)};
						jTable=null;
						jScrollPane.setViewportView(getJTable());
						Playlist.addToPlaylist(f);
					    jTable=null;
					    jScrollPane.setViewportView(getJTable());
					}catch(Exception ex){}
					}
				
			});
		}
		return jMenuItem2;
	}
	private JMenuItem getJMenuItem3() {
		if (jMenuItem3 == null) {
			jMenuItem3 = new JMenuItem();
			jMenuItem3.setText("Open Network Stream");
		}
		return jMenuItem3;
	}
	private JMenuItem getJMenuItem4() {
		if (jMenuItem4 == null) {
			jMenuItem4 = new JMenuItem();
			jMenuItem4.setText("Add Tracks to Library");
		}
		return jMenuItem4;
	}
	private JMenuItem getJMenuItem5() {
		if (jMenuItem5 == null) {
			jMenuItem5 = new JMenuItem();
			jMenuItem5.setText("Open Capture Device");
		}
		return jMenuItem5;
	}
	private JMenuItem getJMenuItem6() {
		if (jMenuItem6 == null) {
			jMenuItem6 = new JMenuItem();
			jMenuItem6.setText("Exit");
			jMenuItem6.addActionListener(new ActionListener(){

				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
					try{
				   		  // String st=(new File("com\\sunone\\CloseFile.so")).toURL().getPath().toString();
				   		   FileEdited.saveSunoneParameters(configuration);
				   		   }catch(Exception ex){}
				   		   System.exit(0);
					
				}
				
			});
		}
		return jMenuItem6;
	}
	private JMenuItem getJMenuPrevious() {
		if (jMenuItem7 == null) {
			jMenuItem7 = new JMenuItem("Previous     ALT+R",KeyEvent.VK_R);
			jMenuItem7.addActionListener(new ActionListener(){

				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
                  try{ 
		    			
						if(lecteur!=null){
						Lecteur.player.stop();
                        lecteur.stop();
                        lecteur=new Lecteur();
    				    Lecteur.CURRENTMETHODE=Lecteur.PREVIOUS;
    				    lecteur.start();
					 }
				    
				}catch (Exception ex){}
				}
				
			});
		}
		return jMenuItem7;
	}
	private JMenuItem getJMenuPlay() {
		if (jMenuItem71 == null) {
			jMenuItem71 = new JMenuItem("Play              ALT+P",KeyEvent.VK_P);
			jMenuItem71.addActionListener(new ActionListener(){

				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
					try{ 
						if(lecteur!=null){
						Lecteur.player.stop();
                        lecteur.stop();
					}
				    lecteur=new Lecteur();
				    Lecteur.CURRENTMETHODE=Lecteur.PLAY;
				    lecteur.start();
				    
				}catch (Exception ex){}
				}
				
			});
			
		}
		return jMenuItem71;
	}
	private JMenuItem jMenuItem72=null;
	private JMenuItem getJMenuPause() {
		if (jMenuItem72 == null) {
			jMenuItem72 = new JMenuItem("Pause          ALT+E",KeyEvent.VK_E);
			jMenuItem72.addActionListener(new ActionListener(){

				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
					try{ 
						if(lecteur!=null)
							{ 
							System.out.println("appel de pause");
								    Lecteur.TIME=Lecteur.player.getMediaTime();
								    System.out.println(Lecteur.TIME.getSeconds());		    
								    Lecteur.player.stop();
								    lecteur.stop();
								    lecteur=null;
                                    Lecteur.PLAYSTATUS=Lecteur.NEXTWASPAUSE;
							}
							else{ 
								lecteur=new Lecteur();
							Lecteur.CURRENTMETHODE=Lecteur.PAUSE;
							lecteur.start();
				           
					}
								
						
				}catch (Exception ex){}
				}
				
			});
			
			
		}
		return jMenuItem72;
	}
	private JMenuItem jMenuItem73=null;
	private JMenuItem getJMenuStop() {
		if (jMenuItem73 == null) {
			jMenuItem73 = new JMenuItem("Stop             ALT+S",KeyEvent.VK_S);
			jMenuItem73.addActionListener(new ActionListener(){

				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
                   try{ 
		    			
						if(lecteur!=null){
						Lecteur.player.stop();
						thisClass.videoPanel.setVisible(false);
					    thisClass.reinitialiseComposantsLecteur();
                        lecteur.stop();
                        lecteur=null;
					}
				    
				}catch (Exception ex){}
				}
				
			});
		}
		return jMenuItem73;
	}
	private JMenuItem jMenuItem74=null;
	private JMenuItem getJMenuNext() {
		if (jMenuItem74 == null) {
			jMenuItem74 = new JMenuItem("Next             ALT+N",KeyEvent.VK_N);
			jMenuItem74.addActionListener(new ActionListener(){

				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
                 try{ 
		    			
						if(lecteur!=null){
						Lecteur.player.stop();
                        lecteur.stop();
                        lecteur=new Lecteur();
    				    Lecteur.CURRENTMETHODE=Lecteur.NEXT;
    				    lecteur.start();
					}
				    
				}catch (Exception ex){}
				}
				
			});
		}
		return jMenuItem74;
	}
	private JPanel getJPanel() throws Exception{
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new FlowLayout());
			jPanel.setPreferredSize(new Dimension(0, 120));
			//ajout des panneau gauche
			jPanel.add(getJPanel1(), null);
			//ajout du panneau de la table
			jPanel.add(getJPanel2(), null);
			
		}
		return jPanel;
	}

	   private JCheckBoxMenuItem getrepeatTrack(){
	   repeatTrack=new JCheckBoxMenuItem("          Repeat Track");
	   repeatTrack.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		 if(repeatTrack.getState()==true)
	    			 Lecteur.REPEATMEDIA=Lecteur.ACTIVE;
	    		 else
	    			 Lecteur.REPEATMEDIA=Lecteur.UNACTIVE;
	    	}
	    	
	    });
	   return repeatTrack;
   } 
   private JCheckBoxMenuItem getrepeatPlaylist(){
	   repeatPlaylist=new JCheckBoxMenuItem("          Repeat Playlist");
	   repeatPlaylist.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		 if(repeatPlaylist.getState()==true)
	    			 Lecteur.REPEATPLAYLIST=Lecteur.ACTIVE;
	    		 else
	    			 Lecteur.REPEATPLAYLIST=Lecteur.UNACTIVE;
	    	}
	    	
	    });
	   return repeatPlaylist;
   }
   public static int lf=0;
   //table assez complexe qui utilise le GridBagLayout comme gestionnaire
   //et
   private JPanel getJPanel1() {
	   if(logger.isDebugEnabled())
		   logger.debug("1) - Adding Panel 1 with ALL Button, displayed info and volume trackbar.");
			if (jPanel1 == null) {
			jLabel6 = new JLabel();
			logger.info("Adding current time Label: (initially: 00:00/00:00");
			jLabel6.setText("00:00/00:00");
			jLabel6.setFont(new Font("Arial", Font.BOLD, 12));		
			jLabel6.setForeground(Color.white);
			jLabel = new JLabel();
			jLabel.setText("SunOne Player");
			jLabel.setForeground(Color.white);
			jLabel.setFont(new Font("Arial", Font.BOLD, 14));
			jLabel.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			jLabel7 = new JLabel();
			jLabel7.setText("     ");
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			gridBagConstraints17.insets = new Insets(0, 25, 0, 50);
			gridBagConstraints17.gridx = 4;
			gridBagConstraints17.gridy=5;
			gridBagConstraints17.gridheight=1;
			gridBagConstraints17.gridwidth=10;
			gridBagConstraints17.fill = GridBagConstraints.HORIZONTAL;
			GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
			gridBagConstraints18.insets = new Insets(0, 33, 0, 50);
			gridBagConstraints18.gridx = 4;
			gridBagConstraints18.gridy=6;
			gridBagConstraints18.gridheight=1;
			gridBagConstraints18.gridwidth=6;
			gridBagConstraints18.fill = GridBagConstraints.HORIZONTAL;
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.insets = new Insets(0, 0, 0, 50);
			gridBagConstraints16.gridx = 10;
			gridBagConstraints16.gridy=0;
			gridBagConstraints16.gridheight=1;
			gridBagConstraints16.gridwidth=1;
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.insets = new Insets(8, 5, 2, 0);
			gridBagConstraints14.gridx=4;
			gridBagConstraints14.gridy=7;
			gridBagConstraints14.gridheight=1;
			gridBagConstraints14.gridwidth=6;
			gridBagConstraints14.ipadx = 210;
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.insets = new Insets(0, 62, 0, -3);
			gridBagConstraints13.gridx=2;
			gridBagConstraints13.gridy=6;
			gridBagConstraints13.gridheight = 9;
			gridBagConstraints13.gridwidth = 1;
			gridBagConstraints13.anchor = GridBagConstraints.EAST;
			gridBagConstraints13.fill=GridBagConstraints.VERTICAL;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new Insets(0, 44, 0, 4);
			jLabel5 = new JLabel();
			jLabel5.setText(" ");
			jLabel4 = new JLabel();
			jLabel4.setText("     ");
			jLabel3 = new JLabel();
			jLabel3.setText(" ");
			jLabel2 = new JLabel();
			jLabel2.setText("");
			jLabel1 = new JLabel();
			jLabel1.setText("     ");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.insets = new Insets(8, 5, 11, 0);
			gridBagConstraints1.gridx=4;
			gridBagConstraints1.gridy=9;
			gridBagConstraints1.gridheight = 1;
			gridBagConstraints1.ipadx = 3;
			gridBagConstraints1.ipady = 9;
			gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.gridwidth=1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new Insets(8, 0, 11, 0);
			gridBagConstraints2.gridx = 6;
			gridBagConstraints2.gridy = 9;
			gridBagConstraints2.gridheight=1;
			gridBagConstraints2.gridwidth=1;
			gridBagConstraints2.ipadx = -1;
			gridBagConstraints2.ipady = 9;
			gridBagConstraints.fill=GridBagConstraints.VERTICAL;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.insets = new Insets(8, 0, 11, 0);
			gridBagConstraints3.gridx = 7;
			gridBagConstraints3.gridy=9;
			gridBagConstraints3.gridheight=1;
			gridBagConstraints3.gridwidth=1;
			gridBagConstraints3.ipadx = -1;
			gridBagConstraints3.ipady = 9;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.insets = new Insets(8, 0, 11, 0);
			gridBagConstraints4.gridx = 8;
			gridBagConstraints4.gridy = 9;
			gridBagConstraints4.gridheight=1;
			gridBagConstraints4.gridwidth=1;
			gridBagConstraints4.ipadx = -1;
			gridBagConstraints4.ipady = 9;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.insets = new Insets(8, 0, 11, 17);
			gridBagConstraints5.gridx = 9;
			gridBagConstraints5.gridy = 9;
			gridBagConstraints5.gridheight=1;
			gridBagConstraints5.gridwidth=1;
			gridBagConstraints5.ipadx = 0;
			gridBagConstraints5.ipady = 9;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.insets = new Insets(2, 35, 0, 0);
			gridBagConstraints6.gridx =0;
			gridBagConstraints6.gridy = 0;
			gridBagConstraints6.gridheight=1;
			gridBagConstraints6.gridwidth=1;
			gridBagConstraints6.fill=GridBagConstraints.BOTH;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints12.gridx =0;
			gridBagConstraints12.gridy = 5;
			gridBagConstraints12.gridheight=1;
			gridBagConstraints12.gridwidth=5;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints11.gridx =0;
			gridBagConstraints11.gridy = 6;
			gridBagConstraints11.gridheight=1;
			gridBagConstraints11.gridwidth=5;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints10.gridx =0;
			gridBagConstraints10.gridy = 6;
			gridBagConstraints10.gridheight=1;
			gridBagConstraints10.gridwidth=5;
			jPanel1 = new JXImagePanel();
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.setPreferredSize(new Dimension(437, 115));
			jPanel1.setForeground(Color.white);
			jPanel1.setVisible(true);
			Image image;
			try {
			    image = ImageIO.read(getClass().getResource(configuration.getString("com.sunone.images.fond1")));
			    jPanel1.setImage(image);
			    jPanel1.add(getJButton(), gridBagConstraints1);
			    jPanel1.add(getJButton1(), gridBagConstraints2);
			    jPanel1.add(getJButton2(), gridBagConstraints3);
			    jPanel1.add(getJButton3(), gridBagConstraints4);
			    jPanel1.add(getJButton4(), gridBagConstraints5);
			    jPanel1.add(jLabel5, gridBagConstraints6);
			    jPanel1.add(jLabel1, gridBagConstraints);
			    jPanel1.add(jLabel2,  gridBagConstraints10);
			    jPanel1.add(jLabel3, gridBagConstraints11);
			    jPanel1.add(jLabel4, gridBagConstraints12);
			    jPanel1.add(getJSlider(), gridBagConstraints13);
			    jPanel1.add(getJSlider1(), gridBagConstraints14);
			    jPanel1.add(jLabel7, gridBagConstraints16);
			    jPanel1.add(jLabel, gridBagConstraints17);
			    jPanel1.add(jLabel6, gridBagConstraints18);
			} catch (IOException ex) {
			    ex.printStackTrace();
			}

		}
		return jPanel1;
		
		
	}


	private JPanel getJPanel2() throws Exception{
		if (jPanel2 == null) {
			jPanel2 = new JXImagePanel();
			jPanel2.setLayout(new BorderLayout());
			jPanel2.setPreferredSize(new Dimension(302, 115));
			jPanel2.setVisible(true);
			jPanel2.add(getJScrollPane(), java.awt.BorderLayout.NORTH);
			jPanel2.add(getJPanel3(), BorderLayout.EAST);
			
		}
		return jPanel2;
	}

	private JButton getJButton() {
		if(logger.isDebugEnabled())
			logger.debug("Adding \"previous\" button...");
		if (jButton == null) {
			jButton = new JButton();
			jButton.setSize(new Dimension(50, 50));
			jButton.setIcon(new ImageIcon(getClass().getResource(configuration.getString("com.sunone.images.previous"))));
			jButton.addActionListener(new ActionListener(){
		    	@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e){
		    		try{ 
		    			
						if(lecteur!=null){
						Lecteur.player.stop();
                        lecteur.stop();
                        lecteur=new Lecteur();
    				    Lecteur.CURRENTMETHODE=Lecteur.PREVIOUS;
    				    lecteur.start();
					 }
				    
				}catch (Exception ex){}
				}
		    		
		    	
		    });		
		}
		return jButton;
	}


	private JButton getJButton1() {
		if(logger.isDebugEnabled())
			logger.debug("Adding \"play\" button...");
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setPreferredSize(new Dimension(50, 50));
			jButton1.setFont(new Font("Dialog", Font.BOLD, 12));
			jButton1.setIcon(new ImageIcon(getClass().getResource(configuration.getString("com.sunone.images.play"))));
			jButton1.addActionListener(new ActionListener(){
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e){
					try{ 
							if(lecteur!=null){
							Lecteur.player.stop();
                            lecteur.stop();
						}
					    lecteur=new Lecteur();
					    Lecteur.CURRENTMETHODE=Lecteur.PLAY;
					    lecteur.start();
					    
					}catch (Exception ex){}
					}
			});
		}
		return jButton1;
	}
	private JButton getJButton2() {
		if(logger.isDebugEnabled())
			logger.debug("Adding \"pause\" button...");
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setPreferredSize(new Dimension(48, 30));
			jButton2.setIcon(new ImageIcon(getClass().getResource(configuration.getString("com.sunone.images.pause"))));
			jButton2.addActionListener(new ActionListener(){
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e){
					try{ 
							if(lecteur!=null)
								{ 
									    Lecteur.TIME=Lecteur.player.getMediaTime();		    
									    Lecteur.player.stop();
									    lecteur.stop();
									    lecteur=null;
                                        Lecteur.PLAYSTATUS=Lecteur.NEXTWASPAUSE;
								}
								else{ 
									lecteur=new Lecteur();
								Lecteur.CURRENTMETHODE=Lecteur.PAUSE;
								lecteur.start();
					           
						}
									
							
					}catch (Exception ex){}
					}
			});
		}
		return jButton2;
	}


	private JButton getJButton3() {
		if(logger.isDebugEnabled())
			logger.debug("Adding \"stop\" button...");
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setPreferredSize(new Dimension(48, 30));
			jButton3.setIcon(new ImageIcon(getClass().getResource(configuration.getString("com.sunone.images.stop"))));
			jButton3.addActionListener(new ActionListener(){
		    	@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e){
		    		try{ 
		    			
						if(lecteur!=null){
						Lecteur.player.stop();
						lecteur.stop();
                        lecteur=null;
						thisClass.videoPanel.setVisible(false);
						thisClass.reinitialiseComposantsLecteur();
                        
					}
				    
				}catch (Exception ex){}
				}
		    		
		    	
		    });
		}
		return jButton3;
	}
	private JButton getJButton4() {
		if(logger.isDebugEnabled())
			logger.debug("Adding \"next\" button...");
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setPreferredSize(new Dimension(48, 30));
			jButton4.setIcon(new ImageIcon(getClass().getResource(configuration.getString("com.sunone.images.next"))));
		    jButton4.addActionListener(new ActionListener(){
		    	@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e){
		    		try{ 
		    			
						if(lecteur!=null){
						Lecteur.player.stop();
                        lecteur.stop();
                        lecteur=new Lecteur();
    				    Lecteur.CURRENTMETHODE=Lecteur.NEXT;
    				    lecteur.start();
					}
				    
				}catch (Exception ex){}
				}
		    		
		    	
		    });
		}
		return jButton4;
	}


	private JSlider getJSlider() {
		logger.info("Adding Volume Slider.");
		if (jSlider == null) {
			jSlider = new JSlider();
			jSlider.setOrientation(JSlider.VERTICAL);
			jSlider.setMaximumSize(new Dimension(8, 32767));
			jSlider.setMinimumSize(new Dimension(8, 36));
			jSlider.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jSlider.setPaintTicks(true);
			jSlider.setPaintLabels(true);
			jSlider.setMaximum(110);
			jSlider.setPreferredSize(new Dimension(10, 165));
			jSlider.addChangeListener(
					
					 new ChangeListener() {					
					 public void stateChanged( ChangeEvent changeEvent )
					 {
						if(Lecteur.player.getState()==Player.Started) 
						{
							Lecteur.player.getGainControl().setDB(-70+jSlider.getValue());
						}
					 }
					
					 }
					);
		}
		return jSlider;
	}

	private JSlider getJSlider1() {
		logger.info("Adding Media Progression indicator Slider.");
		if (jSlider1 == null) {
			jSlider1 = new JSlider();
			jSlider1.setPreferredSize(new Dimension(215, 10));
			jSlider1.setMaximumSize(new Dimension(32767, 8));
			jSlider1.setMinimumSize(new Dimension(36, 8));
			jSlider1.setPaintLabels(true);
			jSlider1.setPaintTicks(false);
			jSlider1.setMaximum(213);
			jSlider1.setValue(0);
			jSlider1.addMouseListener(new MouseListener(){
				
				public void mouseClicked(MouseEvent e){
					Lecteur.TIME=(new javax.media.Time(jSlider1.getValue()));
					Lecteur.player.setMediaTime((new javax.media.Time(jSlider1.getValue())));
				}
				public void mousePressed(MouseEvent e){
					//Lecteur.player.setMediaTime((new javax.media.Time(jSlider1.getValue())));
				}
				public void mouseReleased(MouseEvent e){
					//Lecteur.player.setMediaTime((new javax.media.Time(jSlider1.getValue())));
				}
				public void mouseEntered(MouseEvent e){
					
				}
				public void mouseExited(MouseEvent e){
					
				}
			});
		}
		return jSlider1;
	}

	private JScrollPane getJScrollPane()throws Exception {
		if(logger.isDebugEnabled())
			logger.debug("2) - Adding Panel 2 with playlist Table.");
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			jScrollPane.setViewportView(getJTable());
			jScrollPane.setPreferredSize(new Dimension(302, 90));
		}
		return jScrollPane;
	}
	
	static Object[][] data;
	private JPanel jPanel3 = null; 
	private JXTable getJTable() throws Exception{
		if(logger.isDebugEnabled())
			logger.debug("Adding PlayList JTable...");
		if (jTable == null) {
			Playlist pl=new Playlist(Lecteur.CURRENTPLAYLIST.NAME);
			if(logger.isDebugEnabled())
				logger.debug("Retrieving current playlist data from currentplaylist.pl...");
			data =pl.loadingPlaylist();	
			  jTable = new JXTable(new AbstractTableModel() {
				public static final long serialVersionUID=1L;
				String[] columnNames = {" ","                      Artist - Title","   Time"};
			    public String getColumnName(int col) {
			        return columnNames[col].toString();
			    }			    
			    public int getRowCount() { return data.length; }
			    public int getColumnCount() { return columnNames.length; }
			    public Object getValueAt(int row, int col) {
			        return data[row][col];
			    }
			    public boolean isCellEditable(int row, int col)
			        { return false; }
			    public void setValueAt(Object value, int row, int col) {
			        data[row][col] = value;
			      fireTableCellUpdated(row, col);
			    }
			    @SuppressWarnings("unchecked")
				public Class getColumnClass(int col) {
			    	if(col==0)
			        return ImageIcon.class;
			    	else
			    		return Object.class;
			      }

			}
			);
			jTable.setAutoCreateColumnsFromModel(false);
			jTable.setAutoscrolls(true);
			jTable.setShowHorizontalLines(false);
		    jTable.setShowVerticalLines(false);
		    jTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		    jTable.getColumnModel().getColumn(2).setPreferredWidth(60);
		    jTable.getColumnModel().getColumn(1).setPreferredWidth(250);
		    jTable.addMouseListener(new MouseListener(){
		    	@SuppressWarnings("deprecation")
				public void mouseClicked(MouseEvent e){
		    		if(e.getClickCount()==2){
		    			GUI.thisClass.jTable.setValueAt(" ",Lecteur.CURRENTINDEXMEDIA, 0);
		    			Lecteur.CURRENTINDEXMEDIA=jTable.getSelectedRow();
		    			Lecteur.CURRENTMETHODE=Lecteur.PLAY;
		    			try{ 
							if(lecteur instanceof Lecteur){
							thisClass.remove(videoPanel);
							Lecteur.player.stop();
                            lecteur.stop();
                            lecteur=null;                          
						}
						
					    lecteur=new Lecteur();
					    lecteur.start();
					    
					}catch (Exception ex){}		    			
		    		}
		    	}
		    	public void mousePressed(MouseEvent e){}
		    	public void mouseExited(MouseEvent e){}
		    	public void mouseEntered(MouseEvent e){}
		    	public void mouseReleased(MouseEvent e){}
		    	
		    	
		    	
		    });
		    jTable.addMouseMotionListener(new MouseMotionListener(){
		    	    public void mouseDragged(MouseEvent e){}
			    	public void mouseMoved(MouseEvent e){}
		    });
		    jTable.setHorizontalScrollEnabled(false);
		   
		    jTable.setHighlighters(
	                new HighlighterPipeline(new Highlighter[] {AlternateRowHighlighter.floralWhite}
	        ));
	        jTable.setRolloverEnabled(true);
	        jTable.getHighlighters().addHighlighter(new RolloverHighlighter(Color.BLUE
	        		, Color.WHITE ));
		    jTable.setSelectionBackground(new Color(94, 170, 222));
		    jTable.setShowGrid(false);
	        jTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	        jTable.setIntercellSpacing(new Dimension(0,0));
		}
		return jTable;
	}
	  static int dupli=0;
	  JMenu openPlaylist2=null;
	private JMenu getopenPlaylist() {
		if(dupli!=1){
		if (openPlaylist == null) {
			openPlaylist= new JMenu();
			openPlaylist.setText("Open Playlist");
		}
		return openPlaylist;
		}
		else{
			if (openPlaylist2 == null) {
			openPlaylist2= new JMenu();
			openPlaylist2.setText("       Open Playlist");
		}
		return openPlaylist2;
		}
	}
      JMenuItem jMenuItem92 = null;
	private JMenuItem getJMenuItem9() {
		if(dupli!=1){
		if (jMenuItem9 == null) {
			jMenuItem9 = new JMenuItem();
			jMenuItem9.setText("New Playlist");
			jMenuItem9.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try{
						Playlist.newPlaylist();
					jTable=null;
					jScrollPane.setViewportView(getJTable());
					Lecteur.CURRENTINDEXMEDIA=-1;
					}catch(Exception ex){}
					}
				
			});
		}
		return jMenuItem9;}
		else{
			if (jMenuItem92 == null) {
				jMenuItem92 = new JMenuItem();
				jMenuItem92.setText("       New Playlist");
				jMenuItem92.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						try{
							Playlist.newPlaylist();
						jTable=null;
						jScrollPane.setViewportView(getJTable());
						Lecteur.CURRENTINDEXMEDIA=-1;
						}catch(Exception ex){}
						}
					
				});
			}
			return jMenuItem92;
		}
	}
         JMenuItem jMenuItem102=null;
	private JMenuItem getJMenuItem10() {
		if(dupli!=1){
		if (jMenuItem10 == null) {
			jMenuItem10 = new JMenuItem();
			jMenuItem10.setText("Clear Playlist");
			jMenuItem10.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
					try{
					Playlist.clearPlaylist(Lecteur.CURRENTPLAYLIST);
					jTable=null;
					jScrollPane.setViewportView(getJTable());			
					Lecteur.CURRENTINDEXMEDIA=-1;
					}catch(Exception ex){}
					}
				
			});
		}
		return jMenuItem10;}
		else{
			if (jMenuItem102 == null) {
				jMenuItem102 = new JMenuItem();
				jMenuItem102.setText("       Clear Playlist");
				jMenuItem102.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						
						try{
						Playlist.clearPlaylist(Lecteur.CURRENTPLAYLIST);
						jTable=null;
						jScrollPane.setViewportView(getJTable());			
						Lecteur.CURRENTINDEXMEDIA=-1;
						}catch(Exception ex){}
						}
					
				});
			}
			return jMenuItem102;
		}
	}
	     JMenu removePlaylist2=null;
	private JMenu getremovePlaylist() {
		if(dupli!=1){
		if (removePlaylist == null) {
			removePlaylist = new JMenu();
			removePlaylist.setText("Remove Playlist");
		}
		return removePlaylist;}
		else{
			if (removePlaylist2 == null) {
				removePlaylist2 = new JMenu();
				removePlaylist2.setText("       Remove Playlist");
			}
			return removePlaylist2;
		}
	}
	     JMenuItem jMenuItem122 = null;
	private JMenuItem getJMenuItem12() {
		if(dupli!=1){
		if (jMenuItem12 == null) {
			jMenuItem12 = new JMenuItem();
			jMenuItem12.setText("Remove Track(s)");
			jMenuItem12.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent e){
	        		try{
	        		int [] tab=jTable.getSelectedRows();
	        		Playlist.removeFromPlaylist(tab);
	        		jTable=null;
					jScrollPane.setViewportView(getJTable());
					int k=0;
					for(int i=0;i<tab.length;i++)
						if(tab[i]<Lecteur.CURRENTINDEXMEDIA)
							k++;
					Lecteur.CURRENTINDEXMEDIA-=k;
					GUI.thisClass.jScrollPane.getVerticalScrollBar().setValue(((4*414)/23)*(Lecteur.CURRENTINDEXMEDIA/4));
					GUI.thisClass.jTable.setValueAt(GUI.fleche,Lecteur.CURRENTINDEXMEDIA, 0);
	        		}catch(Exception ex){}
	        		}	        	
	        });
		}
		return jMenuItem12;}
		else{
			if (jMenuItem122 == null) {
				jMenuItem122 = new JMenuItem();
				jMenuItem122.setText("       Remove Track(s) ");
				jMenuItem122.addActionListener(new ActionListener(){
		        	public void actionPerformed(ActionEvent e){
		        		try{
		        		int [] tab=jTable.getSelectedRows();
		        		Playlist.removeFromPlaylist(tab);
		        		jTable=null;
						jScrollPane.setViewportView(getJTable());
						int k=0;
						for(int i=0;i<tab.length;i++)
							if(tab[i]<Lecteur.CURRENTINDEXMEDIA)
								k++;
						Lecteur.CURRENTINDEXMEDIA-=k;
						GUI.thisClass.jScrollPane.getVerticalScrollBar().setValue(((4*414)/23)*(Lecteur.CURRENTINDEXMEDIA/4));
						GUI.thisClass.jTable.setValueAt(GUI.fleche,Lecteur.CURRENTINDEXMEDIA, 0);
		        		}catch(Exception ex){}
		        		}	        	
		        });
			}
			return jMenuItem122;
		}
	}
	     JMenuItem jMenuItem132 = null;
	private JMenuItem getJMenuItem13() {
		if(dupli!=1){
		if (jMenuItem13 == null) {
			jMenuItem13 = new JMenuItem();
			jMenuItem13.setText("Add to Playlist");
			jMenuItem13.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent e){
	        		try{
	        		Playlist.addToPlaylist(getFichier(JFileChooser.FILES_ONLY));
	        		saveData=new String[Lecteur.CURRENTINDEXMEDIA+1];
	        		for(int i=0;i<=Lecteur.CURRENTINDEXMEDIA;i++){
	        			saveData[i]=(String)data[i][2];
	        		}
	        		jTable=null;
					jScrollPane.setViewportView(getJTable());
					GUI.thisClass.jScrollPane.getVerticalScrollBar().setValue(((4*414)/23)*(Lecteur.CURRENTINDEXMEDIA/4));
					GUI.thisClass.jTable.setValueAt(GUI.fleche,Lecteur.CURRENTINDEXMEDIA, 0);
	        		}catch(Exception ex){}
	        		}	        	
	        });
		}
		return jMenuItem13;}
		else{
			if (jMenuItem132 == null) {
				jMenuItem132 = new JMenuItem();
				jMenuItem132.setText("       Add to Playlist");
				jMenuItem132.addActionListener(new ActionListener(){
		        	public void actionPerformed(ActionEvent e){
		        		try{
		        		Playlist.addToPlaylist(getFichier(JFileChooser.FILES_ONLY));
		        		saveData=new String[Lecteur.CURRENTINDEXMEDIA+1];
		        		for(int i=0;i<=Lecteur.CURRENTINDEXMEDIA;i++){
		        			saveData[i]=(String)data[i][2];
		        		}
		        		jTable=null;
						jScrollPane.setViewportView(getJTable());
						GUI.thisClass.jScrollPane.getVerticalScrollBar().setValue(((4*414)/23)*(Lecteur.CURRENTINDEXMEDIA/4));
						GUI.thisClass.jTable.setValueAt(GUI.fleche,Lecteur.CURRENTINDEXMEDIA, 0);
		        	}catch(Exception ex){}
		        		}	        	
		        });
			}
			return jMenuItem132;
		}
	}
	     JMenuItem jMenuItem142 = null;
	private JMenuItem getJMenuItem14() {
		if(dupli!=1){
		if (jMenuItem14 == null) {
			jMenuItem14 = new JMenuItem();
	        jMenuItem14.setText("Save Playlist");
	        jMenuItem14.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent e){
	        		try{	
	        		Playlist.savePlaylist("Give the Playlist Name");
	        	}catch(Exception ex){}
	        		}
	        	
	        });
		}
		return jMenuItem14;}
		else{
			if (jMenuItem142 == null) {
				jMenuItem142 = new JMenuItem();
		        jMenuItem142.setText("       Save Playlist");
		        jMenuItem142.addActionListener(new ActionListener(){
		        	public void actionPerformed(ActionEvent e){
		        		try{	
		        		Playlist.savePlaylist("Give the Playlist Name");
		        	}catch(Exception ex){}
		        		}
		        	
		        });
			}
			return jMenuItem142;
		}
	}
	     JMenu jMenu52=null;
	private JMenu getJMenu5() {
		if(dupli!=1){
		if (jMenu5 == null) {
			jMenu5 = new JMenu();
			jMenu5.add(getJMenuItem15());
			jMenu5.add(getJMenuItem16());
			jMenu5.add(getJMenuItem17());
			jMenu5.setText("Order By");
		}
		return jMenu5;
		}
		else{
			if (jMenu52 == null) {
				jMenu52 = new JMenu();
				jMenu52.add(getJMenuItem15());
				jMenu52.add(getJMenuItem16());
				jMenu52.add(getJMenuItem17());
				jMenu52.setText("       Order By");
			}
			return jMenu52;
		}
	}
	      JMenuItem jMenuItem152 = null;
	private JMenuItem getJMenuItem15() {
		if(dupli!=1){
		if (jMenuItem15 == null) {
			jMenuItem15 = new JCheckBoxMenuItem();
			jMenuItem15.setText("Name");
		}
		return jMenuItem15;}
		else{
			if (jMenuItem152 == null) {
				jMenuItem152 = new JCheckBoxMenuItem();
				jMenuItem152.setText("Name");
			}
			return jMenuItem152;
		}
	}
	      JMenuItem jMenuItem162 = null;
	private JMenuItem getJMenuItem16() {
		if(dupli!=1){
		if (jMenuItem16 == null) {
			jMenuItem16 = new JCheckBoxMenuItem();
			jMenuItem16.setText("Artist");
		}
		return jMenuItem16;}
		else{
			if (jMenuItem162 == null) {
				jMenuItem162 = new JCheckBoxMenuItem();
				jMenuItem162.setText("Artist");
			}
			return jMenuItem162;
		}
	}
	      JMenuItem jMenuItem172 = null;
	private JMenuItem getJMenuItem17() {
		if(dupli!=1){
		if (jMenuItem17 == null) {
			jMenuItem17 = new JCheckBoxMenuItem();
			jMenuItem17.setText("Size");
		}
		return jMenuItem17;}
		else{
			if (jMenuItem172 == null) {
				jMenuItem172 = new JCheckBoxMenuItem();
				jMenuItem172.setText("Size");
			}
			return jMenuItem172;
		}
	}

	public static String messageDialog2(String s){
		return JOptionPane.showInputDialog(s,"SunOne");
	}
	@SuppressWarnings("deprecation")
	public  void createOpenItem(String s)throws Exception{
		final String st=s;
		    String st2[];
			 String sanou=new File(s).toURL().toString();
		    st2=sanou.split("/");
		    StringBuffer stb=new StringBuffer(st2[st2.length-1]);
		    StringBuffer stb2=stb.reverse();
		    String st3=new String(stb2);
		    int i=0;
		    while(st3.charAt(i)!='.')
		        i++;		    
		    String a=st3.substring(i+1,st3.length());
		    if(new StringBuffer(a).reverse().toString().equals("wssederdferdtdfdgetrdfdte.pl")==false){
		trop[tropindex2]=new JMenuItem(new StringBuffer(a).reverse().toString());
		trop2[tropindex2]=new JMenuItem(new StringBuffer(a).reverse().toString());
		trop[tropindex2].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					try{
						Playlist.openPlaylist(new File(st));	      
						jTable=null;
						Lecteur.CURRENTINDEXMEDIA=-1;
				        jScrollPane.setViewportView(getJTable());
			  }catch(Exception ex){}
				}
			
		});
		trop2[tropindex2].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					try{
						Playlist.openPlaylist(new File(st));	      
						jTable=null;
						Lecteur.CURRENTINDEXMEDIA=-1;
				        jScrollPane.setViewportView(getJTable());
			  }catch(Exception ex){}
				}
			
		});
		openPlaylist.add(trop[tropindex2]);
		openPlaylist2.add(trop2[tropindex2]);
		tropindex2=tropindex2+2;
		}
	}
	
	@SuppressWarnings("deprecation")
	public  void createdeleteItem(String s)throws Exception{
	// On retire le chemin et on garde le nom de la playlist
		     final String st=s;
		     String st2[];
			 String sanou=new File(s).toURL().toString();
		    st2=sanou.split("/");
		    StringBuffer stb=new StringBuffer(st2[st2.length-1]);
		    StringBuffer stb2=stb.reverse();
		    String st3=new String(stb2);
		    int i=0;
		    while(st3.charAt(i)!='.')
		        i++;		    
		    String a=st3.substring(i+1,st3.length());
		    if(new StringBuffer(a).reverse().toString().equals("wssederdferdtdfdgetrdfdte.pl")==false)
		 {trop[tropindex]=new JMenuItem(new StringBuffer(a).reverse().toString());		
		 trop2[tropindex]=new JMenuItem(new StringBuffer(a).reverse().toString());	
		 trop[tropindex].addActionListener(new ActionListener(){
			 int s=tropindex;
				public void actionPerformed(ActionEvent e){
					
					try{
						Playlist.removePlaylist(st);
						removePlaylist.remove(trop[s]);
						openPlaylist.remove(trop[s+1]);
						removePlaylist2.remove(trop2[s]);
						openPlaylist2.remove(trop2[s+1]);
					}catch(Exception ex){}
					}
				
			});
		 trop2[tropindex].addActionListener(new ActionListener(){
			 int s=tropindex;
				public void actionPerformed(ActionEvent e){
					
					try{
						Playlist.removePlaylist(st);
						removePlaylist2.remove(trop2[s]);
						openPlaylist2.remove(trop2[s+1]);
						removePlaylist.remove(trop[s]);
						openPlaylist.remove(trop[s+1]);
					}catch(Exception ex){}
					}
				
			});
		
			removePlaylist.add(trop[tropindex]);
			removePlaylist2.add(trop2[tropindex]);
			tropindex=tropindex+2;}
		}
 
	static File currentDirectory=null;
	public static File getFichier2()
	 { JFileChooser choixFichier=null;
		if(currentDirectory!=null)
		 choixFichier= new JFileChooser(currentDirectory);
		else
			choixFichier= new JFileChooser();
		choixFichier.addChoosableFileFilter(new FiltreMultiple("*.mp3 , *.mpg ,*.mpeg ,*.avi ,*.wav ,*.au ",".mp3,.mpg,.peg,.avi,.wav,.au"));
	 int resultat = choixFichier.showSaveDialog( null );
	 currentDirectory=choixFichier.getCurrentDirectory();
	 if ( resultat == JFileChooser.CANCEL_OPTION )
	 return null;
	
	 else
	 return choixFichier.getSelectedFile();
	 }
	
	public File[] getFichier(int i)
	 {
		JFileChooser choixFichier=null;
		if(currentDirectory!=null)
		{ choixFichier= new JFileChooser(currentDirectory);
		choixFichier.addChoosableFileFilter(new FiltreMultiple("*.mp3 , *.mpg ,*.mpeg ,*.avi ,*.wav ,*.au ",".mp3,.mpg,.peg,.avi,.wav,.au"));}
		else
			{choixFichier= new JFileChooser();
			choixFichier.addChoosableFileFilter(new FiltreMultiple("*.mp3 , *.mpg ,*.mpeg ,*.avi ,*.wav ,*.au ",".mp3,.mpg,.peg,.avi,.wav,.au"));
			}
	
	 choixFichier.setFileSelectionMode(i);
	 choixFichier.setMultiSelectionEnabled(true);
	 int resultat = choixFichier.showOpenDialog( this );
	 currentDirectory=choixFichier.getCurrentDirectory();
	 if ( resultat == JFileChooser.CANCEL_OPTION )
	 return null;
	
	 else
	 return choixFichier.getSelectedFiles();
	 }
	
	
	public JPanel getJPanel3() {
		if(logger.isDebugEnabled())
			logger.debug("3) - Adding Panel 3 with combo menu.");
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setLayout(new GridBagLayout());
			jPanel3.setPreferredSize(new Dimension(302, 10));
				    
			        JMenu jMenu22 = ComboMenuBar.createMenu("PLAYLIST OPTIONS");
			        jMenu22.setFocusable(true);
					JPopupMenu.setDefaultLightWeightPopupEnabled(false);
				    JMenu jmenu = ComboMenuBar.createMenu("REPEAT OPTIONS");
				    jmenu.setFocusable(true);
					JPopupMenu.setDefaultLightWeightPopupEnabled(false);
				    jmenu.add(getrepeatTrack());
				    jmenu.add(getrepeatPlaylist());
				    dupli=1;
				    jMenu22.add(getopenPlaylist());
					jMenu22.add(getJMenuItem9());
					jMenu22.add(getJMenuItem10());
					jMenu22.add(getJMenuItem14());
					jMenu22.add(getJMenu5());
					jMenu22.add(getremovePlaylist());
					jMenu22.addSeparator();
					jMenu22.add(getJMenuItem12());
					jMenu22.add(getJMenuItem13());
				    dupli=0;
					
				    ComboMenuBar comboMenu = new ComboMenuBar(jMenu22);
				    ComboMenuBar comboMenu2 = new ComboMenuBar(jmenu);
				    jPanel3.setLayout(new BorderLayout());
				    jPanel3.add(comboMenu,BorderLayout.WEST);
				    jPanel3.add(comboMenu2,BorderLayout.EAST);
		}
		return jPanel3;
	}
	
	public JInternalFrame videoPanel=null;
	public JInternalFrame getJPanel4(){
	if(videoPanel==null){	
		videoPanel=new JInternalFrame();
       videoPanel.setLayout( new BorderLayout() );
        }
	return videoPanel;
	}
	public void reinitialiseComposantsLecteur()
	   {
	     if ( mediaVisuel != null ){
	    videoPanel.remove( mediaVisuel );
	    thisClass.remove(videoPanel);
	    }
	    if(fullScrean!=null)
	    	{fullScrean.remove(mediaVisuel);
	    	thisClass.remove(videoPanel);
	    	}
	   }

	public static void lookAndFeel(int m) throws Exception{
		switch(m){
		case 0:thelast();
		           break;
		case 1:  try{UIManager.setLookAndFeel("com.sun.java.swing.plaf.metal.MetalLookAndFeel");}
		          catch(Exception e){}
		            break;
		case 2:thelast();
		         break;
		default:
		}
		if (OS.isOneDotFourOrMore()) {
		      java.lang.reflect.Method method =
		        JFrame.class.getMethod(
		          "setDefaultLookAndFeelDecorated",
		          new Class[] { boolean.class });
		      method.invoke(null, new Object[] { Boolean.TRUE });

		      method =
		        JDialog.class.getMethod(
		          "setDefaultLookAndFeelDecorated",
		          new Class[] { boolean.class });
		      method.invoke(null, new Object[] { Boolean.TRUE });
		    }
	}
	static JMenuItem jmi=null;
	@SuppressWarnings("deprecation")
	public static void thelast(){
		try{
			
String args;
if(lf==2)
	args="resources\\lookandfeel\\skinlf-1.2.12\\lib\\architectBluethemepack.zip";
else
	args="resources\\lookandfeel\\skinlf-1.2.12\\lib\\themepack.zip";
String themepack = args;
			      if (themepack.endsWith(".xml")) {
			        SkinLookAndFeel.setSkin(
			          SkinLookAndFeel.loadThemePackDefinition(new File(args).toURL()));
			        UIManager.setLookAndFeel("com.l2fprod.gui.plaf.skin.SkinLookAndFeel");
			      } else if (themepack.startsWith("class:")) {
			        String classname = themepack.substring("class:".length());
			        SkinLookAndFeel.setSkin((Skin)Class.forName(classname).newInstance());
			        UIManager.setLookAndFeel("com.l2fprod.gui.plaf.skin.SkinLookAndFeel");
			      } else if (themepack.startsWith("theme:")) {
			        String classname = themepack.substring("theme:".length());
			        MetalTheme theme = (MetalTheme)Class.forName(classname).newInstance();
			        MetalLookAndFeel metal = new MetalLookAndFeel();
			        MetalLookAndFeel.setCurrentTheme(theme);
			        UIManager.setLookAndFeel(metal);
			      }else {
			            SkinLookAndFeel.setSkin(SkinLookAndFeel.loadThemePack(args));
			            UIManager.setLookAndFeel("com.l2fprod.gui.plaf.skin.SkinLookAndFeel");
			          }	
				
		}catch(Exception e){
		}
		
	}
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception{	
		
		 try{
			   SunoneLogic.getInstance().init();
			   FileEdited.restoreSunoneParameters(configuration); 
			   }
		 catch(Exception ex){}
		lookAndFeel(lf);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try{
				     thisClass = new GUI();
				    thisClass.addWindowListener(new WindowListener(){
				    	  public void windowClosing(WindowEvent e){
				   		   try{
				   		   FileEdited.saveSunoneParameters(configuration);
				   		   }catch(Exception ex){}
				   		   System.exit(0);
				   	   }
				   	   public void windowClosed(WindowEvent e){}
				   	   public void windowDeactivated(WindowEvent e){}
				   	   public void windowIconified(WindowEvent e){}
				   	   public void windowOpened(WindowEvent e){
				   		   boolean b=Lecteur.REPEATPLAYLIST==Lecteur.ACTIVE?true:false;
				   		   thisClass.repeatPlaylist.setState(b);
				   		    b=Lecteur.REPEATMEDIA==Lecteur.ACTIVE?true:false;
				   		   thisClass.repeatTrack.setState(b);
				   		   
				   	   }
				   	   public void windowDeiconified(WindowEvent e){}
				          public void windowActivated(WindowEvent e){}	   

				    	
				    });
				     thisClass.setVisible(true);
				     
				}
				catch(Exception e){}
			}
		});
		
	}

	public GUI() throws Exception{
		super();
		initialize();
	}
	@SuppressWarnings("deprecation")
	private void initialize2()throws Exception{
		String s=Lecteur.CURRENTPLAYLIST.NAME.substring(0,Lecteur.CURRENTPLAYLIST.NAME.length()-20);
		File[] f=new File(s).listFiles();
		int k=0;
		if(f!=null)
		for(int i=0;i<f.length;i++)
		{  
			if(f[i].toURL().getPath().toString().equals(Lecteur.CURRENTPLAYLIST.NAME)==false)
			{
				createOpenItem(f[i].toString()); 
			    createdeleteItem(f[i].toString());
		    }
			else
				k=+1;
			}
		if(k==0){
			BufferedReader br=new BufferedReader(new FileReader(new File(configuration.getString("com.sunone.playlist.currentplaylist"))));
		    br.close();	
		}
	}
	@SuppressWarnings("deprecation")
	private void initialize() throws Exception{
		if(logger.isDebugEnabled())
			logger.debug("Initialising player.");
		this.setSize(765, 650);
		this.setResizable(false);
		this.setMinimumSize(new Dimension(765, 650));
		this.setMaximumSize(new Dimension(765, 7540));
		logger.info("--Adding sunone backgroud image.");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(configuration.getString("com.sunone.images.sunone"))));
		logger.info("--Adding current track indicator");
		fleche=new ImageIcon(getClass().getResource(configuration.getString("com.sunone.images.fleche")));	
		logger.info("--Setting sunone background color : 204, 255, 224.");
		this.setBackground(new Color(204, 255, 224));
		logger.info("--Retrieving current playlist name.");
		String st=new File(configuration.getString("com.sunone.playlist.currentplaylist")).toURL().getPath().toString();
		Lecteur.CURRENTPLAYLIST=new Playlist(st);
		logger.info("--Lodding Principal panel...");
		this.setContentPane(getJContentPane());
		this.setJMenuBar(getJJMenuBar());
		logger.info("--Setting title \"SunOne  Player\"...");
		this.setTitle("SunOne  Player");
		initialize2();
	}
	//grand panel de SunOne Player
	private Container getJContentPane() throws Exception{
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.setBackground(new Color(204, 255, 224));
			jContentPane.setFont(new Font("Dialog", Font.PLAIN, 12));
			//ajout du panneau gauche et du panneau contenant la table
			jContentPane.add(getJPanel(), BorderLayout.NORTH);
			//ajout du panneau pour l'affichage de la video entre autre
			//jContentPane.add(getJPanel4(), BorderLayout.CENTER);
		}
		return jContentPane;
	}
	 
}  
 class FiltreMultiple extends FileFilter{
	 //Description et extension acceptée par le filtre
	private String description;
	private String extension;
	private ArrayList<String> acceptedExtensions = new ArrayList<String>();
	
		@SuppressWarnings("unchecked")
		public FiltreMultiple(String description, String extensions){
		if(description == null || extensions ==null){
			throw new NullPointerException("La description (ou extension) ne peut être null.");
		}
		
		this.description = description;
		this.extension = extensions;
		 
		StringTokenizer token = new StringTokenizer(extension,",");
		 
		while(token.hasMoreTokens()){
			acceptedExtensions.add(token.nextToken());
		}
	}
 
	/**
	* Méthode qui dit si un fichier doit etre affiché ou pas en fonction de son extension
	* @param file Fichier qui demande à etre accepté ou pas
	*/
	public boolean accept(File file){
		String nomFichier = file.getName().toLowerCase(); 
		if(file.isDirectory()) { 
			return true; 
		} 
			else{
				for(short i = 0; i < acceptedExtensions.size();i++){
				if(nomFichier.endsWith((String)acceptedExtensions.get(i)))
					return true;
			}
		}
		
		return nomFichier.endsWith((String)acceptedExtensions.get(acceptedExtensions.size()-1));
	}
 
	/**
	* Méthode qui retourne la description du filtre
	*/
	public String getDescription(){
		return description;
	}
}//  @jve:decl-index=0:visual-constraint="-31,-15"
