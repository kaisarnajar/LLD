class Student {
    private final String name;
    private final int age;
    private final int grades;
    private final String address;

    private Student(String name, int age, int grades, String address) {
        this.name = name;
        this.age = age;
        this.grades = grades;
        this.address = address;
    }

    public static class Builder {
        private String name = "no_name";
        private int age = 20;
        private int grade = 10;
        private String address = "Kampora Tangmarg Baramulla";

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setGrade(int grade) {
            this.grade = grade;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        Student build() {
            return new Student(name, age, grade, address);
        }
    }

    @Override
    public String toString() {
        return "Student(name=" + name +
                ", age=" + age +
                ", grade=" + grades +
                ", address=" + address + ")";
    }
}

public class StudentBuilderPattern {
    public static void main(String[] args) {
        Student student = new Student.Builder()
                .setName("Kaisar")
                .setAge(20)
                .setGrade(10)
                .setAddress("Kampora Tangmarg")
                .build();

        System.out.println(student.toString());
    }
}
