package testresources;

import com.lpnu.iot.parking.structure.CSVRepository;

public class TestingRepository extends CSVRepository<TestingResource> {

    public TestingRepository() {
        super("test_file.csv");
    }


    @Override
    protected TestingResource createNewResource() {
        return new TestingResource();
    }
}