package ru.alexandrstal.statemachine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineModelConfigurer;
import org.springframework.statemachine.config.model.DefaultStateMachineComponentResolver;
import org.springframework.statemachine.config.model.StateMachineComponentResolver;
import org.springframework.statemachine.config.model.StateMachineModelFactory;
import org.springframework.statemachine.uml.UmlStateMachineModelFactory;

@Configuration
//@EnableStateMachine
@EnableStateMachineFactory
public class Config1 extends StateMachineConfigurerAdapter<String, String> {

    @Override
    public void configure(StateMachineModelConfigurer<String, String> model) throws Exception {
        model
                .withModel()
                .factory(modelFactory());
    }

    @Bean
    public StateMachineModelFactory<String, String> modelFactory() {
        UmlStateMachineModelFactory factory = new UmlStateMachineModelFactory("classpath:/simple-machine.uml");
        factory.setStateMachineComponentResolver(stateMachineComponentResolver());
   return  factory;
    }

    @Bean
    public StateMachineComponentResolver<String, String> stateMachineComponentResolver() {
        DefaultStateMachineComponentResolver<String, String> resolver = new DefaultStateMachineComponentResolver<>();
     //   resolver.registerAction("myAction", myAction());
      //  resolver.registerGuard("myGuard", myGuard());
        return resolver;
    }


}
