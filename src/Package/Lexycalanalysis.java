package Package;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lexycalanalysis {
    private int currentpoint = -1;
    private int currentstate = 0;

    private String path;//masir code manbae
    private String code;//after read code ,code is here

    //four list {digit , space ,letter ,symbol}

    private char[] letters = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
            'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'W', 'X', 'Y', 'Z'

    };// letters = {a,b,c,...,z,A,B,...,Z}
    private final  char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};//  digits = {0,1,...9}
    private final char[] symbols = {'(', ')', '{', '}', ';', '+', '/', '=', ':', '.', '<', '>', '!', '"'}; // symbols

    private final char[] spaces = {'\n', '\t', ' '};//spaces ={ \n ,\t ,'  ' }

    private final String[] keywords = {"write", "read", "ifso", "selector", "loop", "until"};


    public token nextToken() {
        char ch;
        currentstate = 0;
        String toKen_name = "";
        token t;
        while (true) {
            switch (currentstate) {
                case 0:
                    ch = nextchar();
                    toKen_name = toKen_name + ch;
                    if (isInletters(ch))
                        currentstate = 1;
                    else if (ch == '+')
                        currentstate = 2;
                    else if (ch == '-')
                        currentstate = 3;
                    else if (isIndigits(ch))
                        currentstate = 4;
                    else if (ch == '(')
                        currentstate = 7;
                    else if (ch == ')')
                        currentstate = 8;
                    else if (ch == ';')
                        currentstate = 9;
                    else if (ch == '{')
                        currentstate = 10;
                    else if (ch == '}')
                        currentstate = 11;
                    else if (ch == '/')
                        currentstate = 12;
                    else if (ch == '"')
                        currentstate = 18;
                    else if (ch == '*')
                        currentstate = 20;
                    else if (ch == '=')
                        currentstate = 21;
                    else if (ch == '<')
                        currentstate = 23;
                    else if (ch == '>')
                        currentstate = 25;
                    else if (ch == '!')
                        currentstate = 27;
                    else if (ch == '$')
                        currentstate = 29;
                    else if (isInspaces(ch))
                        currentstate = 30;
                    else {
                         t = new token(toKen_name + "is not recognizing", 0, 0, token.Token_type.err, token.ID_type.none);
                        return t;
                    }


                    break;
                case 1:
                    if (isInletters(watchNextChar()) || isIndigits(watchNextChar())) {
                        ch = nextchar();
                        toKen_name = toKen_name + ch;
                        currentstate = 1;
                    } else {
                        if (isInkeywords(toKen_name)) {
                            if (toKen_name.equals("ashar")) {
                                 t = new token(toKen_name, 0, 0, token.Token_type.ashar, token.ID_type.none);
                                return t;
                            } else if (toKen_name.equals("sahih")) {
                                 t = new token(toKen_name, 0, 0, token.Token_type.sahih, token.ID_type.none);
                                return t;
                            } else if (toKen_name.equals("write")) {
                                 t = new token(toKen_name, 0, 0, token.Token_type.write, token.ID_type.none);
                                return t;
                            } else if (toKen_name.equals("read")) {
                                 t = new token(toKen_name, 0, 0, token.Token_type.read, token.ID_type.none);
                                return t;
                            } else if (toKen_name.equals("ifso")) {
                                 t = new token(toKen_name, 0, 0, token.Token_type.ifso, token.ID_type.none);
                                return t;
                            } else if (toKen_name.equals("selector")) {
                                 t = new token(toKen_name, 0, 0, token.Token_type.selector, token.ID_type.none);
                                return t;
                            } else if (toKen_name.equals("loop")) {
                                 t = new token(toKen_name, 0, 0, token.Token_type.loop, token.ID_type.none);
                                return t;
                            } else if (toKen_name.equals("until")) {
                                 t = new token(toKen_name, 0, 0, token.Token_type.until, token.ID_type.none);
                                return t;
                            }
                        } else {
                             t = new token(toKen_name, 0, 0, token.Token_type.id, token.ID_type.none);
                            return t;
                        }
                    }

                    break;
                case 2:
                    if (isIndigits(watchNextChar())) {
                        ch = nextchar();
                        toKen_name = toKen_name + ch;
                        currentstate = 4;
                    } else {
                         t = new token(toKen_name, 0, 0, token.Token_type.plus, token.ID_type.none);
                        return t;
                    }


                    break;
                case 3:
                    if (isIndigits(watchNextChar())) {
                        ch = nextchar();
                        toKen_name = toKen_name + ch;
                        currentstate = 4;
                    } else {
                         t = new token(toKen_name, 0, 0, token.Token_type.minus, token.ID_type.none);
                        return t;
                    }
                    break;
                case 4:
                    if (isIndigits(watchNextChar())) {
                        ch = nextchar();
                        currentstate = 4;
                    } else if (watchNextChar() == '.') {
                        ch = nextchar();
                        toKen_name = toKen_name + ch;
                        currentstate = 5;
                    } else {
                         t = new token(toKen_name, 0, 0, token.Token_type.intNumber, token.ID_type.none);
                        return t;
                    }
                    break;
                case 5:
                    if (isIndigits(watchNextChar())) {
                        ch = nextchar();
                        toKen_name = toKen_name + ch;
                        currentstate = 6;
                    } else {
                         t = new token(toKen_name + "is not defined in simple language", 0, 0, token.Token_type.err, token.ID_type.none);
                        return t;
                    }
                    break;
                case 6:
                    if (isIndigits(watchNextChar())) {
                        ch = nextchar();
                        toKen_name = toKen_name + ch;
                        currentstate = 6;
                    } else {
                         t = new token(toKen_name, 0, 0, token.Token_type.floatNumber, token.ID_type.none);
                        return t;
                    }
                    break;
                case 7:
                     t = new token(toKen_name, 0, 0, token.Token_type.leftParanters, token.ID_type.none);
                    return t;
                case 8:
                     t = new token(toKen_name, 0, 0, token.Token_type.rightParantese, token.ID_type.none);
                    return t;
                case 9:
                     t = new token(toKen_name, 0, 0, token.Token_type.semicolon, token.ID_type.none);
                    return t;
                case 10:
                     t = new token(toKen_name, 0, 0, token.Token_type.leftAccolade, token.ID_type.none);
                    return t;
                case 11:
                    t = new token(toKen_name, 0, 0, token.Token_type.rightAccolade, token.ID_type.none);
                    return t;
                case 12:
                    if (watchNextChar() == '/') {
                        ch = nextchar();
                        toKen_name = toKen_name + ch;
                        currentstate = 13;
                    } else if (watchNextChar() == '*') {
                        ch = nextchar();
                        toKen_name = toKen_name + ch;
                        currentstate = 15;
                    } else {
                        t = new token(toKen_name, 0, 0, token.Token_type.divide, token.ID_type.none);
                        return t;
                    }
                    break;
                case 13:
                    if (watchNextChar() == '\n') {
                        ch = nextchar();
                        currentstate = 14;
                    } else {
                        ch = nextchar();
                        toKen_name = toKen_name + ch;
                        currentstate = 13;
                    }
                    break;
                case 14:
                    t = new token(toKen_name, 0, 0, token.Token_type.lineComment, token.ID_type.none);
                    return t;
                case 15:
                    if (watchNextChar() == '*') {
                        ch = nextchar();
                        toKen_name = toKen_name + ch;
                        currentstate = 15;
                    }
                    break;
                case 16:
                    if (watchNextChar() == '/') {
                        ch = nextchar();
                        toKen_name = toKen_name + ch;
                        currentstate = 17;
                    } else if (watchNextChar() == '*') {
                        ch = nextchar();
                        toKen_name = toKen_name + ch;
                        currentstate = 16;
                    } else {
                        ch = nextchar();
                        toKen_name = toKen_name + ch;
                        currentstate = 15;
                    }
                    break;
                case 17:
                    t = new token(toKen_name, 0, 0, token.Token_type.multiComment, token.ID_type.none);
                    return t;
                case 18:
                    if (watchNextChar() == '"') {
                        ch = nextchar();
                        toKen_name = toKen_name + ch;
                        currentstate = 19;
                    } else {
                        ch = nextchar();
                        toKen_name = toKen_name + ch;
                        currentstate = 18;
                    }
                    break;
                case 19:
                    t = new token(toKen_name, 0, 0, token.Token_type.string, token.ID_type.none);
                    return t;
                case 20:
                    t = new token(toKen_name, 0, 0, token.Token_type.multiple, token.ID_type.none);
                    return t;
                case 21:
                    if (watchNextChar() == '=') {
                        ch = nextchar();
                        toKen_name = toKen_name + ch;
                        currentstate = 22;
                    } else {
                        t = new token(toKen_name, 0, 0, token.Token_type.equal, token.ID_type.none);
                        return t;
                    }
                    break;
                case 22:
                    t = new token(toKen_name, 0, 0, token.Token_type.equalTwice, token.ID_type.none);
                    return t;
                case 23:
                    if (watchNextChar() == '=') {
                        ch = nextchar();
                        toKen_name = toKen_name + ch;
                        currentstate = 14;
                    } else {
                        t = new token(toKen_name, 0, 0, token.Token_type.less, token.ID_type.none);
                        return t;
                    }
                    break;
                case 24:
                    t = new token(toKen_name, 0, 0, token.Token_type.lessEqual, token.ID_type.none);
                    return t;
                case 25:
                    if (watchNextChar() == '=') {
                        ch = nextchar();
                        toKen_name = toKen_name + ch;
                        currentstate = 26;
                    } else {
                        t = new token(toKen_name, 0, 0, token.Token_type.more, token.ID_type.none);
                        return t;
                    }
                    break;
                case 26:
                    t = new token(toKen_name, 0, 0, token.Token_type.moreEqual, token.ID_type.none);
                    return t;
                case 27:
                    if (watchNextChar() == '=') {
                        ch = nextchar();
                        toKen_name = toKen_name + ch;
                        currentstate = 28;
                    }
                    break;
                case 28:
                    t = new token(toKen_name, 0, 0, token.Token_type.notEqual, token.ID_type.none);
                    return t;
                case 29:
                    t = new token(toKen_name, 0, 0, token.Token_type.$, token.ID_type.none);
                    return t;
                case 30:
                    toKen_name = "";
                    currentstate = 0;
                    break;
            }
        }
    }

    //***********************************************************next char
    //read char one by one
    private char nextchar() {
        char ch;
        currentpoint++;
        if (currentpoint >= code.length())
            return '$'; // code tamam shode ast.
        ch = code.charAt(currentpoint);

        if (!(isIndigits(ch) || isInletters(ch) || isInspaces(ch) || isInsymbols(ch))) {
            System.err.println("character" + ch + "is not in alphabet");
            ch = ' ';//for continue
        }
        return ch;
    }

    //*************************************************************watch next char
    private char watchNextChar() {
        char ch;
        int watchNextCharPoint = currentpoint + 1;

        if (watchNextCharPoint >= code.length())
            return '$';

        ch = code.charAt(watchNextCharPoint);
        return ch;
    }

    //**************************************************************Alphabet
    //************************************************************letters
    private boolean isInletters(char ch) {
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == ch)
                return true;
        }
        return false;
    }

    //*******************************************************************Spaces
    private boolean isInspaces(char ch) {
        for (int i = 0; i < spaces.length; i++) {
            if (spaces[i] == ch)
                return true;
        }
        return false;
    }


    //***********************************************************************digits
    private boolean isIndigits(char ch) {
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == ch)
                return true;

        }
        return false;
    }

    //***********************************************************************symbols
    private boolean isInsymbols(char ch) {
        for (int i = 0; i < symbols.length; i++) {
            if (symbols[i] == ch)
                return true;

        }
        return false;
    }

    private boolean isInkeywords(String word) {
        for (int i = 0; i < keywords.length; i++) {
            if (word.equals(keywords[i]))
                return true;

        }
        return false;
    }


    //***************************************************************constructor
    public Lexycalanalysis(String path) {
        this.setpath(path);
    }

    //**********************************************************************setpath
    //for resd the input and save it in the code
    private void setpath(String path) {
        this.path = path;
        code = "";

        try {
            File source = new File(this.path);
            Scanner input = new Scanner(source);
            while (input.hasNextLine()) {
                code = code + "\n" + input.nextLine();
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.err.println("source is empty ");
//
        }// if this location it will be empty(null)
    }

}
