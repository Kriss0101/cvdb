package kriss0101.cvdb.api.datamodel;


public enum Grade {

    NONE("No experience","No experience at all"),
    BEGINNER("Beginner","Have some theoretical knowledge but no or little practical experience"),
    PROFICIENT("Proficient","Can comfortable apply the tool in practical and real life problems"),
    EXPERIENCED("Experience","At least three years of pratical and real life experience"),
    AUTHORITY("Authority","Very experienced. Can teach others");

    private final String description;
    private final String name;

    Grade(String name, String description) {
        this.description = description;
        this.name = name;
    }


}
