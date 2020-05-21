package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * This class implements an FSM to check the validity of a string based on the
 * FSM for this project, found in the project_docs folder. The Course name
 * validator constructor sets the current state to the initial state and the
 * valid state to false. The isValid method checks to see if the passed in
 * String is valid. There are 4 inner classes which are the states of the FSM
 * (Initial, number, letter, and suffix), all of these inner classes extend the
 * abstract class state. The abstract class State has 3 methods which are
 * extended by the 4 inner classes. The methods are onLetter, onDigit and
 * onOther. These methods dictate what should be done when the character being
 * processed is a letter, digit, or another type of character.
 *
 * @author Mike Babb
 * @author Sanjana Cheerla
 * @author Sami Gephart
 */
public class CourseNameValidator
{
    /** boolean value to see if the object reaches a final state */
    private boolean validEndState;
    /** Keeps track of the letters in the object being validated */
    private int letterCount;
    /** keeps track of the digits in the object being validated */
    private int digitCount;

    /** final state for new letter state. */
    private final State stateLetter = new LetterState();
    /** final state for new number state. */
    private final State stateNumber = new NumberState();
    /** final state for new initial state. */
    private final State stateInitial = new InitialState();
    /** final state for new suffix state. */
    private final State stateSuffix = new SuffixState();
    /** The current state of the object being validated. */
    private State currentState;

    /** Maximal number of letters in the prefix. */
    public static final int PREFIX_MAX_LETTER_COUNT = 4;
    /** Number of requisite digits in the string.  */
    public static final int DIGIT_COUNT = 3;
    /** Maximal number of letters in the suffix. */
    public static final int SUFFIX_MAX_LETTER_COUNT = 1;

    /**
     * Creates a course name validator object, current state is set to the
     * initial state, and the valid end state is set to false.
     */
    public CourseNameValidator()
    {
        this.currentState = stateInitial;
        this.validEndState = false;
    }

    // public static void main(String[] args)
    // {
    //     var tmp = "CSC36";

    //     var a = new CourseNameValidator();

    //     try {
    //         var b = a.isValid(tmp);
    //     } catch (Exception e) {
    //         System.out.println("help");
    //     }
    // }

    /**
     * Used to reset all attributes hereof upon the analyzation of an input
     * string.
     */
    private void reset()
    {
        this.currentState = stateInitial;
        this.letterCount = 0;
        this.digitCount = 0;
        this.validEndState = false;
    }

    // public static void main(String[] args) throws InvalidTransitionException
    // {
    //     var s = "E115";
    //     var validator = new CourseNameValidator();
    //     validator.isValid(s);
    // }

    /**
     * Checks to see if the passed in string is a valid string.
     *
     * @param s the string being checked to see if it is a valid FSM
     * @return true if the String s is a valid string based on the FSM provided
     *     in
     *         the project_docs file.
     * @throws InvalidTransitionException if the string has any false
     *     transitions in the FSM provided in the project_docs
     * file.
     */
    public boolean isValid(String s) throws InvalidTransitionException
    {
        if (s == null || s.isEmpty()) {
            return false;
        }
        this.reset();

        for (var ch : s.toCharArray()) {
            if (Character.isLetter(ch)) {
                this.currentState.onLetter();
            } else if (Character.isDigit(ch)) {
                this.currentState.onDigit();
            } else {
                throw new InvalidTransitionException(
                    "Course name can only contain letters and digits.");
            }
        }

        this.validEndState =
            this.currentState == stateSuffix ||
            (this.currentState == stateNumber && digitCount == 3);

        return this.validEndState;
    }

    /**
     * Abstract class that holds the main 3 methods for the state classes in the
     * FSM that extend it. The methods are onLetter, onDigit and onOther. These
     * methods dictate what should be done when the character being processed is
     * a letter, digit, or another type of character.
     *
     * @author Mike Babb
     * @author Sanjana Cheerla
     * @author Sami Gephart
     */
    public abstract class State
    {
        /**
         * Checks to see if the current character is a letter, if it is not an
         * InvalidTransitionException is thrown.
         *
         * @throws InvalidTransitionException if the character is not a letter.
         */
        public abstract void onLetter() throws InvalidTransitionException;

        /**
         * Checks to see if the current character is a digit, if it is not an
         * InvalidTransitionException is thrown.
         *
         * @throws InvalidTransitionException if the character is not a digit.
         */
        public abstract void onDigit() throws InvalidTransitionException;
    }

    /**
     * The InitialState class extends state class. The initial state is when the
     * FSM starts, checking to see if the first character is a letter.
     *
     * @author Mike Babb
     * @author Sanjana Cheerla
     * @author Sami Gephart
     */
    public class InitialState extends State
    {
        /**
         * Throws InvalidTransitionException if the method is called.
         *
         * @throws InvalidTransitionException with the message "Course name must
         *     start
         *                                    with a letter." if the method is
         * called.
         */
        @Override public void onDigit() throws InvalidTransitionException
        {
            throw new InvalidTransitionException(
                "Course name must start with a letter.");
        }

        /**
         * sets current state to letter state, and increments letter count
         *
         * @throws InvalidTransitionException if the method cannot be performed.
         */
        @Override public void onLetter() throws InvalidTransitionException
        {
            letterCount++;
            currentState = stateLetter;
        }
    }

    /**
     * The letter state class that extends the state class. This class is used
     * when the character is a letter.
     *
     * @author Mike Babb
     * @author Sanjana Cheerla
     * @author Sami Gephart
     */
    public class LetterState extends State
    {
        /**
         * Sets the current state to number state, and increments digit count.
         *
         * @throws InvalidTransitionException if the method cannot be performed.
         */
        @Override public void onDigit() throws InvalidTransitionException
        {
            digitCount++;
            currentState = stateNumber;
            letterCount = 0;
        }

        /**
         * checks to see if the letter count is greater than 4, if not
         * increments letter count.
         *
         * @throws InvalidTransitionException with the message "Course name
         *     cannot start
         *                                    with more than 4 letters." if the
         * course name has more than 4 letters.
         */
        @Override public void onLetter() throws InvalidTransitionException
        {
            letterCount++;
            if (letterCount > PREFIX_MAX_LETTER_COUNT) {
                throw new InvalidTransitionException(
                    "Course name cannot start with more than 4 letters.");
            }
        }
    }

    /**
     * This class extends the State class, used when the character is a number.
     *
     * @author Mike Babb
     * @author Sanjana Cheerla
     * @author Sami Gephart
     */
    public class NumberState extends State
    {
        /**
         * Throws an exception if there are more than 3 digits, if not digit
         * count is incremented
         *
         * @throws InvalidTransitionException with the message "Course name must
         *     have 3
         *                                    digits." if the course name has
         * more than 3 digits.
         */
        @Override public void onDigit() throws InvalidTransitionException
        {
            digitCount++;
            if (digitCount > DIGIT_COUNT) {
                throw new InvalidTransitionException(
                    "Course name can only have 3 digits.");
            }
        }

        /**
         * if the digit count is equal to 3, current state is set to state
         * suffix, and letter count is incremented.
         *
         * @throws InvalidTransitionException with the message "Course name must
         *     have 3
         *                                    digits." if the course name does
         * not have 3 digits.
         */
        @Override public void onLetter() throws InvalidTransitionException
        {
            if (digitCount == DIGIT_COUNT) {
                letterCount++;
                currentState = stateSuffix;
            } else {
                throw new InvalidTransitionException(
                    "Course name must have 3 digits.");
            }
        }
    }

    /**
     * The suffix state extends state, used when the state is at the end of the
     * FSM.
     *
     * @author Mike Babb
     * @author Sanjana Cheerla
     * @author Sami Gephart
     */
    public class SuffixState extends State
    {
        /**
         * Throws an exception if there are any digits at the end of the FSM.
         *
         * @throws InvalidTransitionException with the message "Course name
         *     cannot
         *                                    contain digits after the suffix."
         * if there are digits at the end of the FSM.
         */
        @Override public void onDigit() throws InvalidTransitionException
        {
            throw new InvalidTransitionException(
                "Course name cannot contain digits after the suffix.");
        }

        /**
         * Throws if there are more than 1 letters at the end of the FSM.
         *
         * @throws InvalidTransitionException with the message "Course name can
         *     only
         *                                    have a 1 letter suffix." if there
         * are more than 1 letter at the end of the FSM.
         */
        @Override public void onLetter() throws InvalidTransitionException
        {
            letterCount++;
            if (letterCount > SUFFIX_MAX_LETTER_COUNT) {
                throw new InvalidTransitionException(
                    "Course name can only have a 1 letter suffix.");
            }
        }
    }
}