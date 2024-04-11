<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="dadosAlunoAprovado" scope="session" class="java.util.ArrayList"/>
<jsp:useBean id="dadosAlunoReprovado" scope="session" class="java.util.ArrayList"/>
<jsp:useBean id="msgErro" scope="session" class="java.lang.String"/>
<!DOCTYPE html>
<html>
    <head>
        <title>P1 - Atividade 1</title>
        <link rel="stylesheet" href="tabelas.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Dados do aluno</h1>
        <form action="media" method="POST"> <!-- Envia as informaçoes do aluno para o gerenciador -->
            Nome: <input type="text" name="nome" size="80"/><br/>
            Nota 1: <input type="text" name="nota1" size="12"/>
            Nota 2: <input type="text" name="nota2" size="12"/> <br/>
            <input type="submit" name="Lançar"/>
        </form>
        <c:if test="${not empty msgErro}"> <!-- Caso retorne erro, exiba-o -->
            <p style="color: red">${msgErro}</p>
        </c:if>  
        <c:if test="${empty dadosAlunoAprovado && empty dadosAlunoReprovado}">
            <h3 style="color: red">Nenhum dado cadastrado</h3>
        </c:if>
        <c:if test="${not empty dadosAlunoAprovado || not empty dadosAlunoReprovado}"> <!-- Se pelo menos uma das listas não estiverem vazias entra no bloco -->
            <h1>Resultados: </h1>
            <div class="tabelasAlunos">
                <div class="tabela">
                    <table class="Aprovados">
                        <caption>Aprovados</caption>
                        <tr>
                            <th>Nome</th>
                            <th>Nota 1</th>
                            <th>Nota 2</th>
                            <th>Média</th>
                        </tr>
                        <c:set var="pos" value="0" scope="request"/>
                        <c:forEach items="${dadosAlunoAprovado}" var="aprovados"> <!-- Enquanto tem dados no Array de alunos aprovados busca informações -->
                            <tr>
                                <td>${aprovados.nome}</td>
                                <td>${aprovados.nota1}</td>
                                <td>${aprovados.nota2}</td>
                                <td>${aprovados.media}</td>                    
                            </tr>
                        </c:forEach>
                    </table>
                    <table class="Reprovados">
                        <caption>Reprovados</caption>
                        <tr>
                            <th>Nome</th>
                            <th>Nota 1</th>
                            <th>Nota 2</th>
                            <th>Média</th>
                        </tr>
                        <c:set var="pos" value="0" scope="request"/>
                        <c:forEach items="${dadosAlunoReprovado}" var="reprovados"> <!-- Enquanto tem dados no Array de alunos reprovados busca informações -->
                            <tr>
                                <td>${reprovados.nome}</td>
                                <td>${reprovados.nota1}</td>
                                <td>${reprovados.nota2}</td>
                                <td>${reprovados.media}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>      
            </div>
    
            <form action="media" method="POST"> <!-- Botão para resetar dados -->
                <input type="hidden" name="acao" value="limpar"/>
                <input type="submit" value="Limpar dados"/>
            </form>
        </c:if>
        <c:remove var="msgErro" scope="session"/>
    </body>
</html>
