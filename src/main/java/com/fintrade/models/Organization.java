package com.fintrade.models;

import com.fintrade.core.models.Address;
import com.fintrade.core.models.Contact;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "organization")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organization {

    @Id
    @Column(name = "org_id")
    private String orgId;

    @Column(name = "org_name")
    private String orgName;

    @Enumerated(EnumType.STRING)
    @Column(name = "org_type")
    private OrganizationType type;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "org_address_street")),
            @AttributeOverride(name = "city", column = @Column(name = "org_address_city")),
            @AttributeOverride(name = "state", column = @Column(name = "org_address_state")),
            @AttributeOverride(name = "country", column = @Column(name = "org_address_country")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "org_address_zip_code"))
    })
    private Address address;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "email", column = @Column(name = "org_contact_email")),
            @AttributeOverride(name = "phone", column = @Column(name = "org_contact_phone")),
            @AttributeOverride(name = "fax", column = @Column(name = "org_contact_fax"))
    })
    private Contact contact;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> users;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Client> clients;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Bank> banks;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        if (orgId == null) orgId = com.fintrade.core.utils.IdGenerator.generateRawId("O");
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
