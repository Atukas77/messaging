public class PollMessage extends Message {
    private String[] options;

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        if (options == null || options.length == 0) {
            throw new IllegalArgumentException("Options cannot be null or empty");
        }
        this.options = options;
    }

    @Override
    public int getSize() {
        int size = 0;
        for (String option : options) {
            size += option.length();
        }
        return size;
    }

    public int numberOfChoices() {
        return options.length;
    }

    public String getChoice(int number) {
        if (number < 0 || number >= options.length) {
            throw new IllegalArgumentException("Invalid choice index");
        }
        return options[number];
    }
}

