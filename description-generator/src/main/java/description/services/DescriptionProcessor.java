package description.services;

import core.service.IProcessor;
import core.service.impl.AbstractProcessor;
import description.beans.Description;
import description.beans.DescriptionResult;
import description.beans.SynonymList;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mikhail Boldinov
 */
public class DescriptionProcessor extends AbstractProcessor<Description> implements IProcessor<DescriptionResult> {

    private static final Pattern PATTERN = Pattern.compile("(\\{[^\\{]*\\})");

    public DescriptionProcessor(Description bean) {
        super(bean);
    }

    @Override
    public DescriptionResult process() {
        DescriptionResult descriptionResult = new DescriptionResult();
        String descriptionText = getProcessData().getDescriptionText();

        List<SynonymList> synonymLists = new ArrayList<>();
        Matcher matcher = PATTERN.matcher(descriptionText);
        while (matcher.find()) {
            SynonymList synonymList = new SynonymList(matcher.group(0));
            synonymLists.add(synonymList);
        }

        List<String> codes = getProcessData().getCodes();
        for (String code : codes) {
            matcher = PATTERN.matcher(descriptionText.replaceAll("%code%", code));

            int idx = 0;
            StringBuffer sb = new StringBuffer();

            while (matcher.find()) {
                SynonymList synonyms = synonymLists.get(idx++);
                matcher = matcher.appendReplacement(sb, synonyms.getRandomSynonym());
            }
            matcher.appendTail(sb);
            descriptionResult.putResult(code, sb.toString());
        }
        return descriptionResult;
    }
}
