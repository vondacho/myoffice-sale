package edu.noia.myoffice.sale.messaging.adapter.axon;

import edu.noia.myoffice.common.domain.command.Command;
import edu.noia.myoffice.common.domain.command.CommandPublisher;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;

import java.util.HashMap;
import java.util.Map;

import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;

@RequiredArgsConstructor
public class CommandPublisherAdapter implements CommandPublisher {

    @NonNull
    CommandGateway commandGateway;
    @NonNull
    CommandCallback saleCommandCallback;

    @Override
    public void accept(Command command) {
        Map<String, String> metadata = new HashMap<>();
        commandGateway.send(asCommandMessage(command).withMetaData(metadata), saleCommandCallback);
    }

    private Map<String, String> makeMetadata() {
        Map<String, String> metadata = new HashMap<>();
        return metadata;
    }
}
