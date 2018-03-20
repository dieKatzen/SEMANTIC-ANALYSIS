package Lexer;

public class Token {
    String token;
    String lexeme;
    String position;
    String prodMatch;

    public String getProdMatch() {
        return prodMatch;
    }

    public Token(Token token) {
        this.token = token.getToken();
        this.lexeme = token.getLexeme();
        this.position = token.getPosition();
        this.prodMatch = token.getProdMatch();
    }
    public void setProdMatch(String prodMatch) {
        this.prodMatch = prodMatch;
    }

    public Token(String token, String lexeme, String position, String prodMatch) {
        this.token = token;
        this.lexeme = lexeme;
        this.position = position;
        this.prodMatch = prodMatch;
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Token(String token, String lexeme, String position) {
        this.token = token;
        this.lexeme = lexeme;
        this.position = position;
    }
}
