package examples.extensions;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

public class ToTestTemplate {
	final List<String> fruits = Arrays.asList("apple", "banana", "lemon");

	@TestTemplate
	@ExtendWith(MyTestTemplateInvocationContextProvider.class)
	void testTemplate(String fruit) {
	    assertTrue(fruits.contains(fruit));
	}
}
