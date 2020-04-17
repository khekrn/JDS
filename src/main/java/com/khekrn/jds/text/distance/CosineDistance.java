package com.khekrn.jds.text.distance;

import com.khekrn.jds.text.AbstractText;
import com.khekrn.jds.text.IText;
import com.khekrn.jds.text.similarity.CosineSimilarity;

/**
 * @author khekrn
 *
 * <p>Calculates cosine distance using 1 - CosineSimilarity</p>
 */
public class CosineDistance extends AbstractText implements IText {

    private static final String DESCRIPTION = "Cosine Distance using 1 - CosineSimilarity";

    private static final CosineSimilarity COSINE_SIMILARITY = CosineSimilarity.create();

    private CosineDistance() {

    }

    public static CosineDistance create() {
        return new CosineDistance();
    }

    @Override
    public double apply(CharSequence text1, CharSequence text2) {
        return 1.0 - COSINE_SIMILARITY.apply(text1, text2);
    }

    @Override
    public String name() {
        return DESCRIPTION;
    }
}
