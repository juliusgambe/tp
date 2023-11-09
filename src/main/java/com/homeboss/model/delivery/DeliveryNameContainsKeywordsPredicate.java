package com.homeboss.model.delivery;

import java.util.List;
import java.util.function.Predicate;

import com.homeboss.commons.util.StringUtil;
import com.homeboss.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Delivery}'s {@code DeliveryName} matches any of the keywords given.
 */
public class DeliveryNameContainsKeywordsPredicate implements Predicate<Delivery> {
    private final List<String> keywords;

    /**
     * Creates a DeliveryNameContainsKeywordsPredicate.
     * @param keywords the list of keywords to search for.
     */
    public DeliveryNameContainsKeywordsPredicate(List<String> keywords) {
        // Create Defensive Copy to prevent modification
        this.keywords = List.copyOf(keywords);
    }

    @Override
    public boolean test(Delivery delivery) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(delivery.getName().deliveryName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeliveryNameContainsKeywordsPredicate)) {
            return false;
        }

        DeliveryNameContainsKeywordsPredicate otherNameContainsKeywordsPredicate =
            (DeliveryNameContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
