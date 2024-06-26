package com.shadyplace.registerjee.models;

import com.shadyplace.registerjee.models.enums.FamilyLinkLabel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;


@Entity
@Table(name = "family_link")
public class FamilyLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Family link label cannot be null.")
    private FamilyLinkLabel label;
    @Column(name = "discount_rate", columnDefinition = "float(2,2)",nullable = false)
    @PositiveOrZero(message = "Family link discount rate cannot be negative.")
    private double discountRate;

    public FamilyLink() {
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public FamilyLinkLabel getLabel() {
        return label;
    }

    public long getId() {
        return id;
    }

    public void setLabel(FamilyLinkLabel label) {
        this.label = label;
    }
}
