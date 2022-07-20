package org.itmo;

import org.itmo.service.PersonGeneratorImpl;
import org.itmo.type.Person;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        int total = 1000;
        int duplicates = 50;
        PersonGenerator generator = new PersonGeneratorImpl();
        List<Person> people = generator.generate(total, duplicates);

        PrintWriter writer = new PrintWriter("out.txt");
        people.forEach(p -> writer.println(String.format("%s, %s, %s", p.getSurname(), p.getName(), p.getPatronymic())));
        writer.close();
    }
}
