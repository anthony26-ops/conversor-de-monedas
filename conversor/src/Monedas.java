import com.google.gson.annotations.SerializedName;

public class Monedas {
    @SerializedName("base_code")
    private String origin;
    @SerializedName("target_code")
    private String destine;
    @SerializedName("conversion_rate")
    private double cuota;
    @SerializedName("conversion_result")
    private double resultado;

    public Monedas(String origin, String destine, double cuota, double resultado) {
        this.origin = origin;
        this.destine = destine;
        this.cuota = cuota;
        this.resultado = resultado;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestine() {
        return destine;
    }

    public double getCuota() {
        return cuota;
    }

    public double getResultado() {
        return resultado;
    }

    @Override
    public String toString() {
        return "Moneda origen: " + origin +
                ". Moneda destino: " + destine +
                ". Tasa de cambio: " + cuota +
                ". Monto convertido: " + resultado;
    }
}
