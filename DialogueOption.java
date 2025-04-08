public class DialogueOption {
    private String prompt;
    private boolean isGood;
    private String response;

    public DialogueOption(String prompt, boolean isGood, String response) {
        this.prompt = prompt;
        this.isGood = isGood;
        this.response = response;
    }

    public String getPrompt() {
        return prompt;
    }

    public boolean isGood() {
        return isGood;
    }

    public String getResponse() {
        return response;
    }
}
