package edu.noia.myoffice.sale.ui.handler;

import edu.noia.myoffice.common.domain.event.Event;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaleEventHandler {

    SaleEventConsumer bridge;

    @Getter
    Flux<Event> eventStream = Flux.create(sink -> bridge = new SaleEventConsumer() {
        @Override
        public void accept(Event event) {
            sink.next(event);
        }

        @Override
        public void complete() {
            sink.complete();
        }
    });

    private interface SaleEventConsumer extends Consumer<Event> {
        void complete();
    }

    public void on(Event event) {
        LOG.debug("{} received event: {}", getClass().getName(), event);
        if (bridge != null) {
            bridge.accept(event);
        }
    }

    public void terminate() {
        LOG.debug("{} is being terminated", getClass().getName());
        bridge.complete();
        LOG.debug("{} has been terminated", getClass().getName());
    }

}
