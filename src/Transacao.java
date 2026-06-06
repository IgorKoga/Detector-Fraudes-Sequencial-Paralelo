public class Transacao {
    private String id;
    private double valor;
    private String status;
    private String cpfCliente;

    public Transacao(String id, double valor, String cpfCliente) {
        this.id = id;
        this.valor = valor;
        this.status = "PROCESSANDO";
        this.cpfCliente = cpfCliente;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }
}