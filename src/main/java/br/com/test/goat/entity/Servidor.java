package br.com.test.goat.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_servidor")
public class Servidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String email;
    private String nome;
    private LocalDate dataNascimento;
    private Character sexo;

    @Column(name = "tipo_id")
    private Integer tipoServidor;

    public Servidor() {}

    private Servidor(Builder builder) {
        this.id = builder.id;
        this.cpf = builder.cpf;
        this.email = builder.email;
        this.nome = builder.nome;
        this.dataNascimento = builder.dataNascimento;
        this.sexo = builder.sexo;
        this.tipoServidor = builder.tipoServidor;
    }

    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Character getSexo() {
        return sexo;
    }

    public Integer getTipoServidor() {
        return tipoServidor;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String cpf;
        private String email;
        private String nome;
        private LocalDate dataNascimento;
        private Character sexo;
        private Integer tipoServidor;

        private Builder() {}

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder dataNascimento(LocalDate dataNascimento) {
            this.dataNascimento = dataNascimento;
            return this;
        }

        public Builder sexo(Character sexo) {
            this.sexo = sexo;
            return this;
        }

        public Builder tipoServidor(Integer tipoServidor) {
            this.tipoServidor = tipoServidor;
            return this;
        }

        public Servidor build() {
            return new Servidor(this);
        }
    }
}
