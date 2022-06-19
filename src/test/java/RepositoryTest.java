import com.lpnu.iot.parking.resources.Resource;
import com.lpnu.iot.parking.structure.CSVRepository;
import junit.framework.TestCase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.management.OperationsException;
import java.io.File;
import java.io.FileNotFoundException;

@AllArgsConstructor
@NoArgsConstructor
class TestResource extends Resource {

    public int someInt;
    public String someString;
    public boolean someBoolean;

    @Override
    public String[] toListOfStrings() {
        return new String[] {
                Long.toString(id),
                Integer.toString(someInt),
                someString,
                Boolean.toString(someBoolean)
        };
    }

    @Override
    public void fromListOfStrings(String[] csv) {
        id =  Long.parseLong(csv[0]);
        someInt = Integer.parseInt(csv[1]);
        someString =  csv[2];
        someBoolean = Boolean.parseBoolean(csv[3]);
    }
}

class TestRepository extends CSVRepository<TestResource> {

    public TestRepository() {
        filePath =  "test_file.csv";

        try {
            readDataFromTable();
        } catch (FileNotFoundException exc) {
            try {
                System.out.println("CSV file not found, creating a new one...");
                saveDataToFile();
            } catch (Exception internalExc) {
                System.out.println("An error occurred while initializing a file:");
                internalExc.printStackTrace();
            }
        } catch (Exception exc) {
            System.out.println("An error occurred while reading:");
            exc.printStackTrace();
        }
    }


    @Override
    protected TestResource createNewResource() {
        return new TestResource();
    }
}



public class RepositoryTest extends TestCase {


    public  void testRepository() throws Exception {

        TestRepository repository = new TestRepository();

        var testSubjects =  new TestResource[] {
                new TestResource(-4, "William", true),
                new TestResource(5836, "Marcus", false),
                new TestResource(5, "Jenny, Robert", true)
        };

        repository.save(testSubjects[0]);
        repository.save(testSubjects[1]);
        repository.save(testSubjects[2]);

        repository.saveDataToFile();

        repository.readDataFromTable();

       var found =  repository.findAll();

        assertEquals(found.get(3L).someString, testSubjects[2].someString);
        assertTrue(found.get(1L).someString.equals(testSubjects[0].someString) &&
                found.get(1L).someBoolean == testSubjects[0].someBoolean &&
                found.get(1L).someInt == testSubjects[0].someInt);

        assertNull(found.get(0L));

        File toDelete =  new File(repository.getFilePath());
        if(!toDelete.delete()) {
            System.out.println("Warning: test file was not deleted!");
        } else {
            System.out.println("Test file was successfully deleted.");
        }
    }

}
