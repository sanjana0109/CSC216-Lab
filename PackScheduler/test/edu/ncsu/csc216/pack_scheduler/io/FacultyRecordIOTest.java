package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Test for FacultyRecordIO class
 * 
 * @author Raymond Dong
 *
 */
public class FacultyRecordIOTest {
    /** Valid faculty records */
    private final String validTestFile = "test-files/faculty_records.txt";

    /** Invalid faculty records */
    private final String invalidTestFile = "test-files/invalid_faculty_records.txt";

    /** Expected results for valid faculty, Valid Faculty String 0 */
    private final String validFaculty0 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,pw,2";
    /** Valid Faculty String 1 */
    private final String validFaculty1 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,pw,3";
    /** Valid Faculty String 2 */
    private final String validFaculty2 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,pw,1";
    /** Valid Faculty String 3 */
    private final String validFaculty3 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,pw,3";
    /** Valid Faculty String 4 */
    private final String validFaculty4 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,pw,1";
    /** Valid Faculty String 5 */
    private final String validFaculty5 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,pw,3";
    /** Valid Faculty String 6 */
    private final String validFaculty6 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,pw,1";
    /** Valid Faculty String 7 */
    private final String validFaculty7 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,pw,2";

    /** Array to hold expected results */
    private String[] validFacultyList = { validFaculty0, validFaculty1, validFaculty2, validFaculty3, validFaculty4,
            validFaculty5, validFaculty6, validFaculty7 };

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
     * Testing if test correctly set up.
     */
    @Before
    public void setUp() {
        try {
            String password = "pw";
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            digest.update(password.getBytes());
            hashPW = new String(digest.digest());

            for (int i = 0; i < validFacultyList.length; i++) {
                validFacultyList[i] = validFacultyList[i].replace(",pw,", "," + hashPW + ",");
            }
        } catch (NoSuchAlgorithmException e) {
            fail("Unable to create hash during setup");
        }
    }

    /**
     * Testing if the FacultyRecordIO correctly read the faculty file.
     */
    @Test
    public void testReadFacultyRecords() {
        try {
            LinkedList<Faculty> facultyList = FacultyRecordIO.readFacultyRecords(validTestFile);
            assertEquals(8, facultyList.size());

            assertEquals(validFacultyList[7], facultyList.get(7).toString());
            assertEquals(validFacultyList[6], facultyList.get(6).toString());
            assertEquals(validFacultyList[5], facultyList.get(5).toString());
            assertEquals(validFacultyList[4], facultyList.get(4).toString());
            assertEquals(validFacultyList[3], facultyList.get(3).toString());
            assertEquals(validFacultyList[2], facultyList.get(2).toString());
            assertEquals(validFacultyList[1], facultyList.get(1).toString());
            assertEquals(validFacultyList[0], facultyList.get(0).toString());
        } catch (FileNotFoundException e) {
            fail("Unexpected error reading " + validFacultyList);
        }

        LinkedList<Faculty> facultyList;
        try {
            facultyList = FacultyRecordIO.readFacultyRecords(invalidTestFile);
            assertEquals(0, facultyList.size());
        } catch (FileNotFoundException e) {
            fail("Unexpected FileNotFoundException");
        }
    }

    /**
     * Testing if the writer correctly write out the file.
     */
    @Test
    public void testWriteFacultyRecords() {
        LinkedList<Faculty> facultyList = new LinkedList<Faculty>();
        facultyList.add(new Faculty("Zeyu", "Chang", "znchang", "znchang@ncsu.edu", "123", 1));
        facultyList.add(new Faculty("Alex", "Mercer", "malex", "malex@ncsu.edu", "1_2_3", 2));
        facultyList.add(new Faculty("Wenchun", "Han", "wchan", "wchan@ncsu.edu", "asdfghjkl1_2_3", 3));

        try {
            FacultyRecordIO.writeFacultyRecords("test-files/actual_faculty_records.txt", facultyList);
        } catch (IOException e) {
            fail("Cannot write to student records file");
        }
    }

}
