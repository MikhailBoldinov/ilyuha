package description.beans;

import core.service.IWriteable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mikhail Boldinov
 */
public class DescriptionResult implements IWriteable {
    private Map<String, String> resultMap = new HashMap<>();

    public Map<String, String> getResultMap() {
        return resultMap;
    }

    public void putResult(String code, String result) {
        resultMap.put(code, result);
    }

    @Override
    public int getCount() {
        return resultMap.size();
    }
}
