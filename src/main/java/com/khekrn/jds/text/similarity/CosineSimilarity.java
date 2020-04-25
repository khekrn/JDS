package com.khekrn.jds.text.similarity;

import com.khekrn.jds.text.AbstractText;
import com.khekrn.jds.text.IText;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author khekrn
 * <p>
 * {@link CosineSimilarity} Measures the Cosine similarity of two vectors of an inner product space and compares the angle between them.
 * <p>
 * More on {link http://en.wikipedia.org/wiki/Cosine_similarity}
 */
public class CosineSimilarity extends AbstractText implements IText {

    private static final String DESCRIPTION = "CosineSimilarity - Measures the Cosine similarity of two vectors of an inner product space and compares the angle between them";

    private CosineSimilarity() {
    }

    public static CosineSimilarity create() {
        return new CosineSimilarity();
    }

    /**
     * Text similarity between two string via cosine similarity
     *
     * @param text1 input string
     * @param text2 comparison string
     * @return similarity threshold in double
     */
    @Override
    public double apply(CharSequence text1, CharSequence text2) {
        double cosineSimilarity = 0.0;

        final var leftVector = super.generateVectorFromText(text1);
        final var rightVector = super.generateVectorFromText(text2);

        final var intersection = getIntersection(leftVector, rightVector);

        final var dotProduct = dot(leftVector, rightVector, intersection);
        final var d1 = vectorDot(leftVector.values());
        final var d2 = vectorDot(rightVector.values());

        if (d1 >= 0.0 && d2 >= 0.0) {
            cosineSimilarity = dotProduct / (d1 * d2);
        }

        return cosineSimilarity;
    }

    @Override
    public String name() {
        return DESCRIPTION;
    }

    private Set<CharSequence> getIntersection(Map<CharSequence, Long> leftVector, Map<CharSequence, Long> rightVector) {
        var intersectionSet = new HashSet<>(leftVector.keySet());
        intersectionSet.retainAll(rightVector.keySet());
        return intersectionSet;
    }

    private double dot(Map<CharSequence, Long> leftVector, Map<CharSequence, Long> rightVector, Set<CharSequence> intersection) {
        double result = 0.0;
        for (CharSequence word : intersection) {
            result += (leftVector.get(word) * rightVector.get(word));
        }
        return result;
    }

    private double vectorDot(Collection<Long> vectorValues) {
        var sum = vectorValues.stream().mapToLong(x -> x).map(x -> x * x).sum();
        return Math.sqrt(sum);
    }

}
