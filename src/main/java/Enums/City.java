package Enums;

public enum City {
    // COLOMBIA
    BOGOTA(Country.COLOMBIA),
    MEDELLIN(Country.COLOMBIA),
    CALI(Country.COLOMBIA),
    BARRANQUILLA(Country.COLOMBIA),
    CARTAGENA(Country.COLOMBIA),

    // ARGENTINA
    BUENOS_AIRES(Country.ARGENTINA),
    CORDOBA(Country.ARGENTINA),
    ROSARIO(Country.ARGENTINA),
    MENDOZA(Country.ARGENTINA),
    LA_PLATA(Country.ARGENTINA),

    // CHILE
    SANTIAGO(Country.CHILE),
    VALPARAISO(Country.CHILE),
    CONCEPCION(Country.CHILE),
    LA_SERENA(Country.CHILE),
    ANTOFAGASTA(Country.CHILE),

    // PERU
    LIMA(Country.PERU),
    AREQUIPA(Country.PERU),
    TRUJILLO(Country.PERU),
    CUSCO(Country.PERU),
    PIURA(Country.PERU),

    // MEXICO
    CIUDAD_DE_MEXICO(Country.MEXICO),
    GUADALAJARA(Country.MEXICO),
    MONTERREY(Country.MEXICO),
    PUEBLA(Country.MEXICO),
    CANCUN(Country.MEXICO);

    private final Country country;

    City(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }
}
