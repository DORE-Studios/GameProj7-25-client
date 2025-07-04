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

        GameMap map = new GameMap();

        double gridAngleDeg = 30.0;
        double tileHorizontalWidth = 100;

        double xCenter = Main.SCREEN_WIDTH / 2.0;
		double yCenter = Main.SCREEN_HEIGHT / 2.0;
		
		double gridAngleRad = (gridAngleDeg / 180.0) * Math.PI;
		double tileHalfWidth = tileHorizontalWidth / 2.0;
        double tan = Math.tan(gridAngleRad);
		double tileHalfHeight = tan * tileHalfWidth;
		
        Cell centerCell = map.getCellAtPosition(9, 5);
		ArrayList<Cell> cellsToRender = new ArrayList<>();
        Set<Cell> alreadyRendered = new HashSet<>();
		cellsToRender.add(centerCell);

        while (!cellsToRender.isEmpty()) {
            
            Cell renderCell = cellsToRender.get(0);
            cellsToRender.remove(0);
            alreadyRendered.add(renderCell);
            
            double cellDifX = renderCell.getX() - centerCell.getX();
            double cellDifY = renderCell.getY() - centerCell.getY();

            double cellCenterX = xCenter + (cellDifX + cellDifY) * tileHalfWidth;
            double cellCenterY = yCenter + (cellDifY - cellDifX) * tileHalfHeight;

            // Check if cell is out of bounds and should not be rendered
            if (cellCenterX < (0 - tileHalfWidth)) continue;
            if (cellCenterX > (Main.SCREEN_WIDTH + tileHalfWidth)) continue;
            if (cellCenterY < (0 - tileHalfHeight)) continue;
            if (cellCenterY > (Main.SCREEN_HEIGHT + tileHalfHeight)) continue;

            drawTileBorder(gc, cellCenterX, cellCenterY, tileHalfWidth, tileHalfHeight);
            
            for (Cell neighborCell : map.getNeighboringCells(renderCell, true)) {
                if (alreadyRendered.contains(neighborCell) || cellsToRender.contains(neighborCell)) continue;
                cellsToRender.add(neighborCell);
            }

        }

    }

    public void drawTileBorder(GraphicsContext gc, double xCenter, double yCenter, double tileHalfWidth, double tileHalfHeight) {

		double x1 = xCenter + tileHalfWidth;
		double y1 = yCenter;
		double x2 = xCenter;
		double y2 = yCenter + tileHalfHeight;
		double x3 = xCenter - tileHalfWidth;
		double y3 = yCenter;
		double x4 = xCenter;
		double y4 = yCenter - tileHalfHeight;

		gc.setFill(Color.BLACK);
		gc.strokeLine(x1, y1, x2, y2);
		gc.strokeLine(x2, y2, x3, y3);
		gc.strokeLine(x3, y3, x4, y4);
		gc.strokeLine(x4, y4, x1, y1);

	}
}
