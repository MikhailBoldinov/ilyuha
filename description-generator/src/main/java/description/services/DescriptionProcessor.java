package description.services;

import description.beans.MatchResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mikhail Boldinov
 */
public class DescriptionProcessor {
    private static final String TEST_STR =
            "{Концевой выключатель|Выключатель|Концевик} %code% {производства фирмы|компании|корпорации} Omron {предназначен|служит} для защитных {механизмов|систем}. %code% - это {высокотехнологичное|технологичное} решение Вашх проблем в {сфере|области} автоматизации. Для {размещения заказа|того, чтобы разместить заказ} свяжитесь с нами любым удобным для Вас способом.";

    private static final String[] CODES = {
            "D4N-4C62",
            "D4N-412G",
            "D4N-4120"
    };

    public static void main(String[] args) {
        String regex = "(\\{[^\\{]*\\})";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(TEST_STR);

        MatchResult matchResult = new MatchResult();
        while (matcher.find()) {
            matchResult.addSynonymList(matcher.group(0));
        }

        for (String code : CODES) {
            matcher = pattern.matcher(TEST_STR.replaceAll("%code%", code));

            int idx = 0;
            StringBuffer sb = new StringBuffer();

            while (matcher.find()) {
                matcher = matcher.appendReplacement(sb, matchResult.getSynonymList(idx++).getRandomSynonym());
            }
            matcher.appendTail(sb);
            System.out.println(sb.toString());
        }
    }
}
