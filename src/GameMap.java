import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


/**
 * Represents the map that the game takes place on.
 * 
 * The map is a 2D grid of cells, loaded from a json file. 
 */
public class GameMap {
    
    private static String MAP_FILENAME = "./bin/MAP_DATA.json";

    private Cell[][] cellGrid;

    public GameMap () {
        cellGrid = loadMapFromJson();
    }

    public Cell[][] loadMapFromJson () {

        try {

            Gson gson = new Gson();
            Path mapFilePath = Path.of(MAP_FILENAME);

            if (!Files.exists(mapFilePath))
                throw new IOException();

            String jsonContents = Files.readString(mapFilePath);
            CellData[][] data = gson.fromJson(jsonContents, CellData[][].class);

            // Verify that every row is the same length (should be a rectangle)
            int rowSize = 0;
            rowSize = data[0].length;
            for (CellData[] row : data) {
                if (row.length != rowSize) {
                    // Raise exception - to do later LMAO
                    throw new IllegalStateException("Invalid map file, row of invalid size found...");
                }
            }

            // Create the cells
            Cell[][] cells = new Cell[data.length][rowSize];
            for (int y = 0; y < data.length; y++) {
                for (int x = 0; x < rowSize; x++){
                    cells[y][x] = new Cell(data[y][x]);
                    cells[y][x].setPosition(x, y);
                }
            }
            
            return cells;
            
        } catch (InvalidPathException e) {
            System.out.println("Map could not be loaded: JSON file path invalid");
            throw e;
        } catch (IOException e) {
            System.out.println("Map could not be loaded: Can't read JSON file");
            throw new RuntimeException();
        } catch (JsonSyntaxException e) {
            System.out.println("Map could not be loaded: JSON file syntax is invalid");
            throw e;
        }

    }
    
    public Cell getCellAtPosition (int x, int y) {
        if (y < 0 || y > cellGrid.length - 1) {
            return null;
        }
        if (x < 0 || x > cellGrid[0].length - 1) {
            return null;
        }
        return cellGrid[y][x];
    }

    public Set<Cell> getNeighboringCells (Cell cell, boolean includeDiagonals) {

        int cellX = cell.getX();
        int cellY = cell.getY();
        if (getCellAtPosition(cell.getX(), cell.getY()) != cell) return null;

        Set<Cell> neighboringCells = new HashSet<>();
        
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) continue;
                if (includeDiagonals && x != 0 && y != 0) continue;
                Cell cellAtPos = getCellAtPosition(x + cellX, y + cellY);
                if (cellAtPos != null) {
                    neighboringCells.add(cellAtPos);
                }
            }
        }

        return neighboringCells;

    }

}
