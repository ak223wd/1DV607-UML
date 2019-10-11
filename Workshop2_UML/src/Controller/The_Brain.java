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

    /*
        This Class will be used as the controller. All the methods will be concentrated into this class.

     */
    //Method to add a member by taking their name and personal number.
    public void addMember(String name, String personalNumber){

        Member member = new Member(name, personalNumber); //Creates an object of the class Member.

        //ADD new Member info into a document using JSON
        JSONObject jso = new JSONObject();
        JSONArray jsa = new JSONArray();
        //The following lines will add all the information into the JSON Object.
        jso.put("name",member.getMemberName());
        jso.put("ID", member.getMemberID());
        jso.put("Personal Number", member.getPersonal_Number());
        jso.put("Boat(s)", jsa); //We are using a JSON Array of JSONobjects to store the boats.


        String text = fetchFromDatabase(); //Call of the method "fetchFromDatabase" and return a string of an JSON Array

        if (text.isEmpty()){
            /*
            If the String "text" is empty then creates a new JSON Array and add the JSON Object(Member).
            */
            JSONArray memberList = new JSONArray();
            memberList.put(jso);
            writeToDatabase(memberList); //Method to write the JSON Array to a JSON file for the database.

        } else {
            /*
            If the "text" is not empty it will come here.
             */
            JSONArray memberList = new JSONArray(text); // Creating a new JSON Array and adding all the current Database into it.
            boolean isinDatabase = false; //Allow us to check if we have that member in the DB

            for (int i = 0; i < memberList.length(); i++) {//Loop that checks every JSON Objects into the JSON Array.

                String check = memberList.getJSONObject(i).getString("Personal Number");//Saves the personal number of the member into the String check

                if (check.equals(member.getPersonal_Number())) {//if the personal number(PN) from the database match with the one we are trying to create,
                    System.out.println("Already in the database");// It will Print this if the PN is already in the Database
                    isinDatabase = true;  //And will set this boolean to true so that we know it is already in the database
                    break; //go out of the loop
                }
            }

            if (isinDatabase == false) {//Will go in of the PN is not in the database

                memberList.put(jso);//add the JSON object to our JSON array

            }

            writeToDatabase(memberList); //updates the changes to the database
        }

    }
    //This method will be used to add a new boat
    public void addBoat(String boatType, double boatLength, double boatWidth, String personalNumber){

        Boat bt = new Boat(boatType, boatWidth, boatLength);// Creates an object of the class Boat

        //Creates a new JSONobject and put all the information of the boat into it.
        JSONObject jsoBoat = new JSONObject();
        jsoBoat.put("Boat Type", bt.getBoatType());
        jsoBoat.put("Boat Length", bt.getBoatLength());
        jsoBoat.put("Boat Width", bt.getBoatWidth());


        String text = fetchFromDatabase(); //get the string of the database


        JSONArray memberList = new JSONArray(text); // Creates a JSONArray with the database information
        boolean isBoatInDB = false; //Allow us to check if we have that member in the DB

        //Loop through the JSONarray to look for each member in the database
        for (int i = 0; i < memberList.length(); i++) {
            //Save the personal number
            String check = memberList.getJSONObject(i).getString("Personal Number");

            if (check.equals(personalNumber)) { //Checks if the PN matches with the given one. if it matches go inside if otherwise go to the else statement
                //Gets the JSONarray of Boats
                JSONArray boats = memberList.getJSONObject(i).getJSONArray("Boat(s)");
                boats.put(jsoBoat); // Adds the boat to the array of boats
                memberList.getJSONObject(i).put("Boat(s)", boats); //updates the the array of boats of the member
            }else{
                System.out.println("This personal number has not been registered before");
            }
        }
        writeToDatabase(memberList); //Update the database with the correct changes
    }





    //This method will be used for deleting member.
    public void deleteMember(String personalnb){
        String memberList = fetchFromDatabase(); //Copy the database into a String
        JSONArray members = new JSONArray(memberList);//add the database into the JSONArray
        JSONArray updatedMemberList = new JSONArray();//Creates a new JSONArray where the updated member list will be saved

        //loop through the database to check for the user to delete
        for (int i=0;i<members.length();i++){
            /*
            If the personal number from the database does not match the given one then we will add the Member to the database
            When the personal number from the database will match the given one then the member will not be added to the database
            This is how we will delete a member from the database
             */
            if(!personalnb.equals(members.getJSONObject(i).getString("Personal Number"))){
                updatedMemberList.put(members.getJSONObject(i));
            }
        }

        writeToDatabase(updatedMemberList); //Updates the database

    }

    //This method will be used for deleting Boats from a member
    public void deleteBoat(int index, String personalNB){
        String list = fetchFromDatabase();//Copy the database into a String
        JSONArray memberList = new JSONArray(list); //Add the database into the JSONArray
        JSONArray updatedBoats = new JSONArray();////Creates a new JSONArray where the updated boat list will be saved

        //Loop through all the members
        for(int i=0;i<memberList.length();i++){
            //Goes into the if statement if the personal nb from database is matching the given one
            if (memberList.getJSONObject(i).getString("Personal Number").equals(personalNB)){

                JSONArray boats = memberList.getJSONObject(i).getJSONArray("Boat(s)"); //Get the JSON Array of the boats of the required member

                if(boats.length()<index|| index<0){//Checks if the "player" entered an incorrect number
                    System.out.print("There is no boat with that number");
                }else {//if the "player" entered a correct number then goes to the else statement.
                    //Loop to go through all the boats of the member
                    for(int j=0;j<boats.length();j++){
                        /*
                        If the j is not equal to the index add the boat to the updatedBoats JSONArray.
                        Otherwise do nothing. With this way the boat that the "player" selected will not be added
                         */
                        if (j!=index){
                            updatedBoats.put(boats.getJSONObject(j));
                        }
                    }
                }
                memberList.getJSONObject(i).put("Boat(s)",updatedBoats); //Updates the JSONArray of boats for the corresponding member
            }else{
                System.out.println("Personal number was not found in the database");
            }
        }
        writeToDatabase(memberList); //updated the database

    }
    //Method to update the member information
    public void updateMemberData(String name, String personalNumber){
        String list = fetchFromDatabase();  ////Copy the database into a String
        JSONArray memberList = new JSONArray(list);//Add the database into the JSONArray

        //Loop through the member list to find the desired member
        for(int i=0;i<memberList.length();i++){
            //if the personal number given is matching the one from the database then updates the name of that particular member.
            //Otherwise tell the "player" that the personal number was not found
            if (personalNumber.equals(memberList.getJSONObject(i).getString("Personal Number"))){
                memberList.getJSONObject(i).put("name",name);
            }else{
                System.out.println("The given personal number was not found in the database");
            }
        }

        writeToDatabase(memberList); //updates the database

    }
    //Method to update the information of a boat.
    public void updateBoatData(int index, String boatType, double boatLength, double boatWidth, String personalNB){
        String list = fetchFromDatabase(); //Copy the database into a String.
        JSONArray memberList = new JSONArray(list);//Add the database into the JSONArray.

        //To loop through the member list to find the desired member
        for(int i=0;i<memberList.length();i++){
            /*
            Checks if the given personal number is included into the database.
            If it is, then goes into the if statement. Otherwise will go to the else statement.
             */
            if (memberList.getJSONObject(i).getString("Personal Number").equals(personalNB)){
                //Get the JSONArray of boats of the desired member
                JSONArray boats = memberList.getJSONObject(i).getJSONArray("Boat(s)");

                if(boats.length()<index|| index<0){ //to check if the "player" has given the wrong index
                    System.out.print("There is no boat with that number");
                }else {
                    //To loop through all the boats of the member to update the correct one
                    for(int j=0;j<boats.length();j++){
                        //When it founds the correct boat, then updates the information of the boat
                        if (j==index){
                            boats.getJSONObject(j).put("Boat Type",boatType);
                            boats.getJSONObject(j).put("Boat Length",boatLength);
                            boats.getJSONObject(j).put("Boat Width",boatWidth);
                        }
                    }
                }
                memberList.getJSONObject(i).put("Boat(s)",boats);//updates the information of the boats of the user
            }else{
                System.out.println("Personal number was not found in the database");
            }
        }
        writeToDatabase(memberList);//updates the database

    }
    //Method to search for a specific user
    public String searchData(String personalNB){

        String list = fetchFromDatabase();//Copy the database into a String
        JSONArray memberList = new JSONArray(list);//Add the database into the JSONArray
        String memberData="";//Create a String that holds the specific member info

        //Loop through the member list to check for the user
        for (int i=0;i<memberList.length();i++){
            /*
            Checks if the given personal number is included into the database.
            If it is, then goes into the if statement. Otherwise will go to the else statement.
             */
            if(memberList.getJSONObject(i).getString("Personal Number").equals(personalNB)){
                //Gets the String of the JSONObject of the specific member
                memberData = memberList.getJSONObject(i).toString();
            }else{
                System.out.println("Personal number was not found in the database");
            }
        }

        return memberData; //Returns the String to where it was called

    }



    //READ DATA FROM DATABASE
    public String fetchFromDatabase() {

        try {
            //Get the file of the Database
            File file = new File("../DataBase.json");
            //Scanner to be able to read the file
            Scanner sT = new Scanner(file);

            String text = "";
            //Will loop until the scanner has no next line to read.
            while (sT.hasNext()) {
                text += sT.nextLine();
            }
            return text; //Return the string of the database.

        } catch (IOException e) {

        }
        return null;
    }

    //WRITE DATA TO DATABASE
    private void writeToDatabase(JSONArray database){
        try {
            //Creates a FileWriter to be able to write on a file.
            FileWriter fileWriter = new FileWriter("../DataBase.json");//Select the name of the file and the location
            fileWriter.write(database.toString()); //Writes the file with the database
            fileWriter.close();//Close the FileWriter
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
