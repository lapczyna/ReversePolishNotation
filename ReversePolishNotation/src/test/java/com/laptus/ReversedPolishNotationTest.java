package com.laptus;

import static com.laptus.ReversedPolishNotation.calc;
import static com.laptus.ReversedPolishNotation.calcSign;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class ReversedPolishNotationTest {

	@Test
	public void shouldReturnStackWithTwoElementsPoppedAndOneElementPushed() {
		// when
		Deque<Double> numbers = new LinkedList<Double>();
		IntStream.rangeClosed(1, 5).forEach(number -> numbers.add((double) number));
		Deque<Double> actual = calcSign(numbers, (num1, num2) -> num2 / num1);

		// then
		assertThat(actual.size()).isEqualTo(4);
	}

	@Test
	public void shouldUseOperationParameterToCalculatePushedValue() {
		// when
		Deque<Double> numbers = new LinkedList<>();
		numbers.push((double) 15);
		numbers.push((double) 3);
		Deque<Double> actual = calcSign(numbers, (num1, num2) -> num2 / num1);

		// then
		assertThat(actual.pop()).isEqualTo(5);
	}

	@Test
	public void shouldBeAbleToCalculateSingleDigitNumbersSum() {
		assertThat(calc("1 2 +")).isEqualTo(3.0);
	}

	@Test
	public void shouldBeAbleToCalculateSingleDigitNumbersDifference() {
		assertThat(calc("4 2 -")).isEqualTo(2);
	}

	@Test
	public void shouldBeAbleToCalculateSingleDigitNumbersProduct() {
		assertThat(calc("4 3 *")).isEqualTo(12);
	}

	@Test
	public void shouldBeAbleToCalculateSingleDigitNumbersQuotient() {
		assertThat(calc("50 5 /")).isEqualTo(10);
	}

	@Test
	public void shouldBeAbleToHandleDecimalNumbers() {
		assertThat(calc("-45.9 3 /")).isEqualTo(-15.3, within(0.01));
	}

	@Test
	public void shouldBeAbleToHandleMoreComplexNotation() {
		assertThat(calc("1 2 + 3 * 4 * 6 /")).isEqualTo(6.0);
	}
}
