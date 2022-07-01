package testresources;

import com.lpnu.iot.parking.structure.repositories.CSVRepository;

public class TestingRepository extends CSVRepository<TestingResource> {

    public TestingRepository() {
        super("test");
    }

    @Override
    protected TestingResource createNewResource() {
        return new TestingResource();
    }
}