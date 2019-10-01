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

        //ADD new Member info into a document using JSON

        JSONObject jso = new JSONObject();

        jso.put("name",member.getMemberName());
        jso.put("ID", member.getMemberID());
        jso.put("Personal Number", member.getPersonal_Number());
        jso.put("Boat(s)", JSONObject.NULL);


        String text = fetchFromDatabase();

        if (text.isBlank()){
            JSONArray fefe = new JSONArray();
            fefe.put(jso);
            writeToDatabase(fefe);
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
            writeToDatabase(fefe);
        }

    }

    public void addBoat(String boatType, double boatLength, double boatWidth){

        Boat bt = new Boat(boatType, boatWidth, boatLength);
    }

    //DELETE METHODS
    public void deleteMember(int personalnb){


    }

    private String fetchFromDatabase() {
        try {
            File file = new File("DataBase.json");
            Scanner sT = new Scanner(file);

            String text = "";
            while (sT.hasNext()) {
                text += sT.nextLine();
            }
            return text;

        } catch (IOException e) {
            e.printStackTrace();
        }
       return null;
    }

    private void writeToDatabase(JSONArray database){
        try {
            FileWriter fileWriter = new FileWriter("DataBase.json");
            fileWriter.write(database.toString());
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
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
