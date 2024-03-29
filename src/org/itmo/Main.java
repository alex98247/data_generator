package org.itmo;

import org.itmo.service.PersonGeneratorImpl;
import org.itmo.type.GeneratorResult;
import org.itmo.type.Person;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        int total = 1000;
        int duplicates = 50;
        int maxClusterSize = 10;
        PersonGenerator generator = new PersonGeneratorImpl();
        GeneratorResult generatorResult = generator.generate(total, duplicates, maxClusterSize);
        List<Person> people = generatorResult.getGeneratedItems();

        try (PrintWriter personWriter = new PrintWriter("out.txt");
             PrintWriter groundTruthWriter = new PrintWriter("ground_truth.txt")) {
            people.forEach(p -> personWriter.println(personToCsvRow(p)));
            generatorResult.getGroundTruth().forEach(p -> groundTruthWriter.println(personToCsvRow(p)));
        }

        System.out.printf("Total count: %d, \n Clusters count: %d%n", people.size(), generatorResult.getClusterInfos().size());
    }

    private static String personToCsvRow(Person person) {
        return String.format("%s, %s, %s, %s", person.getClusterId(), person.getSurname(), person.getName(), person.getPatronymic());
    }
}
