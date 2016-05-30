package fatec.br_bus.Model.Object;

public class Linha {
    String nome;
    String codigo;
    String terminal;
    int id_ponto;
    int id_agenda;
    String codPonto;


    public Linha(String nome, String codigo, String terminal, int id_ponto, int id_agenda, String codPonto) {
        this.nome = nome;
        this.codigo = codigo;
        this.terminal = terminal;
        this.id_ponto = id_ponto;
        this.id_agenda = id_agenda;
        this.codPonto = codPonto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public int getId_ponto() {
        return id_ponto;
    }

    public void setId_ponto(int id_ponto) {
        this.id_ponto = id_ponto;
    }

    public int getId_agenda() {
        return id_agenda;
    }

    public void setId_agenda(int id_agenda) {
        this.id_agenda = id_agenda;
    }

    public String getCodPonto() {return codPonto;}

    public void setCodPonto(String codPonto) { this.codPonto = codPonto; }
}


