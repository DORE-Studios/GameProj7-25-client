import java.nio.file.Path;
import java.util.*;

public class MenuElement // TODO add documentation
{
    private static final Set<MenuElement> allElements = new HashSet<>();

    private final int left, top, width, height, imageOffsetX, imageOffsetY, zIndex;
    private final Path imagePath;
    private final MenuElement parentElement;
    private final Set<MenuElement> childElements = new HashSet<>();
    private final UUID elementID = UUID.randomUUID();

    private MenuClickAction onClickFunc = null;

    private MenuElement(MenuElement parentElement, int left, int top, int width, int height, int zIndex, int imageOffsetX, int imageOffsetY, Path imagePath)
    {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
        this.zIndex = zIndex;
        this.imageOffsetX = imageOffsetX;
        this.imageOffsetY = imageOffsetY;
        this.imagePath = imagePath;
        this.parentElement = parentElement;

        allElements.add(this);
    }

    private MenuElement(MenuElement parentElement, int left, int top, int width, int height, int zIndex)
    {
        this(parentElement, left, top, width, height, zIndex, 0, 0, null);
    }

    @Override
    public int hashCode()
    {
        return elementID.hashCode();
    }

    @Override
    public boolean equals(Object other)
    {
        return other instanceof MenuElement e && e.hashCode() == hashCode();
    }

    public final static MenuElement newRoot()
    {
        return new MenuElement(null, 0, 0, 0, 0, -1);
    }

    public final MenuElement newChild(int left, int top, int width, int height, int zIndex, int imageOffsetX, int imageOffsetY, Path imagePath)
    {
        MenuElement child = new MenuElement(this, left, top, width, height, zIndex, imageOffsetX, imageOffsetY, imagePath);
        this.childElements.add(child);
        return child;
    }

    public final MenuElement newChild(int left, int top, int width, int height, int zIndex)
    {
        MenuElement child = new MenuElement(this, left, top, width, height, zIndex);
        this.childElements.add(child);
        return child;
    }

    public final MenuElement setOnClick(MenuClickAction func)
    {
        this.onClickFunc = func;
        return this;
    }

    public final boolean removeChild(MenuElement child)
    {
        return childElements.remove(child);
    }

    public final boolean isRoot()
    {
        return parentElement == null;
    }

    public final static boolean processMouseClick(String clickType, int cursorLeft, int cursorTop)
    {
        // TODO implement, where click affects greatest z-index element the cursor is on, and 
        return false;
    }


    public void drawElement(GUIManager guiManager)
    {
        // TODO ...
    }
}
