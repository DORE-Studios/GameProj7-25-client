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

}
