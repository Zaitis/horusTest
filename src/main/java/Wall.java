import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        for (Block block : blocks) {
            if (block.color().equals(color)) {
                return Optional.of(block);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> block = blocks.stream().filter(block1 -> block1.material().equals(material)).toList();
        return block;
    }

    @Override
    public int count() {
        return blocks.size();
    }

    public List<Block> getBlocks() {
        return blocks;
    }
}