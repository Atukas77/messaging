public class TextMessage extends Message {
    private String text;

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int getSize() {
        return text.length();
    }
}