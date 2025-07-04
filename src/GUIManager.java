import java.nio.file.Path;
import java.util.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/** Handles writing to the graphics context. */
public final class GUIManager
{
    private final GraphicsContext gc;

    public GUIManager(GraphicsContext gc)
    {
        this.gc = gc;
    }

    public GraphicsContext gc()
    {
        return gc;
    }

    // TODO add methods for stuff to do with writing to the GUI
    public void drawImage(Path filePath, int left, int top, int width, int height)
    {
        Image img = new Image(filePath.toAbsolutePath().toString(), width, height, false, false);
        gc.drawImage(img, left, top, width, height);
    }

    public void drawGrid()
    {
        gc.setFill(Color.BLACK);
		// draw grids 
		for(int j = 0; j < Main.TITLES; j++){
			for(int i = 0; i < Main.TITLES; i++){
			
			gc.strokeLine(i * Main.TITLE_WIDTH, Main.offset/2 + Main.TITLE_HEIGHT/2 + j * Main.TITLE_HEIGHT, Main.TITLE_WIDTH/2 + i * Main.TITLE_WIDTH, Main.offset/2 + j * Main.TITLE_HEIGHT);
			gc.strokeLine(Main.TITLE_WIDTH/2 + i * Main.TITLE_WIDTH, Main.offset/2 + j * Main.TITLE_HEIGHT, Main.TITLE_WIDTH + i * Main.TITLE_WIDTH, Main.offset/2 + Main.TITLE_HEIGHT/2 + j * Main.TITLE_HEIGHT);
			gc.strokeLine(i * Main.TITLE_WIDTH, Main.offset/2 + Main.TITLE_HEIGHT/2 + j * Main.TITLE_HEIGHT, Main.TITLE_WIDTH/2 + i * Main.TITLE_WIDTH, Main.offset/2 + Main.TITLE_HEIGHT + j * Main.TITLE_HEIGHT);
			gc.strokeLine(Main.TITLE_WIDTH/2 + i * Main.TITLE_WIDTH, Main.offset/2 + Main.TITLE_HEIGHT + j * Main.TITLE_HEIGHT, Main.TITLE_WIDTH + i * Main.TITLE_WIDTH, Main.offset/2 + Main.TITLE_HEIGHT/2 + j * Main.TITLE_HEIGHT);

			}
		}
    }
}
