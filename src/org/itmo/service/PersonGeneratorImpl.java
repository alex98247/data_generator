package org.itmo.service;

import org.itmo.MistakeProcessor;
import org.itmo.PersonGenerator;
import org.itmo.type.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

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
    public List<Person> generate(int totalCount, int duplicatesCount) {
        List<Person> result = new ArrayList<>(totalCount);
        for (int i = 0; i < duplicatesCount; i++) {
            Person person = getRandomPerson();
            result.add(person);
            result.add(mistakeProcessor.process(person));
        }

        int unique = totalCount - 2 * duplicatesCount;
        for (int i = 0; i < unique; i++) {
            result.add(getRandomPerson());
        }

        return result;
    }

    private void loadDictionary(List<String> dictionary, String path) throws IOException {
        BufferedReader namesReader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = namesReader.readLine()) != null) {
            dictionary.add(line);
        }
        namesReader.close();
    }

    private String getRandomElement(List<String> dictionary) {
        return dictionary.get(rand.nextInt(dictionary.size()));
    }

    private Person getRandomPerson() {
        Person person = new Person();
        person.setId(UUID.randomUUID());
        person.setClusterId(UUID.randomUUID());
        person.setName(getRandomElement(names));
        person.setSurname(getRandomElement(surnames));
        person.setPatronymic(getRandomElement(patronymics));
        person.setDateOfBirth(new Date());
        person.setPhone("89111111111");

        return person;
    }
}
