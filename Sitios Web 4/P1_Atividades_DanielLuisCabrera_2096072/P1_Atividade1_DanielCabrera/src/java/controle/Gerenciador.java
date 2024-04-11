package controle;

import dados.Aluno;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/* @author dudbub
 * 
 */
@WebServlet(name = "Gerenciador", urlPatterns = {"/media"})
public class Gerenciador extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { //Muito semelhante a criação do servlet em sala
        HttpSession sessao = request.getSession();
        
        ArrayList<Aluno> dadosAlunoAprovado = (ArrayList<Aluno>) sessao.getAttribute("dadosAlunoAprovado"); //Um ArrayList para alunos que atingiram a média
        ArrayList<Aluno> dadosAlunoReprovado = (ArrayList<Aluno>) sessao.getAttribute("dadosAlunoReprovado");//Outro ArrayList para alunos que não atingiram a média
        String acao = request.getParameter("acao"); //Recebendo comando acao
        
        if (dadosAlunoAprovado == null) { //Sempre que a sessão é iniciada, gera novo ArrayList
            dadosAlunoAprovado = new ArrayList<Aluno>();
            sessao.setAttribute("dadosAlunoAprovado", dadosAlunoAprovado);
        }
        
        if (dadosAlunoReprovado == null) { //Same
            dadosAlunoReprovado = new ArrayList<Aluno>();
            sessao.setAttribute("dadosAlunoAprovado", dadosAlunoReprovado);
        }
        
        if("limpar".equals(acao)){ //Caso a ação seja "limpar", deve resetar as informações contidas nas listas
            sessao.removeAttribute("dadosAlunoAprovado");
            sessao.removeAttribute("dadosAlunoReprovado");
            response.sendRedirect("index.jsp");
            return;
        }
        
        try {
            String nome = request.getParameter("nome");//recebendo os parametros da sessão
            String n1 = request.getParameter("nota1");
            String n2 = request.getParameter("nota2");
            Double nota1, nota2; //Variaveis do tipo Double para tratar a variavel que é recebida como texto
            
            if (nome == null || nome.trim().isEmpty()) { //Testador se o nome do aluno ta vazio
                throw new Exception("Nome não pode ser vazio.");
            }
            
            if (n1.trim().isEmpty() || n1 == null) {//Testador se a nota está vazia, negativa ou se está acima do limite
                throw new Exception("A primeira nota não pode ser vazia.");
            } else if (Double.parseDouble(n1) < 0) {
                throw new Exception("A primeira nota não pode ser negativa.");
            } else if (Double.parseDouble(n1) > 10) {
                throw new Exception("10 é a nota máxima.");
            } else nota1 = Double.parseDouble(n1);
            
            if (n2.trim().isEmpty() || n2 == null) { //Testador identico ao de cima para nota 2
                throw new Exception("A segunda nota não pode ser vazia.");
            } else if (Double.parseDouble(n2) < 0) {
                throw new Exception("A segunda nota não pode ser negativa.");
            } else if (Double.parseDouble(n2) > 10) {
                throw new Exception("10 é a nota máxima.");
            } else nota2 = Double.parseDouble(n2);
            
            Double media = (((nota1 * 2) + (nota2 * 3))/5);//Calculo da média final
            
            if (media < 7) { //Caso a nota não atinja a média mínima, aluno adicionado na Lista de reprovados
                dadosAlunoReprovado.add(new Aluno(nome, nota1, nota2, media));
            } else { //Caso atinja, adiciona na lista de aprovados
                dadosAlunoAprovado.add(new Aluno(nome, nota1, nota2, media));
            }
            
        } catch(NumberFormatException nfe) { //Pega erros
            sessao.setAttribute("msgErro", "Digite apenas números");
        }
        
    catch (Exception ex) { //Pega erros
        sessao.setAttribute("msgErro", ex.getMessage());
    }
    response.sendRedirect("index.jsp"); //Recarrega página
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
