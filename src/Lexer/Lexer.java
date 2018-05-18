package Lexer;

import Main.TransitionTable;

import java.io.*;
import java.util.*;

public class Lexer {

    List<String> tokenList = new ArrayList<String>();
    List<Token> tokenListReturn = new ArrayList<Token>();
    List<String> errorList = new ArrayList<String>();
    Set <String> reservedWords = new HashSet <String>();

    public static Character [] singles = new Character[]{':',',','.',';','[',
            ']' , '{' ,'}' ,'(',')','+' , '-' ,'*' ,'/'};
    public static String [] doubles = new String[]{ "==", "<>","<=",">=",
            "::"};

    public static HashSet <Character>  singlesHash = new HashSet<Character>(Arrays.asList(singles));
    public static HashSet <String>  doublesHash = new HashSet<String>(Arrays.asList(doubles));

    TransitionTable transitionTable;
    String startState = "A";

    public Lexer() {
        transitionTable = new TransitionTable();
    }

    public List<Token> parse(String line) {
        if(line.isEmpty()) {
            System.out.println("Line is empty");
            return null;
        }

       for(int i = 0; i<line.length();i++){
            if(line.charAt(i) == ' '){
            continue;
            } else {
                i = nextToken(i, line);
            }
       }
        System.out.println("parseCompleted");
       return tokenListReturn;
    }

    public int nextToken (int startPos, String line){
        int currPos = startPos;
        String currToken = "";
        char currChar;
        char nextChar;
        String currState = startState;
        String nextState = "blank";
        String currStateStateType = "INIT_STATE_TYPE";
        String currStateTokenType = "INIT_TOKEN_TYPE";
        boolean LOn = false;

        do {

            nextChar = nextChar(currPos, line);
            currToken += Character.toString(nextChar);
            if (nextChar == '@'){

                currStateStateType = transitionTable.getStateType(nextState);
                currStateTokenType = transitionTable.getTokenType(nextState);

                if(currStateStateType.equals("State Type not found") || currStateTokenType.equals("Token Type not found")){
                    errorList.add("<" + line.substring(startPos,currPos)+ ", " + startPos + "-" + (currPos-1)+ ", " + currStateStateType + ", " + currStateTokenType + ">");
                }else {
                    tokenList.add("<" + line.substring(startPos, currPos) + ", " + startPos + "-" + (currPos - 1) + ", " + currStateStateType + ", " + currStateTokenType + ">");
                    tokenListReturn.add(new Token(currStateStateType,line.substring(startPos, currPos), startPos + "-" + (currPos - 1)));
                }
                return currPos;
            }


            nextState = transitionTable.findState(currState, isL(nextChar));

            currState = nextState;
            currPos++;
        }while(true);

    }

    public char nextChar (int pos, String line){
        char currChar;
        if (pos >= line.length() || line.charAt(pos) ==  ' '){
            return '@';
        }else{
            return currChar = line.charAt(pos);
        }

    }

    public String loadTestFile (String filename){
        String csvFile = filename;
        BufferedReader br = null;
        String line = "";
        String source = "";
        String cvsSplitBy = "`";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                source +=line + " ";
            }
            return source;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    return source;
    }

    public char isL(char c){
        if(Character.isLetter(c)){
            if (c != 'a' && c != 'c' && c != 'd' && c != 'e' && c != 'f' && c != 'g' && c != 'h' && c != 'i' && c != 'l' && c != 'm' && c != 'n' && c != 'o' && c != 'p' && c != 'r' && c != 's' && c != 't' && c != 'u') {
                return 'L';
            }
            return c;
        }
        if(Character.isDigit(c)){
            if (c != '0') {
                return 'N';
            }
            return c;
        }
        return c;
    }

    public static void printList(List<String[]> myList) {
        for (String [] arrlist: myList) {
            for (int i=0;i<arrlist.length;i++) {
                System.out.print(arrlist[i]+ "\t\t\t "+ i+" |");
            }
            System.out.println();
        }
    }

    public static void writeToFile(String filename, List<String> stringList) {
        try {
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            writer.println(Arrays.toString(stringList.toArray()));
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(String filename, List<String> stringList, Boolean vertical) {
        if(vertical){
            try {
                PrintWriter writer = new PrintWriter(filename, "UTF-8");
                for(Object each: stringList.toArray()){
                    writer.println(each.toString());
                }
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else {
            try {
                PrintWriter writer = new PrintWriter(filename, "UTF-8");
                writer.println(Arrays.toString(stringList.toArray()));
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }


    public static String addSpaces(String s){
        s = s + "  ";
        StringBuilder build = new StringBuilder();
        for (int i = 0; i< s.length()-1 ;i++) {
            if(s.charAt(i)=='.' && Character.isDigit(s.charAt(i-1)) && Character.isDigit(s.charAt(i+1)) ){
                build.append(s.substring(i,i+1));
            }else if(singlesHash.contains(s.charAt(i)) && !doublesHash.contains(s.substring(i,i+2))){
                build.append(" "+ s.substring(i,i+1)+ " ");
            }else if(doublesHash.contains(s.substring(i,i+2))){
                build.append(" "+ s.substring(i,i+2)+" ");
                i++;
            }else{
                build.append(s.substring(i,i+1));
            }
        }
        return build.toString();
    };

    public List<Token> returnTokenList( List<Token> returnList,String fileName){
        Lexer lexer = new Lexer ();
        lexer.transitionTable.loadTransitionTableCSV();
        String source = lexer.loadTestFile(fileName);
        source = lexer.addSpaces(source);
        System.out.println("Source: " + source);
        lexer.parse(source);

        returnList = addProd(lexer.tokenListReturn);
        writeToFile("tokenResults.txt",lexer.tokenList,true);
        writeToFile("errorResults.txt",lexer.errorList);
        return returnList;


    }

    public List<Token> addProd(List<Token>tokenList){
        for (Token token: tokenList) {
            if(token.getToken().equals("IDE")){
                token.setProdMatch("id");
            }else if (token.getToken().equals("INT")){
                token.setProdMatch("intNum");
            }else if (token.getToken().equals("FLOAT")){
                token.setProdMatch("floatNum");
            }
            else if (token.getToken().equals("RES")){
                String lex = token.getLexeme();
                switch (lex){
                    case "program": token.setProdMatch("program");
                                    break;
                    case "class": token.setProdMatch("class");
                        break;
                    case "if": token.setProdMatch("if");
                        break;
                    case "then": token.setProdMatch("then");
                        break;
                    case "else": token.setProdMatch("else");
                        break;
                    case "for": token.setProdMatch("for");
                        break;
                    case "get": token.setProdMatch("get");
                        break;
                    case "put": token.setProdMatch("put");
                        break;
                    case "return": token.setProdMatch("return");
                        break;
                    case "int": token.setProdMatch("int");
                        break;
                    case "float": token.setProdMatch("float");
                        break;
                    case "==": token.setProdMatch("eq");
                        break;
                    case "<>": token.setProdMatch("neq");
                        break;
                    case "<": token.setProdMatch("lt");
                        break;
                    case ">": token.setProdMatch("gt");
                        break;
                    case "<=": token.setProdMatch("leq");
                        break;
                    case ">=": token.setProdMatch("geq");
                        break;
                    case "+": token.setProdMatch("+");
                        break;
                    case "-": token.setProdMatch("-");
                        break;
                    case "*": token.setProdMatch("*");
                        break;
                    case "/": token.setProdMatch("/");
                        break;
                    case "not": token.setProdMatch("not");
                        break;
                    case "and": token.setProdMatch("and");
                        break;
                    case "or": token.setProdMatch("or");
                        break;
                    case "=": token.setProdMatch("=");
                        break;
                    case "::": token.setProdMatch("sr");
                        break;
                    case ":": token.setProdMatch(":");
                        break;
                    case ",": token.setProdMatch(",");
                        break;
                    case ".": token.setProdMatch(".");
                        break;
                    case ";": token.setProdMatch(";");
                        break;
                    case "[": token.setProdMatch("[");
                        break;
                    case "]": token.setProdMatch("]");
                        break;
                    case "{": token.setProdMatch("{");
                        break;
                    case "}": token.setProdMatch("}");
                        break;
                    case "(": token.setProdMatch("(");
                        break;
                    case ")": token.setProdMatch(")");
                        break;
                }
            }
        }
        return tokenList;
    }


    public static void main(String[] args) {

        Lexer lexer = new Lexer ();
        lexer.transitionTable.loadTransitionTableCSV();
        String source = lexer.loadTestFile("test.txt");
        source = lexer.addSpaces(source);
        System.out.println("Source: " + source);
        lexer.parse(source);

        writeToFile("tokenResults.txt",lexer.tokenList,true);
        writeToFile("errorResults.txt",lexer.errorList);

    }

}