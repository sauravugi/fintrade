package com.fintrade.models;

import com.fintrade.core.models.Address;
import com.fintrade.core.models.Contact;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType type;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "email", column = @Column(name = "user_contact_email")),
            @AttributeOverride(name = "phone", column = @Column(name = "user_contact_phone")),
            @AttributeOverride(name = "fax", column = @Column(name = "user_contact_fax"))
    })
    private Contact contact;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "user_address_street")),
            @AttributeOverride(name = "city", column = @Column(name = "user_address_city")),
            @AttributeOverride(name = "state", column = @Column(name = "user_address_state")),
            @AttributeOverride(name = "country", column = @Column(name = "user_address_country")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "user_address_zip_code"))
    })
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", nullable = false)
    private Organization organization;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        if (userId == null) userId = com.fintrade.core.utils.IdGenerator.generateRawId("U");
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
