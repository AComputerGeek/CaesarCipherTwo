import edu.duke.*;

/**
* 
* @author: Amir Armion 
* 
* @version: V.01
* 
*/
public class TestCaesarCipherTwo 
{

    public String halfOfString(String message, int start)
    {
        StringBuilder sb = new StringBuilder();

        for(int i = start; i < message.length(); i += 2)
        {
            char ch = message.charAt(i);
            sb.append(ch);
        }

        return sb.toString();
    }

    public int[] countLetters(String encrypted)
    {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[]  counts   = new int[26];

        for(int i = 0; i < encrypted.length(); i++)
        {
            char ch              = Character.toUpperCase(encrypted.charAt(i));
            int  indexOfThisChar = alphabet.indexOf(ch);

            if(indexOfThisChar != -1)
            {
                counts[indexOfThisChar]++;
            }
        }

        return counts;
    }

    public int indexOfMax(int[] counts)
    {
        int maxIndex = 0;

        for(int i = 0; i < counts.length; i++)
        {
            if(counts[i] > counts[maxIndex])
            {
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    public void simpleTests()
    {
        FileResource fr    = new FileResource();
        String       input = fr.asString();

        CaesarCipherTwo cct                 = new CaesarCipherTwo(17, 3);
        String          encryptedWithTwoKeys = cct.encrypt(input);
        System.out.println("Encrypted Message is: " + encryptedWithTwoKeys);

        String decryptedWithTwoKeys = cct.decrypt(encryptedWithTwoKeys);
        System.out.println("Decrypted Message is: " + decryptedWithTwoKeys);
        
        String bbc = breakCaesarCipher(decryptedWithTwoKeys);
        System.out.println("BBC with two keys: " + bbc);
    }

    public String breakCaesarCipher(String input)
    {
        String part1 = halfOfString(input, 0);
        String part2 = halfOfString(input, 1);

        int[] frequent1 = countLetters(part1);
        int[] frequent2 = countLetters(part2);

        int maxIndex1 = indexOfMax(frequent1);
        int maxIndex2 = indexOfMax(frequent2);

        int dKey1 = maxIndex1 - 4;
        int dKey2 = maxIndex2 - 4;

        if(maxIndex1 < 4)
        {
            dKey1 = 26 - (4 - maxIndex1);
        }

        if(maxIndex2 < 4)
        {
            dKey2 = 26 - (4 - maxIndex2);
        }

        CaesarCipherTwo cct = new CaesarCipherTwo(dKey1, dKey2);
        
        return cct.decrypt(input);
    }
}
