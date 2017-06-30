package com.kp.clock;

import java.util.*;
import javax.swing.JPanel;
import java.awt.*;

public class ClockPanel extends JPanel
{
	public ClockPanel()
	{
		super();
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new ClockTimerTask(),0,50);
	}
	
	public void update()
	{
		repaint();
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		super.setOpaque(false); //”wŒi‚ğ“§–¾‚É
		super.paint(g);
		
		int rs = 180;
		int rm = 120;
		int rh = 80;
		
		Calendar now = Calendar.getInstance();
		
		int sec = now.get(Calendar.SECOND);
		int min = now.get(Calendar.MINUTE);
		int hour = now.get(Calendar.HOUR);
		
		double ts = 2*Math.PI*((sec-15)%60)/60; //ƒÆs
		double tm = 2*Math.PI*((min-15)%60)/60;
		double th = 2*Math.PI*((hour-3)%12)/12;
		
		tm += ts/60; //Sec, Min ‚É‚æ‚éjˆÊ’u‚Ì•â³
		th += tm/12;
		
		g.clearRect(0,0,400,400);
		g2.setStroke(new BasicStroke(2.0f)); //ü‚Ì‘¾‚³‚ğ•ÏX
		
		g.setColor(new Color(255,255,255,63)); //”’‚Å”¼“§–¾
		g.fillOval(10,10,380,380);
		g.setColor(new Color(0,0,0,255));
		g.drawOval(10,10,380,380);
		
		g.drawLine(200,200,(int)(rs*Math.cos(ts))+200,(int)(rs*Math.sin(ts))+200);
		g.drawLine(200,200,(int)(rm*Math.cos(tm))+200,(int)(rm*Math.sin(tm))+200);
		g.drawLine(200,200,(int)(rh*Math.cos(th))+200,(int)(rh*Math.sin(th))+200);
	}
	
	class ClockTimerTask extends TimerTask
	{
		public void run()
		{
			update();
		}
	}
}
