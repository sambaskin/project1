import java.lang.reflect.Array;
import java.util.ArrayList;
import static java.lang.System.*;
import java.util.Scanner;
import java.io.File;
import java.lang.Exception;
import java.io.PrintWriter;
import java.util.HashMap;

public class TextRepeat {



    public static void main(String[] args) throws Exception {
        boolean avgCheck = false;

        //File f = new File("./input");
        //String[] books = f.list();
        //URI
        //Add number to arrayList
        //
        ArrayList<ArrayList<Integer>>  data = new ArrayList<ArrayList<Integer>>();
        //ArrayList<String> books = new ArrayList<>(asList("mobydick", "frankenstein", "pride", "beowulf", "henri", "homer", "huck", "meta", "sherlock", "sugar", "tom", "dracula", "ball", "bey", "but", "fables", "fan", "fland", "good", "gret", "harv","hes", "hum", "merry", "para", "pio", "prop", "scar", "sioux", "son", "soul", "troll"));
        //Arrays.asList(
        if(avgCheck) {
        String[] books = {"mobydick", "frankenstein", "pride", "beowulf", "henri", "homer", "huck", "meta", "sherlock", "sugar", "tom", "dracula", "ball", "bey", "but", "fables", "fan", "fland", "good", "gret", "harv","hes", "hum", "merry", "para", "pio", "prop", "scar", "sioux", "son", "soul", "troll", "361", "362", "363", "364", "365", "366", "367", "368", "369", "370", "380", "381"};

        File outputCSV = new File( "./output/avg/"+"Avg" + ".uniquewords" + ".csv");
        PrintWriter csvTyper = new PrintWriter(outputCSV);
        for(String title : books) {
            System.out.println(title);
            data.add(repeat(title));

        }

            for (int i = 0; i < data.get(0).size(); i++) //ALL OF THE THINGS IN ONE THING
            {
                int avg = 0;
                for (int x = 0; x < data.size(); x++) {
                    avg += data.get(x).get(i);
                }
                csvTyper.print((avg / data.size()) + ",");  //Avg


            }

            csvTyper.close();

        }
        else
        {
            repeat("beowulf");
        }

    }
    public static ArrayList<Integer> repeat(String fileName) throws Exception
    {
        int origLength = 0;
        int prevCount = 0;
        int lengthMinus = 0;
        String extension = ".txt";
        File inputFile = new File("./input/" + fileName + extension);
        Scanner inputObject = new Scanner(inputFile);
        inputObject.useDelimiter(" ");
        ArrayList<String> origTokens = new ArrayList<>();
        ArrayList<String> newTokens = new ArrayList<>();
        String[] line;
        HashMap<String, String> words = new HashMap<>();
        HashMap<String, String> wordsOut = new HashMap<>();
        ArrayList<Integer> data = new ArrayList<Integer>();
        int wordCount = 0;
        File outputFile = new File( "./output/"+fileName + ".uniquewords" + ".txt");
        PrintWriter typer = new PrintWriter(outputFile);
        File outputCSV = new File( "./output/"+fileName + ".uniquewords" + ".csv");
        PrintWriter csvTyper = new PrintWriter(outputCSV);

        while (inputObject.hasNextLine()) {

            line = inputObject.nextLine().split(" ");
            for (String tok : line) {
                origTokens.add(tok);
                origLength++;
                for (int i = 1; i < tok.length()+1; i++) {
                    String charTok = tok.substring(i - 1, i);
                    if (charTok.equals(".") || charTok.equals(",") || charTok.equals("(") || charTok.equals(")") || charTok.equals("\"")
                            || charTok.equals("-") || charTok.equals("") || charTok.equals("!") || charTok.equals(";") || charTok.equals(":") || charTok.equals(" ") || charTok.equals("?")) {
                        //System.out.println(charTok);
                        tok = tok.substring(0, i - 1) + tok.substring(i);
                        i--;
                        lengthMinus++;

                    }
                }
                    newTokens.add(tok);
                if(wordsOut.put(tok.toUpperCase(), tok) == null)
                    {
                        tok = tok.toUpperCase();
                    }
                    typer.print(tok+" ");




            }
        }
        typer.close();
        for(int i = 0; i < newTokens.size(); i++) {
            if(words.put(newTokens.get(i).toUpperCase(), newTokens.get(i)) == null) {
                //words.put(tok.toUpperCase(), tok);
                //System.out.println(tok.toUpperCase());
                wordCount++;

            }
           // System.out.println(newTokens.size()/500);
            if(i%(newTokens.size()/200) == 0)
            {
                System.out.println(wordCount-prevCount);
                csvTyper.print((wordCount-prevCount)+",");
                data.add(wordCount-prevCount);
                prevCount = wordCount;
            }
        }

        //csvTyper.close();

        //OUTPUT WHERE THE FILE IS RECREATED BUT WITH UPPERCASE FOR EACH NEW WORD


        System.out.println(" ");
        System.out.println("Original Length: " + origLength);
        System.out.println("New Length: " + (origLength - lengthMinus));
        System.out.println(words.keySet());
        System.out.println(wordCount);

        inputObject.close();
        return data;
    }


}