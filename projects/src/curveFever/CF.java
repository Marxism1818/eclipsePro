package curveFever;

import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class CF extends JFrame{

	CF()
	{	
		CF_Panel panel = new CF_Panel(this, "a", "d", "left", "right");
		add(panel);
		
		setTitle("Curvez");						
		setResizable(false);					
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());				
		setLocationRelativeTo(null);
		setVisible(true);			
				
		validate();
	}
	
	public static void main(String[] args) {
		new CF();
	}

}
