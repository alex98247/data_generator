package org.itmo;

import org.itmo.service.PersonGeneratorImpl;
import org.itmo.type.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        int total = 10;
        int duplicates = 3;
        PersonGenerator generator = new PersonGeneratorImpl();
        List<Person> people = generator.generate(total, duplicates);
        people.forEach(p -> System.out.printf("%s %s %s%n", p.getSurname(), p.getName(), p.getPatronymic()));
        Map<UUID, List<Person>> collect = people.stream().collect(Collectors.groupingBy(Person::getClusterId));

        process(people);
        people.forEach(p -> System.out.printf("%s %s %s%n", p.getSurname(), p.getName(), p.getPatronymic()));

        Map<UUID, UUID> result = new HashMap<>();

        for (int i = 0; i < people.size(); i++) {
            Person person = people.get(i);
            for (int j = i + 1; j < people.size(); j++) {
                Person person1 = people.get(j);
                if (Objects.equals(person.getName(), person1.getName()) &&
                        Objects.equals(person.getSurname(), person1.getSurname()) &&
                        Objects.equals(person.getPatronymic(), person1.getPatronymic())
                ) {
                    UUID value = result.getOrDefault(person.getId(), UUID.randomUUID());
                    result.put(person1.getId(), value);
                    result.put(person.getId(), value);
                }
            }
        }

        float trueResult = 0;

        for (var x : collect.entrySet()) {
            List<Person> personList = x.getValue();
            if (personList.size() < 2) {
                continue;
            }
            Person person = personList.get(0);
            Person person1 = personList.get(1);
            if (Objects.equals(result.get(person1.getId()), result.get(person.getId()))) {
                trueResult += 1;
            }
        }

        System.out.println(trueResult / duplicates);
    }

    private static List<String> ngrams(int n, String str) {
        List<String> ngrams = new ArrayList<>();
        for (int i = 0; i < str.length() - n + 1; i++)
            // Add the substring or size n
            ngrams.add(str.substring(i, i + n));
        // In each iteration, the window moves one step forward
        // Hence, each n-gram is added to the list

        return ngrams;
    }

    private static void process(List<Person> people) {
        people.forEach(person -> {
            int minName = ngrams(2, person.getName()).stream().mapToInt(String::hashCode).min().getAsInt();
            person.setName(String.valueOf(minName));
            int minSurname = ngrams(2, person.getSurname()).stream().mapToInt(String::hashCode).min().getAsInt();
            person.setSurname(String.valueOf(minSurname));
            int minPatronymic = ngrams(2, person.getPatronymic()).stream().mapToInt(String::hashCode).min().getAsInt();
            person.setPatronymic(String.valueOf(minPatronymic));
        });
    }
}
