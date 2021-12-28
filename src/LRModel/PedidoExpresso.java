package LRModel;

public class PedidoExpresso {
    private String contacto;
    private String nif;
    private String idTecnico;
    private String descricao;

    public PedidoExpresso(String nif,String contacto){  //TODO apagar este e passar todos os construtores para o outro
        this.contacto = contacto;
        this.nif = nif;
    }

    public PedidoExpresso (String contacto, String nif, String idTecnico, String descricao) {
        this.contacto = contacto;
        this.nif = nif;
        this.idTecnico = idTecnico;
        this.descricao = descricao;
    }

    // getters e setters
    public String getContacto(){
        return this.contacto;
    }

    public String getNif() {
        return this.nif;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(String idTecnico) {
        this.idTecnico = idTecnico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
