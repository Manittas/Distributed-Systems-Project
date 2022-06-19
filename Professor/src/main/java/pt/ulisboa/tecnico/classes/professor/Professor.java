package pt.ulisboa.tecnico.classes.professor;

import java.util.Scanner;

import pt.ulisboa.tecnico.classes.Constants;

import io.grpc.StatusRuntimeException;

public class Professor {
    public static void main(String[] args) {
        System.out.println(Professor.class.getSimpleName());
        System.out.printf("Received %d Argument(s)%n", args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.printf("args[%d] = %s%n", i, args[i]);
        }

        boolean debugMode = false;
        if (args.length > 0 && args[args.length - 1].equals(Constants.DEBUG_FLAG)) {
            debugMode = true;
        }
        else if (args.length > 0) {   // Wrong flag
            System.out.println(Constants.INVALID_ARG);
            System.exit(1);
        }

        try {
            ProfessorFrontend professorFrontend = new ProfessorFrontend(debugMode);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("> ");

                String line = scanner.nextLine();
                String[] splited = line.split("\\s+");

                if (splited.length == 0) {
                    System.out.print("\n");
                    continue;
                }

                String command = splited[0];

                switch (command) {
                    case (Constants.OPEN_ENROLLMENTS_CMD):
                        try {
                            if (splited.length == 2) {
                                int capacity = Integer.parseInt(splited[1]);
                                professorFrontend.openEnrollments(capacity);
                            } else {
                                System.out.println(Constants.INVALID_OPEN_ENROLLMENTS_CMD);
                            }

                        } catch (NumberFormatException e) {
                            System.out.println(Constants.INVALID_CAPACITY);
                        }
                        break;

                    case (Constants.CLOSE_ENROLLMENTS_CMD):
                        if (splited.length == 1) {
                            professorFrontend.closeEnrollments();
                        }
                        else {
                            System.out.println(Constants.INVALID_CLOSE_ENROLLMENTS_CMD);
                        }
                        break;

                    case (Constants.LIST_CMD):
                        if (splited.length == 1) {
                            professorFrontend.listClass();
                        }
                        else {
                            System.out.println(Constants.INVALID_LIST_CMD);
                        }
                        break;

                    case (Constants.CANCEL_ENROLLMENT_CMD):
                        try {
                            if (splited.length == 2) {
                                String[] results = splited[1].split("(?<=\\G.{" + 5 + "})");
                                if (results.length == 2) {
                                    if (results[0].equals("aluno")) {
                                        if (results[1].length() == 4) {
                                            Integer.parseInt(results[1]);
                                            professorFrontend.cancelEnrollment(results[1]);
                                        } else {
                                            System.out.println(Constants.INVALID_STUDENT_ID);
                                        }
                                    } else {
                                        System.out.println(Constants.INVALID_STUDENT_ID);
                                    }
                                } else {
                                    System.out.println(Constants.INVALID_STUDENT_ID);
                                }
                            } else {
                                System.out.println(Constants.INVALID_CANCEL_ENROLLMENT_CMD);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(Constants.INVALID_STUDENT_ID);
                        }
                        break;

                    case (Constants.EXIT_CMD):
                        if (splited.length > 1) {
                            System.out.println(Constants.INVALID_EXIT_CMD);
                            continue;
                        }

                        professorFrontend.closeChannels();
                        System.exit(0);

                    default:
                        System.out.println(Constants.INVALID_CMD);
                }
            }
        } catch (StatusRuntimeException e) {
            System.out.println(Constants.SERVER_UNAVAILABLE);
            System.exit(1);
        }
    }
}