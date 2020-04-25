package com.khekrn.jds.text.tokenizer;

import java.util.stream.Stream;

/**
 * @author khekrn
 */

@FunctionalInterface
public interface Tokenizer<T> {

    Stream<T> tokenize(CharSequence text);
}
