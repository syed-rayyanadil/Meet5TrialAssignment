package org.meet5.meet5trialassignment;
import org.meet5.meet5trialassignment.config.TableCreation;
import org.meet5.meet5trialassignment.models.ProfileVisits;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;



@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Meet5TrialAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(Meet5TrialAssignmentApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner (ApplicationContext ctx) {
        return args -> {
            TableCreation tableCreation = ctx.getBean(TableCreation.class);
            tableCreation.createTables();
            tableCreation.generateDummyData(500);
        };
    }
}
