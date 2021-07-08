package api_models.put;

import java.util.Objects;

public class PutEmployeeRequestModel {
    public String name;
    public String salary;
    public String age;

    public PutEmployeeRequestModel() {
        super();
    }

    public PutEmployeeRequestModel(String name, String salary, String age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PutEmployeeRequestModel that = (PutEmployeeRequestModel) o;
        return Objects.equals(name, that.name) && Objects.equals(salary, that.salary) && Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary, age);
    }

    @Override
    public String toString() {
        return "{\"name\":\"" + name + "\",\"salary\":\"" + salary + "\",\"age\":\"" + age + "\"}";
    }
}
