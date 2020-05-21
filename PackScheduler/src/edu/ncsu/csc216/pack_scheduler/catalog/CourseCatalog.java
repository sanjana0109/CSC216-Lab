package edu.ncsu.csc216.pack_scheduler.catalog;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The CourseCatalog class maintains a sorted list of course objects. The
 * functionality of this class is to provide a base to create a course catalog,
 * load courses from a file, add courses to a sorted list get a course from the
 * sorted list, remove a course from the sorted list, save the catalog and
 * finally be able to view the courses in a string format.
 *
 * @author Sanjana Cheerla
 * @author Zeyu Chang
 * @author Walker Clem
 *
 */
public class CourseCatalog
{

    /** The sorted list of course objects */
    private SortedList<Course> catalog;

    /** The number of rows used in getCourseCatalog() */
    private static final int SCHEDULE_COURSE_INDEX = 5;

    /**
     * Creates a new course catalog of course objects.
     */
    public CourseCatalog()
    {
        this.catalog = new SortedList<Course>();
    }

    /**
     * creates a clean copy of a course catalog
     */
    public void newCourseCatalog()
    {
        this.catalog = new SortedList<Course>();
    }

    /**
     * Loads courses from a file and puts them in a sorted list of courses
     *
     * @param fileName the file that the courses are being added from
     * @throws IllegalArgumentException if a FileNotFoundException is caught
     */
    public void loadCoursesFromFile(String fileName)
    {
        try {
            this.catalog = CourseRecordIO.readCourseRecords(fileName);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Adds a course to a catalog and if the course is added, the method returns
     * true. If the course is a duplicate or is already in the catalog, false is
     * returned.
     *
     * @param name         the name of the course being added
     * @param title        the title of the course being added
     * @param section      the section of the course being added
     * @param credits      the credits that the course being added has
     * @param instructorId the instructorId of the course being added
     * @param enrollmentCap the max amount of students to cap enrollment at
     * @param meetingDays  the meeting days of the course being added
     * @param startTime    the start time of the course being added
     * @param endTime      the end time of the course being added
     * @return a boolean, true if the course has been added, false if the course
     *     is
     *         already in the list and if the course is a duplicate course.
     */
    public boolean addCourseToCatalog(
        String name,
        String title,
        String section,
        int credits,
        String instructorId,
        int enrollmentCap,
        String meetingDays,
        int startTime,
        int endTime)
    {

        Course toAdd = new Course(
            name,
            title,
            section,
            credits,
            instructorId,
            enrollmentCap,
            meetingDays,
            startTime,
            endTime);

        if (catalog.contains(toAdd)) {
            return false;
        }

        for (int i = 0; i < this.catalog.size(); i++) {
            if (name.equals(this.catalog.get(i).getName()) &&
                section.equals(this.catalog.get(i).getSection())) {
                return false;
            }
        }

        catalog.add(toAdd);
        return true;
    }

    /**
     * Returns the course being searched for if it is in the catalog
     *
     * @param name    the name of the course being searched
     * @param section the section of the course being searched
     * @return null if the course does not exist, the course being searched for
     *     if
     *         it is in the catalog.
     */
    public Course getCourseFromCatalog(String name, String section)
    {
        for (int i = 0; i < catalog.size(); i++) {
            if (name.equals(this.catalog.get(i).getName()) &&
                section.equals(this.catalog.get(i).getSection())) {
                return catalog.get(i);
            }
        }
        return null;
    }

    /**
     * Removes the provided course from the catalog.
     *
     * @param name    of the course being removed
     * @param section the section of the course being removed
     * @return true if the course is removed. false if the course is not removed
     */
    public boolean removeCourseFromCatalog(String name, String section)
    {
        for (int i = 0; i < catalog.size(); i++) {
            if (name.equals(this.catalog.get(i).getName()) &&
                section.equals(this.catalog.get(i).getSection())) {
                catalog.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the courses in the catalog in a string array format. Each course
     * is returned so that name, section, title, meeting string are in the
     * respective columns 0 to 3.
     *
     * @return a 2D string array of each course. Column 0 contains the name,
     *     column
     *         1 contains the section, column 2 contains the title, and column 3
     *         contains the meeting string for the course. returns an empty 2D
     *         string if the catalog is empty.
     */
    public String[][] getCourseCatalog()
    {
        if (this.catalog.isEmpty()) {
            return new String[0][0];
        }

        String[][] courseCatalog =
            new String[this.catalog.size()][SCHEDULE_COURSE_INDEX];

        for (int i = 0; i < courseCatalog.length; i++) {
            int index = 0;
            courseCatalog[i][index] =
                catalog.get(i).getShortDisplayArray()[index];
            index++;
            courseCatalog[i][index] =
                catalog.get(i).getShortDisplayArray()[index];
            index++;
            courseCatalog[i][index] =
                catalog.get(i).getShortDisplayArray()[index];
            index++;
            courseCatalog[i][index] =
                catalog.get(i).getShortDisplayArray()[index];
            index++;
            courseCatalog[i][index] =
                catalog.get(i).getShortDisplayArray()[index];
        }

        return courseCatalog;
    }

    /**
     * Saves the course Catalog into a file provided the file name.
     *
     * @param fileName the name of the file that the course catalog is being
     *     saved
     *                 into.
     * @throws IllegalArgumentException if an IOException is caught
     */
    public void saveCourseCatalog(String fileName)
    {
        try {
            CourseRecordIO.writeCourseRecords(fileName, this.catalog);
        } catch (IOException e) {
            throw new IllegalArgumentException("The file cannot be saved.");
        }
    }
}
