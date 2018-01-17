package datamodel;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public enum SkillLevel {

    NONE("No experience","No experience at all"),
    BEGINNER("Beginner","Have some theoretical knowledge but no or little practical experience"),
    PROFICIENT("Proficient","Can comfortable apply the tool in practical and real life problems"),
    EXPERIENCED("Experience","At least three years of pratical and real life experience"),
    AUTHORITY("Authority","Very experienced. Can teach others");

    private final String description;
    private final String name;

    SkillLevel(String name, String description) {
        this.description = description;
        this.name = name;
    }


}
