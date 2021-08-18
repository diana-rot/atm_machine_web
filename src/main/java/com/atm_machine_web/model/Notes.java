package com.atm_machine_web.model;
import javax.persistence.*;
import java.io.Serializable;


@Entity(name = "Notes")
public class Notes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "note_id",
            updatable = false
    )
    private Long noteId;

    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "value", nullable = false)
    private Integer value;
    @Column(name = "currency", nullable = false)
    private String currency;



    public void setValue(Integer value) {
        this.value = value;
    }



    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Notes() {

    }

    public Notes(String type, Integer value) {
        this.type = type;
        this.value = value;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "noteId=" + noteId +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", currency='" + currency + '\'' +
                '}';
    }


    public enum LeuTypes{
        LEU_100,
        LEU_50,
        LEU_10,
        LEU_5,
        LEU_1;
    }
    public enum EuroTypes{
        Euro_100,
        Euro_50,
        Euro_10,
        Euro_5,
        Euro_1;
    }



}