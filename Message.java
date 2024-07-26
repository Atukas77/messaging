public abstract class Message {
    private String recipient;
    
    public String getRecipient() {
        return recipient;
    }
    public void setRecipient(String name) {
        recipient = name;
    }

    public abstract int getSize();
}