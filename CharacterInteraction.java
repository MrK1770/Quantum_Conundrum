import java.util.*;

public class CharacterInteraction implements Interactable {
    private String name;  // e.g., "Waitress"
    private String introMessage;
    private List<DialogueOption> dialogueOptions;
    private Set<String> chosenPrompts;  // tracks repeated prompts

    public CharacterInteraction(String name, String introMessage, List<DialogueOption> dialogueOptions) {
        this.name = name;
        this.introMessage = introMessage;
        this.dialogueOptions = dialogueOptions;
        this.chosenPrompts = new HashSet<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void interact(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(name + " says: " + introMessage);

        boolean keepTalking = true;
        while (keepTalking) {
            System.out.println("\nWhat would you like to say?");
            for (int i = 0; i < dialogueOptions.size(); i++) {
                System.out.println((i + 1) + ". " + dialogueOptions.get(i).getPrompt());
            }
            System.out.println((dialogueOptions.size() + 1) + ". Leave conversation");

            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
                continue;
            }

            if (choice == dialogueOptions.size() + 1) {
                System.out.println("You politely excuse yourself.");
                keepTalking = false;
                continue;
            }

            if (choice < 1 || choice > dialogueOptions.size()) {
                System.out.println("That's not a valid choice.");
                continue;
            }

            DialogueOption selected = dialogueOptions.get(choice - 1);
            String prompt = selected.getPrompt();

            if (chosenPrompts.contains(prompt)) {
                System.out.println(name + " looks at you oddly. \"Didn’t you just ask that?\"");
                player.increaseSuspicion();
            } else {
                chosenPrompts.add(prompt);
                System.out.println(name + " responds: \"" + selected.getResponse() + "\"");
                if (!selected.isGood()) {
                    player.increaseSuspicion();
                }
            }
        }
    }
}