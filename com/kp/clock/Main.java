package com.kp.clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main
{
	private static JPopupMenu popupMenu;
	
	public static void main(String[] args)
	{
		ClockPanel clockPanel = new ClockPanel();
		FrameMouseListener frameMouseListener = new FrameMouseListener();
		MenuMouseListener menuMouseListener = new MenuMouseListener();
		popupMenu = new JPopupMenu();
		JMenuItem closeMenuItem = new JMenuItem("Close");
		JFrame frame = new JFrame();
		
		frame.setName("frame");
		frame.setTitle("Clock");
		frame.setSize(400,400);
		frame.setUndecorated(true); //フレームを消す
		frame.setBackground(new Color(0,0,0,0)); //RGBA 黒で完全に透明
		frame.setAlwaysOnTop(true);
		
		clockPanel.setScale(200);
		clockPanel.setCenter(200,200);
		
		frame.add(clockPanel);
		frame.addMouseListener(frameMouseListener);
		frame.addMouseMotionListener(frameMouseListener);
		
		closeMenuItem.setName("closeMenuItem");
		closeMenuItem.addMouseListener(menuMouseListener);
		popupMenu.add(closeMenuItem);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	static class FrameMouseListener implements MouseListener,MouseMotionListener //2種のMouseListenerをまとめて実装(良いのかこれ)
	{
		private Point prevPos;
		private int btn;
		
		public FrameMouseListener()
		{
			super();
		}
		
		public void mouseClicked(MouseEvent e)
		{
			if(btn == e.BUTTON3)
			{
				popupMenu.show(e.getComponent(),e.getX(),e.getY());//クリックされた位置にメニューを表示
			}
		}
		
		public void mousePressed(MouseEvent e)
		{
			btn = e.getButton(); //押されてるボタンをメモ
			if(btn == e.BUTTON1)
			{
				prevPos = e.getPoint(); //移動前のマウス座標を取得
			}
		}
		
		public void mouseReleased(MouseEvent e){}
		
		public void mouseEntered(MouseEvent e){}
		
		public void mouseExited(MouseEvent e){}
		
		public void mouseDragged(MouseEvent e)
		{
			if(btn == MouseEvent.BUTTON1) //メモを参照 (何故かこのメソッドからgetButton()すると通らなかった)
			{
				Point pos = e.getPoint(); //移動後のマウス座標を取得
				e.getComponent().setLocation(e.getComponent().getX()+pos.x-prevPos.x,e.getComponent().getY()+pos.y-prevPos.y); //マウスの移動分だけ移動
			}
		}
		
		public void mouseMoved(MouseEvent e){}
	}
	
	static class MenuMouseListener implements MouseListener
	{
		public MenuMouseListener()
		{
			super();
		}
		
		public void mouseClicked(MouseEvent e){}
		
		public void mousePressed(MouseEvent e)
		{
			if((e.getButton() == e.BUTTON1)&&(e.getComponent().getName()=="closeMenuItem"))
			{
				System.exit(0);
			}
		}
		
		public void mouseReleased(MouseEvent e){}
		
		public void mouseEntered(MouseEvent e){}
		
		public void mouseExited(MouseEvent e){}
	}
}
