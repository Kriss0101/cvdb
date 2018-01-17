import datamodel.Person;
import datamodel.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import sun.tools.jar.CommandLine;

public class Bootstrap implements CommandLineRunner {

    @Autowired
    PersonRepository repo;

    @Override
    public void run(String... strings) throws Exception {

        storeDummyData();

    }

    private void storeDummyData() {

        Person p = new Person();


        repo.save(p);


    }
}
