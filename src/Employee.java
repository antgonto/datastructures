import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="Employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    private String code;

    private String name;

    private int salary;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int population) {
        this.salary = population;
    }


    @XmlRootElement(name = "employees")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Employees {
        @XmlElement(name = "employee")
        private List<Employee> employees = null;

        public List<Employee> getEmployees() {
            return employees;
        }

        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
        }
    }


    public static void main(String[] args) throws JAXBException {

        Employees employees = new Employees();


        employees.setEmployees(new ArrayList<Employee>());

        Employee emp1 = new Employee();
        emp1.setName("Lokesh");
        emp1.setCode("1");
        emp1.setSalary(10000);

        Employee emp2 = new Employee();
        emp2.setName("John");
        emp2.setCode("2");
        emp2.setSalary(10000);

        employees.getEmployees().add(emp1);
        employees.getEmployees().add(emp2);
        JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(employees, System.out);
    }
}