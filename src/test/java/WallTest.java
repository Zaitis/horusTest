import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class WallTest {
    Wall wall1, wall2, wall3, wall4;
    List<Block> sBlocks1, sBlocks2, nBlocks3, nBlocks4;
    SimpleBlock sBlock1, sBlock2, sBlock3, sBlock4;
    NestedBlock nBlock1, nBlock2, nBlock3, nBlock4;

    @BeforeEach
    void setUp() {

        sBlock1 = new SimpleBlock("blue", "stone");
        sBlock2 = new SimpleBlock("red", "plastic");
        sBlock3 = new SimpleBlock("green", "metal");
        sBlock4 = new SimpleBlock("grey", "plastic");

        sBlocks1 = new ArrayList<>();
        sBlocks2 = new ArrayList<>();
        nBlocks3 = new ArrayList<>();
        nBlocks4 = new ArrayList<>();

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
        nBlocks3.add(nBlock1);
        nBlock3 = new NestedBlock("violet", "coal", nBlocks3);

        nBlock4 = new NestedBlock("violet", "plastic", nBlocks3);
        nBlocks4.add(nBlock1);
        nBlocks4.add(nBlock2);
        nBlocks4.add(nBlock4);

        wall1 = new Wall(sBlocks1);
        wall2 = new Wall(sBlocks2);
        wall3 = new Wall(nBlocks3);
        wall4 = new Wall(nBlocks4);
    }

    @Test
    void shouldGetListOfBlock() {
        //given

        //when
        List<Block> result =wall1.getBlocks();

        //then
        assertSame(result, wall1.getBlocks());
    }

    @Test
    void shouldCountCompositeBlockCorrectly() {
        //given

        //when
        int result0=(new Wall(new ArrayList<>())).count();
        int result1= wall1.count();
        int result2= wall2.count();
        int result3= wall3.count();
        int result4= wall4.count();

        //then
        assertEquals(result0, (new Wall(new ArrayList<>())).count());
        assertEquals(result1, wall1.count());
        assertEquals(result2, wall2.count());
        assertEquals(result3, wall3.count());
        assertEquals(result4, wall4.count());
    }

    @Test
    void shouldCountBlocksWithMaterial() {
        //given

        //when
        int result1= wall1.findBlocksByMaterial("plastic").size();
        int result2= wall2.findBlocksByMaterial("metal").size();
        int result3= wall3.findBlocksByMaterial("plastic").size();
        int result4= wall4.findBlocksByMaterial("plastic").size();
        int result5= wall4.findBlocksByMaterial("stone").size();

        //then
        assertEquals(result1, wall1.findBlocksByMaterial("plastic").size());
        assertEquals(result2, wall2.findBlocksByMaterial("metal").size());
        assertEquals(result3, wall3.findBlocksByMaterial("plastic").size());
        assertEquals(result4, wall4.findBlocksByMaterial("plastic").size());
        assertEquals(result5, wall4.findBlocksByMaterial("stone").size());
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

        //given

        //when
        Optional<Block> result1 = wall3.findBlockByColor("grey");
        boolean result2 =wall1.findBlockByColor("blue").isPresent();
        boolean result3 =wall4.findBlockByColor("red").isPresent();
        boolean result4 =wall3.findBlockByColor("green").isPresent();

        //then
        assertEquals(result1, wall3.findBlockByColor("grey"));
        assertTrue(result2);
        assertTrue(result3);
        assertTrue(result4);
    }

    @Test
    void shouldNotFindBlocksByMaterial() {

        //given

        //when
        boolean result1 = wall1.findBlocksByMaterial("Wood").isEmpty();
        boolean result2 = wall2.findBlockByColor("Wood").isPresent();
        boolean result3 = wall3.findBlockByColor("Crystal").isPresent();

        //then
        assertTrue(result1);
        assertFalse(result2);
        assertFalse(result3);
    }

    @Test
    void shouldFindBlocksByMaterial() {

        //given
        List<Block> list = List.of(sBlock1, nBlock1, sBlock1, sBlock1, nBlock1);

        //when
        List<Block> result1= wall3.findBlocksByMaterial("stone");
        boolean result2= wall1.findBlocksByMaterial("Wood").isEmpty();
        boolean result3= wall2.findBlocksByMaterial("plastic").isEmpty();
        boolean result4= wall4.findBlocksByMaterial("metal").isEmpty();

        //then
        assertEquals(result1,list);
        assertTrue(result2);
        assertFalse(result3);
        assertFalse(result4);

    }
}

