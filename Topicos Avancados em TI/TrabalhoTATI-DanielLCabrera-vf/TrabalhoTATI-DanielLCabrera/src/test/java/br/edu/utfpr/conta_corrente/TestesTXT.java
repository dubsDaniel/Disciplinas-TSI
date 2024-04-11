/*package br.edu.utfpr.conta_corrente;
public class TestesTXT {
}*/
/* 
GETs
-------------/clientes

-------------/contas

-------------/cartao

-------------/emprestimo

-------------/clientes/1
-------------/clientes/2
-------------/clientes/3
-------------/clientes/4
-------------/contas/1
-------------/contas/2
-------------/contas/3
-------------/contas/4
-------------/cartao/1
-------------/cartao/2
-------------/cartao/3
-------------/emprestimo/1
-------------/emprestimo/2
---------------------------------Clientes
POST
-------------/clientes
{   
    "nome":"Teste",
    "cpf":"99999999999"
}

PUT
-------------/clientes
{   
    "id":5,
    "renda":1000.00,
    "cliente":{
        "id":5,
        "nome":"Teste",
        "cpf":"99999999999"
    }
}



DELETE
-------------/clientes/5
-------------/clientes/1
----------------------------------Contas
POST
-------------/contas
{   
    "renda":500.00,
    "cliente":{
        "id":5
    }
}

PUT
-------------/contas
{   
    "id":5,
    "renda":1000.00,
    "cliente":{
        "id":5
    }
}

{   
    "id":5,
    "renda":500.00,
    "cliente":{
        "id":4
    }
}

DELETE
-------------/contas/5
-------------/contas/1

------------------------------Cartoes
POST
-------------/cartao
{   
    "limite":500.00,
    "valor":0.00,
    "vencimento":19092022,
    "conta":{
        "id":5
    }
}

PUT
-------------/cartao
{   
    "id":4,
    "limite":1000.00,
    "valor":400.00,
    "vencimento":22092022,
    "conta":{
        "id":5
    }
}

{   
    "id":4,
    "limite":1200.00,
    "valor":600.00,
    "vencimento":22092022,
    "conta":{
        "id":4
    }
}

DELETE
-------------/cartao/4
-------------/cartao/1

------------------------------Emprestimos
POST
-------------/emprestimo/pegarEmprestimo
{   
    "valor":1000.00,
    "vencimento":19092022,
    "conta":{
        "id":5,
        "renda":1000
    }
}

{   
    "id":6,
    "valor":1200.00,
    "vencimento":21092022,
    "conta":{
        "id":4
    }
}

DELETE
-------------/emprestimo/3
-------------/emprestimo/1



POSTs
-------------/cartao/compra/2
2.00
-------------/cartao/compra/1
2.00

-------------/cartao/pagamentoFatura/1
10.00
-------------/cartao/pagamentoFatura/3
10.00

---------------/emprestimo/pegarEmprestimo
{
    "valor": 1000.0,
    "vencimento":19092022,
    "conta":{
        "id":5, 
        "renda": 1000.0,
        "cliente":{
            "id":5
        }
    }
}
---------------/emprestimo/pagamentoEmprestimo/3
1000
*/