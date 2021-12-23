package examples.regular;

import static org.junit.jupiter.api.Assertions.*;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;



class TestParametersAndStuff {

	//First indexed args, then aggregator, then parameterresolver
	//Indexed args are Arguements/argumentsproviders, aggregators are argumentaccessors or tagged
	
	//Consumes arguments usually
	@ParameterizedTest
	@ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
	void test(String candidate) {
		assertTrue(candidate.length()<300);

	}
	
	//value is array of primitive types, String and Class, sent once at a time
	//if you wanna reuse add (autoCloseArguments = false)
	@ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
	void testReuse(String candidate) {
		assertTrue(candidate.length()<300);
	}
	
	@ParameterizedTest(autoCloseArguments = false)
	@NullAndEmptySource //Tests sending edgecases for non primitive types
	@ValueSource(strings = { " ", "   ", "\t", "\n" }) //if decimal literal converted into numeric types
	void testEmptyString(String text) {
		//Should be a normal method that takes a string
		 assertTrue(text == null || text.trim().isEmpty());
	}	
	


	@ParameterizedTest
	@EnumSource(mode = Mode.MATCH_ALL, names = "^.*DAYS$")
	void testWithEnumSourceRegex(ChronoUnit unit) {
	    assertTrue(unit.name().endsWith("DAYS"));
	}
	
	
	@ParameterizedTest
	@MethodSource("stringProvider")
	void testWithExplicitLocalMethodSource(String argument) {
	    assertNotNull(argument);
	}
	//@MethodSource refers to a static/TestInstance class
	//That accepts no args
	static Stream<String> stringProvider() {
	    return Stream.of("apple", "banana");
	}


	//For multiple streamable arguments
	//Steamable like collection, iterable, iterator, stream etc
	@ParameterizedTest
	@MethodSource("stringIntAndListProvider")
	void testWithMultiArgMethodSource(String str, int num, List<String> list) {
	    assertEquals(5, str.length());
	    assertTrue(num >=1 && num <=2);
	    assertEquals(2, list.size());
	}
	
	//Named.of replaces the name of argument in test-list
	static Stream<Arguments> stringIntAndListProvider() {
	    return Stream.of(
    		Arguments.of(Named.of("Green" ,"apple"), 1, Arrays.asList("a", "b")),
    		Arguments.of(Named.of("Yellow" ,"lemon"), 2, Arrays.asList("x", "y"))
	    );
	}
	// External methods are referenced: package.class#Method
	// @MethodSource("example.StringsProviders#tinyStrings")
/////////////////////////////////////////////////////////////////////

	//Can format how it looks in test-log
//	@ParameterizedTest(name = "[{index}] => {argumentsWithNames}")
//	@CsvSource(useHeadersInDisplayName = true, textBlock = """
//	    FRUIT,         RANK
//	    apple,         1
//	    banana,        2
//	    'lemon, lime', 0xF1
//	    strawberry,    700_000
//	    """) // closing text block delimiter
//	void testWithCsvSource(String fruit, int rank) {
//	    assertNotNull(fruit);
//	    assertNotEquals(0, rank);
//	}

	//Csv file, uses quotation for strings with space in " "
	// Index makes it count for me
	@ParameterizedTest(name = "[{index}] {arguments}")
	@CsvFileSource(resources = "/two-column.csv", useHeadersInDisplayName = true)
	void testWithCsvFileSourceAndHeaders(String country, int reference) {
	    assertNotNull(country);
	    assertNotEquals(0, reference);
	}
	

	@ParameterizedTest
	@ArgumentsSource(MyArgumentsProvider.class)
	void testWithArgumentsSource(String argument) {
	    assertNotNull(argument);
	}

	@ParameterizedTest
	@ValueSource(strings = "superlong")
	void testLengthConversion(
			@ConvertWith(ToLengthArgumentConverter.class)int length) {
		assertTrue(length>5);
		
	}


	

}
