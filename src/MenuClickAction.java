public interface MenuClickAction
{
    /**
     * Triggers the click action if clicked upon
     * @return True if action was successfull and click should NOT pass through to next element. False will mean the next element behind will be clicked too.
     */
    public abstract boolean trigger(String clickType, int cursorLeft, int cursorTop);
}
