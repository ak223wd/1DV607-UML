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
        JSONArray jsa = new JSONArray();

        jso.put("name",member.getMemberName());
        jso.put("ID", member.getMemberID());
        jso.put("Personal Number", member.getPersonal_Number());
        jso.put("Boat(s)", jsa); //ARRAY OF BOATS


        String text = fetchFromDatabase(); //GET JSON STRING TO PUT IT INTO JSONARRAY

        if (text.isBlank()){

            JSONArray memberList = new JSONArray();
            memberList.put(jso);
            writeToDatabase(memberList);

        } else {

            JSONArray memberList = new JSONArray(text);
            boolean isinDatabase = false; //Allow us to check if we have that member in the DB

            for (int i = 0; i < memberList.length(); i++) {

                String check = memberList.getJSONObject(i).getString("Personal Number");

                if (check.equals(member.getPersonal_Number())) {
                    System.out.println("Already in the database");
                    isinDatabase = true;
                }
            }

            if (isinDatabase == false) {

                memberList.put(jso);

            }

            writeToDatabase(memberList);
        }

    }

    public void addBoat(String boatType, double boatLength, double boatWidth, String personalNumber){

        Boat bt = new Boat(boatType, boatWidth, boatLength);

        JSONObject jsoBoat = new JSONObject();

        jsoBoat.put("Boat Type", bt.getBoatType());
        jsoBoat.put("Boat Length", bt.getBoatLength());
        jsoBoat.put("Boat Width", bt.getBoatWidth());


        String text = fetchFromDatabase(); //GET JSON STRING TO PUT IT INTO JSONARRAY


        JSONArray memberList = new JSONArray(text);
        boolean isBoatInDB = false; //Allow us to check if we have that member in the DB

        //JSONArray boatList = new JSONArray();
        JSONArray jsa = new JSONArray();

        for (int i = 0; i < memberList.length(); i++) {

            String check = memberList.getJSONObject(i).getString("Personal Number");

            if (check.equals(personalNumber)) {
                JSONArray boats = memberList.getJSONObject(i).getJSONArray("Boat(s)");
                boats.put(jsoBoat);
                memberList.getJSONObject(i).put("Boat(s)", boats);
            }
        }
        writeToDatabase(memberList);
    }





    //DELETE METHODS
    public void deleteMember(String personalnb){
        String memberList = fetchFromDatabase();
        JSONArray members = new JSONArray(memberList);
        JSONArray updatedMemberList = new JSONArray();

        for (int i=0;i<members.length();i++){
            if(!personalnb.equals(members.getJSONObject(i).getString("Personal Number"))){
                updatedMemberList.put(members.getJSONObject(i));
            }
        }

        writeToDatabase(updatedMemberList);

    }

    public void deleteBoat(int index, String personalNB){
        String list = fetchFromDatabase();
        JSONArray memberList = new JSONArray(list);
        JSONArray updatedBoats = new JSONArray();
        for(int i=0;i<memberList.length();i++){
            if (memberList.getJSONObject(i).getString("Personal Number").equals(personalNB)){
                JSONArray boats = memberList.getJSONObject(i).getJSONArray("Boat(s)");
                System.out.println(boats.length());
                if(boats.length()<index|| index<0){
                    System.out.print("There is no boat with that number");
                }else {
                    for(int j=0;j<boats.length();j++){
                        if (j!=index){
                            updatedBoats.put(boats.getJSONObject(j));
                        }
                    }
                }
                memberList.getJSONObject(i).put("Boat(s)",updatedBoats);
            }
        }
        writeToDatabase(memberList);

    }

    public void updateMemberData(String name, String personalNumber){
        String list = fetchFromDatabase();
        JSONArray memberList = new JSONArray(list);

        for(int i=0;i<memberList.length();i++){
            if (personalNumber.equals(memberList.getJSONObject(i).getString("Personal Number"))){
                memberList.getJSONObject(i).put("name",name);
            }
        }

        writeToDatabase(memberList);

    }

    public void updateBoatData(int index, String boatType, double boatLength, double boatWidth, String personalNB){
        String list = fetchFromDatabase();
        JSONArray memberList = new JSONArray(list);

        for(int i=0;i<memberList.length();i++){
            if (memberList.getJSONObject(i).getString("Personal Number").equals(personalNB)){
                JSONArray boats = memberList.getJSONObject(i).getJSONArray("Boat(s)");

                if(boats.length()<index|| index<0){
                    System.out.print("There is no boat with that number");
                }else {
                    for(int j=0;j<boats.length();j++){
                        if (j==index){
                            boats.getJSONObject(j).put("Boat Type",boatType);
                            boats.getJSONObject(j).put("Boat Length",boatLength);
                            boats.getJSONObject(j).put("Boat Width",boatWidth);
                        }
                    }
                }
                memberList.getJSONObject(i).put("Boat(s)",boats);
            }
        }
        writeToDatabase(memberList);

    }

    public String searchData(String personalNB){

        String list = fetchFromDatabase();
        JSONArray memberList = new JSONArray(list);
        String memberData="";
        for (int i=0;i<memberList.length();i++){
           if(memberList.getJSONObject(i).getString("Personal Number").equals(personalNB)){
                memberData = memberList.getJSONObject(i).toString();
           }
        }

        return memberData;

    }



//READ DATA FROM DATABASE
    public String fetchFromDatabase() {

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



//WRITE DATA TO DATABASE
    private void writeToDatabase(JSONArray database){
        try {
            FileWriter fileWriter = new FileWriter("DataBase.json");
            fileWriter.write(database.toString());
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
