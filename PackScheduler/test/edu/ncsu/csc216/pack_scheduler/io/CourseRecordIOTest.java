package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Test for CourseRecordIO class
 * 
 * @author Raymond Dong
 *
 */
public class CourseRecordIOTest {
    /** Valid course records */
    private final String validTestFile = "test-files/course_records.txt";
    
    /** Invalid course records */
    private final String invalidTestFile = "test-files/invalid_course_records.txt";
    
    /** Expected results for valid course, Valid Course String 0 */
    private final String validCourse0 = "CSC116,Intro to Programming - Java,001,3,jdyoung2,10,MW,910,1100";
    /** Valid Course String 1 */
    private final String validCourse1 = "CSC116,Intro to Programming - Java,002,3,spbalik,10,MW,1120,1310";
    /** Valid Course String 2 */
    private final String validCourse2 = "CSC116,Intro to Programming - Java,003,3,tbdimitr,10,TH,1120,1310";
    /** Valid Course String 3 */
    private final String validCourse3 = "CSC116,Intro to Programming - Java,002,3,jtking,10,TH,910,1100";
    /** Valid Course String 4 */
    private final String validCourse4 = "CSC216,Programming Concepts - Java,001,4,sesmith5,10,TH,1330,1445";
    /** Valid Course String 5 */
    private final String validCourse5 = "CSC216,Programming Concepts - Java,002,4,jtking,10,MW,1330,1445";
    /** Valid Course String 6 */
    private final String validCourse6 = "CSC216,Programming Concepts - Java,601,4,jep,10,A";
    /** Valid Course String 7 */
    private final String validCourse7 = "CSC226,Discrete Mathematics for Computer Scientists,001,3,tmbarnes,10,MWF,935,1025";
    /** Valid Course String 8 */
    private final String validCourse8 = "CSC230,C and Software Tools,001,3,dbsturgi,10,MW,1145,1300";
    
    /** Array to hold expected results */
    private String[] validCourseList = { validCourse0, validCourse1, validCourse2, validCourse3, validCourse4,
            validCourse5, validCourse6, validCourse7, validCourse8 };
    
    /**
     * Helper method to compare two files for the same contents
     *
     * @param expFile expected output
     * @param actFile actual output
     */
    @SuppressWarnings("unused")
    private void checkFiles(String expFile, String actFile) {
        try {
            Scanner expScanner = new Scanner(new FileInputStream(expFile));
            Scanner actScanner = new Scanner(new FileInputStream(actFile));

            while (expScanner.hasNextLine() && actScanner.hasNextLine()) {
                String exp = expScanner.nextLine();
                String act = actScanner.nextLine();
                assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
            }
            if (expScanner.hasNextLine()) {
                fail("The expected results expect another line " + expScanner.nextLine());
            }
            if (actScanner.hasNextLine()) {
                fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
            }

            expScanner.close();
            actScanner.close();
        } catch (IOException e) {
            fail("Error reading files.");
        }
    }
    
    /**
     * Testing if the CourseRecordIO correctly read the faculty file.
     */
    @Test
    public void testReadCourseRecords() {
        try {
            SortedList<Course> courseList = CourseRecordIO.readCourseRecords(validTestFile);
            assertEquals(8, courseList.size());
        } catch (FileNotFoundException e) {
            fail("Unexpected error reading " + validCourseList);
        }

        SortedList<Course> courseList;
        try {
            courseList = CourseRecordIO.readCourseRecords(invalidTestFile);
            assertEquals(0, courseList.size());
        } catch (FileNotFoundException e) {
            fail("Unexpected FileNotFoundException");
        }
    }

    /**
     * Testing if the writer correctly write out the file.
     */
    @Test
    public void testWriteCourseRecords() {
        SortedList<Course> courseList = new SortedList<Course>();
        courseList.add(new Course("CSC116", "Intro to Programming - Java", "003", 3, "spbalik", 10, "MW", 1250, 1440));
        courseList.add(new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "MW", 1330, 1445));
        courseList.add(new Course("CSC216", "Programming Concepts - Java", "601", 4, "jep", 10, "A"));

        try {
            CourseRecordIO.writeCourseRecords("test-files/actual_course_records.txt", courseList);
        } catch (IOException e) {
            fail("Cannot write to student records file");
        }
    }

}
