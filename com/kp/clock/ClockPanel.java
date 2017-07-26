package com.kp.clock;

import java.util.*;
import javax.swing.JPanel;
import java.awt.*;

public class ClockPanel extends JPanel
{
	private Point center;
	private double scale;
	private double opacity;
	
	public ClockPanel()
	{
		super();
		
		center = new Point(200,200);
		scale = 1;
		opacity = 0.25;
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new ClockTimerTask(),0,20);
	}
	
	public void setOpacity(double o)
	{
		opacity = o;
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
		super.setOpaque(false);
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
		double ts = 2*Math.PI*((sec-15)%60)/60; //θs
		double tm = 2*Math.PI*((min-15)%60)/60;
		double th = 2*Math.PI*((hour-3)%12)/12;
		
		ts += tms/60;
		tm += ts/60; //Sec, Min による針位置の補正
		th += tm/12;

		g.clearRect(0,0,400,400);
		
		g2.setStroke(new BasicStroke((float)(2.0f*scale))); //文字盤の描画
		g.setColor(new Color(255,255,255,(int)(255*opacity)));
		g.fillOval(center.x-rc,center.y-rc,rc*2,rc*2);
		g.setColor(new Color(0,0,0,255));
		g.drawOval(center.x-rc,center.y-rc,rc*2,rc*2);
		
		g2.setStroke(new BasicStroke((float)(3.0f*scale))); //3,6,9,12時の目盛り
		g.drawLine(center.x-rc,center.y, (int)(center.x-0.9*rc),center.y);
		g.drawLine((int)(center.x+0.9*rc),center.y, center.x+rc,center.y);
		g.drawLine(center.x,center.y-rc,center.x,(int)(center.y-0.9*rc));
		g.drawLine(center.x,(int)(center.y+0.9*rc),center.x,center.y+rc);
		
		g2.setStroke(new BasicStroke((float)(2.0f*scale)));
		g.drawLine(center.x,center.y,(int)(rs*Math.cos(ts))+center.x,(int)(rs*Math.sin(ts))+center.y);
		g.drawLine(center.x,center.y,(int)(rm*Math.cos(tm))+center.x,(int)(rm*Math.sin(tm))+center.y);
		g2.setStroke(new BasicStroke((float)(4.0f*scale))); //時針だけ太く
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
