import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class SolidRect extends MenuElement
{
    private final Color fillColor, borderColor;
    private final double borderWidth;

    public SolidRect(MenuElement parentElement, int left, int top, int width, int height, int zIndex, Color fillColor, Color borderColor, double borderWidth)
    {
        super(parentElement, left, top, width, height, zIndex);

        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
    }

    @Override
    public void drawElement(GUIManager guiManager)
    {
        guiManager.gc().setFill(fillColor);
        guiManager.gc().fillRect(left(), top(), width(), height());
        guiManager.gc().setLineWidth(borderWidth);
        guiManager.gc().strokeRect(left(), top(), width(), height());
    }
}
