
package resources;

/**
 *
 * @author rawandkurdy
 */
public class receptionist_credentials {
    private int id;
    private String username;
    private String password;
    private int receptionist_id;
    boolean discharged;
    //If true means this username is not functional
    
    public static final String id_KEY = "ID";
    public static final String username_KEY = "NAME";
    public static final String password_KEY = "PASSWORD";
    public static final String receptionist_id_KEY = "RECEPTIONIST_ID";
    public static final String discharged_KEY = "DISCHARGED";
    public static final String Table_Name = "RECEPTIONIST_CREDENTIALS";

    public receptionist_credentials(int id, String username, String password, int receptionist_id, boolean discharged) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.receptionist_id = receptionist_id;
        this.discharged = discharged;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getReceptionist_id() {
        return receptionist_id;
    }

    public void setReceptionist_id(int receptionist_id) {
        this.receptionist_id = receptionist_id;
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }
    
    

}
