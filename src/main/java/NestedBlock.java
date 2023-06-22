import java.util.List;

public class NestedBlock implements CompositeBlock {
    private final String color;
    private final String material;
    private final List<Block> blocks;

    public NestedBlock(String color, String material, List<Block> blocks) {
        this.color = color;
        this.material = material;
        this.blocks = blocks;
    }

    @Override
    public String color() {
        return color;
    }

    @Override
    public String material() {
        return material;
    }

    @Override
    public List<Block> blocks() {
        return blocks;
    }

}
