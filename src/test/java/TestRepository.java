import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import testresources.TestingRepository;
import testresources.TestingResource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;


import java.io.File;




public class TestRepository {

    private static TestingRepository repository;
    private  static TestingResource[] testSubjects;

    @BeforeAll
    public static void setUp() {
        repository = new TestingRepository();

        testSubjects =  new TestingResource[] {
                new TestingResource(-4, "William", true),
                new TestingResource(5836, "Marcus", false),
                new TestingResource(5, "Jenny, Robert", true)
        };
    }

    @AfterAll
    public static void cleanUp() {
        File toDelete =  new File(repository.getResourceRootPath());
        if(!toDelete.delete()) {
            System.out.println("Warning: test file was not deleted!");
        } else {
            System.out.println("Test file was successfully deleted.");
        }
    }


    @Test
    public void testRepositoryWriteToFile() throws Exception {

        repository.save(testSubjects[0]);
        repository.save(testSubjects[1]);
        repository.save(testSubjects[2]);

        repository.saveToFileIfNecessary();

        repository.readDataFromFile();

        var found =  repository.findAll();

        assertEquals(found.get(3L).someString, testSubjects[2].someString);
        assertTrue(found.get(1L).someString.equals(testSubjects[0].someString) &&
                found.get(1L).someBoolean == testSubjects[0].someBoolean &&
                found.get(1L).someInt == testSubjects[0].someInt);

        assertNull(found.get(0L));
    }

}
