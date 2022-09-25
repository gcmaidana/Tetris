package edu.uwplatt.team05.utils;

/**
 * Class that helps convert enumerations to strings
 * @author: doug selent (taken from OOAD file we were given)
 */
public class Utils {
    public static String convertEnumString(String rawName)
    {
        // Assumes rawName is not null, will throw NPE otherwise
        // Choosing not to explicitly handle this since it would be programmer error anyway

        String lowerCaseName = rawName.toLowerCase();
        String[] wordArray = lowerCaseName.split("_");
        StringBuilder printNameBuilder = new StringBuilder();

        for(int i=0; i<wordArray.length; i++)
        {
            String word = wordArray[i];

            if(!word.isEmpty())
            {
                String firstLetter = word.substring(0, 1);
                String remainingWord = word.substring(1);

                printNameBuilder.append(firstLetter.toUpperCase() + remainingWord);

                if(i < wordArray.length-1)
                {
                    printNameBuilder.append(" ");
                }
            }
        }

        return printNameBuilder.toString();
    }
}
