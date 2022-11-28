
/**
* 
* @author: Amir Armion 
* 
* @version: V.01
* 
*/
public class CaesarCipherTwo 
{
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int    mainKey1;
    private int    mainKey2;

    public CaesarCipherTwo(int key1, int key2)
    {
        mainKey1         = key1;
        mainKey2         = key2;
        alphabet         = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    }

    public String encrypt(String input)
    {
        StringBuilder output    = new StringBuilder("");

        for(int i = 0; i < input.length(); i++)
        {
            char currentChar = input.charAt(i);

            if(i % 2 == 0) // Even index
            {
                if(Character.isUpperCase(currentChar))
                {
                    int positionOfCurrentChar = alphabet.indexOf(currentChar);

                    if(positionOfCurrentChar != -1)
                    {
                        char cryptedChar = shiftedAlphabet1.charAt(positionOfCurrentChar);
                        output.append(cryptedChar);
                    }
                    else
                    {
                        output.append(currentChar);
                    }
                }
                else if(Character.isLowerCase(currentChar))
                {
                    int positionOfCurrentChar = alphabet.indexOf(Character.toUpperCase(currentChar));

                    if(positionOfCurrentChar != -1)
                    {
                        char cryptedChar = Character.toLowerCase(shiftedAlphabet1.charAt(positionOfCurrentChar));
                        output.append(cryptedChar);
                    }
                    else
                    {
                        output.append(currentChar);
                    }
                }
                else
                {
                    output.append(currentChar);
                }
            }
            else // Odd index
            {
                if(Character.isUpperCase(currentChar))
                {
                    int positionOfCurrentChar = alphabet.indexOf(currentChar);

                    if(positionOfCurrentChar != -1)
                    {
                        char cryptedChar = shiftedAlphabet2.charAt(positionOfCurrentChar);
                        output.append(cryptedChar);
                    }
                    else
                    {
                        output.append(currentChar);
                    }
                }
                else if(Character.isLowerCase(currentChar))
                {
                    int positionOfCurrentChar = alphabet.indexOf(Character.toUpperCase(currentChar));

                    if(positionOfCurrentChar != -1)
                    {
                        char cryptedChar = Character.toLowerCase(shiftedAlphabet2.charAt(positionOfCurrentChar));
                        output.append(cryptedChar);
                    }
                    else
                    {
                        output.append(currentChar);
                    }
                }
                else
                {
                    output.append(currentChar);
                }
            }
        }

        return output.toString();
    }

    public String decrypt(String input)
    {
        CaesarCipherTwo cct = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        
        return cct.encrypt(input);
    }
}
