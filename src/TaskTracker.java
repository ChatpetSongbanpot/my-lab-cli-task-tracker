import java.util.Date;

public class TaskTracker {
    private int id;
    private String description;
    private String status;
    private Date created;
    private Date updated;

    public TaskTracker() {
    }

    public TaskTracker(int id, String description, String status, Date created, Date updated) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.created = created;
        this.updated = updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
