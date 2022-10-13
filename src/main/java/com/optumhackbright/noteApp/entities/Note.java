package com.optumhackbright.noteApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.optumhackbright.noteApp.dtos.NoteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Notes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String body;

    @ManyToOne //create the association within Hibernate
    @JsonBackReference //prevent infinite recursion when deliver the resource up
    //as JSON through the RESTful API endpoint
    private User user;

    public Note(NoteDto noteDto){
        if(noteDto.getBody() != null){
            this.body = noteDto.getBody();
        }
    }
}
