package api_models.post;

import api_models.Data;

import java.util.Objects;

public class PostNewEmployeeResponseModel {
    public String status;
    public Data data;
    public String message;

    public PostNewEmployeeResponseModel() {
        super();
    }

    public PostNewEmployeeResponseModel(String status, Data data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostNewEmployeeResponseModel that = (PostNewEmployeeResponseModel) o;
        return Objects.equals(status, that.status) && Objects.equals(data, that.data) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, data, message);
    }

    @Override
    public String toString() {
        return "{\"status\":\"" + status + "\",\"data\":\"" + data + "\",\"message\":\"" + message + "\"}";
    }
}