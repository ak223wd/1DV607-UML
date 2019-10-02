package View;
import Controller.The_Brain;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.Scanner;


public class The_Face {
    public static void main (String[] args) {
        The_Brain br = new The_Brain();
        br.createDatabase();
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
        System.out.println("-------------------------");
        System.out.println("1 - Add a Member");
        System.out.println("2 - Delete a Member");
        System.out.println("3 - Update a Member");
        System.out.println("-------------------------");
        System.out.println("4 - Member info");
        System.out.println("-------------------------");
        System.out.println("5 - Add a Boat");
        System.out.println("6 - Delete a Boat");
        System.out.println("7 - Update a Boat");
        System.out.println("-------------------------\n");
        System.out.println("Press \"C\" for Compact List. Or press \"V\" for Verbose List");
        System.out.println("--------------------------------\n");
        System.out.println("8 - Quit\n");

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

            System.out.println("--- Member info ---");

            System.out.println("Personal Number :");
            String personalNumber = sc.nextLine();
            JSONObject member = new JSONObject(br.searchData(personalNumber));

            System.out.println(member.toString(4));


        } else if(chooseNum.equals("5")){

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


        } else if(chooseNum.equals("6")){

            System.out.println("--- Delete a Boat ---");

            System.out.println("Personal Number :");
            String personalNumber = sc.nextLine();




            String boatD =  br.searchData(personalNumber);
            JSONObject member = new JSONObject(boatD);
            JSONArray arr = member.getJSONArray("Boat(s)");


            System.out.println("Here are the available boats : " + arr.toString(4));

            System.out.println("Choose a boat, starting from 0 to " + (arr.length()-1));
            int index = sc.nextInt();

            br.deleteBoat(index, personalNumber);


        } else if(chooseNum.equals("7")){

            System.out.println("--- Update a Boat ---");

            System.out.println("Personal Number :");
            String personalNumber = sc.nextLine();


            String boatD =  br.searchData(personalNumber);
            JSONObject member = new JSONObject(boatD);
            JSONArray arr = member.getJSONArray("Boat(s)");

            System.out.println("Here are the available boats : " + arr.toString(4));

            System.out.println("Choose a boat, starting from 0 to " + (arr.length()-1));
            int index = sc.nextInt();

            System.out.println("Boat Type: ");
            String boatType = sc.next();

            System.out.println("Boat Length: ");
            double boatLength = sc.nextDouble();

            System.out.println("Boat Width: ");
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

                File file = new File("../DataBase.json");
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






        } else if (chooseNum.equals("8")){
            System.exit(0);
        }

    }

}
