package mobile.divulga.editais.ifsuldeminas.edu.br.services;

public class AsyncTaskResult<T> {

    private T result;
    private Exception error;

    public AsyncTaskResult(T result) {
        super();
        this.result = result;
    }

    public AsyncTaskResult(Exception error) {
        super();
        this.error = error;
    }

    public AsyncTaskResult(T result, Exception error) {
        super();
        this.error = error;
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    public Exception getError() {
        return error;
    }
}
