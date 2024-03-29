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

import java.util.List;

/**
 * @author Alexey Mironov
 */
public class ClusterInfo {
    private List<Person> generatedItems;

    public ClusterInfo() {
        super();
    }

    public ClusterInfo(List<Person> generatedItems) {
        this.generatedItems = generatedItems;
    }

    public int getCount() {
        return generatedItems.size();
    }

    public List<Person> getGeneratedItems() {
        return generatedItems;
    }

    public void setGeneratedItems(List<Person> generatedItems) {
        this.generatedItems = generatedItems;
    }
}
