package fun.adun.logopedia;

public class SavedSound {
    final private String id, text;
    final private boolean isCorrect;

    public SavedSound(String id, String text, boolean isCorrect) {
        this.id = id;
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
