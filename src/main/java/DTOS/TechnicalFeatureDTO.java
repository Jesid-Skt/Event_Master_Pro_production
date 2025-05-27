package DTOS;

public class TechnicalFeatureDTO {
    private String featureID;
    private String name;
    private String description;

    public TechnicalFeatureDTO() {}

    public TechnicalFeatureDTO(String featureID, String name, String description) {
        this.featureID = featureID;
        this.name = name;
        this.description = description;
    }

    public String getFeatureID() {
        return featureID;
    }

    public void setFeatureID(String featureID) {
        this.featureID = featureID;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

