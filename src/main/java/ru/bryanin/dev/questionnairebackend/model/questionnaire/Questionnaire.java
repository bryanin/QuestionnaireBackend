package ru.bryanin.dev.questionnairebackend.model.questionnaire;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.TypeDef;
import ru.bryanin.dev.questionnairebackend.model.task.Task;

import javax.persistence.*;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "questionnaires")
public class Questionnaire {
    @Id
    @SequenceGenerator(name = "questionnaires_sequence", sequenceName = "questionnaires_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "questionnaires_sequence")
    Long id;
    @Column(name = "system")
    String system;
    @Transient
    @OneToOne(mappedBy = "tasks")
    private Task task;
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    ArrayList<Content> content;

    //@JsonProperty("System")
    public String getSystem() {
        return this.system;
    }
    public void setSystem(String system) {
        this.system = system;
    }

//    @JsonProperty("Content")
//    public ArrayList<Content> getContent() {
//        return this.content;
//    }
//    public void setContent(ArrayList<Content> content) {
//        this.content = content;
//    }

    public Questionnaire() {
    }

    public Questionnaire(
            //Long id,
            String system
            //ArrayList<Content> content
    ) {
        //this.id = id;
        this.system = system;
        //this.content = content;
    }
}
