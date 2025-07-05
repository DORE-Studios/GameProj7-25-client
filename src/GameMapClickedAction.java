public class GameMapClickedAction implements MenuClickAction {

    private final GameMapDrawer mapDrawer;

    public GameMapClickedAction (GameMapDrawer mapDrawer) {
        this.mapDrawer = mapDrawer;
    }

    @Override
    public boolean trigger (String clickType, int cursorLeft, int cursorTop) {

        if (clickType.equals("released")) {
            mapDrawer.highlightCell(cursorLeft, cursorTop);
        }

        return true;

    }

}