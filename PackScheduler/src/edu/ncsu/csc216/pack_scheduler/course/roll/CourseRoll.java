package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * Manages enrolling/dropping students from a course as well as maintains an
 * amount of students within the minimum and maximum enrolled students range.
 *
 * @author sggephar
 * @author scheerl
 * @author mbabb
 *
 */
public class CourseRoll {

	/** the roll’s enrollment capacity */
	private int enrollmentCap;

	/** the smallest size a class can be (10 students) */
	private static final int MIN_ENROLLMENT = 10;

	/** the largest size a class can be (250 students) */
	private static final int MAX_ENROLLMENT = 250;

	/** the roll of students in the class */
	private LinkedAbstractList<Student> roll;
	/** the wait list of students in the class */
	private LinkedQueue<Student> waitList;
	/**
	 * Wait list size final
	 */
	private static final int WAITLIST_SIZE = 10;

	/**
	 * Course maintained in course roll
	 */
	private Course course;

	/**
	 * Creates an empty LinkedAbstractList of Students
	 *
	 * @param enrollmentCap of the student
	 * @param c             the course having the enrollment cap
	 * @throws IllegalArgumentException the enrollmentCap is less than
	 *                                  MIN_ENROLLMENT or greater than
	 *                                  MAX_ENROLLMENT
	 */
	public CourseRoll(int enrollmentCap, Course c) {
		if (c == null) {
			throw new IllegalArgumentException();
		}
		this.course = c;
		this.setEnrollmentCap(enrollmentCap);
		this.roll = new LinkedAbstractList<Student>(enrollmentCap);
		this.waitList = new LinkedQueue<Student>(WAITLIST_SIZE);
	}

	/**
	 * Returns the difference between the enrollmentCap and the size of the roll
	 *
	 * @return enrollmentCap the number to stop enrollment at
	 */
	public int getEnrollmentCap() {
		return this.enrollmentCap;
	}

	/**
	 * Sets the amount of students to cap enrollment at
	 *
	 * @param enrollmentCap the enrollment cap.
	 * @throws IllegalArgumentException the enrollmentCap is less than
	 *                                  MIN_ENROLLMENT or greater than
	 *                                  MAX_ENROLLMENT
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT
				|| (this.roll != null && enrollmentCap < this.roll.size())) {
			throw new IllegalArgumentException();
		}
		this.enrollmentCap = enrollmentCap;
		if (roll != null) {
			this.roll.setCapacity(enrollmentCap);
		}
	}

	/**
	 * Adds the student to the end of roll
	 *
	 * @param s student to be enrolled in a course
	 * @throws IllegalArgumentException if the student is null, there is no more
	 *                                  room in the class, the student is already
	 *                                  enrolled, or if adding the student to the
	 *                                  LinkedAbstractList generates an exception
	 */
	public void enroll(Student s) {
		if (roll == null || s == null || roll.contains(s)) {
			throw new IllegalArgumentException();
		}

		if (this.enrollmentCap == this.roll.size()) {
			if (waitList.size() < WAITLIST_SIZE) {
				waitList.enqueue(s);
			} else {
				throw new IllegalArgumentException();
			}
		} else {
			roll.add(roll.size(), s);
		}
	}

	/**
	 * Removes a student from the roll
	 *
	 * @param s student to be removed from the roll
	 * @throws IllegalArgumentException if the student is null or if removing the
	 *                                  student from the LinkedAbstractList
	 *                                  generates an exception
	 */
	public void drop(Student s) {
		if (s == null || roll == null || roll.size() == 0) {
			throw new IllegalArgumentException();
		}
		if (roll.contains(s)) {
			try {
				this.roll.remove(s);
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}

			if (!waitList.isEmpty()) {
				Student waitListedStudent = waitList.dequeue();
				this.enroll(waitListedStudent);
				waitListedStudent.getSchedule().addCourseToSchedule(course);
			}
		} else {
			for(int i = 0; i < waitList.size(); i++) {
				Student tmp = waitList.dequeue();
				if(!s.equals(tmp)) {
					waitList.enqueue(tmp);
				}
			}
		}
	}

	/**
	 * Returns the number of open seats in a course
	 *
	 * @return number of open seats in a course
	 */
	public int getOpenSeats() {
		return this.enrollmentCap - this.roll.size();
	}

	/**
	 * Returns true if the student can be enrolled and false if the student cannot
	 * be enrolled (no more room or already enrolled)
	 *
	 * @param s the student to check for possible enrollment
	 * @return true if student can be added to roll, false otherwise
	 */
	public boolean canEnroll(Student s) {
		if (this.getOpenSeats() == 0 || roll.contains(s)) {
			return false;
		}
		return true;
	}

	/**
	 * Gets Wait list size
	 * 
	 * @return int of number of people waiting
	 */
	public int getNumberOnWaitlist() {
		return waitList.size();

	}
}
