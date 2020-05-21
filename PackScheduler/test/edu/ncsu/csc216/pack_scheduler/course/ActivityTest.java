/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * Test class to solely the checkConflict method in the Activity.java class. All
 * other method tests have been already provided and checked in the other test
 * classes of this package.
 * 
 * @author Sanjana Cheerla
 *
 */
public class ActivityTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.course.Activity#checkConflict(edu.ncsu.csc216.pack_scheduler.course.Activity)}.
	 * Source (lines 29 to 51): No Conflict Test and Testing an Exception Conflict
	 * Test from GP3 Guided Task: Test Driven Development. ConflictException.
	 */
	@Test
	public void testCheckConflict() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 100, "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 100, "TH", 1330, 1445);
		try {
			a1.checkConflict(a2);
			assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM",
					a2.getMeetingString());
		} catch (ConflictException e) {
			fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
		}

		// Update a1 with the same meeting days and a start time that overlaps the end
		// time of a2
		a1.setMeetingDays("TH");
		a1.setActivityTime(1445, 1530);
		try {
			a1.checkConflict(a2);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
			assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}

		// checks to see if Arranged courses do not throw an exception
		a1.setMeetingDays("A");
		a2.setMeetingDays("A");
		try {
			a1.checkConflict(a2);
			assertEquals("A", a1.getMeetingDays());
			assertEquals("A", a2.getMeetingDays());
		} catch (ConflictException e) {
			fail();
		}
		//reverse direction
		try {
			a2.checkConflict(a1);
			assertEquals("A", a1.getMeetingDays());
			assertEquals("A", a2.getMeetingDays());
		} catch (ConflictException e) {
			fail();
		}

		// exact same time and day, should throw exception
		a1.setMeetingDays("TH");
		a1.setActivityTime(1445, 1530);
		
		a2.setMeetingDays("TH");
		a2.setActivityTime(1445, 1530);
		try {
			a1.checkConflict(a2);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
			assertEquals("TH 2:45PM-3:30PM", a2.getMeetingString());
		}
		//reverse direction
		try {
			a2.checkConflict(a1);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
			assertEquals("TH 2:45PM-3:30PM", a2.getMeetingString());
		}
		
		//course 2 is in between the times of 1
		a1.setMeetingDays("TH");
		a1.setActivityTime(900, 1100);
		
		a2.setMeetingDays("TH");
		a2.setActivityTime(1000, 1030);
		try {
			a1.checkConflict(a2);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 9:00AM-11:00AM", a1.getMeetingString());
			assertEquals("TH 10:00AM-10:30AM", a2.getMeetingString());
		}
		//reverse direction
		try {
			a2.checkConflict(a1);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 9:00AM-11:00AM", a1.getMeetingString());
			assertEquals("TH 10:00AM-10:30AM", a2.getMeetingString());
		}
		
		//course 2 ends while course 1 is in session
		a1.setMeetingDays("TH");
		a1.setActivityTime(900, 1100);
		
		a2.setMeetingDays("TH");
		a2.setActivityTime(800, 1030);
		try {
			a1.checkConflict(a2);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 9:00AM-11:00AM", a1.getMeetingString());
			assertEquals("TH 8:00AM-10:30AM", a2.getMeetingString());
		}
		//reverse direction
		try {
			a2.checkConflict(a1);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 9:00AM-11:00AM", a1.getMeetingString());
			assertEquals("TH 8:00AM-10:30AM", a2.getMeetingString());
		}
		
		//course 2 starts while course 1 is in session
		a1.setMeetingDays("TH");
		a1.setActivityTime(900, 1100);
		
		a2.setMeetingDays("TH");
		a2.setActivityTime(1000, 1130);
		try {
			a1.checkConflict(a2);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 9:00AM-11:00AM", a1.getMeetingString());
			assertEquals("TH 10:00AM-11:30AM", a2.getMeetingString());
		}
		//reverse direction
		try {
			a2.checkConflict(a1);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 9:00AM-11:00AM", a1.getMeetingString());
			assertEquals("TH 10:00AM-11:30AM", a2.getMeetingString());
		}
		
		//course 2 ends at the exact same time as course 1 starts
		a1.setMeetingDays("TH");
		a1.setActivityTime(900, 1100);
		
		a2.setMeetingDays("TH");
		a2.setActivityTime(800, 900);
		try {
			a1.checkConflict(a2);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 9:00AM-11:00AM", a1.getMeetingString());
			assertEquals("TH 8:00AM-9:00AM", a2.getMeetingString());
		}
		//reverse direction
		try {
			a2.checkConflict(a1);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 9:00AM-11:00AM", a1.getMeetingString());
			assertEquals("TH 8:00AM-9:00AM", a2.getMeetingString());
		}
		
		
		//course 2 starts at the exact same time as when course 1 ends
		a1.setMeetingDays("TH");
		a1.setActivityTime(900, 1100);
		
		a2.setMeetingDays("TH");
		a2.setActivityTime(1100, 1130);
		try {
			a1.checkConflict(a2);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 9:00AM-11:00AM", a1.getMeetingString());
			assertEquals("TH 11:00AM-11:30AM", a2.getMeetingString());
		}
		//reverse direction
		try {
			a2.checkConflict(a1);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 9:00AM-11:00AM", a1.getMeetingString());
			assertEquals("TH 11:00AM-11:30AM", a2.getMeetingString());
		}
		
		//course 2 ends at the one minute after course 1 starts
		a1.setMeetingDays("TH");
		a1.setActivityTime(900, 1100);
		
		a2.setMeetingDays("TH");
		a2.setActivityTime(800, 901);
		try {
			a1.checkConflict(a2);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 9:00AM-11:00AM", a1.getMeetingString());
			assertEquals("TH 8:00AM-9:01AM", a2.getMeetingString());
		}
		//reverse direction
		try {
			a2.checkConflict(a1);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 9:00AM-11:00AM", a1.getMeetingString());
			assertEquals("TH 8:00AM-9:01AM", a2.getMeetingString());
		}
		
		//course 2 starts at the one minute before course 1 ends
		a1.setMeetingDays("TH");
		a1.setActivityTime(900, 1100);
		
		a2.setMeetingDays("TH");
		a2.setActivityTime(1059, 1130);
		try {
			a1.checkConflict(a2);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 9:00AM-11:00AM", a1.getMeetingString());
			assertEquals("TH 10:59AM-11:30AM", a2.getMeetingString());
		}
		//reverse direction
		try {
			a2.checkConflict(a1);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 9:00AM-11:00AM", a1.getMeetingString());
			assertEquals("TH 10:59AM-11:30AM", a2.getMeetingString());
		}
		
		//course 2 starts at the one minute after course 1 ends
		a1.setMeetingDays("TH");
		a1.setActivityTime(900, 1100);
		
		a2.setMeetingDays("TH");
		a2.setActivityTime(1101, 1200);
		try {
			a1.checkConflict(a2);
			assertEquals("TH 9:00AM-11:00AM", a1.getMeetingString());
			assertEquals("TH 11:01AM-12:00PM", a2.getMeetingString());
		} catch (ConflictException e) {
			fail();
		}
		//reverse direction
		try {
			a2.checkConflict(a1);
			assertEquals("TH 9:00AM-11:00AM", a1.getMeetingString());
			assertEquals("TH 11:01AM-12:00PM", a2.getMeetingString());
		} catch (ConflictException e) {
			fail();
		}
		
		//course 2 ends at the one minute before course 1 starts
		a1.setMeetingDays("TH");
		a1.setActivityTime(900, 1100);
		
		a2.setMeetingDays("TH");
		a2.setActivityTime(800, 859);
		try {
			a1.checkConflict(a2);
			assertEquals("TH 9:00AM-11:00AM", a1.getMeetingString());
			assertEquals("TH 8:00AM-8:59AM", a2.getMeetingString());
		} catch (ConflictException e) {
			fail();
		}
		//reverse direction
		try {
			a1.checkConflict(a2);
			assertEquals("TH 9:00AM-11:00AM", a1.getMeetingString());
			assertEquals("TH 8:00AM-8:59AM", a2.getMeetingString());
		} catch (ConflictException e) {
			fail();
		}
		
	}

}
