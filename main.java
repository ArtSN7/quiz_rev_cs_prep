import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 



public class main{
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in); // creating input object

        ArrayList<String> Array_Plain_Questions = new ArrayList<>(); // creating an array of questions of type plain
        ArrayList<String> Array_Plain_Answers = new ArrayList<>(); // creating an array of answers for the questions of type plain

        ArrayList<String> Array_Choice_Questions = new ArrayList<>(); // creating an array of questions of type choice
        ArrayList<String> Array_Choice_Answers = new ArrayList<>(); // creating an array of answers for the questions of type choice

        Array_Plain_Questions = getting_plain_questions(); // getting plain questions
        Array_Plain_Answers = getting_plain_answers(); // getting plain answers

        Array_Choice_Answers = getting_choice_answers(); // getting choice answers
        Array_Choice_Questions = getting_choice_questions(); // getting choice questions


        ArrayList<Integer> Array_Of_Indx = new ArrayList<>(); // creating an array of randomly generated indxs

        Array_Of_Indx.add(0); // adding 5 numbers
        Array_Of_Indx.add(1); 
        Array_Of_Indx.add(2);
        Array_Of_Indx.add(3);
        Array_Of_Indx.add(4);


        Collections.shuffle(Array_Of_Indx); // shuffling    



        // code of the quiz 

        System.out.println("\nDear friend! Now u will be doing a test. Let's begin!\n\n");

        Integer count = 0;
        String question = "";
        String answer = "";
        String response = ""; // input of the user

        ArrayList<String> Array_Of_Wrong_Question = new ArrayList<>();
        ArrayList<String> Array_Of_Wrong_Answers = new ArrayList<>();

        for (int i = 0; i <= 4; i++) { // iteration for plain questions

            question = Array_Plain_Questions.get(Array_Of_Indx.get(i));
            answer = Array_Plain_Answers.get(Array_Of_Indx.get(i));

            System.out.println(question);
            System.out.println("\n\n");

            response = input.nextLine();

            if (response.equals(answer)){ // if user did answer right

                System.out.println("\n\nYes, that's right. Good job!\n\n");
                
                count += 1;

            }else{ // if user didn't answer right

                System.out.println("\n\nNo, that's wrong. The right answer is:\n");
                System.out.println(answer + "\n\n");

                Array_Of_Wrong_Answers.add(answer);
                Array_Of_Wrong_Question.add(question);

            }

          }





          for (int i = 0; i <= 4; i++) { // iteration for choice questions

            question = Array_Choice_Questions.get(Array_Of_Indx.get(i));
            answer = Array_Choice_Answers.get(Array_Of_Indx.get(i));

            String[] parts = question.split("=");
            System.out.println(parts[0]);

            System.out.println("\n");

            System.out.println(parts[1]);
            System.out.println(parts[2]);
            System.out.println(parts[3]);
            System.out.println(parts[4]);

            System.out.println("\n\n");

            response = input.nextLine();

            if (response.equals(answer)){ // if user did answer right

                System.out.println("\n\nYes, that's right. Good job!\n\n");
                
                count += 1;

            }else{ // if user didn't answer right

                System.out.println("\n\nNo, that's wrong. The right answer is:\n");
                System.out.println(answer + "\n\n");

                Array_Of_Wrong_Answers.add(answer);
                Array_Of_Wrong_Question.add(question);

            }

          }

          // finishin test

          System.out.println("Your score is:\n");
          System.out.println(count);

          System.out.println("\n\nNow let's go through ur mistakes\n\n");
          
          Integer length = Array_Of_Wrong_Question.size();

          for (int i = 0; i < length; i++){

            question = Array_Of_Wrong_Question.get(i);
            
            answer = Array_Of_Wrong_Answers.get(i);

            String[] stuff = question.split("=");

            

            if (stuff.length == 1){

                System.out.println(stuff[0]);
                System.out.println("\nAnswer:\n");
                System.out.println(answer);
                System.out.println("\n\n");


            }else{

                System.out.println(stuff[0]);

                System.out.println("\n");
    
                System.out.println(stuff[1]);
                System.out.println(stuff[2]);
                System.out.println(stuff[3]);
                System.out.println(stuff[4]);
    
                System.out.println("\nAnswer:\n");
                System.out.println(answer);
                System.out.println("\n\n");

            }


          }

          // writing results

          try {
            FileWriter myWriter = new FileWriter("results.txt", true);  
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
            
            response = "Score: " + count + " | Date: " + dtf.format(now);
    
            myWriter.write(response);
            myWriter.write("\n");
            myWriter.close();
      
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
      
          }




    }


    public static ArrayList<String> getting_choice_questions(){ // function which returns array list of question of type choice

        ArrayList<String> response = new ArrayList<>(); // creating an array which will be returned 

        String question = "";

        try { // working with the file

            File myObj = new File("choice_question.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

              String data = myReader.nextLine(); // reading the line

              if (data == "") {

                response.add(question); // adding to the array list
                question = "";

              }else{

                question += data;
                question += "="; // adding a mark to then be able to split

              }

            }

            myReader.close();

          } catch (FileNotFoundException e) {

            System.out.println("An error occurred.");
            e.printStackTrace();

          } // finish working with the file

        response.add(question);

        return response; // returning array list

    }


    public static ArrayList<String> getting_plain_questions(){ // function which returns array list of question of type plain

        ArrayList<String> response = new ArrayList<>(); // creating an array which will be returned 

        try { // working with the file

            File myObj = new File("plain_question.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

              String data = myReader.nextLine(); // reading the line
              response.add(data); // adding to the array list

            }

            myReader.close();

          } catch (FileNotFoundException e) {

            System.out.println("An error occurred.");
            e.printStackTrace();

          } // finish working with the file

        return response; // returning array list

    }


    public static ArrayList<String> getting_plain_answers(){ // function which returns array list of answers of type plain

        ArrayList<String> response = new ArrayList<>(); // creating an array which will be returned 

        try { // working with the file

            File myObj = new File("plain_answer.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

              String data = myReader.nextLine(); // reading the line
              response.add(data); // adding to the array list

            }

            myReader.close();

          } catch (FileNotFoundException e) {

            System.out.println("An error occurred.");
            e.printStackTrace();

          } // finish working with the file

        return response; // returning array list

    }


    public static ArrayList<String> getting_choice_answers(){ // function which returns array list of answers of type choice

        ArrayList<String> response = new ArrayList<>(); // creating an array which will be returned 

        try { // working with the file

            File myObj = new File("choice_answer.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

              String data = myReader.nextLine(); // reading the line
              response.add(data); // adding to the array list

            }

            myReader.close();

          } catch (FileNotFoundException e) {

            System.out.println("An error occurred.");
            e.printStackTrace();

          } // finish working with the file
          

        return response; // returning array list

    }
    
}