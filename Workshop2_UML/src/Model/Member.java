package Model;

public class Member {
    private String Name;
    private String Personal_Number;
    private int ID;

    public Member(String name, String personal_Number) {
        Name = name;
        Personal_Number = personal_Number;
    }


    public String getMemberName(){return Name;}

    public String getPersonal_Number(){return Personal_Number;}

    public int getMemberID(){return ID;}

    public void setMemberName(String name){
        this.Name = name;
    }

    public void setPersonal_Number(String personalNumber){
        this.Personal_Number = personalNumber;
    }
}
