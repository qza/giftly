package org.koko.resource.store.s3;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class S3RestAPIResponse {

    enum Status {
        OK, FAILED
    }

    private Status status;
    private String message;

    public static S3RestAPIResponse error(String message) {
        return new S3RestAPIResponse(Status.FAILED, message);
    }

    public static S3RestAPIResponse ok(String message) {
        return new S3RestAPIResponse(Status.OK, message);
    }

    public S3RestAPIResponse(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
