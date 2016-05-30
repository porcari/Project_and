package fatec.br_bus.Model.Object;

public class Agenda {
    String hora_Ini;
    String hora_Fim;
    String min_Ini;
    String min_Fim;
    String segunda;
    String terca;
    String quarta;
    String quinta;
    String sexta;
    String sabado;
    String domingo;

    public Agenda(String hora_Ini, String hora_Fim, String min_Ini, String min_Fim, String segunda, String terca, String quarta, String quinta, String sexta, String sabado, String domingo) {
        this.hora_Ini = hora_Ini;
        this.hora_Fim = hora_Fim;
        this.min_Ini = min_Ini;
        this.min_Fim = min_Fim;
        this.segunda = segunda;
        this.terca = terca;
        this.quarta = quarta;
        this.quinta = quinta;
        this.sexta = sexta;
        this.sabado = sabado;
        this.domingo = domingo;
    }

    public String getHora_Ini() {
        return hora_Ini;
    }

    public void setHora_Ini(String hora_Ini) {
        this.hora_Ini = hora_Ini;
    }

    public String getHora_Fim() {
        return hora_Fim;
    }

    public void setHora_Fim(String hora_Fim) {
        this.hora_Fim = hora_Fim;
    }

    public String getMin_Ini() {
        return min_Ini;
    }

    public void setMin_Ini(String min_Ini) {
        this.min_Ini = min_Ini;
    }

    public String getMin_Fim() {
        return min_Fim;
    }

    public void setMin_Fim(String min_Fim) {
        this.min_Fim = min_Fim;
    }

    public String getSegunda() {
        return segunda;
    }

    public void setSegunda(String segunda) {
        this.segunda = segunda;
    }

    public String getTerca() {
        return terca;
    }

    public void setTerca(String terca) {
        this.terca = terca;
    }

    public String getQuarta() {
        return quarta;
    }

    public void setQuarta(String quarta) {
        this.quarta = quarta;
    }

    public String getQuinta() {
        return quinta;
    }

    public void setQuinta(String quinta) {
        this.quinta = quinta;
    }

    public String getSexta() {
        return sexta;
    }

    public void setSexta(String sexta) {
        this.sexta = sexta;
    }

    public String getSabado() {
        return sabado;
    }

    public void setSabado(String sabado) {
        this.sabado = sabado;
    }

    public String getDomingo() {
        return domingo;
    }

    public void setDomingo(String domingo) {
        this.domingo = domingo;
    }
}
