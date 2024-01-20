package it.sella.boot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import it.sella.boot.model.Employee;
import it.sella.boot.model.Laptop;

//Tesusko

@SpringBootApplication
public class SpringAutoWiringDiApplication implements ApplicationRunner, CommandLineRunner {

	public static void main(String[] args) {
		System.out.println("Inside Application !!!!!!!!!!!!!!!!!!");
		ConfigurableApplicationContext context = SpringApplication.run(SpringAutoWiringDiApplication.class, args);
		
		// This will only create Employee object
		// (if scope of laptop is prototype) Laptop object which is present inside this
		// will not be created
		Employee emp1 = new Employee();

		Employee emp2 = (Employee) context.getBean(Employee.class);
		System.out.println(emp2.isCreated());
		emp2.work();
		Employee emp3 = (Employee) context.getBean("emp");
		System.out.println(emp3.isCreated());

		
		//No object will be created..Since laptop is single ton
		Laptop lap = (Laptop) context.getBean("lap");
		lap.compile();

	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println(
				"Application Runner is an interface used to execute the code after the Spring Boot application started");

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("CommandLineRunner");
	}

}
