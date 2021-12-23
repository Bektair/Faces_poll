package examples.regular;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

public class ArgumentsaggregationTest {
	//Can also aggregate them into a object passed into method
	//AGGREGATE METHOD INSERT


@ParameterizedTest 
@CsvSource({
    "Jane, 30",
    "John, 20"
})
void testWithArgumentsAccessor(ArgumentsAccessor arguments) {
   
	String name = arguments.getString(0);
	int age = arguments.getInteger(1);
	Dog dog = new Dog(name, age);
	if(age>25)
		assertEquals("Jane", dog.getName());
	else {
		assertEquals("John", dog.getName());
	}
	
}

//Aggregation can be used to return an object out of the args


@ParameterizedTest(name = "[{index}] => {argumentsWithNames}") 
@CsvSource({
    "Jane, 30",
    "John, 20"
}) //@AggregateWith(DogAggregator.class) can be implemented with composed annotation if used alot
void testWithArgumentsAggregator(@AggregateWith(DogAggregator.class) Dog dog) {
    // perform assertions against person
	if(dog.getAge()>25)
		assertEquals("Jane", dog.getName());
	else {
		assertEquals("John", dog.getName());
	}
}




	
	
	
	
}
