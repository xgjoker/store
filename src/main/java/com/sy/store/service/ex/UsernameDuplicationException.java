package com.sy.store.service.ex;

public class UsernameDuplicationException extends ServiceException{
    public UsernameDuplicationException() {
        super();
    }

    public UsernameDuplicationException(String message) {
        super(message);
    }

    public UsernameDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDuplicationException(Throwable cause) {
        super(cause);
    }

    protected UsernameDuplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
