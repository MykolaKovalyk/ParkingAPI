import org.junit.jupiter.api.*;
import testresources.TestingRepository;
import testresources.TestingResource;


import java.io.File;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;


public class TestRepository {

    private static TestingRepository repository;
    private  static TestingResource[] resources;

    @BeforeEach
    public void setUp() {
        repository = new TestingRepository();

        resources =  new TestingResource[] {
                new TestingResource(-4, "William", true),
                new TestingResource(5836, "Marcus", false),
                new TestingResource(7, "Jenny, Robert", true),
                new TestingResource(4, "Robert", true),
                new TestingResource(6, "Tracey", false),
                new TestingResource(5, "Terminator", true),
                new TestingResource(6, "Whatever,,,", false),
        };

        for (var resource: resources) {
            repository.add(resource);
        }
    }

    @AfterEach
    public void cleanUp() {
        deleteDirectory("test/");
    }


    @Test
    public void testRepositoryIO() throws Exception {

        WriteReadData();

        var found =  repository.findAll();

        assertEquals(found.get(3L).someString, resources[2].someString);
        assertTrue(found.get(1L).someString.equals(resources[0].someString) &&
                found.get(1L).someBoolean == resources[0].someBoolean &&
                found.get(1L).someInt == resources[0].someInt);

        assertNull(found.get(0L));
    }

    @Test
    public void testRepositoryFindAll() {

        var found = repository.findAll();

        for (var resource: resources) {
            assertEquals(resource.someString, resources[resource.getId().intValue() - 1].someString);
        }
    }

    @Test
    public void testRepositoryFindById() {

        var found =  repository.findById(4L);
        assertEquals(found.someString, resources[3].someString);

        found = repository.findById(5L);
        assertEquals(found.someString, resources[4].someString);

        found =  repository.findById(6L);
        assertEquals(found.someString, resources[5].someString);

        found =  repository.findById(8L);
        assertNull(found);
    }

    @Test
    public void testRepositoryFindAllByPredicate() {
        var found =  repository.findAll(resource -> resource.someBoolean);

        assertTrue(found.containsKey(1L));
        assertTrue(found.containsKey(3L));
        assertTrue(found.containsKey(4L));
        assertTrue(found.containsKey(6L));

        assertFalse(found.containsKey(2L));
        assertFalse(found.containsKey(5L));
        assertFalse(found.containsKey(7L));

        found = repository.findAll(resource -> resource.someInt == 6);

        assertTrue(found.containsKey(5L));
        assertTrue(found.containsKey(7L));

        assertFalse(found.containsKey(1L));
        assertFalse(found.containsKey(2L));
        assertFalse(found.containsKey(3L));
        assertFalse(found.containsKey(4L));
        assertFalse(found.containsKey(6L));
    }

    @Test
    public void testRepositoryFindAnyByPredicate() {

        var found =  repository.findAny(resource -> resource.someString.endsWith("rt"));
        assertEquals(found.someString, resources[2].someString);

        found = repository.findAny(resource -> resource.someInt == 6);
        assertEquals(found.someString, resources[4].someString);
    }

    @Test
    public void testRepositoryReplace() {
        var replacement =  new TestingResource(542, ";*!@#$%^&*()_+,.{}", true);
        var original = repository.replace(4L, replacement);

        var found = repository.findAny(resource -> resource.someInt == 542);
        assertEquals(found.someString, replacement.someString);

        assertEquals(repository.replace(4L, original).someString, replacement.someString);

        found = repository.findById(4L);
        assertEquals(found.someString, original.someString);
    }

    @Test
    public void testRepositoryRemoveAndAddIfPresent() {
        var removed  =  repository.remove(5L);

        assertNull(repository.findById(5L));

        assertEquals(repository.findById(1L).someString, resources[0].someString);
        assertEquals(repository.findById(2L).someString, resources[1].someString);
        assertEquals(repository.findById(3L).someString, resources[2].someString);
        assertEquals(repository.findById(4L).someString, resources[3].someString);
        assertEquals(repository.findById(6L).someString, resources[5].someString);
        assertEquals(repository.findById(7L).someString, resources[6].someString);

        assertNull(repository.addOrGetIfPresent(5L, removed));

        assertEquals(repository.findById(5L).someString, resources[4].someString);
    }


    private static  void WriteReadData() throws Exception {
        var calendar = Calendar.getInstance();

        var dayBefore = calendar.get(Calendar.DAY_OF_MONTH);
        repository.writeDataToFile();

        repository.readDataFromFile();
        var dayAfter = calendar.get(Calendar.DAY_OF_MONTH);

        if(dayBefore != dayAfter) {
            System.out.println("Edge case: day mismatch, repeating the test...");

            WriteReadData();
        }
    }

    private static void deleteDirectory(String path) {
        File root =  new File(path);
        File[] subfiles = root.listFiles();

        if (subfiles != null) {
            for (File subfile : subfiles) {

                if (subfile.isDirectory()) {
                    deleteDirectory(subfile.getAbsolutePath());
                }

                subfile.delete();
            }
        }

        root.delete();
    }

}
