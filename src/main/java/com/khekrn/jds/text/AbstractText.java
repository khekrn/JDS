package com.khekrn.jds.text;

import com.khekrn.jds.IDataStructure;
import com.khekrn.jds.text.tokenizer.Tokenizer;
import com.khekrn.jds.text.tokenizer.impl.RegexTokenizer;
import com.khekrn.jds.utils.StringUtils;
import com.khekrn.jds.utils.Validate;

import java.util.HashMap;
import java.util.Map;

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
     * <p> Generates the vector from com.khekrn.jds.text</p>
     *
     * @param text input com.khekrn.jds.text
     * @return character count vector in {@link Map} with {@link CharSequence} as key and {@link Integer} as value
     */
    public Map<CharSequence, Integer> generateVectorFromText(CharSequence text){
        Validate.isTrue(StringUtils.isNotBlank(text), "Required com.khekrn.jds.text is missing");

        final var vector = new HashMap<CharSequence, Integer>();
        var charArray = tokenizer.tokenize(text);
        for(CharSequence charSequence : charArray){
            if(vector.containsKey(charSequence)){
                vector.put(charSequence, vector.get(charSequence)+1);
            }else{
                vector.put(charSequence, 1);
            }
        }
        return vector;
    }

}
