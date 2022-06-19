package pt.ulisboa.tecnico.classes.student;

import java.util.Scanner;

import pt.ulisboa.tecnico.classes.Constants;

import io.grpc.StatusRuntimeException;

public class Student {

    public static void main(String[] args) {
        System.out.println(Student.class.getSimpleName());
        System.out.printf("Received %d Argument(s)%n", args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.printf("args[%d] = %s%n", i, args[i]);
        }

        boolean debugMode = false;
        if (args.length > 0 && args[args.length - 1].equals(Constants.DEBUG_FLAG)) {
            debugMode = true;
        }

        // Argument verification
        try{
            StudentFrontend studentFrontend = new StudentFrontend(debugMode);

            if(args.length >= 2){
                String[] idSplit = args[0].split("(?<=\\G.{" + 5 + "})");
                if(idSplit.length == 2){
                    if(idSplit[0].equals("aluno")){
                        if(idSplit[1].length() == 4){
                            int number = Integer.parseInt(idSplit[1]);
                            String name = "";

                            // Concatenating student name
                            int nameN = (debugMode) ? (args.length - 1) : args.length;
                            for(int i = 1; i < nameN; i++){
                                if(i == 1) {
                                    name = "" + args[1];
                                }
                                else {
                                    name = name + " " + args[i];
                                }
                            }

                            if((name.length() > 3) && (name.length() <= 30)){
                                try {
                                    Scanner scanner = new Scanner(System.in);
                                    // Command scan
                                    while(true) {
                                        System.out.print("> ");
                                        String line = scanner.nextLine();
                                        String[] split = line.split("\\s+");

                                        if(split.length == 0){
                                            System.out.print(Constants.INVALID_CMD);
                                            continue;
                                        }

                                        String command = split[0];

                                        // Frontend command call
                                        switch(command){
                                            case(Constants.LIST_CMD):
                                                if(split.length == 1) {
                                                    studentFrontend.list();
                                                }
                                                else {
                                                    System.out.print(Constants.INVALID_LIST_CMD);
                                                }
                                                break;

                                            case(Constants.ENROLL_CMD):
                                                if(split.length == 1) {
                                                    studentFrontend.enroll(idSplit[1], name);
                                                }
                                                else {
                                                    System.out.print(Constants.INVALID_ENROLL_CMD);
                                                }
                                                break;

                                            case(Constants.EXIT_CMD):
                                                if(split.length == 1) {
                                                    studentFrontend.closeChannels();
                                                    System.exit(6);
                                                }
                                                else {
                                                    System.out.print(Constants.INVALID_EXIT_CMD);
                                                }
                                                break;
                                            
                                            default:
                                                System.out.print(Constants.INVALID_CMD);
                                        }
                                    }
                                } catch (StatusRuntimeException e) {
                                    System.out.print(Constants.SERVER_UNAVAILABLE);
                                    System.exit(1);
                                }
                            }
                            else{
                                System.out.print(Constants.INVALID_STUDENT_NAME_LENGTH);
                                System.exit(5);
                            }
                        }
                        else{
                            System.out.print(Constants.INVALID_STUDENT_ID);
                            System.exit(4);
                        }
                    }
                    else{
                        System.out.print(Constants.INVALID_STUDENT_ID);
                        System.exit(3);
                    }
                }
                else{
                    System.out.print(Constants.INVALID_STUDENT_ID);
                    System.exit(2);
                }
            }
            else{
                System.out.print(Constants.INVALID_STUDENT);
                System.exit(1);
            }
        } catch (NumberFormatException nfe){
            System.out.print(Constants.INVALID_STUDENT_ID);
            System.exit(0);
        }
    }
}
