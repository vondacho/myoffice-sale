package edu.noia.myoffice.sale.command.axon.command;

import edu.noia.myoffice.common.domain.command.Command;
import edu.noia.myoffice.common.domain.command.CommandPublisher;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;

@Component
public class AxonCommandPublisher implements CommandPublisher {

    @Autowired
    CommandGateway commandGateway;

    @Override
    public void accept(Command command) {
        commandGateway.send(asCommandMessage(command), LoggingCallback.INSTANCE);
    }
}
