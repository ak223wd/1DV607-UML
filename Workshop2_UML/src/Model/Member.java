package Model;

public class Member {

    private String name;
    private String personalNumber;
    private String ID;

    public Member(String name, String personalNumber) {

        this.name = name;
        this.personalNumber = personalNumber;
        //CREATE an ID
        long ID = System.currentTimeMillis();
        String nbr = Long.toString(ID);
        nbr = nbr.substring(7,13);

        setID(nbr);
    }


    public String getMemberName(){
        return name;
    }

    public String getPersonal_Number(){
        return personalNumber;
    }

    public String getMemberID(){
        return ID;
    }

    public void setID(String iD){
        this.ID = iD;
    }

    public void setMemberName(String name){
        this.name = name;
    }

    public void setPersonal_Number(String personalNumber){
        this.personalNumber = personalNumber;
    }
}
