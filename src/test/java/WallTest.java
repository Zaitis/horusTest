import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class WallTest {
    Wall wall1, wall2, wall3, wall4;
    List<Block> sBlocks1,sBlocks2,nBlocks3,nBlocks4;
    SimpleBlock sBlock1, sBlock2, sBlock3, sBlock4;
    NestedBlock nBlock1, nBlock2, nBlock3, nBlock4;

    @BeforeEach
    void setUp(){

        sBlocks1 = new ArrayList<>();
        sBlocks2 = new ArrayList<>();
        nBlocks3 = new ArrayList<>();
       nBlocks4 = new ArrayList<>();
        sBlock1 = new SimpleBlock("blue", "stone");
        sBlock2 = new SimpleBlock("red", "plastic");
        sBlock3 = new SimpleBlock("green", "metal");
        sBlock4 = new SimpleBlock("grey", "plastic");
        sBlocks1.add(sBlock1);
        sBlocks1.add(sBlock2);
        sBlocks1.add(sBlock3);
        sBlocks1.add(sBlock4);

        sBlocks2.add(sBlock1);
        sBlocks2.add(sBlock4);

        nBlock1 = new NestedBlock("blue", "stone", sBlocks1);
        nBlock2 = new NestedBlock("red", "cloud", sBlocks2);
        nBlocks3.add(nBlock1);
        nBlocks3.add(nBlock2);
        nBlock4= new NestedBlock("purple", "cloud", nBlocks3);
      //  nBlock3 = new NestedBlock("green", "metal", nBlock2);
        nBlock4 = new NestedBlock("blue", "plastic",nBlocks3);
        nBlocks4.add(nBlock1);
        nBlocks4.add(nBlock2);
        nBlocks4.add(nBlock4);




        wall1 = new Wall(sBlocks1);
        wall2 = new Wall(sBlocks2);
        wall3 = new Wall(nBlocks3);
        wall4 = new Wall(nBlocks4);
    }

    @Test
    void shouldCountCompositeBlockCorrectly(){
        assertEquals(Optional.of(sBlock4), wall3.findBlockByColor("grey"));
        assertEquals(List.of(sBlock1,nBlock1,sBlock1), wall3.findBlocksByMaterial("stone"));
        assertEquals(4, wall1.count());
        assertEquals(8, wall3.count());
        assertEquals(2, wall2.count());
        assertEquals(17, wall4.count());
    }

    @Test
    void shouldNotFindBlockByColor() {

        assertFalse(wall1.findBlockByColor("black").isPresent());
        assertFalse(wall1.findBlockByColor("purple").isPresent());
        assertFalse(wall1.findBlockByColor("white").isPresent());
        assertFalse(wall1.findBlockByColor("66").isPresent());
        assertTrue(wall1.findBlockByColor("66").isEmpty());
    }


    @Test
    void shouldFindBlockByColor() {

        assertTrue(wall1.findBlockByColor("blue").isPresent());
        assertTrue(wall1.findBlockByColor("red").isPresent());
        assertTrue(wall1.findBlockByColor("green").isPresent());
    }

    @Test
    void shouldNotFindBlocksByMaterial() {
        assertTrue(wall1.findBlocksByMaterial("Wood").isEmpty());
        assertFalse(wall1.findBlockByColor("Wood").isPresent());
        assertFalse(wall1.findBlockByColor("Crystal").isPresent());
    }

    @Test
    void shouldFindBlocksByMaterial() {
        assertTrue(wall1.findBlocksByMaterial("Wood").isEmpty());
        assertFalse(wall1.findBlocksByMaterial("plastic").isEmpty());
        assertFalse(wall1.findBlocksByMaterial("metal").isEmpty());

    }

    @Test
    void shouldCountBlocksWithMaterial() {

        assertEquals(2, wall1.findBlocksByMaterial("plastic").size());
    }

    @Test
    void shouldCountBlocks() {

        assertEquals(4, wall1.count());
        assertEquals(0, (new Wall(new ArrayList<>())).count());
    }
}

