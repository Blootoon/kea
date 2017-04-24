package cz.kea.impl.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cz.kea.api.enums.Genus;
import cz.kea.api.enums.Sex;
import cz.kea.api.enums.Species;
import cz.kea.api.enums.State;
import cz.kea.api.model.Bird;
import cz.kea.api.model.Contact;
import cz.kea.api.model.Nest;
import cz.kea.api.model.WeightRecord;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Entity
@Table(name = "BIRD")
public class BirdEntity implements Bird {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "GENUS", length = 64, nullable = false)
    private Genus genus;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "SPECIES", length = 128, nullable = false)
    private Species species;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "SEX", length = 16, nullable = false)
    private Sex sex;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "STATE", length = 16, nullable = false)
    private State state;

    @ManyToOne(targetEntity = NestEntity.class)
    @JoinColumn(name = "NEST_ID")
    private Nest nest;

    @Column(name = "LAYED")
    private LocalDate layed;

    @Column(name = "HATCHED")
    private LocalDate hatched;

    @Column(name = "MUTATION", length = 64)
    private String mutation;

    @Column(name = "IDENTIFICATION", length = 64)
    private String identification;

    @Column(name = "NAME", length = 64)
    private String name;

    @ManyToOne(targetEntity = ContactEntity.class)
    @JoinColumn(name = "OWNER_ID")
    private Contact owner;

    @Lob
    @Column(name = "NOTE")
    private String note;

    @OneToMany(targetEntity = WeightRecordEntity.class, mappedBy = "bird")
    private List<WeightRecord> weightRecords;

    public BirdEntity(Genus genus, Species species, Sex sex, State state) {
        this.genus = genus;
        this.species = species;
        this.sex = sex;
        this.state = state;
    }

    public BirdEntity(Genus genus, Species species, Sex sex, State state, Nest nest, LocalDate layed, LocalDate hatched, String mutation, String identification, String name, Contact owner, String
        note) {
        this.genus = genus;
        this.species = species;
        this.sex = sex;
        this.state = state;
        this.nest = nest;
        this.layed = layed;
        this.hatched = hatched;
        this.mutation = mutation;
        this.identification = identification;
        this.name = name;
        this.owner = owner;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Genus getGenus() {
        return genus;
    }

    public void setGenus(Genus genus) {
        this.genus = genus;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

    public Nest getNest() {
        return nest;
    }

    public void setNest(Nest nest) {
        this.nest = nest;
    }

    public LocalDate getLayed() {
        return layed;
    }

    public void setLayed(LocalDate layed) {
        this.layed = layed;
    }

    public LocalDate getHatched() {
        return hatched;
    }

    public void setHatched(LocalDate hatched) {
        this.hatched = hatched;
    }

    public String getMutation() {
        return mutation;
    }

    public void setMutation(String mutation) {
        this.mutation = mutation;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getOwner() {
        return owner;
    }

    public void setOwner(Contact owner) {
        this.owner = owner;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<WeightRecord> getWeightRecords() {
        return weightRecords;
    }

    public void setWeightRecords(List<WeightRecord> weightRecords) {
        this.weightRecords = weightRecords;
    }

    @Override
    public String toString() {
        return "BirdEntity{" +
            "species=" + species +
            ", genus=" + genus +
            ", id=" + id +
            ", sex=" + sex +
            ", identification='" + identification + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}
