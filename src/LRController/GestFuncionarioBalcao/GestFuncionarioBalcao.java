package LRController.GestFuncionarioBalcao;

import LRModel.*;

import java.time.LocalDate;
import java.util.List;


public class GestFuncionarioBalcao implements IGestFuncionarioBalcao {
    ILojaReparacoesModel model;



    public GestFuncionarioBalcao(ILojaReparacoesModel model){
        this.model = model;
    }

    public boolean loginFuncionarioBalcao(String username, String password) {
        if (!this.model.containsFuncionario(username)) return false;

        FuncionarioBalcao f = this.model.getFuncionarioBalcao(username);
        String fPass = f.getPassword();
        return password.equals(fPass);
    }





    public void registarPedidoOrcamento(String nomeCliente, String contacto, String nif, String email, String descricao, LocalDate data){
        PedidoOrcamento po = new PedidoOrcamento(nomeCliente,contacto,email,nif,descricao,data);
        model.adicionaPedidoOrcamento(po);
    }

    //Caso não exista disponiblidade devolve null, Caso exista devolve o username do tecnico que realizará o serviço

    public String verificaDisponiblidadeExpresso(){

        List<Tecnico> tecnicos = this.model.getTecnicos();
        String username = null;

        for (Tecnico t : tecnicos) {

            if (t.getOcupado() == false) {
                username = t.getUsername();
                t.setOcupado(true);

                break;
            }
        }
        return username;
    }



    public String registarServicoExpresso(String nif,String contacto,String descricao) {

        String username = verificaDisponiblidadeExpresso();

        if(username != null){
            PedidoExpresso pe = new PedidoExpresso(nif, contacto, username, descricao);
            model.adicionaPedidoExpresso(pe);
        }
        return username;
    }

    public List<String> getNomeFuncionarios() { return this.model.getNomeFuncionarios();}

    public List<FuncionarioBalcao> getFuncionarios() {
        return this.model.getListaDeFuncionarios();
    }

    public void registarConfirmacaoOrcamento(String nif,boolean confirm){
        PedidoOrcamento po = this.model.getPedidoOrcamento(nif);
        po.setConfirmacaoReparacao(confirm);
    }

    public void registarEntregaEquipamentoPeloCliente(String nif,String idFuncionarioBalcao){
        Entrega e = new Entrega(nif,idFuncionarioBalcao);
        model.adicionaEntregaPeloCliente(e);
        FuncionarioBalcao fb = model.getFuncionarioBalcao(idFuncionarioBalcao);
        fb.incrementaRececao();
    }


    public void registarEntregaEquipamentoePagamento(String nif,String idFuncionarioBalcao){
        Entrega e = this.model.getEntrega(nif);
        e.setEntregue(true);
        e.setPago(true);
        FuncionarioBalcao fb = model.getFuncionarioBalcao(idFuncionarioBalcao);
        fb.incrementaEntregas();
    }

    public void saveFiles(){
        this.model.saveData("saves");
    }

}
