package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.Conexao;
import modelo.Pessoa;

public class PessoaDAO {

    public Pessoa login(String login, String senha) throws SQLException {
        Connection conexao = new Conexao().getConexao();
        String sql = "SELECT * FROM usuario where login=? and senha=?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setString(1, login);
        ps.setString(2, senha);
        ResultSet rs = ps.executeQuery();

        Pessoa usuario = null;
        while (rs.next()) {
            usuario = new Pessoa();
            usuario.setCodigo(rs.getInt("codigo"));
            usuario.setNome(rs.getString("nome"));
            usuario.setLogin(rs.getString("login"));
            usuario.setNivel(rs.getString("nivel"));

        }
        rs.close();
        ps.close();
        conexao.close();
        
        return usuario;
    }

}

/*package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.Conexao;
import modelo.Pessoa;


public class PessoaDAO {

 public void adicionar(Pessoa pessoa)throws SQLException{
        Connection conexao = new Conexao().getConexao();
        String sql="INSERT INTO pessoa(nome,cep,bairro,numero)VALUE(?,?,?,?)";
        PreparedStatement ps=conexao.prepareStatement(sql);
        ps.setString(1, pessoa.getNome());
        ps.setString(2, pessoa.getCep());
        ps.setString(3, pessoa.getBairro());
        ps.setInt(4, pessoa.getNumero());
        ps.execute();
        ps.close();
        conexao.close();
        
    }


    public List<Pessoa> buscarPeloNome(String nome) throws SQLException {
        Connection conexao = new Conexao().getConexao();
        String sql = "select * from pessoa where nome like ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setString(1, "%" + nome + "%");
        ResultSet rs = ps.executeQuery();
        List<Pessoa> pessoas = new ArrayList<>();
        while (rs.next()) {
            Pessoa pessoa = new Pessoa(rs.getString("nome"), rs.getString("cep"), rs.getString("bairro"), rs.getInt("numero"),rs.getInt("codigo"));
            pessoas.add(pessoa);
        }
        ps.execute();
        ps.close();
        conexao.close();
         return pessoas;
    }
    
    public void remover(int codigo) throws SQLException {
        Connection conexao = new Conexao().getConexao();
        String sql = "delete from pessoa where codigo=?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, codigo);
        ps.executeUpdate();
        ps.close();
        conexao.close();

    }
    
    public void alterar(Pessoa pessoa)throws SQLException{
       Connection conexao = new Conexao().getConexao();
       String sql= "UPDATE pessoa set nome=?,cep=?, bairro=?,numero=?"
                + " where codigo=?";
       PreparedStatement ps = conexao.prepareStatement(sql);
       ps.setString(1, pessoa.getNome());
       ps.setString(2, pessoa.getCep());
       ps.setString(3, pessoa.getBairro());
       ps.setInt(4, pessoa.getNumero());
       ps.setInt(5, pessoa.getCodigo());
       ps.executeUpdate();
       ps.close();
       conexao.close();
       

    }
    
    


}*/
