package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for Student.java, tests all public setters and getters and
 * constructors.
 * 
 * @author scheerl
 * @author waclem
 * @author znchang
 *
 */
public class StudentTest {

	/**First name of student	 */
	private static final String FIRST = "First";
	
	/**Last name of student */
	private static final String LAST = "Last";
	
	/**Id for student */
	private static final String ID = "flast";
	
	/**Email for student */
	private static final String EMAIL = "Email@ncsu.edu";
	
	/**Password for Student */
	private static final String PW = "hashedPassword";
	
	/**Max credits for Student */
	private static final int MAX_CREDITS = 18;

	/**
	 * Tests the Hash code method
	 */
	@Test
	public void testHashCode() {
		User s1 = new Student(FIRST, LAST, ID, EMAIL, PW, MAX_CREDITS);
		User s2 = new Student(FIRST, LAST, ID, EMAIL, PW, MAX_CREDITS);

		User s3 = new Student("Bob", LAST, ID, EMAIL, PW, MAX_CREDITS);
		User s4 = new Student(FIRST, "Harvey", ID, EMAIL, PW, MAX_CREDITS);
		User s5 = new Student(FIRST, LAST, "bhharvey", EMAIL, PW, MAX_CREDITS);
		User s6 = new Student(FIRST, LAST, ID, "bharvey@ncsu.edu", PW, MAX_CREDITS);
		User s7 = new Student(FIRST, LAST, ID, EMAIL, "thisismypassword", MAX_CREDITS);
		User s8 = new Student(FIRST, LAST, ID, EMAIL, PW, 10);

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

	/**
	 * Tests the Constructor with max credits
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {
		Student s = null;
		try {
			s = new Student(null, LAST, ID, EMAIL, PW, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		s = null;
		try {
			s = new Student(FIRST, LAST, null, EMAIL, PW, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		s = null;
		try {
			s = new Student(FIRST, LAST, "", EMAIL, PW, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		s = null;
		try {
			s = new Student(FIRST, LAST, ID, EMAIL, PW);
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PW, s.getPassword());
			assertEquals(MAX_CREDITS, s.getMaxCredits());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests the Constructor without max credits.
	 */
	@Test
	public void testStudentStringStringStringStringString() {
		User s = null;
		try {
			s = new Student(null, LAST, ID, EMAIL, PW);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		s = null;
		try {
			s = new Student(FIRST, LAST, ID, EMAIL, PW);
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PW, s.getPassword());
		} catch (IllegalArgumentException e) {
			fail();
		}

	}

	/**
	 * Tests the set first name method
	 */
	@Test
	public void testSetFirstName() {
		User s = new Student(FIRST, LAST, ID, EMAIL, PW);
		assertEquals(FIRST, s.getFirstName());
		assertEquals(LAST, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PW, s.getPassword());

		try {
			s.setFirstName(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PW, s.getPassword());
		}

		try {
			s.setFirstName("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PW, s.getPassword());
		}

		s.setFirstName("Bob");
		assertEquals("Bob", s.getFirstName());
		assertEquals(LAST, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PW, s.getPassword());
	}

	/**
	 * Tests the set last name method
	 */
	@Test
	public void testSetLastName() {
		User s = new Student(FIRST, LAST, ID, EMAIL, PW);
		assertEquals(FIRST, s.getFirstName());
		assertEquals(LAST, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PW, s.getPassword());

		try {
			s.setLastName(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PW, s.getPassword());
		}

		try {
			s.setLastName("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PW, s.getPassword());
		}

		s.setLastName("Harvey");
		assertEquals(FIRST, s.getFirstName());
		assertEquals("Harvey", s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PW, s.getPassword());
	}
	
	/**
	 * Tests the set email method
	 */
	@Test
	public void testSetEmail() {
		User s = new Student(FIRST, LAST, ID, EMAIL, PW);
		assertEquals(FIRST, s.getFirstName());
		assertEquals(LAST, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PW, s.getPassword());

		try {
			s.setEmail(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PW, s.getPassword());
		}

		try {
			s.setEmail("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PW, s.getPassword());
		}

		try {
			s.setEmail("bharveyncsu.edu");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PW, s.getPassword());
		}

		try {
			s.setEmail("bharvey@ncsuedu");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PW, s.getPassword());
		}

		try {
			s.setEmail("bharvey.ncsu@edu");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PW, s.getPassword());
		}

		s.setEmail("bharvey@ncsu.edu");
		assertEquals(FIRST, s.getFirstName());
		assertEquals(LAST, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals("bharvey@ncsu.edu", s.getEmail());
		assertEquals(PW, s.getPassword());
	}

	/**
	 * Tests the set password method
	 */
	@Test
	public void testSetPassword() {
		User s = new Student(FIRST, LAST, ID, EMAIL, PW);
		assertEquals(FIRST, s.getFirstName());
		assertEquals(LAST, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PW, s.getPassword());

		try {
			s.setPassword(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PW, s.getPassword());
		}

		try {
			s.setPassword("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PW, s.getPassword());
		}

		s.setPassword("thisismypassword");
		assertEquals(FIRST, s.getFirstName());
		assertEquals(LAST, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals("thisismypassword", s.getPassword());
	}

	/**
	 * Tests the sex max credits method
	 */
	@Test
	public void testSetMaxCredits() {
		Student s = new Student(FIRST, LAST, ID, EMAIL, PW, MAX_CREDITS);
		try {
			s.setMaxCredits(2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PW, s.getPassword());
			assertEquals(MAX_CREDITS, s.getMaxCredits());
		}

		try {
			s.setMaxCredits(19);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PW, s.getPassword());
			assertEquals(MAX_CREDITS, s.getMaxCredits());
		}

		s.setMaxCredits(10);
		assertEquals(FIRST, s.getFirstName());
		assertEquals(LAST, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PW, s.getPassword());
		assertEquals(10, s.getMaxCredits());
	}

	/**
	 * Tests the equals method
	 */
	@Test
	public void testEqualsObject() {
		User s1 = new Student(FIRST, LAST, ID, EMAIL, PW, MAX_CREDITS);
		User s2 = new Student(FIRST, LAST, ID, EMAIL, PW, MAX_CREDITS);

		User s3 = new Student("Bob", LAST, ID, EMAIL, PW, MAX_CREDITS);
		User s4 = new Student(FIRST, "Harvey", ID, EMAIL, PW, MAX_CREDITS);
		User s5 = new Student(FIRST, LAST, "bhharvey", EMAIL, PW, MAX_CREDITS);
		User s6 = new Student(FIRST, LAST, ID, "bharvey@ncsu.edu", PW, MAX_CREDITS);
		User s7 = new Student(FIRST, LAST, ID, EMAIL, "thisismypassword", MAX_CREDITS);
		User s8 = new Student(FIRST, LAST, ID, EMAIL, PW, 10);

		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));

		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
/**
		// test to check with a different type
		String i = FIRST;
		assertFalse(s1.equals(i));
		*/

		// test to check with null
		User n = null;
		assertFalse(s1.equals(n));

		// test to self
		assertTrue(s1.equals(s1));

	}

	/**
	 * Tests the toString method
	 */
	@Test
	public void testToString() {
		User s1 = new Student(FIRST, LAST, ID, EMAIL, PW, MAX_CREDITS);
		String toStringS1 = "First,Last,flast,Email@ncsu.edu,hashedPassword,18";
		assertEquals(s1.toString(), toStringS1);

		User s2 = new Student(FIRST, LAST, ID, EMAIL, PW);
		String toStringS2 = "First,Last,flast,Email@ncsu.edu,hashedPassword,18";
		assertEquals(s2.toString(), toStringS2);
	}
	
	/**
	 * Tests compareTo method
	 */
	@Test
	public void testCompareTo() {
		Student s1 = new Student("Ann", "Albert", "aalbert", "aalbert@ncsu.edu", PW, MAX_CREDITS);
		Student s2 = new Student("Bob", "Barnes", "bbarnes", "bbarnes@ncsu.edu", PW, MAX_CREDITS);
		Student s3 = new Student("Ann", "Albert", "aalbert", "aalbert@ncsu.edu", PW, MAX_CREDITS);
		
		assertTrue(s2.compareTo(s1) > 0);
		assertTrue(s1.compareTo(s2) < 0);
		assertTrue(s1.compareTo(s3) == 0);
		assertTrue(s3.compareTo(s1) == 0);
	}

}
