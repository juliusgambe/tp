package com.homeboss.testutil;

import com.homeboss.logic.commands.delivery.DeliveryEditCommand.DeliveryEditDescriptor;
import com.homeboss.logic.parser.CliSyntax;

/**
 * A utility class for a Delivery.
 */
public class DeliveryUtil {

    /**
     * Returns the part of command string for the given {@code DeliveryEditDescriptor}'s details.
     */
    public static String getEditDeliveryDescriptorDetails(DeliveryEditDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getDeliveryName().ifPresent(name -> sb.append(CliSyntax.PREFIX_NAME).append(name).append(" "));
        descriptor.getCustomerId().ifPresent(customerId -> sb.append(CliSyntax.PREFIX_CUSTOMER_ID).append(customerId)
                .append(" "));
        descriptor.getDeliveryDate().ifPresent(deliveryDate -> sb.append(CliSyntax.PREFIX_DATE).append(deliveryDate)
                .append(" "));
        descriptor.getNote().ifPresent(note -> sb.append(CliSyntax.PREFIX_NOTE).append(note).append(" "));
        descriptor.getStatus().ifPresent(status -> sb.append(CliSyntax.PREFIX_STATUS).append(status).append(" "));

        return sb.toString();
    }
}
