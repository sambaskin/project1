import java.lang.reflect.Array;
import java.util.ArrayList;
import static java.lang.System.*;
import java.util.Scanner;
import java.io.File;
import java.lang.Exception;
import java.io.PrintWriter;
import java.util.HashMap;

public class TextCompression {


    public static void main(String[] args) throws Exception {
        compress("pride");
    }
    public static void compress(String fileName) throws Exception{
        int origLength = 0;
        int lengthMinus = 0;
        String extension = ".txt";
        File inputFile = new File("./input/" + fileName + extension);
        Scanner inputObject = new Scanner(inputFile);
        inputObject.useDelimiter(" ");
        ArrayList<String> origTokens = new ArrayList<>();
        ArrayList<String> newTokens = new ArrayList<>();
        String[] line;
        while (inputObject.hasNextLine()) {

            line = inputObject.nextLine().split(" ");
            for (String tok : line) {
                origTokens.add(tok);
                origLength+=tok.length();
                for (int i = 1; i < tok.length()+1; i++) {
                    String charTok = tok.substring(i - 1, i);
                    if (charTok.equals(".") || charTok.equals(",") || charTok.equals("(") || charTok.equals(")") || charTok.equals("\"")
                            || charTok.equals("-") || charTok.equals("") || charTok.equals("?")|| charTok.equals(";") || charTok.equals(":") || charTok.equals(" ")) {
                        System.out.println(charTok);
                        tok = tok.substring(0, i-1) + tok.substring(i);
                        i--;
                        lengthMinus++;
                    }
                }
                newTokens.add(tok + " ");

            }
        }

        File outputFile = new File( "./output/"+fileName + ".clean" + ".txt");
        PrintWriter typer = new PrintWriter(outputFile);

        for (String token : newTokens) {
            typer.print(token);
        }

        for (String token : newTokens) {
            //System.out.print(token);
        }
        for (String token : origTokens) {
            //System.out.print(token);
        }
        System.out.println(" ");
        System.out.println("Original Length: " + origLength);
        System.out.println("New Length: " + (origLength - lengthMinus));
        inputObject.close();
        typer.close();
        compression(outputFile);

    }


    public static void compression(File cleanFile) throws Exception
    {
        File inputFile = cleanFile;
        Scanner inputObject = new Scanner(inputFile);
        int comCount = 0;
        int charCount = 0;
        int ogCount = 0;
        String[] line;
        HashMap<String, String> map = new HashMap<>();
        ArrayList<String> compressedFile = new ArrayList<>();
        String outputfileName = "./output/"+cleanFile.getName().substring(0, cleanFile.getName().length()-3) + ".comp10" + ".txt";
        File outputFile = new File( outputfileName); //Change to File name
        PrintWriter typer = new PrintWriter(outputFile);
        while(inputObject.hasNextLine())
        {
           line = inputObject.nextLine().split(" ");
           for(String str : line)
           {
               if(str.length() > 5)
               {
                   String strKey = "*" + comCount;
                   comCount++;
                   map.put(strKey, str);
                   str = strKey;
               }
               charCount+= str.length();

               compressedFile.add(str);
                typer.print(str+" ");
           }
        }
        inputObject.close();

        typer.close();
        System.out.println("AAAAA");
        File inputFile2 = cleanFile;
        Scanner inputObject2 = new Scanner(inputFile2);
        ArrayList<String> list = new ArrayList<>();
        while(inputObject2.hasNextLine())
        {
            line = inputObject2.nextLine().split(" ");
            for(String str : line)
            {

                if (str.length() > 1 && str.substring(0, 1).equals("*"))
                {
                    str = map.get(str);
                }
                ogCount+= str.length();
                list.add(str);
            }

        }

        System.out.println("Compressed Char Count: " + charCount);
        System.out.println("Ratio: " + 1.0 * ogCount/charCount);
        inputObject2.close();
        typer.close();

    }

}