package testresources;

import com.lpnu.iot.parking.resources.Resource;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TestingResource extends Resource {

    public int someInt;
    public String someString;
    public boolean someBoolean;

    @Override
    public String[] getFieldValues() {
        return new String[] {
                Long.toString(getId()),
                Integer.toString(someInt),
                someString,
                Boolean.toString(someBoolean)
        };
    }

    @Override
    public String[] getFieldNames() {
        return new String[] {
                "someInt",
                "someString",
                "someBoolean"
        };
    }

    @Override
    public void setFieldValues(String[] csv) {
        setId(Long.parseLong(csv[0]));
        someInt = Integer.parseInt(csv[1]);
        someString =  csv[2];
        someBoolean = Boolean.parseBoolean(csv[3]);
    }
}


