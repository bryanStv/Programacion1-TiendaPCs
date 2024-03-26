public class Ordenador{

    private int id;
    private String modelo;
    private float precio;
    private boolean portatil;

    public Ordenador(int id, String modelo, float precio, boolean portatil) {
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

    public boolean isPortatil() {
        return portatil;
    }

    public void setPortatil(boolean portatil) {
        this.portatil = portatil;
    }

    @Override
    public String toString() {
        if(portatil){
            return "Ordenador Portatil{" +
                    ", modelo='" + modelo + '\'' +
                    ", precio=" + precio +
                    '}';
        }else{
            return "Ordenador de Sobremesa{" +
                    ", modelo='" + modelo + '\'' +
                    ", precio=" + precio +
                    '}';
        }
    }
}
