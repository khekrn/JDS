package com.khekrn.jds.app;

import com.khekrn.jds.text.distance.CosineDistance;
import com.khekrn.jds.text.similarity.CosineSimilarity;

/**
 * @author khekrn
 */
public class Main {

    public static void main(String[] args) {
        var cosineSimilarity = CosineSimilarity.create();

        String text1 = "i love java coding";
        String text2 = "i love java java";

        var res = cosineSimilarity.apply(text1, text2);
        System.out.println("Similarity = " + res);

        var distance = CosineDistance.create();
        System.out.println("Distance = " + distance.apply(text1, text2));
    }
}
