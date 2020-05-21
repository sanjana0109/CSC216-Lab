package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.io.StudentRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the StudentRecordIOTest for the StudentRecordIO Class in the src
 * folder.
 *
 * @author znchang waclem scheerl
 *
 */
public class StudentRecordIOTest {

    /** Valid course records */
    private final String validTestFile = "test-files/student_records.txt";

    /** Invalid course records */
    private final String invalidTestFile = "test-files/invalid_student_records.txt";

    /** Expected results for valid courses */
    private final String validStudent0 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
    /** Valid Student String 1 */
    private final String validStudent1 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
    /** Valid Student String 2 */
    private final String validStudent2 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
    /** Valid Student String 3 */
    private final String validStudent3 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
    /** Valid Student String 4 */
    private final String validStudent4 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
    /** Valid Student String 5 */
    private final String validStudent5 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
    /** Valid Student String 6 */
    private final String validStudent6 = "Lane,Berg,lberg,sociis@non.org,pw,14";
    /** Valid Student String 7 */
    private final String validStudent7 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
    /** Valid Student String 8 */
    private final String validStudent8 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
    /** Valid Student String 9 */
    private final String validStudent9 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";

    /** Array to hold expected results */
    private String[] validStudents = { validStudent0, validStudent1, validStudent2, validStudent3, validStudent4,
            validStudent5, validStudent6, validStudent7, validStudent8, validStudent9 };

    /** Variable String hash password */
    private String hashPW;
    /** Instance variable hash algorithm */
    private static final String HASH_ALGORITHM = "SHA-256";

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
     * Testing if test correctly setted up.
     */
    @Before
    public void setUp() {
        try {
            String password = "pw";
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            digest.update(password.getBytes());
            hashPW = new String(digest.digest());

            for (int i = 0; i < validStudents.length; i++) {
                validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
            }
        } catch (NoSuchAlgorithmException e) {
            fail("Unable to create hash during setup");
        }
    }

    /** For testing the constructors hereof. */
    @Test
    public void testConstructorsHereof() {
        var c1 = new StudentRecordIO();
        assertNotNull(c1);
        var c2 = new CourseRecordIO();
        assertNotNull(c2);
    }

    /**
     * Testing if the StudentRecordIO correctly read the student file.
     */
    @Test
    public void testReadStudentRecords() {
        try {
            SortedList<Student> students = StudentRecordIO.readStudentRecords(validTestFile);
            assertEquals(10, students.size());

            assertEquals(validStudents[3], students.get(0).toString());
            assertEquals(validStudents[6], students.get(1).toString());
            assertEquals(validStudents[4], students.get(2).toString());
            assertEquals(validStudents[5], students.get(3).toString());
            assertEquals(validStudents[2], students.get(4).toString());
            assertEquals(validStudents[8], students.get(5).toString());
            assertEquals(validStudents[0], students.get(6).toString());
            assertEquals(validStudents[9], students.get(7).toString());
            assertEquals(validStudents[1], students.get(8).toString());
            assertEquals(validStudents[7], students.get(9).toString());
        } catch (FileNotFoundException e) {
            fail("Unexpected error reading " + validStudents);
        }

        SortedList<Student> students;
        try {
            students = StudentRecordIO.readStudentRecords(invalidTestFile);
            assertEquals(0, students.size());
        } catch (FileNotFoundException e) {
            fail("Unexpected FileNotFoundException");
        }
    }

    /**
     * Testing if the writer correctly write out the file.
     */
    @Test
    public void testWriteStudentRecords() {
        SortedList<Student> students = new SortedList<Student>();
        students.add(new Student("Zeyu", "Chang", "znchang", "znchang@ncsu.edu", "123"));
        students.add(new Student("Alex", "Mercer", "malex", "malex@ncsu.edu", "1_2_3"));
        students.add(new Student("Wenchun", "Han", "wchan", "wchan@ncsu.edu", "asdfghjkl1_2_3"));

        try {
            StudentRecordIO.writeStudentRecords("test-files/actual_student_records.txt", students);
        } catch (IOException e) {
            fail("Cannot write to student records file");
        }
    }
}
