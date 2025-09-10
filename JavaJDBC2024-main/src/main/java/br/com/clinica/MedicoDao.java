package br.com.clinica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDao {
    
    public void salvar(Medico medico) throws Exception {

        var sql = "insert into medicos (nome, crm, especialidade) values (?, ?, ?)";
        
        try (var conexao = Conexao.obterConexao();
            var stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, medico.nome());
                stmt.setString(2,  medico.crm());
                stmt.setString(3, medico.especialidade());
                stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public List<Medico> buscarTodos() throws Exception{

        var sql = "select * from medicos";

        List<Medico> medicos = new ArrayList<>();

        try (var conexao = Conexao.obterConexao();
            var stmt = conexao.prepareStatement(sql)) {

                try(ResultSet rs = stmt.executeQuery()){
                    while (rs.next()) {

                        Medico medico = new Medico(rs.getLong("id"), 
                        rs.getString("nome"), rs.getString("crm"), 
                        rs.getString("especialidade"));

                        medicos.add(medico);
                    }
                }

        } catch (SQLException e) {
            throw new Exception(e);
        }

        return medicos;
    }
}

