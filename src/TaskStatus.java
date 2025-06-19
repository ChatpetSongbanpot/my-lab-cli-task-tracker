public enum TaskStatus {
    DONE("done"),
    TODO("todo"),
    IN_PROGRESS("in-progress");
    private final String value;

    TaskStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
