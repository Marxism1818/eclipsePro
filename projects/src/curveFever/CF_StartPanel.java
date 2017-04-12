package curveFever;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CF_StartPanel extends JPanel{

	private JButton selectedButton = null;
	private int num = 2;
	
	public CF_StartPanel(CF frame)
	{
		setBackground(Color.black);
		JPanel mainPanel = this;
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.black);
		panel.setLayout(new GridLayout(num+1, 3));
		panel.setPreferredSize(new Dimension(frame.getWidth(), 700));
		
		JPanel head = new JPanel();
		head.setBackground(Color.BLACK);
		head.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()-panel.getPreferredSize().height));
		
		JLabel p1 = new JLabel("p1", JLabel.CENTER);
		p1.setForeground(Color.red);
		p1.setFont(new Font("a", Font.BOLD, 35));
		JLabel p2 = new JLabel("p2", JLabel.CENTER);
		p2.setForeground(Color.blue);
		p2.setFont(new Font("a", Font.BOLD, 35));
		JLabel blank = new JLabel();
		
		JButton p1l = new JButton(); 
		p1l.setForeground(Color.red);
		p1l.setFont(new Font("a", Font.BOLD, 30));
		p1l.addActionListener(new ButtonAction(p1l));
		
		JButton p1r = new JButton(); 
		p1r.setForeground(Color.red);
		p1r.setFont(new Font("a", Font.BOLD, 30));
		p1r.addActionListener(new ButtonAction(p1r));
		
		JButton p2l = new JButton(); 
		p2l.setForeground(Color.blue);
		p2l.setFont(new Font("a", Font.BOLD, 30));
		p2l.addActionListener(new ButtonAction(p2l));
		
		JButton p2r = new JButton();
		p2r.setForeground(Color.blue);
		p2r.setFont(new Font("a", Font.BOLD, 30));
		p2r.addActionListener(new ButtonAction(p2r));
		
		JButton start = new JButton("start");
		start.setBackground(Color.yellow);
		start.setFont(new Font("a", Font.BOLD, 30));
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPanel);
				frame.add(new CF_Panel(frame, p1l.getText(), p1r.getText(), p2l.getText(), p2r.getText()));
				frame.validate();
			}
		});
		
		JLabel numOfPlayers = new JLabel(Integer.toString(num));
		numOfPlayers.setForeground(Color.GREEN);
		numOfPlayers.setFont(new Font("a", Font.BOLD, 30));
		
		JButton add = new JButton("+1");
		add.setBackground(Color.GREEN);
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(num < 2)
					num++;
				numOfPlayers.setText(Integer.toString(num));
				panel.setLayout(new GridLayout(num+1, 3));
				panel.removeAll();
				panel.add(p1);
				panel.add(p1l);
				panel.add(p1r);
				if(num>1)
				{
					panel.add(p2);
					panel.add(p2l);
					panel.add(p2r);
				}
				panel.add(blank);
				panel.add(start);
			}
		});
		
		JButton decrease = new JButton("-1");
		decrease.setBackground(Color.GREEN);
		decrease.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(num > 1)
					num--;
				numOfPlayers.setText(Integer.toString(num));
				panel.setLayout(new GridLayout(num+1, 3));
				panel.removeAll();
				panel.add(p1);
				panel.add(p1l);
				panel.add(p1r);
				if(num>1)
				{
					panel.add(p2);
					panel.add(p2l);
					panel.add(p2r);
				}
				panel.add(blank);
				panel.add(start);
			}
		});
		
		panel.add(p1);
		panel.add(p1l);
		panel.add(p1r);
		panel.add(p2);
		panel.add(p2l);
		panel.add(p2r);
		panel.add(blank);
		panel.add(start);
		head.add(add);
		head.add(numOfPlayers);
		head.add(decrease);
		add(head);
		add(panel);
		
		frame.addKeyListener(new KeyAction());
	}

	class KeyAction implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e) {
			if(selectedButton != null)
			{
				selectedButton.setText(KeyEvent.getKeyText(e.getKeyCode()));
				selectedButton.setBackground(Color.black);
				selectedButton = null;
			}
				
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			
		}
		
	}
	
	class ButtonAction implements ActionListener
	{
		private JButton b;
		public ButtonAction(JButton b)
		{
			this.b = b;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(selectedButton != b)
			{
				if(selectedButton != null)
					selectedButton.setBackground(Color.black);
				
				selectedButton = b;
				b.setBackground(Color.green);
			}
			else
			{
				selectedButton = null;
				b.setBackground(Color.BLACK);
			}
			getTopLevelAncestor().requestFocus();
		}
	}
}
