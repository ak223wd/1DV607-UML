package Controller;
import Model.Boat;
import Model.Member;

public class The_Brain {

    public void addMember(String name, String personalNumber){

        Member mdr = new Member(name, personalNumber);

        //CREATE an ID
        

        //ADD new Member info into a document using JSON




    }

    public void addBoat(String boatType, double boatLength, double boatWidth){

        Boat bt = new Boat(boatType, boatWidth, boatLength);
    }

    //DELETE METHODS

    public void deleteMember(int memberID){

    }

    public void deleteBoat(){

    }

    public void updateMemberData(String name, String personalNumber){

    }

    public void updateBoatData(String boatType, double boatLength, double boatWidth){

    }

    public void searchData(){

    }
}
