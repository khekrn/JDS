package com.khekrn.jds.text;

import com.khekrn.jds.IDataStructure;
import com.khekrn.jds.text.tokenizer.Tokenizer;
import com.khekrn.jds.text.tokenizer.impl.RegexTokenizer;
import com.khekrn.jds.utils.StringUtils;
import com.khekrn.jds.utils.Validate;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author khekrn
 */
public abstract class AbstractText implements IDataStructure {

    private final Tokenizer<CharSequence> tokenizer;

    protected AbstractText() {
        tokenizer = new RegexTokenizer();
    }

    public abstract String name();

    /**
     * <p> Generates the vector from text</p>
     *
     * @param text input text
     * @return character count vector in {@link Map} with {@link CharSequence} as key and {@link Long} as value
     */
    public Map<CharSequence, Long> generateVectorFromText(CharSequence text) {
        Validate.isTrue(StringUtils.isNotBlank(text), "Required text is missing");

        var charSequenceStream = tokenizer.tokenize(text);

        return charSequenceStream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

}
