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
@Table(name = "NEST")
public class NestEntity implements Nest {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @ManyToOne(targetEntity = PairEntity.class)
    @JoinColumn(name = "PAIR_ID")
    private Pair pair;

    @Column(name = "DATE_STARTED")
    private LocalDate date;

    @OneToMany(targetEntity = BirdEntity.class, mappedBy = "nest")
    private List<Bird> chicks;

    @Lob
    @Column(name = "NOTE")
    private String note;

    public NestEntity() {
    }

    public NestEntity(Pair pair, LocalDate date) {
        this.pair = pair;
        this.date = date;
    }

    public NestEntity(Pair pair, LocalDate date, List<Bird> chicks, String note) {
        this.pair = pair;
        this.date = date;
        this.chicks = chicks;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pair getPair() {
        return pair;
    }

    public void setPair(Pair pair) {
        this.pair = pair;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Bird> getChicks() {
        return chicks;
    }

    public void setChicks(List<Bird> chicks) {
        this.chicks = chicks;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "NestEntity{" +
            "id=" + id +
            ", pair=" + pair +
            ", date=" + date +
            '}';
    }
}
