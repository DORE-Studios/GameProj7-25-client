import java.util.*;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameMapDrawer {
    
    private static final double gridAngleDeg = 30.0;
    private static final double cellHorizontalWidth = 100.0;

    private static final double gridAngleRad = (gridAngleDeg / 180.0) * Math.PI;
	private static final double cellHalfWidth = cellHorizontalWidth / 2.0;
    private static final double tan = Math.tan(gridAngleRad);
	private static final double cellHalfHeight = tan * cellHalfWidth;

    private Cell highlightedCell = null;

    private final GUIManager guiManager;

    public GameMapDrawer (GUIManager guiManager) {
        this.guiManager = guiManager;
    }

    public void drawMap (GameMap map, double centerCellX, double centerCellY) {

        // Figure out which cells to render on the screen
        List<Cell> cellsToDraw = getCellsToDraw(map, centerCellX, centerCellY);
        
        for (Cell cell : cellsToDraw) {
            double[] cellPosition = getCellCenterOnScreen(Main.SCREEN_WIDTH / 2.0, Main.SCREEN_HEIGHT / 2.0, centerCellX, centerCellY, cell.getX(), cell.getY());
            drawTile(guiManager.gc(), cellPosition[0], cellPosition[1], Color.BLACK);
        }

        if (highlightedCell != null) {
            if (cellsToDraw.contains(highlightedCell)) {
                double[] cellPosition = getCellCenterOnScreen(Main.SCREEN_WIDTH / 2.0, Main.SCREEN_HEIGHT / 2.0, centerCellX, centerCellY, highlightedCell.getX(), highlightedCell.getY());
                drawTile(guiManager.gc(), cellPosition[0], cellPosition[1], Color.YELLOW);
            }
        }
        
    }

    public void highlightCell (GameMap map, double clickX, double clickY) {

        System.out.println(clickX);
        System.out.println(clickY);

        double[] cellClickPos = getCellPositionAtScreenPosition(Main.SCREEN_WIDTH / 2.0, Main.SCREEN_HEIGHT / 2.0, clickX, clickY, 0, 0);

        System.out.println(cellClickPos[0]);
        System.out.println(cellClickPos[1]);

        Cell cell = map.getCellAtPosition((int) Math.round(cellClickPos[0]), (int) Math.round(cellClickPos[1]));
        highlightedCell = cell;

        drawMap(map, 0, 0);

    }

    public List<Cell> getCellsToDraw (GameMap map, double centerCellX, double centerCellY) {

        ArrayList<Cell> result = new ArrayList<>();

        double xCenter = Main.SCREEN_WIDTH / 2.0;
		double yCenter = Main.SCREEN_HEIGHT / 2.0;

        int closestToCenterCellX = (int) Math.round(centerCellX);
        int closestToCenterCellY = (int) Math.round(centerCellY);

        Cell centerCell = map.getCellAtPosition(closestToCenterCellX, closestToCenterCellY);
        
		ArrayList<Cell> cellsToRender = new ArrayList<>();
        Set<Cell> alreadyRendered = new HashSet<>();
		cellsToRender.add(centerCell);

        while (!cellsToRender.isEmpty()) {
            
            Cell renderCell = cellsToRender.get(0);
            cellsToRender.remove(0);
            alreadyRendered.add(renderCell);;

            // Check if cell is out of bounds and should not be rendered
            double[] renderCellPosition = getCellCenterOnScreen(xCenter, yCenter, centerCellX, centerCellY, renderCell.getX(), renderCell.getY());
            if (!isCenterOnScreen(renderCellPosition[0], renderCellPosition[1])) continue;

            result.add(renderCell);
            for (Cell neighborCell : map.getNeighboringCells(renderCell, true)) {
                if (alreadyRendered.contains(neighborCell) || cellsToRender.contains(neighborCell)) continue;
                cellsToRender.add(neighborCell);
            }
        }
        
        result.sort((c1, c2) -> Integer.compare(c1.getY() - c1.getX(), c2.getY() - c2.getX()));
        return result;

    }

    public double[] getCellPositionAtScreenPosition (double x, double y, double screenCenterX, double screenCenterY, double centerCellX, double centerCellY) {

        double[] cellPosition = new double[2];

        double xFromCenter = screenCenterX - x;
        double yFromCenter = screenCenterY - y;

        cellPosition[0] = centerCellX + (((xFromCenter / cellHalfWidth) - (yFromCenter / cellHalfHeight)) / 2);
        cellPosition[1] = centerCellY + (((xFromCenter / cellHalfWidth) + (yFromCenter / cellHalfHeight)) / 2);

        return cellPosition;

    }

    public double[] getCellCenterOnScreen (double screenCenterX, double screenCenterY, double centerCellX, double centerCellY, int cellX, int cellY) {

        double[] cellCenterOnScreen = new double[2];

        double cellDifX = cellX - centerCellX;
        double cellDifY = cellY - centerCellY;

        cellCenterOnScreen[0] = screenCenterX + (cellDifX + cellDifY) * cellHalfWidth;
        cellCenterOnScreen[1] = screenCenterY + (cellDifY - cellDifX) * cellHalfHeight;

        return cellCenterOnScreen;

    }

    public void drawTile (GraphicsContext gc, double xCenter, double yCenter, Color color) {

		double x1 = xCenter + cellHalfWidth;
		double y1 = yCenter;
		double x2 = xCenter;
		double y2 = yCenter + cellHalfHeight;
		double x3 = xCenter - cellHalfWidth;
		double y3 = yCenter;
		double x4 = xCenter;
		double y4 = yCenter - cellHalfHeight;

		gc.setStroke(color);
		gc.strokeLine(x1, y1, x2, y2);
		gc.strokeLine(x2, y2, x3, y3);
		gc.strokeLine(x3, y3, x4, y4);
		gc.strokeLine(x4, y4, x1, y1);

	}

    public boolean isCenterOnScreen (double x, double y) {

        if (x > Main.SCREEN_WIDTH + cellHalfWidth) return false;
        if (x < -cellHalfWidth) return false;
        if (y > Main.SCREEN_HEIGHT + cellHalfHeight) return false;
        if (y < -cellHalfHeight) return false;
        return true;

    }

}
