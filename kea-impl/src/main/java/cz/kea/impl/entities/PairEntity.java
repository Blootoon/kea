package cz.kea.impl.entities;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cz.kea.api.model.Bird;
import cz.kea.api.model.Nest;
import cz.kea.api.model.Pair;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Entity
@Table(name = "PAIR")
public class PairEntity implements Pair {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @ManyToOne(targetEntity = BirdEntity.class)
    @JoinColumn(name = "MALE_ID")
    private Bird male;

    @ManyToOne(targetEntity = BirdEntity.class)
    @JoinColumn(name = "FEMALE_ID")
    private Bird female;

    @OneToMany(targetEntity = NestEntity.class, mappedBy = "pair")
    private List<Nest> nests;

    @Column(name = "DATE_FROM")
    private LocalDate dateFrom;

    @Column(name = "DATE_TO")
    private LocalDate dateTo;

    @Lob
    @Column(name = "NOTE")
    private String note;

    public PairEntity() {
    }

    public PairEntity(Bird male, Bird female) {
        this.male = male;
        this.female = female;
    }

    public PairEntity(Bird male, Bird female, List<Nest> nests, LocalDate dateFrom, LocalDate dateTo, String note) {
        this.male = male;
        this.female = female;
        this.nests = nests;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bird getMale() {
        return male;
    }

    public void setMale(Bird male) {
        this.male = male;
    }

    public Bird getFemale() {
        return female;
    }

    public void setFemale(Bird female) {
        this.female = female;
    }

    public List<Nest> getNests() {
        return nests;
    }

    public void setNests(List<Nest> nests) {
        this.nests = nests;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "PairEntity{" +
            "id=" + id +
            ", male=" + male +
            ", female=" + female +
            '}';
    }
}
