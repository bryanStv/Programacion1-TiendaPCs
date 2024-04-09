public class Ordenador{

    private int id;
    private String modelo;
    private float precio;
    private int portatil;
    public Ordenador(String modelo, float precio, int portatil) {
        this.modelo = modelo;
        this.precio = precio;
        this.portatil = portatil;
    }
    public Ordenador(int id, String modelo, float precio, int portatil) {
        this.id = id;
        this.modelo = modelo;
        this.precio = precio;
        this.portatil = portatil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int isPortatil() {
        return portatil;
    }

    public void setPortatil(int portatil) {
        this.portatil = portatil;
    }

    @Override
    public String toString() {
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_RESET = "\u001B[0m";
        if(portatil==1){
            return ANSI_YELLOW+"Ordenador Portatil"+
                    "\n\t id = "+ id+
                    "\n\t modelo = " + modelo+
                    "\n\t precio = " + precio + "€ "+ANSI_RESET;
        }else{
            return ANSI_YELLOW+"Ordenador de Sobremesa" +
                    "\n\t id = "+ id+
                    "\n\t modelo = " + modelo+
                    "\n\t precio = " + precio + "€ "+ANSI_RESET;
        }
    }
}
