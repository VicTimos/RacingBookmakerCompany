package org.example.rbc.application;

import static org.example.rbc.domain.CommandType.BET;
import static org.example.rbc.domain.CommandType.BET_TYPE;
import static org.example.rbc.domain.CommandType.CABINET;
import static org.example.rbc.domain.CommandType.ERROR;
import static org.example.rbc.domain.CommandType.LOGIN;
import static org.example.rbc.domain.CommandType.LOGOUT;
import static org.example.rbc.domain.CommandType.RACING;
import static org.example.rbc.domain.SubCommandType.ADD;
import static org.example.rbc.domain.SubCommandType.NO;
import static org.example.rbc.domain.SubCommandType.ADD_PROCESS;
import static org.example.rbc.domain.SubCommandType.VIEW;

import java.util.HashMap;
import java.util.Map;
import org.example.rbc.command.AddCommand;
import org.example.rbc.command.ErrorCommand;
import org.example.rbc.command.CabinetCommand;
import org.example.rbc.command.Command;
import org.example.rbc.command.LoginCommand;
import org.example.rbc.command.LogoutCommand;
import org.example.rbc.command.AddProcessCommand;
import org.example.rbc.command.ViewCommand;
import org.example.rbc.domain.CommandFull;
import org.example.rbc.domain.SubCommandType;
import org.example.rbc.service.Dao;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class CommandSession {

    private ApplicationContext context;

    public CommandSession(ApplicationContext context) {
        this.context = context;
    }

    @Bean
    @SessionScope // there are 6 scopes in the spring. Singleton (all beans by default), prototype, request, session, application, websocket
    // the last 4 - are for web-applications and this bean is session scoped, it means it is one for the whole session , that starts when a controller called
    // from your browser and ends with the specified timout
    //
    // Some commands , such as ErrorCommand, LoginCommand that are in the constructor parameters
    // are static, some (ViewCommand, AddCommand, etc.) are common for concrete entities
    public Map<CommandFull, Command> commands(
        ErrorCommand errorCommand,
        LoginCommand loginCommand,
        LogoutCommand logoutCommand,
        CabinetCommand cabinetCommand
    ) {
        Map<CommandFull, Command> commands = new HashMap<>();

        commands.put(new CommandFull(ERROR, NO), errorCommand);

        CommandFull racingView = new CommandFull(RACING, VIEW);
        commands.put(
            racingView,
            getCommandFactory(racingView)
                .getBean(ViewCommand.class)
        );

        CommandFull racingAdd = new CommandFull(RACING, ADD);
        commands.put(
            racingAdd,
            getCommandFactory(racingAdd)
                .getBean(AddCommand.class)
        );

        CommandFull racingProcess = new CommandFull(RACING, ADD_PROCESS);
        commands.put(
            racingProcess,
            getCommandFactory(racingProcess)
                .getBean(AddProcessCommand.class)
        );

        CommandFull betView = new CommandFull(BET, VIEW);
        commands.put(
            betView,
            getCommandFactory(betView)
                .getBean(ViewCommand.class)
        );

        CommandFull betAdd = new CommandFull(BET, ADD);
        commands.put(
            betAdd,
            getCommandFactory(betAdd)
                .getBean(AddCommand.class)
        );

        CommandFull betTypeView = new CommandFull(BET_TYPE, VIEW);
        commands.put(
            betTypeView,
            getCommandFactory(betTypeView)
                .getBean(ViewCommand.class)
        );

        CommandFull betTypeAdd = new CommandFull(BET_TYPE, ADD);
        commands.put(
            betTypeAdd,
            getCommandFactory(betTypeAdd)
                .getBean(AddCommand.class)
        );

        commands.put(new CommandFull(LOGIN, NO), loginCommand);
        commands.put(new CommandFull(LOGOUT, NO), logoutCommand);
        commands.put(new CommandFull(CABINET, NO), cabinetCommand);

        return commands;
    }

    public DefaultListableBeanFactory getCommandFactory(CommandFull commandFull) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        Map<String, ?> daos = context.getBeansOfType(Dao.class);
        switch (commandFull.getSubCommandType()) {
            case VIEW: {
                beanDefinition.setBeanClass(ViewCommand.class);
                MutablePropertyValues propertyValues = new MutablePropertyValues();
                propertyValues.add("service", daos.get(commandFull.getCommandType().asKey() + "Service"));
                beanDefinition.setPropertyValues(propertyValues);
                beanDefinition.setFactoryBeanName("dao");
                factory.registerBeanDefinition(commandFull.getCommandType().asKey() + VIEW.asClassName(), beanDefinition);
                break;
            }
            case ADD: {
                beanDefinition.setBeanClass(AddCommand.class);
                MutablePropertyValues propertyValues = new MutablePropertyValues();
                propertyValues.add("service", daos.get(commandFull.getCommandType().asKey() + "Service"));
                beanDefinition.setPropertyValues(propertyValues);
                beanDefinition.setFactoryBeanName("dao");
                factory.registerBeanDefinition(commandFull.getCommandType().asKey() + ADD.asClassName(), beanDefinition);
                break;
            }
            case EDIT: {
                break;
            }
            case REMOVE: {
                break;
            }

            case ADD_PROCESS: {
                beanDefinition.setBeanClass(AddProcessCommand.class);
                MutablePropertyValues propertyValues = new MutablePropertyValues();
                propertyValues.add("service", daos.get(commandFull.getCommandType().asKey() + "Service"));
                beanDefinition.setPropertyValues(propertyValues);
                beanDefinition.setFactoryBeanName("dao");
                factory.registerBeanDefinition(commandFull.getCommandType().asKey() + ADD_PROCESS.asClassName(), beanDefinition);
                break;
            }
            default: break;
        }
        return factory;
    }
}
