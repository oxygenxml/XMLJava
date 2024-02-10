/*******************************************************************************
 * Copyright (c) 2022 - 2024 Maxprograms.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/org/documents/epl-v10.html
 *
 * Contributors:
 *     Maxprograms - initial API and implementation
 *******************************************************************************/
package com.maxprograms.xml;

import java.util.List;
import java.util.Vector;

public class DTDSecuence implements ContentParticle {

    private int cardinality;
    private List<ContentParticle> content;

    public DTDSecuence() {
        content = new Vector<>();
        cardinality = ContentModel.NONE;
    }

    public void addParticle(ContentParticle particle) {
        content.add(particle);
    }

    @Override
    public int getType() {
        return ContentParticle.SEQUENCE;
    }

    @Override
    public void setCardinality(int cardinality) {
        this.cardinality = cardinality;
    }

    @Override
    public int getCardinality() {
        return cardinality;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}
