import java.util.*;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameMapDrawer extends MenuElement {
    
    private static final double gridAngleDeg = 30.0;
    private static final double cellHorizontalWidth = 100.0;

    private static final double gridAngleRad = (gridAngleDeg / 180.0) * Math.PI;
	private static final double cellHalfWidth = cellHorizontalWidth / 2.0;
    private static final double tan = Math.tan(gridAngleRad);
	private static final double cellHalfHeight = tan * cellHalfWidth;

    private GameMap map;

    private Cell highlightedCell = null;

    private double centerCellX = 0;
    private double centerCellY = 0;

    public GameMapDrawer (GameMap map) {
        
        super(null, 0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT, 0);
        this.map = map;
        this.setOnClick(new GameMapClickedAction(this));

    }

    @Override
    public void drawElement (GUIManager guiManager) {

        // Clear graphics
        // TODO: Remove once experimentation is done
        guiManager.gc().clearRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        // Figure out which cells to render on the screen
        List<Cell> cellsToDraw = getCellsToDraw();
        System.out.println(cellsToDraw.size());
        for (Cell cell : cellsToDraw) {
            double[] cellPosition = getCellCenterOnScreen(cell.getX(), cell.getY());
            drawTile(guiManager.gc(), cellPosition[0], cellPosition[1], Color.BLACK);
        }

        if (highlightedCell != null) {
            if (cellsToDraw.contains(highlightedCell)) {
                double[] cellPosition = getCellCenterOnScreen(highlightedCell.getX(), highlightedCell.getY());
                drawTile(guiManager.gc(), cellPosition[0], cellPosition[1], Color.YELLOW);
            }
        }
    }

    public void highlightCell (double clickX, double clickY) {

        double[] cellClickPos = getCellPositionAtScreenPosition(clickX, clickY, centerCellX, centerCellY);

        System.out.println(cellClickPos[0]);
        System.out.println(cellClickPos[1]);

        Cell cell = map.getCellAtPosition((int) Math.round(cellClickPos[0]), (int) Math.round(cellClickPos[1]));
        highlightedCell = cell;

        if (cell == null) return;
        
    }

    public List<Cell> getCellsToDraw () {

        ArrayList<Cell> result = new ArrayList<>();

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
            double[] renderCellPosition = getCellCenterOnScreen(renderCell.getX(), renderCell.getY());
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

    public double[] getCellPositionAtScreenPosition (double x, double y, double centerCellX, double centerCellY) {

        double[] cellPosition = new double[2];

        double xFromCenter = x - (width() / 2);
        double yFromCenter = y - (height() / 2);

        cellPosition[0] = centerCellX + (((xFromCenter / cellHalfWidth) - (yFromCenter / cellHalfHeight)) / 2);
        cellPosition[1] = centerCellY + (((xFromCenter / cellHalfWidth) + (yFromCenter / cellHalfHeight)) / 2);

        return cellPosition;

    }

    public double[] getCellCenterOnScreen (int cellX, int cellY) {

        double[] cellCenterOnScreen = new double[2];

        double cellDifX = cellX - centerCellX;
        double cellDifY = cellY - centerCellY;

        cellCenterOnScreen[0] = (width() / 2) + (cellDifX + cellDifY) * cellHalfWidth;
        cellCenterOnScreen[1] = (height() / 2) + (cellDifY - cellDifX) * cellHalfHeight;

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

        if (x > width() + cellHalfWidth) return false;
        if (x < -cellHalfWidth) return false;
        if (y > height() + cellHalfHeight) return false;
        if (y < -cellHalfHeight) return false;
        return true;

    }

}
