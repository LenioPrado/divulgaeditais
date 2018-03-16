package mobile.divulga.editais.ifsuldeminas.edu.br.other;

public enum RequestMethods {
    GET("GET"),
    POST("POST");

    private final String text;

    RequestMethods(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
