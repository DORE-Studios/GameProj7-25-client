import java.awt.*;
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

public class Main extends Application
{
	private static Timer gameLoopTimer;
	private static long gameTick = 0;
	private final int SCREEN_WIDTH = 640; 
	private final int SCREEN_HEIGHT = 480;
	private final int TITLES = 10; //number of titles 
	private final int HORIZONTAL_TITLES = SCREEN_WIDTH/2; //divide by 2 cus of 2 pixels across and 1 pixel up
	private final int offset = SCREEN_HEIGHT - HORIZONTAL_TITLES; //offset for the grids on the middle of the screen
	private final int TITLE_WIDTH = SCREEN_WIDTH/TITLES;
	private final int TITLE_HEIGHT = HORIZONTAL_TITLES/TITLES;
	

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
		Canvas canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		gc.setFill(Color.BLACK);
		// draw grids 
		for(int j = 0; j < TITLES; j++){
			for(int i = 0; i < TITLES; i++){
			
			gc.strokeLine(i * TITLE_WIDTH, offset/2 + TITLE_HEIGHT/2 + j * TITLE_HEIGHT, TITLE_WIDTH/2 + i * TITLE_WIDTH, offset/2 + j * TITLE_HEIGHT);
			gc.strokeLine(TITLE_WIDTH/2 + i * TITLE_WIDTH, offset/2 + j * TITLE_HEIGHT, TITLE_WIDTH + i * TITLE_WIDTH, offset/2 + TITLE_HEIGHT/2 + j * TITLE_HEIGHT);
			gc.strokeLine(i * TITLE_WIDTH, offset/2 + TITLE_HEIGHT/2 + j * TITLE_HEIGHT, TITLE_WIDTH/2 + i * TITLE_WIDTH, offset/2 + TITLE_HEIGHT + j * TITLE_HEIGHT);
			gc.strokeLine(TITLE_WIDTH/2 + i * TITLE_WIDTH, offset/2 + TITLE_HEIGHT + j * TITLE_HEIGHT, TITLE_WIDTH + i * TITLE_WIDTH, offset/2 + TITLE_HEIGHT/2 + j * TITLE_HEIGHT);

			}
		}
		
		
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);

		// scene.setOnKeyPressed(Main::keypressEventHandler);
		// scene.setOnMouseReleased(Program::mouseEventHandler);
		primaryStage.setScene(scene);
		primaryStage.show();

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