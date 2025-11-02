package br.com.faculdade.atividade_2;

public class Disciplina {
    private Long id;
    private String nome;
    private String curso;
    private Integer cargaHoraria;

    public Disciplina() {}

    public Disciplina(Long id, String nome, String curso, Integer cargaHoraria) {
        this.id = id;
        this.nome = nome;
        this.curso = curso;
        this.cargaHoraria = cargaHoraria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
}
