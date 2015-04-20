import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;


public class Environment extends JPanel implements ActionListener{

	private DisplayPanel displayPanel;
	
	public Environment(){
		this.setLayout(new BorderLayout());
		displayPanel = new DisplayPanel();
		this.add(displayPanel, BorderLayout.CENTER);
		this.add(new ControlPanel(this), BorderLayout.SOUTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		
		displayPanel.repaint();
	}
}
