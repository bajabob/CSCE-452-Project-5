import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class ControlPanel extends JPanel{

	public static final String TOGGLE_DROP_START_POINT = "toggleDropStartPoint";
	public static final String TOGGLE_DROP_END_POINT = "toggleDropEndPoint";
	public static final String TOGGLE_MOVE_BOXES = "toggleMoveBoxes";
	public static final String ACTION_SIMULATE = "actionSimulate";
	
	/**
	 * runs the path finding simulation
	 */
	JButton buttonSimulate;
	
	/**
	 * radio buttons for toggling UI controls
	 */
	JRadioButton toggleDropStartPoint, toggleDropEndPoint, toggleMoveBoxes;
		
	public ControlPanel(ActionListener ae) {
		super();
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.black)); 
		this.setPreferredSize(new Dimension(300, 50));
		
		toggleDropStartPoint = new JRadioButton("Drop Start Point");
		toggleDropStartPoint.setSelected(true);
		toggleDropStartPoint.setActionCommand(TOGGLE_DROP_START_POINT);
		toggleDropEndPoint = new JRadioButton("Drop End Point");
		toggleDropEndPoint.setActionCommand(TOGGLE_DROP_END_POINT);
		toggleMoveBoxes = new JRadioButton("Move Boxes");
		toggleMoveBoxes.setActionCommand(TOGGLE_MOVE_BOXES);
		
		ButtonGroup toggles = new ButtonGroup();
		toggles.add(toggleDropStartPoint);
		toggles.add(toggleDropEndPoint);
		toggles.add(toggleMoveBoxes);
		
		buttonSimulate = new JButton("Simulate");
		buttonSimulate.setActionCommand(ACTION_SIMULATE);
		
		// create action listeners
		toggleDropStartPoint.addActionListener(ae);
		toggleDropEndPoint.addActionListener(ae);
		toggleMoveBoxes.addActionListener(ae);
		buttonSimulate.addActionListener(ae);
		
		// add components to this panel
		this.add(Box.createHorizontalBox());
		this.add(Box.createHorizontalGlue());
		this.add(toggleDropStartPoint);
		this.add(toggleDropEndPoint);
		this.add(toggleMoveBoxes);
		this.add(buttonSimulate);
		this.add(Box.createHorizontalGlue());
	}

}
