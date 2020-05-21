package edu.ncsu.csc216.pack_scheduler.manager;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Registration Manager class, manages the logging in and out of the login and
 * logout system. Creates a registration manager, registrar, hashed password,
 * get instance, get course catalog,login, logout, get current user, clear data.
 * There is also an inner class of creating a registrar using the singleton
 * pattern.
 *
 * @author sggephar
 * @author mbabb
 * @author scheerl
 *
 */
public class RegistrationManager {

	/** Instance of an registration manager */
	private static RegistrationManager instance;

	/** Course catalog object */
	private CourseCatalog courseCatalog;

	/** Student directory object */
	private StudentDirectory studentDirectory;

	/** Faculty directory object */
	private FacultyDirectory facultyDirectory;

	/** A registrar */
	private final User registrar;

	/** A current user */
	private User currentUser;

	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/** registrar PW */
	private static final String PW = "Regi5tr@r";

	/** hashed pw */
	private static String hashPW;

	/**
	 * Registration Manager Class, creates a registration manager.
	 */
	private RegistrationManager() {
		this.courseCatalog = new CourseCatalog();
		this.studentDirectory = new StudentDirectory();
		this.registrar = new Registrar();
		this.facultyDirectory = new FacultyDirectory();
	}
	
	{
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(PW.getBytes());
			hashPW = new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

//	/**
//	 * Creates a hashed password
//	 *
//	 * @param pw the password being hashed
//	 * @return the hashed password
//	 * @throws IllegalArgumentException with the message "Cannot hash password" if a
//	 *                                  NoSuchAlgorithmException is thrown.
//	 */
//	private String hashPW(String pw) {
//		try {
//			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
//			digest1.update(pw.getBytes());
//			return new String(digest1.digest());
//		} catch (NoSuchAlgorithmException e) {
//			throw new IllegalArgumentException("Cannot hash password");
//		}
//	}

	/**
	 * gets the single instance of a registration manager
	 *
	 * @return the instance of the manager.
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * Returns the course catalog.
	 *
	 * @return the courseCatalog of the Registration Manager
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * Returns the student directory.
	 *
	 * @return the studentDirectory of the Registration Manager
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * logs in the user, whether they are a student or a registrar.
	 *
	 * @param id       the id of the user
	 * @param password the password of the user
	 * @return true if the user is logged in and false if not.
	 * @throws IllegalArgumentException if the hashing algorithm cannot be found.
	 *                                  //TODO update login
	 */
	public boolean login(String id, String password) {
		if (currentUser != null) {
			return false;
		}

		if (registrar.getId().equals(id)) {
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (registrar.getPassword().equals(localHashPW)) {
					currentUser = registrar;
					return true;
				} else {
					return false;
				}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
		}

		else if (getStudentDirectory().getStudentById(id) != null) {
			Student s = studentDirectory.getStudentById(id);
			if (s == null) {
				throw new IllegalArgumentException("User doesn't exist.");
			}

			try {
				MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (s.getPassword().equals(localHashPW)) {
					currentUser = s;
					return true;
				}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
		} else {
			FacultyDirectory dir = getFacultyDirectory();
			if (dir.getFacultyById(id) != null) {
				Faculty f = dir.getFacultyById(id);

				try {
					MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
					digest.update(password.getBytes());
					String hashPass = new String(digest.digest());
					if (f.getPassword().equals(hashPass)) {
						this.currentUser = f;
						return true;
					}

				} catch (NoSuchAlgorithmException e) {
					throw new IllegalArgumentException();
				}
			} else {
				throw new IllegalArgumentException("User doesn't exist.");
			}
		}

		return false;
	}

	/**
	 * Logs out the current user and sets the current user to null, and clears all
	 * data.
	 */
	public void logout() {
		currentUser = null;
	}

	/**
	 * This method provides the current student.
	 *
	 * @return the current student
	 */
	public User getCurrentUser() {
		return this.currentUser;
	}

	/**
	 * Clears the data of the studentDirectory and courseCatalog
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		facultyDirectory.newFacultyDirectory();
	}

	/**
	 * Inner class, following the singleton pattern, of the Registrar class. Creates
	 * a registrar, which extends the user class.
	 *
	 * @author sggephar
	 * @author mbabb
	 * @author scheerl
	 */
	private static class Registrar extends User {
		private static final String FIRST_NAME = "Wolf";
		private static final String LAST_NAME = "Scheduler";
		private static final String ID = "registrar";
		private static final String EMAIL = "registrar@ncsu.edu";
		/**
		 * Create a registrar user with the user id and password in the
		 * registrar.properties file.
		 */
		public Registrar() {
			super(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPW);
		}
	}

	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * 
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
		if (currentUser == null || !(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			CourseRoll roll = c.getCourseRoll();

			if (s.canAdd(c) && roll.canEnroll(s)) {
				schedule.addCourseToSchedule(c);
				roll.enroll(s);
				return true;
			}

		} catch (IllegalArgumentException e) {
			return false;
		}
		return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * 
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
		if (currentUser == null || !(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			c.getCourseRoll().drop(s);
			return s.getSchedule().removeCourseFromSchedule(c);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	/**
	 * Resets the logged in student's schedule by dropping them from every course
	 * and then resetting the schedule.
	 */
	public void resetSchedule() {
		if (currentUser == null || !(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			String[][] scheduleArray = schedule.getScheduledCourses();
			for (int i = 0; i < scheduleArray.length; i++) {
				Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
				c.getCourseRoll().drop(s);
			}
			schedule.resetSchedule();
		} catch (IllegalArgumentException e) {
			// do nothing
		}
	}

	/**
	 * Returns the faculty directory
	 * 
	 * @return the faculty directory of RegistrationManager
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}

	/**
	 * Adds a faculty member to a course
	 * 
	 * @param c course getting faculty added
	 * @param f faculty member being added to course
	 * @return true if faculty member is added, false otherwise
	 * @throws IllegalArgumentException if current user is null or it is not an
	 *                                  instance of a registrar
	 */
	public boolean addFacultyToCourse(Course c, Faculty f) {
		if (currentUser != null && currentUser instanceof Registrar) {
			return f.getSchedule().addCourseToSchedule(c);
		}
		throw new IllegalArgumentException();
	}

	/**
	 * Removes a faculty member from a course
	 * 
	 * @param c course getting removed from faculty
	 * @param f faculty member being removed from course
	 * @return true if faculty member is removed, false otherwise
	 * @throws IllegalArgumentException if current user is null or it is not an
	 *                                  instance of a registrar
	 */
	public boolean removeFacultyFromCourse(Course c, Faculty f) {
		if (currentUser != null && currentUser instanceof Registrar) {
			return f.getSchedule().removeCourseFromSchedule(c);
		}
		throw new IllegalArgumentException();
	}

	/**
	 * Resets a faculty schedule
	 * 
	 * @param f faculty's schedulde being reset
	 * @throws IllegalArgumentException if current user is null or it is not an
	 *                                  instance of a registrar
	 */
	public void resetFacultySchedule(Faculty f) {
		if (currentUser != null && currentUser instanceof Registrar) {
			f.getSchedule().resetSchedule();
			return;
		}
		throw new IllegalArgumentException();
	}

}
