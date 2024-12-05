package com.ennea.enneaservices.model;

import com.ennea.enneaservices.constants.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String businessName;

    private String displayName;

    private String contactPerson;

    private String promoCode;

    private String profileImage;

    @Column(unique = true)
    private String username;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @NotNull(message = "Email required")
    private String emailId;

    @NotNull(message = "Contact number required")
    @Column(unique = true)
    private Long phoneNumber;

    private Long contactNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    @Column(unique = true)
    private String drugLicense;

    @ManyToOne
    @JoinTable(name = "user_role_configuration",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_configuration_id", referencedColumnName = "id"))
    private RoleConfiguration roleConfiguration;

    @ManyToOne
    private UserStatus userStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    private Settings settings;

    @OneToOne(cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    private UserLocation location;

    private LocalDateTime modificationDateTime;

    private LocalDateTime creationDateTime;

    private Long uuid;

    private int rewardAffinity;

    private String drugLicenseFileName;

    private LocalDate drugLicenseExpiry;

    private String gstin;

    private String code;

    private String blockComment;

    private String additionalInfo;

    private boolean verified = true;

    private transient String profileImageUrl;

    private transient String drugLicenseUrl;

    @OneToOne(cascade = CascadeType.ALL)
    private UserRating userRating;

    @Column(unique = true)
    private Long whatsappNumber;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "preference")
    private List<Preference> preferences;

    private boolean scraped;

    public boolean isAdmin() {
        return Constants.ROLE_ADMIN.equalsIgnoreCase(roleConfiguration.getRole().getName());
    }

    public boolean isDistributor() {
        return Constants.ROLE_DISTRIBUTOR.equalsIgnoreCase(roleConfiguration.getRole().getName());
    }

    public boolean isSalesman() {
        return Constants.ROLE_SALESMAN.equalsIgnoreCase(roleConfiguration.getRole().getName());
    }

    public boolean isRetailer() {
        return Constants.ROLE_RETAILER.equalsIgnoreCase(roleConfiguration.getRole().getName());
    }

}

