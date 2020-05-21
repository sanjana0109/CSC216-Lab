package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Maintains a directory of all faculty at NC State. Can add faculty, remove
 * faculty , get faculty, load the faculty and save from and to a file.
 * 
 * @author sanjana cheerla
 */
public class FacultyDirectory {

	/** List of faculty in the directory */
	private static LinkedList<Faculty> facultyDirectory;

	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Creates an empty faculty directory.
	 */
	public FacultyDirectory() {
		newFacultyDirectory();
	}

	/**
	 * Creates an empty faculty directory. All students in the previous list are
	 * list unless saved by the user.
	 */
	public void newFacultyDirectory() {
		FacultyDirectory.facultyDirectory = new LinkedList<Faculty>();
	}

	/**
	 * Constructs the faculty directory by reading in student information from the
	 * given file. Throws an IllegalArgumentException if the file cannot be found.
	 * 
	 * @param fileName file containing list of faculty
	 * @throws IllegalArgumentException with the message "Unable to read fileName"
	 *                                  if file cannot be read
	 */
	public void loadFacultyFromFile(String fileName) {
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}

	/**
	 * Adds a Faculty member to the directory. Returns true if the faculty member is
	 * added and false if the faculty member is unable to be added because their id
	 * matches another Faculty member's id.
	 * 
	 * This method also hashes the faculty's password for internal storage.
	 * 
	 * @param firstName      first name
	 * @param lastName       last name
	 * @param id             id
	 * @param email          email
	 * @param password       password
	 * @param repeatPassword verified password
	 * @param maxCredits     max credits
	 * @return T or F based on if it was added to the list
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password,
			String repeatPassword, int maxCredits) {
		// TODO
		String hashPW = "";
		String repeatHashPW = "";
		if (password == null || repeatPassword == null || password.equals("") || repeatPassword.equals("")) {
			throw new IllegalArgumentException("Invalid password");
		}
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(password.getBytes());
			hashPW = new String(digest1.digest());

			MessageDigest digest2 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest2.update(repeatPassword.getBytes());
			repeatHashPW = new String(digest2.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}

		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}

		Faculty faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCredits);

		for (int i = 0; i < facultyDirectory.size(); i++) {
			User f = facultyDirectory.get(i);
			if (f.getId().equals(faculty.getId())) {
				return false;
			}
		}
		facultyDirectory.add(faculty);
		return true;
	}

	/**
	 * Removes the faculty with the given id from the list of faculty with the given
	 * id. Returns true if the faculty is removed and false if the faculty is not in
	 * the list.
	 * 
	 * @param facultyId of the faculty member being removed
	 * @return false if not removed, true if removed
	 */
	public boolean removeFaculty(String facultyId) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User f = facultyDirectory.get(i);
			if (f.getId().equals(facultyId)) {
				facultyDirectory.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets information of faculty members in array with a column for first name,
	 * last name, and id.
	 * 
	 * @return array of information
	 */
	public String[][] getFacultyDirectory() {
		String[][] directory = new String[facultyDirectory.size()][3];
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			directory[i][0] = s.getFirstName();
			directory[i][1] = s.getLastName();
			directory[i][2] = s.getId();
		}
		return directory;
	}

	/**
	 * Saves all faculty in the directory to a file.
	 * 
	 * @param fileName name of file to save faculty to.
	 * @throws IllegalArgumentException if the given fileName cannot be written to
	 *                                  with the message "Unable to write to file
	 *                                  fileName"
	 */
	public void saveFacultyDirectory(String fileName) {
		try {
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}

	/**
	 * Finds member by id number
	 * 
	 * @param id id of faculty searched
	 * @return Faculty member with matching Id
	 */
	public Faculty getFacultyById(String id) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			Faculty temp = facultyDirectory.get(i);
			if (temp.getId().equals(id)) {
				return temp;
			}
		}
		return null;
	}
}
