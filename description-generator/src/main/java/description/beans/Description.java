package description.beans;

import core.service.IProcessable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mikhail Boldinov
 */
public class Description implements IProcessable {
    private String descriptionText;
    private List<String> codes = new ArrayList<>();

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public List<String> getCodes() {
        return codes;
    }

    public void addCode(String code) {
        codes.add(code);
    }
}
