import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Message[] createMessages() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("How many messages to create?");
        int size;
        while (true) {
            try {
                size = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input, enter an integer value.");
                scanner.next();
            }
        }
        Message[] messages = new Message[size];

        for (int i = 0; i < messages.length; i++) {
            boolean isValidType = false;

            // Loop until a valid message type ("Text" or "Poll") is entered
            while (!isValidType) {
                System.out.println("Choose type for message " + (i+1) + " (Text or Poll):");
                String type = scanner.nextLine().trim();
                
                try {
                    // Check if the input is either "Text" or "Poll"
                    if (type.equalsIgnoreCase("Text") || type.equalsIgnoreCase("text")) {
                        // Create a TextMessage and add it to the array
                        messages[i] = new TextMessage();
                        System.out.println("Enter message:");
                        String text = scanner.nextLine();
                        if (text.isEmpty()) {
                        throw new IllegalArgumentException("Error: Message cannot be empty.");
                    }
                        ((TextMessage)messages[i]).setText(text);
                        isValidType = true; // Exit the loop
                    } else if (type.equalsIgnoreCase("Poll") || type.equalsIgnoreCase("poll")) {
                        // Create a PollMessage and add it to the array
                        messages[i] = new PollMessage();
                        System.out.println("Enter number of options for Poll message:");
                        int optionNumber = scanner.nextInt();
                        scanner.nextLine();
                        String[] options = new String[optionNumber];
                        for (int j = 0; j < options.length; j++) {
                            System.out.println("Enter option: " + (j+1));
                            options[j] = scanner.nextLine();
                        }
                        ((PollMessage)messages[i]).setOptions(options);
                        isValidType = true; // Exit the loop
                    } else {
                        // If input is neither "Text" nor "Poll", throw an exception
                        throw new IllegalArgumentException("Invalid entry.");
                    }
                } catch (IllegalArgumentException e) {
                    // Print the error message and prompt the user to try again
                    System.out.println(e.getMessage());
                }
            }
        }

        return messages;
    }
    public static void main(String[] args) {
        Message[] messages = createMessages();

        int totalMessages = 0;
        int textMessages = 0;
        int pollMessages = 0;
        int totalSize = 0;
        int maxSize = 0;

        // Iterate through the messages and compute statistics
        for (Message msg : messages) {
            totalMessages++;

            // Check the type of message
            if (msg instanceof TextMessage) {
                textMessages++;
            } else if (msg instanceof PollMessage) {
                pollMessages++;
            }

            // Calculate size and update max size
            int size = msg.getSize();
            totalSize += size;
            if (size > maxSize) {
                maxSize = size;
            }
        }
        // Calculate average size
        double averageSize = (double)totalSize / totalMessages;

        // Print results
        System.out.println("Total messages: " + totalMessages);
        System.out.println("Text messages: " + textMessages);
        System.out.println("Poll messages: " + pollMessages);
        System.out.println("Total size: " + totalSize);
        System.out.println("Average size: " + String.format("%.2f", averageSize));
        System.out.println("Max size: " + maxSize);
    }
}
