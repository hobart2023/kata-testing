enum Operation {
    SUM("+"), DIFF("-"), DIV("/"), MULTI("*");

    private final String value;

    Operation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
