package org.itmo;

import org.itmo.type.Person;

/**
 * @author Alexey Mironov
 */
public interface MistakeProcessor {
    Person process(Person person);
}
