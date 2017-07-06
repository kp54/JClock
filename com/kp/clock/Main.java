package com.kp.clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main
{
	private static ClockPanel clockPanel;
	private static JPopupMenu popupMenu;
	private static JFrame frame;
	
	public static void main(String[] args)
	{
		clockPanel = new ClockPanel();
		FrameMouseListener frameMouseListener = new FrameMouseListener();
		MenuActionListener menuActionListener = new MenuActionListener();
		OpacitySubmenuActionListener opacitySubmenuActionListener = new OpacitySubmenuActionListener();
		popupMenu = new JPopupMenu();
		JMenuItem closeMenuItem = new JMenuItem("Close");
		JCheckBoxMenuItem alwaysOnTopCBMenuItem = new JCheckBoxMenuItem("Always on top");
		JMenu opacitySubmenu = new JMenu("Opacity");
		ButtonGroup opacityRBGroup = new ButtonGroup();
		JRadioButtonMenuItem opacity0RBMenuItem = new JRadioButtonMenuItem("0%");
		JRadioButtonMenuItem opacity1RBMenuItem = new JRadioButtonMenuItem("25%");
		JRadioButtonMenuItem opacity2RBMenuItem = new JRadioButtonMenuItem("50%");
		JRadioButtonMenuItem opacity3RBMenuItem = new JRadioButtonMenuItem("75%");
		JRadioButtonMenuItem opacity4RBMenuItem = new JRadioButtonMenuItem("100%");
		frame = new JFrame();
		
		frame.setName("frame");
		frame.setTitle("Clock");
		frame.setSize(400,400);
		frame.setUndecorated(true); //�t���[��������
		frame.setBackground(new Color(0,0,0,0)); //RGBA
		frame.setAlwaysOnTop(true);
		
		clockPanel.setScale(200);
		clockPanel.setCenter(200,200);
		clockPanel.setOpacity(0.25);
		
		frame.add(clockPanel);
		frame.addMouseListener(frameMouseListener);
		frame.addMouseMotionListener(frameMouseListener);
		
		closeMenuItem.addActionListener(menuActionListener);
		
		alwaysOnTopCBMenuItem.addActionListener(menuActionListener);
		alwaysOnTopCBMenuItem.setState(true);
		
		opacity0RBMenuItem.addActionListener(opacitySubmenuActionListener);
		opacity1RBMenuItem.addActionListener(opacitySubmenuActionListener);
		opacity2RBMenuItem.addActionListener(opacitySubmenuActionListener);
		opacity3RBMenuItem.addActionListener(opacitySubmenuActionListener);
		opacity4RBMenuItem.addActionListener(opacitySubmenuActionListener);
		
		opacityRBGroup.add(opacity0RBMenuItem);
		opacityRBGroup.add(opacity1RBMenuItem);
		opacityRBGroup.add(opacity2RBMenuItem);
		opacityRBGroup.add(opacity3RBMenuItem);
		opacityRBGroup.add(opacity4RBMenuItem);
		
		opacitySubmenu.add(opacity0RBMenuItem);
		opacitySubmenu.add(opacity1RBMenuItem);
		opacitySubmenu.add(opacity2RBMenuItem);
		opacitySubmenu.add(opacity3RBMenuItem);
		opacitySubmenu.add(opacity4RBMenuItem);
		
		opacity1RBMenuItem.setSelected(true);
		
		popupMenu.add(alwaysOnTopCBMenuItem);
		popupMenu.add(opacitySubmenu);
		popupMenu.add(closeMenuItem);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	static class FrameMouseListener implements MouseListener,MouseMotionListener
	//2���MouseListener���܂Ƃ߂Ď���(�ǂ��̂�����)
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
				popupMenu.show(frame,e.getX(),e.getY());//�N���b�N���ꂽ�ʒu�Ƀ��j���[��\��
			}
		}
		
		public void mousePressed(MouseEvent e)
		{
			btn = e.getButton(); //������Ă�{�^��������
			if(btn == e.BUTTON1)
			{
				prevPos = e.getPoint(); //�ړ��O�̃}�E�X���W���擾
			}
		}
		
		public void mouseReleased(MouseEvent e){}
		
		public void mouseEntered(MouseEvent e){}
		
		public void mouseExited(MouseEvent e){}
		
		public void mouseDragged(MouseEvent e)
		{
			if(btn == MouseEvent.BUTTON1) //�������Q�� (���̂����̃��\�b�h����getButton()����ƒʂ�Ȃ�����)
			{
				Point pos = e.getPoint(); //�ړ���̃}�E�X���W���擾
				frame.setLocation(frame.getX()+pos.x-prevPos.x,frame.getY()+pos.y-prevPos.y);
				//�}�E�X�̈ړ��������ړ�
			}
		}
		
		public void mouseMoved(MouseEvent e){}
	}
	
	static class MenuActionListener implements ActionListener
	{
		public MenuActionListener()
		{
			super();
		}
		
		public void actionPerformed(ActionEvent e)
		{
			switch(e.getActionCommand())
			{
				case "Close":
					System.exit(0);
					break;
				
				case "Always on top":
					frame.setAlwaysOnTop(((JCheckBoxMenuItem)(e.getSource())).getState());
					//alwaysOnTopCBMenuItem���擾->state���擾
					break;
			}
		}
	}
	
	static class OpacitySubmenuActionListener implements ActionListener
	{
		public OpacitySubmenuActionListener()
		{
			super();
		}
		
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
}
