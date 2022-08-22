package org.itmo;

import org.itmo.type.GeneratorResult;

/**
 * @author Alexey Mironov
 */
public interface PersonGenerator {
    GeneratorResult generate(int totalCount, int duplicatesCount, int maxClusterSize);
}
