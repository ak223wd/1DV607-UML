package View;
import Controller.The_Brain;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class The_Face {
    public static void main (String[] args) {

        boolean bool = true;
        while (bool){
            menu();

        }
    }


    public static void menu(){
        The_Brain br = new The_Brain();
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--------------------------------------------------");
        System.out.println("-----------------------MENU-------------------------");
        System.out.println("----------------------------------------------------");

        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("1 - Add a Member");
        System.out.println("2 - Delete a Member");
        System.out.println("3 - Update a Member");
        System.out.println("-------------------------\n");
        System.out.println("4 - Add a Boat");
        System.out.println("5 - Delete a Boat");
        System.out.println("6 - Update a Boat");
        System.out.println("-------------------------\n");
        System.out.println("Press \"C\" for Compact List. Or press \"V\" for Verbose List");
        System.out.println("--------------------------------\n");
        System.out.println("7 - Quit\n");

        System.out.println("--------------------------------\n");

        System.out.print("Your choice :\t");



        String chooseNum = sc.nextLine();

        //ADD a MEMBER
        if(chooseNum.equals("1")){
            System.out.println("--- Add a Member ---");
            System.out.print("Full name : ");
            String name = sc.nextLine();
            System.out.print("Personal number : ");
            String personalNumber = sc.nextLine();
            br.addMember(name,personalNumber);

        } else if (chooseNum.equals("2")){
            System.out.println("--- Delete a Member ---");
            System.out.println("Personal Number :");
            String personalNumber = sc.nextLine();
            br.deleteMember(personalNumber);

        } else if(chooseNum.equals("3")){
            System.out.println("--- Update a Member ---");

            System.out.println("Personal Number :");
            String personalNumber = sc.nextLine();

            System.out.print("Name to Update : ");
            String name = sc.nextLine();

            br.updateMemberData(name,personalNumber);

        } else if(chooseNum.equals("4")){

            System.out.println("--- Add a Boat ---");

            System.out.println("Personal Number :");
            String personalNumber = sc.nextLine();

            System.out.println("Boat Type : ");
            String boatType = sc.nextLine();

            System.out.println("Boat Length : ");
            double boatLength = sc.nextDouble();

            System.out.println("Boat Width :");
            double boatWidth = sc.nextDouble();


            br.addBoat(boatType, boatLength, boatWidth, personalNumber);

        } else if(chooseNum.equals("5")){

            System.out.println("--- Delete a Boat ---");

            System.out.println("Personal Number :");
            String personalNumber = sc.nextLine();




            String boatD =  br.searchData(personalNumber);
            JSONArray arr = new JSONArray(boatD);

            System.out.println("Here are the available boats : " + arr.toString(4));

            System.out.println("Choose a boat, starting from 0 to " + (arr.length()-1));
            Scanner sC = new Scanner(System.in);

            int index = sC.nextInt();

            br.deleteBoat(index, personalNumber);

            sC.close();

        } else if(chooseNum.equals("6")){
            System.out.println("--- Update a Boat ---");

            System.out.println("Personal Number :");
            String personalNumber = sc.nextLine();


            String boatD =  br.searchData(personalNumber);
            JSONArray arr = new JSONArray(boatD);

            System.out.println("Here are the available boats : " + arr.toString(4));

            System.out.println("Choose a boat, starting from 0 to " + (arr.length()-1));
            Scanner sC = new Scanner(System.in);

            int index = sC.nextInt();

            System.out.println("Boat Type : ");
            String boatType = sc.nextLine();

            System.out.println("Boat Length : ");
            double boatLength = sc.nextDouble();

            System.out.println("Boat Width :");
            double boatWidth = sc.nextDouble();


            br.updateBoatData(index ,boatType, boatLength, boatWidth, personalNumber);

        }



        //COMPACT LIST
        else if (chooseNum.equals("C") || chooseNum.equals("c")){




            String dataBase = br.fetchFromDatabase();
            JSONArray jsArr = new JSONArray(dataBase);
            JSONArray jsAAA = new JSONArray();

            for (int i  =0; i<jsArr.length();i++){
                JSONObject jsObj = new JSONObject();

                jsObj.put("name",jsArr.getJSONObject(i).getString("name") );
                jsObj.put("ID", jsArr.getJSONObject(i).getString("ID"));
                jsObj.put("Number of Boats", jsArr.getJSONObject(i).getJSONArray("Boat(s)").length());

                jsAAA.put(jsObj);

            }

            System.out.print(jsAAA.toString(4));





        }

        //VERBOSE LIST
        else if (chooseNum.equals("V")|| chooseNum.equals("v")){


            try {

                File file = new File("DataBase.json");
                Scanner sT = new Scanner(file);
                String text="";

                while(sT.hasNext()){


                    text += sT.nextLine();


                }



                JSONArray json = new JSONArray(text); // Convert text to object
                System.out.println(json.toString(4));



            } catch (Exception e){
                e.printStackTrace();
            }






        } else if (chooseNum.equals("7")){
            System.exit(0);
        }
    }

}
