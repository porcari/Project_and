package fatec.br_bus.Model.Object;

public class Onibus {

    String nome;
    String horarioCheg;
    String status;

    public Onibus(String nome, String horarioCheg, String status) {
        this.nome = nome;
        this.horarioCheg = horarioCheg;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHorarioCheg() {
        return horarioCheg;
    }

    public void setHorarioCheg(String horarioCheg) {
        this.horarioCheg = horarioCheg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
