package Bean;

import java.util.List;

public class Person {

    private String StudentId;
    private String Name;
    private String Sex;
    private int Age;
    private double Height;
    private double Weight;
    private List<String> Hobby;
    private String Department;

    public Person(){}

    public Person(String studentId,String name,String sex,int age,double height,double weight,List<String> hobby,String department){
        this.StudentId = studentId;
        this.Name = name;
        this.Sex = sex;
        this.Age = age;
        this.Height = height;
        this.Weight = weight;
        this.Hobby = hobby;
        this.Department = department;
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

    public List<String> getHobby() {
        return Hobby;
    }

    public void setHobby(List<String> hobby) {
        Hobby = hobby;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }
}
