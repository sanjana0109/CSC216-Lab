/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;

/**
 * Tests the faculty directory class
 * 
 * @author sanjanacheerla
 * @author Nicholas Loftin
 *
 */
public class FacultyDirectoryTest {

	/** Valid course records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Fac";
	/** Test last name */
	private static final String LAST_NAME = "culty";
	/** Test id */
	private static final String ID = "fculty";
	/** Test email */
	private static final String EMAIL = "fculty@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_COURSES = 2;

	/**
	 * Resets course_records.txt for use in other tests.
	 * 
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {
		// Reset Faculty_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_faculty_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "faculty_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#FacultyDirectory()}.
	 */
	@Test
	public void testFacultyDirectory() {
		FacultyDirectory fd = new FacultyDirectory();
		assertNotNull(fd);
		assertFalse(fd.removeFaculty("sesmith5"));
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#newFacultyDirectory()}.
	 */
	@Test
	public void testNewFacultyDirectory() {
		FacultyDirectory fd = new FacultyDirectory();

		fd.loadFacultyFromFile(validTestFile);
		assertEquals(8, fd.getFacultyDirectory().length);

		fd.newFacultyDirectory();
		assertEquals(0, fd.getFacultyDirectory().length);

		// Test IllegalArgumentException
		String fileName = "nonexistingtestfile.txt";
		try {
			fd.loadFacultyFromFile(fileName);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Unable to read file " + fileName);
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#loadFacultyFromFile(java.lang.String)}.
	 */
	@Test
	public void testLoadFacultyFromFile() {
		FacultyDirectory fd = new FacultyDirectory();

		// Test valid file
		fd.loadFacultyFromFile(validTestFile);
		assertEquals(8, fd.getFacultyDirectory().length);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#addFaculty(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	@Test
	public void testAddFaculty() {
		FacultyDirectory fd = new FacultyDirectory();

		// Test valid Faculty
		fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		String[][] facultyDirectory = fd.getFacultyDirectory();
		assertEquals(1, facultyDirectory.length);
		assertEquals(FIRST_NAME, facultyDirectory[0][0]);
		assertEquals(LAST_NAME, facultyDirectory[0][1]);
		assertEquals(ID, facultyDirectory[0][2]);

		// Tests invalid password (null)
		try {
			fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid password");
		}

		// Tests invalid repeated password (null)
		try {
			fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, null, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid password");
		}

		// Tests invalid password (empty)
		try {
			fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "", PASSWORD, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid password");
		}

		// Tests invalid repeated password (empty)
		try {
			fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "", MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid password");
		}

		// Tests password hash
		String falsePassword = "afdf";
		try {
			fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, falsePassword, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Passwords do not match");
		}

		fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		assertFalse(fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#removeFaculty(java.lang.String)}.
	 */
	@Test
	public void testRemoveFaculty() {
		FacultyDirectory fd = new FacultyDirectory();

		// Add facultys and remove
		fd.loadFacultyFromFile(validTestFile);
		assertEquals(8, fd.getFacultyDirectory().length);
		assertTrue(fd.removeFaculty("fmeadow"));
		String[][] facultyDirectory = fd.getFacultyDirectory();
		assertEquals(7, facultyDirectory.length);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#saveFacultyDirectory(java.lang.String)}.
	 */
	@Test
	public void testSaveFacultyDirectory() {
		FacultyDirectory fd = new FacultyDirectory();

		// Add a faculty
		fd.addFaculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "pw", "pw", 2);
		fd.addFaculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", "pw", "pw", 3);
		fd.addFaculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", "pw", "pw", 1);
		assertEquals(3, fd.getFacultyDirectory().length);
		fd.saveFacultyDirectory("test-files/actual_faculty_records.txt");
		checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
	}

	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));

			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#getFacultyById(java.lang.String)}.
	 */
	@Test
	public void testGetFacultyById() {
		FacultyDirectory fd = new FacultyDirectory();
		fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);

		Faculty s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);

		assertEquals(s.getId(), fd.getFacultyById(ID).getId());
	}

}
