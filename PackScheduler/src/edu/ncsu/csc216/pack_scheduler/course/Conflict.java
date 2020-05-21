/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * This is the conflict interface. Contains the checkConflict method to see if 2
 * activities conflict in the Activity hierarchy.
 * 
 * @author Sanjana Cheerla
 *
 */
public interface Conflict {

	/**
	 * Checks if there is a conflict in between the local activities and the
	 * activity in the parameter. If there is a conflict a ConflictException is
	 * thrown.
	 * 
	 * @param possibleConflictingActivity the activity being checked to see if it
	 *                                    conflicts.
	 * @throws ConflictException is thrown if there is a conflict between the local
	 *                           activities and the parameter.
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
