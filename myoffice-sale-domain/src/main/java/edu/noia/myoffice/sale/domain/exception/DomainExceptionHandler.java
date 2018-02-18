package edu.noia.myoffice.sale.domain.exception;

import edu.noia.myoffice.common.domain.command.Command;
import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.common.domain.event.ProblemEventPayload;
import edu.noia.myoffice.common.util.exception.EntityNotFoundException;
import edu.noia.myoffice.common.util.exception.Problem;
import edu.noia.myoffice.sale.domain.command.cart.CartCommand;
import edu.noia.myoffice.sale.domain.event.cart.CartProblemEventPayload;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolationException;

@Slf4j
@RequiredArgsConstructor
public class DomainExceptionHandler {

    @NonNull
    EventPublisher eventPublisher;

    public void handle(Command command, Throwable cause) {
        LOG.warn(cause.getMessage());
        if (command instanceof CartCommand) {
            handleException((CartCommand) command, cause);
        } else {
            handleException(cause);
        }
    }

    private void handleException(Throwable cause) {
        if (cause.getCause() instanceof ConstraintViolationException) {
            eventPublisher.accept(new ProblemEventPayload(
                    Problem.from((ConstraintViolationException) (cause.getCause()))));
        }
    }

    private void handleException(CartCommand command, Throwable cause) {
        if (cause.getCause() instanceof EntityNotFoundException) {
            eventPublisher.accept(new CartProblemEventPayload(
                    Problem.from((EntityNotFoundException) (cause.getCause())), command.getCartId()));
        }
    }
}
