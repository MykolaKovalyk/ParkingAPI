package com.lpnu.iot.parking.structure;

import com.lpnu.iot.parking.resources.Resource;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class CSVRepository<Res extends Resource> {

    private Map<Long, Res> dataTable = new HashMap<>();

    @Getter
    @Setter(AccessLevel.PROTECTED)
    private String filePath;

    @Getter
    private long idSequence = 0;



    public CSVRepository(String filePath) {
        this.filePath = filePath;

        try {
            var rootDir = new File(filePath).getParent();
            if (rootDir != null && rootDir.length() > 0) {
                new File(rootDir).mkdirs();
            }
        } catch (Exception e) {
            System.out.println(this.getClass().toString() + ": Failed to create dirs:");
            e.printStackTrace();
        }

        try {
            readDataFromFile();
        } catch (FileNotFoundException exc) {
            try {
                System.out.println(this.getClass().toString() + ": CSV file not found, creating a new one...");
                saveDataToFile();
            } catch (Exception internalExc) {
                System.out.println(this.getClass().toString() + ": An error occurred while initializing a file:");
                internalExc.printStackTrace();
            }
        } catch (Exception exc) {
            System.out.println(this.getClass().toString() + ": An error occurred while reading a file:");
            exc.printStackTrace();
        }
    }

    public void saveToFileIfNecessary() {
        try {
            saveDataToFile();
        } catch (Exception exc) {
            System.out.println(this.getClass().toString() + "An error occurred while saving:");
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

        Map<Long, Res> returned =  new HashMap<>();
        for (var pair : dataTable.entrySet()) {
            if (predicate.test(pair.getValue())) {
                returned.put(pair.getKey(), pair.getValue());
            }
        }

        return returned;
    }

    public Res findAny(Predicate<Res> predicate) {

        for (var pair : dataTable.entrySet()) {
            if (predicate.test(pair.getValue())) {
               return pair.getValue();
            }
        }

        return null;
    }

    public Res save(Res newResource) {
        idSequence++;
        newResource.setId(idSequence);
        dataTable.put(idSequence, newResource);

        return newResource;
    }

    public Res replace(Long idToReplace, Res newResource) {
        return dataTable.replace(idToReplace, newResource);
    }

    public Res remove(Long idToRemove) {
        return dataTable.remove(idToRemove);
    }

    public void readDataFromFile() throws IOException, CsvValidationException {
        try (FileReader fileReader = new FileReader(filePath);
             CSVReader reader = new CSVReader(fileReader)) {

            var names  = reader.readNext();
            var sequence = reader.readNext()[1];
            idSequence = Long.parseLong(sequence);

            String[] record;
            while ((record = reader.readNext()) != null) {

                Res newResource = createNewResource();
                newResource.fromArrayOfStrings(record);

                dataTable.put(newResource.getId(), newResource);
            }
        }
    }

    public void saveDataToFile() throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath);
             CSVWriter writer = new CSVWriter(fileWriter)) {

            writer.writeNext(createNewResource().fieldNamesToStringArray());
            writer.writeNext(new String[]{ "idSequence", String.valueOf(idSequence) }, false);

            for (Map.Entry<Long, Res> entry : dataTable.entrySet()) {
                writer.writeNext(entry.getValue().toArrayOfStrings(), false);
            }
        }
    }

    protected  abstract Res createNewResource();
}
