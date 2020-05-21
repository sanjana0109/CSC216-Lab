package edu.ncsu.csc216.pack_scheduler.course;

/**
 * This class creates the checked conflict exception. There are 2 different
 * constructors available to create the ConflictException. One constructor has a
 * parameter with a message if the exception is thrown another constructor that
 * creates a ConflictExcetption with a default message.
 * 
 * @author Sanjana Cheerla
 *
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a ConflictException with a message parameter.
	 * 
	 * @param message the message to be used if the exception is thrown.
	 */
	public ConflictException(String message) {
		super(message);
	}

	/**
	 * Creates a conflict exception with the default "Schedule conflict." message if
	 * the exception is thrown.
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}
}
