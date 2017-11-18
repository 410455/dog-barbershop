package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.enums.Gender;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

/**
 * @author Martin Kuchár 433499
 */

@Entity
public class Dog {

    @Id
    @Column(name = "dog_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(min = 2, max = 30)
    @Column(nullable = false)
    private String name;

    @NotNull
    @Length(min = 1, max = 30)
    @Column(nullable = false)
    private String breed;

    @NotNull
    @Column(nullable = false)
    private Date dateOfBirth;

    @Enumerated
    @NotNull
    @Column(nullable = false)
    private Gender gender;

    @OneToMany(mappedBy = "dog")
    private Set<ServiceRecord> serviceRecords;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "customer_id")
    private Customer owner;

    public Dog() {
    }

    public Dog(String name, String breed, Date dateOfBirth, Gender gender, Set<ServiceRecord> serviceRecords, Customer owner) {
        this.name = name;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.serviceRecords = serviceRecords;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Set<ServiceRecord> getServiceRecords() {
        return serviceRecords;
    }

    public void setServiceRecords(Set<ServiceRecord> serviceRecords) {
        this.serviceRecords = serviceRecords;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dog)) return false;
        Dog dog = (Dog) o;
        return Objects.equals(getName(), dog.getName()) &&
            Objects.equals(getBreed(), dog.getBreed()) &&
            Objects.equals(getDateOfBirth(), dog.getDateOfBirth()) &&
            getGender() == dog.getGender() &&
            Objects.equals(getOwner(), dog.getOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBreed(), getDateOfBirth(), getGender(), getOwner());
    }
}
