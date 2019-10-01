package Controller;
import Model.Boat;
import Model.Member;
import org.json.JSONObject;
import org.json.JSONArray;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class The_Brain {

    public void addMember(String name, String personalNumber){

        Member member = new Member(name, personalNumber);

        //CREATE an ID
        long ID = System.currentTimeMillis();
        String nbr = Long.toString(ID);
        nbr = nbr.substring(7,13);

        member.setID(nbr);

        //ADD new Member info into a document using JSON

        JSONObject jso = new JSONObject();

        jso.put("name",member.getMemberName());
        jso.put("ID", member.getMemberID());
        jso.put("Personal Number", member.getPersonal_Number());

        try {
            File file = new File("DataBase.json");
            Scanner sT = new Scanner(file);

            String text="";
            while(sT.hasNext()){
                text += sT.nextLine();
            }
            
            if (text.isBlank()){
                JSONArray fefe = new JSONArray();
                fefe.put(jso);
                FileWriter fileWriter = new FileWriter("DataBase.json");
                fileWriter.write(fefe.toString());
                fileWriter.close();

            }else {
                JSONArray fefe = new JSONArray(text);
                boolean isinDatabase = false;
                for (int i = 0; i < fefe.length(); i++) {
                    String check = fefe.getJSONObject(i).getString("Personal Number");
                    if (check.equals(member.getPersonal_Number())) {
                        System.out.print("Already in the database");
                        isinDatabase = true;
                    }
                }
                if (isinDatabase == false) {
                    fefe.put(jso);
                }
                FileWriter fileWriter = new FileWriter("DataBase.json");
                fileWriter.write(fefe.toString());
                fileWriter.close();
            }


        } catch (IOException e){
            e.printStackTrace();
        }

        

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
