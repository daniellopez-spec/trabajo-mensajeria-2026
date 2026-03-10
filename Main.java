import java.util.ArrayList;
import java.util.Scanner;  
 
public class Main {
    private static ArrayList<String> users = new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        ConversationManager chat = new ConversationManager();

        User u1 = new User("1", "Juan");
        User u2 = new User("2", "Angie");

        chat.addParticipant(u1);
        chat.addParticipant(u2);
        users.add("Juan");
        users.add("Angie");
        
        int option;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1 Send text message");
            System.out.println("2 Send multimedia");
            System.out.println("3 Create poll");
            System.out.println("4 View messages");
            System.out.println("5 Vote poll");
            System.out.println("6 Register new user");
            System.out.println("7 View polls and votes");
            System.out.println("0 Exit");

            option = sc.nextInt();
            sc.nextLine();

            if (option == 0) break;

            // Opción 6 y 7 no necesitan validación de usuario
            if (option == 6) {
                System.out.print("Enter new username: ");
                String newUser = sc.nextLine();
                boolean existe = users.stream().anyMatch(u -> u.equalsIgnoreCase(newUser));

                registerUser(newUser);
                if (!existe){
                    User newU = new User("u" + System.currentTimeMillis(), newUser);
                    chat.addParticipant(newU);
                }
                continue;
            }

            if (option == 7) {
                chat.showPolls();
                continue;
            }

            // Validación para las demás opciones
            System.out.print("Enter your username: ");
            String name = sc.nextLine();

            User currentUser = null;

            if (users.stream().anyMatch(u -> u.equalsIgnoreCase(name))) {
                for (User u : chat.getParticipants()) {
                    if (u.getName().equalsIgnoreCase(name)) {
                        currentUser = u;
                        break;
                    }
                }
            } else {
                System.out.println("User not registered.");
                continue;
            }
     
            switch (option) {
                case 1:
                    System.out.print("Message: ");
                    String text = sc.nextLine();
                    TextMessage tm = new TextMessage(
                            "m" + System.currentTimeMillis(),
                            currentUser,
                            text
                    );
                    chat.sendMessage(tm);
                    currentUser.sendMessage(tm);
                    break;

                case 2:
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Type (video/audio/image): ");
                    String type = sc.nextLine();
                    System.out.print("URL: ");
                    String url = sc.nextLine();
                    MultimediaMessage mm = new MultimediaMessage(
                            "m" + System.currentTimeMillis(),
                            currentUser,
                            desc,
                            type,
                            url
                    );
                    chat.sendMessage(mm);
                    currentUser.sendMessage(mm);
                    break;

                case 3:
                    System.out.print("Poll question: ");
                    String question = sc.nextLine();
                    PollMessage poll = new PollMessage(
                            "m" + System.currentTimeMillis(),
                            currentUser,
                            question
                    );
                    System.out.print("Number of options: ");
                    int n = sc.nextInt();
                    sc.nextLine();
                    for (int i = 1; i <= n; i++) {
                        System.out.print("Option " + i + ": ");
                        String op = sc.nextLine();
                        poll.addOption(new Option(i, op));
                    }
                    chat.sendMessage(poll);
                    break;

                case 4:
                    chat.showMessages();
                    break;

                case 5:
                    for (Message m : chat.getMessages()) {
                        if (m instanceof PollMessage) {
                            PollMessage pm = (PollMessage) m;
                            pm.show();
                            System.out.print("Choose option: ");
                            int vote = sc.nextInt();
                            sc.nextLine();
                            pm.vote(vote);
                            pm.showResults();
                        }
                    }
                    break;
            }

        } while (option != 0);

        sc.close();
    }

    public static void registerUser(String name) {
        boolean existe = users.stream().anyMatch(u -> u.equalsIgnoreCase(name));
        if (existe) {
            System.out.println("User '" + name + "' is already registered.");
        } else {
            users.add(name);
            System.out.println("User '" + name + "' registered successfully.");
        }
    }
}