import java.util.HashMap;

public class Item {
    public enum ItemType{
        FOOD, OTHER
    }
    
    private static final java.util.Map<ItemType, Double> ITEM_WEIGHTS = new HashMap<>();{
        ITEM_WEIGHTS.put(ItemType.FOOD, 0.0);
    }

    private ItemType itemType;

    public Item(ItemType IT){
        this.itemType = IT;
    }

    public double getItemWeight(){
        return ITEM_WEIGHTS.get(itemType);
    }

    public ItemType getItemType(){
        return itemType;
    }
}
//dfgh