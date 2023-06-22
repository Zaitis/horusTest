import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private final List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return findColorInCompositeBlock(blocks, color);
    }

    private Optional<Block> findColorInCompositeBlock(List<Block> blockList, String color) {
        Optional<Block> result = Optional.empty();
        for (Block block : blockList) {
            if (block.color().equals(color)) {
                return Optional.of(block);
            }
            if (block instanceof CompositeBlock) {
                    result = findColorInCompositeBlock(((CompositeBlock) block).blocks(), color);
            }
        }
        return result;
    }


    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> list = new ArrayList<>();
        return collectBlockByMaterial(blocks, material, list);
    }


    private List<Block> collectBlockByMaterial(List<Block> blocks, String material, List<Block> collectedList) {

        for (Block block : blocks) {
            if (block instanceof CompositeBlock) {
                collectBlockByMaterial(((CompositeBlock) block).blocks(), material, collectedList);
            }
            if (block.material().equals(material)) {
                collectedList.add(block);
            }
        }
        return collectedList;
    }

    @Override
    public int count() {
        int totalCount = 0;
        return getTotalCount(blocks, totalCount);
    }

    private int getTotalCount(List<Block> blocks, int totalCount) {
        for (Block block : blocks) {
            totalCount++;
            if (block instanceof CompositeBlock) {
                totalCount = getTotalCount(((CompositeBlock) block).blocks(), totalCount);
            }
        }

        return totalCount;
    }

    public List<Block> getBlocks() {
        return blocks;
    }
}