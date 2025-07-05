import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

/**
 * Represents a menu element in a graphical user interface. This class provides functionality for creating hierarchical menu elements, handling mouse click actions, and rendering elements with associated images.
 */
public abstract class MenuElement
{
    private static final Set<MenuElement> allElements = new HashSet<>();

    private final int left, top, width, height, zIndex /*, imageOffsetX, imageOffsetY, imageWidth, imageHeight */;
    // private final Path imagePath;
    private final MenuElement parentElement;
    private final Set<MenuElement> childElements = new HashSet<>();
    private final UUID elementID = UUID.randomUUID();

    private MenuClickAction onClickFunc = null;

    public MenuElement(MenuElement parentElement, int left, int top, int width, int height, int zIndex)
    {
        this.parentElement = parentElement;
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
        this.zIndex = zIndex;

        allElements.add(this);
        if (parentElement != null)
            parentElement.addChild(this);
    }

    // Getters

    public int left()
    {
        return left;
    }

    public int top()
    {
        return top;
    }

    public int width()
    {
        return width;
    }

    public int height()
    {
        return height;
    }

    public int zIndex()
    {
        return zIndex;
    }

    @Override
    public int hashCode()
    {
        return elementID.hashCode();
    }

    @Override
    public boolean equals(Object other)
    {
        // return other instanceof MenuElement e && e.hashCode() == hashCode();
        return this == other;
    }

    public final static MenuElement newRoot()
    {
        return new MenuElement(null, 0, 0, 0, 0, -1)
        {
            @Override
            public void drawElement(GUIManager g)
            {}
        };
    }

    // public final <T extends MenuElement> T newChild(int left, int top, int width, int height, int zIndex, int imageOffsetX, int imageOffsetY, int imageWidth, int imageHeight, Path imagePath)
    // {
    //     T child = new T(this, left, top, width, height, zIndex, imageOffsetX, imageOffsetY, imageWidth, imageHeight, imagePath);
    //     this.childElements.add(child);
    //     return child;
    // }

    // public final MenuElement newChild(int left, int top, int width, int height, int zIndex)
    // {
    //     MenuElement child = new MenuElement(this, left, top, width, height, zIndex);
    //     this.childElements.add(child);
    //     return child;
    // }

    private void addChild(MenuElement childElement)
    {
        childElements.add(childElement);
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
        // Acquire topmost elements with listeners
        PriorityQueue<MenuElement> clickedElements = new PriorityQueue<>((e1, e2) -> -zIndexCompAsc(e1, e2));
        clickedElements.addAll(allElements.stream().filter(e -> e.onClickFunc != null).toList());

        // Process all the clicks in the sorted queue
        boolean any = !clickedElements.isEmpty();
        while (!clickedElements.isEmpty() && !clickedElements.poll().onClickFunc.trigger(clickType, cursorLeft, cursorTop));

        return any;
    }

    public final static void drawAll(GUIManager guiManager)
    {
        // Draw all elements in ascending zIndex order
        allElements.stream().sorted(MenuElement::zIndexCompAsc).forEachOrdered(e -> e.drawElement(guiManager));
    }

    private final static int zIndexCompAsc(MenuElement e1, MenuElement e2)
    {
        return e1.zIndex - e2.zIndex;
    }

    public final void delete()
    {
        allElements.remove(this);
    }

    public abstract void drawElement(GUIManager guiManager);
}
