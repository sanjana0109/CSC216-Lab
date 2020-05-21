package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * This class creates the checked conflict exception. There are 2 different
 * constructors available to create the InvalidTransitionException. One
 * constructor has a parameter with a message if the exception is thrown another
 * constructor that creates a ConflictException with a default message.
 *
 * @author Mike Babb
 */
public class InvalidTransitionException extends Exception
{
    /** ID used for serialization. */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a InvalidTransitionException with a message parameter.
     *
     * @param message the message to be used if the exception is thrown.
     */
    public InvalidTransitionException(String message)
    {
        super(message);
    }

    /**
     * Creates a conflict exception with the default "Invalid state transition."
     * message if the exception is thrown.
     */
    public InvalidTransitionException()
    {
        this("Invalid FSM Transition.");
    }
}