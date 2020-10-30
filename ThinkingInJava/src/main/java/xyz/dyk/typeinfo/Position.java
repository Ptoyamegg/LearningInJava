package xyz.dyk.typeinfo;

public class Position {
    private String title;
    private Person person;
    public Position(String jobIitle, Person employee) {
        title = jobIitle;
        person = employee;
        if (person == null)
            person = Person.Null;
    }
    public Position(String jobTitle) {
        title = jobTitle;
        person = Person.Null;
    }
    public String getTitle() { return title;}
    public void setTitle(String newTitle) { this.title = newTitle; }
    public Person getPerson() { return person; }
    public void setPerson(Person newPerson) {
        this.person = newPerson;
    }
    public String toString() {
        return "\nPosition: " + title + " " + person;
    }
}
