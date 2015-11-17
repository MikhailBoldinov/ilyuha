package description.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mikhail Boldinov
 */
public class Description {
    private String text;
    private List<String> codes = new ArrayList<>();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getCodes() {
        return codes;
    }

    public void addCode(String code) {
        codes.add(code);
    }
}
