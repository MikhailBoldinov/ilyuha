package description.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mikhail Boldinov
 */
public class MatchResult {
    private List<SynonymList> synonymLists = new ArrayList<>();

    public void addSynonymList(String synonyms) {
        synonymLists.add(new SynonymList(synonyms));
    }

    public SynonymList getSynonymList(int index) {
        return synonymLists.get(index);
    }
}
