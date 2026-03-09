import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ConversationManager chat = new ConversationManager();

        User u1 = new User("1", "Mario");
        User u2 = new User("2", "Angie");
        

        chat.addParticipant(u1);
        chat.addParticipant(u2);

        int option;

        do {

            System.out.println("\n--- MENU ---");
            System.out.println("1 Send text message");
            System.out.println("2 Send multimedia");
            System.out.println("3 Create poll");
            System.out.println("4 View messages");
            System.out.println("5 Vote poll");
            System.out.println("0 Exit");

            option = sc.nextInt();
            sc.nextLine();

            if (option == 0) break;

            System.out.print("Enter your username: ");
            String name = sc.nextLine();

            User currentUser = null;

            if (name.equalsIgnoreCase("Mario")) {
                currentUser = u1;
            } else if (name.equalsIgnoreCase("Angie")) {
                currentUser = u2;
            }
         
            
            if (currentUser == null) {
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

                            pm.vote(vote);

                            pm.showResults();
                        }
                    }

                    break;
            }

        } while (option != 0);

        sc.close();
    }
}