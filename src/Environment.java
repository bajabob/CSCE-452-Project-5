import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;


public class Environment extends JPanel implements ActionListener{

	private DisplayPanel displayPanel;
	
	public Environment(){
		// set the laout for thie topmost view
		this.setLayout(new BorderLayout());
		
		// create display panel and attach mouse listeners
		displayPanel = new DisplayPanel();
		MouseControl mControl = new MouseControl();
		displayPanel.addMouseListener(mControl);
		displayPanel.addMouseMotionListener(mControl);
		
		// add control panel and display to this view
		this.add(displayPanel, BorderLayout.CENTER);
		this.add(new ControlPanel(this), BorderLayout.SOUTH);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());

	}
	
	private class MouseControl extends MouseInputAdapter{
		public void mousePressed(MouseEvent e) {
			displayPanel.onMousePressed( e.getX(), e.getY() );
	    }

	    public void mouseDragged(MouseEvent e) {
	    	displayPanel.onMouseDragged( e.getX(), e.getY() );
	    }

	    public void mouseReleased(MouseEvent e) {
	    	displayPanel.onMouseReleased( e.getX(), e.getY() );
	    }
	}
}
