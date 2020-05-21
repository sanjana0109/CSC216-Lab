package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;

/**
 * Course class for WolfScheduler, use to create Course objects. A Course object
 * consists of a name, title, section, credits instructor id, meeting days,
 * start time and end time. Extends the Activity class. Contains overridden
 * methods from the Activity class. Implements the comparable interface.
 *
 * @author Sanjana Cheerla
 */
public class Course extends Activity implements Comparable<Course> {

	/** Course's name. */
	private String name;

	/** Course's section. */
	private String section;

	/** Course's credit hours. */
	private int credits;

	/** Course's instructor. */
	private String instructorId;

	/** Length of a section number */
	private static final int SECTION_LENGTH = 3;

	/** Maximum length of name */
	private static final int MAX_NAME_LENGTH = 6;

	/** Minimum length of name */
	private static final int MIN_NAME_LENGTH = 4;

	/** Max credits for a course */
	private static final int MAX_CREDITS = 5;

	/** Minimum credits for a course */
	private static final int MIN_CREDITS = 1;

	/** Length of short display array */
	private static final int SHORT_DISPLAY_LENGTH = 5;

	/** Length of long display array */
	private static final int LONG_DISPLAY_LENGTH = 7;

	/** the roll of students in the class */
	private CourseRoll roll;

	/**
	 * Constructs a Course object with values for all fields.
	 *
	 * @param name          Name of Course
	 * @param title         Title of Course
	 * @param section       Section of Course
	 * @param credits       Credit hours for Course
	 * @param instructorId  Instructor's unity id
	 * @param enrollmentCap the roll’s enrollment capacity
	 * @param meetingDays   Meeting days for Course as series of chars
	 * @param startTime     Start time for Course
	 * @param endTime       End time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays, int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		roll = new CourseRoll(enrollmentCap, this);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged. Source: Method is from GP1
	 * Guided Task: Encapsulation and Reducing Redundancy, Reducing Redundancy in
	 * the Course Constructors.
	 *
	 * @param name          Name of Course
	 * @param title         Title of Course
	 * @param section       Section of Course
	 * @param credits       Credit hours for Course
	 * @param instructorId  Instructor's unity id
	 * @param enrollmentCap the roll’s enrollment capacity
	 * @param meetingDays   Meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name
	 *
	 * @return String of the name of the course
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 4 or
	 * greater than 6, an IllegalArgumentException is thrown. Source: method is from
	 * GP1 Independent Task: Finish Course Implementation setName() implementation.
	 *
	 * @param name The name to set
	 * @throws IllegalArgumentException If name is null or length is less than 4 or
	 *                                  greater than 6
	 */
	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Invalid course name");
		}
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name");
		}
		this.name = name;
	}

	/**
	 * Returns the Course's section
	 *
	 * @return String of the Course's section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section. If the section number is not 3 numbers long, an
	 * IllegalArgumentException is thrown.
	 *
	 * @param section The section to set
	 * @throws IllegalArgumentException If the section is not 3 numbers long
	 */
	public void setSection(String section) {
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section number");
		}

		if (Character.isDigit(section.charAt(0)) && Character.isDigit(section.charAt(1))
				&& Character.isDigit(section.charAt(SECTION_LENGTH - 1))) {
			this.section = section;
			return;
		}
		throw new IllegalArgumentException("Invalid section number");
	}

	/**
	 * Returns the Course's credits
	 *
	 * @return The number of credits of the Course
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credits. If the course credits is not a digit, or credits
	 * is less than 1 or greater than 5 an IllegalArgumentException is thrown.
	 *
	 * @param credits The number credits to set for the course
	 * @throws IllegalArgumentException If credits is less than 1 or greater than 5
	 */
	public void setCredits(int credits) {

		if (credits < MIN_CREDITS) {
			throw new IllegalArgumentException();
		}
		if (credits > MAX_CREDITS) {
			throw new IllegalArgumentException();
		}

		this.credits = credits;
	}

	/**
	 * Returns the Course's instructorId
	 *
	 * @return String of the Course's instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's instructorId. If instructorId is null or empty an
	 * IllegalArgumentException is thrown.
	 *
	 * @param instructorId The instructorId to set
	 */
	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 *
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if (super.getMeetingDays().equals("A")) {
			return name + "," + super.getTitle() + "," + section + "," + credits + "," + instructorId + ","
					+ getCourseRoll().getEnrollmentCap() + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
				+ getCourseRoll().getEnrollmentCap() + "," + getMeetingDays() + "," + getStartTime() + ","
				+ getEndTime();
	}

	/**
	 * Generates a hashCode for Course
	 *
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/**
	 * Compares the given obj and checks to see if obj is equal to this Course
	 *
	 * @param obj the object being compared
	 * @return true if obj is equals this Course
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * This method creates an array of length 4 that contains Course name, section,
	 * title, and meeting string in that order.
	 *
	 * @return String[] of length 4 that contains Course name, section, title and
	 *         meeting string
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplay = new String[SHORT_DISPLAY_LENGTH];
		int index = 0;
		shortDisplay[index] = name;
		shortDisplay[++index] = section;
		shortDisplay[++index] = super.getTitle();
		shortDisplay[++index] = super.getMeetingString();
		shortDisplay[++index] = Integer.toString(roll.getOpenSeats());
		return shortDisplay;
	}

	/**
	 * This method creates an array of length 7 that contains Course name, section,
	 * title, credits, instructorId, meeting string, and an empty string.
	 *
	 * @return String[] of length 7 that contains Course name, section, title,
	 *         credits, instructorId, meeting string, empty string.
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplay = new String[LONG_DISPLAY_LENGTH];
		int index = 0;
		longDisplay[index] = name;
		longDisplay[++index] = section;
		longDisplay[++index] = super.getTitle();
		longDisplay[++index] = Integer.toString(credits);
		longDisplay[++index] = instructorId;
		longDisplay[++index] = super.getMeetingString();
		longDisplay[++index] = "";
		return longDisplay;
	}

	/**
	 * Sets the Course's meetingDays. If the meetingDays contains any other
	 * characters than "M,T,W,H,F,A", an IllegalArgumentException is thrown. If
	 * there is an "A" it must be the only character or else, an
	 * IllegalArgumentException is thrown.
	 *
	 * @param meetingDays The meetingDays to set
	 * @throws IllegalArgumentException If meetingDays is null or empty, and if the
	 *                                  meetingDays are invalid meaning that it must
	 *                                  have "M" "T" "W" "T" "F" as a combination of
	 *                                  strings and no other characters. If there is
	 *                                  an "A" it must be the only character.
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.isEmpty()) {
			throw new IllegalArgumentException("Invalid meeting days");
		}

		if (meetingDays.contains("A") && meetingDays.length() == 1) {
			super.setMeetingDays(meetingDays);
			return;
		}

		if (meetingDays.contains("A") && meetingDays.length() != 1) {
			throw new IllegalArgumentException("Invalid meeting days");
		}

		boolean isValid = true;

		for (int i = 0; i < meetingDays.length(); i++) {
			if (!(meetingDays.charAt(i) == 'M') && !(meetingDays.charAt(i) == 'T') && !(meetingDays.charAt(i) == 'W')
					&& !(meetingDays.charAt(i) == 'H') && !(meetingDays.charAt(i) == 'F')) {
				isValid = false;
				break;
			}
		}

		if (!isValid) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
		if (isValid) {
			super.setMeetingDays(meetingDays);
		}
	}

	/**
	 * Compares 2 courses to see which one comes first
	 *
	 * @param o the course being compared
	 */
	@Override
	public int compareTo(Course o) {
		if (o == null) {
			throw new NullPointerException();
		}

		if (this.getClass() != o.getClass()) {
			throw new ClassCastException();
		}

		Course c = (Course) o;

		int n = this.name.compareTo(c.getName());
		int s = this.section.compareTo(c.getSection());

		if (n > 0) {
			return 1;
		} else if (n < 0) {
			return -1;
		} else if (s > 0) {
			return 1;
		} else if (s < 0) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * Gets the course's roll.
	 * 
	 * @return the roll.
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}

	/**
	 * checks to see if 2 courses are duplicate
	 * 
	 * @param activity Activity to be compared
	 * @return true if activity is a duplicate course
	 */
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course other = (Course) activity;
			return this.getName().equals(other.getName());
		}
		return false;
	}
}
