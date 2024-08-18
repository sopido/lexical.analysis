package Package;

import java.util.ArrayList;

public class Main {

    public ArrayList<token> Symbol_Table = new ArrayList<token>();

    public static void main(String[] args) {
        Lexycalanalysis la = new Lexycalanalysis("");
        token token;
        while (true) {
            token = la.nextToken();
            System.out.println("< "+ token.getName() + " ,  "+ token.getType() + " >");
            if (token.getName().equals("$"))
                break;
        }
    }
}