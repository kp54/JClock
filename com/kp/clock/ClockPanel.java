package com.kp.clock;

import java.util.*;
import javax.swing.JPanel;
import java.awt.*;

public class ClockPanel extends JPanel
{
	private Point center;
	private double scale;
	
	public ClockPanel()
	{
		super();
		
		center = new Point(200,200);
		scale = 1;
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new ClockTimerTask(),0,50);
	}
	
	public void setScale(int r)
	{
		scale = r / (double)200;
	}
	
	public void setCenter(int cx, int cy)
	{
		center = new Point(cx,cy);
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

		int rs = (int)(scale*180);
		int rm = (int)(scale*120);
		int rh = (int)(scale*80);
		int rc = (int)(scale*190);

		Calendar now = Calendar.getInstance();
	
		int msec = now.get(Calendar.MILLISECOND);
		int sec = now.get(Calendar.SECOND);
		int min = now.get(Calendar.MINUTE);
		int hour = now.get(Calendar.HOUR);
		
		double tms = 2*Math.PI*((msec-250)%1000)/1000;
		double ts = 2*Math.PI*((sec-15)%60)/60; //ƒÆs
		double tm = 2*Math.PI*((min-15)%60)/60;
		double th = 2*Math.PI*((hour-3)%12)/12;
		
		ts += tms/60;
		tm += ts/60; //Sec, Min ‚É‚æ‚éjˆÊ’u‚Ì•â³
		th += tm/12;

		g.clearRect(0,0,400,400);
		g2.setStroke(new BasicStroke(2.0f)); //ü‚Ì‘¾‚³‚ğ•ÏX

		g.setColor(new Color(255,255,255,63)); //”’‚Å”¼“§–¾
		g.fillOval(center.x-rc,center.y-rc,rc*2,rc*2);
		g.setColor(new Color(0,0,0,255));
		g.drawOval(center.x-rc,center.y-rc,rc*2,rc*2);

		g.drawLine(center.x,center.y,(int)(rs*Math.cos(ts))+center.x,(int)(rs*Math.sin(ts))+center.y);
		g.drawLine(center.x,center.y,(int)(rm*Math.cos(tm))+center.x,(int)(rm*Math.sin(tm))+center.y);
		g.drawLine(center.x,center.y,(int)(rh*Math.cos(th))+center.x,(int)(rh*Math.sin(th))+center.y);
	}

	class ClockTimerTask extends TimerTask
	{
		public void run()
		{
			update();
		}
	}
}
