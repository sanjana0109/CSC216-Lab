package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Manages the users schedule. Tracks and removes desired courses based on user
 * input.
 *
 * @author sggephar
 * @author scheerl
 * @author mbabb
 */
public class Schedule
{

    /** A schedule's title */
    private String title;

    /** a schedule of courses */
    private ArrayList<Course> schedule;

    /**
     * Constructs the user's schedule
     */
    public Schedule()
    {
        this.title = "My Schedule";
        this.schedule = new ArrayList<Course>();
    }

    /**
     * Resets the schedule
     */
    public void resetSchedule()
    {
        schedule.clear();
        this.title = "My Schedule";
    }

    /**
     * Returns a 2D String array of the scheduled courses
     *
     * @return a 2D String array of the tracked scheduled courses
     */
    public String[][] getScheduledCourses()
    {
        String[][] scheduled;
        if (schedule.isEmpty()) {
            scheduled = new String[0][1];
        } else {
            scheduled = new String[schedule.size()][];
            for (int i = 0; i < schedule.size(); i++) {
                scheduled[i] = schedule.get(i).getShortDisplayArray();
            }
        }
        return scheduled;
    }

    /**
     * Sets the title of the schedule
     *
     * @param title the title.
     */
    public void setTitle(String title)
    {
        if (title == null) {
            throw new IllegalArgumentException("Title cannot be null.");
        }
        this.title = title;
    }

    /**
     * Returns the title of the schedule
     *
     * @return title of the schedule
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Adds a course to the schedule
     *
     * @param course course to add to schedule
     * @return true if course can be added/false if it cannot be
     * @throws IllegalArgumentException if name is found in the current
     *     schedule.
     */
    public boolean addCourseToSchedule(Course course)
    {

        for (var a : this.schedule) {
            try {
                course.checkConflict(a);
            } catch (ConflictException e) {
                throw new IllegalArgumentException(
                    "The course cannot be added due to a conflict.");
            }

            if (a instanceof Course) {
                var tCourse = (Course) a;
                if (course.getName().equals(tCourse.getName())) {
                    throw new IllegalArgumentException(String.format(
                        "You are already enrolled in %s", course.getName()));
                }
            }
        }
        this.schedule.add(course);
        return true;
    }

    /**
     * Removes a course from the schedule
     *
     * @param course course to removed from schedule
     * @return true if course can be removed/false if it cannot be
     */
    public boolean removeCourseFromSchedule(Course course)
    {
        for (var c : this.schedule) {
            if (c.equals(course)) {
                return this.schedule.remove(course);
            }
        }
        return false;
    }

    /**
     * Returns the cumulative sum of credits within the schedule.
     *
     * @return the scheduled credits.
     */
    public int getScheduleCredits()
    {
        var sum = 0;
        for (var c : this.schedule) {
            sum += c.getCredits();
        }
        return sum;
    }

    /**
     * Checks if a course can be added to the schedule.
     *
     * @param course input course whereof to add.
     * @return truth value hereof.
     */
    public boolean canAdd(Course course)
    {
        try {
            this.addCourseToSchedule(course);
            this.removeCourseFromSchedule(course);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
