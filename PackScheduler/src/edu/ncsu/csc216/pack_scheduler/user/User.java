package edu.ncsu.csc216.pack_scheduler.user;

/**
 * User class, has first name, last name, id, email, password, create a user,
 * getters and setters for the fields, and the hash code and equals methods.
 *
 *
 * @author sggephar
 * @author mbabb
 * @author scheerl
 */
public abstract class User
{

    /**
     * Student first name
     */
    protected String firstName;
    /**
     * Student last name
     */
    protected String lastName;
    /**
     * Student id
     */
    protected String id;
    /**
     * Student email
     */
    protected String email;
    /**
     * Student password
     */
    protected String password;

    /**
     * Creates a user object
     *
     * @param firstName first name of user
     * @param lastName  last name of user
     * @param id        id of user
     * @param email     email of user
     * @param password  password of user
     */
    public User(
        String firstName,
        String lastName,
        String id,
        String email,
        String password)
    {
        setFirstName(firstName);
        setLastName(lastName);
        setId(id);
        setEmail(email);
        setPassword(password);
    }

    /**
     * getter of first name
     *
     * @return the firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * setter of first name, setting IllegalArgumentException for null or empty
     * situation.
     *
     * @param firstName the firstName to set
     * @throws IllegalArgumentException if the firstName parameter is null or
     *     empty
     */
    public void setFirstName(String firstName)
    {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("Invalid first name");
        }
        this.firstName = firstName;
    }

    /**
     * getter of last name
     *
     * @return the lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * setter of last name, throw IllegalArgumentException for empty or null
     * situation
     *
     * @param lastName the lastName to set
     * @throws IllegalArgumentException if the lastName parameter is null or
     *     empty
     */
    public void setLastName(String lastName)
    {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Invalid last name");
        }
        this.lastName = lastName;
    }

    /**
     * getter of id
     *
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**
     * setter of id, throw IllegalArgumentException for empty or null situation
     *
     * @param id the id to set
     * @throws IllegalArgumentException if the id parameter is null or empty
     */
    protected void setId(String id)
    {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Invalid id");
        }
        this.id = id;
    }

    /**
     * getter of email
     *
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * setter of eamil, throw IllegalArgumentException for empty, null, does not
     * contain @, does not contain . , and . before @ situation
     *
     * @param email the email to set
     * @throws IllegalArgumentException if the email parameter is null or empty,
     *                                  doesn't contain an "@" or "." character,
     * or the index of the last "." is earlier than the index of the first "@"
     * character.
     */
    public void setEmail(String email)
    {

        if (email == null || email.isEmpty() || !email.contains("@") ||
            !email.contains(".")) {
            throw new IllegalArgumentException("Invalid email");
        }
        if (email.lastIndexOf('.') < email.indexOf("@")) {
            throw new IllegalArgumentException("Invalid email");
        }

        this.email = email;
    }

    /**
     * getter of password
     *
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * setter of password, throw IllegalArgumentException for empty or null
     * situation
     *
     * @param password the password to set
     * @throws IllegalArgumentException if the password parameter is null or
     *     empty
     */
    public void setPassword(String password)
    {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Invalid password");
        }
        this.password = password;
    }

    /**
     * generate hash code for user using all fields
     *
     * @return hashCode for Student
     */
    @Override public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result =
            prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result =
            prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result =
            prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    /**
     * Compares a given object to this user for equality on all fields
     *
     * @param obj the object to compare
     * @return true if the object parameter and this Student are the same on all
     *         fields
     */
    @Override public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        return true;
    }
}