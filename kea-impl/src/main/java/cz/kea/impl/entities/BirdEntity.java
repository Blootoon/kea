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

import cz.kea.api.enums.Sex;
import cz.kea.api.enums.State;
import cz.kea.api.model.Bird;
import cz.kea.api.model.Contact;
import cz.kea.api.model.Nest;
import cz.kea.api.model.Taxon;
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

    @ManyToOne(targetEntity = TaxonEntity.class)
    @JoinColumn(name = "TAXON_ID")
    private Taxon taxon;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "SEX", nullable = false)
    private Sex sex;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "STATE", nullable = false)
    private State state;

    @ManyToOne(targetEntity = NestEntity.class)
    @JoinColumn(name = "NEST_ID")
    private Nest nest;

    @Column(name = "LAYED")
    private LocalDate layed;

    @Column(name = "HATCHED")
    private LocalDate hatched;

    @Column(name = "MUTATION")
    private String mutation;

    @Column(name = "IDENTIFICATION")
    private String identification;

    @Column(name = "NAME")
    private String name;

    @ManyToOne(targetEntity = ContactEntity.class)
    @JoinColumn(name = "OWNER_ID")
    private Contact owner;

    @Lob
    @Column(name = "NOTE")
    private String note;

    @OneToMany(targetEntity = WeightRecordEntity.class, mappedBy = "bird")
    private List<WeightRecord> weightRecords;

    public BirdEntity() {
    }

    public BirdEntity(Taxon taxon, Sex sex, State state) {
        this.taxon = taxon;
        this.sex = sex;
        this.state = state;
    }

    public BirdEntity(Taxon taxon, Sex sex, State state, Nest nest, LocalDate layed, LocalDate hatched, String mutation, String identification, String name, Contact owner, String note) {
        this.taxon = taxon;
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Taxon getTaxon() {
        return taxon;
    }

    @Override
    public void setTaxon(Taxon taxon) {
        this.taxon = taxon;
    }

    @Override
    public Sex getSex() {
        return sex;
    }

    @Override
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

    @Override
    public Nest getNest() {
        return nest;
    }

    @Override
    public void setNest(Nest nest) {
        this.nest = nest;
    }

    @Override
    public LocalDate getLayed() {
        return layed;
    }

    @Override
    public void setLayed(LocalDate layed) {
        this.layed = layed;
    }

    @Override
    public LocalDate getHatched() {
        return hatched;
    }

    @Override
    public void setHatched(LocalDate hatched) {
        this.hatched = hatched;
    }

    @Override
    public String getMutation() {
        return mutation;
    }

    @Override
    public void setMutation(String mutation) {
        this.mutation = mutation;
    }

    @Override
    public String getIdentification() {
        return identification;
    }

    @Override
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Contact getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Contact owner) {
        this.owner = owner;
    }

    @Override
    public String getNote() {
        return note;
    }

    @Override
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public List<WeightRecord> getWeightRecords() {
        return weightRecords;
    }

    @Override
    public void setWeightRecords(List<WeightRecord> weightRecords) {
        this.weightRecords = weightRecords;
    }

    @Override
    public String toString() {
        return "BirdEntity{" +
            "taxon=" + taxon +
            ", id=" + id +
            ", sex=" + sex +
            ", identification='" + identification + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}
