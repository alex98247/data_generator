package org.itmo;

import org.itmo.type.Person;

import java.util.List;

/**
 * @author Alexey Mironov
 */
public interface PersonGenerator {
    List<Person> generate(int totalCount, int duplicatesCount);
}
