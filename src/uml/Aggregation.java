package uml;

import java.io.*;
import java.util.*;

    // student class
    class Student{
        String name;
        int id ;
        String dept;

        public Student(String name, int id, String dept){
            this.name = name;
            this.id = id;
            this.dept = dept;
        }
    }

    class Department{

        String name;
        private List<Student> students;
        public Department(String name, List<Student> students){
            this.name = name;
            this.students = students;
        }

        public List<Student> getStudents(){
            return students;
        }
    }

    class Institute{

        String instituteName;
        private List<Department> departments;

        Institute(String instituteName, List<Department> departments){
            this.instituteName = instituteName;
            this.departments = departments;
        }

        public int getTotalStudentsInInstitute(){
            int noOfStudents = 0;
            List<Student> students;
            for(Department dept : departments){
                students = dept.getStudents();
                for(Student s : students){
                    noOfStudents++;
                }
            }
            return noOfStudents;
        }
    }

    // main method
    class Aggregation{
        public static void main (String[] args){
            Student s1 = new Student("Mia", 1, "CSE");
            Student s2 = new Student("Priya", 2, "CSE");
            Student s3 = new Student("John", 1, "EE");
            Student s4 = new Student("Rahul", 2, "EE");

            // making a List of
            // CSE Students.
            List <Student> cse_students = new ArrayList<Student>();
            cse_students.add(s1);
            cse_students.add(s2);

            // making a List of
            // EE Students
            List <Student> ee_students = new ArrayList<Student>();
            ee_students.add(s3);
            ee_students.add(s4);

            Department CSE = new Department("CSE", cse_students);
            Department EE = new Department("EE", ee_students);

            List <Department> departments = new ArrayList<Department>();
            departments.add(CSE);
            departments.add(EE);

            // creating an instance of Institute.
            Institute institute = new Institute("BITS", departments);

            System.out.print("Total students in institute: ");
            System.out.print(institute.getTotalStudentsInInstitute());
        }
    }