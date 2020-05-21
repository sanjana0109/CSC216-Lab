/**
 *FacultyRecordIO.java
 * @author Nicholas Loftin nlloftin@ncsu.edu
 * Version 1
 */
package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Reads faculty records from files and writes them to another file
 * 
 * @author Raymond Dong
 * @author Nicholas Loftin nlloftin@ncsu.edu
 *
 */
public class FacultyRecordIO {
	/**
	 * Generates an SortedList of the faculty records read from the file. If the
	 * file can't be found or accessed a File NotFoundException is thrown.
	 * 
	 * @param fileName the file to read Course records from
	 * @return a list of Faculty objects from the file provided
	 * @throws FileNotFoundException is thrown if the file can't be found or read
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> facultyList = new LinkedList<Faculty>();
		while (fileReader.hasNextLine()) {
			try {
				Faculty faculty = processFaculty(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < facultyList.size(); i++) {
					User f = facultyList.get(i);
					if (faculty.getId().equals(f.getId())) {
						// duplicate faculty
						duplicate = true;
					}
				}
				if (!duplicate) {
					facultyList.add(faculty);
				}
			} catch (IllegalArgumentException e) {
				// skip the line
			}
		}
		fileReader.close();
		return facultyList;
	}

	/**
	 * Using PrintStream as writer main method.
	 * 
	 * @param fileName    file name
	 * @param facultyList list of faculty members
	 * @throws IOException throw an IO exception
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyList) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < facultyList.size(); i++) {
			fileWriter.println(facultyList.get(i).toString());
		}

		fileWriter.close();
	}

	/**
	 * Processes a line from the file and returns a constructed Faculty object from
	 * it.
	 * 
	 * @param line the line that is being processed
	 * @return a Faculty object that gets constructed from the input parameter line
	 * @throws IllegalArgumentException if the course is in an invalid format.
	 */
	private static Faculty processFaculty(String line) {
		Scanner lineScanner = new Scanner(line);
		lineScanner.useDelimiter(",");
		try {
			try {

				String firstName = lineScanner.next();
				String lastName = lineScanner.next();
				String id = lineScanner.next();
				String email = lineScanner.next();
				String hashedPassword = lineScanner.next();
				int maxCourses = lineScanner.nextInt();

				lineScanner.close();

				Faculty f = new Faculty(firstName, lastName, id, email, hashedPassword, maxCourses);
				return f;

			} catch (NoSuchElementException e) {
				throw new IllegalArgumentException();
			}

		}

		catch (InputMismatchException e) {
			throw new IllegalArgumentException();
		}
	}
}
