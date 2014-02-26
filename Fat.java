package scr.Gui;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Dialogs;

/*
 * fat class opens a dialog box, which allows the user to 
 * ask the coach a question, and than get a respounce from the
 * from the coach
 */
public class Fat 
 extends Application 
{
	private Stage mStager;
	
	/*
	 * allows this Fat class to function by itself
	 */
	public static void main(String[] args)
	{
		launch(args);
	}
	
	/*
	 * (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	public void start(Stage stager)
	{
		stager.setTitle("Lessons from coach");	
		Button btn1 = new Button();
		btn1.setText("Ask");
		btn1.setOnAction(
		 new EventHandler<ActionEvent>()
		 {
			 @Override
			 public void handle(ActionEvent event)
			 {
				 Stage stager = getStage();
				 askQuestion(stager);
				 try
				 {
					 Thread.sleep(1500);
				 }
				 catch (Exception e)
				 {
					 
				 }
				 getAnswer(stager);
			 }
		 });
		StackPane root = new StackPane();
		root.getChildren().add(btn1);
		stager.setScene(new Scene(root, 50, 50));
		mStager = stager;
		stager.show();
	}
	
	/*
	 * ask the question, by opening a dialog box
	 */
	public void askQuestion(Stage st)
	{
		Dialogs.showInformationDialog(
				st, "Athlete","Coach what should I do?", "Alert");
	}
	
	/*
	 * gets the answer back from the coach, and shows it in a dialog box
	 */
	public void getAnswer(Stage st)
	{
		Dialogs.showInformationDialog(
				st, "Coach","Run another lap", "Alert");
	}
	
	/*
	 * returns the stage being used
	 */
	public Stage getStage()
	{
		return mStager;
	}
}
