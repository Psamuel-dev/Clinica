package br.com.clinica;

public record Medico(Long id, String nome, String crm, String especialidade) {
    
    public Medico(String nome, String crm, String especialidade){
        this(null, nome, crm, especialidade);
    }
}
