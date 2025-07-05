import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.net.*;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class Main extends Application
{
	private static Timer gameLoopTimer;
	private static long gameTick = 0;
	private static GUIManager guiManager;

	public static final int SCREEN_WIDTH = 640;
	public static final int SCREEN_HEIGHT = 480;
	public static final int TITLES = 10; //number of titles 
	public static final int HORIZONTAL_TITLES = SCREEN_WIDTH / 2; //divide by 2 cus of 2 pixels across and 1 pixel up
	public static final int offset = SCREEN_HEIGHT - HORIZONTAL_TITLES; //offset for the grids on the middle of the screen
	public static final int TITLE_WIDTH = SCREEN_WIDTH / TITLES;
	public static final int TITLE_HEIGHT = HORIZONTAL_TITLES / TITLES;


	public static void main(String[] args)
	{
		System.out.println("Program start.");

		// Startup JavaFX GUI
		launch();
		System.out.println("Fin.");
		System.exit(0);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		// Window settings
		primaryStage.setTitle("GameProj7-25");
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();

		// Canvas setup, we basically just do everything on the one canvas' graphics context
		Group root = new Group();
		Canvas canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		guiManager = new GUIManager(gc);
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();

		guiManager.drawGrid();

		// scene.setOnKeyPressed(Main::keypressEventHandler);
		// scene.setOnMouseReleased(Program::mouseEventHandler);

		gameTick = 0;
		TimerTask loopTask = new TimerTask()
		{
			@Override
			public void run()
			{
				gameLoop(primaryStage, gc);
			}
		};
		gameLoopTimer = new Timer();
		gameLoopTimer.schedule(loopTask, 250, 250);

		System.out.println("Init Success");
	}

	private void gameLoop(Stage gameStage, GraphicsContext gc)
	{
		System.out.println("Tick: " + gameTick);
		gameTick++;
		try
		{
			Socket socket = new Socket(InetAddress.getByName(null), 8051);

			socket.close();
		}
		catch(IOException e)
		{
			//Can't connect
		}
	}
}