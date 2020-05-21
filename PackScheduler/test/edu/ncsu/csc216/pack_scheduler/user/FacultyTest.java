/**
 *FacultyTest.java
 * @author Nicholas Loftin nlloftin@ncsu.edu
 * Version 1
 */
package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Faculty test class
 * @author Nicholas Loftin nlloftin@ncsu.edu
 *
 */
public class FacultyTest {

	/** First name of Faculty */
	private static final String FIRST = "First";

	/** Last name of Faculty */
	private static final String LAST = "Last";

	/** Id for Faculty */
	private static final String ID = "flast";

	/** Email for Faculty */
	private static final String EMAIL = "Email@ncsu.edu";

	/** Password for Faculty */
	private static final String PW = "hashedPassword";

	/** Max credits for Faculty */
	private static final int MAX_CREDITS = 2;

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.user.Faculty#Faculty(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	@Test
	public void testFaculty() {
		Faculty boye = new Faculty("Fors", "Laest", "ThisIsMyId", "Email@email.com", "hasheD", 2);
		@SuppressWarnings("unused")
		Faculty boye2 = null;
		try {
			boye2 = new Faculty(null, "Laest", "ThisIsMyId", "Email@email.com", "hasheD", 2);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		try {
			boye2 = new Faculty("Fors", "", "ThisIsMyId", "Email@email.com", "hasheD", 2);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		try {
			boye2 = new Faculty("Fors", "Laest", "", "Email@email.com", "hasheD", 2);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		try {
			boye2 = new Faculty("Fors", "Laest", "ThisIsMyId", "Emai.l@emailcom", "hasheD", 2);
		} catch (Exception e) {
			System.out.println(e.getMessage());

			assertTrue(e instanceof IllegalArgumentException);
		}
		try {
			boye2 = new Faculty("Fors", "Laest", "ThisIsMyId", "", "hasheD", 2);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		try {
			boye2 = new Faculty("Fors", "Laest", "ThisIsMyId", "emai@email.com", "", 2);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		try {
			boye2 = new Faculty("Fors", "Laest", "ThisIsMyId", "emaiemailcom", "", 2);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		try {
			boye2 = new Faculty("Fors", "Laest", "ThisIsMyId", "ema.@imailcom", "", 2);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		assertTrue(boye instanceof Faculty);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.user.Faculty#getMaxCourses()}.
	 */
	@Test
	public void testGetAndSetMaxCourses() {
		Faculty boye = new Faculty("Fors", "Laest", "ThisIsMyId", "Email@email.com", "hasheD", 2);
		assertEquals(2, boye.getMaxCourses());
		boye.setMaxCourses(1);
		assertEquals(1, boye.getMaxCourses());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.user.Faculty#toString()}.
	 */
	@Test
	public void testToString() {
		Faculty boye = new Faculty("Fors", "Laest", "ThisIsMyId", "Email@email.com", "hasheD", 2);
		assertEquals("Fors,Laest,ThisIsMyId,Email@email.com,hasheD,2", boye.toString());
	}

	/**
	 * Tests the equals method
	 */
	@Test
	public void testEqualsObject() {
		User s1 = new Faculty(FIRST, LAST, ID, EMAIL, PW, MAX_CREDITS);
		User s2 = new Faculty(FIRST, LAST, ID, EMAIL, PW, MAX_CREDITS);

		User s3 = new Faculty("Bob", LAST, ID, EMAIL, PW, MAX_CREDITS);
		User s4 = new Faculty(FIRST, "Harvey", ID, EMAIL, PW, MAX_CREDITS);
		User s5 = new Faculty(FIRST, LAST, "bhharvey", EMAIL, PW, MAX_CREDITS);
		User s6 = new Faculty(FIRST, LAST, ID, "bharvey@ncsu.edu", PW, MAX_CREDITS);
		User s7 = new Faculty(FIRST, LAST, ID, EMAIL, "thisismypassword", MAX_CREDITS);
		User s8 = new Faculty(FIRST, LAST, ID, EMAIL, PW, 1);

		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));

		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));

		// test to check with null
		User n = null;
		assertFalse(s1.equals(n));

		// test to self
		assertTrue(s1.equals(s1));

	}

	/**
	 * Tests the Hash code method
	 */
	@Test
	public void testHashCode() {
		User s1 = new Faculty(FIRST, LAST, ID, EMAIL, PW, MAX_CREDITS);
		User s2 = new Faculty(FIRST, LAST, ID, EMAIL, PW, MAX_CREDITS);

		User s3 = new Faculty("Bob", LAST, ID, EMAIL, PW, MAX_CREDITS);
		User s4 = new Faculty(FIRST, "Harvey", ID, EMAIL, PW, MAX_CREDITS);
		User s5 = new Faculty(FIRST, LAST, "bhharvey", EMAIL, PW, MAX_CREDITS);
		User s6 = new Faculty(FIRST, LAST, ID, "bharvey@ncsu.edu", PW, MAX_CREDITS);
		User s7 = new Faculty(FIRST, LAST, ID, EMAIL, "thisismypassword", MAX_CREDITS);
		User s8 = new Faculty(FIRST, LAST, ID, EMAIL, PW, 1);

		assertEquals(s1.hashCode(), s2.hashCode());

		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());

		s3 = null;
		s4 = null;
		s5 = null;
		s6 = null;
		s7 = null;
	}

}
