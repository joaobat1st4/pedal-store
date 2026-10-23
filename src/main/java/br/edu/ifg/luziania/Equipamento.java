package br.edu.ifg.luziania;

public class Equipamento {
    public Long id;
    public String titulo;
    public String descricao;
    public double preco;
    public String urlImagem;
    public String status;

    // Construtor vazio (Obrigatório para o JSON-B)
    public Equipamento() {
    }

    public Equipamento(Long id, String titulo, String descricao, double preco, String urlImagem, String status) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.urlImagem = urlImagem;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
