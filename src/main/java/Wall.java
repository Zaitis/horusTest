import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private List<Block> blocks;


    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return getBlock(blocks, color);
    }

    private Optional<Block> getBlock(List<Block> block1, String color) {
        Optional<Block> result = Optional.empty();
        for (Block block : block1) {
            if (block.color().equals(color)) {
                return Optional.of(block);
            }
            if (block instanceof CompositeBlock) {
                if (block.color().equals(color)) return Optional.of(block);
                else {
                    result = getBlock(((CompositeBlock) block).blocks(), color);
                }
            }
        }
        return result;
    }


    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> list = new ArrayList<>();
        return getMaterial(blocks, material, list);
    }


    private List<Block> getMaterial(List<Block> blocks, String material, List<Block> list) {


        for (Block block : blocks) {
            if (block instanceof CompositeBlock) {
                getMaterial(((CompositeBlock) block).blocks(), material, list);
            }
            if (block.material().equals(material)) {
                list.add(block);
            }

        }
        return list;
    }

    @Override
    public int count() {
        int totalCount = 0;
        totalCount = getTotalCount(blocks, totalCount);

        return totalCount;
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