/**
 *  Controls player's interactions with objects
 */


public class ObjectItem implements Interactable {
    private String name;
    private boolean isSuspicious;
    private String collectibleItem; //

    public ObjectItem(String name, boolean isSuspicious, String collectibleItem) {
        this.name = name;
        this.isSuspicious = isSuspicious;
        this.collectibleItem = collectibleItem;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void interact(Player player) {
        if (isSuspicious) {
            System.out.println("You tamper with the " + name + ". People notice your behavior...");
            player.increaseSuspicion();
        } else {
            System.out.println("You interact with the " + name + " and find something interesting.");
        }

        if (collectibleItem != null) {
            player.collectItem(collectibleItem);
        }
    }
}
