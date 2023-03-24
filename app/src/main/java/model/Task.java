package model;

import java.util.Date;

public class Task {

    private int id;
    private int idProject;
    private String name;
    private String description;
    private String notes;
    private Boolean completed;
    private Date deadline;
    private Date createdDate;
    private Date updatedDate;

    public Task(int id, int idProject, String name, String description, String notes, Boolean completed, Date deadline,
            Date createdDate, Date updatedDate) {
        this.id = id;
        this.idProject = idProject;
        this.name = name;
        this.description = description;
        this.notes = notes;
        this.completed = completed;
        this.deadline = deadline;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Task() {
        this.createdDate = new Date();
        this.updatedDate = new Date();
        this.deadline = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getCompleted() {
        return false;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", idProject=" + idProject + ", name=" + name + ", description=" + description
                + ", notes=" + notes + ", completed=" + completed + ", deadline=" + deadline + ", createdDate="
                + createdDate + ", updatedDate=" + updatedDate + "]";
    }

    public String getIdName() {
        return null;
    }

    public void add(Task task) {

    }

    public boolean getCompleted(Boolean b) {
        return false;
    }

}
