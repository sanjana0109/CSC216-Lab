package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import java.util.stream.*;
import org.junit.Test;

/**
 * Tests the CourseNameValidator class
 *
 * @author Mike Babb
 * @author Sanjana Cheerla
 * @author Sami Gephart
 */
public class CourseNameValidatorTest {

	/**
	 * Tests the CourseNameValidator valid cases
	 */
	@Test
	public void testFSMTransition1() {
		var prefix = "abcd";
		var digits = "123";
		var suffix = "a";

		var sb = new StringBuilder();

		var validator = new CourseNameValidator();

		for (var a : prefix.toCharArray()) {
			sb.insert(0, a);

			IntStream.range(0, 2).forEach((i) -> {
				var s = sb.toString() + digits;
				s += i % 2 == 0 ? suffix : "";

				try {
					assertTrue(validator.isValid(s));
				} catch (InvalidTransitionException e) {
					fail();
				}
			});
		}
	}

	/**
	 * Tests the CourseNameValidator invalid cases
	 *
	 */
	@Test
	public void testInvalidCourseName(){
		var nameValidator = new CourseNameValidator();
		var i = 1;

		try {
			assertFalse(nameValidator.isValid(""));
		} catch (InvalidTransitionException e) {
			assertTrue(i + 1 == 2);
		}

		try {
			assertFalse(nameValidator.isValid("123d"));
		} catch (InvalidTransitionException e) {
			assertTrue(i + 1 == 2);
		}

		try {
			assertFalse(nameValidator.isValid("1bc123d"));
		} catch (InvalidTransitionException e) {
			assertTrue(i + 1 == 2);
		}

		try {
			assertFalse(nameValidator.isValid("a2c123d"));
		} catch (InvalidTransitionException e) {
			assertTrue(i + 1 == 2);
		}

		try {
			assertFalse(nameValidator.isValid("ab3123d"));
		} catch (InvalidTransitionException e) {
			assertTrue(i + 1 == 2);
		}

		try {
			assertFalse(nameValidator.isValid("abcde123d"));
		} catch (InvalidTransitionException e) {
			assertTrue(i + 1 == 2);
		}

		try {
			assertFalse(nameValidator.isValid("abcd1e3d"));
		} catch (InvalidTransitionException e) {
			assertTrue(i + 1 == 2);
		}

		try {
			assertFalse(nameValidator.isValid("abcd12ed"));
		} catch (InvalidTransitionException e) {
			assertTrue(i + 1 == 2);
		}

		try {
			assertFalse(nameValidator.isValid("abcd1234"));
		} catch (InvalidTransitionException e) {
			assertTrue(i + 1 == 2);
		}

		try {
			assertFalse(nameValidator.isValid("!@b?d1#<4"));
		} catch (InvalidTransitionException e) {
			assertTrue(i + 1 == 2);
		}

		try {
			assertFalse(nameValidator.isValid(null));
		} catch (InvalidTransitionException e) {
			assertTrue(i + 1 == 2);
		}

		try {
			assertFalse(nameValidator.isValid("abcd123aa"));
		} catch (InvalidTransitionException e) {
			assertTrue(i + 1 == 2);
		}

		try {
			assertFalse(nameValidator.isValid("abcd123a1"));
		} catch (InvalidTransitionException e) {
			assertTrue(i + 1 == 2);
		}
	}
}