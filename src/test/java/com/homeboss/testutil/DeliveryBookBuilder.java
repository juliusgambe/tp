package com.homeboss.testutil;

import com.homeboss.model.DeliveryBook;
import com.homeboss.model.delivery.Delivery;

/**
 * A utility class to help with building Deliverybook objects.
 * Example usage: <br>
 * {@code DeliveryBook db = new DeliveryBookBuilder().withDelivery("").build();}
 */
public class DeliveryBookBuilder {

    private DeliveryBook deliveryBook;

    public DeliveryBookBuilder() {
        deliveryBook = new DeliveryBook();
    }

    public DeliveryBookBuilder(DeliveryBook deliveryBook) {
        this.deliveryBook = deliveryBook;
    }

    /**
     * Adds a new {@code Delivery} to the {@code DeliveryBook} that we are building.
     */
    public DeliveryBookBuilder withDelivery(Delivery delivery) {
        deliveryBook.addDelivery(delivery);
        return this;
    }

    public DeliveryBook build() {
        return deliveryBook;
    }
}
