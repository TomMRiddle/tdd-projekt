package se.iths;

import java.time.Duration;
import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    public static User user;
    
    public static void main(String[] args) {
        createUser();
        createActivity();
//        System.out.println("""
//                Meny:\s
//                1. Skapa löprunda\s
//                2. Lista alla löprundor\s
//                3. Visa detaljer för en löprunda\s
//                4. Ta bort en löprunda\s
//                """
//        );
//        String input = scanner.nextLine();
//        switch (input) {
//            case "1":
        
              
//            case "2":
//                user.printActivities();
//                break;
//            case "3":
//                System.out.println("Ange ett id för en löprunda");
//                String id = scanner.nextLine();
//                user.printActivityById(id);
//                break;
//            case "4":
//                while(true) {
//                    System.out.println("Ange ett id för en löprunda");
//                    String id2 = scanner.nextLine();
//                    try {
//                        System.out.println("Vill du ta bort följande löprunda? (ja/nej)");
//                        user.printActivityById(id2);
//                        if(scanner.nextLine().equals("ja")) {
//                            user.deleteActivityById(id2);
//                            System.out.println("Löprunda med id " + id2 + " har tagits bort");
//                            break;
//                        }
//                    } catch (NullPointerException e) {
//                        System.out.println(e);
//                    }
//                }
        //}
    }
    public static void createUser() {
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter a height in centimeters");
        int height = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter weight in kilograms");
        int weight = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter age in years");
        int age = Integer.parseInt(scanner.nextLine());
        user = new User(name);
        user.setHeight(height);
        user.setWeight(weight);
        user.setAge(age);
        System.out.println(user);
    }

    public static void createActivity() {

        System.out.println("Enter distance in kilometers:");
        int distance = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Enter elapsed time in the format HH:mm:ss");
        String timeInput = scanner.nextLine();
                String[] timeParts = timeInput.split(":");
                Duration duration = Duration.ofHours(Long.parseLong(timeParts[0]))
                        .plusMinutes(Long.parseLong(timeParts[1]))
                        .plusSeconds(Long.parseLong(timeParts[2]));
        
        System.out.println("Enter a date in the format YYYY-MM-DD (ex.2025-09-01), leave empty for today's date: ");
        String date = scanner.nextLine();
        Activity activity = new Activity(distance, duration, date);
        System.out.println(activity);
        //user.addActivity(activity);
    }
 }

