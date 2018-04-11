package ru.alexandrstal.statemachine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StateMachineUMLConfig.class)
public class StateMachineIntegrationTest {

    private StateMachine<String, String> stateMachine;

    @Autowired
    private StateMachineFactory<String, String> statemachinefactory;

    @Before
    public void setUp() {

        stateMachine = statemachinefactory.getStateMachine();
        stateMachine.start();
    }

    @Test
    public void aa() throws Exception {
        StateMachine<String, String> stateMachine = statemachinefactory.getStateMachine();
        stateMachine.start();

        assertEquals("FE", stateMachine.getState().getId());

        Collection<String> eventsForState = stateMachine.getTransitions().stream().
                filter(p -> p.getSource().getId().equals(stateMachine.getState().getId())).map(p -> p.getTrigger().getEvent()).
                collect(Collectors.toCollection(ArrayList::new));
        for (String transition : eventsForState) {
            System.out.print("transition " + transition);
        }


        stateMachine.sendEvent("FINISH_FE_SIGNAL");

        assertEquals("FINISH_FE", stateMachine.getState().getId());

        eventsForState = stateMachine.getTransitions().stream().
                filter(p -> p.getSource().getId().equals(stateMachine.getState().getId())).map(p -> p.getTrigger().getEvent()).
                collect(Collectors.toCollection(ArrayList::new));

        for (String transition : eventsForState) {
            System.out.print("transition " + transition);
        }
    }

    @After
    public void tearDown() {
        stateMachine.stop();
    }
}
