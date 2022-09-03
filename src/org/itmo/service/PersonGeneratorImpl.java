package org.itmo.service;

import org.itmo.MistakeProcessor;
import org.itmo.PersonGenerator;
import org.itmo.type.ClusterInfo;
import org.itmo.type.GeneratorResult;
import org.itmo.type.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Alexey Mironov
 */
public class PersonGeneratorImpl implements PersonGenerator {
    private final Random rand = new Random();
    private final List<String> names;
    private final List<String> surnames;
    private final List<String> patronymics;
    private final MistakeProcessor mistakeProcessor;

    public PersonGeneratorImpl() throws IOException {
        mistakeProcessor = new MistakeProcessorImpl();
        names = new ArrayList<>();
        surnames = new ArrayList<>();
        patronymics = new ArrayList<>();

        loadDictionary(names, "names.txt");
        loadDictionary(surnames, "surnames.txt");
        loadDictionary(patronymics, "patronymics.txt");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GeneratorResult generate(int totalCount, int clusterCount, int maxClusterSize) {
        long clusterId = 0;
        GeneratorResult result = new GeneratorResult();
        for (int i = 0; i < clusterCount; i++) {
            Person person = getRandomPerson(clusterId);
            clusterId++;
            int clusterSize = ThreadLocalRandom.current().nextInt(1, maxClusterSize);

            List<Person> clusterItems = new ArrayList<>(clusterSize + 1);
            clusterItems.add(person);
            result.addGroundTruth(person);
            totalCount--;

            for (int j = 0; j < clusterSize; j++) {
                clusterItems.add(mistakeProcessor.process(person));
                totalCount--;
            }

            result.addClusterInfo(new ClusterInfo(clusterItems));
        }

        for (int i = totalCount; i > 0; i--) {
            List<Person> clusterItems = Collections.singletonList(getRandomPerson(clusterId));
            clusterId++;
            result.addClusterInfo(new ClusterInfo(clusterItems));
        }

        return result;
    }

    private void loadDictionary(List<String> dictionary, String path) throws IOException {
        try (BufferedReader namesReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = namesReader.readLine()) != null) {
                dictionary.add(line);
            }
        }
    }

    private String getRandomElement(List<String> dictionary) {
        return dictionary.get(rand.nextInt(dictionary.size()));
    }

    private Person getRandomPerson(long clusterId) {
        Person person = new Person();
        person.setId(UUID.randomUUID());
        person.setClusterId(clusterId);
        person.setName(getRandomElement(names));
        person.setSurname(getRandomElement(surnames));
        person.setPatronymic(getRandomElement(patronymics));
        person.setDateOfBirth(new Date());
        person.setPhone("89111111111");

        return person;
    }
}
