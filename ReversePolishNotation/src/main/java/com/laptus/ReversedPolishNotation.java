package com.laptus;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.function.DoubleBinaryOperator;

public class ReversedPolishNotation {
	private ReversedPolishNotation() {
	}

	public static Double calc(String input) {
		Deque<Double> numbers = new LinkedList<>();
		Arrays.asList(input.split(" ")).stream().forEach(number -> {
			switch (number) {
			case "+":
				calcSign(numbers, (n1, n2) -> n2 + n1);
				break;
			case "-":
				calcSign(numbers, (n1, n2) -> n2 - n1);
				break;
			case "*":
				calcSign(numbers, (n1, n2) -> n2 * n1);
				break;
			case "/":
				calcSign(numbers, (n1, n2) -> n2 / n1);
				break;
			default:
				numbers.push(Double.parseDouble(number));
			}

		});

		return numbers.pop();
	}

	protected static Deque<Double> calcSign(Deque<Double> numbers, DoubleBinaryOperator operation) {
		numbers.push(operation.applyAsDouble(numbers.pop(), numbers.pop()));
		return numbers;
	}
}
