package curveFever;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class CF_Panel extends JPanel{
	
	private Timer update = new Timer(5, new TimerAction());
	private CurvePlayer p1, p2;
	private CF frame;
	
	public CF_Panel(CF frame, String left1, String right1, String left2, String right2)
	{
		setBackground(Color.BLACK);
		this.frame = frame;
		
		p1 = new CurvePlayer(77,77, Color.red, right1, left1);
		setBindings(p1, "p1");
		p2 = new CurvePlayer(576,432, Color.blue, right2, left2);
		setBindings(p2, "p2");
		
		this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "close");
		this.getActionMap().put("close", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(update.isRunning())
					update.stop();
				frame.dispose();
			}
		});
		
		this.addMouseListener(new MouseAction());
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		p1.drawPlayer(g);
		p2.drawPlayer(g);
	}
	
	public void setBindings(CurvePlayer p, String name)
	{
		InputMap im = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = this.getActionMap();
		
		im.put(KeyStroke.getKeyStroke("pressed " + p.getRight()), name + "rightPressed");
		am.put(name + "rightPressed", new AbstractAction()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) {
						p.setDirection('r');
					}
				}
		);
		
		im.put(KeyStroke.getKeyStroke("released " + p.getRight()), name + "rightReleased");
		am.put(name + "rightReleased", new AbstractAction()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) {
						p.setDirection(' ');
					}
				}
		);
		
		im.put(KeyStroke.getKeyStroke("pressed " + p.getLeft()), name + "leftPressed");
		am.put(name + "leftPressed", new AbstractAction()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) {
						p.setDirection('l');;
					}
				}
		);
		
		im.put(KeyStroke.getKeyStroke("released " + p.getLeft()), name + "leftReleased");
		am.put(name + "leftReleased", new AbstractAction()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) {
						p.setDirection(' ');;
					}
				}
		);
	}
	
	private class TimerAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(p1.active)
				p1.updatePlayer();
			if(p2.active)
				p2.updatePlayer();
			
			if(p1.getHead().getX() > frame.getWidth() || p1.getHead().getY() > frame.getHeight() || p1.getHead().getY() < 0 || p1.getHead().getX() < 0)
				p1.active = false;
			if(p2.getHead().getX() > frame.getWidth() || p2.getHead().getY() > frame.getHeight() || p2.getHead().getY() < 0 || p2.getHead().getX() < 0)
				p2.active = false;
			
			
			for(Rectangle2D p :p1.getPoints())
			{
				if(p2.getHead().intersects(p))
					p2.active = false;
			}
			
			for(Rectangle2D p :p2.getPoints())
			{
				if(p1.getHead().intersects(p))
					p1.active = false;
			}
			
			repaint();
		}
	}
	
	private class MouseAction implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(update.isRunning())
				update.stop();
			else
				update.start();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
}
