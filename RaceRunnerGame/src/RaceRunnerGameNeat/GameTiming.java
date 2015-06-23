package RaceRunnerGameNeat;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class GameTiming {
	static int interval;
	static Timer timer;
	String secs = "";
	int delay = 1000;
	int period = 1000;
	JLabel label;
	
	public void setTimer(String secs){
		this.secs = secs;
	}
	
	public GameTiming(JLabel label){
		this.label = label;
	}
	
	public String getTimer(){
		return this.secs;
	}
	
	public void runTimer(){
		timer = new Timer();
		interval = Integer.parseInt(secs);
		timer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				label.setText("Time Left: "+String.valueOf(setInterval()));
			}
		}, delay, period);
	}
	
	private static final int setInterval() {
		if (interval == 1)
			timer.cancel();
		return --interval;
	}

}
