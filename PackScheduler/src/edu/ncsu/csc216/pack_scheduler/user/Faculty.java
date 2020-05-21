/**
 *Faculty.java
 * @author Nicholas Loftin nlloftin@ncsu.edu
 * Version 1
 */
package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * Faculty Class
 * 
 * @author Nicholas Loftin nlloftin@ncsu.edu
 * @author Sanjana Cheerla
 *
 */
public class Faculty extends User {
	/**
	 * set Max courses
	 */
	private int maxCourses;
	/**
	 * Absolute min courses
	 */
	public static final int MIN_COURSES = 1;
	/**
	 * Absolute max courses
	 */
	public static final int MAX_COURSES = 3;

	/**
	 * Faculty Schedule
	 */
	private FacultySchedule facultySchedule;

	/**
	 * Faculty constructor
	 * 
	 * @param firstName      first name
	 * @param lastName       last name
	 * @param id             id of faculty
	 * @param email          email
	 * @param hashedPassword password
	 * @param maxCourses     max courses
	 * @throws IllegalArgumentException if any of the params are invalid
	 */
	public Faculty(String firstName, String lastName, String id, String email, String hashedPassword, int maxCourses) {
		super(firstName, lastName, id, email, hashedPassword);
		facultySchedule = new FacultySchedule(id);
		setMaxCourses(maxCourses);

		if (hashedPassword == null || hashedPassword.isEmpty() || hashedPassword.isBlank()) {
			throw new IllegalArgumentException("Invalid password");
		}

	}

	/**
	 * gets max credits
	 * 
	 * @return the maxCourses
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * sets max credits
	 * 
	 * @param maxCourses the maxCourses to set
	 * @throws IllegalArgumentException if not valid
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses > MAX_COURSES || maxCourses < MIN_COURSES) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		this.maxCourses = maxCourses;
	}

	/**
	 * To string functionality
	 * 
	 * @return String output
	 */
	@Override
	public String toString() {
		String out = "";
		out += this.firstName + "," + this.lastName + "," + this.id + "," + this.email + "," + this.password + ","
				+ this.maxCourses;

		return out;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses)
			return false;
		return true;
	}

	/**
	 * Returns the faculty schedule
	 * 
	 * @return the faculty schedule of the Faculty
	 */
	public FacultySchedule getSchedule() {
		return facultySchedule;
	}

	/**
	 * Checks if the faculty object has an overloaded schedule meaning that there
	 * are more courses than the max number of courses.
	 * 
	 * @return true if the faculty's schedule is overloaded, false if the faculty's
	 *         schedule is not overloaded.
	 */
	public boolean isOverloaded() {
		if (facultySchedule.getScheduledCourses().length > this.maxCourses)
			return true;
		return false;
	}

}
