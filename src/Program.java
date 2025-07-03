import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

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

public class Program extends Application
{
	private static Timer gameLoopTimer;
	private static long gameTick = 0;
	private static GUIManager guiManager;

	public static void main(String[] args)
	{
		System.out.println("Program start.");

		// Startup JavaFX GUI
		launch();
		System.out.println("Fin.");
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		primaryStage.setTitle("GameProj7-25");
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();

		
		Group root = new Group();
		Canvas canvas = new Canvas(640, 480);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		// scene.setOnKeyPressed(Main::keypressEventHandler);
		// scene.setOnMouseReleased(Program::mouseEventHandler);
		primaryStage.setScene(scene);
		
		primaryStage.show();
		guiManager = new GUIManager(gc);

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
	}
}