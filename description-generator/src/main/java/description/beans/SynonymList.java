package description.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Mikhail Boldinov
 */
public class SynonymList {
    private static final String START = "{";
    private static final String END = "}";
    private static final String SPLITTER = "\\|";

    private List<String> synonyms = new ArrayList<>();
    private int size;
    private Random random = new Random();

    public SynonymList(String synonyms) {
        buildSynonymList(synonyms);
    }

    private void buildSynonymList(String synonyms) {
        if (synonyms == null || synonyms.isEmpty()) {
            throw new IllegalArgumentException("Synonyms string is empty.");
        }
        if (!synonyms.startsWith(START) && !synonyms.endsWith(END)) {
            throw new IllegalArgumentException(String.format("Invalid synonyms string format: '%s'.", synonyms));
        }
        synonyms = synonyms.substring(1, synonyms.length() - 1);

        this.synonyms.addAll(Arrays.asList(synonyms.split(SPLITTER)));
        size = this.synonyms.size();
    }

    public String getRandomSynonym() {
        int idx = random.nextInt(size);
        return synonyms.get(idx);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String synonym : synonyms) {
            sb.append(synonym);
            sb.append(SPLITTER);
        }
        return sb.toString();
    }
}
