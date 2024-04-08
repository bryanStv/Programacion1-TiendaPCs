public class Ordenador{

    private int id;
    private String modelo;
    private float precio;
    private int portatil;

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
        if(portatil==1){
            return "Ordenador Portatil{" +
                    "modelo='" + modelo + '\'' +
                    ", precio=" + precio +
                    '}';
        }else{
            return "Ordenador de Sobremesa{" +
                    "modelo='" + modelo + '\'' +
                    ", precio=" + precio +
                    '}';
        }
    }
}
