package com.khekrn.jds.text.tokenizer.impl;

import com.khekrn.jds.text.tokenizer.Tokenizer;
import com.khekrn.jds.utils.StringUtils;
import com.khekrn.jds.utils.Validate;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author khekrn
 */
public class RegexTokenizer implements Tokenizer<CharSequence> {

    private static final Pattern PATTERN = Pattern.compile("(\\w)+");

    /**
     * <p> Tokenize the given regex pattern</p>
     *
     * @param text input text to tokenize
     * @return tokenized {@link CharSequence} array
     */
    @Override
    public Stream<CharSequence> tokenize(CharSequence text) {
        Validate.isTrue(StringUtils.isNotBlank(text), "Required parameter is missing");

        final var matcher = PATTERN.matcher(text);
        final var tokenList = new ArrayList<CharSequence>();
        while (matcher.find()){
            tokenList.add(matcher.group(0));
        }
        return tokenList.stream();
    }
}
