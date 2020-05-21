package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the CourseCatalog Class
 *
 * @author Walker Clem
 * @author Sanjana Cheerla
 * @author Zeyu Chang
 */
public class CourseCatalogTest {

	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";

	/**
	 * Resets course_records.txt for use in other tests.
	 * 
	 * @throws Exception if there is an issue loading the path
	 */
	@Before
	public void setUp() throws Exception {
		// Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests the getCourseFromCatalog() method
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog a = new CourseCatalog();
		Course b = new Course("name1", "title1", "101", 3, "instructorId1", 10, "M", 1500, 1600);
		Course c = new Course("name2", "title2", "102", 3, "instructorId2", 10, "W", 1500, 1600);
		a.addCourseToCatalog("name1", "title1", "101", 3, "instructorId1", 10, "M", 1500, 1600);
		a.addCourseToCatalog("name2", "title2", "102", 3, "instructorId2", 10, "W", 1500, 1600);
		assertEquals(b, a.getCourseFromCatalog("name1", "101"));
		assertEquals(c, a.getCourseFromCatalog("name2", "102"));
	}

	/**
	 * Tests the removeCourseFromCatalog() method
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog a = new CourseCatalog();
		a.addCourseToCatalog("name1", "title1", "101", 3, "instructorId1", 10, "M", 1500, 1600);
		a.addCourseToCatalog("name2", "title2", "102", 3, "instructorId2", 10, "W", 1500, 1600);
		assertTrue(a.removeCourseFromCatalog("name1", "101"));
		assertTrue(a.removeCourseFromCatalog("name2", "102"));
	}

	/**
	 * Tests the getCourseCatalog() method
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog a = new CourseCatalog();
		a.addCourseToCatalog("name1", "title1", "101", 3, "instructorId1", 10, "M", 1500, 1600);
		String[][] course = new String[1][5];
		course[0][0] = "name1";
		course[0][1] = "101";
		course[0][2] = "title1";
		course[0][3] = "M 3:00PM-4:00PM";
		course[0][4] = "10";
		assertArrayEquals(course, a.getCourseCatalog());
	}

	/**
	 * Tests the saveCourseCatalog() method
	 */
	@Test
	public void testSaveCourseCatalog() {
		try {
			CourseCatalog a = new CourseCatalog();
			a.addCourseToCatalog("name1", "title1", "101", 3, "instructorId1", 10, "M", 1500, 1600);
			a.saveCourseCatalog("testfile");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "The file cannot be saved.");
		}
	}

	/**
	 * Tests the addCourseToCatalog() method
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog a = new CourseCatalog();
		assertTrue(a.addCourseToCatalog("name1", "title1", "101", 3, "instructorId1", 10, "M", 1500, 1600));
		assertFalse(a.addCourseToCatalog("name1", "title1", "101", 3, "instructorId1", 10, "M", 1500, 1600));
		assertTrue(a.addCourseToCatalog("name2", "title2", "102", 3, "instructorId1", 10, "M", 1500, 1600));
		assertEquals(a.getCourseCatalog().length, 2);
	}

	/**
	 * Tests the loadCoursesFromFile() method
	 */
	@Test
	public void testLoadCoursesFromFile() {
		CourseCatalog ws = new CourseCatalog();
		ws.loadCoursesFromFile(validTestFile);
		assertEquals(8, ws.getCourseCatalog().length);
	}

	/**
	 * Tests the newCourseCatalog() method
	 */
	@Test
	public void testNewCourseCatalog() {
		CourseCatalog a = new CourseCatalog();
		assertTrue(a.addCourseToCatalog("name1", "title1", "101", 3, "instructorId1", 10, "M", 1500, 1600));
		assertFalse(a.addCourseToCatalog("name1", "title1", "101", 3, "instructorId1", 10, "M", 1500, 1600));
		assertTrue(a.addCourseToCatalog("name2", "title2", "102", 3, "instructorId1", 10, "M", 1500, 1600));
		assertEquals(a.getCourseCatalog().length, 2);
		a.newCourseCatalog();
		assertEquals(a.getCourseCatalog().length, 0);
	}

	/**
	 * Tests the CourseCatalog() constructor
	 */
	@Test
	public void testCourseCatalog() {
		CourseCatalog a = new CourseCatalog();
		assertEquals(a.getCourseCatalog().length, 0);
	}

}
