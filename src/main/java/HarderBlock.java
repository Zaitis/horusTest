import java.util.List;

public class HarderBlock implements CompositeBlock {
    private String color;
    private String material;
    private List<Block> blocks;

    public HarderBlock(String color, String material, List<Block> blocks) {
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
