package examples.regular;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

public class DogAggregator implements ArgumentsAggregator {

	@Override
    public Dog aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
        return new Dog(arguments.getString(0),
                          arguments.getInteger(1));
    }
	
	
}
