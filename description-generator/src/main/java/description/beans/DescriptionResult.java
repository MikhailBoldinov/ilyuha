package description.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mikhail Boldinov
 */
public class DescriptionResult {
    private Map<String, String> resultMap = new HashMap<>();

    public Map<String, String> getResultMap() {
        return resultMap;
    }

    public void putResult(String code, String result) {
        resultMap.put(code, result);
    }
}
