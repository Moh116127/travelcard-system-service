package labdoubt;

public class Teacher {
    String name;
    String subject;

    public Teacher(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    public void teaching(){
        System.out.println(name+" is teaching "+subject);
    }
}
