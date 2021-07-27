package com.atm_machine_web.model;
import javax.persistence.*;



@Entity(name = "Notes")
public class Notes {
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Notes() {

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


    public Notes(Long noteId, String type, Integer value) {
        this.noteId = noteId;
        this.type = type;
        this.value = value;
    }
    public Notes(Long noteId, String type) {
        this.noteId = noteId;
        this.type = type;

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




}