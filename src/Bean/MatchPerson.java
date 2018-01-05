package Bean;

import java.util.List;

public class MatchPerson {

    private int minage;
    private int maxage;
    private double minheight;
    private double maxheight;
    private double minweight;
    private double maxweight;
    private List<String> hobby;
    private String department;
    private String sex;

    public MatchPerson(){}

    public MatchPerson(int minage,int maxage,double minheight,double maxheight,double minweight,
                       double maxweight,List<String> hobby,String department,String sex){
        this.minage = minage;
        this.maxage = maxage;
        this.minheight = minheight;
        this.maxheight = maxheight;
        this.minweight = minweight;
        this.maxweight = maxweight;
        this.hobby = hobby;
        this.department = department;
        this.sex = sex;
    }


    public int getMinage() {
        return minage;
    }

    public void setMinage(int minage) {
        this.minage = minage;
    }

    public int getMaxage() {
        return maxage;
    }

    public void setMaxage(int maxage) {
        this.maxage = maxage;
    }

    public double getMinheight() {
        return minheight;
    }

    public void setMinheight(double minheight) {
        this.minheight = minheight;
    }

    public double getMaxheight() {
        return maxheight;
    }

    public void setMaxheight(double maxheight) {
        this.maxheight = maxheight;
    }

    public double getMinweight() {
        return minweight;
    }

    public void setMinweight(double minweight) {
        this.minweight = minweight;
    }

    public double getMaxweight() {
        return maxweight;
    }

    public void setMaxweight(double maxweight) {
        this.maxweight = maxweight;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
