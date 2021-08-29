package com.icommerce.audit.service;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Bindable interface with one input channel.
 *
 * @author Dave Syer
 * @author Marius Bogoevici
 * @see org.springframework.cloud.stream.annotation.EnableBinding
 */
public interface ProductAuditChanelSink {

    /**
     * Input channel name.
     */
    String INPUT = "input-product";

    /**
     * @return input channel.
     */
    @Input(ProductAuditChanelSink.INPUT)
    SubscribableChannel input();

}