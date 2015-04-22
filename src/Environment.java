import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;


public class Environment extends JPanel implements ActionListener{

	private DisplayPanel displayPanel;
	
	private ControlPanel controlPanel;
	
	public Environment(){
		// set the laout for thie topmost view
		this.setLayout(new BorderLayout());
		
		// create display panel and attach mouse listeners
		displayPanel = new DisplayPanel();
		MouseControl mControl = new MouseControl();
		displayPanel.addMouseListener(mControl);
		displayPanel.addMouseMotionListener(mControl);
		
		// create the control panel
		controlPanel = new ControlPanel(this);
		
		// add control panel and display to this view
		this.add(displayPanel, BorderLayout.CENTER);
		this.add(controlPanel, BorderLayout.SOUTH);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		displayPanel.onSimulate();
	}
	
	private class MouseControl extends MouseInputAdapter{
		public void mousePressed(MouseEvent e) {
			displayPanel.onMousePressed( e.getX(), e.getY(), controlPanel.getCurrentToggle() );
	    }

	    public void mouseDragged(MouseEvent e) {
	    	displayPanel.onMouseDragged( e.getX(), e.getY(), controlPanel.getCurrentToggle()  );
	    }

	    public void mouseReleased(MouseEvent e) {
	    	displayPanel.onMouseReleased( e.getX(), e.getY(), controlPanel.getCurrentToggle()  );
	    }
	}
}
