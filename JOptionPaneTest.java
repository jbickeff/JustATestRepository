package test;
import javax.swing.JOptionPane;

public class JOptionPaneTest 
{
	public static void main(String[] args)
	{
		
	
		Object[] options = {"Ok", "Cancel"};
		JOptionPane pane = new JOptionPane(options);
		pane.setVisible(true);
		pane.showOptionDialog(null, "Click Ok to continue", "Warning",
		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
		options, options[0]);
		String nameF = pane.showInputDialog("Please Type your first name");
		String nameL = pane.showInputDialog("Please type your last name");
	}
}	
