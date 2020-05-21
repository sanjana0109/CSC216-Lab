/**
 *
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the InvalidTransitionException class
 *
 * @author Sanjana Cheerla
 */
public class InvalidTransitionExceptionTest
{

    /**
     * Tests both the InvalidTransitionException class' constructor
     */
    @Test public void testInvalidExceptionConstructor()
    {
        InvalidTransitionException it1 =
            new InvalidTransitionException("Custom exception message");
        assertEquals("Custom exception message", it1.getMessage());

        InvalidTransitionException it2 = new InvalidTransitionException();
        assertEquals("Invalid FSM Transition.", it2.getMessage());
    }
}
