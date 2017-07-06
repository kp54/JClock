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
		frame.setUndecorated(true); //�t���[��������
		frame.setBackground(new Color(0,0,0,0)); //RGBA ���Ŋ��S�ɓ���
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
	
	static class FrameMouseListener implements MouseListener,MouseMotionListener //2���MouseListener���܂Ƃ߂Ď���(�ǂ��̂�����)
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
				popupMenu.show(e.getComponent(),e.getX(),e.getY());//�N���b�N���ꂽ�ʒu�Ƀ��j���[��\��
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
				e.getComponent().setLocation(e.getComponent().getX()+pos.x-prevPos.x,e.getComponent().getY()+pos.y-prevPos.y); //�}�E�X�̈ړ��������ړ�
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
