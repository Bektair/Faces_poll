package examples.regular;

import org.junit.jupiter.params.converter.TypedArgumentConverter;

public class ToLengthArgumentConverter extends TypedArgumentConverter<String, Integer> {

    protected ToLengthArgumentConverter() {
        super(String.class, Integer.class);
    }

    //Source input comes from tag over method that calls it in parameter
    @Override
    protected Integer convert(String source) {
        return (source != null ? source.length() : 0);
    }

}

