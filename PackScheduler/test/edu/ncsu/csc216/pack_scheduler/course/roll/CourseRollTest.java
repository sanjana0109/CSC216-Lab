/**
 *
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import org.junit.Test;

/**
 * Tests the CourseRoll class
 *
 * @author sggephar
 * @author scheerl
 * @author mbabb
 * @author Nicholas Loftin
 *
 */
public class CourseRollTest {

	/** Expected results for valid courses */
	// private final String validStudent0 =
	// "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	/** Valid Student String 1 */
	// private final String validStudent1 =
	// "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	/** Valid Student String 2 */
	// private final String validStudent2 =
	// "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	/** Valid Student String 3 */
	// private final String validStudent3 =
	// "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	/** Valid Student String 4 */
	// private final String validStudent4 =
	// "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";

	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 4;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "MW";
	/** Enrollment cap for a course */
	private static final int ENROLLMENT_CAP = 100;
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;

	/**
	 * Course being added
	 */
	private Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS,
			START_TIME, END_TIME);

	/**
	 * Test method for creating a course roll object
	 */
	@Test
	public final void testCourseRoll() {
		CourseRoll c = new CourseRoll(10, course);
		assertNotNull(c);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll#getEnrollmentCap()}.
	 */
	@Test
	public final void testGetEnrollmentCap() {
		CourseRoll c1 = new CourseRoll(10, course);
		assertEquals(10, c1.getEnrollmentCap());

		CourseRoll c2 = new CourseRoll(250, course);
		assertEquals(250, c2.getEnrollmentCap());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll#setEnrollmentCap(int)}.
	 */
	@Test
	public final void testSetEnrollmentCap() {
		CourseRoll c = new CourseRoll(10, course);
		c.setEnrollmentCap(20);
		assertEquals(20, c.getEnrollmentCap());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll#enroll(edu.ncsu.csc216.pack_scheduler.user.Student)}.
	 */
	@Test
	public final void testEnroll() {
		CourseRoll c = new CourseRoll(10, course);

		Student s0 = new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", 15);
		Student s1 = new Student("Cassandra", "Schwartz", "cschwartz", "semper@imperdietornare.co.uk", "pw", 4);
		Student s2 = new Student("Shannon", "Hansen", "shansen", "convallis.est.vitae@arcu.ca", "pw", 4);
		Student s3 = new Student("Demetrius", "Austin", "daustin", "Curabitur.egestas.nunc@placeratorcilacus.co.uk",
				"pw", 18);
		Student s4 = new Student("Raymond", "Brennan", "rbrennan", "litora.torquent@pellentesquemassalobortis.ca", "pw",
				12);

		c.enroll(s0);
		c.enroll(s1);
		c.enroll(s2);
		c.enroll(s3);
		c.enroll(s4);

		assertEquals(5, c.getOpenSeats());
		assertEquals(10, c.getEnrollmentCap());

		try {
			Student s = null;
			c.enroll(s);
			fail();

		} catch (IllegalArgumentException e) {
			assertEquals(5, c.getOpenSeats());
			assertEquals(10, c.getEnrollmentCap());
			assertEquals(0, c.getNumberOnWaitlist());
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll#drop(edu.ncsu.csc216.pack_scheduler.user.Student)}.
	 */
	@Test
	public final void testDrop() {
		CourseRoll c = new CourseRoll(10, course);

		Student s0 = new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", 15);
		Student s1 = new Student("Cassandra", "Schwartz", "cschwartz", "semper@imperdietornare.co.uk", "pw", 4);
		Student s2 = new Student("Shannon", "Hansen", "shansen", "convallis.est.vitae@arcu.ca", "pw", 4);
		Student s3 = new Student("Demetrius", "Austin", "daustin", "Curabitur.egestas.nunc@placeratorcilacus.co.uk",
				"pw", 18);
		Student s4 = new Student("Raymond", "Brennan", "rbrennan", "litora.torquent@pellentesquemassalobortis.ca", "pw",
				12);

		c.enroll(s0);
		c.enroll(s1);
		c.enroll(s2);
		c.enroll(s3);
		c.enroll(s4);

		assertEquals(5, c.getOpenSeats());

		c.drop(s1);
		assertEquals(6, c.getOpenSeats());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll#canEnroll(edu.ncsu.csc216.pack_scheduler.user.Student)}.
	 */
	@Test
	public final void testCanEnroll() {
		CourseRoll c = new CourseRoll(10, course);

		Student s0 = new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", 15);
		Student s1 = new Student("Cassandra", "Schwartz", "cschwartz", "semper@imperdietornare.co.uk", "pw", 4);
		Student s2 = new Student("Shannon", "Hansen", "shansen", "convallis.est.vitae@arcu.ca", "pw", 4);
		Student s3 = new Student("Demetrius", "Austin", "daustin", "Curabitur.egestas.nunc@placeratorcilacus.co.uk",
				"pw", 18);
		Student s4 = new Student("Raymond", "Brennan", "rbrennan", "litora.torquent@pellentesquemassalobortis.ca", "pw",
				12);

		Student s5 = new Student("R", "Brennan", "rbrennan", "litora.torquent@pellentesquemassalobortis.ca", "pw", 12);

		Student s6 = new Student("Ra", "Brennan", "rbrennan", "litora.torquent@pellentesquemassalobortis.ca", "pw", 12);

		Student s7 = new Student("Ray", "Brennan", "rbrennan", "litora.torquent@pellentesquemassalobortis.ca", "pw",
				12);

		Student s8 = new Student("Raym", "Brennan", "rbrennan", "litora.torquent@pellentesquemassalobortis.ca", "pw",
				12);

		Student s9 = new Student("Raymo", "Brennan", "rbrennan", "litora.torquent@pellentesquemassalobortis.ca", "pw",
				12);

		Student s10 = new Student("Raymon", "Brennan", "rbrennan", "litora.torquent@pellentesquemassalobortis.ca", "pw",
				12);

		c.enroll(s0);
		assertTrue(c.canEnroll(s1));
		c.enroll(s1);
		assertTrue(c.canEnroll(s2));
		c.enroll(s2);
		assertTrue(c.canEnroll(s3));
		c.enroll(s3);
		assertTrue(c.canEnroll(s4));
		c.enroll(s4);
		assertTrue(c.canEnroll(s5));
		c.enroll(s5);
		assertTrue(c.canEnroll(s6));
		c.enroll(s6);
		assertTrue(c.canEnroll(s7));
		c.enroll(s7);
		assertTrue(c.canEnroll(s8));
		c.enroll(s8);
		assertTrue(c.canEnroll(s9));
		c.enroll(s10);
		assertFalse(c.canEnroll(s10));
	}
	/**
	 * Extra Tests for coverage
	 * Testing enrolling too many students
	 */
	@Test
	public final void testCoverage() {
		CourseRoll c = new CourseRoll(10, course);

		Student s0 = new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", 15);
		Student s1 = new Student("Cassandra", "Schwartz", "cschwartz", "semper@imperdietornare.co.uk", "pw", 4);
		Student s2 = new Student("Shannon", "Hansen", "shansen", "convallis.est.vitae@arcu.ca", "pw", 4);
		Student s3 = new Student("Demetrius", "Austin", "daustin", "Curabitur.egestas.nunc@placeratorcilacus.co.uk",
				"pw", 18);
		Student s4 = new Student("Raymond", "Brennan", "rbrennan", "litora.torquent@pellentesquemassalobortis.ca", "pw",
				12);

		Student s5 = new Student("R", "Brennan", "rbrennan", "litora.torquent@pellentesquemassalobortis.ca", "pw", 12);

		Student s6 = new Student("Ra", "Brennan", "rbrennan", "litora.torquent@pellentesquemassalobortis.ca", "pw", 12);

		Student s7 = new Student("Ray", "Brennan", "rbrennan", "litora.torquent@pellentesquemassalobortis.ca", "pw",
				12);

		Student s8 = new Student("Raym", "Brennan", "rbrennan", "litora.torquent@pellentesquemassalobortis.ca", "pw",
				12);

		Student s9 = new Student("Raymo", "Brennan", "rbrennan", "litora.torquent@pellentesquemassalobortis.ca", "pw",
				12);

		Student s10 = new Student("Raymon", "Brennan", "rbrennan", "litora.torquent@pellentesquemassalobortis.ca", "pw",
				12);
		c.enroll(s0);
		c.enroll(s1);
		c.enroll(s2);
		c.enroll(s3);
		c.enroll(s4);
		c.enroll(s5);
		c.enroll(s6);
		c.enroll(s7);
		c.enroll(s8);
		c.enroll(s9);
		c.enroll(s10);
		assertEquals(c.getNumberOnWaitlist(), 1);
		c.drop(s0);
		assertEquals(0, c.getNumberOnWaitlist());
		
	}
}
