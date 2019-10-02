package Model;

public class Member {
    //********************************************//
    //********THIS CLASS IS FOR THE MEMBER********//
    //********************************************//
    private String name;
    private String personalNumber;
    private String ID;

    //Constructor for the member
    public Member(String name, String personalNumber) {

        this.name = name;
        this.personalNumber = personalNumber;

        //CREATE an User ID, using the current time
        long ID = System.currentTimeMillis();
        String nbr = Long.toString(ID); //Change the long into a String
        nbr = nbr.substring(7,13);  //Only takes from the 7th number to the 13th number

        setID(nbr); //Method to set the User ID.
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
