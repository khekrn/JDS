package com.khekrn.jds.text;

import com.khekrn.jds.IDataStructure;

/**
 * @author khekrn
 *
 * Text related algorithms
 */
@FunctionalInterface
public interface IText {

    double apply(CharSequence text1, CharSequence text2);
}
