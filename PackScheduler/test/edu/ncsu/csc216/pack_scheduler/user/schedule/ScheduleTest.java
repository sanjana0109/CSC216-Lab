package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;

import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import java.io.FileNotFoundException;
import org.junit.Test;

/**
 * For testing the schedule.
 *
 * @author sggephar
 * @author scheerl
 * @author mbabb
 */
public class ScheduleTest
{
    /** Path of the test file. */
    public static String pth = "test-files/course_records.txt";

    /**
     * Test method for
     * {@link edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule#Schedule()}.
     */
    @Test public void testSchedule()
    {
        try {
            var courses = CourseRecordIO.readCourseRecords(pth);
            var sc = new Schedule();

            sc.addCourseToSchedule(courses.get(0));
            sc.addCourseToSchedule(courses.get(4));

            assertEquals(sc.getScheduledCourses().length, 2);

        } catch (FileNotFoundException e) {
            fail();
        }
    }

    /**
     * Test method for
     * {@link
     * edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule#resetSchedule()}.
     */
    @Test public void testResetSchedule()
    {
        try {
            var courses = CourseRecordIO.readCourseRecords(pth);
            var sc = new Schedule();

            sc.addCourseToSchedule(courses.get(0));
            sc.addCourseToSchedule(courses.get(4));

            assertEquals(sc.getScheduledCourses().length, 2);

            sc.resetSchedule();

            assertEquals(sc.getScheduledCourses().length, 0);

        } catch (FileNotFoundException e) {
            fail();
        }
    }

    /**
     * Test method for
     * {@link
     * edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule#getScheduledCourses()}.
     */
    @SuppressWarnings("deprecation") @Test public void testGetScheduledCourses()
    {
        try {
            var courses = CourseRecordIO.readCourseRecords(pth);
            var sc = new Schedule();

            sc.addCourseToSchedule(courses.get(0));
            sc.addCourseToSchedule(courses.get(4));
            sc.addCourseToSchedule(courses.get(7));

            assertEquals(sc.getScheduledCourses().length, 3);

            assertEquals(
                sc.getScheduledCourses()[0],
                courses.get(0).getShortDisplayArray());

        } catch (FileNotFoundException e) {
            fail();
        }
    }

    /**
     * Test method for
     * {@link
     * edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule#setTitle(java.lang.String)}.
     */
    @Test public void testSetTitle()
    {
        try {
            var courses = CourseRecordIO.readCourseRecords(pth);
            var sc = new Schedule();

            sc.addCourseToSchedule(courses.get(0));
            sc.addCourseToSchedule(courses.get(4));

            assertEquals(sc.getScheduledCourses().length, 2);

            sc.resetSchedule();

            assertEquals(sc.getScheduledCourses().length, 0);

            sc.setTitle("viper is tha best");

            assertEquals(sc.getTitle(), "viper is tha best");

        } catch (FileNotFoundException e) {
            fail();
        }
    }

    /**
     * Test method for
     * {@link
     * edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule#addCourseToSchedule(edu.ncsu.csc216.pack_scheduler.course.Course)}.
     */
    @Test public void testAddCourseToSchedule()
    {
        try {
            var courses = CourseRecordIO.readCourseRecords(pth);
            var sc = new Schedule();

            sc.addCourseToSchedule(courses.get(0));
            sc.addCourseToSchedule(courses.get(4));
            sc.addCourseToSchedule(courses.get(7));

            assertEquals(sc.getScheduledCourses().length, 3);
        } catch (FileNotFoundException e) {
            fail();
        }
    }

    /**
     * Test method for
     * {@link
     * edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule#removeCourseFromSchedule(edu.ncsu.csc216.pack_scheduler.course.Course)}.
     */
    @Test public void testRemoveCourseFromSchedule()
    {
        try {
            var courses = CourseRecordIO.readCourseRecords(pth);
            var sc = new Schedule();

            sc.addCourseToSchedule(courses.get(0));
            sc.addCourseToSchedule(courses.get(4));
            sc.addCourseToSchedule(courses.get(7));

            assertEquals(sc.getScheduledCourses().length, 3);

            sc.removeCourseFromSchedule(courses.get(7));

            assertEquals(sc.getScheduledCourses().length, 2);
        } catch (FileNotFoundException e) {
            fail();
        }
    }

    /**
     * Test method for
     * {@link
     * edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule#addCourseToSchedule(edu.ncsu.csc216.pack_scheduler.course.Course)}.
     */
    @Test public void testCanAdd()
    {
        try {
            var courses = CourseRecordIO.readCourseRecords(pth);
            var sc = new Schedule();

            sc.addCourseToSchedule(courses.get(0));

            assertEquals(sc.getScheduledCourses().length, 1);

            assertFalse(sc.canAdd(courses.get(0)));
            assertTrue(sc.canAdd(courses.get(7)));

        } catch (FileNotFoundException e) {
            fail();
        }
    }
}
