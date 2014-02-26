package scr.Gui;
import java.util.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * The Glory Application class that creates
 * and manages the interface for Threads of Glory.
 */
public class Glory
   extends Application
{
   private Stage mPrimaryStage;
   private Group mRoot;
   private Scene mScene;
   private TextField mTextField;

   private GlorifiedList mRunnables;
   private GlorifiedList mRunningThreads;
   private BorderPane mBorderPane;

   private EventHandler<ActionEvent> mStartEventHandler;
   private EventHandler<ActionEvent> mStopEventHandler;
   
   // added by jeff
   private Set<Thread> runningThreads;
   
   /*
    * Starting to set up the singleton template 
    * This static variable is to help set it up 
    */
   private static Glory cInstance = null;
   
  /*
   * This constructor finishes the singleton set up
   */
   public Glory()
   {
	   super();
	   synchronized (Glory.class)
	   {
		   if (cInstance != null)
	         {
	            throw new UnsupportedOperationException(
	               getClass() +
	               " is a singleton but the constructor" +  
	               " is called more than once");
	         }
	         cInstance = this;
	   }
   }

   /*
    * returns the Glory cInstance, so that other threads can 
    * access the Stage
    */
   public static Glory getInstance()
   {
	   return cInstance;
   }
   
   /*
    * Glorified lists allows the user the manage all the load able 
    * threads, and running threads
    */
   private class GlorifiedList
      extends VBox
   {
      private ListView<String> mListView;
      private Label mLabel;
      private Button mButton;

      /*
       * Constructs the glorified list, with the the list box, as well as 
       * buttons title, the label for the list, how the buttons react
       * the handler to tie to the button, and the selection mode   
       */
      public GlorifiedList(String pLabelText,
                           ObservableList<String> pListItems,
                           String pButtonText,
                           EventHandler<ActionEvent> pButtonEventHandler,
                           SelectionMode pSelectionMode)
      {
         mLabel = new Label(pLabelText);

         mListView = new ListView<String>();
         if (pListItems != null)
         {
            mListView.setItems(pListItems);
         }
         mListView.setPrefWidth(200);

         mListView.getSelectionModel().setSelectionMode(
            pSelectionMode);

         mButton = new Button(pButtonText);

         mButton.setOnAction(pButtonEventHandler);
         
         /*
          *  This button is added simply so that i can test to see if what
          *  Threads are running
          */
         Button mTestingButton = new Button("testing");
         mTestingButton.setOnAction(
        		 new EventHandler<ActionEvent>()
        		{
        			public void handle(ActionEvent event)
        			{
        				//Set<Thread> r= Thread.getAllStackTraces().keySet();
        				
        				for(Thread t : runningThreads)
        				{
        					System.out.println(t.getName());
        				}
        			}
        		});
         getChildren().addAll(mLabel, mListView, mButton, mTestingButton);
         setMargin(mLabel, new Insets(10));
         setMargin(mListView, new Insets(20));
         setMargin(mButton, new Insets(10));
         
         /*
          * fitting the new button in
          */
         setMargin(mTestingButton, new Insets(10)); //please remove me
         setAlignment(Pos.CENTER);
      }

      /*
       * adds the name of a thread to the list
       */
      public boolean addToList(String pName)
      {
         if (isInList(pName))
         {
            return false;
         }
         else
         {
            mListView.getItems().add(pName);
            mListView.getSelectionModel().select(pName);
            return true;
         }
      }

      /*
       * removes the name of a thread from the list
       */
      public void removeFromList(String pName)
      {
         if (isInList(pName))
         {
            mListView.getItems().remove(pName);
         }
      }

      /*
       * this allows for more than one string to call the remove from
       * list for more than 
       */
      public void removeFromList()
      {
         for (String name :
                 new ArrayList<String>(
                    mListView.getSelectionModel().getSelectedItems()))
         {
            removeFromList(name);
         }
         mListView.getSelectionModel().selectFirst();
      }

      /*
       * returns the names that are selected
       */
      public String getSelectedItem()
      {
         return mListView.getSelectionModel().getSelectedItem();
      }

      /*
       * checks to see if the string name is in the list or not
       */
      public boolean isInList(String pName)
      {
         return mListView.getItems().contains(pName);
      }

      /*
       * focuses on the button at the bottom of the list
       */
      public void requestButtonFocus()
      {
         mButton.requestFocus();
      }
   }
   
 
   /*
    * creates a dialog that displays 2 messages
    */
   private void showMessage(String pMsg1, String pMsg2)
   {
      Dialogs.showInformationDialog(
         mPrimaryStage, pMsg2, pMsg1, "Alert");
   }

   /*
    * returns the stage so that other threads can access the stage
    */
   public Stage getStage()
   {
	   return mPrimaryStage;
   }
   
   /*
    * (non-Javadoc)
    * @see javafx.application.Application#start(javafx.stage.Stage)
    */
   public void start(Stage pPrimaryStage)
   {
      mPrimaryStage = pPrimaryStage;
      mPrimaryStage.setTitle("Glory");

      /*
       * added so that we can keep track of the threads that are running
       * again added by Jeff
       */
      runningThreads = new HashSet<Thread>(); 
      mPrimaryStage.setOnCloseRequest(
         new EventHandler<WindowEvent>()
         {
            public void handle(WindowEvent event) 
            {
               event.consume();
               try
               {
                  Platform.exit();
                  System.exit(0);
               }
               catch (Exception e)
               {
                  System.out.println(e.getMessage());
               }
            }
         });

      mRoot = new Group();

      mScene = new Scene(mRoot, 460, 640, Color.WHITE);

      /*
       * the event for the start button, on the left side of the GUI
       */
      mStartEventHandler =
      new EventHandler<ActionEvent>()
      {
    	  public void handle(ActionEvent event)
    	  {
    		  startThread();
    	  }	
      };

         
      mStopEventHandler =
      new EventHandler<ActionEvent>()
      {
    	  public void handle(ActionEvent event)
    	  {
    		  stopThread();
    	  }
      };

      
      /* 
       * sets up the top of the Glory box
       */
      HBox hbox = new HBox(10);
      Label label = new Label("Enter Runnable: ");
      mTextField = new TextField("");

      hbox.getChildren().addAll(label, mTextField);
      hbox.setMargin(label, new Insets(10, 0, 10, 30));
      hbox.setMargin(mTextField, new Insets(10, 10, 10, 0));
      hbox.setHgrow(mTextField, Priority.ALWAYS);

      /*
       * creates one of the glorified lists, the one on the right side 
       */
      mRunnables = 
         new GlorifiedList(
            "Runnables", null,
/*
            FXCollections.observableArrayList(
               "Silly", "Blinky", "Lovely", "Funny", "Jiggly", "Praiser"),
*/
            "Start", mStartEventHandler, SelectionMode.SINGLE);

      /*
       * creates and set up the other glorified lists, the one of the left
       */
      mRunningThreads = 
         new GlorifiedList(
            "Running Threads", null,
/*
            FXCollections.observableArrayList(
               "SillyThread-2", "SillyThread-4", "LovelyThread-6", "PraiserThread-8"),
*/
            "Stop", mStopEventHandler, SelectionMode.MULTIPLE);

      mTextField.setOnAction(
         new EventHandler<ActionEvent>()
         {
            public void handle(ActionEvent event)
            {
               enterRunnable();
            }
         });

      HBox listsBox = new HBox(10);
      listsBox.getChildren().addAll(mRunnables, mRunningThreads);
      listsBox.setMargin(mRunnables, new Insets(10, 10, 10, 10));
      listsBox.setMargin(mRunningThreads, new Insets(10, 10, 10, 10));

      mBorderPane = new BorderPane();
      mBorderPane.prefHeightProperty().bind(mScene.heightProperty());
      mBorderPane.prefWidthProperty().bind(mScene.widthProperty());
      mBorderPane.setCenter(hbox);
      mBorderPane.setBottom(listsBox);

      mRoot.getChildren().add(mBorderPane);

      mPrimaryStage.setScene(mScene);

      mPrimaryStage.show();
   }

   /*
    * Main method calls the launch(args) method to start the application
    */
   public static void main(String[] args)
   {
      launch(args);
   }

   /*
    * enters the runnable class in the text field, if the class is not a 
    * runnable than it wont add it.
    */
   private void enterRunnable()
   {
      String runnableName = mTextField.getText();
      mTextField.setText("");
      if (loadRunnable(runnableName) )//&&)startThread())
      {
         mRunningThreads.requestButtonFocus();
      }
   }

   /*
    * returns weither or not the class entered is a runnable or not
    * will call showMessage method if the class was not load-able
    */
   private boolean loadRunnable(String name)
   {
      Class runnable = null;
      boolean isReallyRunnable = false;
      try
      {
         if (name != null && name.length() > 0)
         {
        	name = "scr.Gui." + name;
            runnable = Class.forName(name);
            isReallyRunnable = Runnable.class.isAssignableFrom(runnable);
            if (isReallyRunnable)
            {
               mRunnables.addToList(runnable.getName());
            }
            else
            {
               throw new ClassCastException(name + " is not a Runnable.");
            }
         }
      }
      catch (Throwable t)
      {
         showMessage("Error loading Runnable", t.toString());
      }
      return (runnable != null && isReallyRunnable);
   }

   /*
    * starts the thread running
    */
   private boolean startThread()
   {
      String name = mRunnables.getSelectedItem();
      
      Thread t = createThread(name);
      if (t != null)
      {
         mRunningThreads.addToList(t.getName());
         t.start();
         runningThreads.add(t);
         return true;
      }
      return false;
   }

   /*
    * creates a new thread from the list of classes that are runnables 
    */
   private Thread createThread(String name)
   {
      Thread t = null;
      try
      {
         Runnable r = (Runnable) Class.forName(name).newInstance();
         t = new Thread(r);
         t.setName(r.getClass().getName() + t.getName());
      }
      catch (Exception e)
      {
         // should never get here
      }
      return t; // should never be null
   }

   
   /*
    * At first this method will remove the thread name from the list of 
    * threads that are expected to be running.  Than to make sure that 
    * there aren't any threads running that aren't supposed to be a check is
    * made and if one is found thats not supposed to be running, that thread
    * is forced to stop.
    */
   @SuppressWarnings("deprecation")
   private void stopThread()
   {
      mRunningThreads.removeFromList();
      // checks to see if there are any more threads that are 
      // supposed to be stoped
      Iterator<Thread> it = runningThreads.iterator();
      Thread t;
      while (it.hasNext())
      {  
    	  t = it.next();
    	  if(!shouldThreadBeRunning(t))
    	  {
    		  try 
    		  {		
    			  t.stop();
    			  it.remove();;
    		  }
    		  catch (Exception e)
    		  {	
    		  }
    	  }
      }
   }         

   /*
    * checks to see if the thread should be running, each thread should use
    * this method to make sure that they are still supposed to be running
    */
   public boolean shouldThreadBeRunning()
   {
      return (shouldThreadBeRunning(Thread.currentThread()));
   }

   /*
    * This check is to make sure that a certain thread is still supposed to 
    * be running.  This allows for the checking for uncompliant threads, so 
    * if you need to you can do something about it. 
    */
   public boolean shouldThreadBeRunning(Thread t)
   {
	//  if (startsWith(t.getName()))
		  
		  {
		  return mRunningThreads.isInList(t.getName());
   
		  }
	//  else
//		  return true;
   }	  
 /*  public boolean startsWith(String test)
   {
	   boolean inFileThread
   return 
   }
   */
}
