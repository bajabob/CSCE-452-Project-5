import java.awt.Color;
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


public class ControlPanel extends JPanel implements ActionListener{

	public static final String TOGGLE_DROP_START_POINT = "toggleDropStartPoint";
	public static final String TOGGLE_DROP_END_POINT = "toggleDropEndPoint";
	public static final String TOGGLE_MOVE_OBSTACLES = "toggleMoveObstacles";
	public static final String ACTION_SIMULATE = "actionSimulate";
	
	/**
	 * runs the path finding simulation
	 */
	private JButton buttonSimulate;
	
	/**
	 * radio buttons for toggling UI controls
	 */
	private JRadioButton toggleDropStartPoint, toggleDropEndPoint, toggleMoveObstacles;
		
	
	/**
	 * Current selected toggle
	 */
	private String currentToggle = TOGGLE_DROP_START_POINT;
	
	
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
		toggleMoveObstacles = new JRadioButton("Move Obstacles");
		toggleMoveObstacles.setActionCommand(TOGGLE_MOVE_OBSTACLES);
		
		ButtonGroup toggles = new ButtonGroup();
		toggles.add(toggleDropStartPoint);
		toggles.add(toggleDropEndPoint);
		toggles.add(toggleMoveObstacles);
		
		buttonSimulate = new JButton("Simulate");
		buttonSimulate.setActionCommand(ACTION_SIMULATE);
		
		// add action listeners
		// toggles callback locally
		toggleDropStartPoint.addActionListener(this);
		toggleDropEndPoint.addActionListener(this);
		toggleMoveObstacles.addActionListener(this);
		// simulate calls-back to Environment
		buttonSimulate.addActionListener(ae);
		
		// add components to this panel
		this.add(Box.createHorizontalBox());
		this.add(Box.createHorizontalGlue());
		this.add(toggleDropStartPoint);
		this.add(toggleDropEndPoint);
		this.add(toggleMoveObstacles);
		this.add(buttonSimulate);
		this.add(Box.createHorizontalGlue());
	}

	
	/**
	 * Get the current selected toggle
	 * @return String
	 */
	public String getCurrentToggle(){
		return this.currentToggle;
	}
	
	@Override
	public void actionPerformed( ActionEvent e )
	{
		currentToggle = e.getActionCommand();
	}

}
