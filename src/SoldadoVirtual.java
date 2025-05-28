public class SoldadoVirtual {
    private int Id;
    private String Alias;
    private String TecnologiaEspecial;
    private int NivelVirtualidad;
    private String DimensionOperativa;

    public SoldadoVirtual(int id, String alias, String tecnologiaEspecial, int nivelVirtualidad, String dimensionOperativa) {
        Id = id;
        Alias = alias;
        TecnologiaEspecial = tecnologiaEspecial;
        NivelVirtualidad = nivelVirtualidad;
        DimensionOperativa = dimensionOperativa;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public String getTecnologiaEspecial() {
        return TecnologiaEspecial;
    }

    public void setTecnologiaEspecial(String tecnologiaEspecial) {
        TecnologiaEspecial = tecnologiaEspecial;
    }

    public int getNivelVirtualidad() {
        return NivelVirtualidad;
    }

    public void setNivelVirtualidad(int nivelVirtualidad) {
        NivelVirtualidad = nivelVirtualidad;
    }

    public String getDimensionOperativa() {
        return DimensionOperativa;
    }

    public void setDimensionOperativa(String dimensionOperativa) {
        DimensionOperativa = dimensionOperativa;
    }

    @Override
    public String toString() {
        return "Id: " + Id  + "\nAlias: " + Alias + "\nTecnología Especial: " + TecnologiaEspecial + "\nNivel Virtualidad: " + NivelVirtualidad + "\nDimension Operativa" + DimensionOperativa;

    }
}
