package component;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestMultiArrays {

    @Test
    public void multi_dimensional_arrays_can_be_created() {
        JFixture fixture = new JFixture();

        int[][] multiArray = fixture.create(int[][].class);

        assertTrue(multiArray != null);
    }

    @Test
    public void generic_multi_dimensional_arrays_can_be_created() {
        JFixture fixture = new JFixture();

        List<Integer>[][] multiArray = fixture.create(new SpecimenType<List<Integer>[][]>(){});

        assertTrue(multiArray != null);
    }

    @Test
    public void large_multi_dimensional_arrays_can_be_created() {
        JFixture fixture = new JFixture();

        int[][][][][] multiArray = fixture.create(int[][][][][].class);

        assertTrue(multiArray != null);
    }

    @Test
    public void contents_of_multi_dimensional_arrays_are_populated() {
        JFixture fixture = new JFixture();

        int[][] multiArray = fixture.create(int[][].class);

        for (int[] innerArray : multiArray) {
            for (int innerInnerArray : innerArray) {
                assertTrue(innerInnerArray > 0);
            }
        }
    }

    @Test
    public void all_inner_arrays_are_of_the_same_size() {
        JFixture fixture = new JFixture();

        int[][] multiArray = fixture.create(int[][].class);

        assertEquals(multiArray[0].length, multiArray[1].length);
        assertEquals(multiArray[1].length, multiArray[2].length);
    }

    @Test
    public void all_inner_arrays_respect_repeat_count_of_the_fixture() {
        JFixture fixture = new JFixture();
        int repeatCount = 5;
        fixture.customise().repeatCount(repeatCount);

        int[][] multiArray = fixture.create(int[][].class);
        assertTrue(multiArray.length == repeatCount);
        for (int[] innerArray : multiArray) {
            assertTrue(innerArray.length == repeatCount);
        }

    }
}
