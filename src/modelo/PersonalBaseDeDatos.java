package modelo;


import java.util.*;

public class PersonalBaseDeDatos {
    private static PersonalBaseDeDatos instancia;
    private Map<String, Employee> personalAlmacenamiento = new HashMap<>();
    private PersonalBaseDeDatos(){
        cargarEmpleadosPorDefector();
    }

    public static PersonalBaseDeDatos getInstancia(){
        if(instancia == null){//optimization
            instancia = new PersonalBaseDeDatos();
            Employee director = new Employee(
                    "1117554311",
                    "Emerson Tavera",      // name
                    "123",                 //Password
                    "123 Main St",         // address
                    "Metropolis",          // locality
                    "Jane Doe",            // peopleLivingWith
                    "Healthy",             // currentHealthStatus
                    false,                 // hadCovid
                    "N/A",                 // covidImpactLevel
                    false,                 // hasComorbidities
                    false,                 // familyWithComorbidities
                    "Feeling good",        // dailyStatus
                    false,                 // contactWithCovidPersons
                    36.6,                  // dailyTemperature
                    "Director"             // role
            );

            instancia.guardarActualizar(director);
            return instancia;
        }
        return instancia;
    }
    public Employee buscarPorId(String id){
        return personalAlmacenamiento.get(id);
    }

    public void guardarActualizar(Employee employeeToSave){
        Collection<Employee> storedPersonal = personalAlmacenamiento.values();
        boolean flag = false;
        for (Employee unEmpleado : storedPersonal) {
            if (employeeToSave.getId().equals(unEmpleado.getId())) {
                flag = true;
            }
        }
        if (flag) {
            personalAlmacenamiento.replace(employeeToSave.getId(), employeeToSave);
        } else {
            personalAlmacenamiento.put(employeeToSave.getId(), employeeToSave);
        }

    }

    public boolean eliminarPersonalPorId(String id){
        if(id.equals("1117554311")){
            //No se puede eliminar el perfil del director, ni siquiera el mismo
            return false;
        }
        Employee p = personalAlmacenamiento.remove(id);
        return p != null;
    }

    public Collection<Employee> getPersonal( ){
        return personalAlmacenamiento.values();
    }

    private void cargarEmpleadosPorDefector( ){
        List<Employee> listadeempleados = new ArrayList<>();

        // Creating 10 example employees
        listadeempleados.add(new Employee("1", "John Doe", "password1", "Address 1", "Locality 1", "None", "Good", false, "", false, false, "Normal", false, 36.5, "Employee"));
        listadeempleados.add(new Employee("2", "Jane Smith", "password2", "Address 2", "Locality 2", "None", "Good", false, "", false, false, "Normal", false, 36.7, "Employee"));
        listadeempleados.add(new Employee("3", "Alice Johnson", "password3", "Address 3", "Locality 3", "None", "Good", false, "", false, false, "Normal", false, 36.8, "Employee"));
        listadeempleados.add(new Employee("4", "Bob Brown", "password4", "Address 4", "Locality 4", "None", "Good", false, "", false, false, "Normal", false, 36.4, "Employee"));
        listadeempleados.add(new Employee("5", "Chris Lee", "password5", "Address 5", "Locality 5", "None", "Good", false, "", false, false, "Normal", false, 36.6, "Employee"));
        listadeempleados.add(new Employee("6", "Emma Davis", "password6", "Address 6", "Locality 6", "None", "Good", false, "", false, false, "Normal", false, 36.9, "Employee"));
        listadeempleados.add(new Employee("7", "Michael Wilson", "password7", "Address 7", "Locality 7", "None", "Good", false, "", false, false, "Normal", false, 36.3, "Employee"));
        listadeempleados.add(new Employee("8", "Olivia Garcia", "password8", "Address 8", "Locality 8", "None", "Good", false, "", false, false, "Normal", false, 36.2, "Employee"));
        listadeempleados.add(new Employee("9", "William Martinez", "password9", "Address 9", "Locality 9", "None", "Good", false, "", false, false, "Normal", false, 36.1, "Employee"));
        listadeempleados.add(new Employee("10", "Sophia Rodriguez", "password10", "Address 10", "Locality 10", "None", "Good", false, "", false, false, "Normal", false, 36.0, "Employee"));

        for (Employee employee : listadeempleados) {
            personalAlmacenamiento.put(employee.getId(), employee);
        }
    }

}