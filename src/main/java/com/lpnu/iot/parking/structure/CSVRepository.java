package com.lpnu.iot.parking.structure;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class CSVRepository<Resource extends com.lpnu.iot.parking.resources.Resource> {

    private static final SimpleDateFormat FILE_NAME_DATE_FORMAT = new SimpleDateFormat("yyyy_MM_dd");
    private static final SimpleDateFormat MONTH_DATE_FORMAT = new SimpleDateFormat("yyyy_MMM");
    private final Map<Long, Resource> dataTable = new HashMap<>();
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private String resourceRootPath;
    @Getter
    private long idSequence = 0;


    public CSVRepository(String filePath) {
        this.resourceRootPath = filePath;

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
        } catch (IOException exc) {
            try {
                System.out.println(this.getClass().toString() + ": CSV file not found, creating a new one...");
                writeDataToFile();
            } catch (Exception internalExc) {
                System.out.println(this.getClass().toString() + ": An error occurred while initializing a file:");
                internalExc.printStackTrace();
            }
        } catch (Exception exc) {
            System.out.println(this.getClass().toString() + ": An error occurred while reading a file:");
            exc.printStackTrace();
        }
    }


    public Map<Long, Resource> findAll() {
        return Collections.unmodifiableMap(dataTable);
    }

    public Resource findById(Long id) {
        return dataTable.get(id);
    }

    public Map<Long, Resource> findAll(Predicate<Resource> predicate) {

        Map<Long, Resource> returned = new HashMap<>();
        for (var pair : dataTable.entrySet()) {
            if (predicate.test(pair.getValue())) {
                returned.put(pair.getKey(), pair.getValue());
            }
        }
        return returned;
    }

    public Resource findAny(Predicate<Resource> predicate) {

        for (var pair : dataTable.entrySet()) {
            if (predicate.test(pair.getValue())) {
                return pair.getValue();
            }
        }
        return null;
    }

    public Resource add(Resource newResource) {
        idSequence++;
        newResource.setId(idSequence);
        dataTable.put(idSequence, newResource);

        return newResource;
    }

    public Resource replace(Long idToReplace, Resource newResource) {
        return dataTable.replace(idToReplace, newResource);
    }

    public Resource addOrGetIfPresent(Long id, Resource newResource) {
        return dataTable.putIfAbsent(id, newResource);
    }

    public Resource remove(Long idToRemove) {
        return dataTable.remove(idToRemove);
    }

    public void readDataFromFile() throws IOException, CsvValidationException {

        File tableRootDirectory = Paths.get(resourceRootPath, MONTH_DATE_FORMAT.format(new Date())).toFile();

        if (!tableRootDirectory.exists()) {
            throw new IOException("Directory not found!");
        }

        File[] tables = tableRootDirectory.listFiles();
        if (tables == null) {
            throw new IOException("tableRootDirectory is a file!");
        }

        dataTable.clear();
        for (var file : tables) {
            try (FileReader fileReader = new FileReader(file);
                 CSVReader reader = new CSVReader(fileReader)) {
                var names = reader.readNext();

                String[] record;
                while ((record = reader.readNext()) != null) {
                    Resource newResource = createNewResource();
                    newResource.setFieldValues(record);

                    if (newResource.getId() > idSequence) {
                        idSequence = newResource.getId();
                    }

                    dataTable.put(newResource.getId(), newResource);
                }
            }
        }
    }

    public void writeDataToFile() throws IOException {

        var currentDate = new Date();
        File tableFile = Paths.get(
                resourceRootPath,
                MONTH_DATE_FORMAT.format(currentDate),
                FILE_NAME_DATE_FORMAT.format(currentDate) + ".csv"
        ).toFile();

        if (!tableFile.exists()) {
            dataTable.clear();
            new File(tableFile.getParent()).mkdirs();
        }

        try (FileWriter fileWriter = new FileWriter(tableFile);
             CSVWriter writer = new CSVWriter(fileWriter)) {

            writer.writeNext(createNewResource().getFieldNames(), false);

            for (Map.Entry<Long, Resource> entry : dataTable.entrySet()) {
                writer.writeNext(entry.getValue().getFieldValues(), false);
            }
        }
    }

    protected abstract Resource createNewResource();
}
