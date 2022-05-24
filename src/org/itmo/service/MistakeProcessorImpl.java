package org.itmo.service;

import org.itmo.MistakeProcessor;
import org.itmo.type.Person;

import java.util.Random;

/**
 * @author Alexey Mironov
 */
public class MistakeProcessorImpl implements MistakeProcessor {

    private final String symbols = "абвгдежзийклмнопрстуфхцчшщэюя";

    private final Random random = new Random();

    /**
     * {@inheritDoc}
     */
    @Override
    public Person process(Person person) {
        Person result = new Person();
        result.setName(person.getName());
        result.setClusterId(person.getClusterId());
        result.setId(person.getId());
        result.setSurname(person.getSurname());
        result.setPatronymic(person.getPatronymic());
        result.setDateOfBirth(person.getDateOfBirth());
        result.setPhone(person.getPhone());

        int i = random.nextInt(3);
        if (i == 0) {
            result.setName(randomReplace(person.getName()));
        } else if (i == 2) {
            result.setSurname(randomReplace(person.getSurname()));
        } else {
            result.setPatronymic(randomReplace(person.getPatronymic()));
        }

        return result;
    }

    private String replaceChar(String str, char ch, int index) {
        return str.substring(0, index) + ch + str.substring(index + 1);
    }

    private String randomReplace(String str) {
        char randomChar = symbols.charAt(random.nextInt(symbols.length()));
        return replaceChar(str, randomChar, random.nextInt(str.length()));
    }
}
