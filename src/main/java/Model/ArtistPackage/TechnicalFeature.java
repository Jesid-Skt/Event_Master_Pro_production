package Model.ArtistPackage;

public class TechnicalFeature {
    private String name;
    private String description;

    public TechnicalFeature(String name) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRequirement() { return name; }
}

