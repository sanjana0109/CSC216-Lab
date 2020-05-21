package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for ConflictException.java. Tests both the constructors in
 * ConflictException.java.
 *
 * @author Sanjana Cheerla
 *
 */
public class ConflictExceptionTest
{

    /**
     * Test method for
     * {@link
     * edu.ncsu.csc216.pack_scheduler.course.ConflictException#ConflictException(java.lang.String)}.
     * Source: Writing Test Methods from GP3 Guided Task: Implement and Test
     * ConflictException.
     */
    @Test public void testConflictExceptionString()
    {
        ConflictException ce =
            new ConflictException("Custom exception message");
        assertEquals("Custom exception message", ce.getMessage());
    }

    /**
     * Test method for
     * {@link
     * edu.ncsu.csc216.pack_scheduler.course.ConflictException#ConflictException()}.
     */
    @Test public void testConflictException()
    {
        ConflictException ce = new ConflictException();
        assertEquals("Schedule conflict.", ce.getMessage());
    }
}
