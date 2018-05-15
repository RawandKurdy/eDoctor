
package resources;

/**
 *
 * @author rawandkurdy
 */
public class req_info {
    int id ; //the id of the inserted 
    boolean inserted; //either inserted or not

    public req_info(int id, boolean inserted) {
        this.id = id;
        this.inserted = inserted;
    }

    public req_info() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isInserted() {
        return inserted;
    }

    public void setInserted(boolean inserted) {
        this.inserted = inserted;
    }

    @Override
    public String toString() {
        return "status{" + "id=" + id + ", inserted=" + inserted + '}';
    }
    
    
}
