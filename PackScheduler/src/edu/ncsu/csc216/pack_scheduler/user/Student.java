package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Student Object Class, implements Comparable Student. This class can create
 * students, with the fields First Name, Last Name, ID, Email, Password, and Max
 * Credits. There are getters and setters for the fields, equals(), hashCode(),
 * compareTo()(from Comparable) and toString() methods.
 *
 * @author Zeyu Chang
 * @author Walker Clem
 * @author Sanjana Cheerla
 *
 */
public class Student extends User implements Comparable<Student>
{

    /**
     * Student max credits
     */
    int maxCredits;

    /** The student's schedule. */
    private Schedule schedule;

    /**
     * the maximum credits that a student is allowed to take is 18
     */
    public static final int MAX_CREDITS = 18;

    /**
     * Constructs a Student object with values for all fields.
     *
     * @param firstName  first name of students
     * @param lastName   last name of students
     * @param id         id of students
     * @param email      email of students
     * @param password   password of students
     * @param maxCredits max credits of students
     */
    public Student(
        String firstName,
        String lastName,
        String id,
        String email,
        String password,
        int maxCredits)
    {
        super(firstName, lastName, id, email, password);
        setMaxCredits(maxCredits);
        this.schedule = new Schedule();
    }

    /**
     * Constructs a Student object with values for all fields, except for max
     * credits.
     *
     * @param firstName first name of students
     * @param lastName  last name of students
     * @param id        id of students
     * @param email     email of students
     * @param password  password of students
     */
    public Student(
        String firstName,
        String lastName,
        String id,
        String email,
        String password)
    {
        this(firstName, lastName, id, email, password, MAX_CREDITS);
    }

    /**
     * getter of max credits
     *
     * @return the maxCredits
     */
    public int getMaxCredits()
    {
        return maxCredits;
    }

    /**
     * setter of last name, throw IllegalArgumentException for credits smaller
     * than 3 or greater than 18
     *
     * @param maxCredits the maxCredits to set
     * @throws IllegalArgumentException if the maxCredits parameter is not a
     *     number
     *                                  or is less than 3 or greater than 18
     */
    public void setMaxCredits(int maxCredits)
    {
        if (maxCredits < 3 || maxCredits > 18) {
            throw new IllegalArgumentException("Invalid max credits");
        }
        this.maxCredits = maxCredits;
    }

    /**
     * Gets the student's schedule.
     *
     * @return the schedule
     */
    public Schedule getSchedule()
    {
        return this.schedule;
    }

    /**
     * Return a comma separated value String for all Student fields
     *
     * @return String representation of Student
     */
    @Override public String toString()
    {
        return firstName + "," + lastName + "," + id + "," + email + "," +
            password + "," + maxCredits;
    }

    /**
     * Compares two students to see which one comes first.
     *
     * @param o the Student to compare to
     * @return 1 if this is is greater than that, 0 if equal, and -1 of this is
     *     less
     *         than that
     * @throws NullPointerException if the object in parameter is null
     * @throws ClassCastException   if the classes are not the same type to
     *     compare
     *                              to
     */
    @Override public int compareTo(Student o)
    {

        if (o == null) {
            throw new NullPointerException();
        }

        if (this.getClass() != o.getClass()) {
            throw new ClassCastException();
        }

        Student s = (Student) o;

        int ln = this.lastName.compareTo(s.getLastName());
        int fn = this.firstName.compareTo(s.getFirstName());
        int uid = this.id.compareTo(s.getId());

        if (ln > 0) {
            return 1;
        } else if (ln < 0) {
            return -1;
        } else if (fn > 0) {
            return 1;
        } else if (fn < 0) {
            return -1;
        } else if (uid > 0) {
            return 1;
        } else if (uid < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Checks if a course can be added to the schedule.
     *
     * @param course input course whereof to add.
     * @return truth value hereof.
     */
    public boolean canAdd(Course course)
    {
        return this.schedule.canAdd(course) &&
            (this.getMaxCredits() >=
             this.schedule.getScheduleCredits() + course.getCredits());
    }

    /**
     * generate hash code for student using all fields
     *
     * @return hashCode for Student
     */
    @Override public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + maxCredits;
        return result;
    }

    /**
     * Compares a given object to this Student for equality on all fields
     *
     * @param obj the object to compare
     * @return true if the object parameter and this Student are the same on all
     *         fields
     */
    @Override public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (maxCredits != other.maxCredits)
            return false;
        return true;
    }
}