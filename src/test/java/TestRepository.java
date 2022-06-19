import com.lpnu.iot.parking.resources.Resource;
import com.lpnu.iot.parking.structure.CSVRepository;
import junit.framework.TestCase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;

@AllArgsConstructor
@NoArgsConstructor
class DecoyResource extends Resource {

    public int someInt;
    public String someString;
    public boolean someBoolean;

    @Override
    public String[] toArrayOfStrings() {
        return new String[] {
                Long.toString(getId()),
                Integer.toString(someInt),
                someString,
                Boolean.toString(someBoolean)
        };
    }

    @Override
    public void fromArrayOfStrings(String[] csv) {
        setId(Long.parseLong(csv[0]));
        someInt = Integer.parseInt(csv[1]);
        someString =  csv[2];
        someBoolean = Boolean.parseBoolean(csv[3]);
    }
}

class DecoyRepository extends CSVRepository<DecoyResource> {

    public DecoyRepository() {
        super("test_file.csv");
    }


    @Override
    protected DecoyResource createNewResource() {
        return new DecoyResource();
    }
}



public class TestRepository {

    @Test
    public  void testRepository() throws Exception {

        DecoyRepository repository = new DecoyRepository();

        var testSubjects =  new DecoyResource[] {
                new DecoyResource(-4, "William", true),
                new DecoyResource(5836, "Marcus", false),
                new DecoyResource(5, "Jenny, Robert", true)
        };

        repository.save(testSubjects[0]);
        repository.save(testSubjects[1]);
        repository.save(testSubjects[2]);

        repository.saveDataToFile();

        repository.readDataFromFile();

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
