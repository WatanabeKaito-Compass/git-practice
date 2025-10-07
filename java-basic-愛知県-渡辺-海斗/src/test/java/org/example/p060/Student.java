package org.example.p060;

/**
 * 学生クラス
 * ラムダ・ストリームAPIの演習用
 */
public class Student {
    private String name;
    private int score;
    private int age;
    private String department;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
        this.age = 20; // デフォルト年齢
        this.department = "未定";
    }

    public Student(String name, int score, int age, String department) {
        this.name = name;
        this.score = score;
        this.age = age;
        this.department = department;
    }

    // ゲッター
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    // セッター
    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return String.format("Student{name='%s', score=%d, age=%d, department='%s'}", 
                           name, score, age, department);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return score == student.score && 
               age == student.age && 
               name.equals(student.name) && 
               department.equals(student.department);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, score, age, department);
    }
}
