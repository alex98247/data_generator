/*
 * Unidata Platform Community Edition
 * Copyright (c) 2013-2022, UNIDATA LLC, All rights reserved.
 * This file is part of the Unidata Platform Community Edition software.
 *
 * Unidata Platform Community Edition is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Unidata Platform Community Edition is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package org.itmo.type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexey Mironov
 */
public class GeneratorResult {
    private List<ClusterInfo> clusterInfos = new ArrayList<>();
    private final List<Person> groundTruth = new ArrayList<>();

    public List<Person> getGeneratedItems() {
        return clusterInfos.stream()
                .flatMap(cluster -> cluster.getGeneratedItems().stream())
                .collect(Collectors.toList());
    }

    public long getTotalCount() {
        return clusterInfos.stream()
                .map(ClusterInfo::getCount)
                .reduce(0, Integer::sum);
    }

    public List<ClusterInfo> getClusterInfos() {
        return clusterInfos;
    }

    public void setClusterInfos(List<ClusterInfo> clusterInfos) {
        this.clusterInfos = clusterInfos;
    }

    public void addClusterInfo(ClusterInfo clusterInfo) {
        clusterInfos.add(clusterInfo);
    }

    public List<Person> getGroundTruth() {
        return groundTruth;
    }

    public void addGroundTruth(Person person) {
        groundTruth.add(person);
    }
}
