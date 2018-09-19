package uml;

import java.io.*;

    class Bank{
        private String name;
        public Bank(String name){
            this.name = name;
        }

        public String getBankName(){
            return this.name;
        }
    }

    class Employee {
        private String name;
        public Employee(String name){
            this.name = name;
        }

        public String getEmployeeName(){
            return this.name;
        }
    }

    public class Association{
        public static void main (String[] args){
            Bank bank = new Bank("Axis");
            Employee emp = new Employee("Neha");
            System.out.println(emp.getEmployeeName() + " is employee of " + bank.getBankName());
        }
    }