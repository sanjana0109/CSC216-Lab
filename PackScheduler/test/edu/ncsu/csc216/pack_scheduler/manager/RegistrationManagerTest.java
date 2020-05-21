package edu.ncsu.csc216.pack_scheduler.manager;

import static org.junit.Assert.*;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the RegistrationManager.java class
 *
 * @author sggephar
 * @author mbabb
 * @author scheerl
 * @author Nicholas Loftin
 *
 */
public class RegistrationManagerTest {

	/** Valid course records */
	private final String validTestFileCC = "test-files/course_records.txt";

	/** Valid course records */
	private final String validTestFileSD = "test-files/student_records.txt";

	/** First name of student */
	private static final String FIRST = "First";

	/** Last name of student */
	private static final String LAST = "Last";

	/** Id for student */
	private static final String ID = "flast";

	/** Email for student */
	private static final String EMAIL = "Email@ncsu.edu";

	/** Password for Student */
	private static final String PW = "hashedPassword";

	/** Max credits for Student */
	private static final int MAX_CREDITS = 18;

//	/** Properties file for registrar */
//	private static final String PROP_FILE = "registrar.properties";

	/**
	 * The registration manager being tested
	 */
	private RegistrationManager manager;

	/**
	 * Sets up the CourseManager and clears the data.
	 *
	 * @throws Exception if error
	 */
	@Before
	public void setUp() throws Exception {
		manager = RegistrationManager.getInstance();
		manager.logout();
		manager.clearData();
	}

	/**
	 * Tests the GetCourseCatalog() method
	 */
	@Test
	public void testGetCourseCatalog() {
		var cc = manager.getCourseCatalog();
		cc.loadCoursesFromFile(validTestFileCC);
		assertEquals(8, cc.getCourseCatalog().length);
	}

	/**
	 * Tests the GetStudentDirectory() method
	 */
	@Test
	public void testGetStudentDirectory() {
		var sd = manager.getStudentDirectory();
		sd.loadStudentsFromFile(validTestFileSD);
		assertEquals(10, sd.getStudentDirectory().length);
	}

	/**
	 * Tests the login() student method
	 */
	@Test
	public void testLoginStudent() {
		assertNull(manager.getCurrentUser());

		var sd = manager.getStudentDirectory();
		sd.addStudent(FIRST, LAST, ID, EMAIL, PW, PW, MAX_CREDITS);
		assertEquals(1, sd.getStudentDirectory().length);

		assertTrue(manager.login(ID, PW));
		assertEquals(sd.getStudentById(ID), manager.getCurrentUser());
		var sd1 = manager.getStudentDirectory();
		sd1.addStudent("alex", "p", "alexp", EMAIL, PW, PW, MAX_CREDITS);
		assertFalse(manager.login("alexp", PW));
		manager.logout();

		try {
			assertFalse(manager.login("holp", PW));
			manager.logout();
		} catch (IllegalArgumentException e) {
			assertEquals("User doesn't exist.", e.getMessage());
		}

		assertFalse(manager.login(ID, "tmp"));
		manager.logout();

//        Properties prop = new Properties();
//
//        try (InputStream input = new FileInputStream(PROP_FILE)) {
//            prop.load(input);
//
//            String pw = prop.getProperty("pw");
//
//            String id = prop.getProperty("id");
//
//            assertNull(manager.getCurrentUser());
//
//            assertTrue(manager.login(id, pw));
//            manager.logout();
//
//        } catch (IOException e) {
//            throw new IllegalArgumentException("Cannot load file.");
//        }
	}

	/**
	 * Tests login as faculty
	 */
	@Test
	public void testFacultyLogin() {
		FacultyDirectory fd = manager.getFacultyDirectory();
		fd.addFaculty(FIRST, LAST, ID, EMAIL, PW, PW, 2);
		assertTrue(manager.login(ID, PW));
		assertEquals(manager.getCurrentUser().getEmail(), EMAIL);
	}

	/**
	 * Tests the login() registrar method
	 */
//    @Test public void testLoginRegistrar()
//    {
//        Properties prop = new Properties();
//
//        try (InputStream input = new FileInputStream(PROP_FILE)) {
//            prop.load(input);
//
//            String pw = prop.getProperty("pw");
//
//            String id = prop.getProperty("id");
//
//            assertNull(manager.getCurrentUser());
//
//            assertTrue(manager.login(id, pw));
//            manager.logout();
//
//        } catch (IOException e) {
//            throw new IllegalArgumentException("Cannot load file.");
//        }
//    }

	/**
	 * Tests the logout() method
	 */
	@Test
	public void testLogout() {
		var sd = manager.getStudentDirectory();
		sd.addStudent(FIRST, LAST, ID, EMAIL, PW, PW, MAX_CREDITS);
		assertEquals(1, sd.getStudentDirectory().length);

		assertTrue(manager.login(ID, PW));
		manager.logout();
		assertNull(manager.getCurrentUser());
	}

	/**
	 * Tests the getCurrentUser() method
	 */
	@Test
	public void testGetCurrentUser() {
		var sd = manager.getStudentDirectory();
		sd.addStudent(FIRST, LAST, ID, EMAIL, PW, PW, MAX_CREDITS);
		assertEquals(1, sd.getStudentDirectory().length);

		assertTrue(manager.login(ID, PW));
		assertEquals(sd.getStudentById(ID), manager.getCurrentUser());
		manager.logout();

		sd.addStudent(FIRST, LAST, ID, EMAIL, PW, PW, MAX_CREDITS);
		assertEquals(1, sd.getStudentDirectory().length);

		assertTrue(manager.login(ID, PW));
		manager.logout();
		assertNull(manager.getCurrentUser());
	}

	/**
	 * Tests RegistrationManager.enrollStudentInCourse()
	 */
	@Test
	public void testEnrollStudentInCourse() {
		StudentDirectory directory = manager.getStudentDirectory();
		directory.loadStudentsFromFile("test-files/student_records.txt");

		CourseCatalog catalog = manager.getCourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records.txt");

		manager.logout(); // In case not handled elsewhere

		// test if not logged in
		try {
			manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001"));
			fail("RegistrationManager.enrollStudentInCourse() - If the current user is null, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertNull("RegistrationManager.enrollStudentInCourse() - currentUser is null, so cannot enroll in course.",
					manager.getCurrentUser());
		}

		// test if registrar is logged in
		manager.login("registrar", "Regi5tr@r");
		try {
			manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001"));
			fail("RegistrationManager.enrollStudentInCourse() - If the current user is registrar, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertEquals(
					"RegistrationManager.enrollStudentInCourse() - currentUser is registrar, so cannot enroll in course.",
					"registrar", manager.getCurrentUser().getId());
		}
		manager.logout();

		manager.login("efrost", "pw");
		assertFalse(
				"Action: enroll\nUser: efrost\nCourse: CSC216-001\nResults: False - Student max credits are 3 and course has 4.",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertTrue("Action: enroll\nUser: efrost\nCourse: CSC226-001\nResults: True",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse(
				"Action: enroll\nUser: efrost\nCourse: CSC226-001, CSC230-001\nResults: False - cannot exceed max student credits.",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));

		// Check Student Schedule
		Student efrost = directory.getStudentById("efrost");
		Schedule scheduleFrost = efrost.getSchedule();
		assertEquals(3, scheduleFrost.getScheduleCredits());
		String[][] scheduleFrostArray = scheduleFrost.getScheduledCourses();
		assertEquals(1, scheduleFrostArray.length);
		assertEquals("CSC226", scheduleFrostArray[0][0]);
		assertEquals("001", scheduleFrostArray[0][1]);
		assertEquals("Discrete Mathematics for Computer Scientists", scheduleFrostArray[0][2]);
		assertEquals("MWF 9:35AM-10:25AM", scheduleFrostArray[0][3]);
		assertEquals("9", scheduleFrostArray[0][4]);

		manager.logout();

		manager.login("ahicks", "pw");
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001\nResults: True",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001\nResults: True",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC226-001\nResults: False - duplicate",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-001\nResults: False - time conflict",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\nResults: True",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "003")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC116-002\nResults: False - already in section of 116",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "601")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC230-001\nResults: False - exceeded max credits",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));

		// Check Student Schedule
		Student ahicks = directory.getStudentById("ahicks");
		Schedule scheduleHicks = ahicks.getSchedule();
		assertEquals(10, scheduleHicks.getScheduleCredits());
		String[][] scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals(3, scheduleHicksArray.length);
		assertEquals("CSC216", scheduleHicksArray[0][0]);
		assertEquals("001", scheduleHicksArray[0][1]);
		assertEquals("Programming Concepts - Java", scheduleHicksArray[0][2]);
		assertEquals("TH 1:30PM-2:45PM", scheduleHicksArray[0][3]);
		assertEquals("9", scheduleHicksArray[0][4]);
		assertEquals("CSC226", scheduleHicksArray[1][0]);
		assertEquals("001", scheduleHicksArray[1][1]);
		assertEquals("Discrete Mathematics for Computer Scientists", scheduleHicksArray[1][2]);
		assertEquals("MWF 9:35AM-10:25AM", scheduleHicksArray[1][3]);
		assertEquals("8", scheduleHicksArray[1][4]);
		assertEquals("CSC116", scheduleHicksArray[2][0]);
		assertEquals("003", scheduleHicksArray[2][1]);
		assertEquals("Intro to Programming - Java", scheduleHicksArray[2][2]);
		assertEquals("TH 11:20AM-1:10PM", scheduleHicksArray[2][3]);
		assertEquals("9", scheduleHicksArray[2][4]);

		manager.logout();
	}

	/**
	 * Tests RegistrationManager.dropStudentFromCourse()
	 */
	@Test
	public void testDropStudentFromCourse() {
		StudentDirectory directory = manager.getStudentDirectory();
		directory.loadStudentsFromFile("test-files/student_records.txt");

		CourseCatalog catalog = manager.getCourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records.txt");

		manager.logout(); // In case not handled elsewhere

		// test if not logged in
		try {
			manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001"));
			fail("RegistrationManager.dropStudentFromCourse() - If the current user is null, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertNull("RegistrationManager.dropStudentFromCourse() - currentUser is null, so cannot enroll in course.",
					manager.getCurrentUser());
		}

		// test if registrar is logged in
		manager.login("registrar", "Regi5tr@r");
		try {
			manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001"));
			fail("RegistrationManager.dropStudentFromCourse() - If the current user is registrar, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertEquals(
					"RegistrationManager.dropStudentFromCourse() - currentUser is registrar, so cannot enroll in course.",
					"registrar", manager.getCurrentUser().getId());
		}
		manager.logout();

		manager.login("efrost", "pw");
		assertFalse(
				"Action: enroll\nUser: efrost\nCourse: CSC216-001\nResults: False - Student max credits are 3 and course has 4.",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertTrue("Action: enroll\nUser: efrost\nCourse: CSC226-001\nResults: True",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse(
				"Action: enroll\nUser: efrost\nCourse: CSC226-001, CSC230-001\nResults: False - cannot exceed max student credits.",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));

		assertFalse(
				"Action: drop\nUser: efrost\nCourse: CSC216-001\nResults: False - student not enrolled in the course",
				manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertTrue("Action: drop\nUser: efrost\nCourse: CSC226-001\nResults: True",
				manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC226", "001")));

		// Check Student Schedule
		Student efrost = directory.getStudentById("efrost");
		Schedule scheduleFrost = efrost.getSchedule();
		assertEquals(0, scheduleFrost.getScheduleCredits());
		String[][] scheduleFrostArray = scheduleFrost.getScheduledCourses();
		assertEquals(0, scheduleFrostArray.length);

		manager.logout();

		manager.login("ahicks", "pw");
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001\nResults: True",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001\nResults: True",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC226-001\nResults: False - duplicate",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-001\nResults: False - time conflict",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\nResults: True",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "003")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC116-002\nResults: False - already in section of 116",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "601")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC230-001\nResults: False - exceeded max credits",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));

		Student ahicks = directory.getStudentById("ahicks");
		Schedule scheduleHicks = ahicks.getSchedule();
		assertEquals(10, scheduleHicks.getScheduleCredits());
		String[][] scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals(3, scheduleHicksArray.length);
		assertEquals("CSC216", scheduleHicksArray[0][0]);
		assertEquals("001", scheduleHicksArray[0][1]);
		assertEquals("Programming Concepts - Java", scheduleHicksArray[0][2]);
		assertEquals("TH 1:30PM-2:45PM", scheduleHicksArray[0][3]);
		assertEquals("9", scheduleHicksArray[0][4]);
		assertEquals("CSC226", scheduleHicksArray[1][0]);
		assertEquals("001", scheduleHicksArray[1][1]);
		assertEquals("Discrete Mathematics for Computer Scientists", scheduleHicksArray[1][2]);
		assertEquals("MWF 9:35AM-10:25AM", scheduleHicksArray[1][3]);
		assertEquals("9", scheduleHicksArray[1][4]);
		assertEquals("CSC116", scheduleHicksArray[2][0]);
		assertEquals("003", scheduleHicksArray[2][1]);
		assertEquals("Intro to Programming - Java", scheduleHicksArray[2][2]);
		assertEquals("TH 11:20AM-1:10PM", scheduleHicksArray[2][3]);
		assertEquals("9", scheduleHicksArray[2][4]);

		assertTrue("Action: drop\nUser: efrost\nCourse: CSC226-001\nResults: True",
				manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC226", "001")));

		// Check schedule
		ahicks = directory.getStudentById("ahicks");
		scheduleHicks = ahicks.getSchedule();
		assertEquals(7, scheduleHicks.getScheduleCredits());
		scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals(2, scheduleHicksArray.length);
		assertEquals("CSC216", scheduleHicksArray[0][0]);
		assertEquals("001", scheduleHicksArray[0][1]);
		assertEquals("Programming Concepts - Java", scheduleHicksArray[0][2]);
		assertEquals("TH 1:30PM-2:45PM", scheduleHicksArray[0][3]);
		assertEquals("9", scheduleHicksArray[0][4]);
		assertEquals("CSC116", scheduleHicksArray[1][0]);
		assertEquals("003", scheduleHicksArray[1][1]);
		assertEquals("Intro to Programming - Java", scheduleHicksArray[1][2]);
		assertEquals("TH 11:20AM-1:10PM", scheduleHicksArray[1][3]);
		assertEquals("9", scheduleHicksArray[1][4]);

		assertFalse("Action: drop\nUser: efrost\nCourse: CSC226-001\nResults: False - already dropped",
				manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC226", "001")));

		assertTrue("Action: drop\nUser: efrost\nCourse: CSC216-001\nResults: True",
				manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001")));

		// Check schedule
		ahicks = directory.getStudentById("ahicks");
		scheduleHicks = ahicks.getSchedule();
		assertEquals(3, scheduleHicks.getScheduleCredits());
		scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals(1, scheduleHicksArray.length);
		assertEquals("CSC116", scheduleHicksArray[0][0]);
		assertEquals("003", scheduleHicksArray[0][1]);
		assertEquals("Intro to Programming - Java", scheduleHicksArray[0][2]);
		assertEquals("TH 11:20AM-1:10PM", scheduleHicksArray[0][3]);
		assertEquals("9", scheduleHicksArray[0][4]);

		assertTrue("Action: drop\nUser: efrost\nCourse: CSC116-003\nResults: True",
				manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC116", "003")));

		// Check schedule
		ahicks = directory.getStudentById("ahicks");
		scheduleHicks = ahicks.getSchedule();
		assertEquals(0, scheduleHicks.getScheduleCredits());
		scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals(0, scheduleHicksArray.length);

		manager.logout();
	}

	/**
	 * Tests RegistrationManager.resetSchedule()
	 */
	@Test
	public void testResetSchedule() {
		StudentDirectory directory = manager.getStudentDirectory();
		directory.loadStudentsFromFile("test-files/student_records.txt");

		CourseCatalog catalog = manager.getCourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records.txt");

		manager.logout(); // In case not handled elsewhere

		// Test if not logged in
		try {
			manager.resetSchedule();
			fail("RegistrationManager.resetSchedule() - If the current user is null, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertNull("RegistrationManager.resetSchedule() - currentUser is null, so cannot enroll in course.",
					manager.getCurrentUser());
		}

		// test if registrar is logged in
		manager.login("registrar", "Regi5tr@r");
		try {
			manager.resetSchedule();
			fail("RegistrationManager.resetSchedule() - If the current user is registrar, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertEquals("RegistrationManager.resetSchedule() - currentUser is registrar, so cannot enroll in course.",
					"registrar", manager.getCurrentUser().getId());
		}
		manager.logout();

		manager.login("efrost", "pw");
		assertFalse(
				"Action: enroll\nUser: efrost\nCourse: CSC216-001\nResults: False - Student max credits are 3 and course has 4.",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertTrue("Action: enroll\nUser: efrost\nCourse: CSC226-001\nResults: True",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse(
				"Action: enroll\nUser: efrost\nCourse: CSC226-001, CSC230-001\nResults: False - cannot exceed max student credits.",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));

		manager.resetSchedule();
		// Check Student Schedule
		Student efrost = directory.getStudentById("efrost");
		Schedule scheduleFrost = efrost.getSchedule();
		assertEquals(0, scheduleFrost.getScheduleCredits());
		String[][] scheduleFrostArray = scheduleFrost.getScheduledCourses();
		assertEquals(0, scheduleFrostArray.length);

		manager.logout();

		manager.login("ahicks", "pw");
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001\nResults: True",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001\nResults: True",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC226-001\nResults: False - duplicate",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-001\nResults: False - time conflict",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\nResults: True",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "003")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC116-002\nResults: False - already in section of 116",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "601")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC230-001\nResults: False - exceeded max credits",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));

		manager.resetSchedule();
		// Check Student schedule
		Student ahicks = directory.getStudentById("ahicks");
		Schedule scheduleHicks = ahicks.getSchedule();
		assertEquals(0, scheduleHicks.getScheduleCredits());
		String[][] scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals(0, scheduleHicksArray.length);

		manager.logout();
	}

	/**
	 * Tests add faculty to course
	 */
	@Test
	public void testAddFacultyToCourse() {
		FacultyDirectory fd = manager.getFacultyDirectory();
		fd.addFaculty("Wolf", "Scheduler", "registrar", "registrar@ncsu.edu", "Regi5tr@r", "Regi5tr@r", 2);
		assertTrue(manager.login("registrar", "Regi5tr@r"));
		assertEquals(manager.getCurrentUser().getEmail(), "registrar@ncsu.edu");
		CourseCatalog catalog = manager.getCourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records.txt");
		manager.addFacultyToCourse(catalog.getCourseFromCatalog("CSC116", "001"), fd.getFacultyById("registrar"));
	}

	/**
	 * Tests remove faculty to course
	 */
	@Test
	public void testRemoveFacultyFromCourse() {
		FacultyDirectory fd = manager.getFacultyDirectory();
		fd.addFaculty("Wolf", "Scheduler", "registrar", "registrar@ncsu.edu", "Regi5tr@r", "Regi5tr@r", 2);
		assertTrue(manager.login("registrar", "Regi5tr@r"));
		assertEquals(manager.getCurrentUser().getEmail(), "registrar@ncsu.edu");
		CourseCatalog catalog = manager.getCourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records.txt");
		//manager.addFacultyToCourse(catalog.getCourseFromCatalog("CSC116", "001"), fd.getFacultyById("registrar"));
		manager.removeFacultyFromCourse(catalog.getCourseFromCatalog("CSC116", "001"), fd.getFacultyById("registrar"));
	}

	/**
	 * Tests reset Faculty schedule
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testResetFacultySchedule() {
		FacultyDirectory fd = manager.getFacultyDirectory();
		fd.addFaculty("Wolf", "Scheduler", "registrar", "registrar@ncsu.edu", "Regi5tr@r", "Regi5tr@r", 2);
		assertTrue(manager.login("registrar", "Regi5tr@r"));
		assertEquals(manager.getCurrentUser().getEmail(), "registrar@ncsu.edu");
		manager.resetFacultySchedule(fd.getFacultyById("registrar"));
	}
}
