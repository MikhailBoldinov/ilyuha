package description.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mikhail Boldinov
 */
public class DescriptionResult {
    private List<String> resultList = new ArrayList<>();

    public List<String> getResultList() {
        return resultList;
    }

    public void addResult(String result) {
        resultList.add(result);
    }
}
