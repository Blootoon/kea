package cz.kea.api.model;

import java.time.LocalDate;
import java.util.List;

import cz.kea.api.enums.Sex;
import cz.kea.api.enums.State;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface Bird extends Identifiable<Long> {

    void setId(Long id);

    Taxon getTaxon();

    void setTaxon(Taxon species);

    Sex getSex();

    void setSex(Sex sex);

    State getState();

    void setState(State state);

    Nest getNest();

    void setNest(Nest nest);

    LocalDate getLayed();

    void setLayed(LocalDate layed);

    LocalDate getHatched();

    void setHatched(LocalDate hatched);

    String getMutation();

    void setMutation(String mutation);

    String getIdentification();

    void setIdentification(String identification);

    String getName();

    void setName(String name);

    Contact getOwner();

    void setOwner(Contact owner);

    String getNote();

    void setNote(String note);

    List<WeightRecord> getWeightRecords();

    void setWeightRecords(List<WeightRecord> weightRecords);

}
