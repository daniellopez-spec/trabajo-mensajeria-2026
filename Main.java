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
        u1.registerUser();
        u2.registerUser();
        u1.participateInConversation();
        u2.participateInConversation();
        users.add("Juan");
        users.add("Angie");

        int option;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1  Send text message");
            System.out.println("2  Send multimedia");
            System.out.println("3  Create poll");
            System.out.println("4  View messages");
            System.out.println("5  Vote poll");
            System.out.println("6  Register new user");
            System.out.println("7  View polls and votes");
            System.out.println("8  Check message status");
            System.out.println("9  Text tools");
            System.out.println("10 Multimedia tools");
            System.out.println("11 Poll statistics");
            System.out.println("0  Exit");

            option = sc.nextInt();
            sc.nextLine();

            if (option == 0) break;

            // Opciones que no necesitan validación de usuario
            if (option == 6) {
                System.out.print("Enter new username: ");
                String newUser = sc.nextLine();
                registerUser(newUser);
                User newU = new User("u" + System.currentTimeMillis(), newUser);
                chat.addParticipant(newU);
                newU.registerUser();
                newU.participateInConversation();
                continue;
            }

            if (option == 7) {
                chat.showPolls();
                continue;
            }

            // Opciones 8, 9, 10, 11 no necesitan usuario
            if (option == 8) {
                if (chat.getMessages().isEmpty()) {
                    System.out.println("No messages yet.");
                } else {
                    System.out.println("\n--- MESSAGE STATUS ---");
                    for (Message m : chat.getMessages()) {
                        m.markAsReceived();
                        m.markAsRead();
                        System.out.println("Message status: " + m.getStatus());
                    }
                }
                continue;
            }

            if (option == 9) {
                System.out.println("\n--- TEXT TOOLS ---");
                boolean hasText = false;
                for (Message m : chat.getMessages()) {
                    if (m instanceof TextMessage) {
                        hasText = true;
                        TextMessage t = (TextMessage) m;
                        System.out.print("Max length to validate: ");
                        int max = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Valid length: " + t.validateLength(max));
                        System.out.println("Summary: " + t.summarizeText());
                    }
                }
                if (!hasText) System.out.println("No text messages found.");
                continue;
            }

            if (option == 10) {
                System.out.println("\n--- MULTIMEDIA TOOLS ---");
                boolean hasMedia = false;
                for (Message m : chat.getMessages()) {
                    if (m instanceof MultimediaMessage) {
                        hasMedia = true;
                        MultimediaMessage mm = (MultimediaMessage) m;
                        mm.show();
                        System.out.println("Valid format: " + mm.validateFormat());
                        mm.viewImage();
                        mm.downloadFile();
                    }
                }
                if (!hasMedia) System.out.println("No multimedia messages found.");
                continue;
            }

            if (option == 11) {
                System.out.println("\n--- POLL STATISTICS ---");
                boolean hasPolls = false;
                for (Message m : chat.getMessages()) {
                    if (m instanceof PollMessage) {
                        hasPolls = true;
                        PollMessage pm = (PollMessage) m;
                        pm.show();
                        System.out.println("Total votes: " + pm.countVotes());
                        System.out.println("Results:");
                        for (Option o : pm.getResults()) {
                            System.out.println("  " + o.getText() + ": " + o.getVotes() + " votes");
                        }
                    }
                }
                if (!hasPolls) System.out.println("No polls found.");
                continue;
            }

            // Validación de usuario para opciones 1-5
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