package org.itmo;

import org.itmo.service.PersonGeneratorImpl;
import org.itmo.type.Person;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        int total = 10;
        int duplicates = 3;
        PersonGenerator generator = new PersonGeneratorImpl();
        List<Person> people = generator.generate(total, duplicates);
        people.forEach(p -> System.out.printf("%s %s %s%n", p.getSurname(), p.getName(), p.getPatronymic()));
    }
}
