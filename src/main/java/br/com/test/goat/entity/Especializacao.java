package br.com.test.goat.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_especializacao")
public class Especializacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String area;
    private String tipo;
    @Column(name = "cargahoraria")
    private Integer cargaHoraria;

    @Column(name = "idservidor")
    private Integer idServidor;

    @Column(name = "idstatusdeferimento")
    private Integer idStatusDeferimento;

    @Column(name = "valortotaldocurso")
    private Double valorTotalDoCurso;

    public Especializacao() {}

    public Especializacao(Builder builder) {
        this.id = builder.id;
        this.area = builder.area;
        this.tipo = builder.tipo;
        this.cargaHoraria = builder.cargaHoraria;
        this.valorTotalDoCurso = builder.valorTotalDoCurso;
        this.idServidor = builder.idServidor;
        this.idStatusDeferimento = builder.idStatusDeferimento;
    }

    public String getArea() {
        return area;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public Double getValorTotalDoCurso() {
        return valorTotalDoCurso;
    }

    public Integer getIdServidor() {
        return idServidor;
    }

    public Integer getIdStatusDeferimento() {
        return idStatusDeferimento;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Integer getId() {
        return id;
    }

    public static final class Builder {
        private Integer id;
        private String area;
        private String tipo;
        private Integer cargaHoraria;
        private Integer idServidor;
        private Integer idStatusDeferimento;
        private Double valorTotalDoCurso;

        private Builder() {}

        public Builder area(String area) {
            this.area = area;
            return this;
        }

        public Builder tipo(String tipo) {
            this.tipo = tipo;
            return this;
        }

        public Builder cargaHoraria(Integer cargaHoraria) {
            this.cargaHoraria = cargaHoraria;
            return this;
        }

        public Builder valorTotalDoCurso(Double valorTotalDoCurso) {
            this.valorTotalDoCurso = valorTotalDoCurso;
            return this;
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder idServidor(Integer idServidor) {
            this.idServidor = idServidor;
            return this;
        }

        public Builder idStatusDeferimento(Integer idStatusDeferimento) {
            this.idStatusDeferimento = idStatusDeferimento;
            return this;
        }

        public Especializacao build() {
            return new Especializacao(this);
        }
    }
}
