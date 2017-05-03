package cz.kea.impl.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cz.kea.api.enums.TaxonomicRank;
import cz.kea.api.model.Taxon;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Entity
@Table(name = "TAXON")
public class TaxonEntity implements Taxon {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @ManyToOne(targetEntity = TaxonEntity.class)
    @JoinColumn(name = "PARENT_ID")
    private Taxon parent;

    @OneToMany(targetEntity = TaxonEntity.class, mappedBy = "parent")
    private List<Taxon> children;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "TAXONOMIC_RANK")
    private TaxonomicRank taxonomicRank;

    @Column(name = "LATIN_NAME")
    private String latinName;

    @Column(name = "ENGLISH_NAME")
    private String englishName;

    @Column(name = "GERMAN_NAME")
    private String germanName;

    @Column(name = "CZECH_NAME")
    private String czechName;

    public TaxonEntity() {
    }

    public TaxonEntity(Taxon parent, TaxonomicRank taxonomicRank, String latinName, String englishName, String germanName, String czechName) {
        this.parent = parent;
        this.taxonomicRank = taxonomicRank;
        this.latinName = latinName;
        this.englishName = englishName;
        this.germanName = germanName;
        this.czechName = czechName;
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
    public Taxon getParent() {
        return parent;
    }

    @Override
    public void setParent(Taxon parent) {
        this.parent = parent;
    }

    @Override
    public List<Taxon> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<Taxon> children) {
        this.children = children;
    }

    @Override
    public TaxonomicRank getTaxonomicRank() {
        return taxonomicRank;
    }

    @Override
    public void setTaxonomicRank(TaxonomicRank taxonomicRank) {
        this.taxonomicRank = taxonomicRank;
    }

    @Override
    public String getLatinName() {
        return latinName;
    }

    @Override
    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    @Override
    public String getEnglishName() {
        return englishName;
    }

    @Override
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    @Override
    public String getGermanName() {
        return germanName;
    }

    @Override
    public void setGermanName(String germanName) {
        this.germanName = germanName;
    }

    @Override
    public String getCzechName() {
        return czechName;
    }

    @Override
    public void setCzechName(String czechName) {
        this.czechName = czechName;
    }

}
