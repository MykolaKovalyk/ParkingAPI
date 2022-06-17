package com.lpnu.iot.parking.structure;

import com.lpnu.iot.parking.resources.Resource;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import lombok.Getter;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class CSVRepository<Res extends Resource> {

    protected Map<Long, Res> dataTable = new HashMap<>();

    @Getter
    protected String filePath;

    @Getter
    protected long idSequence = 0;



    public Map<Long, Res> findAll() {
        return Collections.unmodifiableMap(dataTable);
    }

    public Res findById(Long id) {
        return dataTable.get(id);
    }

    public void save(Res newResource) {
        idSequence++;
        newResource.id = idSequence;
        dataTable.put(idSequence, newResource);
    }

    public void replace(Long idToReplace, Res newResource) {
        dataTable.replace(idToReplace, newResource);
    }

    public void readDataFromTable() throws FileNotFoundException, IOException, CsvValidationException {

        FileReader fileReader = new FileReader(filePath);
        CSVReader reader = new CSVReader(fileReader);

        var seq = reader.readNext()[0];
        idSequence = Long.parseLong(seq);

        String[] record;
        while ((record = reader.readNext()) !=  null) {

            Res newResource = createNewResource();
            newResource.fromListOfStrings(record);

            dataTable.put(newResource.id, newResource);
        }

        reader.close();
        fileReader.close();
    }

    public void saveDataToFile() throws IOException {

        FileWriter fileWriter = new FileWriter(filePath);
        CSVWriter writer = new CSVWriter(fileWriter);

        writer.writeNext(new String[] { String.valueOf(idSequence), }, false);

        for (Map.Entry<Long, Res> entry : dataTable.entrySet()) {
            writer.writeNext(entry.getValue().toListOfStrings(), false);
        }

        writer.close();
        fileWriter.close();
    }

    protected  abstract Res createNewResource();
}
