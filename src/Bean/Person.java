package Bean;

import java.util.List;

public class Person {

    private String StudentId;
    private String Name;
    private String Sex;
    private int Age;
    private double Height;
    private double Weight;
//    private List<String> Hobby;

    public Person(){}

    public Person(String studentId,String name,String sex,int age,double height,double weight){
        this.StudentId = studentId;
        this.Name = name;
        this.Sex = sex;
        this.Age = age;
        this.Height = height;
        this.Weight = weight;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(double height) {
        Height = height;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }

}
