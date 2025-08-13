import java.util.HashMap;
import java.util.Map;

interface Tree
{
    void display(int x, int y);
}

class TreeType implements Tree
{
    private String name;
    private String color;
    private String texture;

    public TreeType(String name, String color, String texture)
    {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    @Override
    public void display(int x,int y)
    {
        System.out.println("Displaying " + name + " tree at (" + x + "," + y +  ") with color " + color
         + " and texture " + texture);
    }
}

class TreeFactory
{
    private static final Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture)
    {
        String key = name + "_" + color + "_" + texture;
        if(!treeTypes.containsKey(key))
        {
            treeTypes.put(key, new TreeType(name, color, texture));
            System.out.println("Created new tree with treeType : " + key);
        }

        return treeTypes.get(key);
    }
}

class Forest
{
    public void plantTree(String name, String color, String texture, int x, int y)
    {
        TreeType treeType = TreeFactory.getTreeType(name, color, texture);
        treeType.display(x, y);
    }
}

public class FlyweightDesignPattern
{
    public static void main(String[] args) {
        Forest forest = new Forest();

        forest.plantTree("TreeOne", "Black", "TextureOne", 100, 200);
        forest.plantTree("TreeTwo", "Red", "TextureTwo", 1000, 2000);
        forest.plantTree("TreeOne", "Black", "TextureOne", 500, 1000);
    }
}
