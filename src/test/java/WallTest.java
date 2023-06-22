import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class WallTest {
    Wall wall;
    List<Block> blocks;
    SimpleBlock block1, block2, block3, block4;

    @BeforeEach
    void setUp(){

        blocks = new ArrayList<>();
        block1 = new SimpleBlock("blue", "stone");
        block2 = new SimpleBlock("red", "plastic");
        block3 = new SimpleBlock("green", "metal");
        block4 = new SimpleBlock("blue", "plastic");
        blocks.add(block1);
        blocks.add(block2);
        blocks.add(block3);
        blocks.add(block4);
        wall = new Wall(blocks);
    }

    @Test
    void shouldNotFindBlockByColor() {

        assertFalse(wall.findBlockByColor("black").isPresent());
        assertFalse(wall.findBlockByColor("purple").isPresent());
        assertFalse(wall.findBlockByColor("white").isPresent());
        assertFalse(wall.findBlockByColor("66").isPresent());
        assertTrue(wall.findBlockByColor("66").isEmpty());
    }


    @Test
    void shouldFindBlockByColor() {

        assertTrue(wall.findBlockByColor("blue").isPresent());
        assertTrue(wall.findBlockByColor("red").isPresent());
        assertTrue(wall.findBlockByColor("green").isPresent());
    }

    @Test
    void shouldNotFindBlocksByMaterial() {
        assertTrue(wall.findBlocksByMaterial("Wood").isEmpty());
        assertFalse(wall.findBlockByColor("Wood").isPresent());
        assertFalse(wall.findBlockByColor("Crystal").isPresent());
    }

    @Test
    void shouldFindBlocksByMaterial() {
        assertTrue(wall.findBlocksByMaterial("Wood").isEmpty());
        assertFalse(wall.findBlocksByMaterial("plastic").isEmpty());
        assertFalse(wall.findBlocksByMaterial("metal").isEmpty());

    }

    @Test
    void shouldCountBlocksWithMaterial() {

        assertEquals(2,wall.findBlocksByMaterial("plastic").size());
    }

    @Test
    void shouldCountBlocks() {

        assertEquals(4, wall.count());
        assertEquals(0, (new Wall(new ArrayList<>())).count());
    }
}

