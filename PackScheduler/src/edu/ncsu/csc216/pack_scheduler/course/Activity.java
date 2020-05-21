package edu.ncsu.csc216.pack_scheduler.course;

/**
 * The Activity class is an abstract class and the parent for Event and Course
 * classes. An activity consists of the title, meeting days, start time, and end
 * time. The purpose of this class is to be a parent of the Event and Course
 * classes by sharing the fields mentioned above, and contains abstract methods,
 * getShortDisplayArray, getLongDisplayArray, and isDuplicate(). Also checks to
 * see if 2 activities conflict each other.
 * 
 * @author Sanjana Cheerla
 *
 */
public abstract class Activity implements Conflict {

	/** Course's title. */
	private String title;

	/** Course's meeting days. */
	private String meetingDays;

	/** Course's starting time. */
	private int startTime;

	/** Course's ending time. */
	private int endTime;

	/** Upper time limit */
	private static final int UPPER_TIME = 2400;

	/** Upper hour limit */
	private static final int UPPER_HOUR = 60;

	/** Constant for accessing different parts of time */
	private static final int TIME_CONSTANT = 100;

	/** Time constant to differentiate between AM and PM in standard time format */
	private static final int AM_OR_PM = 12;

	/** Constant to check if minutes is between 0 and 9 included */
	private static final int SINGLE_DIGIT_MINUTE = 10;

	/**
	 * This abstract method creates an array of length 4 that contains the
	 * respective values for Event and Course. Overridden in Event and Course.
	 * 
	 * @return String[] of length 4 that contains the respective values for Event
	 *         and Course
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * This method creates an array of length 7 that contains that contains the
	 * respective values for Event and Course. Overridden in Event and Course.
	 * 
	 * @return String[] of length 7 that contains that contains the respective
	 *         values for Event and Course
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Constructs an Activity objects with the provided title, meeting days, start
	 * time, and end time.
	 * 
	 * @param title       the title of the Activity
	 * @param meetingDays the meeting days of the Activity
	 * @param startTime   the start time of the Activity
	 * @param endTime     the end time of the Activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		this.setTitle(title);
		this.setMeetingDays(meetingDays);
		this.setActivityTime(startTime, endTime);
	}

	/**
	 * Returns the Activity's title
	 * 
	 * @return String of the Activity's title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Activity's title. If the title is null or an empty string, an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param title The title to set
	 * @throws IllegalArgumentException If title is null or an empty string
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Invalid course title");
		}
		if (title.isEmpty()) {
			throw new IllegalArgumentException("Invalid course title");
		}
		this.title = title;
	}

	/**
	 * Returns the Activity's meetingDays
	 * 
	 * @return String of the meetingDays that the Activity meets
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the meetingDays parameter. Overridden in Event and Course.
	 * 
	 * @param meetingDays The meetingDays to set
	 * @throws IllegalArgumentException if meetingDays is null or empty
	 */
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.isEmpty()) {
			throw new IllegalArgumentException("Invalid meeting days");
		}

		this.meetingDays = meetingDays;

	}

	/**
	 * Returns the Activity's startTime
	 * 
	 * @return the startTime of the Activity
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Activity's endTime
	 * 
	 * @return the endTime of the Activity
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets Activity's startTime and endTime. If the startTime or endTime is not
	 * between 0 and 2359 included, an IllegalArgumentException is thrown. For the
	 * Course class child, if the Course has meeting days of "A" then startTime and
	 * endTime are set to 0000.
	 * 
	 * @param startTime The Activity's startTime
	 * @param endTime   The Activity's end time
	 * @throws IllegalArgumentException If start time or end time is less than 0 or
	 *                                  greater than 2359, and the minutes are not
	 *                                  between 0 and 59 inclusive.
	 */
	public void setActivityTime(int startTime, int endTime) {
		if (startTime / TIME_CONSTANT < 0 || startTime / TIME_CONSTANT > UPPER_TIME / TIME_CONSTANT - 1) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		if (startTime % TIME_CONSTANT < 0 || startTime % TIME_CONSTANT > UPPER_HOUR - 1) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		if (endTime / TIME_CONSTANT < 0 || endTime / TIME_CONSTANT > UPPER_TIME / TIME_CONSTANT - 1) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		if (endTime % TIME_CONSTANT < 0 || endTime % TIME_CONSTANT > UPPER_HOUR - 1) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		if (endTime < startTime) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		if (this.meetingDays.equals("A")) {
			this.startTime = 0000;
			this.endTime = 0000;
			return;
		}
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Returns a String of meetingDays followed by startTime and endTime in standard
	 * time format with respective AM and PM.
	 * 
	 * @return a String representation of Activity's meetingDays and startTime and
	 *         endTime.
	 */
	public String getMeetingString() {
		if (this.meetingDays.contentEquals("A")) {
			return "Arranged";
		}
		String startTimeStandard = convertTimeMilitaryToStandard(this.startTime);
		String endTimeStandard = convertTimeMilitaryToStandard(this.endTime);
		return this.meetingDays + " " + startTimeStandard + "-" + endTimeStandard;
	}

	/**
	 * Returns a string representation of militaryTime with AM or PM attached to the
	 * end. For example 1340 would return "1:40PM".
	 * 
	 * @param militaryTime The time in military time to be converted to standard
	 *                     time format
	 * @return a String representation of time in standard format
	 */
	private String convertTimeMilitaryToStandard(int militaryTime) {
		int hour = militaryTime / TIME_CONSTANT;
		int minutes = militaryTime % TIME_CONSTANT;

		// PM times
		if (hour >= AM_OR_PM) {
			hour = hour % AM_OR_PM;
			if (hour == 0) {
				hour = AM_OR_PM;
			}

			if (minutes < SINGLE_DIGIT_MINUTE) {
				return hour + ":0" + minutes + "PM";
			}

			return hour + ":" + minutes + "PM";
		}

		// AM times
		if (hour == 0) {
			hour = AM_OR_PM;
		}
		if (minutes < SINGLE_DIGIT_MINUTE) {
			return hour + ":0" + minutes + "AM";
		}

		return hour + ":" + minutes + "AM";
	}

	/**
	 * Generates a hashCode for activity
	 * 
	 * @return hashCode for activity
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Compares the given obj and checks to see if obj is equal to this activity
	 * 
	 * @param obj the object being compared
	 * @return true if obj is equals this activity
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Checks to see if the possible conflicting activity is a conflicting activity
	 * compared to the local activity. An activity conflicts another activity if
	 * both the activities meet on the same day and have overlapping times.
	 * 
	 * @param possibleConflictingActivity the activity checking to see if it is
	 *                                    being conflicted with the local activity.
	 * @throws ConflictException if the events conflict
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		String meetingDays1 = this.getMeetingDays();
		String meetingDays2 = possibleConflictingActivity.getMeetingDays();

		int start1 = this.getStartTime();
		int start2 = possibleConflictingActivity.getStartTime();

		int end1 = this.getEndTime();
		int end2 = possibleConflictingActivity.getEndTime();

		// if both activities are arranged
		if (meetingDays1.equals("A") || meetingDays2.equals("A")) {
			return;
		}

		// if both activities have the exact same everything
		if (meetingDays1.equals(meetingDays2) && start1 == start2 && end1 == end2) {
			throw new ConflictException();
		}

		// checks to see if any activities overlap
		for (int i = 0; i < meetingDays1.length(); i++) {
			for (int j = 0; j < meetingDays2.length(); j++) {
				if (meetingDays1.charAt(i) == meetingDays2.charAt(j)) {
					if (start2 >= start1 && end2 <= end1) {
						throw new ConflictException();
					}
					if (end2 >= start1 && start2 <= start1) {
						throw new ConflictException();
					}
					if (start2 <= end1 && end2 >= end1) {
						throw new ConflictException();
					}
				}
			}
		}
	}

}