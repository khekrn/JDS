package com.khekrn.jds.text.tokenizer;

/**
 * @author khekrn
 */

@FunctionalInterface
public interface Tokenizer<T> {

    T[] tokenize(CharSequence text);
}
