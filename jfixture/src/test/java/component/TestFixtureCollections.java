package component;

import com.flextrade.jfixture.FixtureCollections;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.MultipleCount;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

import static junit.framework.Assert.assertEquals;

public class TestFixtureCollections {

    private FixtureCollections fixtureCollections;
    private static final int DEFAULT_SIZE = 3;

    @Before
    public void initialise() {

        JFixture fixture = new JFixture();
        MultipleCount multipleCount = new MultipleCount(DEFAULT_SIZE);
        this.fixtureCollections = new FixtureCollections(fixture, multipleCount);
    }

    @Test
    public void add_many_to_collection_populates_an_existing_collection() {
        ArrayList<String> list = new ArrayList<String>();
        this.fixtureCollections.addManyTo(list, String.class);
        assertEquals(DEFAULT_SIZE, list.size());
    }

    @Test
    public void add_many_to_collection_populates_an_existing_collection_with_size() {
        ArrayList<String> list = new ArrayList<String>();
        int size = 5;
        this.fixtureCollections.addManyTo(list, String.class, size);
        assertEquals(size, list.size());
    }

    @Test
    public void create_collection_populates_a_new_collection() {
        Collection<String> collection = this.fixtureCollections.createCollection(String.class);
        assertEquals(DEFAULT_SIZE, collection.size());
    }

    @Test
    public void create_collection_populates_a_new_collection_with_size() {
        int size = 5;
        Collection<String> collection = this.fixtureCollections.createCollection(String.class, size);
        assertEquals(size, collection.size());
    }

    @Test
    public void create_collection_populates_a_new_collection_of_specified_type() {
        HashSet<String> collection = this.fixtureCollections.createCollection(HashSet.class, String.class);
        assertEquals(DEFAULT_SIZE, collection.size());
    }

    @Test
    public void add_many_to_map_populates_an_existing_map() {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        this.fixtureCollections.addManyTo(map, Integer.class, String.class);
        assertEquals(DEFAULT_SIZE, map.size());
    }

    @Test
    public void add_many_to_map_populates_an_existing_map_with_size() {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        int size = 5;
        this.fixtureCollections.addManyTo(map, Integer.class, String.class, size);
        assertEquals(size, map.size());
    }

    @Test
    public void create_map_populates_a_new_map() {
        Map<Integer, String> map = this.fixtureCollections.createMap(Integer.class, String.class);
        assertEquals(DEFAULT_SIZE, map.size());
    }

    @Test
    public void create_map_populates_a_new_map_with_size() {
        int size = 5;
        Map<Integer, String> map = this.fixtureCollections.createMap(Integer.class, String.class, size);
        assertEquals(size, map.size());
    }

    @Test
    public void create_map_populates_a_new_map_of_specified_type() {
        TreeMap<Integer, String> collection = this.fixtureCollections.createMap(TreeMap.class, Integer.class, String.class);
        assertEquals(DEFAULT_SIZE, collection.size());
    }
}
