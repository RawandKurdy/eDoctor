
package resources;

import java.sql.Date;

/**
 *
 * @author rawandkurdy
 */
public class prescription {
    private int id;
    private String dosage;
    private String details;
    private Date from_Date;
    private Date to_Date;
    public static final String id_KEY="ID";
    public static final String dosage_KEY="DOSAGE";
    public static final String details_KEY="DETAILS";
    public static final String fromDate_KEY="FROM_DATE";
    public static final String toDate_KEY="TO_DATE";
    public static final String Table_Name = "PRESCRIPTION";

    public prescription(int id, String dosage, String details, Date from_Date, Date to_Date) {
        this.id = id;
        this.dosage = dosage;
        this.details = details;
        this.from_Date = from_Date;
        this.to_Date = to_Date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getFrom_Date() {
        return from_Date;
    }

    public void setFrom_Date(Date from_Date) {
        this.from_Date = from_Date;
    }

    public Date getTo_Date() {
        return to_Date;
    }

    public void setTo_Date(Date to_Date) {
        this.to_Date = to_Date;
    }
    
    
    
    
}
