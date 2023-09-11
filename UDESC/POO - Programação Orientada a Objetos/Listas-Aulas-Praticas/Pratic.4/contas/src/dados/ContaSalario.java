package dados;

public class ContaSalario extends ContaBancaria {
    private int setCpnjEmpresa;

    public ContaSalario(){
        super();
    }

    public void setCpnjEmpresa(int setCpnjEmpresa) {
        this.setCpnjEmpresa = setCpnjEmpresa;
    }
    public boolean depositar(int cnpj, float valor){
        if(this.setCpnjEmpresa == cnpj && valor > 0){
            this.saldo = this.saldo + valor;
            return true;
        }
        else{
            return false;
        }
    }

}
