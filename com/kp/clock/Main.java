package com.kp.clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main
{
	//なにか編集する
	private static ClockPanel clockPanel;
	private static JPopupMenu popupMenu;
	private static JFrame frame;
	
	public static void main(String[] args)
	{
		String[] opacitySubmenuTexts = new String[]{"0%","25%","50%","75%","100%"};
		String[] sizeSubmenuTexts = new String[]{"Small","Middle","Large"};
		
		clockPanel = new ClockPanel();
		FrameMouseListener frameMouseListener = new FrameMouseListener();
		MenuActionListener menuActionListener = new MenuActionListener();
		OpacitySubmenuActionListener opacitySubmenuActionListener = new OpacitySubmenuActionListener();
		SizeSubmenuActionListener sizeSubmenuActionListener = new SizeSubmenuActionListener();
		popupMenu = new JPopupMenu();
		JMenuItem closeMenuItem = new JMenuItem("Close");
		JCheckBoxMenuItem alwaysOnTopCBMenuItem = new JCheckBoxMenuItem("Always on top");
		JMenu opacitySubmenu = new JMenu("Opacity");
		ButtonGroup opacityRBGroup = new ButtonGroup();
		JRadioButtonMenuItem[] opacityRBMenuItems = new JRadioButtonMenuItem [5];
		JMenu sizeSubmenu = new JMenu("Size");
		ButtonGroup sizeRBGroup = new ButtonGroup();
		JRadioButtonMenuItem[] sizeRBMenuItems = new JRadioButtonMenuItem [3];
		
		frame = new JFrame();
		
		frame.setName("frame");
		frame.setTitle("Clock");
		frame.setSize(600,600);
		frame.setUndecorated(true);
		frame.setBackground(new Color(0,0,0,0));
		frame.setAlwaysOnTop(true);
		
		clockPanel.setScale(200);
		clockPanel.setCenter(300,300);
		clockPanel.setOpacity(0.25);
		
		frame.add(clockPanel);
		frame.addMouseListener(frameMouseListener);
		frame.addMouseMotionListener(frameMouseListener);
		
		closeMenuItem.addActionListener(menuActionListener);
		
		alwaysOnTopCBMenuItem.addActionListener(menuActionListener);
		alwaysOnTopCBMenuItem.setState(true);
		
		for(int i=0;i<5;i++)
		{
			opacityRBMenuItems[i] = new JRadioButtonMenuItem(opacitySubmenuTexts[i]);
			opacityRBMenuItems[i].addActionListener(opacitySubmenuActionListener);
			opacityRBGroup.add(opacityRBMenuItems[i]);
			opacitySubmenu.add(opacityRBMenuItems[i]);
		}
		opacityRBMenuItems[1].setSelected(true);
		
		for(int i=0;i<3;i++)
		{
			sizeRBMenuItems[i] = new JRadioButtonMenuItem(sizeSubmenuTexts[i]);
			sizeRBMenuItems[i].addActionListener(sizeSubmenuActionListener);
			sizeRBGroup.add(sizeRBMenuItems[i]);
			sizeSubmenu.add(sizeRBMenuItems[i]);
		}
		sizeRBMenuItems[1].setSelected(true);
		
		popupMenu.add(alwaysOnTopCBMenuItem);
		popupMenu.add(opacitySubmenu);
		popupMenu.add(sizeSubmenu);
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
			if(btn == MouseEvent.BUTTON3)
			{
				popupMenu.show(frame,e.getX(),e.getY()); //クリックされた位置にメニューを表示
			}
		}
		
		public void mousePressed(MouseEvent e)
		{
			btn = e.getButton(); //押されてるボタンをメモ
			if(btn == MouseEvent.BUTTON1)
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
				frame.setLocation(frame.getX()+pos.x-prevPos.x,frame.getY()+pos.y-prevPos.y); //マウスの移動分だけ移動
			}
		}
		
		public void mouseMoved(MouseEvent e){}
	}
	
	static class MenuActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			switch(e.getActionCommand())
			{
				case "Close":
					System.exit(0);
					break;
				
				case "Always on top":
					frame.setAlwaysOnTop(((JCheckBoxMenuItem)(e.getSource())).getState()); //alwaysOnTopCBMenuItemを取得->stateを取得
					break;
			}
		}
	}
	
	static class OpacitySubmenuActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			switch(e.getActionCommand())
			{
				case "0%":
					clockPanel.setOpacity(0.01);
					break;
				
				case "25%":
					clockPanel.setOpacity(0.25);
					break;
				
				case "50%":
					clockPanel.setOpacity(0.50);
					break;
				
				case "75%":
					clockPanel.setOpacity(0.75);
					break;
				
				case "100%":
					clockPanel.setOpacity(1);
					break;
			}
		}
	}
	
	static class SizeSubmenuActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			switch(e.getActionCommand())
			{
				case "Small":
					clockPanel.setScale(100);
					break;
				
				case "Middle":
					clockPanel.setScale(200);
					break;
				
				case "Large":
					clockPanel.setScale(300);
					break;
			}
		}
	}
}
