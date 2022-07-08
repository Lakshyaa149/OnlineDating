import java.util.HashSet;

public class User {

    Integer userId;
    int age;
    double  xLoc;
    double yLoc;
    Gender gender;
    HashSet<Integer> set;

    public User(int age, double xLoc, double yLoc, Gender gender, HashSet<Integer> set, String interest) {
        this.userId=OnlineDating.currentId++;
        this.age = age;
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.gender = gender;
        this.set = new HashSet<>();
        this.interest = interest;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getxLoc() {
        return xLoc;
    }

    public void setxLoc(double xLoc) {
        this.xLoc = xLoc;
    }

    public double getyLoc() {
        return yLoc;
    }

    public void setyLoc(double yLoc) {
        this.yLoc = yLoc;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    String interest;

}
