package game.vilage.view.component;

import game.vilage.quests.Quest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class PanelTop extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JProgressBar progressBar;
	private JLabel heading;
	private JLabel startTime;
	
	//CONSTRUCTORS
	
	/**
	 * 
	 */
	public PanelTop(){
		init();
	}
	
	//OTHERS
	
	/**
	 * inicializuje horný panel
	 */
	private void init(){
		setPreferredSize(new Dimension(200,200));
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		add(heading = new JLabel());
		heading.setFont(new Font("Serif", Font.BOLD, 52));
		
		add(startTime = new JLabel());
		startTime.setFont(new Font("Serif", Font.BOLD, 32));
		
		add(progressBar = new JProgressBar(0,100));
		progressBar.setValue(50);
		progressBar.setStringPainted(true);
		progressBar.setPreferredSize(new Dimension(570,30));
		
		setVisible(false);
	}
	
	/**
	 * @param actProgress
	 */
	public void upadeProgress(int actProgress) {
		progressBar.setValue(actProgress);
		progressBar.setString(actProgress+" / "+progressBar.getMaximum());
	}
	
	//SETTERS
	
	/**
	 * @param quest
	 */
	public void setActQuest(Quest quest){
		setVisible(true);
		heading.setText(quest.getTitle());
		startTime.setText("Štart o: "+new SimpleDateFormat("HH:mm  d.M.Y").format(new Date(quest.getTime())));
		progressBar.setMaximum(quest.getSubQestsProgress().size());
		progressBar.setValue(quest.getCompletedSubQuests());
		progressBar.setString(quest.getCompletedSubQuests()+" / "+progressBar.getMaximum());
	}
}
