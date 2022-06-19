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
import java.util.function.Predicate;

public abstract class CSVRepository<Res extends Resource> {

    protected Map<Long, Res> dataTable = new HashMap<>();

    @Getter
    protected String filePath;

    @Getter
    protected long idSequence = 0;


    public CSVRepository(String filePath) {
        this.filePath = filePath;

        try {
            readDataFromTable();
        } catch (FileNotFoundException exc) {
            try {
                System.out.println(this.getClass().toString() + ": CSV file not found, creating a new one...");
                saveDataToFile();
            } catch (Exception internalExc) {
                System.out.println(this.getClass().toString() + "An error occurred while initializing a file:");
                internalExc.printStackTrace();
            }
        } catch (Exception exc) {
            System.out.println(this.getClass().toString() + "An error occurred while reading:");
            exc.printStackTrace();
        }
    }


    public Map<Long, Res> findAll() {
        return Collections.unmodifiableMap(dataTable);
    }

    public Res findById(Long id) {
        return dataTable.get(id);
    }

    public Map<Long, Res> findAll(Predicate<Res> predicate) {
        Map<Long, Res> all =  findAll();
        Map<Long, Res> returned =  new HashMap<>();

        for (var pair : all.entrySet()) {
            if(predicate.test(pair.getValue())) {
                returned.put(pair.getKey(), pair.getValue());
            }
        }

        return returned;
    }

    public Res findAny(Predicate<Res> predicate) {
        Map<Long, Res> all =  findAll();

        for (var pair : all.entrySet()) {
            if(predicate.test(pair.getValue())) {
               return pair.getValue();
            }
        }

        return null;
    }


    public Res save(Res newResource) {
        idSequence++;
        newResource.id = idSequence;
        dataTable.put(idSequence, newResource);

        return newResource;
    }

    public Res replace(Long idToReplace, Res newResource) {
        return dataTable.replace(idToReplace, newResource);
    }

    public Res remove(Long idToRemove) {
        return dataTable.remove(idToRemove);
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
