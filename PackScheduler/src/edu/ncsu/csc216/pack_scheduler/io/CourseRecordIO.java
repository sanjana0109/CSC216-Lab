package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import edu.ncsu.csc216.collections.list.SortedList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;

/**
 * This class reads course records from files and writes them to another file
 * using the Course Class as well.
 *
 * @author Sanjana Cheerla
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates a list of valid Courses. Any
	 * invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileScanner = new Scanner(new File(fileName));
		SortedList<Course> courses = new SortedList<Course>();
		while (fileScanner.hasNextLine()) {
			try {
				String line = fileScanner.nextLine();
				Course course = readCourse(line);
				boolean duplicate = false;

				for (int i = 0; i < courses.size(); i++) {
					Course c = courses.get(i);
					if (c.getName().equals(course.getName()) && c.getSection().equals(course.getSection())) {
						// it's a duplicate
						duplicate = true;
					}
				}
				if (!duplicate) {
					courses.add(course);
				}

			} catch (IllegalArgumentException e) {
				// skip the line
			}
		}
		fileScanner.close();
		return courses;
	}

	/**
	 * Reads Course information from the passed string
	 * 
	 * @param nextLine string to read information from
	 * @return Course based on passed string
	 */
	private static Course readCourse(String nextLine) throws IllegalArgumentException {
		Course c = null;
		Scanner lineScanner = new Scanner(nextLine);
		lineScanner.useDelimiter(",");
		try {
			String name = lineScanner.next();
			String title = lineScanner.next();
			String section = lineScanner.next();
			int credits = lineScanner.nextInt();
			String instructorId = lineScanner.next();
			int enrollmentCap = lineScanner.nextInt();
			String meetingDays = lineScanner.next();
			int startTime = 0;
			int endTime = 0;

			if (lineScanner.hasNextInt()) {
				startTime = lineScanner.nextInt();
				endTime = lineScanner.nextInt();
			}

			var directory = RegistrationManager.getInstance().getFacultyDirectory();

			if (directory.getFacultyById(instructorId) == null) {
				instructorId = null;
			}
			c = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);

		} catch (NoSuchElementException e) {
			lineScanner.close();
			throw new IllegalArgumentException();
		}
		lineScanner.close();
		return c;

	}

	/**
	 * Writes the course record information to a file
	 * 
	 * @param fileName file name to write course information to
	 * @param courses  courses to write to the file
	 * @throws IOException if file cannot be written to
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < courses.size(); i++) {
			fileWriter.println(courses.get(i).toString());
		}

		fileWriter.close();
	}

}