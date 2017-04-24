package cz.kea.impl.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cz.kea.api.model.Bird;
import cz.kea.api.model.WeightRecord;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Entity
@Table(name = "WEIGHT_RECORD")
public class WeightRecordEntity implements WeightRecord {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @ManyToOne(targetEntity = BirdEntity.class)
    @JoinColumn(name = "BIRD_ID")
    private Bird bird;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "WEIGHT")
    private double weight;

    public WeightRecordEntity() {
    }

    public WeightRecordEntity(Bird bird, LocalDate date, double weight) {
        this.bird = bird;
        this.date = date;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bird getBird() {
        return bird;
    }

    public void setBird(Bird bird) {
        this.bird = bird;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
