import javax.swing.JFrame;

public class Main
{
	
	private static Environment environment;

	public static void main( String[] args )
	{

		// create a new display panel
		environment = new Environment();
		environment.setBounds(0, 0, Config.DISPLAY_WIDTH, Config.DISPLAY_HEIGHT);
		
		// create the top level window
		JFrame window = new JFrame( "Team Ares - Project 5" );
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		window.setContentPane( environment );
		window.setSize( 800, 872 );
		window.setLocation( 100, 100 );
		window.setVisible( true );
		window.setResizable( false );
	}

}
